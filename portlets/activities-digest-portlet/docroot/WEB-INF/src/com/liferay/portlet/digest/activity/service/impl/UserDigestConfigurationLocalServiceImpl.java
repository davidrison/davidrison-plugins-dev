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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.digest.activity.model.UserDigestConfiguration;
import com.liferay.portlet.digest.activity.service.base.UserDigestConfigurationLocalServiceBaseImpl;

import java.util.Date;

/**
 * The implementation of the user digest configuration local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portlet.digest.activity.service.UserDigestConfigurationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.digest.activity.service.base.UserDigestConfigurationLocalServiceBaseImpl
 * @see com.liferay.portlet.digest.activity.service.UserDigestConfigurationLocalServiceUtil
 */
public class UserDigestConfigurationLocalServiceImpl
	extends UserDigestConfigurationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.portlet.digest.activity.service.UserDigestConfigurationLocalServiceUtil} to access the user digest configuration local service.
	 */

	public UserDigestConfiguration fetchUserDigestConfigurationByUserId(long userId) throws PortalException, SystemException {
		return userDigestConfigurationPersistence.fetchByUserId(userId);
	}

	public UserDigestConfiguration addUserDigestConfiguration(long companyId, long userId, int frequency) throws PortalException, SystemException {

		long id = counterLocalService.increment(UserDigestConfiguration.class.getName());

		UserDigestConfiguration userDigestConfiguration = userDigestConfigurationPersistence.create(id);

		userDigestConfiguration.setCompanyId(companyId);
		userDigestConfiguration.setModifiedDate(new Date());
		userDigestConfiguration.setCreateDate(new Date());
		userDigestConfiguration.setUserId(userId);
		userDigestConfiguration.setFrequency(frequency);

		return userDigestConfigurationPersistence.update(userDigestConfiguration, false);
	}

	public void populateDefaultUserConfigurations(long companyId, long groupId, int frequency) throws PortalException, SystemException {
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);

		long[] userIds = UserLocalServiceUtil.getGroupUserIds(group.getGroupId());

		for (int i = 0; i < _MAX_USERS; i++) {
			long userId = userIds[i];

			if (Validator.isNull(
					userDigestConfigurationPersistence.fetchByUserId(userId))) {
				addUserDigestConfiguration(companyId, userId, frequency);
			}

		}
	}

	public UserDigestConfiguration updateUserDigestConfiguration(long id, long userId, int frequency) throws PortalException, SystemException {

		UserDigestConfiguration userDigestConfiguration = userDigestConfigurationPersistence.fetchByUserId(userId);

		if (Validator.isNotNull(userDigestConfiguration)) {
			userDigestConfiguration.setFrequency(frequency);
			userDigestConfiguration.setModifiedDate(new Date());

			try {
				userDigestConfiguration = userDigestConfigurationPersistence.update(userDigestConfiguration, false);
			}
			finally {
				userDigestConfigurationPersistence.clearCache(userDigestConfiguration);
			}
		}

		return null;
	}

	private static final int _MAX_USERS = 5000;
}