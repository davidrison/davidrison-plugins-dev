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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link UserDigestConfigurationLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserDigestConfigurationLocalService
 * @generated
 */
public class UserDigestConfigurationLocalServiceWrapper
	implements UserDigestConfigurationLocalService,
		ServiceWrapper<UserDigestConfigurationLocalService> {
	public UserDigestConfigurationLocalServiceWrapper(
		UserDigestConfigurationLocalService userDigestConfigurationLocalService) {
		_userDigestConfigurationLocalService = userDigestConfigurationLocalService;
	}

	/**
	* Adds the user digest configuration to the database. Also notifies the appropriate model listeners.
	*
	* @param userDigestConfiguration the user digest configuration
	* @return the user digest configuration that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration addUserDigestConfiguration(
		com.liferay.portlet.digest.activity.model.UserDigestConfiguration userDigestConfiguration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.addUserDigestConfiguration(userDigestConfiguration);
	}

	/**
	* Creates a new user digest configuration with the primary key. Does not add the user digest configuration to the database.
	*
	* @param id the primary key for the new user digest configuration
	* @return the new user digest configuration
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration createUserDigestConfiguration(
		long id) {
		return _userDigestConfigurationLocalService.createUserDigestConfiguration(id);
	}

	/**
	* Deletes the user digest configuration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the user digest configuration
	* @return the user digest configuration that was removed
	* @throws PortalException if a user digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration deleteUserDigestConfiguration(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.deleteUserDigestConfiguration(id);
	}

	/**
	* Deletes the user digest configuration from the database. Also notifies the appropriate model listeners.
	*
	* @param userDigestConfiguration the user digest configuration
	* @return the user digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration deleteUserDigestConfiguration(
		com.liferay.portlet.digest.activity.model.UserDigestConfiguration userDigestConfiguration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.deleteUserDigestConfiguration(userDigestConfiguration);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userDigestConfigurationLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration fetchUserDigestConfiguration(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.fetchUserDigestConfiguration(id);
	}

	/**
	* Returns the user digest configuration with the primary key.
	*
	* @param id the primary key of the user digest configuration
	* @return the user digest configuration
	* @throws PortalException if a user digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration getUserDigestConfiguration(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.getUserDigestConfiguration(id);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.portlet.digest.activity.model.UserDigestConfiguration> getUserDigestConfigurations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.getUserDigestConfigurations(start,
			end);
	}

	/**
	* Returns the number of user digest configurations.
	*
	* @return the number of user digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int getUserDigestConfigurationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.getUserDigestConfigurationsCount();
	}

	/**
	* Updates the user digest configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userDigestConfiguration the user digest configuration
	* @return the user digest configuration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration updateUserDigestConfiguration(
		com.liferay.portlet.digest.activity.model.UserDigestConfiguration userDigestConfiguration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.updateUserDigestConfiguration(userDigestConfiguration);
	}

	/**
	* Updates the user digest configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userDigestConfiguration the user digest configuration
	* @param merge whether to merge the user digest configuration with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the user digest configuration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration updateUserDigestConfiguration(
		com.liferay.portlet.digest.activity.model.UserDigestConfiguration userDigestConfiguration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.updateUserDigestConfiguration(userDigestConfiguration,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _userDigestConfigurationLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_userDigestConfigurationLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _userDigestConfigurationLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration fetchUserDigestConfigurationByUserId(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.fetchUserDigestConfigurationByUserId(userId);
	}

	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration addUserDigestConfiguration(
		long companyId, long userId, int frequency)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.addUserDigestConfiguration(companyId,
			userId, frequency);
	}

	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration updateUserDigestConfiguration(
		long id, long userId, int frequency)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfigurationLocalService.updateUserDigestConfiguration(id,
			userId, frequency);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public UserDigestConfigurationLocalService getWrappedUserDigestConfigurationLocalService() {
		return _userDigestConfigurationLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedUserDigestConfigurationLocalService(
		UserDigestConfigurationLocalService userDigestConfigurationLocalService) {
		_userDigestConfigurationLocalService = userDigestConfigurationLocalService;
	}

	public UserDigestConfigurationLocalService getWrappedService() {
		return _userDigestConfigurationLocalService;
	}

	public void setWrappedService(
		UserDigestConfigurationLocalService userDigestConfigurationLocalService) {
		_userDigestConfigurationLocalService = userDigestConfigurationLocalService;
	}

	private UserDigestConfigurationLocalService _userDigestConfigurationLocalService;
}