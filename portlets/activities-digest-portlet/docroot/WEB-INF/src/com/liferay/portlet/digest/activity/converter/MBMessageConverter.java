package com.liferay.portlet.digest.activity.converter;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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
import com.liferay.portlet.digest.activity.util.actions.MBActivityKeys;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.digest.util.PropsValues;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBBanLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MBMessageConverter extends BaseDigestActivityConverter  {

	@Override
	public String getActionDisplay(DigestActivity activity) {
		int type = activity.getType();

		switch (type) {
			case MBActivityKeys.ADD_MESSAGE:
				return LanguageUtil.get(activity.getUser().getLocale(), "added-by");
			case MBActivityKeys.REPLY_MESSAGE:
				return LanguageUtil.get(activity.getUser().getLocale(), "replied-to");
			default:
				return LanguageUtil.get(activity.getUser().getLocale(), "created-by");
		}
	}

	@Override
	public List<Integer> getActions() throws Exception {
		List<Integer> actions = new ArrayList<Integer>();

		actions.add(MBActivityKeys.ADD_MESSAGE);
		actions.add(MBActivityKeys.REPLY_MESSAGE);

		return actions;
	}

	@Override
	public String getActionName(int action) throws Exception {
		switch (action) {
			case MBActivityKeys.ADD_MESSAGE:
				return "add-message";
			case MBActivityKeys.REPLY_MESSAGE:
				return "reply-message";
			default:
				return null;
		}
	}

	@Override
	public String getLink(BaseModel model) throws Exception {
		MBMessage message = (MBMessage) model;

		String link =
				DigestHelperUtil.getPortalURL(message.getGroupId()) + DigestHelperUtil.getPathMain() +
						"/message_boards/find_message?messageId=" +
						message.getMessageId();

		return link;
	}

	@Override
	protected DigestActivity doConvert(BaseModel model, User digestUser) throws Exception {
		if (!(model instanceof SocialActivity)) {
			throw new Exception("SocialActivity required!");
		}

		SocialActivity socialActivity = (SocialActivity) model;

		MBMessage message = MBMessageLocalServiceUtil.fetchMBMessage(
				socialActivity.getClassPK());

		DigestActivity digestActivity = null;

		if (Validator.isNotNull(message)) {
			digestActivity = DigestActivityFactoryUtil.getDigestActivity(
					message.getModelClassName());

			User modelUser = UserLocalServiceUtil.fetchUser(socialActivity.getUserId());

			digestActivity.setUser(modelUser);

			String summary = StringUtil.shorten(
				HtmlUtil.extractText(message.getBody()),
					PropsValues.DIGEST_ACTIVITY_SUMMARY_LENGTH);

			digestActivity.setActivityType(
					DigestActivityFactoryUtil.getDigestActivityType(message.getModelClassName()));

			digestActivity.setType(socialActivity.getType());

			digestActivity.setId(message.getPrimaryKey());
			digestActivity.setCreateDate(new Date(socialActivity.getCreateDate()));
			digestActivity.setExtraData(socialActivity.getExtraData());
			digestActivity.setGroupId(message.getGroupId());
			digestActivity.setImageURL(DigestHelperUtil.getDefaultImagePath(message.getCompanyId()));
			digestActivity.setName(
					LocalizationUtil.getLocalization(message.getSubject(), LocaleUtil.toLanguageId(digestUser.getLocale())));
			digestActivity.setLink(getLink(message));
			digestActivity.setLinkDisplay(getLinkDisplay(digestActivity, digestUser));
			digestActivity.setSummary(summary);
			digestActivity.setUuid(message.getUuid());
		}

		return digestActivity;
	}

	@Override
	protected boolean hasPermission(PermissionChecker permissionChecker, BaseModel model, String actionId) {

		try {
			SocialActivity socialActivity = (SocialActivity) model;

			MBMessage message = MBMessageLocalServiceUtil.fetchMBMessage(
					socialActivity.getClassPK());

			long groupId = message.getGroupId();

			if (message.isPending()) {
				Boolean hasPermission = WorkflowPermissionUtil.hasPermission(
						permissionChecker, message.getGroupId(),
						message.getWorkflowClassName(), message.getMessageId(),
						actionId);

				if (hasPermission != null) {
					return hasPermission.booleanValue();
				}
			}

			if (MBBanLocalServiceUtil.hasBan(
					groupId, permissionChecker.getUserId())) {

				return false;
			}

			long categoryId = message.getCategoryId();

			if ((categoryId != MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) &&
					(categoryId != MBCategoryConstants.DISCUSSION_CATEGORY_ID)) {

				MBCategory category = MBCategoryLocalServiceUtil.getCategory(
						categoryId);

				if (PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {
					if (!MBCategoryPermission.contains(
							permissionChecker, category, ActionKeys.VIEW)) {

						return false;
					}
				}
			}

			if (permissionChecker.hasOwnerPermission(
					message.getCompanyId(), MBMessage.class.getName(),
					message.getRootMessageId(), message.getUserId(), actionId)) {

				return true;
			}

			return permissionChecker.hasPermission(
					groupId, MBMessage.class.getName(), message.getMessageId(),
					actionId);
		}
		catch (Throwable t) {
			if (_log.isErrorEnabled()) {
				_log.error(t, t);
			}
		}

		return false;
	}

	private static class MBCategoryPermission {
		public static boolean contains(
				PermissionChecker permissionChecker, MBCategory category,
				String actionId)
				throws PortalException, SystemException {

			if (actionId.equals(ActionKeys.ADD_CATEGORY)) {
				actionId = ActionKeys.ADD_SUBCATEGORY;
			}

			if (MBBanLocalServiceUtil.hasBan(
					category.getGroupId(), permissionChecker.getUserId())) {

				return false;
			}

			long categoryId = category.getCategoryId();

			if (actionId.equals(ActionKeys.VIEW)) {
				while (categoryId !=
						MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {

					category = MBCategoryLocalServiceUtil.getCategory(categoryId);

					categoryId = category.getParentCategoryId();

					if (!permissionChecker.hasOwnerPermission(
							category.getCompanyId(), MBCategory.class.getName(),
							category.getCategoryId(), category.getUserId(),
							actionId) &&
							!permissionChecker.hasPermission(
									category.getGroupId(), MBCategory.class.getName(),
									category.getCategoryId(), actionId)) {

						return false;
					}

					if (!PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {
						break;
					}
				}

				return true;
			}
			else {
				while (categoryId !=
						MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {

					category = MBCategoryLocalServiceUtil.getCategory(categoryId);

					categoryId = category.getParentCategoryId();

					if (permissionChecker.hasOwnerPermission(
							category.getCompanyId(), MBCategory.class.getName(),
							category.getCategoryId(), category.getUserId(),
							actionId)) {

						return true;
					}

					if (permissionChecker.hasPermission(
							category.getGroupId(), MBCategory.class.getName(),
							category.getCategoryId(), actionId)) {

						return true;
					}

					if (actionId.equals(ActionKeys.VIEW)) {
						break;
					}
				}

				return false;
			}
		}
	}
	private static final Log _log = LogFactoryUtil.getLog(MBMessageConverter.class);

}
