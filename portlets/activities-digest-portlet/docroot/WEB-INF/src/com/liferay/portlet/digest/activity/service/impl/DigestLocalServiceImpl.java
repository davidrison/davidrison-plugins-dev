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

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;
import com.liferay.portlet.digest.activity.service.base.DigestLocalServiceBaseImpl;
import com.liferay.portlet.digest.builder.DigestBuilderUtil;
import com.liferay.portlet.digest.util.DigestConstants;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.digest.util.PropsValues;

import javax.portlet.PortletPreferences;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the digest local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portlet.digest.activity.service.DigestLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.digest.activity.service.base.DigestLocalServiceBaseImpl
 * @see com.liferay.portlet.digest.activity.service.DigestLocalServiceUtil
 */
public class DigestLocalServiceImpl extends DigestLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.portlet.digest.activity.service.DigestLocalServiceUtil} to access the digest local service.
	 */

	public void processInactiveUsers(long companyId, int frequency) throws PortalException, SystemException {

		try {
			DigestConfiguration portalDigestConfiguration = DigestHelperUtil.getActivePortalDigestConfiguration(companyId);

			if (!portalDigestConfiguration.isEnabled()) {
				if (_log.isInfoEnabled()) {
					_log.info("Portal Digest Configuration is not enabled, exiting.");
				}

				return;
			}

			DynamicQuery dynamicQuery = _buildUserIdDynamicQuery(companyId, true);

			_doProcessUsers(companyId, frequency, true, dynamicQuery);
		}
		catch (Throwable t) {
			throw new PortalException(t);
		}
	}

	public void processUsers(long companyId, int frequency) throws PortalException, SystemException {

		try {
			DigestConfiguration portalDigestConfiguration = DigestHelperUtil.getActivePortalDigestConfiguration(companyId);

			if (!portalDigestConfiguration.isEnabled()) {
				if (_log.isInfoEnabled()) {
					_log.info("Portal Digest Configuration is not enabled, exiting.");
				}

				return;
			}

			DynamicQuery dynamicQuery = _buildUserIdDynamicQuery(companyId, false);

			_doProcessUsers(companyId, frequency, false, dynamicQuery);
		}
		catch (Throwable t) {
			throw new PortalException(t);
		}
	}

	private void _addInactiveUserCriteria(long companyId, DynamicQuery dynamicQuery) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		PortletPreferences preferences = PrefsPropsUtil.getPreferences(
				companyId);

		int inactiveUserDays = GetterUtil.getInteger(
				preferences.getValue(
						DigestConstants.PREFERENCE_DIGEST_INACTIVE_USER_NUMBER_DAYS,
						"" + PropsValues.DIGEST_ACTIVITY_INACTIVE_USER_NUMBER_DAYS));

		if (inactiveUserDays > DigestConstants.MAX_INACTIVE_NUMBER_DAYS) {
			inactiveUserDays = DigestConstants.MAX_INACTIVE_NUMBER_DAYS;
		}

		cal.add(Calendar.DAY_OF_MONTH, -inactiveUserDays);

		Property lastLoginDate = PropertyFactoryUtil.forName("lastLoginDate");

		dynamicQuery.add(lastLoginDate.lt(cal.getTime()));
	}

	private void _addUserCriteria(long companyId, DynamicQuery dynamicQuery) throws Exception {
		Property companyIdProperty = PropertyFactoryUtil.forName("companyId");

		dynamicQuery.add(
			companyIdProperty.eq(companyId)
		);

		Property statusProperty = PropertyFactoryUtil.forName("status");

		dynamicQuery.add(
			statusProperty.eq(WorkflowConstants.STATUS_APPROVED)
		);
	}

	private DynamicQuery _buildUserIdDynamicQuery(long companyId, boolean inactive) throws PortalException, SystemException {
		DynamicQuery dynamicQuery = null;

		try {
			dynamicQuery = DynamicQueryFactoryUtil.forClass(
					User.class, PortalClassLoaderUtil.getClassLoader());

			Projection minUserIdProjection = ProjectionFactoryUtil.min("userId");
			Projection maxUserIdProjection = ProjectionFactoryUtil.max("userId");

			ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

			projectionList.add(minUserIdProjection);
			projectionList.add(maxUserIdProjection);

			dynamicQuery.setProjection(projectionList);

			_addUserCriteria(companyId, dynamicQuery);

			if (inactive) {
				_addInactiveUserCriteria(companyId, dynamicQuery);
			}

		}
		catch (Throwable t) {
			throw new PortalException(t);
		}

		return dynamicQuery;
	}

	private DynamicQuery _buildUserDynamicQuery(long companyId, long startUserId, long endUserId, boolean inactive) throws PortalException, SystemException {

		DynamicQuery dynamicQuery = null;

		try {
			dynamicQuery = DynamicQueryFactoryUtil.forClass(
					User.class, PortalClassLoaderUtil.getClassLoader());

			Property userIdProperty = PropertyFactoryUtil.forName("userId");

			dynamicQuery.add(userIdProperty.ge(startUserId));
			dynamicQuery.add(userIdProperty.lt(endUserId));

			_addUserCriteria(companyId, dynamicQuery);

			if (inactive) {
				_addInactiveUserCriteria(companyId, dynamicQuery);
			}
		}
		catch (Throwable t) {
			throw new PortalException(t);
		}

		return dynamicQuery;
	}

	private void _processDigest(
			long companyId, int frequency, boolean inactive, long startUserId, long endUserId)
			throws Exception {

		DynamicQuery dynamicQuery = _buildUserDynamicQuery(companyId, startUserId, endUserId, inactive);

		Property property = PropertyFactoryUtil.forName("userId");

		dynamicQuery.add(property.ge(startUserId));
		dynamicQuery.add(property.lt(endUserId));

		List<User> users = UserLocalServiceUtil.dynamicQuery(dynamicQuery);

		if (users.isEmpty()) {
			return;
		}

		DigestBuilderUtil.processDigest(users, frequency, PropsValues.DIGEST_ACTIVITY_TEMPLATE_ID);
	}

	private void _doProcessUsers(long companyId, int frequency, boolean inactive, DynamicQuery dynamicQuery) throws PortalException, SystemException {

		try {

			List<Object[]> results = UserLocalServiceUtil.dynamicQuery(
					dynamicQuery);

			if (Validator.isNotNull(results) && results.size() > 0) {
				Object[] minAndMaxUserIds = results.get(0);

				if ((minAndMaxUserIds[0] == null) || (minAndMaxUserIds[1] == null)) {
					return;
				}

				long minUserId = (Long) minAndMaxUserIds[0];
				long maxUserId = (Long) minAndMaxUserIds[1];

				long startUserId = minUserId;
				long endUserId = startUserId + _DEFAULT_INTERVAL;

				while (startUserId <= maxUserId) {
					_processDigest(companyId, frequency, inactive, startUserId, endUserId);

					startUserId = endUserId;
					endUserId += _DEFAULT_INTERVAL;
				}
			}

		}
		catch (Throwable t) {
			throw new PortalException(t);
		}
	}

	private static final int _DEFAULT_INTERVAL = 10000;

	private static final Log _log = LogFactoryUtil.getLog(DigestLocalServiceImpl.class);

}