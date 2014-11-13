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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.digest.activity.model.DigestConfiguration;

import java.util.List;

/**
 * The persistence utility for the digest configuration service. This utility wraps {@link DigestConfigurationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DigestConfigurationPersistence
 * @see DigestConfigurationPersistenceImpl
 * @generated
 */
public class DigestConfigurationUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(DigestConfiguration digestConfiguration) {
		getPersistence().clearCache(digestConfiguration);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DigestConfiguration> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DigestConfiguration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DigestConfiguration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static DigestConfiguration update(
		DigestConfiguration digestConfiguration, boolean merge)
		throws SystemException {
		return getPersistence().update(digestConfiguration, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static DigestConfiguration update(
		DigestConfiguration digestConfiguration, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(digestConfiguration, merge, serviceContext);
	}

	/**
	* Caches the digest configuration in the entity cache if it is enabled.
	*
	* @param digestConfiguration the digest configuration
	*/
	public static void cacheResult(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration) {
		getPersistence().cacheResult(digestConfiguration);
	}

	/**
	* Caches the digest configurations in the entity cache if it is enabled.
	*
	* @param digestConfigurations the digest configurations
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> digestConfigurations) {
		getPersistence().cacheResult(digestConfigurations);
	}

	/**
	* Creates a new digest configuration with the primary key. Does not add the digest configuration to the database.
	*
	* @param id the primary key for the new digest configuration
	* @return the new digest configuration
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration create(
		long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the digest configuration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the digest configuration
	* @return the digest configuration that was removed
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration remove(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence().remove(id);
	}

	public static com.liferay.portlet.digest.activity.model.DigestConfiguration updateImpl(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(digestConfiguration, merge);
	}

	/**
	* Returns the digest configuration with the primary key or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	*
	* @param id the primary key of the digest configuration
	* @return the digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration findByPrimaryKey(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the digest configuration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the digest configuration
	* @return the digest configuration, or <code>null</code> if a digest configuration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByPrimaryKey(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	* Returns all the digest configurations where companyId = &#63; and enabled = &#63;.
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @return the matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> findByC_E(
		long companyId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_E(companyId, enabled);
	}

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
	public static java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> findByC_E(
		long companyId, boolean enabled, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_E(companyId, enabled, start, end);
	}

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
	public static java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> findByC_E(
		long companyId, boolean enabled, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_E(companyId, enabled, start, end, orderByComparator);
	}

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
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration findByC_E_First(
		long companyId, boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence()
				   .findByC_E_First(companyId, enabled, orderByComparator);
	}

	/**
	* Returns the first digest configuration in the ordered set where companyId = &#63; and enabled = &#63;.
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_E_First(
		long companyId, boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_E_First(companyId, enabled, orderByComparator);
	}

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
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration findByC_E_Last(
		long companyId, boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence()
				   .findByC_E_Last(companyId, enabled, orderByComparator);
	}

	/**
	* Returns the last digest configuration in the ordered set where companyId = &#63; and enabled = &#63;.
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_E_Last(
		long companyId, boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_E_Last(companyId, enabled, orderByComparator);
	}

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
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration[] findByC_E_PrevAndNext(
		long id, long companyId, boolean enabled,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence()
				   .findByC_E_PrevAndNext(id, companyId, enabled,
			orderByComparator);
	}

	/**
	* Returns the digest configuration where scopeGroupId = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	*
	* @param scopeGroupId the scope group ID
	* @return the matching digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration findByScopeGroupId(
		long scopeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence().findByScopeGroupId(scopeGroupId);
	}

	/**
	* Returns the digest configuration where scopeGroupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param scopeGroupId the scope group ID
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByScopeGroupId(
		long scopeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByScopeGroupId(scopeGroupId);
	}

	/**
	* Returns the digest configuration where scopeGroupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param scopeGroupId the scope group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByScopeGroupId(
		long scopeGroupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScopeGroupId(scopeGroupId, retrieveFromCache);
	}

	/**
	* Returns the digest configuration where scopeGroupId = &#63; and frequency = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	*
	* @param scopeGroupId the scope group ID
	* @param frequency the frequency
	* @return the matching digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration findBySG_F(
		long scopeGroupId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence().findBySG_F(scopeGroupId, frequency);
	}

	/**
	* Returns the digest configuration where scopeGroupId = &#63; and frequency = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param scopeGroupId the scope group ID
	* @param frequency the frequency
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchBySG_F(
		long scopeGroupId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchBySG_F(scopeGroupId, frequency);
	}

	/**
	* Returns the digest configuration where scopeGroupId = &#63; and frequency = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param scopeGroupId the scope group ID
	* @param frequency the frequency
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchBySG_F(
		long scopeGroupId, int frequency, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySG_F(scopeGroupId, frequency, retrieveFromCache);
	}

	/**
	* Returns the digest configuration where scopeUserId = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	*
	* @param scopeUserId the scope user ID
	* @return the matching digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration findByScopeUserId(
		long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence().findByScopeUserId(scopeUserId);
	}

	/**
	* Returns the digest configuration where scopeUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param scopeUserId the scope user ID
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByScopeUserId(
		long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByScopeUserId(scopeUserId);
	}

	/**
	* Returns the digest configuration where scopeUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param scopeUserId the scope user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByScopeUserId(
		long scopeUserId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByScopeUserId(scopeUserId, retrieveFromCache);
	}

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
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration findByC_SG_SU_E(
		long companyId, long scopeGroupId, long scopeUserId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence()
				   .findByC_SG_SU_E(companyId, scopeGroupId, scopeUserId,
			enabled);
	}

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
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_SG_SU_E(
		long companyId, long scopeGroupId, long scopeUserId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_SG_SU_E(companyId, scopeGroupId, scopeUserId,
			enabled);
	}

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
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_SG_SU_E(
		long companyId, long scopeGroupId, long scopeUserId, boolean enabled,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_SG_SU_E(companyId, scopeGroupId, scopeUserId,
			enabled, retrieveFromCache);
	}

	/**
	* Returns the digest configuration where scopeUserId = &#63; and frequency = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	*
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @return the matching digest configuration
	* @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration findBySU_F(
		long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence().findBySU_F(scopeUserId, frequency);
	}

	/**
	* Returns the digest configuration where scopeUserId = &#63; and frequency = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchBySU_F(
		long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchBySU_F(scopeUserId, frequency);
	}

	/**
	* Returns the digest configuration where scopeUserId = &#63; and frequency = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchBySU_F(
		long scopeUserId, int frequency, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySU_F(scopeUserId, frequency, retrieveFromCache);
	}

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
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration findByC_SG_SU(
		long companyId, long scopeGroupId, long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence()
				   .findByC_SG_SU(companyId, scopeGroupId, scopeUserId);
	}

	/**
	* Returns the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_SG_SU(
		long companyId, long scopeGroupId, long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_SG_SU(companyId, scopeGroupId, scopeUserId);
	}

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
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_SG_SU(
		long companyId, long scopeGroupId, long scopeUserId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_SG_SU(companyId, scopeGroupId, scopeUserId,
			retrieveFromCache);
	}

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
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration findByC_SG_SU_F(
		long companyId, long scopeGroupId, long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence()
				   .findByC_SG_SU_F(companyId, scopeGroupId, scopeUserId,
			frequency);
	}

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
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_SG_SU_F(
		long companyId, long scopeGroupId, long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_SG_SU_F(companyId, scopeGroupId, scopeUserId,
			frequency);
	}

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
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration fetchByC_SG_SU_F(
		long companyId, long scopeGroupId, long scopeUserId, int frequency,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_SG_SU_F(companyId, scopeGroupId, scopeUserId,
			frequency, retrieveFromCache);
	}

	/**
	* Returns all the digest configurations.
	*
	* @return the digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.portlet.digest.activity.model.DigestConfiguration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the digest configurations where companyId = &#63; and enabled = &#63; from the database.
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_E(long companyId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_E(companyId, enabled);
	}

	/**
	* Removes the digest configuration where scopeGroupId = &#63; from the database.
	*
	* @param scopeGroupId the scope group ID
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration removeByScopeGroupId(
		long scopeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence().removeByScopeGroupId(scopeGroupId);
	}

	/**
	* Removes the digest configuration where scopeGroupId = &#63; and frequency = &#63; from the database.
	*
	* @param scopeGroupId the scope group ID
	* @param frequency the frequency
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration removeBySG_F(
		long scopeGroupId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence().removeBySG_F(scopeGroupId, frequency);
	}

	/**
	* Removes the digest configuration where scopeUserId = &#63; from the database.
	*
	* @param scopeUserId the scope user ID
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration removeByScopeUserId(
		long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence().removeByScopeUserId(scopeUserId);
	}

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
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration removeByC_SG_SU_E(
		long companyId, long scopeGroupId, long scopeUserId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence()
				   .removeByC_SG_SU_E(companyId, scopeGroupId, scopeUserId,
			enabled);
	}

	/**
	* Removes the digest configuration where scopeUserId = &#63; and frequency = &#63; from the database.
	*
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration removeBySU_F(
		long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence().removeBySU_F(scopeUserId, frequency);
	}

	/**
	* Removes the digest configuration where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @return the digest configuration that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration removeByC_SG_SU(
		long companyId, long scopeGroupId, long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence()
				   .removeByC_SG_SU(companyId, scopeGroupId, scopeUserId);
	}

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
	public static com.liferay.portlet.digest.activity.model.DigestConfiguration removeByC_SG_SU_F(
		long companyId, long scopeGroupId, long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.digest.activity.NoSuchConfigurationException {
		return getPersistence()
				   .removeByC_SG_SU_F(companyId, scopeGroupId, scopeUserId,
			frequency);
	}

	/**
	* Removes all the digest configurations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of digest configurations where companyId = &#63; and enabled = &#63;.
	*
	* @param companyId the company ID
	* @param enabled the enabled
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_E(long companyId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_E(companyId, enabled);
	}

	/**
	* Returns the number of digest configurations where scopeGroupId = &#63;.
	*
	* @param scopeGroupId the scope group ID
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByScopeGroupId(long scopeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByScopeGroupId(scopeGroupId);
	}

	/**
	* Returns the number of digest configurations where scopeGroupId = &#63; and frequency = &#63;.
	*
	* @param scopeGroupId the scope group ID
	* @param frequency the frequency
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySG_F(long scopeGroupId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySG_F(scopeGroupId, frequency);
	}

	/**
	* Returns the number of digest configurations where scopeUserId = &#63;.
	*
	* @param scopeUserId the scope user ID
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByScopeUserId(long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByScopeUserId(scopeUserId);
	}

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
	public static int countByC_SG_SU_E(long companyId, long scopeGroupId,
		long scopeUserId, boolean enabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_SG_SU_E(companyId, scopeGroupId, scopeUserId,
			enabled);
	}

	/**
	* Returns the number of digest configurations where scopeUserId = &#63; and frequency = &#63;.
	*
	* @param scopeUserId the scope user ID
	* @param frequency the frequency
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySU_F(long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySU_F(scopeUserId, frequency);
	}

	/**
	* Returns the number of digest configurations where companyId = &#63; and scopeGroupId = &#63; and scopeUserId = &#63;.
	*
	* @param companyId the company ID
	* @param scopeGroupId the scope group ID
	* @param scopeUserId the scope user ID
	* @return the number of matching digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_SG_SU(long companyId, long scopeGroupId,
		long scopeUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_SG_SU(companyId, scopeGroupId, scopeUserId);
	}

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
	public static int countByC_SG_SU_F(long companyId, long scopeGroupId,
		long scopeUserId, int frequency)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_SG_SU_F(companyId, scopeGroupId, scopeUserId,
			frequency);
	}

	/**
	* Returns the number of digest configurations.
	*
	* @return the number of digest configurations
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static DigestConfigurationPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (DigestConfigurationPersistence)PortletBeanLocatorUtil.locate(com.liferay.portlet.digest.activity.service.ClpSerializer.getServletContextName(),
					DigestConfigurationPersistence.class.getName());

			ReferenceRegistry.registerReference(DigestConfigurationUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(DigestConfigurationPersistence persistence) {
	}

	private static DigestConfigurationPersistence _persistence;
}