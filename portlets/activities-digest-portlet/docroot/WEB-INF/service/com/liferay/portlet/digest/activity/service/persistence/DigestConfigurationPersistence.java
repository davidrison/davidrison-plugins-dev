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

import com.liferay.portlet.digest.activity.model.DigestConfiguration;

/**
 * The persistence interface for the digest configuration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DigestConfigurationPersistenceImpl
 * @see DigestConfigurationUtil
 * @generated
 */
public interface DigestConfigurationPersistence extends BasePersistence<DigestConfiguration> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DigestConfigurationUtil} to access the digest configuration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the digest configuration in the entity cache if it is enabled.
	*
	* @param digestConfiguration the digest configuration
	*/
	public void cacheResult(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration);

	/**
	* Caches the digest configurations in the entity cache if it is enabled.
	*
	* @param digestConfigurations the digest configurations
	*/
	public void cacheResult(
		java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> digestConfigurations);

	/**
	* Creates a new digest configuration with the primary key. Does not add the digest configuration to the database.
	*
	* @param id the primary key for the new digest configuration
	* @return the new digest configuration
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration create(
		long id);

	/**
	* Removes the digest configuration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the digest configuration
	* @return the digest configuration that was removed
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration remove(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	public com.liferay.portlet.digest.activity.model.DigestConfiguration updateImpl(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration with the primary key or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	*
	* @param id the primary key of the digest configuration
	* @return the digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration findByPrimaryKey(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Returns the digest configuration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the digest configuration
	* @return the digest configuration, or <code>null</code> if a digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByPrimaryKey(
		long id) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the digest configurations where companyId = &#63; and enabled = &#63;.
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @return the matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> findByC_E(
		long companyId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the digest configurations where companyId = &#63; and enabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @param start the lower bound of the range of digest configurations
	* @param end the upper bound of the range of digest configurations (not inclusive)
	* @return the range of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> findByC_E(
		long companyId, boolean enabled, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the digest configurations where companyId = &#63; and enabled = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @param start the lower bound of the range of digest configurations
	* @param end the upper bound of the range of digest configurations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> findByC_E(
		long companyId, boolean enabled, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first digest configuration in the ordered set where companyId = &#63; and enabled = &#63;.
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration findByC_E_First(
		long companyId, boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Returns the first digest configuration in the ordered set where companyId = &#63; and enabled = &#63;.
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_E_First(
		long companyId, boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last digest configuration in the ordered set where companyId = &#63; and enabled = &#63;.
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration findByC_E_Last(
		long companyId, boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Returns the last digest configuration in the ordered set where companyId = &#63; and enabled = &#63;.
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_E_Last(
		long companyId, boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configurations before and after the current digest configuration in the ordered set where companyId = &#63; and enabled = &#63;.
	*
	* @param id the primary key of the current digest configuration
	* @param companyId the company ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration[] findByC_E_PrevAndNext(
		long id, long companyId, boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Returns the digest configuration where scopeGroupId = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	*
	* @param scopeGroupId the scope group ID
	* @return the matching digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration findByScopeGroupId(
		long scopeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Returns the digest configuration where scopeGroupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param scopeGroupId the scope group ID
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByScopeGroupId(
		long scopeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration where scopeGroupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param scopeGroupId the scope group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByScopeGroupId(
		long scopeGroupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration where scopeGroupId = &#63; and frequency = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	*
	* @param scopeGroupId the scope group ID
	* @param frequency the frequency
	* @return the matching digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration findBySG_F(
		long scopeGroupId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Returns the digest configuration where scopeGroupId = &#63; and frequency = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param scopeGroupId the scope group ID
	* @param frequency the frequency
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchBySG_F(
		long scopeGroupId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration where scopeGroupId = &#63; and frequency = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param scopeGroupId the scope group ID
	* @param frequency the frequency
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchBySG_F(
		long scopeGroupId, int frequency, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration where scopeUserId = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	*
	* @param scopeUserId the scope user ID
	* @return the matching digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration findByScopeUserId(
		long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Returns the digest configuration where scopeUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param scopeUserId the scope user ID
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByScopeUserId(
		long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration where scopeUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param scopeUserId the scope user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByScopeUserId(
		long scopeUserId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; and enabled = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @param enabled the enabled
	* @return the matching digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration findByC_SG_SU_E(
		long companyId, long scopeGroupId, long scopeUserId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Returns the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; and enabled = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @param enabled the enabled
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_SG_SU_E(
		long companyId, long scopeGroupId, long scopeUserId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; and enabled = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @param enabled the enabled
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_SG_SU_E(
		long companyId, long scopeGroupId, long scopeUserId, boolean enabled,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration where scopeUserId = &#63; and frequency = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	*
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @return the matching digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration findBySU_F(
		long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Returns the digest configuration where scopeUserId = &#63; and frequency = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchBySU_F(
		long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration where scopeUserId = &#63; and frequency = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchBySU_F(
		long scopeUserId, int frequency, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @return the matching digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration findByC_SG_SU(
		long companyId, long scopeGroupId, long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Returns the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_SG_SU(
		long companyId, long scopeGroupId, long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_SG_SU(
		long companyId, long scopeGroupId, long scopeUserId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; and frequency = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @return the matching digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration findByC_SG_SU_F(
		long companyId, long scopeGroupId, long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Returns the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; and frequency = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_SG_SU_F(
		long companyId, long scopeGroupId, long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; and frequency = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_SG_SU_F(
		long companyId, long scopeGroupId, long scopeUserId, int frequency,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the digest configurations.
	*
	* @return the digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the digest configurations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of digest configurations
	* @param end the upper bound of the range of digest configurations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the digest configurations where companyId = &#63; and enabled = &#63; from the database.
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_E(long companyId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the digest configuration where scopeGroupId = &#63; from the database.
	*
	* @param scopeGroupId the scope group ID
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration removeByScopeGroupId(
		long scopeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Removes the digest configuration where scopeGroupId = &#63; and frequency = &#63; from the database.
	*
	* @param scopeGroupId the scope group ID
	* @param frequency the frequency
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration removeBySG_F(
		long scopeGroupId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Removes the digest configuration where scopeUserId = &#63; from the database.
	*
	* @param scopeUserId the scope user ID
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration removeByScopeUserId(
		long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Removes the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; and enabled = &#63; from the database.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @param enabled the enabled
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration removeByC_SG_SU_E(
		long companyId, long scopeGroupId, long scopeUserId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Removes the digest configuration where scopeUserId = &#63; and frequency = &#63; from the database.
	*
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration removeBySU_F(
		long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Removes the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration removeByC_SG_SU(
		long companyId, long scopeGroupId, long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Removes the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; and frequency = &#63; from the database.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portlet.digest.activity.model.DigestConfiguration removeByC_SG_SU_F(
		long companyId, long scopeGroupId, long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException;

	/**
	* Removes all the digest configurations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of digest configurations where companyId = &#63; and enabled = &#63;.
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_E(long companyId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of digest configurations where scopeGroupId = &#63;.
	*
	* @param scopeGroupId the scope group ID
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int countByScopeGroupId(long scopeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of digest configurations where scopeGroupId = &#63; and frequency = &#63;.
	*
	* @param scopeGroupId the scope group ID
	* @param frequency the frequency
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int countBySG_F(long scopeGroupId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of digest configurations where scopeUserId = &#63;.
	*
	* @param scopeUserId the scope user ID
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int countByScopeUserId(long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of digest configurations where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; and enabled = &#63;.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @param enabled the enabled
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_SG_SU_E(long companyId, long scopeGroupId,
		long scopeUserId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of digest configurations where scopeUserId = &#63; and frequency = &#63;.
	*
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int countBySU_F(long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of digest configurations where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63;.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_SG_SU(long companyId, long scopeGroupId,
		long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of digest configurations where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; and frequency = &#63;.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_SG_SU_F(long companyId, long scopeGroupId,
		long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of digest configurations.
	*
	* @return the number of digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}