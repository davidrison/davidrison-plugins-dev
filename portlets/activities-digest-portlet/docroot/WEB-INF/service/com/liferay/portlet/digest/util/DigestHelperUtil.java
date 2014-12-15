package com.liferay.portlet.digest.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.digest.activity.DigestActivityType;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;

import javax.servlet.ServletContext;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DigestHelperUtil {

	public static String formatDate(Date date) {
		return getDigestHelper().formatDate(date);
	}

	public static String getActivityDigestHtml(String resourcesDir) throws Exception {
		return getDigestHelper().getActivityDigestHtml(resourcesDir);
	}

	public static  DigestConfiguration getActivePortalDigestConfiguration(long companyId) throws Exception {
		return getDigestHelper().getActivePortalDigestConfiguration(companyId);
	}

	public static  DigestConfiguration getActiveSiteDigestConfiguration(long groupId) throws Exception {
		return getDigestHelper().getActiveSiteDigestConfiguration(groupId);
	}

	public static  DigestConfiguration getActiveUserDigestConfiguration(long userId) throws Exception {
		return getDigestHelper().getActiveUserDigestConfiguration(userId);
	}

	public static List<DigestActivityType> getAvailableDigestActivityTypes() throws Exception {
		return getDigestHelper().getAvailableDigestActivityTypes();
	}

	public static String getAvailableDigestActivityTypesAsJSON() throws Exception {
		return getDigestHelper().getAvailableDigestActivityTypesAsJSON();
	}

	public static List<Integer> getAvailableDigestActivityTypeActions(String activityTypeName) throws Exception {
		return getDigestHelper().getAvailableDigestActivityTypeActions(activityTypeName);
	}

	public static  Role getDefaultAdminRole(long companyId) throws SystemException {
		return getDigestHelper().getDefaultAdminRole(companyId);
	}

	public static  User getDefaultAdminUser(long companyId) throws SystemException {
		return getDigestHelper().getDefaultAdminUser(companyId);
	}

	public static String getDefaultImagePath(long companyId) throws Exception {
		return getDigestHelper().getDefaultImagePath(companyId);
	}

	public static  Group getDefaultGroup() throws Exception {
		return getDigestHelper().getDefaultGroup();
	}

	public static String getFrequencyAsString(int frequency) throws Exception {
		return getDigestHelper().getFrequencyAsString(frequency);
	}

	public static String getImagePath(long companyId, String className) throws Exception {
		return getDigestHelper().getImagePath(companyId, className);
	}

	public static  long getDefaultLayoutSetId(long groupId) throws Exception {
		return getDigestHelper().getDefaultLayoutSetId(groupId);
	}

	public static  DigestConfiguration getDefaultPortalDigestConfiguration(long companyId) throws Exception {
		return getDigestHelper().getDefaultPortalDigestConfiguration(companyId);
	}

	public static  DigestConfiguration getDefaultSiteDigestConfiguration(long companyId, long groupId) throws Exception {
		return getDigestHelper().getDefaultSiteDigestConfiguration(companyId, groupId);
	}

	public static  DigestConfiguration getDefaultUserDigestConfiguration(long companyId, long userId) throws Exception {
		return getDigestHelper().getDefaultUserDigestConfiguration(companyId, userId);
	}

	public static  Group getGlobalGroup(long companyId) throws Exception {
		return getDigestHelper().getGlobalGroup(companyId);
	}

	public static  int getDigestInactiveNumberDays(long companyId) throws Exception {
		return getDigestHelper().getDigestInactiveNumberDays(companyId);
	}

	public static  int getDigestInactiveNumberEmails(long companyId) throws Exception {
		return getDigestHelper().getDigestInactiveNumberEmails(companyId);
	}

	public static String getLayoutSetLogoUrl(LayoutSet layoutSet) throws Exception {
		return getDigestHelper().getLayoutSetLogoUrl(layoutSet);
	}

	public static String getLogoUrl(long groupId) throws Exception {
		return getDigestHelper().getLogoUrl(groupId);
	}

	public static  Map<Locale, String> getMap(Locale locale, String value) {
		return getDigestHelper().getMap(locale, value);
	}

	public static  Map<Locale, String> getMap(String value) {
		return getDigestHelper().getMap(value);
	}

	public static String getPathContext() {
		return getDigestHelper().getPathContext();
	}

	public static String getPathFriendlyURLPrivateGroup() {
		return getDigestHelper().getPathFriendlyURLPrivateGroup();
	}

	public static String getPathFriendlyURLPrivateUser() {
		return getDigestHelper().getPathFriendlyURLPrivateUser();
	}

	public static String getPathFriendlyURLPublic() {
		return getDigestHelper().getPathFriendlyURLPublic();
	}

	public static String getPathImage() {
		return getDigestHelper().getPathImage();
	}

	public static String getPathMain() {
		return getDigestHelper().getPathMain();
	}

	public static String getPathProxy() {
		return getDigestHelper().getPathProxy();
	}

	public static String getThemeImagesPath(long companyId) {
		return getDigestHelper().getThemeImagesPath(companyId);
	}

	public static String getPortalURL(long groupId) throws Exception {
		return getDigestHelper().getPortalURL(groupId);
	}

	public static ServiceContext getServiceContext(long companyId) throws SystemException {
		return getDigestHelper().getServiceContext(companyId);
	}

	public static ServletContext getServletContext() throws Exception {
		return getDigestHelper().getServletContext();
	}

	public static boolean isUserActive(long userId) throws Exception {
		return getDigestHelper().isUserActive(userId);
	}

	public static boolean isUserActive(User user) throws Exception {
		return getDigestHelper().isUserActive(user);
	}

	public static boolean isPluginPackageInstalled(String contextName) throws Exception {
		return getDigestHelper().isPluginPackageInstalled(contextName);
	}

	public static void validateFrequency(int frequency1, int frequency2) throws PortalException {
		getDigestHelper().validateFrequency(frequency1, frequency2);
	}

	public static DigestHelper getDigestHelper() {
		return _digestHelper;
	}

	public void setDigestHelper(DigestHelper digestHelper) {
		_digestHelper = digestHelper;
	}

	private static DigestHelper _digestHelper;

}
