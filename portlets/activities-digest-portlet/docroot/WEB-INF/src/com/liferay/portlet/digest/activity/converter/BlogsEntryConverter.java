package com.liferay.portlet.digest.activity.converter;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermissionUtil;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;
import com.liferay.portlet.digest.activity.util.actions.BlogsActivityKeys;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.digest.util.PortletKeys;
import com.liferay.portlet.digest.util.PropsValues;
import com.liferay.portlet.social.model.SocialActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogsEntryConverter extends BaseDigestActivityConverter {

	@Override
	public String getActionDisplay(DigestActivity activity) {
		int type = activity.getType();

		switch (type) {
			case BlogsActivityKeys.ADD_COMMENT:
				return LanguageUtil.get(activity.getUser().getLocale(), "commented-on");
			case BlogsActivityKeys.ADD_ENTRY:
				return LanguageUtil.get(activity.getUser().getLocale(), "added-by");
			case BlogsActivityKeys.UPDATE_ENTRY:
				return LanguageUtil.get(activity.getUser().getLocale(), "updated-by");
			default:
				return LanguageUtil.get(activity.getUser().getLocale(), "created-by");
		}
	}

	@Override
	public List<Integer> getActions() throws Exception {
		List<Integer> actions = new ArrayList<Integer>();

		actions.add(BlogsActivityKeys.ADD_COMMENT);
		actions.add(BlogsActivityKeys.ADD_ENTRY);
		actions.add(BlogsActivityKeys.UPDATE_ENTRY);

		return actions;
	}

	@Override
	public String getActionName(int action) throws Exception {
		switch (action) {
			case BlogsActivityKeys.ADD_COMMENT:
				return "add-comment";
			case BlogsActivityKeys.ADD_ENTRY:
				return "add-blogs";
			case BlogsActivityKeys.UPDATE_ENTRY:
				return "update-blogs";
			default:
				return null;
		}
	}

	@Override
	public String getLink(BaseModel model) throws Exception {
		BlogsEntry entry = (BlogsEntry) model;

		String link =
				DigestHelperUtil.getPortalURL(entry.getGroupId()) + DigestHelperUtil.getPathMain() +
						"/blogs/find_entry?entryId=" + entry.getPrimaryKey();

		return link;
	}

	@Override
	public String getLinkDisplay(DigestActivity digestActivity, User digestUser) throws Exception {
		return super.getLinkDisplay(digestActivity, digestUser);
	}

	@Override
	protected DigestActivity doConvert(BaseModel model, User digestUser) throws Exception {
		if (!(model instanceof SocialActivity)) {
			throw new Exception("SocialActivity required!");
		}

		SocialActivity socialActivity = (SocialActivity) model;

		BlogsEntry entry = BlogsEntryLocalServiceUtil.fetchBlogsEntry(
				socialActivity.getClassPK());

		DigestActivity digestActivity = null;

		if (Validator.isNotNull(entry)) {
			digestActivity = DigestActivityFactoryUtil.getDigestActivity(
					entry.getModelClassName());

			User modelUser = UserLocalServiceUtil.fetchUser(socialActivity.getUserId());

			digestActivity.setUser(modelUser);

			String summary = StringUtil.shorten(
				HtmlUtil.extractText(entry.getContent()),
					PropsValues.DIGEST_ACTIVITY_SUMMARY_LENGTH);

			digestActivity.setActivityType(
					DigestActivityFactoryUtil.getDigestActivityType(entry.getModelClassName()));

			digestActivity.setType(socialActivity.getType());

			digestActivity.setCreateDate(new Date(socialActivity.getCreateDate()));
			digestActivity.setExtraData(socialActivity.getExtraData());
			digestActivity.setGroupId(entry.getGroupId());
			digestActivity.setId(entry.getPrimaryKey());
			digestActivity.setImageURL(DigestHelperUtil.getDefaultImagePath(entry.getCompanyId()));
			digestActivity.setName(
					LocalizationUtil.getLocalization(entry.getTitle(), LocaleUtil.toLanguageId(digestUser.getLocale())));
			digestActivity.setLink(getLink(entry));
			digestActivity.setLinkDisplay(getLinkDisplay(digestActivity, digestUser));
			digestActivity.setSummary(summary);
			digestActivity.setUuid(entry.getUuid());
		}

		return digestActivity;
	}

	@Override
	protected boolean hasPermission(
			PermissionChecker permissionChecker, BaseModel model, String actionId) {

		try {
			SocialActivity socialActivity = (SocialActivity) model;

			BlogsEntry entry = BlogsEntryLocalServiceUtil.fetchBlogsEntry(
					socialActivity.getClassPK());

			Boolean hasPermission = StagingPermissionUtil.hasPermission(
					permissionChecker, entry.getGroupId(), BlogsEntry.class.getName(),
					entry.getEntryId(), PortletKeys.BLOGS, actionId);

			if (hasPermission != null) {
				return hasPermission.booleanValue();
			}

			if (entry.isPending()) {
				hasPermission = WorkflowPermissionUtil.hasPermission(
						permissionChecker, entry.getGroupId(),
						BlogsEntry.class.getName(), entry.getEntryId(), actionId);

				if (hasPermission != null) {
					return hasPermission.booleanValue();
				}
			}

			if (permissionChecker.hasOwnerPermission(
					entry.getCompanyId(), BlogsEntry.class.getName(),
					entry.getEntryId(), entry.getUserId(), actionId)) {

				return true;
			}

			return permissionChecker.hasPermission(
					entry.getGroupId(), BlogsEntry.class.getName(), entry.getEntryId(),
					actionId);
		}
		catch (Throwable t) {
			if (_log.isErrorEnabled()) {
				_log.error(t, t);
			}
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(BlogsEntryConverter.class);

}
