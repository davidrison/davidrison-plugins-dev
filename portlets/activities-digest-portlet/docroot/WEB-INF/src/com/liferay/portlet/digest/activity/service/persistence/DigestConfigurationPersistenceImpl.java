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

import com.liferay.portlet.digest.activity.NoSuchConfigurationException;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;
import com.liferay.portlet.digest.activity.model.impl.DigestConfigurationImpl;
import com.liferay.portlet.digest.activity.model.impl.DigestConfigurationModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the digest configuration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DigestConfigurationPersistence
 * @see DigestConfigurationUtil
 * @generated
 */
public class DigestConfigurationPersistenceImpl extends BasePersistenceImpl<DigestConfiguration>
	implements DigestConfigurationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DigestConfigurationUtil} to access the digest configuration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DigestConfigurationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_E = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			DigestConfigurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_E",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_E = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			DigestConfigurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_E",
			new String[] { Long.class.getName(), Boolean.class.getName() },
			DigestConfigurationModelImpl.COMPANYID_COLUMN_BITMASK |
			DigestConfigurationModelImpl.ENABLED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_E = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_E",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_SCOPEGROUPID = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			DigestConfigurationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByScopeGroupId", new String[] { Long.class.getName() },
			DigestConfigurationModelImpl.SCOPEGROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCOPEGROUPID = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByScopeGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_SG_F = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			DigestConfigurationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySG_F",
			new String[] { Long.class.getName(), Integer.class.getName() },
			DigestConfigurationModelImpl.SCOPEGROUPID_COLUMN_BITMASK |
			DigestConfigurationModelImpl.FREQUENCY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SG_F = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySG_F",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_SCOPEUSERID = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			DigestConfigurationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByScopeUserId", new String[] { Long.class.getName() },
			DigestConfigurationModelImpl.SCOPEUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SCOPEUSERID = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByScopeUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_SG_SU_E = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			DigestConfigurationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_SG_SU_E",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			DigestConfigurationModelImpl.COMPANYID_COLUMN_BITMASK |
			DigestConfigurationModelImpl.SCOPEGROUPID_COLUMN_BITMASK |
			DigestConfigurationModelImpl.SCOPEUSERID_COLUMN_BITMASK |
			DigestConfigurationModelImpl.ENABLED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_SG_SU_E = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_SG_SU_E",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_FETCH_BY_SU_F = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			DigestConfigurationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySU_F",
			new String[] { Long.class.getName(), Integer.class.getName() },
			DigestConfigurationModelImpl.SCOPEUSERID_COLUMN_BITMASK |
			DigestConfigurationModelImpl.FREQUENCY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SU_F = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySU_F",
			new String[] { Long.class.getName(), Integer.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_SG_SU = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			DigestConfigurationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_SG_SU",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			DigestConfigurationModelImpl.COMPANYID_COLUMN_BITMASK |
			DigestConfigurationModelImpl.SCOPEGROUPID_COLUMN_BITMASK |
			DigestConfigurationModelImpl.SCOPEUSERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_SG_SU = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_SG_SU",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_FETCH_BY_C_SG_SU_F = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			DigestConfigurationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_SG_SU_F",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			DigestConfigurationModelImpl.COMPANYID_COLUMN_BITMASK |
			DigestConfigurationModelImpl.SCOPEGROUPID_COLUMN_BITMASK |
			DigestConfigurationModelImpl.SCOPEUSERID_COLUMN_BITMASK |
			DigestConfigurationModelImpl.FREQUENCY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_SG_SU_F = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_SG_SU_F",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			DigestConfigurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED,
			DigestConfigurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the digest configuration in the entity cache if it is enabled.
	 *
	 * @param digestConfiguration the digest configuration
	 */
	public void cacheResult(DigestConfiguration digestConfiguration) {
		EntityCacheUtil.putResult(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationImpl.class, digestConfiguration.getPrimaryKey(),
			digestConfiguration);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SCOPEGROUPID,
			new Object[] { Long.valueOf(digestConfiguration.getScopeGroupId()) },
			digestConfiguration);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SG_F,
			new Object[] {
				Long.valueOf(digestConfiguration.getScopeGroupId()),
				Integer.valueOf(digestConfiguration.getFrequency())
			}, digestConfiguration);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SCOPEUSERID,
			new Object[] { Long.valueOf(digestConfiguration.getScopeUserId()) },
			digestConfiguration);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU_E,
			new Object[] {
				Long.valueOf(digestConfiguration.getCompanyId()),
				Long.valueOf(digestConfiguration.getScopeGroupId()),
				Long.valueOf(digestConfiguration.getScopeUserId()),
				Boolean.valueOf(digestConfiguration.getEnabled())
			}, digestConfiguration);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SU_F,
			new Object[] {
				Long.valueOf(digestConfiguration.getScopeUserId()),
				Integer.valueOf(digestConfiguration.getFrequency())
			}, digestConfiguration);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU,
			new Object[] {
				Long.valueOf(digestConfiguration.getCompanyId()),
				Long.valueOf(digestConfiguration.getScopeGroupId()),
				Long.valueOf(digestConfiguration.getScopeUserId())
			}, digestConfiguration);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU_F,
			new Object[] {
				Long.valueOf(digestConfiguration.getCompanyId()),
				Long.valueOf(digestConfiguration.getScopeGroupId()),
				Long.valueOf(digestConfiguration.getScopeUserId()),
				Integer.valueOf(digestConfiguration.getFrequency())
			}, digestConfiguration);

		digestConfiguration.resetOriginalValues();
	}

	/**
	 * Caches the digest configurations in the entity cache if it is enabled.
	 *
	 * @param digestConfigurations the digest configurations
	 */
	public void cacheResult(List<DigestConfiguration> digestConfigurations) {
		for (DigestConfiguration digestConfiguration : digestConfigurations) {
			if (EntityCacheUtil.getResult(
						DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
						DigestConfigurationImpl.class,
						digestConfiguration.getPrimaryKey()) == null) {
				cacheResult(digestConfiguration);
			}
			else {
				digestConfiguration.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all digest configurations.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DigestConfigurationImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DigestConfigurationImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the digest configuration.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DigestConfiguration digestConfiguration) {
		EntityCacheUtil.removeResult(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationImpl.class, digestConfiguration.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(digestConfiguration);
	}

	@Override
	public void clearCache(List<DigestConfiguration> digestConfigurations) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DigestConfiguration digestConfiguration : digestConfigurations) {
			EntityCacheUtil.removeResult(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
				DigestConfigurationImpl.class,
				digestConfiguration.getPrimaryKey());

			clearUniqueFindersCache(digestConfiguration);
		}
	}

	protected void cacheUniqueFindersCache(
		DigestConfiguration digestConfiguration) {
		if (digestConfiguration.isNew()) {
			Object[] args = new Object[] {
					Long.valueOf(digestConfiguration.getScopeGroupId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SCOPEGROUPID, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SCOPEGROUPID, args,
				digestConfiguration);

			args = new Object[] {
					Long.valueOf(digestConfiguration.getScopeGroupId()),
					Integer.valueOf(digestConfiguration.getFrequency())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SG_F, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SG_F, args,
				digestConfiguration);

			args = new Object[] {
					Long.valueOf(digestConfiguration.getScopeUserId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SCOPEUSERID, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SCOPEUSERID, args,
				digestConfiguration);

			args = new Object[] {
					Long.valueOf(digestConfiguration.getCompanyId()),
					Long.valueOf(digestConfiguration.getScopeGroupId()),
					Long.valueOf(digestConfiguration.getScopeUserId()),
					Boolean.valueOf(digestConfiguration.getEnabled())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_SG_SU_E, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU_E, args,
				digestConfiguration);

			args = new Object[] {
					Long.valueOf(digestConfiguration.getScopeUserId()),
					Integer.valueOf(digestConfiguration.getFrequency())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SU_F, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SU_F, args,
				digestConfiguration);

			args = new Object[] {
					Long.valueOf(digestConfiguration.getCompanyId()),
					Long.valueOf(digestConfiguration.getScopeGroupId()),
					Long.valueOf(digestConfiguration.getScopeUserId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_SG_SU, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU, args,
				digestConfiguration);

			args = new Object[] {
					Long.valueOf(digestConfiguration.getCompanyId()),
					Long.valueOf(digestConfiguration.getScopeGroupId()),
					Long.valueOf(digestConfiguration.getScopeUserId()),
					Integer.valueOf(digestConfiguration.getFrequency())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_SG_SU_F, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU_F, args,
				digestConfiguration);
		}
		else {
			DigestConfigurationModelImpl digestConfigurationModelImpl = (DigestConfigurationModelImpl)digestConfiguration;

			if ((digestConfigurationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SCOPEGROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(digestConfiguration.getScopeGroupId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SCOPEGROUPID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SCOPEGROUPID,
					args, digestConfiguration);
			}

			if ((digestConfigurationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SG_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(digestConfiguration.getScopeGroupId()),
						Integer.valueOf(digestConfiguration.getFrequency())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SG_F, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SG_F, args,
					digestConfiguration);
			}

			if ((digestConfigurationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SCOPEUSERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(digestConfiguration.getScopeUserId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SCOPEUSERID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SCOPEUSERID,
					args, digestConfiguration);
			}

			if ((digestConfigurationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_SG_SU_E.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(digestConfiguration.getCompanyId()),
						Long.valueOf(digestConfiguration.getScopeGroupId()),
						Long.valueOf(digestConfiguration.getScopeUserId()),
						Boolean.valueOf(digestConfiguration.getEnabled())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_SG_SU_E, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU_E, args,
					digestConfiguration);
			}

			if ((digestConfigurationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SU_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(digestConfiguration.getScopeUserId()),
						Integer.valueOf(digestConfiguration.getFrequency())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SU_F, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SU_F, args,
					digestConfiguration);
			}

			if ((digestConfigurationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_SG_SU.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(digestConfiguration.getCompanyId()),
						Long.valueOf(digestConfiguration.getScopeGroupId()),
						Long.valueOf(digestConfiguration.getScopeUserId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_SG_SU, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU, args,
					digestConfiguration);
			}

			if ((digestConfigurationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_SG_SU_F.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(digestConfiguration.getCompanyId()),
						Long.valueOf(digestConfiguration.getScopeGroupId()),
						Long.valueOf(digestConfiguration.getScopeUserId()),
						Integer.valueOf(digestConfiguration.getFrequency())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_SG_SU_F, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU_F, args,
					digestConfiguration);
			}
		}
	}

	protected void clearUniqueFindersCache(
		DigestConfiguration digestConfiguration) {
		DigestConfigurationModelImpl digestConfigurationModelImpl = (DigestConfigurationModelImpl)digestConfiguration;

		Object[] args = new Object[] {
				Long.valueOf(digestConfiguration.getScopeGroupId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCOPEGROUPID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SCOPEGROUPID, args);

		if ((digestConfigurationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SCOPEGROUPID.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(digestConfigurationModelImpl.getOriginalScopeGroupId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCOPEGROUPID, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SCOPEGROUPID, args);
		}

		args = new Object[] {
				Long.valueOf(digestConfiguration.getScopeGroupId()),
				Integer.valueOf(digestConfiguration.getFrequency())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SG_F, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SG_F, args);

		if ((digestConfigurationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SG_F.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(digestConfigurationModelImpl.getOriginalScopeGroupId()),
					Integer.valueOf(digestConfigurationModelImpl.getOriginalFrequency())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SG_F, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SG_F, args);
		}

		args = new Object[] { Long.valueOf(digestConfiguration.getScopeUserId()) };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCOPEUSERID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SCOPEUSERID, args);

		if ((digestConfigurationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SCOPEUSERID.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(digestConfigurationModelImpl.getOriginalScopeUserId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SCOPEUSERID, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SCOPEUSERID, args);
		}

		args = new Object[] {
				Long.valueOf(digestConfiguration.getCompanyId()),
				Long.valueOf(digestConfiguration.getScopeGroupId()),
				Long.valueOf(digestConfiguration.getScopeUserId()),
				Boolean.valueOf(digestConfiguration.getEnabled())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_SG_SU_E, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_SG_SU_E, args);

		if ((digestConfigurationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_SG_SU_E.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(digestConfigurationModelImpl.getOriginalCompanyId()),
					Long.valueOf(digestConfigurationModelImpl.getOriginalScopeGroupId()),
					Long.valueOf(digestConfigurationModelImpl.getOriginalScopeUserId()),
					Boolean.valueOf(digestConfigurationModelImpl.getOriginalEnabled())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_SG_SU_E, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_SG_SU_E, args);
		}

		args = new Object[] {
				Long.valueOf(digestConfiguration.getScopeUserId()),
				Integer.valueOf(digestConfiguration.getFrequency())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SU_F, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SU_F, args);

		if ((digestConfigurationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SU_F.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(digestConfigurationModelImpl.getOriginalScopeUserId()),
					Integer.valueOf(digestConfigurationModelImpl.getOriginalFrequency())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SU_F, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SU_F, args);
		}

		args = new Object[] {
				Long.valueOf(digestConfiguration.getCompanyId()),
				Long.valueOf(digestConfiguration.getScopeGroupId()),
				Long.valueOf(digestConfiguration.getScopeUserId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_SG_SU, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_SG_SU, args);

		if ((digestConfigurationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_SG_SU.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(digestConfigurationModelImpl.getOriginalCompanyId()),
					Long.valueOf(digestConfigurationModelImpl.getOriginalScopeGroupId()),
					Long.valueOf(digestConfigurationModelImpl.getOriginalScopeUserId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_SG_SU, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_SG_SU, args);
		}

		args = new Object[] {
				Long.valueOf(digestConfiguration.getCompanyId()),
				Long.valueOf(digestConfiguration.getScopeGroupId()),
				Long.valueOf(digestConfiguration.getScopeUserId()),
				Integer.valueOf(digestConfiguration.getFrequency())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_SG_SU_F, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_SG_SU_F, args);

		if ((digestConfigurationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_SG_SU_F.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(digestConfigurationModelImpl.getOriginalCompanyId()),
					Long.valueOf(digestConfigurationModelImpl.getOriginalScopeGroupId()),
					Long.valueOf(digestConfigurationModelImpl.getOriginalScopeUserId()),
					Integer.valueOf(digestConfigurationModelImpl.getOriginalFrequency())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_SG_SU_F, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_SG_SU_F, args);
		}
	}

	/**
	 * Creates a new digest configuration with the primary key. Does not add the digest configuration to the database.
	 *
	 * @param id the primary key for the new digest configuration
	 * @return the new digest configuration
	 */
	public DigestConfiguration create(long id) {
		DigestConfiguration digestConfiguration = new DigestConfigurationImpl();

		digestConfiguration.setNew(true);
		digestConfiguration.setPrimaryKey(id);

		return digestConfiguration;
	}

	/**
	 * Removes the digest configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the digest configuration
	 * @return the digest configuration that was removed
	 * @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a digest configuration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration remove(long id)
		throws NoSuchConfigurationException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the digest configuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the digest configuration
	 * @return the digest configuration that was removed
	 * @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a digest configuration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DigestConfiguration remove(Serializable primaryKey)
		throws NoSuchConfigurationException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DigestConfiguration digestConfiguration = (DigestConfiguration)session.get(DigestConfigurationImpl.class,
					primaryKey);

			if (digestConfiguration == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchConfigurationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(digestConfiguration);
		}
		catch (NoSuchConfigurationException nsee) {
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
	protected DigestConfiguration removeImpl(
		DigestConfiguration digestConfiguration) throws SystemException {
		digestConfiguration = toUnwrappedModel(digestConfiguration);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, digestConfiguration);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(digestConfiguration);

		return digestConfiguration;
	}

	@Override
	public DigestConfiguration updateImpl(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration,
		boolean merge) throws SystemException {
		digestConfiguration = toUnwrappedModel(digestConfiguration);

		boolean isNew = digestConfiguration.isNew();

		DigestConfigurationModelImpl digestConfigurationModelImpl = (DigestConfigurationModelImpl)digestConfiguration;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, digestConfiguration, merge);

			digestConfiguration.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DigestConfigurationModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((digestConfigurationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_E.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(digestConfigurationModelImpl.getOriginalCompanyId()),
						Boolean.valueOf(digestConfigurationModelImpl.getOriginalEnabled())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_E, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_E,
					args);

				args = new Object[] {
						Long.valueOf(digestConfigurationModelImpl.getCompanyId()),
						Boolean.valueOf(digestConfigurationModelImpl.getEnabled())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_E, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_E,
					args);
			}
		}

		EntityCacheUtil.putResult(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
			DigestConfigurationImpl.class, digestConfiguration.getPrimaryKey(),
			digestConfiguration);

		clearUniqueFindersCache(digestConfiguration);
		cacheUniqueFindersCache(digestConfiguration);

		return digestConfiguration;
	}

	protected DigestConfiguration toUnwrappedModel(
		DigestConfiguration digestConfiguration) {
		if (digestConfiguration instanceof DigestConfigurationImpl) {
			return digestConfiguration;
		}

		DigestConfigurationImpl digestConfigurationImpl = new DigestConfigurationImpl();

		digestConfigurationImpl.setNew(digestConfiguration.isNew());
		digestConfigurationImpl.setPrimaryKey(digestConfiguration.getPrimaryKey());

		digestConfigurationImpl.setId(digestConfiguration.getId());
		digestConfigurationImpl.setCompanyId(digestConfiguration.getCompanyId());
		digestConfigurationImpl.setCreateDate(digestConfiguration.getCreateDate());
		digestConfigurationImpl.setModifiedDate(digestConfiguration.getModifiedDate());
		digestConfigurationImpl.setGroupId(digestConfiguration.getGroupId());
		digestConfigurationImpl.setUserId(digestConfiguration.getUserId());
		digestConfigurationImpl.setEnabled(digestConfiguration.isEnabled());
		digestConfigurationImpl.setFrequency(digestConfiguration.getFrequency());
		digestConfigurationImpl.setScopeGroupId(digestConfiguration.getScopeGroupId());
		digestConfigurationImpl.setScopeUserId(digestConfiguration.getScopeUserId());
		digestConfigurationImpl.setSummaryLength(digestConfiguration.getSummaryLength());
		digestConfigurationImpl.setActivityTypes(digestConfiguration.getActivityTypes());

		return digestConfigurationImpl;
	}

	/**
	 * Returns the digest configuration with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the digest configuration
	 * @return the digest configuration
	 * @throws com.liferay.portal.NoSuchModelException if a digest configuration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DigestConfiguration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the digest configuration with the primary key or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	 *
	 * @param id the primary key of the digest configuration
	 * @return the digest configuration
	 * @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a digest configuration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration findByPrimaryKey(long id)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = fetchByPrimaryKey(id);

		if (digestConfiguration == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchConfigurationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return digestConfiguration;
	}

	/**
	 * Returns the digest configuration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the digest configuration
	 * @return the digest configuration, or <code>null</code> if a digest configuration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DigestConfiguration fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the digest configuration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the digest configuration
	 * @return the digest configuration, or <code>null</code> if a digest configuration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration fetchByPrimaryKey(long id)
		throws SystemException {
		DigestConfiguration digestConfiguration = (DigestConfiguration)EntityCacheUtil.getResult(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
				DigestConfigurationImpl.class, id);

		if (digestConfiguration == _nullDigestConfiguration) {
			return null;
		}

		if (digestConfiguration == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				digestConfiguration = (DigestConfiguration)session.get(DigestConfigurationImpl.class,
						Long.valueOf(id));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (digestConfiguration != null) {
					cacheResult(digestConfiguration);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(DigestConfigurationModelImpl.ENTITY_CACHE_ENABLED,
						DigestConfigurationImpl.class, id,
						_nullDigestConfiguration);
				}

				closeSession(session);
			}
		}

		return digestConfiguration;
	}

	/**
	 * Returns all the digest configurations where companyId = &#63; and enabled = &#63;.
	 *
	 * @param companyId the company ID
	 * @param enabled the enabled
	 * @return the matching digest configurations
	 * @throws SystemException if a system exception occurred
	 */
	public List<DigestConfiguration> findByC_E(long companyId, boolean enabled)
		throws SystemException {
		return findByC_E(companyId, enabled, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	public List<DigestConfiguration> findByC_E(long companyId, boolean enabled,
		int start, int end) throws SystemException {
		return findByC_E(companyId, enabled, start, end, null);
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
	public List<DigestConfiguration> findByC_E(long companyId, boolean enabled,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_E;
			finderArgs = new Object[] { companyId, enabled };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_E;
			finderArgs = new Object[] {
					companyId, enabled,
					
					start, end, orderByComparator
				};
		}

		List<DigestConfiguration> list = (List<DigestConfiguration>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DigestConfiguration digestConfiguration : list) {
				if ((companyId != digestConfiguration.getCompanyId()) ||
						(enabled != digestConfiguration.getEnabled())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_C_E_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_E_ENABLED_2);

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

				qPos.add(enabled);

				list = (List<DigestConfiguration>)QueryUtil.list(q,
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
	 * Returns the first digest configuration in the ordered set where companyId = &#63; and enabled = &#63;.
	 *
	 * @param companyId the company ID
	 * @param enabled the enabled
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching digest configuration
	 * @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration findByC_E_First(long companyId, boolean enabled,
		OrderByComparator orderByComparator)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = fetchByC_E_First(companyId,
				enabled, orderByComparator);

		if (digestConfiguration != null) {
			return digestConfiguration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", enabled=");
		msg.append(enabled);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConfigurationException(msg.toString());
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
	public DigestConfiguration fetchByC_E_First(long companyId,
		boolean enabled, OrderByComparator orderByComparator)
		throws SystemException {
		List<DigestConfiguration> list = findByC_E(companyId, enabled, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public DigestConfiguration findByC_E_Last(long companyId, boolean enabled,
		OrderByComparator orderByComparator)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = fetchByC_E_Last(companyId,
				enabled, orderByComparator);

		if (digestConfiguration != null) {
			return digestConfiguration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", enabled=");
		msg.append(enabled);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchConfigurationException(msg.toString());
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
	public DigestConfiguration fetchByC_E_Last(long companyId, boolean enabled,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_E(companyId, enabled);

		List<DigestConfiguration> list = findByC_E(companyId, enabled,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public DigestConfiguration[] findByC_E_PrevAndNext(long id, long companyId,
		boolean enabled, OrderByComparator orderByComparator)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			DigestConfiguration[] array = new DigestConfigurationImpl[3];

			array[0] = getByC_E_PrevAndNext(session, digestConfiguration,
					companyId, enabled, orderByComparator, true);

			array[1] = digestConfiguration;

			array[2] = getByC_E_PrevAndNext(session, digestConfiguration,
					companyId, enabled, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DigestConfiguration getByC_E_PrevAndNext(Session session,
		DigestConfiguration digestConfiguration, long companyId,
		boolean enabled, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DIGESTCONFIGURATION_WHERE);

		query.append(_FINDER_COLUMN_C_E_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_E_ENABLED_2);

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

		qPos.add(enabled);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(digestConfiguration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DigestConfiguration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the digest configuration where scopeGroupId = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	 *
	 * @param scopeGroupId the scope group ID
	 * @return the matching digest configuration
	 * @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration findByScopeGroupId(long scopeGroupId)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = fetchByScopeGroupId(scopeGroupId);

		if (digestConfiguration == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("scopeGroupId=");
			msg.append(scopeGroupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchConfigurationException(msg.toString());
		}

		return digestConfiguration;
	}

	/**
	 * Returns the digest configuration where scopeGroupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param scopeGroupId the scope group ID
	 * @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration fetchByScopeGroupId(long scopeGroupId)
		throws SystemException {
		return fetchByScopeGroupId(scopeGroupId, true);
	}

	/**
	 * Returns the digest configuration where scopeGroupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param scopeGroupId the scope group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration fetchByScopeGroupId(long scopeGroupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { scopeGroupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SCOPEGROUPID,
					finderArgs, this);
		}

		if (result instanceof DigestConfiguration) {
			DigestConfiguration digestConfiguration = (DigestConfiguration)result;

			if ((scopeGroupId != digestConfiguration.getScopeGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_SCOPEGROUPID_SCOPEGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scopeGroupId);

				List<DigestConfiguration> list = q.list();

				result = list;

				DigestConfiguration digestConfiguration = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SCOPEGROUPID,
						finderArgs, list);
				}
				else {
					digestConfiguration = list.get(0);

					cacheResult(digestConfiguration);

					if ((digestConfiguration.getScopeGroupId() != scopeGroupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SCOPEGROUPID,
							finderArgs, digestConfiguration);
					}
				}

				return digestConfiguration;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SCOPEGROUPID,
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
				return (DigestConfiguration)result;
			}
		}
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
	public DigestConfiguration findBySG_F(long scopeGroupId, int frequency)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = fetchBySG_F(scopeGroupId,
				frequency);

		if (digestConfiguration == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("scopeGroupId=");
			msg.append(scopeGroupId);

			msg.append(", frequency=");
			msg.append(frequency);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchConfigurationException(msg.toString());
		}

		return digestConfiguration;
	}

	/**
	 * Returns the digest configuration where scopeGroupId = &#63; and frequency = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param scopeGroupId the scope group ID
	 * @param frequency the frequency
	 * @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration fetchBySG_F(long scopeGroupId, int frequency)
		throws SystemException {
		return fetchBySG_F(scopeGroupId, frequency, true);
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
	public DigestConfiguration fetchBySG_F(long scopeGroupId, int frequency,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { scopeGroupId, frequency };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SG_F,
					finderArgs, this);
		}

		if (result instanceof DigestConfiguration) {
			DigestConfiguration digestConfiguration = (DigestConfiguration)result;

			if ((scopeGroupId != digestConfiguration.getScopeGroupId()) ||
					(frequency != digestConfiguration.getFrequency())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_SG_F_SCOPEGROUPID_2);

			query.append(_FINDER_COLUMN_SG_F_FREQUENCY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scopeGroupId);

				qPos.add(frequency);

				List<DigestConfiguration> list = q.list();

				result = list;

				DigestConfiguration digestConfiguration = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SG_F,
						finderArgs, list);
				}
				else {
					digestConfiguration = list.get(0);

					cacheResult(digestConfiguration);

					if ((digestConfiguration.getScopeGroupId() != scopeGroupId) ||
							(digestConfiguration.getFrequency() != frequency)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SG_F,
							finderArgs, digestConfiguration);
					}
				}

				return digestConfiguration;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SG_F,
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
				return (DigestConfiguration)result;
			}
		}
	}

	/**
	 * Returns the digest configuration where scopeUserId = &#63; or throws a {@link com.liferay.portlet.digest.activity.NoSuchConfigurationException} if it could not be found.
	 *
	 * @param scopeUserId the scope user ID
	 * @return the matching digest configuration
	 * @throws com.liferay.portlet.digest.activity.NoSuchConfigurationException if a matching digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration findByScopeUserId(long scopeUserId)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = fetchByScopeUserId(scopeUserId);

		if (digestConfiguration == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("scopeUserId=");
			msg.append(scopeUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchConfigurationException(msg.toString());
		}

		return digestConfiguration;
	}

	/**
	 * Returns the digest configuration where scopeUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param scopeUserId the scope user ID
	 * @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration fetchByScopeUserId(long scopeUserId)
		throws SystemException {
		return fetchByScopeUserId(scopeUserId, true);
	}

	/**
	 * Returns the digest configuration where scopeUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param scopeUserId the scope user ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration fetchByScopeUserId(long scopeUserId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { scopeUserId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SCOPEUSERID,
					finderArgs, this);
		}

		if (result instanceof DigestConfiguration) {
			DigestConfiguration digestConfiguration = (DigestConfiguration)result;

			if ((scopeUserId != digestConfiguration.getScopeUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_SCOPEUSERID_SCOPEUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scopeUserId);

				List<DigestConfiguration> list = q.list();

				result = list;

				DigestConfiguration digestConfiguration = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SCOPEUSERID,
						finderArgs, list);
				}
				else {
					digestConfiguration = list.get(0);

					cacheResult(digestConfiguration);

					if ((digestConfiguration.getScopeUserId() != scopeUserId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SCOPEUSERID,
							finderArgs, digestConfiguration);
					}
				}

				return digestConfiguration;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SCOPEUSERID,
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
				return (DigestConfiguration)result;
			}
		}
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
	public DigestConfiguration findByC_SG_SU_E(long companyId,
		long scopeGroupId, long scopeUserId, boolean enabled)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = fetchByC_SG_SU_E(companyId,
				scopeGroupId, scopeUserId, enabled);

		if (digestConfiguration == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", scopeGroupId=");
			msg.append(scopeGroupId);

			msg.append(", scopeUserId=");
			msg.append(scopeUserId);

			msg.append(", enabled=");
			msg.append(enabled);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchConfigurationException(msg.toString());
		}

		return digestConfiguration;
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
	public DigestConfiguration fetchByC_SG_SU_E(long companyId,
		long scopeGroupId, long scopeUserId, boolean enabled)
		throws SystemException {
		return fetchByC_SG_SU_E(companyId, scopeGroupId, scopeUserId, enabled,
			true);
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
	public DigestConfiguration fetchByC_SG_SU_E(long companyId,
		long scopeGroupId, long scopeUserId, boolean enabled,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, scopeGroupId, scopeUserId, enabled
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_SG_SU_E,
					finderArgs, this);
		}

		if (result instanceof DigestConfiguration) {
			DigestConfiguration digestConfiguration = (DigestConfiguration)result;

			if ((companyId != digestConfiguration.getCompanyId()) ||
					(scopeGroupId != digestConfiguration.getScopeGroupId()) ||
					(scopeUserId != digestConfiguration.getScopeUserId()) ||
					(enabled != digestConfiguration.getEnabled())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_C_SG_SU_E_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_E_SCOPEGROUPID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_E_SCOPEUSERID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_E_ENABLED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(scopeGroupId);

				qPos.add(scopeUserId);

				qPos.add(enabled);

				List<DigestConfiguration> list = q.list();

				result = list;

				DigestConfiguration digestConfiguration = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU_E,
						finderArgs, list);
				}
				else {
					digestConfiguration = list.get(0);

					cacheResult(digestConfiguration);

					if ((digestConfiguration.getCompanyId() != companyId) ||
							(digestConfiguration.getScopeGroupId() != scopeGroupId) ||
							(digestConfiguration.getScopeUserId() != scopeUserId) ||
							(digestConfiguration.getEnabled() != enabled)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU_E,
							finderArgs, digestConfiguration);
					}
				}

				return digestConfiguration;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_SG_SU_E,
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
				return (DigestConfiguration)result;
			}
		}
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
	public DigestConfiguration findBySU_F(long scopeUserId, int frequency)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = fetchBySU_F(scopeUserId,
				frequency);

		if (digestConfiguration == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("scopeUserId=");
			msg.append(scopeUserId);

			msg.append(", frequency=");
			msg.append(frequency);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchConfigurationException(msg.toString());
		}

		return digestConfiguration;
	}

	/**
	 * Returns the digest configuration where scopeUserId = &#63; and frequency = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param scopeUserId the scope user ID
	 * @param frequency the frequency
	 * @return the matching digest configuration, or <code>null</code> if a matching digest configuration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration fetchBySU_F(long scopeUserId, int frequency)
		throws SystemException {
		return fetchBySU_F(scopeUserId, frequency, true);
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
	public DigestConfiguration fetchBySU_F(long scopeUserId, int frequency,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { scopeUserId, frequency };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SU_F,
					finderArgs, this);
		}

		if (result instanceof DigestConfiguration) {
			DigestConfiguration digestConfiguration = (DigestConfiguration)result;

			if ((scopeUserId != digestConfiguration.getScopeUserId()) ||
					(frequency != digestConfiguration.getFrequency())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_SU_F_SCOPEUSERID_2);

			query.append(_FINDER_COLUMN_SU_F_FREQUENCY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scopeUserId);

				qPos.add(frequency);

				List<DigestConfiguration> list = q.list();

				result = list;

				DigestConfiguration digestConfiguration = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SU_F,
						finderArgs, list);
				}
				else {
					digestConfiguration = list.get(0);

					cacheResult(digestConfiguration);

					if ((digestConfiguration.getScopeUserId() != scopeUserId) ||
							(digestConfiguration.getFrequency() != frequency)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SU_F,
							finderArgs, digestConfiguration);
					}
				}

				return digestConfiguration;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SU_F,
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
				return (DigestConfiguration)result;
			}
		}
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
	public DigestConfiguration findByC_SG_SU(long companyId, long scopeGroupId,
		long scopeUserId) throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = fetchByC_SG_SU(companyId,
				scopeGroupId, scopeUserId);

		if (digestConfiguration == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", scopeGroupId=");
			msg.append(scopeGroupId);

			msg.append(", scopeUserId=");
			msg.append(scopeUserId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchConfigurationException(msg.toString());
		}

		return digestConfiguration;
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
	public DigestConfiguration fetchByC_SG_SU(long companyId,
		long scopeGroupId, long scopeUserId) throws SystemException {
		return fetchByC_SG_SU(companyId, scopeGroupId, scopeUserId, true);
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
	public DigestConfiguration fetchByC_SG_SU(long companyId,
		long scopeGroupId, long scopeUserId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, scopeGroupId, scopeUserId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_SG_SU,
					finderArgs, this);
		}

		if (result instanceof DigestConfiguration) {
			DigestConfiguration digestConfiguration = (DigestConfiguration)result;

			if ((companyId != digestConfiguration.getCompanyId()) ||
					(scopeGroupId != digestConfiguration.getScopeGroupId()) ||
					(scopeUserId != digestConfiguration.getScopeUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_C_SG_SU_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_SCOPEGROUPID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_SCOPEUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(scopeGroupId);

				qPos.add(scopeUserId);

				List<DigestConfiguration> list = q.list();

				result = list;

				DigestConfiguration digestConfiguration = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU,
						finderArgs, list);
				}
				else {
					digestConfiguration = list.get(0);

					cacheResult(digestConfiguration);

					if ((digestConfiguration.getCompanyId() != companyId) ||
							(digestConfiguration.getScopeGroupId() != scopeGroupId) ||
							(digestConfiguration.getScopeUserId() != scopeUserId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU,
							finderArgs, digestConfiguration);
					}
				}

				return digestConfiguration;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_SG_SU,
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
				return (DigestConfiguration)result;
			}
		}
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
	public DigestConfiguration findByC_SG_SU_F(long companyId,
		long scopeGroupId, long scopeUserId, int frequency)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = fetchByC_SG_SU_F(companyId,
				scopeGroupId, scopeUserId, frequency);

		if (digestConfiguration == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", scopeGroupId=");
			msg.append(scopeGroupId);

			msg.append(", scopeUserId=");
			msg.append(scopeUserId);

			msg.append(", frequency=");
			msg.append(frequency);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchConfigurationException(msg.toString());
		}

		return digestConfiguration;
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
	public DigestConfiguration fetchByC_SG_SU_F(long companyId,
		long scopeGroupId, long scopeUserId, int frequency)
		throws SystemException {
		return fetchByC_SG_SU_F(companyId, scopeGroupId, scopeUserId,
			frequency, true);
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
	public DigestConfiguration fetchByC_SG_SU_F(long companyId,
		long scopeGroupId, long scopeUserId, int frequency,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, scopeGroupId, scopeUserId, frequency
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_SG_SU_F,
					finderArgs, this);
		}

		if (result instanceof DigestConfiguration) {
			DigestConfiguration digestConfiguration = (DigestConfiguration)result;

			if ((companyId != digestConfiguration.getCompanyId()) ||
					(scopeGroupId != digestConfiguration.getScopeGroupId()) ||
					(scopeUserId != digestConfiguration.getScopeUserId()) ||
					(frequency != digestConfiguration.getFrequency())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_C_SG_SU_F_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_F_SCOPEGROUPID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_F_SCOPEUSERID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_F_FREQUENCY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(scopeGroupId);

				qPos.add(scopeUserId);

				qPos.add(frequency);

				List<DigestConfiguration> list = q.list();

				result = list;

				DigestConfiguration digestConfiguration = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU_F,
						finderArgs, list);
				}
				else {
					digestConfiguration = list.get(0);

					cacheResult(digestConfiguration);

					if ((digestConfiguration.getCompanyId() != companyId) ||
							(digestConfiguration.getScopeGroupId() != scopeGroupId) ||
							(digestConfiguration.getScopeUserId() != scopeUserId) ||
							(digestConfiguration.getFrequency() != frequency)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_SG_SU_F,
							finderArgs, digestConfiguration);
					}
				}

				return digestConfiguration;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_SG_SU_F,
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
				return (DigestConfiguration)result;
			}
		}
	}

	/**
	 * Returns all the digest configurations.
	 *
	 * @return the digest configurations
	 * @throws SystemException if a system exception occurred
	 */
	public List<DigestConfiguration> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<DigestConfiguration> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	public List<DigestConfiguration> findAll(int start, int end,
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

		List<DigestConfiguration> list = (List<DigestConfiguration>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DIGESTCONFIGURATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DIGESTCONFIGURATION;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<DigestConfiguration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<DigestConfiguration>)QueryUtil.list(q,
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
	 * Removes all the digest configurations where companyId = &#63; and enabled = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param enabled the enabled
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_E(long companyId, boolean enabled)
		throws SystemException {
		for (DigestConfiguration digestConfiguration : findByC_E(companyId,
				enabled)) {
			remove(digestConfiguration);
		}
	}

	/**
	 * Removes the digest configuration where scopeGroupId = &#63; from the database.
	 *
	 * @param scopeGroupId the scope group ID
	 * @return the digest configuration that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration removeByScopeGroupId(long scopeGroupId)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = findByScopeGroupId(scopeGroupId);

		return remove(digestConfiguration);
	}

	/**
	 * Removes the digest configuration where scopeGroupId = &#63; and frequency = &#63; from the database.
	 *
	 * @param scopeGroupId the scope group ID
	 * @param frequency the frequency
	 * @return the digest configuration that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration removeBySG_F(long scopeGroupId, int frequency)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = findBySG_F(scopeGroupId,
				frequency);

		return remove(digestConfiguration);
	}

	/**
	 * Removes the digest configuration where scopeUserId = &#63; from the database.
	 *
	 * @param scopeUserId the scope user ID
	 * @return the digest configuration that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration removeByScopeUserId(long scopeUserId)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = findByScopeUserId(scopeUserId);

		return remove(digestConfiguration);
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
	public DigestConfiguration removeByC_SG_SU_E(long companyId,
		long scopeGroupId, long scopeUserId, boolean enabled)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = findByC_SG_SU_E(companyId,
				scopeGroupId, scopeUserId, enabled);

		return remove(digestConfiguration);
	}

	/**
	 * Removes the digest configuration where scopeUserId = &#63; and frequency = &#63; from the database.
	 *
	 * @param scopeUserId the scope user ID
	 * @param frequency the frequency
	 * @return the digest configuration that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DigestConfiguration removeBySU_F(long scopeUserId, int frequency)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = findBySU_F(scopeUserId,
				frequency);

		return remove(digestConfiguration);
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
	public DigestConfiguration removeByC_SG_SU(long companyId,
		long scopeGroupId, long scopeUserId)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = findByC_SG_SU(companyId,
				scopeGroupId, scopeUserId);

		return remove(digestConfiguration);
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
	public DigestConfiguration removeByC_SG_SU_F(long companyId,
		long scopeGroupId, long scopeUserId, int frequency)
		throws NoSuchConfigurationException, SystemException {
		DigestConfiguration digestConfiguration = findByC_SG_SU_F(companyId,
				scopeGroupId, scopeUserId, frequency);

		return remove(digestConfiguration);
	}

	/**
	 * Removes all the digest configurations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DigestConfiguration digestConfiguration : findAll()) {
			remove(digestConfiguration);
		}
	}

	/**
	 * Returns the number of digest configurations where companyId = &#63; and enabled = &#63;.
	 *
	 * @param companyId the company ID
	 * @param enabled the enabled
	 * @return the number of matching digest configurations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_E(long companyId, boolean enabled)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, enabled };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_E,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_C_E_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_E_ENABLED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(enabled);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_E, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of digest configurations where scopeGroupId = &#63;.
	 *
	 * @param scopeGroupId the scope group ID
	 * @return the number of matching digest configurations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByScopeGroupId(long scopeGroupId) throws SystemException {
		Object[] finderArgs = new Object[] { scopeGroupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SCOPEGROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_SCOPEGROUPID_SCOPEGROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scopeGroupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SCOPEGROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of digest configurations where scopeGroupId = &#63; and frequency = &#63;.
	 *
	 * @param scopeGroupId the scope group ID
	 * @param frequency the frequency
	 * @return the number of matching digest configurations
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySG_F(long scopeGroupId, int frequency)
		throws SystemException {
		Object[] finderArgs = new Object[] { scopeGroupId, frequency };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SG_F,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_SG_F_SCOPEGROUPID_2);

			query.append(_FINDER_COLUMN_SG_F_FREQUENCY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scopeGroupId);

				qPos.add(frequency);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SG_F,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of digest configurations where scopeUserId = &#63;.
	 *
	 * @param scopeUserId the scope user ID
	 * @return the number of matching digest configurations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByScopeUserId(long scopeUserId) throws SystemException {
		Object[] finderArgs = new Object[] { scopeUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SCOPEUSERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_SCOPEUSERID_SCOPEUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scopeUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SCOPEUSERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
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
	public int countByC_SG_SU_E(long companyId, long scopeGroupId,
		long scopeUserId, boolean enabled) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, scopeGroupId, scopeUserId, enabled
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_SG_SU_E,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_C_SG_SU_E_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_E_SCOPEGROUPID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_E_SCOPEUSERID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_E_ENABLED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(scopeGroupId);

				qPos.add(scopeUserId);

				qPos.add(enabled);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_SG_SU_E,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of digest configurations where scopeUserId = &#63; and frequency = &#63;.
	 *
	 * @param scopeUserId the scope user ID
	 * @param frequency the frequency
	 * @return the number of matching digest configurations
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySU_F(long scopeUserId, int frequency)
		throws SystemException {
		Object[] finderArgs = new Object[] { scopeUserId, frequency };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SU_F,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_SU_F_SCOPEUSERID_2);

			query.append(_FINDER_COLUMN_SU_F_FREQUENCY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(scopeUserId);

				qPos.add(frequency);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SU_F,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
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
	public int countByC_SG_SU(long companyId, long scopeGroupId,
		long scopeUserId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, scopeGroupId, scopeUserId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_SG_SU,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_C_SG_SU_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_SCOPEGROUPID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_SCOPEUSERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(scopeGroupId);

				qPos.add(scopeUserId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_SG_SU,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
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
	public int countByC_SG_SU_F(long companyId, long scopeGroupId,
		long scopeUserId, int frequency) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, scopeGroupId, scopeUserId, frequency
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_SG_SU_F,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_DIGESTCONFIGURATION_WHERE);

			query.append(_FINDER_COLUMN_C_SG_SU_F_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_F_SCOPEGROUPID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_F_SCOPEUSERID_2);

			query.append(_FINDER_COLUMN_C_SG_SU_F_FREQUENCY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(scopeGroupId);

				qPos.add(scopeUserId);

				qPos.add(frequency);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_SG_SU_F,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of digest configurations.
	 *
	 * @return the number of digest configurations
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DIGESTCONFIGURATION);

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
	 * Initializes the digest configuration persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portlet.digest.activity.model.DigestConfiguration")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DigestConfiguration>> listenersList = new ArrayList<ModelListener<DigestConfiguration>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<DigestConfiguration>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(DigestConfigurationImpl.class.getName());
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
	private static final String _SQL_SELECT_DIGESTCONFIGURATION = "SELECT digestConfiguration FROM DigestConfiguration digestConfiguration";
	private static final String _SQL_SELECT_DIGESTCONFIGURATION_WHERE = "SELECT digestConfiguration FROM DigestConfiguration digestConfiguration WHERE ";
	private static final String _SQL_COUNT_DIGESTCONFIGURATION = "SELECT COUNT(digestConfiguration) FROM DigestConfiguration digestConfiguration";
	private static final String _SQL_COUNT_DIGESTCONFIGURATION_WHERE = "SELECT COUNT(digestConfiguration) FROM DigestConfiguration digestConfiguration WHERE ";
	private static final String _FINDER_COLUMN_C_E_COMPANYID_2 = "digestConfiguration.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_E_ENABLED_2 = "digestConfiguration.enabled = ?";
	private static final String _FINDER_COLUMN_SCOPEGROUPID_SCOPEGROUPID_2 = "digestConfiguration.scopeGroupId = ?";
	private static final String _FINDER_COLUMN_SG_F_SCOPEGROUPID_2 = "digestConfiguration.scopeGroupId = ? AND ";
	private static final String _FINDER_COLUMN_SG_F_FREQUENCY_2 = "digestConfiguration.frequency = ?";
	private static final String _FINDER_COLUMN_SCOPEUSERID_SCOPEUSERID_2 = "digestConfiguration.scopeUserId = ?";
	private static final String _FINDER_COLUMN_C_SG_SU_E_COMPANYID_2 = "digestConfiguration.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_SG_SU_E_SCOPEGROUPID_2 = "digestConfiguration.scopeGroupId = ? AND ";
	private static final String _FINDER_COLUMN_C_SG_SU_E_SCOPEUSERID_2 = "digestConfiguration.scopeUserId = ? AND ";
	private static final String _FINDER_COLUMN_C_SG_SU_E_ENABLED_2 = "digestConfiguration.enabled = ?";
	private static final String _FINDER_COLUMN_SU_F_SCOPEUSERID_2 = "digestConfiguration.scopeUserId = ? AND ";
	private static final String _FINDER_COLUMN_SU_F_FREQUENCY_2 = "digestConfiguration.frequency = ?";
	private static final String _FINDER_COLUMN_C_SG_SU_COMPANYID_2 = "digestConfiguration.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_SG_SU_SCOPEGROUPID_2 = "digestConfiguration.scopeGroupId = ? AND ";
	private static final String _FINDER_COLUMN_C_SG_SU_SCOPEUSERID_2 = "digestConfiguration.scopeUserId = ?";
	private static final String _FINDER_COLUMN_C_SG_SU_F_COMPANYID_2 = "digestConfiguration.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_SG_SU_F_SCOPEGROUPID_2 = "digestConfiguration.scopeGroupId = ? AND ";
	private static final String _FINDER_COLUMN_C_SG_SU_F_SCOPEUSERID_2 = "digestConfiguration.scopeUserId = ? AND ";
	private static final String _FINDER_COLUMN_C_SG_SU_F_FREQUENCY_2 = "digestConfiguration.frequency = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "digestConfiguration.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DigestConfiguration exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DigestConfiguration exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(DigestConfigurationPersistenceImpl.class);
	private static DigestConfiguration _nullDigestConfiguration = new DigestConfigurationImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DigestConfiguration> toCacheModel() {
				return _nullDigestConfigurationCacheModel;
			}
		};

	private static CacheModel<DigestConfiguration> _nullDigestConfigurationCacheModel =
		new CacheModel<DigestConfiguration>() {
			public DigestConfiguration toEntityModel() {
				return _nullDigestConfiguration;
			}
		};
}