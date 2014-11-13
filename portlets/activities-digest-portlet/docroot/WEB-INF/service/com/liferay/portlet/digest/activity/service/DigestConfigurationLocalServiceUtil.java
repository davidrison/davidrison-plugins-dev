/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.digest.activity.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the digest configuration local service. This utility wraps {@link com.liferay.portlet.digest.activity.service.impl.DigestConfigurationLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DigestConfigurationLocalService
 * @see com.liferay.portlet.digest.activity.service.base.DigestConfigurationLocalServiceBaseImpl
 * @see com.liferay.portlet.digest.activity.service.impl.DigestConfigurationLocalServiceImpl
 * @generated
 */
public class DigestConfigurationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.digest.activity.service.impl.DigestConfigurationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the digest configuration to the database. Also notifies the appropriate model listeners.
	*
	* @param digestConfiguration the digest configuration
	* @return the digest configuration that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration addDigestConfiguration(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addDigestConfiguration(digestConfiguration);
	}

	/**
	* Creates a new digest configuration with the primary key. Does not add the digest configuration to the database.
	*
	* @param id the primary key for the new digest configuration
	* @return the new digest configuration
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration createDigestConfiguration(
		long id) {
		return getService().createDigestConfiguration(id);
	}

	/**
	* Deletes the digest configuration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the digest configuration
	* @return the digest configuration that was removed
	* @throws PortalException if a digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration deleteDigestConfiguration(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteDigestConfiguration(id);
	}

	/**
	* Deletes the digest configuration from the database. Also notifies the appropriate model listeners.
	*
	* @param digestConfiguration the digest configuration
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration deleteDigestConfiguration(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteDigestConfiguration(digestConfiguration);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfiguration(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchDigestConfiguration(id);
	}

	/**
	* Returns the digest configuration with the primary key.
	*
	* @param id the primary key of the digest configuration
	* @return the digest configuration
	* @throws PortalException if a digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration getDigestConfiguration(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDigestConfiguration(id);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the digest configurations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of digest configurations
	* @param end the upper bound of the range of digest configurations (not inclusive)
	* @return the range of digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> getDigestConfigurations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDigestConfigurations(start, end);
	}

	/**
	* Returns the number of digest configurations.
	*
	* @return the number of digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public static int getDigestConfigurationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDigestConfigurationsCount();
	}

	/**
	* Updates the digest configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param digestConfiguration the digest configuration
	* @return the digest configuration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration updateDigestConfiguration(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateDigestConfiguration(digestConfiguration);
	}

	/**
	* Updates the digest configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param digestConfiguration the digest configuration
	* @param merge whether to merge the digest configuration with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the digest configuration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration updateDigestConfiguration(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateDigestConfiguration(digestConfiguration, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this interface directly. Always use {@link com.liferay.portlet.digest.activity.service.DigestConfigurationLocalServiceUtil} to access the digest configuration local service.
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration addDigestConfiguration(
		long userId, long groupId, long companyId, boolean enabled,
		int frequency, long scopeGroupId, long scopeUserId, int summaryLength,
		java.lang.String activityTypes)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addDigestConfiguration(userId, groupId, companyId, enabled,
			frequency, scopeGroupId, scopeUserId, summaryLength, activityTypes);
	}

	public static java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> getCompanyDigestConfigurations()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyDigestConfigurations();
	}

	public static java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> getDigestConfigurationsByCompanyEnabled(
		long companyId, boolean enabled)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getDigestConfigurationsByCompanyEnabled(companyId, enabled);
	}

	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfigurationByScopeGroupId(
		long scopeGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchDigestConfigurationByScopeGroupId(scopeGroupId);
	}

	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfigurationBySG_F(
		long scopeGroupId, int frequency)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchDigestConfigurationBySG_F(scopeGroupId, frequency);
	}

	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfigurationByScopeUserId(
		long scopeUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchDigestConfigurationByScopeUserId(scopeUserId);
	}

	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfigurationBySU_F(
		long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchDigestConfigurationBySU_F(scopeUserId, frequency);
	}

	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfigurationByC_SG_SU(
		long companyId, long scopeGroupId, long scopeUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchDigestConfigurationByC_SG_SU(companyId, scopeGroupId,
			scopeUserId);
	}

	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfigurationByC_SG_SU_F(
		long companyId, long scopeGroupId, long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchDigestConfigurationByC_SG_SU_F(companyId,
			scopeGroupId, scopeUserId, frequency);
	}

	public static boolean isUserDigestConfigurationEnabled(long companyId,
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .isUserDigestConfigurationEnabled(companyId, groupId, userId);
	}

	public static com.liferay.portlet.digest.activity.model.DigestConfiguration updateDigestConfiguration(
		long id, long userId, long groupId, long companyId, boolean enabled,
		int frequency, long scopeGroupId, long scopeUserId, int summaryLength,
		java.lang.String activityTypes)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateDigestConfiguration(id, userId, groupId, companyId,
			enabled, frequency, scopeGroupId, scopeUserId, summaryLength,
			activityTypes);
	}

	public static void clearService() {
		_service = null;
	}

	public static DigestConfigurationLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					DigestConfigurationLocalService.class.getName());

			if (invokableLocalService instanceof DigestConfigurationLocalService) {
				_service = (DigestConfigurationLocalService)invokableLocalService;
			}
			else {
				_service = new DigestConfigurationLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(DigestConfigurationLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(DigestConfigurationLocalService service) {
	}

	private static DigestConfigurationLocalService _service;
}