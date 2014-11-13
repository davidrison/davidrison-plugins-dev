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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.digest.activity.model.DigestConfiguration;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing DigestConfiguration in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DigestConfiguration
 * @generated
 */
public class DigestConfigurationCacheModel implements CacheModel<DigestConfiguration>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{id=");
		sb.append(id);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", enabled=");
		sb.append(enabled);
		sb.append(", frequency=");
		sb.append(frequency);
		sb.append(", scopeGroupId=");
		sb.append(scopeGroupId);
		sb.append(", scopeUserId=");
		sb.append(scopeUserId);
		sb.append(", summaryLength=");
		sb.append(summaryLength);
		sb.append(", activityTypes=");
		sb.append(activityTypes);
		sb.append("}");

		return sb.toString();
	}

	public DigestConfiguration toEntityModel() {
		DigestConfigurationImpl digestConfigurationImpl = new DigestConfigurationImpl();

		digestConfigurationImpl.setId(id);
		digestConfigurationImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			digestConfigurationImpl.setCreateDate(null);
		}
		else {
			digestConfigurationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			digestConfigurationImpl.setModifiedDate(null);
		}
		else {
			digestConfigurationImpl.setModifiedDate(new Date(modifiedDate));
		}

		digestConfigurationImpl.setGroupId(groupId);
		digestConfigurationImpl.setUserId(userId);
		digestConfigurationImpl.setEnabled(enabled);
		digestConfigurationImpl.setFrequency(frequency);
		digestConfigurationImpl.setScopeGroupId(scopeGroupId);
		digestConfigurationImpl.setScopeUserId(scopeUserId);
		digestConfigurationImpl.setSummaryLength(summaryLength);

		if (activityTypes == null) {
			digestConfigurationImpl.setActivityTypes(StringPool.BLANK);
		}
		else {
			digestConfigurationImpl.setActivityTypes(activityTypes);
		}

		digestConfigurationImpl.resetOriginalValues();

		return digestConfigurationImpl;
	}

	public long id;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long groupId;
	public long userId;
	public boolean enabled;
	public int frequency;
	public long scopeGroupId;
	public long scopeUserId;
	public int summaryLength;
	public String activityTypes;
}