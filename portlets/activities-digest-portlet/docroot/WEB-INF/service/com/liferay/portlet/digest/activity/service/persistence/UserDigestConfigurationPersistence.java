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

package com.liferay.portlet.digest.activity.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.portlet.digest.activity.model.UserDigestConfiguration;

/**
 * The persistence interface for the user digest configuration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserDigestConfigurationPersistenceImpl
 * @see UserDigestConfigurationUtil
 * @generated
 */
public interface UserDigestConfigurationPersistence extends BasePersistence<UserDigestConfiguration> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserDigestConfigurationUtil} to access the user digest configuration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the user digest configuration in the entity cache if it is enabled.
	*
	* @param userDigestConfiguration the user digest configuration
	*/
	public void cacheResult(
		com.liferay.portlet.digest.activity.model.UserDigestConfiguration userDigestConfiguration);

	/**
	* Caches the user digest configurations in the entity cache if it is enabled.
	*
	* @param userDigestConfigurations the user digest configurations
	*/
	public void cacheResult(
		java.util.List<com.liferay.portlet.digest.activity.model.UserDigestConfiguration> userDigestConfigurations);

	/**
	* Creates a new user digest configuration with the primary key. Does not add the user digest configuration to the database.
	*
	* @param id the primary key for the new user digest configuration
	* @return the new user digest configuration
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration create(
		long id);

	/**
	* Removes the user digest configuration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the user digest configuration
	* @return the user digest configuration that was removed
	* @throws com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException if a user digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration remove(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException;

	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration updateImpl(
		com.liferay.portlet.digest.activity.model.UserDigestConfiguration userDigestConfiguration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user digest configuration with the primary key or throws a {@link com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException} if it could not be found.
	*
	* @param id the primary key of the user digest configuration
	* @return the user digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException if a user digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration findByPrimaryKey(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException;

	/**
	* Returns the user digest configuration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the user digest configuration
	* @return the user digest configuration, or <code>null</code> if a user digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration fetchByPrimaryKey(
		long id) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user digest configurations where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching user digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.digest.activity.model.UserDigestConfiguration> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the user digest configurations where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of user digest configurations
	* @param end the upper bound of the range of user digest configurations (not inclusive)
	* @return the range of matching user digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.digest.activity.model.UserDigestConfiguration> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the user digest configurations where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of user digest configurations
	* @param end the upper bound of the range of user digest configurations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.digest.activity.model.UserDigestConfiguration> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first user digest configuration in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException if a matching user digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException;

	/**
	* Returns the first user digest configuration in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user digest configuration, or <code>null</code> if a matching user digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last user digest configuration in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException if a matching user digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException;

	/**
	* Returns the last user digest configuration in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user digest configuration, or <code>null</code> if a matching user digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user digest configurations before and after the current user digest configuration in the ordered set where companyId = &#63;.
	*
	* @param id the primary key of the current user digest configuration
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException if a user digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration[] findByCompanyId_PrevAndNext(
		long id, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException;

	/**
	* Returns the user digest configuration where userId = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException} if it could not be found.
	*
	* @param userId the user ID
	* @return the matching user digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException if a matching user digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration findByUserId(
		long userId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException;

	/**
	* Returns the user digest configuration where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @return the matching user digest configuration, or <code>null</code> if a matching user digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration fetchByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user digest configuration where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user digest configuration, or <code>null</code> if a matching user digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration fetchByUserId(
		long userId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user digest configurations.
	*
	* @return the user digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.digest.activity.model.UserDigestConfiguration> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.portlet.digest.activity.model.UserDigestConfiguration> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the user digest configurations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user digest configurations
	* @param end the upper bound of the range of user digest configurations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.digest.activity.model.UserDigestConfiguration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user digest configurations where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the user digest configuration where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @return the user digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration removeByUserId(
		long userId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException;

	/**
	* Removes all the user digest configurations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user digest configurations where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching user digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user digest configurations where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user digest configurations.
	*
	* @return the number of user digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}