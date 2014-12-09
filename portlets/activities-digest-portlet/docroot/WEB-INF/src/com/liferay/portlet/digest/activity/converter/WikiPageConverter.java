package com.liferay.portlet.digest.activity.converter;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermissionUtil;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;
import com.liferay.portlet.digest.activity.util.actions.WikiActivityKeys;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.digest.util.PropsValues;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WikiPageConverter extends BaseDigestActivityConverter {

	@Override
	public String getActionDisplay(DigestActivity activity) {
		int type = activity.getType();

		switch (type) {
			case WikiActivityKeys.ADD_COMMENT:
				return LanguageUtil.get(activity.getUser().getLocale(), "commented-on");
			case WikiActivityKeys.ADD_PAGE:
				return LanguageUtil.get(activity.getUser().getLocale(), "added-by");
			case WikiActivityKeys.UPDATE_PAGE:
				return LanguageUtil.get(activity.getUser().getLocale(), "updated-by");
			default:
				return LanguageUtil.get(activity.getUser().getLocale(), "created-by");
		}
	}

	@Override
	public List<Integer> getActions() throws Exception {
		List<Integer> actions = new ArrayList<Integer>();

		actions.add(WikiActivityKeys.ADD_COMMENT);
		actions.add(WikiActivityKeys.ADD_PAGE);
		actions.add(WikiActivityKeys.UPDATE_PAGE);

		return actions;
	}

	@Override
	public String getActionName(int action) throws Exception {
		switch (action) {
			case WikiActivityKeys.ADD_COMMENT:
				return "add-comment";
			case WikiActivityKeys.ADD_PAGE:
				return "add-pages";
			case WikiActivityKeys.UPDATE_PAGE:
				return "update-pages";
			default:
				return null;
		}
	}

	@Override
	public String getLink(BaseModel model) throws Exception {
		WikiPage page = (WikiPage) model;

		String link =
				DigestHelperUtil.getPortalURL(page.getGroupId()) + DigestHelperUtil.getPathMain() +
						"/wiki/find_page?pageResourcePrimKey=" + page.getResourcePrimKey();

		return link;
	}

	@Override
	protected DigestActivity doConvert(BaseModel model, User digestUser) throws Exception {
		if (!(model instanceof SocialActivity)) {
			throw new Exception("SocialActivity required!");
		}

		SocialActivity socialActivity = (SocialActivity) model;

		WikiPage page = WikiPageLocalServiceUtil.getPage(
				socialActivity.getClassPK());

		DigestActivity digestActivity = null;

		if (Validator.isNotNull(page)) {
			digestActivity = DigestActivityFactoryUtil.getDigestActivity(
					page.getModelClassName());

			String summary = StringUtil.shorten(
				HtmlUtil.extractText(page.getContent()),
					PropsValues.DIGEST_ACTIVITY_SUMMARY_LENGTH);

			User modelUser = UserLocalServiceUtil.fetchUser(socialActivity.getUserId());

			digestActivity.setUser(modelUser);

			digestActivity.setActivityType(
					DigestActivityFactoryUtil.getDigestActivityType(page.getModelClassName()));

			digestActivity.setType(socialActivity.getType());

			digestActivity.setId(page.getPrimaryKey());
			digestActivity.setCreateDate(new Date(socialActivity.getCreateDate()));
			digestActivity.setExtraData(socialActivity.getExtraData());
			digestActivity.setImageURL(DigestHelperUtil.getDefaultImagePath(page.getCompanyId()));
			digestActivity.setName(
					LocalizationUtil.getLocalization(page.getTitle(), LocaleUtil.toLanguageId(digestUser.getLocale())));
			digestActivity.setLink(getLink(page));
			digestActivity.setLinkDisplay(getLinkDisplay(digestActivity, digestUser));
			digestActivity.setSummary(summary);
			digestActivity.setUuid(page.getUuid());
		}

		return digestActivity;
	}

	@Override
	protected boolean hasPermission(PermissionChecker permissionChecker, BaseModel model, String actionId) {

		try {
			WikiPage page = null;

			if (model instanceof SocialActivity) {
				SocialActivity socialActivity = (SocialActivity) model;

				page = WikiPageLocalServiceUtil.getPage(
							socialActivity.getClassPK());
			}
			else if (model instanceof WikiPage) {
				page = (WikiPage) model;
			}

			if (actionId.equals(ActionKeys.VIEW)) {
				WikiPage redirectPage = page.getRedirectPage();

				if (redirectPage != null) {
					page = redirectPage;
				}
			}

			if (page.isPending()) {
				Boolean hasPermission = WorkflowPermissionUtil.hasPermission(
						permissionChecker, page.getGroupId(), WikiPage.class.getName(),
						page.getResourcePrimKey(), actionId);

				if (hasPermission != null) {
					return hasPermission.booleanValue();
				}
			}

			if (page.isDraft() && actionId.equals(ActionKeys.DELETE) &&
					(page.getStatusByUserId() == permissionChecker.getUserId())) {

				return true;
			}

			if (permissionChecker.hasOwnerPermission(
					page.getCompanyId(), WikiPage.class.getName(),
					page.getResourcePrimKey(), page.getUserId(), actionId)) {

				return true;
			}

			if (PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {
				if (!WikiNodePermission.contains(
						permissionChecker, page.getNode(), ActionKeys.VIEW)) {

					return false;
				}

				WikiPage parentPage = page.getParentPage();

				if ((parentPage != null) &&
						!hasPermission(permissionChecker, parentPage, ActionKeys.VIEW)) {

					return false;
				}
			}

			return permissionChecker.hasPermission(
					page.getGroupId(), WikiPage.class.getName(),
					page.getResourcePrimKey(), actionId);
		}
		catch (Throwable t) {
			if (_log.isErrorEnabled()) {
				_log.error(t, t);
			}
		}
		return false;


	}

	private static class WikiNodePermission {
		public static boolean contains(
				PermissionChecker permissionChecker, WikiNode node, String actionId) {

			if (permissionChecker.hasOwnerPermission(
					node.getCompanyId(), WikiNode.class.getName(), node.getNodeId(),
					node.getUserId(), actionId)) {

				return true;
			}

			return permissionChecker.hasPermission(
					node.getGroupId(), WikiNode.class.getName(), node.getNodeId(),
					actionId);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(WikiPageConverter.class);

}
