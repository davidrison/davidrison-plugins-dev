package com.liferay.portlet.digest.activity.portlet;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletCategoryKeys;
import com.liferay.portlet.digest.activity.DigestActivityType;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;
import com.liferay.portlet.digest.activity.model.UserDigestConfiguration;
import com.liferay.portlet.digest.activity.service.DigestConfigurationLocalServiceUtil;
import com.liferay.portlet.digest.activity.service.UserDigestConfigurationLocalServiceUtil;
import com.liferay.portlet.digest.util.DigestConstants;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.digest.util.PropsValues;

import javax.portlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDigestPortlet extends MVCPortlet {

	@Override
	public void init(PortletConfig config) throws PortletException {
		super.init(config);

		PortletContext portletContext = config.getPortletContext();

		String portletContextName = portletContext.getPortletContextName();
	}

	@Override
	public void render(RenderRequest request, RenderResponse response) throws PortletException, IOException {

		DigestConfiguration userDigestConfiguration = null;
		DigestConfiguration siteDigestConfiguration = null;
		DigestConfiguration portalDigestConfiguration = null;

		try {
			ThemeDisplay themeDisplay =
					(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			User user = PortalUtil.getUser(request);

			userDigestConfiguration = DigestHelperUtil.getActiveUserDigestConfiguration(user.getUserId());

			siteDigestConfiguration = DigestHelperUtil.getActiveSiteDigestConfiguration(themeDisplay.getScopeGroupId());

			portalDigestConfiguration = DigestHelperUtil.getActivePortalDigestConfiguration(themeDisplay.getCompanyId());

			if (Validator.isNull(portalDigestConfiguration)) {

				// create new portal configuration

				DigestHelperUtil.getDefaultPortalDigestConfiguration(themeDisplay.getCompanyId());

				portalDigestConfiguration = DigestHelperUtil.getActivePortalDigestConfiguration(themeDisplay.getCompanyId());
			}

		}
		catch (Throwable t) {
			if (_log.isErrorEnabled())
				_log.error("Unable to find or create portal digest configuration", t);

		}

		request.setAttribute("userDigestConfiguration", userDigestConfiguration);
		request.setAttribute("portalDigestConfiguration", portalDigestConfiguration);
		request.setAttribute("siteDigestConfiguration", siteDigestConfiguration);

		super.render(request, response);
	}

	public void updateDigestConfiguration(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long digestConfigurationId = ParamUtil.getLong(
				actionRequest, "digestConfigurationId");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {

			long groupId = themeDisplay.getScopeGroupId();

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					DigestConfiguration.class.getName(), actionRequest);

			if (cmd.equals(Constants.DELETE) && digestConfigurationId > 0) {
				DigestConfigurationLocalServiceUtil.deleteDigestConfiguration(digestConfigurationId);
			}
			else if (cmd.equals(Constants.EDIT)) {
				int frequency = ParamUtil.getInteger(
						actionRequest, "digestFrequency", DigestConstants.FREQUENCY_NONE);

				long digestScopeGroupId = ParamUtil.getLong(
						actionRequest, "digestScopeGroupId", 0);

				long digestScopeUserId = ParamUtil.getLong(
						actionRequest, "digestScopeUserId", 0);

				int summaryLength = ParamUtil.getInteger(
						actionRequest, "digestSummaryLength", DigestConstants.DIGEST_ACTIVITY_DEFAULT_SUMMARY_LENGTH);

				boolean digestEnabled = ParamUtil.getBoolean(
						actionRequest, "digestEnabled", false
				);

				String controlPanelCategory = PortalUtil.getControlPanelCategory(
						PortalUtil.getPortletId(actionRequest), themeDisplay);

				JSONArray activityTypes = getActivityTypes(actionRequest);

				int digestConfigurationType = DigestConstants.DIGEST_CONFIGURATION_PORTAL;

				if (controlPanelCategory.equals(PortletCategoryKeys.CONTENT)) {
					digestConfigurationType = DigestConstants.DIGEST_CONFIGURATION_SITE;
				}

				DigestConfiguration digestConfiguration =
						getDigestConfiguration(actionRequest, actionResponse, digestConfigurationType);

				if (Validator.isNull(digestConfiguration)) {
					if (controlPanelCategory.equals(PortletCategoryKeys.CONTENT)) {
						digestConfiguration = DigestHelperUtil.getDefaultSiteDigestConfiguration(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
					}
					else {
						digestConfiguration = DigestHelperUtil.getDefaultPortalDigestConfiguration(themeDisplay.getCompanyId());
					}
				}

				DigestConfiguration portalDigestConfiguration =
						DigestHelperUtil.getActivePortalDigestConfiguration(themeDisplay.getCompanyId());

				if (digestConfigurationType == DigestConstants.DIGEST_CONFIGURATION_SITE) {
					// settings come from portal configuration

					frequency = portalDigestConfiguration.getFrequency();
					summaryLength = portalDigestConfiguration.getSummaryLength();
				}

				DigestConfigurationLocalServiceUtil.updateDigestConfiguration(
						digestConfiguration.getId(), digestConfiguration.getUserId(),
						digestConfiguration.getGroupId(), digestConfiguration.getCompanyId(), digestEnabled,
						frequency, digestConfiguration.getScopeGroupId(), digestConfiguration.getScopeUserId(),
						summaryLength, activityTypes.toString());

				if (digestConfigurationType == DigestConstants.DIGEST_CONFIGURATION_PORTAL) {
					updatePreferences(actionRequest, actionResponse);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
		}

		jsonObject.put("success", Boolean.TRUE);

		//writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateUserDigestConfiguration(ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long userDigestConfigurationId = ParamUtil.getLong(
				actionRequest, "userDigestConfigurationId");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {

			long groupId = themeDisplay.getScopeGroupId();

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					DigestConfiguration.class.getName(), actionRequest);

			if (cmd.equals(Constants.DELETE) && userDigestConfigurationId > 0) {
				UserDigestConfigurationLocalServiceUtil.deleteUserDigestConfiguration(userDigestConfigurationId);
			}
			else if (cmd.equals(Constants.EDIT)) {
				int frequency = ParamUtil.getInteger(
						actionRequest, "digestFrequency", DigestConstants.FREQUENCY_NONE);

				UserDigestConfiguration userDigestConfiguration =
						UserDigestConfigurationLocalServiceUtil.fetchUserDigestConfigurationByUserId(
								themeDisplay.getUserId());

				if (Validator.isNotNull(userDigestConfiguration)) {
					UserDigestConfigurationLocalServiceUtil.updateUserDigestConfiguration(
							userDigestConfiguration.getId(), themeDisplay.getUserId(), frequency);
				}
				else {
					UserDigestConfigurationLocalServiceUtil.addUserDigestConfiguration(
							themeDisplay.getCompanyId(), themeDisplay.getUserId(), frequency);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
		}

		jsonObject.put("success", Boolean.TRUE);

		//writeJSON(actionRequest, actionResponse, jsonObject);
	}

	protected JSONArray getActivityTypes(ActionRequest actionRequest) throws Exception {
		List<DigestActivityType> digestActivityTypeList =
				DigestHelperUtil.getAvailableDigestActivityTypes();

		for (DigestActivityType digestActivityType : digestActivityTypeList) {
			List<Integer> availableActions = digestActivityType.getActionsAsList();

			List<Integer> newActions = new ArrayList();

			for (Integer action : availableActions) {
				boolean checked =
						ParamUtil.getBoolean(
								actionRequest,
								digestActivityType.getName() + "_ACTION_" + action, false);

				if (checked) {
					newActions.add(action);
				}
			}

			digestActivityType.setActions(
					ArrayUtil.toArray(newActions.toArray(new Integer[0])));
		}

		JSONArray availableDigestActivityTypesJSONArray =
				JSONFactoryUtil.createJSONArray();

		for (DigestActivityType digestActivityType : digestActivityTypeList) {
			availableDigestActivityTypesJSONArray.put(digestActivityType.toJSONString());
		}

		return availableDigestActivityTypesJSONArray;
	}

	protected DigestConfiguration getDigestConfiguration(
		ActionRequest actionRequest, ActionResponse actionResponse, int type)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		switch (type) {
			case DigestConstants.DIGEST_CONFIGURATION_PORTAL:
				return DigestHelperUtil.getActivePortalDigestConfiguration(themeDisplay.getCompanyId());
			case DigestConstants.DIGEST_CONFIGURATION_SITE:
				return DigestHelperUtil.getActiveSiteDigestConfiguration(themeDisplay.getScopeGroupId());
			case DigestConstants.DIGEST_CONFIGURATION_USER:
				return DigestHelperUtil.getActiveUserDigestConfiguration(themeDisplay.getUserId());
			default:
				return DigestHelperUtil.getActivePortalDigestConfiguration(themeDisplay.getCompanyId());
		}
	}

	protected void updatePreferences(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		int digestInactiveUserNumberDays = ParamUtil.getInteger(
				actionRequest, "digestInactiveUserNumberDays",
					PropsValues.DIGEST_ACTIVITY_INACTIVE_USER_NUMBER_DAYS);

		int digestInactiveUserNumberEmails = ParamUtil.getInteger(
				actionRequest, "digestInactiveUserNumberEmails",
				PropsValues.DIGEST_ACTIVITY_INACTIVE_USER_MAX_NUMBER_EMAILS);

		PortletPreferences portalPreferences = PrefsPropsUtil.getPreferences(
				PortalUtil.getCompanyId(actionRequest));

		portalPreferences.setValue(
				DigestConstants.PREFERENCE_DIGEST_INACTIVE_USER_NUMBER_DAYS,
				"" + digestInactiveUserNumberDays);

		portalPreferences.setValue(
				DigestConstants.PREFERENCE_DIGEST_INACTIVE_USER_MAX_NUMBER_EMAILS,
				"" + digestInactiveUserNumberEmails);

		portalPreferences.store();
	}

	private static final Log _log = LogFactoryUtil.getLog(ActivityDigestPortlet.class);

}
