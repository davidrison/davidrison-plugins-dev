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

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException;
import com.liferay.portlet.digest.activity.model.UserDigestConfiguration;
import com.liferay.portlet.digest.activity.model.impl.UserDigestConfigurationImpl;
import com.liferay.portlet.digest.activity.model.impl.UserDigestConfigurationModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the user digest configuration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserDigestConfigurationPersistence
 * @see UserDigestConfigurationUtil
 * @generated
 */
public class UserDigestConfigurationPersistenceImpl extends BasePersistenceImpl<UserDigestConfiguration>
	implements UserDigestConfigurationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserDigestConfigurationUtil} to access the user digest configuration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UserDigestConfigurationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			UserDigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			UserDigestConfigurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			UserDigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			UserDigestConfigurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			UserDigestConfigurationModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			UserDigestConfigurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_USERID = new FinderPath(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			UserDigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			UserDigestConfigurationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUserId", new String[] { Long.class.getName() },
			UserDigestConfigurationModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			UserDigestConfigurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			UserDigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			UserDigestConfigurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			UserDigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			UserDigestConfigurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			UserDigestConfigurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the user digest configuration in the entity cache if it is enabled.
	 *
	 * @param userDigestConfiguration the user digest configuration
	 */
	public void cacheResult(UserDigestConfiguration userDigestConfiguration) {
		EntityCacheUtil.putResult(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			UserDigestConfigurationImpl.class,
			userDigestConfiguration.getPrimaryKey(), userDigestConfiguration);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
			new Object[] { Long.valueOf(userDigestConfiguration.getUserId()) },
			userDigestConfiguration);

		userDigestConfiguration.resetOriginalValues();
	}

	/**
	 * Caches the user digest configurations in the entity cache if it is enabled.
	 *
	 * @param userDigestConfigurations the user digest configurations
	 */
	public void cacheResult(
		List<UserDigestConfiguration> userDigestConfigurations) {
		for (UserDigestConfiguration userDigestConfiguration : userDigestConfigurations) {
			if (EntityCacheUtil.getResult(
						UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
						UserDigestConfigurationImpl.class,
						userDigestConfiguration.getPrimaryKey()) == null) {
				cacheResult(userDigestConfiguration);
			}
			else {
				userDigestConfiguration.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all user digest configurations.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(UserDigestConfigurationImpl.class.getName());
		}

		EntityCacheUtil.clearCache(UserDigestConfigurationImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the user digest configuration.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserDigestConfiguration userDigestConfiguration) {
		EntityCacheUtil.removeResult(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			UserDigestConfigurationImpl.class,
			userDigestConfiguration.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(userDigestConfiguration);
	}

	@Override
	public void clearCache(
		List<UserDigestConfiguration> userDigestConfigurations) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserDigestConfiguration userDigestConfiguration : userDigestConfigurations) {
			EntityCacheUtil.removeResult(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
				UserDigestConfigurationImpl.class,
				userDigestConfiguration.getPrimaryKey());

			clearUniqueFindersCache(userDigestConfiguration);
		}
	}

	protected void cacheUniqueFindersCache(
		UserDigestConfiguration userDigestConfiguration) {
		if (userDigestConfiguration.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(userDigestConfiguration.getUserId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID, args,
				userDigestConfiguration);
		}
		else {
			UserDigestConfigurationModelImpl userDigestConfigurationModelImpl = (UserDigestConfigurationModelImpl)userDigestConfiguration;

			if ((userDigestConfigurationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(userDigestConfiguration.getUserId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID, args,
					userDigestConfiguration);
			}
		}
	}

	protected void clearUniqueFindersCache(
		UserDigestConfiguration userDigestConfiguration) {
		UserDigestConfigurationModelImpl userDigestConfigurationModelImpl = (UserDigestConfigurationModelImpl)userDigestConfiguration;

		Object[] args = new Object[] {
				Long.valueOf(userDigestConfiguration.getUserId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERID, args);

		if ((userDigestConfigurationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_USERID.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(userDigestConfigurationModelImpl.getOriginalUserId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERID, args);
		}
	}

	/**
	 * Creates a new user digest configuration with the primary key. Does not add the user digest configuration to the database.
	 *
	 * @param id the primary key for the new user digest configuration
	 * @return the new user digest configuration
	 */
	public UserDigestConfiguration create(long id) {
		UserDigestConfiguration userDigestConfiguration = new UserDigestConfigurationImpl();

		userDigestConfiguration.setNew(true);
		userDigestConfiguration.setPrimaryKey(id);

		return userDigestConfiguration;
	}

	/**
	 * Removes the user digest configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the user digest configuration
	 * @return the user digest configuration that was removed
	 * @throws com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException if a user digest configuration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserDigestConfiguration remove(long id)
		throws NoSuchUserDigestConfigurationException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the user digest configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user digest configuration
	 * @return the user digest configuration that was removed
	 * @throws com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException if a user digest configuration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserDigestConfiguration remove(Serializable primaryKey)
		throws NoSuchUserDigestConfigurationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			UserDigestConfiguration userDigestConfiguration = (UserDigestConfiguration)session.get(UserDigestConfigurationImpl.class,
					primaryKey);

			if (userDigestConfiguration == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserDigestConfigurationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(userDigestConfiguration);
		}
		catch (NoSuchUserDigestConfigurationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected UserDigestConfiguration removeImpl(
		UserDigestConfiguration userDigestConfiguration)
		throws SystemException {
		userDigestConfiguration = toUnwrappedModel(userDigestConfiguration);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, userDigestConfiguration);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(userDigestConfiguration);

		return userDigestConfiguration;
	}

	@Override
	public UserDigestConfiguration updateImpl(
		com.liferay.portlet.digest.activity.model.UserDigestConfiguration userDigestConfiguration,
		boolean merge) throws SystemException {
		userDigestConfiguration = toUnwrappedModel(userDigestConfiguration);

		boolean isNew = userDigestConfiguration.isNew();

		UserDigestConfigurationModelImpl userDigestConfigurationModelImpl = (UserDigestConfigurationModelImpl)userDigestConfiguration;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, userDigestConfiguration, merge);

			userDigestConfiguration.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !UserDigestConfigurationModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((userDigestConfigurationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(userDigestConfigurationModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(userDigestConfigurationModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}
		}

		EntityCacheUtil.putResult(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			UserDigestConfigurationImpl.class,
			userDigestConfiguration.getPrimaryKey(), userDigestConfiguration);

		clearUniqueFindersCache(userDigestConfiguration);
		cacheUniqueFindersCache(userDigestConfiguration);

		return userDigestConfiguration;
	}

	protected UserDigestConfiguration toUnwrappedModel(
		UserDigestConfiguration userDigestConfiguration) {
		if (userDigestConfiguration instanceof UserDigestConfigurationImpl) {
			return userDigestConfiguration;
		}

		UserDigestConfigurationImpl userDigestConfigurationImpl = new UserDigestConfigurationImpl();

		userDigestConfigurationImpl.setNew(userDigestConfiguration.isNew());
		userDigestConfigurationImpl.setPrimaryKey(userDigestConfiguration.getPrimaryKey());

		userDigestConfigurationImpl.setId(userDigestConfiguration.getId());
		userDigestConfigurationImpl.setCompanyId(userDigestConfiguration.getCompanyId());
		userDigestConfigurationImpl.setCreateDate(userDigestConfiguration.getCreateDate());
		userDigestConfigurationImpl.setModifiedDate(userDigestConfiguration.getModifiedDate());
		userDigestConfigurationImpl.setUserId(userDigestConfiguration.getUserId());
		userDigestConfigurationImpl.setFrequency(userDigestConfiguration.getFrequency());

		return userDigestConfigurationImpl;
	}

	/**
	 * Returns the user digest configuration with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user digest configuration
	 * @return the user digest configuration
	 * @throws com.liferay.portal.NoSuchModelException if a user digest configuration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserDigestConfiguration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the user digest configuration with the primary key or throws a {@link com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException} if it could not be found.
	 *
	 * @param id the primary key of the user digest configuration
	 * @return the user digest configuration
	 * @throws com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException if a user digest configuration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserDigestConfiguration findByPrimaryKey(long id)
		throws NoSuchUserDigestConfigurationException, SystemException {
		UserDigestConfiguration userDigestConfiguration = fetchByPrimaryKey(id);

		if (userDigestConfiguration == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchUserDigestConfigurationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return userDigestConfiguration;
	}

	/**
	 * Returns the user digest configuration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user digest configuration
	 * @return the user digest configuration, or <code>null</code> if a user digest configuration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserDigestConfiguration fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the user digest configuration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the user digest configuration
	 * @return the user digest configuration, or <code>null</code> if a user digest configuration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserDigestConfiguration fetchByPrimaryKey(long id)
		throws SystemException {
		UserDigestConfiguration userDigestConfiguration = (UserDigestConfiguration)EntityCacheUtil.getResult(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
				UserDigestConfigurationImpl.class, id);

		if (userDigestConfiguration == _nullUserDigestConfiguration) {
			return null;
		}

		if (userDigestConfiguration == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				userDigestConfiguration = (UserDigestConfiguration)session.get(UserDigestConfigurationImpl.class,
						Long.valueOf(id));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (userDigestConfiguration != null) {
					cacheResult(userDigestConfiguration);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(UserDigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
						UserDigestConfigurationImpl.class, id,
						_nullUserDigestConfiguration);
				}

				closeSession(session);
			}
		}

		return userDigestConfiguration;
	}

	/**
	 * Returns all the user digest configurations where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching user digest configurations
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserDigestConfiguration> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

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
	public List<UserDigestConfiguration> findByCompanyId(long companyId,
		int start, int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

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
	public List<UserDigestConfiguration> findByCompanyId(long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<UserDigestConfiguration> list = (List<UserDigestConfiguration>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (UserDigestConfiguration userDigestConfiguration : list) {
				if ((companyId != userDigestConfiguration.getCompanyId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_USERDIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<UserDigestConfiguration>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first user digest configuration in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user digest configuration
	 * @throws com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException if a matching user digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserDigestConfiguration findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchUserDigestConfigurationException, SystemException {
		UserDigestConfiguration userDigestConfiguration = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (userDigestConfiguration != null) {
			return userDigestConfiguration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserDigestConfigurationException(msg.toString());
	}

	/**
	 * Returns the first user digest configuration in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user digest configuration, or <code>null</code> if a matching user digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserDigestConfiguration fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<UserDigestConfiguration> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user digest configuration in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user digest configuration
	 * @throws com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException if a matching user digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserDigestConfiguration findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchUserDigestConfigurationException, SystemException {
		UserDigestConfiguration userDigestConfiguration = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (userDigestConfiguration != null) {
			return userDigestConfiguration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserDigestConfigurationException(msg.toString());
	}

	/**
	 * Returns the last user digest configuration in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user digest configuration, or <code>null</code> if a matching user digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserDigestConfiguration fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		List<UserDigestConfiguration> list = findByCompanyId(companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	public UserDigestConfiguration[] findByCompanyId_PrevAndNext(long id,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchUserDigestConfigurationException, SystemException {
		UserDigestConfiguration userDigestConfiguration = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			UserDigestConfiguration[] array = new UserDigestConfigurationImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session,
					userDigestConfiguration, companyId, orderByComparator, true);

			array[1] = userDigestConfiguration;

			array[2] = getByCompanyId_PrevAndNext(session,
					userDigestConfiguration, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserDigestConfiguration getByCompanyId_PrevAndNext(
		Session session, UserDigestConfiguration userDigestConfiguration,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERDIGESTCONFIGURATION_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userDigestConfiguration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<UserDigestConfiguration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the user digest configuration where userId = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching user digest configuration
	 * @throws com.liferay.portlet.digest.activity.NoSuchUserDigestConfigurationException if a matching user digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserDigestConfiguration findByUserId(long userId)
		throws NoSuchUserDigestConfigurationException, SystemException {
		UserDigestConfiguration userDigestConfiguration = fetchByUserId(userId);

		if (userDigestConfiguration == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchUserDigestConfigurationException(msg.toString());
		}

		return userDigestConfiguration;
	}

	/**
	 * Returns the user digest configuration where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching user digest configuration, or <code>null</code> if a matching user digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserDigestConfiguration fetchByUserId(long userId)
		throws SystemException {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the user digest configuration where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user digest configuration, or <code>null</code> if a matching user digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserDigestConfiguration fetchByUserId(long userId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_USERID,
					finderArgs, this);
		}

		if (result instanceof UserDigestConfiguration) {
			UserDigestConfiguration userDigestConfiguration = (UserDigestConfiguration)result;

			if ((userId != userDigestConfiguration.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_USERDIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				List<UserDigestConfiguration> list = q.list();

				result = list;

				UserDigestConfiguration userDigestConfiguration = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
						finderArgs, list);
				}
				else {
					userDigestConfiguration = list.get(0);

					cacheResult(userDigestConfiguration);

					if ((userDigestConfiguration.getUserId() != userId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
							finderArgs, userDigestConfiguration);
					}
				}

				return userDigestConfiguration;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERID,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (UserDigestConfiguration)result;
			}
		}
	}

	/**
	 * Returns all the user digest configurations.
	 *
	 * @return the user digest configurations
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserDigestConfiguration> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<UserDigestConfiguration> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

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
	public List<UserDigestConfiguration> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<UserDigestConfiguration> list = (List<UserDigestConfiguration>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_USERDIGESTCONFIGURATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERDIGESTCONFIGURATION;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<UserDigestConfiguration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<UserDigestConfiguration>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the user digest configurations where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (UserDigestConfiguration userDigestConfiguration : findByCompanyId(
				companyId)) {
			remove(userDigestConfiguration);
		}
	}

	/**
	 * Removes the user digest configuration where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the user digest configuration that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public UserDigestConfiguration removeByUserId(long userId)
		throws NoSuchUserDigestConfigurationException, SystemException {
		UserDigestConfiguration userDigestConfiguration = findByUserId(userId);

		return remove(userDigestConfiguration);
	}

	/**
	 * Removes all the user digest configurations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (UserDigestConfiguration userDigestConfiguration : findAll()) {
			remove(userDigestConfiguration);
		}
	}

	/**
	 * Returns the number of user digest configurations where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching user digest configurations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERDIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of user digest configurations where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user digest configurations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERDIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of user digest configurations.
	 *
	 * @return the number of user digest configurations
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERDIGESTCONFIGURATION);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the user digest configuration persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portlet.digest.activity.model.UserDigestConfiguration")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<UserDigestConfiguration>> listenersList = new ArrayList<ModelListener<UserDigestConfiguration>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<UserDigestConfiguration>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(UserDigestConfigurationImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = DigestConfigurationPersistence.class)
	protected DigestConfigurationPersistence digestConfigurationPersistence;
	@BeanReference(type = UserDigestConfigurationPersistence.class)
	protected UserDigestConfigurationPersistence userDigestConfigurationPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_USERDIGESTCONFIGURATION = "SELECT userDigestConfiguration FROM UserDigestConfiguration userDigestConfiguration";
	private static final String _SQL_SELECT_USERDIGESTCONFIGURATION_WHERE = "SELECT userDigestConfiguration FROM UserDigestConfiguration userDigestConfiguration WHERE ";
	private static final String _SQL_COUNT_USERDIGESTCONFIGURATION = "SELECT COUNT(userDigestConfiguration) FROM UserDigestConfiguration userDigestConfiguration";
	private static final String _SQL_COUNT_USERDIGESTCONFIGURATION_WHERE = "SELECT COUNT(userDigestConfiguration) FROM UserDigestConfiguration userDigestConfiguration WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "userDigestConfiguration.companyId = ?";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "userDigestConfiguration.userId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userDigestConfiguration.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserDigestConfiguration exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserDigestConfiguration exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(UserDigestConfigurationPersistenceImpl.class);
	private static UserDigestConfiguration _nullUserDigestConfiguration = new UserDigestConfigurationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<UserDigestConfiguration> toCacheModel() {
				return _nullUserDigestConfigurationCacheModel;
			}
		};

	private static CacheModel<UserDigestConfiguration> _nullUserDigestConfigurationCacheModel =
		new CacheModel<UserDigestConfiguration>() {
			public UserDigestConfiguration toEntityModel() {
				return _nullUserDigestConfiguration;
			}
		};
}