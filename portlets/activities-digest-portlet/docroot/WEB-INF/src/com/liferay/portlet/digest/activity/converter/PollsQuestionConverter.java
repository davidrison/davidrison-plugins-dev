package com.liferay.portlet.digest.activity.converter;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.digest.util.PropsValues;
import com.liferay.portlet.polls.model.PollsQuestion;

import java.util.ArrayList;
import java.util.List;

public class PollsQuestionConverter extends BaseDigestActivityConverter {

	@Override
	public String getActionDisplay(DigestActivity activity) {
		int type = activity.getType();

		switch (type) {
			case ADD_POLL:
				return LanguageUtil.get(activity.getUser().getLocale(), "added-by");
			case UPDATE_POLL:
				return LanguageUtil.get(activity.getUser().getLocale(), "updated-by");
			default:
				return LanguageUtil.get(activity.getUser().getLocale(), "created-by");
		}
	}

	@Override
	public List<Integer> getActions() throws Exception {
		List<Integer> actions = new ArrayList<Integer>();

		actions.add(ADD_POLL);
		actions.add(UPDATE_POLL);

		return actions;
	}

	@Override
	public String getActionName(int action) throws Exception {
		switch (action) {
			case ADD_POLL:
				return "add-poll";
			case UPDATE_POLL:
				return "update-poll";
			default:
				return null;
		}
	}

	@Override
	public String getLink(BaseModel model) throws Exception {
		PollsQuestion pollsQuestion = (PollsQuestion) model;

		String link =
				DigestHelperUtil.getPortalURL(pollsQuestion.getGroupId()) + DigestHelperUtil.getPathMain() +
						"/polls/view_question?questionId=" + pollsQuestion.getQuestionId();

		return link;
	}

	@Override
	protected DigestActivity doConvert(BaseModel model, User digestUser) throws Exception {
		if (!(model instanceof PollsQuestion)) {
			throw new Exception("PollsQuestion required!");
		}

		PollsQuestion question = (PollsQuestion) model;

		DigestActivity digestActivity = null;

		if (Validator.isNotNull(question)) {
			digestActivity = DigestActivityFactoryUtil.getDigestActivity(
					question.getModelClassName());

			User modelUser = UserLocalServiceUtil.fetchUser(question.getUserId());

			digestActivity.setUser(modelUser);

			String summary = StringUtil.shorten(
				HtmlUtil.extractText(question.getDescription()),
					PropsValues.DIGEST_ACTIVITY_SUMMARY_LENGTH);

			digestActivity.setActivityType(
					DigestActivityFactoryUtil.getDigestActivityType(question.getModelClassName()));

			digestActivity.setType(question.getModifiedDate().equals(question.getCreateDate()) ? ADD_POLL : UPDATE_POLL);

			digestActivity.setId(question.getPrimaryKey());
			digestActivity.setCreateDate(question.getCreateDate());
			digestActivity.setExtraData(StringPool.BLANK);
			digestActivity.setGroupId(question.getGroupId());
			digestActivity.setImageURL(DigestHelperUtil.getDefaultImagePath(question.getCompanyId()));
			digestActivity.setName(question.getTitle());
			digestActivity.setName(
					LocalizationUtil.getLocalization(question.getTitle(), LocaleUtil.toLanguageId(digestUser.getLocale())));
			digestActivity.setLink(getLink(question));
			digestActivity.setLinkDisplay(getLinkDisplay(digestActivity, digestUser));
			digestActivity.setSummary(summary);
			digestActivity.setUuid(question.getUuid());
		}

		return digestActivity;
	}

	@Override
	protected boolean hasPermission(PermissionChecker permissionChecker, BaseModel model, String actionId) {

		try {
			if (!(model instanceof PollsQuestion)) {
				return false;
			}

			PollsQuestion question = (PollsQuestion) model;

			if (permissionChecker.hasOwnerPermission(
					question.getCompanyId(), PollsQuestion.class.getName(),
					question.getQuestionId(), question.getUserId(), actionId)) {

				return true;
			}

			return permissionChecker.hasPermission(
					question.getGroupId(), PollsQuestion.class.getName(),
					question.getQuestionId(), actionId);
		}
		catch (Throwable t) {
			if (_log.isErrorEnabled()) {
				_log.error(t, t);
			}
		}
		return false;
	}

	public static final int ADD_POLL = 1;
	public static final int UPDATE_POLL = 2;

	private static final Log _log = LogFactoryUtil.getLog(PollsQuestionConverter.class);

}
