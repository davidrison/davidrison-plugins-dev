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

package com.liferay.portlet.digest.activity.model.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivityType;
import com.liferay.portlet.digest.activity.impl.DigestActivityTypeImpl;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;
import com.liferay.portlet.digest.activity.util.comparator.DigestActivityTypeComparator;
import com.liferay.portlet.digest.util.DigestConstants;
import com.liferay.portlet.digest.util.DigestFrequencyThreadLocal;
import com.liferay.portlet.digest.util.DigestHelperUtil;

import java.util.*;

/**
 * The extended model implementation for the DigestConfiguration service. Represents a row in the &quot;Digest_DigestConfiguration&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portlet.digest.activity.model.DigestConfiguration} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class DigestConfigurationImpl extends DigestConfigurationBaseImpl {
	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a digest configuration model instance should use the {@link com.liferay.portlet.digest.activity.model.DigestConfiguration} interface instead.
	 */
	public DigestConfigurationImpl() {
	}

	public boolean isCompanyDigest() {

		return ((getCompanyId() > 0) &&!isUserDigest() && !isGroupDigest());
	}

	public boolean isGroupDigest() {
		return (getScopeGroupId() > 0);
	}

	public boolean isUserDigest() {
		return (getScopeUserId() > 0);
	}

	public List<Long> getActivityTypeIds() throws Exception {
		List<Long> activityTypeIds =
				new ArrayList<Long>();

		List<DigestActivityType> digestActivityTypes =
				getActivityTypesList();

		for (DigestActivityType digestActivityType : digestActivityTypes) {
			long classNameId = ClassNameLocalServiceUtil.getClassNameId(
					digestActivityType.getName());

			if (classNameId > 0) {
				activityTypeIds.add(classNameId);
			}
		}

		return activityTypeIds;
	}

	public List<DigestActivityType> getActivityTypesList() throws Exception {
		if (Validator.isNotNull(_digestActivityTypes)) {
			if (_originalDigestActivityTypes.equals(getActivityTypes())) {
				return _digestActivityTypes;
			}
		}

		String digestConfigurationActivityTypes =
				super.getActivityTypes();

		if (Validator.isNull(digestConfigurationActivityTypes)) {
			return new ArrayList<DigestActivityType>();
		}

		List<DigestActivityType> digestActivityTypes =
				new ArrayList<DigestActivityType>();


		JSONArray digestActivityTypesJSONArray =
				JSONFactoryUtil.createJSONArray(digestConfigurationActivityTypes);

		for (int i = 0; i < digestActivityTypesJSONArray.length(); i++) {
			digestActivityTypes.add(
					new DigestActivityTypeImpl(
							digestActivityTypesJSONArray.getString(i)));
		}

		Collections.sort(digestActivityTypes, new DigestActivityTypeComparator());

		_digestActivityTypes = digestActivityTypes;

		_originalDigestActivityTypes = getActivityTypes();

		return _digestActivityTypes;
	}

	public Map<String, DigestActivityType> getActivityTypesMap() throws Exception {
		if (Validator.isNotNull(_digestActivityTypesMap)) {
			if (_originalDigestActivityTypes.equals(getActivityTypes())) {
				return _digestActivityTypesMap;
			}
		}

		Map<String, DigestActivityType> digestActivityTypes =
				new HashMap<String, DigestActivityType>();

		List<DigestActivityType> digestActivityTypeList =
				getActivityTypesList();

		for (DigestActivityType digestActivityType : digestActivityTypeList) {
			digestActivityTypes.put(digestActivityType.getName(), digestActivityType);
		}

		_digestActivityTypesMap = digestActivityTypes;

		_originalDigestActivityTypes = getActivityTypes();

		return _digestActivityTypesMap;
	}

	@Override
	public int getFrequency() {
		if (isGroupDigest()) {
			try {
				// site/group must use the setting from portal

				return getPortal().getFrequency();
			}
			catch (Throwable t) {
				if (_log.isDebugEnabled()) {
					_log.debug("Unable to retrieve portal frequency", t);
				}
			}
		}

		return super.getFrequency();
	}

	public String getFrequencyAsString() throws Exception {
		return DigestHelperUtil.getFrequencyAsString(getFrequency());
	}

	public Date getStartDate() {
		if (Validator.isNull(_startDate)) {
			_startDate = DateUtil.newDate();

			Calendar cal = Calendar.getInstance();
			cal.setTime(getEndDate());

			int frequency = DigestFrequencyThreadLocal.getDigestFrequency();

			if (frequency == DigestConstants.FREQUENCY_DAILY) {
				cal.add(Calendar.HOUR_OF_DAY, -24);

				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);

				_startDate = cal.getTime();

			} else if (frequency == DigestConstants.FREQUENCY_WEEKLY) {
				cal.add(Calendar.DAY_OF_WEEK, -DigestConstants.WEEK_IN_DAYS);

				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);

				_startDate = cal.getTime();

			} else {
				if (_log.isDebugEnabled()) {
					_log.debug("Current Configuration does not allow for digest activities for group id " + getScopeGroupId());
				}
			}
		}

		return _startDate;
	}

	public Date getEndDate() {

		if (Validator.isNull(_endDate)) {
			_endDate = DateUtil.newDate();
		}

		return _endDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	@Override
	public Object clone() {
		DigestConfiguration digestConfiguration = (DigestConfiguration) super.clone();

		digestConfiguration.setId(0);
		digestConfiguration.setEnabled(false);

		return digestConfiguration;
	}

	protected DigestConfiguration getPortal() throws Exception {
		if (Validator.isNull(_portal)) {
			try {
				_portal = DigestHelperUtil.getActivePortalDigestConfiguration(getCompanyId());
			}
			catch (Throwable t) {
				if (_log.isDebugEnabled()) {
					_log.debug("Unable to retrieve portal digest configuration, using current configuration.");
				}
				_portal = this;
			}
		}

		return _portal;
	}

	private Date _startDate;
	private Date _endDate;

	private List<DigestActivityType> _digestActivityTypes;
	private Map<String, DigestActivityType> _digestActivityTypesMap;
	private String _originalDigestActivityTypes;
	private DigestConfiguration _portal;

	private static final Log _log = LogFactoryUtil.getLog(DigestConfigurationImpl.class);

}