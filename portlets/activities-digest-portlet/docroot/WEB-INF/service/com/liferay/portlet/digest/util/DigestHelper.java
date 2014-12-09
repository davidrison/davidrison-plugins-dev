package com.liferay.portlet.digest.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.digest.InvalidDigestFrequencyException;
import com.liferay.portlet.digest.activity.DigestActivityType;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;

import javax.servlet.ServletContext;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface DigestHelper {

	public String formatDate(Date date);

	public String getActivityDigestHtml(String resourcesDir) throws Exception;

	public DigestConfiguration getActivePortalDigestConfiguration(long companyId) throws Exception;

	public DigestConfiguration getActiveSiteDigestConfiguration(long groupId) throws Exception;

	public DigestConfiguration getActiveUserDigestConfiguration(long userId) throws Exception;

	public List<DigestActivityType> getAvailableDigestActivityTypes() throws Exception;

	public String getAvailableDigestActivityTypesAsJSON() throws Exception;

	public List<Integer> getAvailableDigestActivityTypeActions(String activityTypeName) throws Exception;

	public Role getDefaultAdminRole(long companyId) throws SystemException;

	public User getDefaultAdminUser(long companyId) throws SystemException;

	public String getDefaultImagePath(long companyId) throws Exception;

	public Group getDefaultGroup() throws Exception;

	public String getFrequencyAsString(int frequency) throws Exception;

	public String getImagePath(long companyId, String className) throws Exception;

	public long getDefaultLayoutSetId(long groupId) throws Exception;

	public DigestConfiguration getDefaultPortalDigestConfiguration(long companyId) throws Exception;

	public DigestConfiguration getDefaultSiteDigestConfiguration(long companyId, long groupId) throws Exception;

	public DigestConfiguration getDefaultUserDigestConfiguration(long companyId, long userId) throws Exception;

	public Group getGlobalGroup(long companyId) throws Exception;

	public int getDigestInactiveNumberDays(long companyId) throws Exception;

	public String getLayoutSetLogoUrl(LayoutSet layoutSet) throws Exception;

	public String getLogoUrl(long groupId) throws Exception;

	public Map<Locale, String> getMap(Locale locale, String value);

	public Map<Locale, String> getMap(String value);

	public String getPathContext();

	public String getPathFriendlyURLPrivateGroup();

	public String getPathFriendlyURLPrivateUser();

	public String getPathFriendlyURLPublic();

	public String getPathImage();

	public String getPathMain();

	public String getPathProxy();

	public String getThemeImagesPath(long companyId);

	public String getPortalURL(long groupId) throws Exception;

	public ServiceContext getServiceContext(long companyId) throws SystemException;

	public ServletContext getServletContext() throws Exception;

	public boolean isUserActive(long userId) throws Exception;

	public boolean isUserActive(User user) throws Exception;

	public boolean isPluginPackageInstalled(String contextName) throws Exception;

	public void validateFrequency(int frequency1, int frequency2) throws InvalidDigestFrequencyException;

}
