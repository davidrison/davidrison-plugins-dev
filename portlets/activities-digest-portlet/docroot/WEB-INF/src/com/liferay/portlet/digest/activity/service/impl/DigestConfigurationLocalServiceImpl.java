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

package com.liferay.portlet.digest.activity.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;
import com.liferay.portlet.digest.activity.service.base.DigestConfigurationLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the digest configuration local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portlet.digest.activity.service.DigestConfigurationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.digest.activity.service.base.DigestConfigurationLocalServiceBaseImpl
 * @see com.liferay.portlet.digest.activity.service.DigestConfigurationLocalServiceUtil
 */
public class DigestConfigurationLocalServiceImpl
	extends DigestConfigurationLocalServiceBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.portlet.digest.activity.service.DigestConfigurationLocalServiceUtil} to access the digest configuration local service.
	 */

	public DigestConfiguration addDigestConfiguration(
			long userId, long groupId, long companyId, boolean enabled,  int frequency, long scopeGroupId, long scopeUserId,
			int summaryLength, String activityTypes)
			throws PortalException, SystemException {

		long id = counterLocalService.increment(DigestConfiguration.class.getName());

		DigestConfiguration digestConfiguration = digestConfigurationPersistence.create(id);

		digestConfiguration.setActivityTypes(activityTypes);
		digestConfiguration.setCompanyId(companyId);
		digestConfiguration.setCreateDate(new Date());
		digestConfiguration.setEnabled(enabled);
		digestConfiguration.setFrequency(frequency);
		digestConfiguration.setGroupId(groupId);
		digestConfiguration.setModifiedDate(new Date());
		digestConfiguration.setScopeGroupId(scopeGroupId);
		digestConfiguration.setScopeUserId(scopeUserId);
		digestConfiguration.setSummaryLength(summaryLength);
		digestConfiguration.setUserId(userId);

		digestConfigurationPersistence.update(digestConfiguration, false);

		return digestConfiguration;
	}

	public List<DigestConfiguration> getCompanyDigestConfigurations() throws PortalException, SystemException {

		List<Company> companies =
				CompanyLocalServiceUtil.getCompanies(
						QueryUtil.ALL_POS, QueryUtil.ALL_POS
				);

		List<Long> companyIdList =
				new ArrayList<Long>();

		for (Company company : companies) {
			companyIdList.add(company.getCompanyId());
		}


		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
				DigestConfiguration.class);

		// companies

		dynamicQuery.add(
				RestrictionsFactoryUtil.in("companyId", companyIdList.toArray())
		);

		// scope group id

		dynamicQuery.add(
				RestrictionsFactoryUtil.isNull("scopeGroupId"));

		// scope user id

		dynamicQuery.add(
				RestrictionsFactoryUtil.isNull("scopeUserId"));

		return	digestConfigurationPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<DigestConfiguration> getDigestConfigurationsByCompanyEnabled(long companyId, boolean enabled) throws PortalException, SystemException {
		return digestConfigurationPersistence.findByC_E(companyId, enabled);
	}

	public DigestConfiguration fetchDigestConfigurationByScopeGroupId(long scopeGroupId) throws PortalException, SystemException {
		return digestConfigurationPersistence.fetchByScopeGroupId(scopeGroupId);
	}

	public DigestConfiguration fetchDigestConfigurationBySG_F(long scopeGroupId, int frequency) throws PortalException, SystemException {
		return digestConfigurationPersistence.fetchBySG_F(scopeGroupId, frequency);
	}

	public DigestConfiguration fetchDigestConfigurationByScopeUserId(long scopeUserId) throws PortalException, SystemException {
		return digestConfigurationPersistence.fetchByScopeUserId(scopeUserId);
	}

	public DigestConfiguration fetchDigestConfigurationBySU_F(long scopeUserId, int frequency) throws PortalException, SystemException {
		return digestConfigurationPersistence.fetchBySU_F(scopeUserId, frequency);
	}

	public DigestConfiguration fetchDigestConfigurationByC_SG_SU(long companyId, long scopeGroupId, long scopeUserId) throws PortalException, SystemException {
		return digestConfigurationPersistence.fetchByC_SG_SU(companyId, scopeGroupId, scopeUserId);
	}

	public DigestConfiguration fetchDigestConfigurationByC_SG_SU_F(long companyId, long scopeGroupId, long scopeUserId, int frequency) throws PortalException, SystemException {
		return digestConfigurationPersistence.fetchByC_SG_SU_F(companyId, scopeGroupId, scopeUserId, frequency);
	}

	public boolean isUserDigestConfigurationEnabled(long companyId, long groupId, long userId) throws SystemException {
		// users always defer to site for enable status

		DigestConfiguration digestConfiguration = digestConfigurationPersistence.fetchByC_SG_SU_E(companyId, groupId, 0, true);

		if (Validator.isNull(digestConfiguration)) {
			digestConfiguration = digestConfigurationPersistence.fetchByC_SG_SU_E(companyId, 0, 0, true);
		}

		return (Validator.isNotNull(digestConfiguration) && digestConfiguration.isEnabled());
	}

	public DigestConfiguration updateDigestConfiguration(
			long id, long userId, long groupId, long companyId, boolean enabled, int frequency, long scopeGroupId, long scopeUserId,
			int summaryLength, String activityTypes)
			throws PortalException, SystemException {

		DigestConfiguration digestConfiguration =
				digestConfigurationPersistence.findByPrimaryKey(id);

		digestConfiguration.setActivityTypes(activityTypes);
		digestConfiguration.setCompanyId(companyId);
		digestConfiguration.setEnabled(enabled);
		digestConfiguration.setFrequency(frequency);
		digestConfiguration.setGroupId(groupId);
		digestConfiguration.setModifiedDate(new Date());
		digestConfiguration.setScopeGroupId(scopeGroupId);
		digestConfiguration.setScopeUserId(scopeUserId);
		digestConfiguration.setSummaryLength(summaryLength);
		digestConfiguration.setUserId(userId);

		digestConfigurationPersistence.update(digestConfiguration, false);

		return digestConfiguration;
	}

}