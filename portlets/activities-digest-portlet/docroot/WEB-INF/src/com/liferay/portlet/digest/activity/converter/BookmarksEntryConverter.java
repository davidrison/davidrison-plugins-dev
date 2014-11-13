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
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.model.BookmarksFolderConstants;
import com.liferay.portlet.bookmarks.service.BookmarksEntryLocalServiceUtil;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;
import com.liferay.portlet.digest.activity.util.actions.BookmarksActivityKeys;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.digest.util.PropsValues;
import com.liferay.portlet.social.model.SocialActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookmarksEntryConverter extends BaseDigestActivityConverter {

	@Override
	public String getActionDisplay(DigestActivity activity) {
		int type = activity.getType();

		switch (type) {
			case BookmarksActivityKeys.ADD_EVENT:
				return LanguageUtil.get(activity.getUser().getLocale(), "added-by");
			case BookmarksActivityKeys.UPDATE_EVENT:
				return LanguageUtil.get(activity.getUser().getLocale(), "updated-by");
			default:
				return LanguageUtil.get(activity.getUser().getLocale(), "created-by");
		}
	}

	@Override
	public List<Integer> getActions() throws Exception {
		List<Integer> actions = new ArrayList<Integer>();

		actions.add(BookmarksActivityKeys.ADD_EVENT);
		actions.add(BookmarksActivityKeys.UPDATE_EVENT);

		return actions;
	}

	@Override
	public String getActionName(int action) throws Exception {
		switch (action) {
			case BookmarksActivityKeys.ADD_EVENT:
				return "add-event";
			case BookmarksActivityKeys.UPDATE_EVENT:
				return "update-event";
			default:
				return null;
		}
	}

	@Override
	public String getLink(BaseModel model) throws Exception {
		BookmarksEntry entry = (BookmarksEntry) model;

		return DigestHelperUtil.getPortalURL(entry.getGroupId()) + DigestHelperUtil.getPathMain() +
				"/bookmarks/find_entry?entryId=" + entry.getPrimaryKey();
	}

	@Override
	protected DigestActivity doConvert(BaseModel model, User digestUser) throws Exception {
		if (!(model instanceof SocialActivity)) {
			throw new Exception("SocialActivity required!");
		}

		SocialActivity socialActivity = (SocialActivity) model;

		BookmarksEntry entry = BookmarksEntryLocalServiceUtil.fetchBookmarksEntry(
				socialActivity.getClassPK());

		DigestActivity digestActivity = null;

		if (Validator.isNotNull(entry)) {
			digestActivity = DigestActivityFactoryUtil.getDigestActivity(
					entry.getModelClassName());

			User modelUser = UserLocalServiceUtil.fetchUser(socialActivity.getUserId());

			digestActivity.setUser(modelUser);

			String summary = StringUtil.shorten(
				HtmlUtil.extractText(entry.getDescription()),
					PropsValues.DIGEST_ACTIVITY_SUMMARY_LENGTH);

			digestActivity.setActivityType(
					DigestActivityFactoryUtil.getDigestActivityType(entry.getModelClassName()));

			digestActivity.setType(socialActivity.getType());

			digestActivity.setId(entry.getPrimaryKey());
			digestActivity.setCreateDate(new Date(socialActivity.getCreateDate()));
			digestActivity.setExtraData(socialActivity.getExtraData());
			digestActivity.setGroupId(entry.getGroupId());
			digestActivity.setImageURL(DigestHelperUtil.getDefaultImagePath(entry.getCompanyId()));
			digestActivity.setName(
					LocalizationUtil.getLocalization(entry.getName(), LocaleUtil.toLanguageId(digestUser.getLocale())));
			digestActivity.setLink(getLink(entry));
			digestActivity.setLinkDisplay(getLinkDisplay(digestActivity, digestUser));
			digestActivity.setSummary(summary);
			digestActivity.setUuid((entry.getUuid()));
		}

		return digestActivity;
	}

	@Override
	protected boolean hasPermission(PermissionChecker permissionChecker, BaseModel model, String actionId) {

		try {
			SocialActivity socialActivity = (SocialActivity) model;

			BookmarksEntry entry = BookmarksEntryLocalServiceUtil.fetchBookmarksEntry(
					socialActivity.getClassPK());

			if (PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {
				if (entry.getFolderId() !=
						BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

					BookmarksFolder folder = entry.getFolder();

					if (!BookmarksFolderPermission.contains(
							permissionChecker, folder, ActionKeys.ACCESS) &&
							!BookmarksFolderPermission.contains(
									permissionChecker, folder, ActionKeys.VIEW)) {

						return false;
					}
				}
			}

			if (permissionChecker.hasOwnerPermission(
					entry.getCompanyId(), BookmarksEntry.class.getName(),
					entry.getEntryId(), entry.getUserId(), actionId)) {

				return true;
			}

			return permissionChecker.hasPermission(
					entry.getGroupId(), BookmarksEntry.class.getName(),
					entry.getEntryId(), actionId);
		}
		catch (Throwable t) {
			if (_log.isErrorEnabled()) {
				_log.error(t, t);
			}
		}

		return false;
	}

	private static class BookmarksFolderPermission {
		public static boolean contains(
				PermissionChecker permissionChecker, BookmarksFolder folder,
				String actionId)
		throws PortalException, SystemException {

			if (actionId.equals(ActionKeys.ADD_FOLDER)) {
				actionId = ActionKeys.ADD_SUBFOLDER;
			}

			long folderId = folder.getFolderId();

			if (actionId.equals(ActionKeys.VIEW)) {
				while (folderId !=
						BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

					folder = BookmarksFolderLocalServiceUtil.getFolder(folderId);

					folderId = folder.getParentFolderId();

					if (!permissionChecker.hasOwnerPermission(
							folder.getCompanyId(), BookmarksFolder.class.getName(),
							folder.getFolderId(), folder.getUserId(), actionId) &&
							!permissionChecker.hasPermission(
									folder.getGroupId(), BookmarksFolder.class.getName(),
									folder.getFolderId(), actionId)) {

						return false;
					}

					if (!PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {
						break;
					}
				}

				return true;
			}
			else {
				while (folderId !=
						BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

					folder = BookmarksFolderLocalServiceUtil.getFolder(folderId);

					folderId = folder.getParentFolderId();

					if (permissionChecker.hasOwnerPermission(
							folder.getCompanyId(), BookmarksFolder.class.getName(),
							folder.getFolderId(), folder.getUserId(), actionId)) {

						return true;
					}

					if (permissionChecker.hasPermission(
							folder.getGroupId(), BookmarksFolder.class.getName(),
							folder.getFolderId(), actionId)) {

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

	private static final Log _log = LogFactoryUtil.getLog(BookmarksEntryConverter.class);

}
