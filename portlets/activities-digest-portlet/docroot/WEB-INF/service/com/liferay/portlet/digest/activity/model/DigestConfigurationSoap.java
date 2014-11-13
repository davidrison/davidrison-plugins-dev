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

package com.liferay.portlet.digest.activity.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class DigestConfigurationSoap implements Serializable {
	public static DigestConfigurationSoap toSoapModel(DigestConfiguration model) {
		DigestConfigurationSoap soapModel = new DigestConfigurationSoap();

		soapModel.setId(model.getId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setEnabled(model.getEnabled());
		soapModel.setFrequency(model.getFrequency());
		soapModel.setScopeGroupId(model.getScopeGroupId());
		soapModel.setScopeUserId(model.getScopeUserId());
		soapModel.setSummaryLength(model.getSummaryLength());
		soapModel.setActivityTypes(model.getActivityTypes());

		return soapModel;
	}

	public static DigestConfigurationSoap[] toSoapModels(
		DigestConfiguration[] models) {
		DigestConfigurationSoap[] soapModels = new DigestConfigurationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DigestConfigurationSoap[][] toSoapModels(
		DigestConfiguration[][] models) {
		DigestConfigurationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DigestConfigurationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DigestConfigurationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DigestConfigurationSoap[] toSoapModels(
		List<DigestConfiguration> models) {
		List<DigestConfigurationSoap> soapModels = new ArrayList<DigestConfigurationSoap>(models.size());

		for (DigestConfiguration model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DigestConfigurationSoap[soapModels.size()]);
	}

	public DigestConfigurationSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public boolean getEnabled() {
		return _enabled;
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public void setEnabled(boolean enabled) {
		_enabled = enabled;
	}

	public int getFrequency() {
		return _frequency;
	}

	public void setFrequency(int frequency) {
		_frequency = frequency;
	}

	public long getScopeGroupId() {
		return _scopeGroupId;
	}

	public void setScopeGroupId(long scopeGroupId) {
		_scopeGroupId = scopeGroupId;
	}

	public long getScopeUserId() {
		return _scopeUserId;
	}

	public void setScopeUserId(long scopeUserId) {
		_scopeUserId = scopeUserId;
	}

	public int getSummaryLength() {
		return _summaryLength;
	}

	public void setSummaryLength(int summaryLength) {
		_summaryLength = summaryLength;
	}

	public String getActivityTypes() {
		return _activityTypes;
	}

	public void setActivityTypes(String activityTypes) {
		_activityTypes = activityTypes;
	}

	private long _id;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _groupId;
	private long _userId;
	private boolean _enabled;
	private int _frequency;
	private long _scopeGroupId;
	private long _scopeUserId;
	private int _summaryLength;
	private String _activityTypes;
}