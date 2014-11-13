package com.liferay.portlet.digest.activity.converter;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermissionUtil;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;
import com.liferay.portlet.digest.activity.util.actions.DLActivityKeys;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.digest.util.PropsValues;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DLFileEntryConverter extends BaseDigestActivityConverter {

	@Override
	public String getActionDisplay(DigestActivity activity) {
		int type = activity.getType();

		switch (type) {
			case DLActivityKeys.ADD_FILE_ENTRY:
				return LanguageUtil.get(activity.getUser().getLocale(), "added-by");
			case DLActivityKeys.UPDATE_FILE_ENTRY:
				return LanguageUtil.get(activity.getUser().getLocale(), "updated-by");
			default:
				return LanguageUtil.get(activity.getUser().getLocale(), "created-by");
		}
	}

	@Override
	public List<Integer> getActions() throws Exception {
		List<Integer> actions = new ArrayList<Integer>();

		actions.add(DLActivityKeys.ADD_FILE_ENTRY);
		actions.add(DLActivityKeys.UPDATE_FILE_ENTRY);

		return actions;
	}

	@Override
	public String getActionName(int action) throws Exception {
		switch (action) {
			case DLActivityKeys.ADD_FILE_ENTRY:
				return "add-file-entry";
			case DLActivityKeys.UPDATE_FILE_ENTRY:
				return "update-file-entry";
			default:
				return null;
		}
	}

	@Override
	public String getLink(BaseModel model) throws Exception {
		DLFileEntry entry = (DLFileEntry) model;

		String link =
				DigestHelperUtil.getPortalURL(entry.getGroupId()) + DigestHelperUtil.getPathMain() +
						"/document_library/get_file?groupId=" +
						entry.getRepositoryId() + "&folderId=" +
						entry.getFolderId() + "&title=" +
						HttpUtil.encodeURL(entry.getTitle());

		return link;
	}

	@Override
	protected DigestActivity doConvert(BaseModel model, User digestUser) throws Exception {
		if (!(model instanceof SocialActivity)) {
			throw new Exception("SocialActivity required!");
		}

		SocialActivity socialActivity = (SocialActivity) model;

		DLFileEntry entry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(
				socialActivity.getClassPK()
		);

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
			digestActivity.setName(entry.getTitle());
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
	protected boolean hasPermission(PermissionChecker permissionChecker, BaseModel model, String actionId) {

		try {
			SocialActivity socialActivity = (SocialActivity) model;

			DLFileEntry entry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(
					socialActivity.getClassPK()
			);

			Boolean hasPermission = StagingPermissionUtil.hasPermission(
					permissionChecker, entry.getGroupId(),
					DLFileEntry.class.getName(), entry.getFileEntryId(),
					PortletKeys.DOCUMENT_LIBRARY, actionId);

			if (hasPermission != null) {
				return hasPermission.booleanValue();
			}

			DLFileVersion latestDLFileVersion = entry.getLatestFileVersion(
					false);

			if (latestDLFileVersion.isPending()) {
				hasPermission = WorkflowPermissionUtil.hasPermission(
						permissionChecker, entry.getGroupId(),
						DLFileEntry.class.getName(), entry.getFileEntryId(),
						actionId);

				if (hasPermission != null) {
					return hasPermission.booleanValue();
				}
			}

			if (PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {
				if (entry.getFolderId() !=
						DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

					DLFolder dlFolder = DLFolderLocalServiceUtil.getFolder(
							entry.getFolderId());

					if (!DLFolderPermission.contains(
							permissionChecker, dlFolder, ActionKeys.ACCESS) &&
							!DLFolderPermission.contains(
									permissionChecker, dlFolder, ActionKeys.VIEW)) {

						return false;
					}
				}
			}

			if (permissionChecker.hasOwnerPermission(
					entry.getCompanyId(), DLFileEntry.class.getName(),
					entry.getFileEntryId(), entry.getUserId(),
					actionId)) {

				return true;
			}

			return permissionChecker.hasPermission(
					entry.getGroupId(), DLFileEntry.class.getName(),
					entry.getFileEntryId(), actionId);
		}
		catch (Throwable t) {
			if (_log.isErrorEnabled()) {
				_log.error(t, t);
			}
		}

		return false;
	}

	private static class DLFolderPermission {
		public static boolean contains(
				PermissionChecker permissionChecker, DLFolder dlFolder,
				String actionId)
		throws PortalException, SystemException {

			if (actionId.equals(ActionKeys.ADD_FOLDER)) {
				actionId = ActionKeys.ADD_SUBFOLDER;
			}

			Boolean hasPermission = StagingPermissionUtil.hasPermission(
					permissionChecker, dlFolder.getGroupId(), DLFolder.class.getName(),
					dlFolder.getFolderId(), PortletKeys.DOCUMENT_LIBRARY, actionId);

			if (hasPermission != null) {
				return hasPermission.booleanValue();
			}

			long folderId = dlFolder.getFolderId();

			if (actionId.equals(ActionKeys.VIEW)) {
				while (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
					dlFolder = DLFolderLocalServiceUtil.getFolder(folderId);

					folderId = dlFolder.getParentFolderId();

					if (!permissionChecker.hasOwnerPermission(
							dlFolder.getCompanyId(), DLFolder.class.getName(),
							dlFolder.getFolderId(), dlFolder.getUserId(),
							actionId) &&
							!permissionChecker.hasPermission(
									dlFolder.getGroupId(), DLFolder.class.getName(),
									dlFolder.getFolderId(), actionId)) {

						return false;
					}

					if (!PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {
						break;
					}
				}

				return true;
			}
			else {
				while (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
					dlFolder = DLFolderLocalServiceUtil.getFolder(folderId);

					folderId = dlFolder.getParentFolderId();

					if (permissionChecker.hasOwnerPermission(
							dlFolder.getCompanyId(), DLFolder.class.getName(),
							dlFolder.getFolderId(), dlFolder.getUserId(),
							actionId)) {

						return true;
					}

					if (permissionChecker.hasPermission(
							dlFolder.getGroupId(), DLFolder.class.getName(),
							dlFolder.getFolderId(), actionId)) {

						return true;
					}
				}

				return false;
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(DLFileEntryConverter.class);

}
