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
 * This class is a wrapper for {@link DigestConfigurationLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DigestConfigurationLocalService
 * @generated
 */
public class DigestConfigurationLocalServiceWrapper
	implements DigestConfigurationLocalService,
		ServiceWrapper<DigestConfigurationLocalService> {
	public DigestConfigurationLocalServiceWrapper(
		DigestConfigurationLocalService digestConfigurationLocalService) {
		_digestConfigurationLocalService = digestConfigurationLocalService;
	}

	/**
	* Adds the digest configuration to the database. Also notifies the appropriate model listeners.
	*
	* @param digestConfiguration the digest configuration
	* @return the digest configuration that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration addDigestConfiguration(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.addDigestConfiguration(digestConfiguration);
	}

	/**
	* Creates a new digest configuration with the primary key. Does not add the digest configuration to the database.
	*
	* @param id the primary key for the new digest configuration
	* @return the new digest configuration
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration createDigestConfiguration(
		long id) {
		return _digestConfigurationLocalService.createDigestConfiguration(id);
	}

	/**
	* Deletes the digest configuration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the digest configuration
	* @return the digest configuration that was removed
	* @throws PortalException if a digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration deleteDigestConfiguration(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.deleteDigestConfiguration(id);
	}

	/**
	* Deletes the digest configuration from the database. Also notifies the appropriate model listeners.
	*
	* @param digestConfiguration the digest configuration
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration deleteDigestConfiguration(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.deleteDigestConfiguration(digestConfiguration);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _digestConfigurationLocalService.dynamicQuery();
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
		return _digestConfigurationLocalService.dynamicQuery(dynamicQuery);
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
		return _digestConfigurationLocalService.dynamicQuery(dynamicQuery,
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
		return _digestConfigurationLocalService.dynamicQuery(dynamicQuery,
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
		return _digestConfigurationLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfiguration(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.fetchDigestConfiguration(id);
	}

	/**
	* Returns the digest configuration with the primary key.
	*
	* @param id the primary key of the digest configuration
	* @return the digest configuration
	* @throws PortalException if a digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration getDigestConfiguration(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.getDigestConfiguration(id);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> getDigestConfigurations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.getDigestConfigurations(start,
			end);
	}

	/**
	* Returns the number of digest configurations.
	*
	* @return the number of digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int getDigestConfigurationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.getDigestConfigurationsCount();
	}

	/**
	* Updates the digest configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param digestConfiguration the digest configuration
	* @return the digest configuration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration updateDigestConfiguration(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.updateDigestConfiguration(digestConfiguration);
	}

	/**
	* Updates the digest configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param digestConfiguration the digest configuration
	* @param merge whether to merge the digest configuration with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the digest configuration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration updateDigestConfiguration(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.updateDigestConfiguration(digestConfiguration,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _digestConfigurationLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_digestConfigurationLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _digestConfigurationLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this interface directly. Always use {@link com.liferay.portlet.digest.activity.service.DigestConfigurationLocalServiceUtil} to access the digest configuration local service.
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration addDigestConfiguration(
		long userId, long groupId, long companyId, boolean enabled,
		int frequency, long scopeGroupId, long scopeUserId, int summaryLength,
		java.lang.String activityTypes)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.addDigestConfiguration(userId,
			groupId, companyId, enabled, frequency, scopeGroupId, scopeUserId,
			summaryLength, activityTypes);
	}

	public java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> getCompanyDigestConfigurations()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.getCompanyDigestConfigurations();
	}

	public java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> getDigestConfigurationsByCompanyEnabled(
		long companyId, boolean enabled)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.getDigestConfigurationsByCompanyEnabled(companyId,
			enabled);
	}

	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfigurationByScopeGroupId(
		long scopeGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.fetchDigestConfigurationByScopeGroupId(scopeGroupId);
	}

	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfigurationBySG_F(
		long scopeGroupId, int frequency)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.fetchDigestConfigurationBySG_F(scopeGroupId,
			frequency);
	}

	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfigurationByScopeUserId(
		long scopeUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.fetchDigestConfigurationByScopeUserId(scopeUserId);
	}

	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfigurationBySU_F(
		long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.fetchDigestConfigurationBySU_F(scopeUserId,
			frequency);
	}

	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfigurationByC_SG_SU(
		long companyId, long scopeGroupId, long scopeUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.fetchDigestConfigurationByC_SG_SU(companyId,
			scopeGroupId, scopeUserId);
	}

	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchDigestConfigurationByC_SG_SU_F(
		long companyId, long scopeGroupId, long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.fetchDigestConfigurationByC_SG_SU_F(companyId,
			scopeGroupId, scopeUserId, frequency);
	}

	public boolean isUserDigestConfigurationEnabled(long companyId,
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.isUserDigestConfigurationEnabled(companyId,
			groupId, userId);
	}

	public com.liferay.portlet.digest.activity.model.DigestConfiguration updateDigestConfiguration(
		long id, long userId, long groupId, long companyId, boolean enabled,
		int frequency, long scopeGroupId, long scopeUserId, int summaryLength,
		java.lang.String activityTypes)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _digestConfigurationLocalService.updateDigestConfiguration(id,
			userId, groupId, companyId, enabled, frequency, scopeGroupId,
			scopeUserId, summaryLength, activityTypes);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DigestConfigurationLocalService getWrappedDigestConfigurationLocalService() {
		return _digestConfigurationLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDigestConfigurationLocalService(
		DigestConfigurationLocalService digestConfigurationLocalService) {
		_digestConfigurationLocalService = digestConfigurationLocalService;
	}

	public DigestConfigurationLocalService getWrappedService() {
		return _digestConfigurationLocalService;
	}

	public void setWrappedService(
		DigestConfigurationLocalService digestConfigurationLocalService) {
		_digestConfigurationLocalService = digestConfigurationLocalService;
	}

	private DigestConfigurationLocalService _digestConfigurationLocalService;
}