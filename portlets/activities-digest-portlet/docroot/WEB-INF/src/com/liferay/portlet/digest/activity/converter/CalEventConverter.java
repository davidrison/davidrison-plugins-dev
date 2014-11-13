package com.liferay.portlet.digest.activity.converter;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;
import com.liferay.portlet.digest.activity.util.actions.CalendarActivityKeys;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.digest.util.PropsValues;
import com.liferay.portlet.social.model.SocialActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalEventConverter extends BaseDigestActivityConverter {

	@Override
	public String getActionDisplay(DigestActivity activity) {
		int type = activity.getType();

		switch (type) {
			case CalendarActivityKeys.ADD_EVENT:
				return LanguageUtil.get(activity.getUser().getLocale(), "added-by");
			case CalendarActivityKeys.UPDATE_EVENT:
				return LanguageUtil.get(activity.getUser().getLocale(), "updated-by");
			default:
				return LanguageUtil.get(activity.getUser().getLocale(), "created-by");
		}
	}

	@Override
	public List<Integer> getActions() throws Exception {
		List<Integer> actions = new ArrayList<Integer>();

		actions.add(CalendarActivityKeys.ADD_EVENT);
		actions.add(CalendarActivityKeys.UPDATE_EVENT);

		return actions;
	}

	@Override
	public String getActionName(int action) throws Exception {
		switch (action) {
			case CalendarActivityKeys.ADD_EVENT:
				return "add-event";
			case CalendarActivityKeys.UPDATE_EVENT:
				return "update-event";
			default:
				return null;
		}
	}

	@Override
	public String getLink(BaseModel model) throws Exception {
		CalEvent calEvent = (CalEvent) model;

		String link =
				DigestHelperUtil.getPortalURL(calEvent.getGroupId()) + DigestHelperUtil.getPathMain() +
						"/calendar/find_event?eventId=" + calEvent.getPrimaryKey();

		return link;
	}

	@Override
	protected DigestActivity doConvert(BaseModel model, User digestUser) throws Exception {
		if (!(model instanceof SocialActivity)) {
			throw new Exception("SocialActivity required!");
		}

		SocialActivity socialActivity = (SocialActivity) model;

		CalEvent event = CalEventLocalServiceUtil.fetchCalEvent(
				socialActivity.getClassPK()
		);

		DigestActivity digestActivity = null;

		if (Validator.isNotNull(event)) {
			digestActivity = DigestActivityFactoryUtil.getDigestActivity(
					event.getModelClassName());


			User modelUser = UserLocalServiceUtil.fetchUser(socialActivity.getUserId());

			digestActivity.setUser(modelUser);

			String summary = StringUtil.shorten(
				HtmlUtil.extractText(event.getDescription()),
					PropsValues.DIGEST_ACTIVITY_SUMMARY_LENGTH);

			digestActivity.setActivityType(
					DigestActivityFactoryUtil.getDigestActivityType(event.getModelClassName()));

			digestActivity.setType(socialActivity.getType());

			digestActivity.setId(event.getPrimaryKey());
			digestActivity.setCreateDate(new Date(socialActivity.getCreateDate()));
			digestActivity.setExtraData(socialActivity.getExtraData());
			digestActivity.setGroupId(event.getGroupId());
			digestActivity.setImageURL(DigestHelperUtil.getDefaultImagePath(event.getCompanyId()));
			digestActivity.setName(
					LocalizationUtil.getLocalization(event.getTitle(), LocaleUtil.toLanguageId(digestUser.getLocale())));
			digestActivity.setLink(getLink(event));
			digestActivity.setLinkDisplay(getLinkDisplay(digestActivity, digestUser));
			digestActivity.setSummary(summary);
			digestActivity.setUuid(event.getUuid());
		}

		return digestActivity;
	}

	@Override
	protected boolean hasPermission(PermissionChecker permissionChecker, BaseModel model, String actionId) {

		try {
			SocialActivity socialActivity = (SocialActivity) model;

			CalEvent event = CalEventLocalServiceUtil.fetchCalEvent(
					socialActivity.getClassPK()
			);

			if (permissionChecker.hasOwnerPermission(
					event.getCompanyId(), CalEvent.class.getName(),
					event.getEventId(), event.getUserId(), actionId)) {

				return true;
			}

			return permissionChecker.hasPermission(
					event.getGroupId(), CalEvent.class.getName(), event.getEventId(),
					actionId);
		}
		catch (Throwable t) {
			if (_log.isErrorEnabled()) {
				_log.error(t, t);
			}
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(CalEventConverter.class);

}
