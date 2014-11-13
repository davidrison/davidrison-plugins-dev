package com.liferay.portlet.digest.activity.converter;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.social.model.SocialRelation;
import com.liferay.portlet.social.model.SocialRelationConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SocialRelationConverter extends BaseDigestActivityConverter {

	@Override
	public String getActionDisplay(DigestActivity activity) {
		int type = activity.getType();

		switch (type) {
			case ADD_CONNECTION:
				return LanguageUtil.get(activity.getUser().getLocale(), "added-by");
			case NEW_FOLLOWER:
				return LanguageUtil.get(activity.getUser().getLocale(), "new-followers");
			default:
				return LanguageUtil.get(activity.getUser().getLocale(), "created-by");
		}
	}

	@Override
	public List<Integer> getActions() throws Exception {
		List<Integer> actions = new ArrayList<Integer>();

		actions.add(ADD_CONNECTION);
		actions.add(NEW_FOLLOWER);

		return actions;
	}

	@Override
	public String getActionName(int action) throws Exception {
		switch (action) {
			case ADD_CONNECTION:
				return "add-connection";
			case NEW_FOLLOWER:
				return "new-followers";
			default:
				return null;
		}
	}

	@Override
	public String getLink(BaseModel model) throws Exception {
		SocialRelation SocialRelation = (SocialRelation) model;

		long userId = SocialRelation.getUserId1();

		User user = UserLocalServiceUtil.fetchUser(userId);

		String link = user.getDisplayURL(
				DigestHelperUtil.getPortalURL(
						DigestHelperUtil.getDefaultGroup().getGroupId()), DigestHelperUtil.getPathMain());

		return link;
	}

	@Override
	public String getLinkDisplay(DigestActivity digestActivity, User digestUser) throws Exception {
		StringBundler linkDisplay = new StringBundler();

		User connectedUser = getConnectedUser(digestActivity, digestUser);

		String connectedUserLink = "<a href=\"" + connectedUser.getDisplayURL(DigestHelperUtil.getPortalURL(digestActivity.getGroupId()), DigestHelperUtil.getPathMain()) + "\">" + connectedUser.getFullName() + "</a>";

		linkDisplay.append("<div>");

		if (digestActivity.getType() == NEW_FOLLOWER) {

			long userId1 = digestActivity.getExtraDataAsJSON().getInt("userId1");
			long userId2 = digestActivity.getExtraDataAsJSON().getInt("userId2");

			if (connectedUser.getUserId() == userId1) {
				if (userId2 == digestUser.getUserId()) {
					linkDisplay.append(
							LanguageUtil.format(digestUser.getLocale(), "x-is-now-following-you", connectedUserLink)
					);
				}
			}
			else {
				linkDisplay.append(
						LanguageUtil.format(digestUser.getLocale(), "you-are-now-following-x", connectedUserLink)
				);
			}
		}
		else {

			linkDisplay.append(
					LanguageUtil.format(digestUser.getLocale(), "you-are-now-connected-to-x", connectedUserLink)
			);
		}

		linkDisplay.append("</div>");

		return linkDisplay.toString();
	}

	protected User getConnectedUser(DigestActivity digestActivity, User digestUser) throws Exception {

		if (Validator.isNotNull(digestActivity.getExtraDataAsJSON())) {

			JSONObject extraDataJSONObject = digestActivity.getExtraDataAsJSON();

			if (Validator.isNotNull(extraDataJSONObject)) {
				long userId1 = extraDataJSONObject.getInt("userId1");
				long userId2 = extraDataJSONObject.getLong("userId2");

				if (userId2 != digestUser.getUserId()) {
					return UserLocalServiceUtil.fetchUser(userId2);
				}
				else {
					return UserLocalServiceUtil.fetchUser(userId1);
				}
			}

		}

		return null;
	}

	@Override
	protected DigestActivity doConvert(BaseModel model, User digestUser) throws Exception {
		if (!(model instanceof SocialRelation)) {
			throw new Exception("JournalArticle required!");
		}

		SocialRelation socialRelation = (SocialRelation) model;

		DigestActivity digestActivity = null;

		if (Validator.isNotNull(socialRelation)) {
			digestActivity = DigestActivityFactoryUtil.getDigestActivity(
					socialRelation.getModelClassName());

			digestActivity.setUser(null);

			String summary = StringPool.BLANK;

			digestActivity.setActivityType(
					DigestActivityFactoryUtil.getDigestActivityType(socialRelation.getModelClassName()));

			// social relation type

			int type = socialRelation.getType();

			if (type == SocialRelationConstants.TYPE_UNI_FOLLOWER) {
				digestActivity.setType(NEW_FOLLOWER);
			}
			else {
				digestActivity.setType(ADD_CONNECTION);
			}

			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

			extraDataJSONObject.put("userId1", socialRelation.getUserId1());
			extraDataJSONObject.put("userId2", socialRelation.getUserId2());
			extraDataJSONObject.put("type", socialRelation.getType());

			digestActivity.setExtraData(extraDataJSONObject.toString());
			digestActivity.setId(socialRelation.getPrimaryKey());
			digestActivity.setCreateDate(new Date(socialRelation.getCreateDate()));
			digestActivity.setGroupId(0);
			digestActivity.setImageURL(DigestHelperUtil.getDefaultImagePath(socialRelation.getCompanyId()));
			digestActivity.setName(LanguageUtil.get(digestUser.getLocale(), "my-connections"));
			digestActivity.setLink(getLink(socialRelation));
			digestActivity.setLinkDisplay(getLinkDisplay(digestActivity, digestUser));
			digestActivity.setSummary(summary);
			digestActivity.setUuid(null);
		}

		return digestActivity;
	}

	@Override
	protected boolean hasPermission(PermissionChecker permissionChecker, BaseModel model, String actionId) {

		try {
			if (!(model instanceof SocialRelation)) {
				return false;
			}

			SocialRelation socialRelation = (SocialRelation) model;

			return ((socialRelation.getUserId1() == permissionChecker.getUserId()) ||
					(socialRelation.getUserId2() == permissionChecker.getUserId()));
		}
		catch (Throwable t) {
			if (_log.isErrorEnabled()) {
				_log.error(t, t);
			}
		}

		return false;
	}

	public static final int ADD_CONNECTION = 1;
	public static final int NEW_FOLLOWER = 2;

	private static final Log _log = LogFactoryUtil.getLog(SocialRelationConverter.class);

}
