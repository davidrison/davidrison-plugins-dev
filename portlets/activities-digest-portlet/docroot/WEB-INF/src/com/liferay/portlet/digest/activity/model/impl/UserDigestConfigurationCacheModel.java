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
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.digest.activity.model.UserDigestConfiguration;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing UserDigestConfiguration in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserDigestConfiguration
 * @generated
 */
public class UserDigestConfigurationCacheModel implements CacheModel<UserDigestConfiguration>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{id=");
		sb.append(id);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", frequency=");
		sb.append(frequency);
		sb.append(", numInactiveSent=");
		sb.append(numInactiveSent);
		sb.append("}");

		return sb.toString();
	}

	public UserDigestConfiguration toEntityModel() {
		UserDigestConfigurationImpl userDigestConfigurationImpl = new UserDigestConfigurationImpl();

		userDigestConfigurationImpl.setId(id);
		userDigestConfigurationImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			userDigestConfigurationImpl.setCreateDate(null);
		}
		else {
			userDigestConfigurationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			userDigestConfigurationImpl.setModifiedDate(null);
		}
		else {
			userDigestConfigurationImpl.setModifiedDate(new Date(modifiedDate));
		}

		userDigestConfigurationImpl.setUserId(userId);
		userDigestConfigurationImpl.setFrequency(frequency);
		userDigestConfigurationImpl.setNumInactiveSent(numInactiveSent);

		userDigestConfigurationImpl.resetOriginalValues();

		return userDigestConfigurationImpl;
	}

	public long id;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long userId;
	public int frequency;
	public int numInactiveSent;
}