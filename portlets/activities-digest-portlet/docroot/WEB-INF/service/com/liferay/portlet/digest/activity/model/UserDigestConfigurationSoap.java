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
public class UserDigestConfigurationSoap implements Serializable {
	public static UserDigestConfigurationSoap toSoapModel(
		UserDigestConfiguration model) {
		UserDigestConfigurationSoap soapModel = new UserDigestConfigurationSoap();

		soapModel.setId(model.getId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setUserId(model.getUserId());
		soapModel.setFrequency(model.getFrequency());
		soapModel.setNumInactiveSent(model.getNumInactiveSent());

		return soapModel;
	}

	public static UserDigestConfigurationSoap[] toSoapModels(
		UserDigestConfiguration[] models) {
		UserDigestConfigurationSoap[] soapModels = new UserDigestConfigurationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserDigestConfigurationSoap[][] toSoapModels(
		UserDigestConfiguration[][] models) {
		UserDigestConfigurationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserDigestConfigurationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserDigestConfigurationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserDigestConfigurationSoap[] toSoapModels(
		List<UserDigestConfiguration> models) {
		List<UserDigestConfigurationSoap> soapModels = new ArrayList<UserDigestConfigurationSoap>(models.size());

		for (UserDigestConfiguration model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserDigestConfigurationSoap[soapModels.size()]);
	}

	public UserDigestConfigurationSoap() {
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

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public int getFrequency() {
		return _frequency;
	}

	public void setFrequency(int frequency) {
		_frequency = frequency;
	}

	public int getNumInactiveSent() {
		return _numInactiveSent;
	}

	public void setNumInactiveSent(int numInactiveSent) {
		_numInactiveSent = numInactiveSent;
	}

	private long _id;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _userId;
	private int _frequency;
	private int _numInactiveSent;
}