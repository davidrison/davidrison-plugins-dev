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
 * The utility for the user digest configuration local service. This utility wraps {@link com.liferay.portlet.digest.activity.service.impl.UserDigestConfigurationLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserDigestConfigurationLocalService
 * @see com.liferay.portlet.digest.activity.service.base.UserDigestConfigurationLocalServiceBaseImpl
 * @see com.liferay.portlet.digest.activity.service.impl.UserDigestConfigurationLocalServiceImpl
 * @generated
 */
public class UserDigestConfigurationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.digest.activity.service.impl.UserDigestConfigurationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the user digest configuration to the database. Also notifies the appropriate model listeners.
	*
	* @param userDigestConfiguration the user digest configuration
	* @return the user digest configuration that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.UserDigestConfiguration addUserDigestConfiguration(
		com.liferay.portlet.digest.activity.model.UserDigestConfiguration userDigestConfiguration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addUserDigestConfiguration(userDigestConfiguration);
	}

	/**
	* Creates a new user digest configuration with the primary key. Does not add the user digest configuration to the database.
	*
	* @param id the primary key for the new user digest configuration
	* @return the new user digest configuration
	*/
	public static com.liferay.portlet.digest.activity.model.UserDigestConfiguration createUserDigestConfiguration(
		long id) {
		return getService().createUserDigestConfiguration(id);
	}

	/**
	* Deletes the user digest configuration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the user digest configuration
	* @return the user digest configuration that was removed
	* @throws PortalException if a user digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.UserDigestConfiguration deleteUserDigestConfiguration(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteUserDigestConfiguration(id);
	}

	/**
	* Deletes the user digest configuration from the database. Also notifies the appropriate model listeners.
	*
	* @param userDigestConfiguration the user digest configuration
	* @return the user digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.UserDigestConfiguration deleteUserDigestConfiguration(
		com.liferay.portlet.digest.activity.model.UserDigestConfiguration userDigestConfiguration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteUserDigestConfiguration(userDigestConfiguration);
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

	public static com.liferay.portlet.digest.activity.model.UserDigestConfiguration fetchUserDigestConfiguration(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchUserDigestConfiguration(id);
	}

	/**
	* Returns the user digest configuration with the primary key.
	*
	* @param id the primary key of the user digest configuration
	* @return the user digest configuration
	* @throws PortalException if a user digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.UserDigestConfiguration getUserDigestConfiguration(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserDigestConfiguration(id);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the user digest configurations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user digest configurations
	* @param end the upper bound of the range of user digest configurations (not inclusive)
	* @return the range of user digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.digest.activity.model.UserDigestConfiguration> getUserDigestConfigurations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserDigestConfigurations(start, end);
	}

	/**
	* Returns the number of user digest configurations.
	*
	* @return the number of user digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public static int getUserDigestConfigurationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserDigestConfigurationsCount();
	}

	/**
	* Updates the user digest configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userDigestConfiguration the user digest configuration
	* @return the user digest configuration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.UserDigestConfiguration updateUserDigestConfiguration(
		com.liferay.portlet.digest.activity.model.UserDigestConfiguration userDigestConfiguration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateUserDigestConfiguration(userDigestConfiguration);
	}

	/**
	* Updates the user digest configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userDigestConfiguration the user digest configuration
	* @param merge whether to merge the user digest configuration with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the user digest configuration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.UserDigestConfiguration updateUserDigestConfiguration(
		com.liferay.portlet.digest.activity.model.UserDigestConfiguration userDigestConfiguration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateUserDigestConfiguration(userDigestConfiguration, merge);
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

	public static com.liferay.portlet.digest.activity.model.UserDigestConfiguration fetchUserDigestConfigurationByUserId(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchUserDigestConfigurationByUserId(userId);
	}

	public static com.liferay.portlet.digest.activity.model.UserDigestConfiguration addUserDigestConfiguration(
		long companyId, long userId, int frequency)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addUserDigestConfiguration(companyId, userId, frequency);
	}

	public static void populateDefaultUserConfigurations(long companyId,
		long groupId, int frequency)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.populateDefaultUserConfigurations(companyId, groupId, frequency);
	}

	public static com.liferay.portlet.digest.activity.model.UserDigestConfiguration updateUserDigestConfiguration(
		long id, long userId, int frequency)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateUserDigestConfiguration(id, userId, frequency);
	}

	public static void clearService() {
		_service = null;
	}

	public static UserDigestConfigurationLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					UserDigestConfigurationLocalService.class.getName());

			if (invokableLocalService instanceof UserDigestConfigurationLocalService) {
				_service = (UserDigestConfigurationLocalService)invokableLocalService;
			}
			else {
				_service = new UserDigestConfigurationLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(UserDigestConfigurationLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(UserDigestConfigurationLocalService service) {
	}

	private static UserDigestConfigurationLocalService _service;
}