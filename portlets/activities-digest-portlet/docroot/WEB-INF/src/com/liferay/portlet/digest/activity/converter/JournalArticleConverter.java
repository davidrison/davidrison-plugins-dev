package com.liferay.portlet.digest.activity.converter;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.digest.util.PropsValues;
import com.liferay.portlet.journal.model.JournalArticle;

import java.util.ArrayList;
import java.util.List;

public class JournalArticleConverter extends BaseDigestActivityConverter {

	@Override
	public String getActionDisplay(DigestActivity activity) {
		int type = activity.getType();

		switch (type) {
			case ADD_WEB_CONTENT:
				return LanguageUtil.get(activity.getUser().getLocale(), "added-by");
			case UPDATE_WEB_CONTENT:
				return LanguageUtil.get(activity.getUser().getLocale(), "updated-by");
			default:
				return LanguageUtil.get(activity.getUser().getLocale(), "created-by");
		}
	}

	@Override
	public List<Integer> getActions() throws Exception {
		List<Integer> actions = new ArrayList<Integer>();

		actions.add(ADD_WEB_CONTENT);
		actions.add(UPDATE_WEB_CONTENT);

		return actions;
	}

	@Override
	public String getActionName(int action) throws Exception {
		switch (action) {
			case ADD_WEB_CONTENT:
				return "add-web-content";
			case UPDATE_WEB_CONTENT:
				return "update-web-content";
			default:
				return null;
		}
	}

	@Override
	public String getLink(BaseModel model) throws Exception {
		JournalArticle journalArticle = (JournalArticle) model;

		String link =
				DigestHelperUtil.getPortalURL(journalArticle.getGroupId()) + DigestHelperUtil.getPathMain() +
						"/journal/view_article_content?cmd=view&groupId=" + journalArticle.getGroupId() + "&articleId=" +
						journalArticle.getArticleId() + "&version=" + journalArticle.getVersion();

		return link;
	}

	protected JSONObject getJournalArticleAsJSON(JournalArticle article) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("uuid", article.getUuid());
		jsonObject.put("articleId", article.getArticleId());
		jsonObject.put("description", article.getDescription());

		return jsonObject;
	}

	@Override
	protected DigestActivity doConvert(BaseModel model, User digestUser) throws Exception {
		if (!(model instanceof JournalArticle)) {
			throw new Exception("JournalArticle required!");
		}

		JournalArticle article = (JournalArticle) model;

		DigestActivity digestActivity = null;

		if (Validator.isNotNull(article)) {
			digestActivity = DigestActivityFactoryUtil.getDigestActivity(
					article.getModelClassName());

			User modelUser = UserLocalServiceUtil.fetchUser(article.getUserId());

			digestActivity.setUser(modelUser);

			String summary = StringUtil.shorten(
				HtmlUtil.extractText(article.getContent()),
					PropsValues.DIGEST_ACTIVITY_SUMMARY_LENGTH);

			digestActivity.setActivityType(
					DigestActivityFactoryUtil.getDigestActivityType(article.getModelClassName()));

			digestActivity.setType(article.getModifiedDate().equals(article.getCreateDate()) ? ADD_WEB_CONTENT : UPDATE_WEB_CONTENT);

			digestActivity.setId(article.getPrimaryKey());
			digestActivity.setCreateDate(article.getCreateDate());
			digestActivity.setExtraData(getJournalArticleAsJSON(article).toString());
			digestActivity.setGroupId(article.getGroupId());
			digestActivity.setImageURL(DigestHelperUtil.getDefaultImagePath(article.getCompanyId()));
			digestActivity.setName(
					LocalizationUtil.getLocalization(article.getTitle(), LocaleUtil.toLanguageId(digestUser.getLocale())));
			digestActivity.setLink(getLink(article));
			digestActivity.setLinkDisplay(getLinkDisplay(digestActivity, digestUser));
			digestActivity.setSummary(summary);
			digestActivity.setUuid(article.getUuid());
		}

		return digestActivity;
	}

	@Override
	protected boolean hasPermission(PermissionChecker permissionChecker, BaseModel model, String actionId) {

		try {
			if (!(model instanceof JournalArticle)) {
				return false;
			}

			JournalArticle article = (JournalArticle) model;

			Boolean hasPermission = StagingPermissionUtil.hasPermission(
					permissionChecker, article.getGroupId(),
					JournalArticle.class.getName(), article.getResourcePrimKey(),
					PortletKeys.JOURNAL, actionId);

			if (hasPermission != null) {
				return hasPermission.booleanValue();
			}

			if (article.isPending()) {
				hasPermission = WorkflowPermissionUtil.hasPermission(
						permissionChecker, article.getGroupId(),
						JournalArticle.class.getName(), article.getResourcePrimKey(),
						actionId);

				if (hasPermission != null) {
					return hasPermission.booleanValue();
				}
			}

			if (permissionChecker.hasOwnerPermission(
					article.getCompanyId(), JournalArticle.class.getName(),
					article.getResourcePrimKey(), article.getUserId(), actionId)) {

				return true;
			}

			return permissionChecker.hasPermission(
					article.getGroupId(), JournalArticle.class.getName(),
					article.getResourcePrimKey(), actionId);
		}
		catch (Throwable t) {
			if (_log.isErrorEnabled()) {
				_log.error(t, t);
			}
		}

		return false;
	}

	public static final int ADD_WEB_CONTENT = 1;
	public static final int UPDATE_WEB_CONTENT = 2;

	private static final Log _log = LogFactoryUtil.getLog(JournalArticleConverter.class);

}
