package com.liferay.portlet.digest.util.impl;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.model.*;
import com.liferay.portal.service.*;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.webserver.WebServerServletTokenUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.digest.activity.DigestActivityType;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;
import com.liferay.portlet.digest.activity.service.DigestConfigurationLocalServiceUtil;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;
import com.liferay.portlet.digest.util.DigestConstants;
import com.liferay.portlet.digest.util.DigestHelper;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.digest.util.PropsValues;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.polls.model.PollsQuestion;
import com.liferay.portlet.social.model.SocialRelation;
import com.liferay.portlet.wiki.model.WikiPage;

import javax.servlet.ServletContext;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.util.*;

public class DigestHelperImpl implements DigestHelper {

	@Override
	public String formatDate(Date date) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
				GetterUtil.getString(
						PropsUtil.get("digest.date.format.pattern."), "MM-dd-yyyy"));

		return dateFormat.format(date);
	}

	@Override
	public String getActivityDigestHtml(String resourcesDir) throws Exception {
		ServletContext servletContext = DigestHelperUtil.getServletContext();

		URL resource = servletContext.getResource(
				resourcesDir.concat("/journal/templates/ActivityDigest/ActivityDigest.vm"));

		if (resource == null) {
			return null;
		}

		URLConnection urlConnection = resource.openConnection();

		String html = StringUtil.read(urlConnection.getInputStream());

		return html;
	}


	@Override
	public DigestConfiguration getActivePortalDigestConfiguration(long companyId) throws Exception {
		return DigestConfigurationLocalServiceUtil.fetchDigestConfigurationByC_SG_SU(
				companyId, 0, 0);
	}

	@Override
	public DigestConfiguration getActiveSiteDigestConfiguration(long groupId) throws Exception {
		return DigestConfigurationLocalServiceUtil.fetchDigestConfigurationByScopeGroupId(
				groupId);
	}

	@Override
	public DigestConfiguration getActiveUserDigestConfiguration(long userId) throws Exception {
		return DigestConfigurationLocalServiceUtil.fetchDigestConfigurationByScopeUserId(
				userId);
	}

	@Override
	public List<DigestActivityType> getAvailableDigestActivityTypes() throws Exception {
		List<DigestActivityType> availableDigestActivityTypes =
				new ArrayList<DigestActivityType>();

		List<String> activityTypeNames =
				DigestActivityFactoryUtil.getDigestActivityTypeNames();

		for (String activityTypeName : activityTypeNames) {
			DigestActivityType digestActivityType =
					DigestActivityFactoryUtil.getDigestActivityType(activityTypeName);

			digestActivityType.setName(activityTypeName);
			digestActivityType.setId(0);
			digestActivityType.setActions(
					ArrayUtil.toArray((Integer[]) getAvailableDigestActivityTypeActions(
							activityTypeName).toArray(new Integer[0])));

			availableDigestActivityTypes.add(digestActivityType);
		}

		return availableDigestActivityTypes;
	}

	@Override
	public String getAvailableDigestActivityTypesAsJSON() throws Exception {
		JSONArray availableDigestActivityTypesJSONArray =
				JSONFactoryUtil.createJSONArray();

		List<DigestActivityType> availableDigestActivityTypes =
				getAvailableDigestActivityTypes();

		if (Validator.isNotNull(availableDigestActivityTypes)) {
			for (DigestActivityType digestActivityType : availableDigestActivityTypes) {
				availableDigestActivityTypesJSONArray.put(digestActivityType.toJSONString());
			}
		}

		return availableDigestActivityTypesJSONArray.toString();
	}

	@Override
	public List<Integer> getAvailableDigestActivityTypeActions(String activityTypeName) throws Exception {
		return DigestActivityFactoryUtil.getDigestActivityTypeActions(activityTypeName);
	}

	@Override
	public Role getDefaultAdminRole(long companyId) throws SystemException {
		Role defaultAdminRole = RoleLocalServiceUtil.fetchRole(
				companyId, RoleConstants.ADMINISTRATOR);

		return defaultAdminRole;
	}

	@Override
	public User getDefaultAdminUser(long companyId) throws SystemException {
		User defaultAdminUser =
				UserLocalServiceUtil.getRoleUsers(
						getDefaultAdminRole(companyId).getRoleId()).get(0);

		return defaultAdminUser;
	}

	@Override
	public String getDefaultImagePath(long companyId) throws Exception {


		return getImagePath(companyId, null);
	}

	@Override
	public Group getDefaultGroup() throws Exception {
		Company company = CompanyLocalServiceUtil.getCompanies().get(0);

		return GroupLocalServiceUtil.fetchGroup(
				company.getCompanyId(), GroupConstants.GUEST);
	}

	@Override
	public String getFrequencyAsString(int frequency) throws Exception {
		switch (frequency) {
			case DigestConstants.FREQUENCY_DAILY :
				return "daily";
			case DigestConstants.FREQUENCY_NONE:
				return "none";
			case DigestConstants.FREQUENCY_WEEKLY:
				return "weekly";
			default:
				return "none";
		}
	}

	@Override
	public String getImagePath(long companyId, String className) throws Exception {

		Group guest = getDefaultGroup();

		String imagePath = "/common/page.png";

		if (Validator.isNotNull(className)) {
			if (className.equals(BlogsEntry.class.getName())) {
				imagePath = "/blogs/blogs.png";
			}
			else if (className.equals(BookmarksEntry.class.getName())) {
				imagePath = "/ratings/star_hover.png";
			}
			else if (className.equals(CalEvent.class.getName())) {
				imagePath = "/common/date.png";
			}
			else if (className.equals(DLFileEntry.class.getName())) {
				imagePath = "/common/pages.png";
			}
			else if (className.equals(JournalArticle.class.getName())) {
				imagePath = "/common/history.png";
			}
			else if (className.equals(MBMessage.class.getName())) {
				imagePath = "/common/conversation.png";
			}
			else if (className.equals(PollsQuestion.class.getName())) {
				imagePath = "/common/page.png";
			}
			else if (className.equals(SocialRelation.class.getName())) {
				imagePath = "/common/page.png";
			}
			else if (className.equals(WikiPage.class.getName())) {
				imagePath = "/common/pages.png";
			}
		}

		return DigestHelperUtil.getPortalURL(guest.getGroupId()) +
				DigestHelperUtil.getThemeImagesPath(companyId) + imagePath;
	}

	@Override
	public long getDefaultLayoutSetId(long groupId) throws Exception {
		Group group = GroupLocalServiceUtil.getGroup(groupId);

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(groupId, false);

		return layoutSet.getLayoutSetId();
	}

	@Override
	public DigestConfiguration getDefaultPortalDigestConfiguration(long companyId) throws Exception {
		DigestConfiguration digestConfiguration =
				DigestConfigurationLocalServiceUtil.fetchDigestConfigurationByC_SG_SU(
						companyId, 0, 0);

		if (Validator.isNull(digestConfiguration)) {
			digestConfiguration = DigestConfigurationLocalServiceUtil.addDigestConfiguration(
					0, 0, companyId, false, DigestConstants.FREQUENCY_NONE,
					0, 0, DigestConstants.DIGEST_ACTIVITY_DEFAULT_SUMMARY_LENGTH, DigestHelperUtil.getAvailableDigestActivityTypesAsJSON());
		}

		return digestConfiguration;
	}

	@Override
	public DigestConfiguration getDefaultSiteDigestConfiguration(long companyId, long groupId) throws Exception {
		DigestConfiguration digestConfiguration =
				DigestConfigurationLocalServiceUtil.fetchDigestConfigurationByScopeGroupId(
						groupId);

		if (Validator.isNull(digestConfiguration)) {
			digestConfiguration = DigestConfigurationLocalServiceUtil.addDigestConfiguration(
					0, 0, companyId, false, DigestConstants.FREQUENCY_NONE,
					groupId, 0, DigestConstants.DIGEST_ACTIVITY_DEFAULT_SUMMARY_LENGTH, DigestHelperUtil.getAvailableDigestActivityTypesAsJSON());
		}

		return digestConfiguration;
	}

	@Override
	public DigestConfiguration getDefaultUserDigestConfiguration(long companyId, long userId) throws Exception {
		DigestConfiguration digestConfiguration =
				DigestConfigurationLocalServiceUtil.fetchDigestConfigurationByScopeUserId(userId);

		if (Validator.isNull(digestConfiguration)) {
			digestConfiguration = DigestConfigurationLocalServiceUtil.addDigestConfiguration(
					0, 0, companyId, false, DigestConstants.FREQUENCY_NONE,
					0, userId, DigestConstants.DIGEST_ACTIVITY_DEFAULT_SUMMARY_LENGTH, DigestHelperUtil.getAvailableDigestActivityTypesAsJSON());
		}

		return digestConfiguration;
	}

	@Override
	public Group getGlobalGroup(long companyId) throws Exception {
		Group globalGroup = null;

		try {
			globalGroup = GroupLocalServiceUtil.getFriendlyURLGroup(
					companyId, "/global");
		}
		catch(NoSuchGroupException e) {
			globalGroup = GroupLocalServiceUtil.getFriendlyURLGroup(
					companyId, "/null");
		}

		return globalGroup;
	}

	@Override
	public int getDigestInactiveNumberDays(long companyId) throws Exception {
		javax.portlet.PortletPreferences portalPreferences = PrefsPropsUtil.getPreferences(companyId);

		return GetterUtil.getInteger(
				portalPreferences.getValue(
						DigestConstants.PREFERENCE_DIGEST_INACTIVE_USER_NUMBER_DAYS,
						"" + PropsValues.DIGEST_ACTIVITY_INACTIVE_USER_NUMBER_DAYS),
				DigestConstants.MAX_INACTIVE_NUMBER_DAYS);
	}

	@Override
	public String getLayoutSetLogoUrl(LayoutSet layoutSet) throws Exception {
		long logoId = 0;

		if (Validator.isNotNull(layoutSet)) {
			logoId = layoutSet.getLogoId();
		}

		StringBundler sb = new StringBundler(6);

		sb.append(DigestHelperUtil.getPortalURL(Validator.isNotNull(layoutSet) ? layoutSet.getGroupId() : getDefaultGroup().getGroupId()));
		sb.append(DigestHelperUtil.getPathImage());
		sb.append("/layout_set_logo?img_id=");
		sb.append(logoId);
		sb.append("&t=");
		sb.append(WebServerServletTokenUtil.getToken(logoId));

		return sb.toString();
	}

	@Override
	public String getLogoUrl(long groupId) throws Exception {
		StringBundler sb = new StringBundler(6);

		sb.append(DigestHelperUtil.getPortalURL(groupId) + "/activities-digest-portlet/digest/images/logo.png");

		return sb.toString();
	}

	@Override
	public Map<Locale, String> getMap(Locale locale, String value) {
		Map<Locale, String> map = new HashMap<Locale, String>();

		map.put(locale, value);

		return map;
	}

	@Override
	public Map<Locale, String> getMap(String value) {
		return getMap(LocaleUtil.getDefault(), value);
	}

	@Override
	public String getPathContext() {
		return _pathContext;
	}

	@Override
	public String getPathFriendlyURLPrivateGroup() {
		return _pathFriendlyURLPrivateGroup;
	}

	@Override
	public String getPathFriendlyURLPrivateUser() {
		return _pathFriendlyURLPrivateUser;
	}

	@Override
	public String getPathFriendlyURLPublic() {
		return _pathFriendlyURLPublic;
	}

	@Override
	public String getPathImage() {
		return _pathImage;
	}

	@Override
	public String getPathMain() {
		return _pathMain;
	}

	@Override
	public String getPathProxy() {
		return _pathProxy;
	}

	@Override
	public String getThemeImagesPath(long companyId) {
		Theme theme = ThemeLocalServiceUtil.fetchTheme(companyId, _DEFAULT_THEME_ID);

		return theme.getImagesPath();
	}

	@Override
	public String getPortalURL(long groupId) throws Exception {
		if (Validator.isNull(groupId)) {
			groupId = getDefaultGroup().getGroupId();
		}

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		Company company =
				CompanyLocalServiceUtil.fetchCompany(group.getCompanyId());

		return company.getPortalURL(group.getGroupId());
	}

	@Override
	public ServiceContext getServiceContext(long companyId) throws SystemException {
		ServiceContext serviceContext = new ServiceContext();

		String layoutFullURL = ""; // TODO fixme

		User adminUser = getDefaultAdminUser(companyId);

		serviceContext.setUserId(adminUser.getUserId());
		serviceContext.setLayoutFullURL(layoutFullURL);

		return serviceContext;
	}

	@Override
	public ServletContext getServletContext() throws Exception {
		String servletContextName = DigestConstants.DIGEST_SERVLET_CONTEXT;

		ServletContext servletContext = ServletContextPool.get(
				servletContextName);

		return servletContext;
	}

	@Override
	public boolean isPluginPackageInstalled(String contextName) throws Exception {
		MethodKey methodKey =
				new MethodKey(_PLUGINPACKAGEUTIL_CLASSNAME, _PLUGINPACKAGEUTIL_METHODNAME, String.class);

		boolean isInstalled = (Boolean) PortalClassInvoker.invoke(false, methodKey, contextName);

		if (Validator.isNull(PortletBeanLocatorUtil.getBeanLocator(contextName))) {
			isInstalled = false;
		}

		return isInstalled;
	}

	private String _pathContext;
	private String _pathFriendlyURLPrivateGroup;


	private String _pathFriendlyURLPrivateUser;
	private String _pathFriendlyURLPublic;
	private String _pathImage;
	private String _pathMain;
	private String _pathProxy;

	private String _themeStaticResourcePath;

	private String _DEFAULT_THEME_ID;

	private  String _PRIVATE_GROUP_SERVLET_MAPPING;

	private  String _PRIVATE_USER_SERVLET_MAPPING;

	private  String _PUBLIC_GROUP_SERVLET_MAPPING;


	{
		_PRIVATE_GROUP_SERVLET_MAPPING = GetterUtil.getString(
				PropsUtil.get(
						com.liferay.portal.kernel.util.PropsKeys.LAYOUT_FRIENDLY_URL_PRIVATE_GROUP_SERVLET_MAPPING)
		);

		_PRIVATE_USER_SERVLET_MAPPING = GetterUtil.getString(
				PropsUtil.get(
						com.liferay.portal.kernel.util.PropsKeys.LAYOUT_FRIENDLY_URL_PRIVATE_USER_SERVLET_MAPPING)
		);

		_PUBLIC_GROUP_SERVLET_MAPPING = GetterUtil.getString(
				PropsUtil.get(
						PropsKeys.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING)
		);

		// Paths

		_pathProxy = PropsValues.PORTAL_PROXY_PATH;

		_pathContext = ContextPathUtil.getContextPath(PropsValues.PORTAL_CTX);
		_pathContext = _pathProxy.concat(_pathContext);

		_pathFriendlyURLPrivateGroup =
				_pathContext + _PRIVATE_GROUP_SERVLET_MAPPING;
		_pathFriendlyURLPrivateUser =
				_pathContext + _PRIVATE_USER_SERVLET_MAPPING;
		_pathFriendlyURLPublic = _pathContext + _PUBLIC_GROUP_SERVLET_MAPPING;
		_pathImage = _pathContext + Portal.PATH_IMAGE;
		_pathMain = _pathContext + Portal.PATH_MAIN;

		_DEFAULT_THEME_ID = PortalUtil.getJsSafePortletId(
				PropsValues.DEFAULT_REGULAR_THEME_ID);

	}

	private final String _PLUGINPACKAGEUTIL_CLASSNAME = "com.liferay.portal.plugin.PluginPackageUtil";

	private final String _PLUGINPACKAGEUTIL_METHODNAME = "isInstalled";

}
