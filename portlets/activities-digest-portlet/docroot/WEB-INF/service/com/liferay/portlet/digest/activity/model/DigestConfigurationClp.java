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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.digest.activity.service.ClpSerializer;
import com.liferay.portlet.digest.activity.service.DigestConfigurationLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class DigestConfigurationClp extends BaseModelImpl<DigestConfiguration>
	implements DigestConfiguration {
	public DigestConfigurationClp() {
	}

	public Class<?> getModelClass() {
		return DigestConfiguration.class;
	}

	public String getModelClassName() {
		return DigestConfiguration.class.getName();
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long primaryKey) {
		setId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_id);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("enabled", getEnabled());
		attributes.put("frequency", getFrequency());
		attributes.put("scopeGroupId", getScopeGroupId());
		attributes.put("scopeUserId", getScopeUserId());
		attributes.put("summaryLength", getSummaryLength());
		attributes.put("activityTypes", getActivityTypes());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
		}

		Integer frequency = (Integer)attributes.get("frequency");

		if (frequency != null) {
			setFrequency(frequency);
		}

		Long scopeGroupId = (Long)attributes.get("scopeGroupId");

		if (scopeGroupId != null) {
			setScopeGroupId(scopeGroupId);
		}

		Long scopeUserId = (Long)attributes.get("scopeUserId");

		if (scopeUserId != null) {
			setScopeUserId(scopeUserId);
		}

		Integer summaryLength = (Integer)attributes.get("summaryLength");

		if (summaryLength != null) {
			setSummaryLength(summaryLength);
		}

		String activityTypes = (String)attributes.get("activityTypes");

		if (activityTypes != null) {
			setActivityTypes(activityTypes);
		}
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;

		if (_digestConfigurationRemoteModel != null) {
			try {
				Class<?> clazz = _digestConfigurationRemoteModel.getClass();

				Method method = clazz.getMethod("setId", long.class);

				method.invoke(_digestConfigurationRemoteModel, id);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_digestConfigurationRemoteModel != null) {
			try {
				Class<?> clazz = _digestConfigurationRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_digestConfigurationRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_digestConfigurationRemoteModel != null) {
			try {
				Class<?> clazz = _digestConfigurationRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_digestConfigurationRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_digestConfigurationRemoteModel != null) {
			try {
				Class<?> clazz = _digestConfigurationRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_digestConfigurationRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_digestConfigurationRemoteModel != null) {
			try {
				Class<?> clazz = _digestConfigurationRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_digestConfigurationRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;

		if (_digestConfigurationRemoteModel != null) {
			try {
				Class<?> clazz = _digestConfigurationRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_digestConfigurationRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public boolean getEnabled() {
		return _enabled;
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public void setEnabled(boolean enabled) {
		_enabled = enabled;

		if (_digestConfigurationRemoteModel != null) {
			try {
				Class<?> clazz = _digestConfigurationRemoteModel.getClass();

				Method method = clazz.getMethod("setEnabled", boolean.class);

				method.invoke(_digestConfigurationRemoteModel, enabled);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getFrequency() {
		return _frequency;
	}

	public void setFrequency(int frequency) {
		_frequency = frequency;

		if (_digestConfigurationRemoteModel != null) {
			try {
				Class<?> clazz = _digestConfigurationRemoteModel.getClass();

				Method method = clazz.getMethod("setFrequency", int.class);

				method.invoke(_digestConfigurationRemoteModel, frequency);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getScopeGroupId() {
		return _scopeGroupId;
	}

	public void setScopeGroupId(long scopeGroupId) {
		_scopeGroupId = scopeGroupId;

		if (_digestConfigurationRemoteModel != null) {
			try {
				Class<?> clazz = _digestConfigurationRemoteModel.getClass();

				Method method = clazz.getMethod("setScopeGroupId", long.class);

				method.invoke(_digestConfigurationRemoteModel, scopeGroupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getScopeUserId() {
		return _scopeUserId;
	}

	public void setScopeUserId(long scopeUserId) {
		_scopeUserId = scopeUserId;

		if (_digestConfigurationRemoteModel != null) {
			try {
				Class<?> clazz = _digestConfigurationRemoteModel.getClass();

				Method method = clazz.getMethod("setScopeUserId", long.class);

				method.invoke(_digestConfigurationRemoteModel, scopeUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getScopeUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getScopeUserId(), "uuid", _scopeUserUuid);
	}

	public void setScopeUserUuid(String scopeUserUuid) {
		_scopeUserUuid = scopeUserUuid;
	}

	public int getSummaryLength() {
		return _summaryLength;
	}

	public void setSummaryLength(int summaryLength) {
		_summaryLength = summaryLength;

		if (_digestConfigurationRemoteModel != null) {
			try {
				Class<?> clazz = _digestConfigurationRemoteModel.getClass();

				Method method = clazz.getMethod("setSummaryLength", int.class);

				method.invoke(_digestConfigurationRemoteModel, summaryLength);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getActivityTypes() {
		return _activityTypes;
	}

	public void setActivityTypes(String activityTypes) {
		_activityTypes = activityTypes;

		if (_digestConfigurationRemoteModel != null) {
			try {
				Class<?> clazz = _digestConfigurationRemoteModel.getClass();

				Method method = clazz.getMethod("setActivityTypes", String.class);

				method.invoke(_digestConfigurationRemoteModel, activityTypes);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getFrequencyAsString() {
		try {
			String methodName = "getFrequencyAsString";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public void setStartDate(java.util.Date startDate) {
		try {
			String methodName = "setStartDate";

			Class<?>[] parameterTypes = new Class<?>[] { java.util.Date.class };

			Object[] parameterValues = new Object[] { startDate };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public void setEndDate(java.util.Date endDate) {
		try {
			String methodName = "setEndDate";

			Class<?>[] parameterTypes = new Class<?>[] { java.util.Date.class };

			Object[] parameterValues = new Object[] { endDate };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isCompanyDigest() {
		try {
			String methodName = "isCompanyDigest";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.Date getStartDate() {
		try {
			String methodName = "getStartDate";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.Date returnObj = (java.util.Date)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.List<com.liferay.portlet.digest.activity.DigestActivityType> getActivityTypesList() {
		try {
			String methodName = "getActivityTypesList";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.portlet.digest.activity.DigestActivityType> returnObj =
				(java.util.List<com.liferay.portlet.digest.activity.DigestActivityType>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.Date getEndDate() {
		try {
			String methodName = "getEndDate";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.Date returnObj = (java.util.Date)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isUserDigest() {
		try {
			String methodName = "isUserDigest";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.List<java.lang.Long> getActivityTypeIds() {
		try {
			String methodName = "getActivityTypeIds";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<java.lang.Long> returnObj = (java.util.List<java.lang.Long>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isGroupDigest() {
		try {
			String methodName = "isGroupDigest";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.Map<java.lang.String, com.liferay.portlet.digest.activity.DigestActivityType> getActivityTypesMap() {
		try {
			String methodName = "getActivityTypesMap";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.Map<java.lang.String, com.liferay.portlet.digest.activity.DigestActivityType> returnObj =
				(java.util.Map<java.lang.String, com.liferay.portlet.digest.activity.DigestActivityType>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getDigestConfigurationRemoteModel() {
		return _digestConfigurationRemoteModel;
	}

	public void setDigestConfigurationRemoteModel(
		BaseModel<?> digestConfigurationRemoteModel) {
		_digestConfigurationRemoteModel = digestConfigurationRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _digestConfigurationRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_digestConfigurationRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			DigestConfigurationLocalServiceUtil.addDigestConfiguration(this);
		}
		else {
			DigestConfigurationLocalServiceUtil.updateDigestConfiguration(this);
		}
	}

	@Override
	public DigestConfiguration toEscapedModel() {
		return (DigestConfiguration)ProxyUtil.newProxyInstance(DigestConfiguration.class.getClassLoader(),
			new Class[] { DigestConfiguration.class },
			new AutoEscapeBeanHandler(this));
	}

	public DigestConfiguration toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		DigestConfigurationClp clone = new DigestConfigurationClp();

		clone.setId(getId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setEnabled(getEnabled());
		clone.setFrequency(getFrequency());
		clone.setScopeGroupId(getScopeGroupId());
		clone.setScopeUserId(getScopeUserId());
		clone.setSummaryLength(getSummaryLength());
		clone.setActivityTypes(getActivityTypes());

		return clone;
	}

	public int compareTo(DigestConfiguration digestConfiguration) {
		long primaryKey = digestConfiguration.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DigestConfigurationClp)) {
			return false;
		}

		DigestConfigurationClp digestConfiguration = (DigestConfigurationClp)obj;

		long primaryKey = digestConfiguration.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{id=");
		sb.append(getId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", enabled=");
		sb.append(getEnabled());
		sb.append(", frequency=");
		sb.append(getFrequency());
		sb.append(", scopeGroupId=");
		sb.append(getScopeGroupId());
		sb.append(", scopeUserId=");
		sb.append(getScopeUserId());
		sb.append(", summaryLength=");
		sb.append(getSummaryLength());
		sb.append(", activityTypes=");
		sb.append(getActivityTypes());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portlet.digest.activity.model.DigestConfiguration");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>id</column-name><column-value><![CDATA[");
		sb.append(getId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>enabled</column-name><column-value><![CDATA[");
		sb.append(getEnabled());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>frequency</column-name><column-value><![CDATA[");
		sb.append(getFrequency());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scopeGroupId</column-name><column-value><![CDATA[");
		sb.append(getScopeGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scopeUserId</column-name><column-value><![CDATA[");
		sb.append(getScopeUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>summaryLength</column-name><column-value><![CDATA[");
		sb.append(getSummaryLength());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>activityTypes</column-name><column-value><![CDATA[");
		sb.append(getActivityTypes());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _id;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _groupId;
	private long _userId;
	private String _userUuid;
	private boolean _enabled;
	private int _frequency;
	private long _scopeGroupId;
	private long _scopeUserId;
	private String _scopeUserUuid;
	private int _summaryLength;
	private String _activityTypes;
	private BaseModel<?> _digestConfigurationRemoteModel;
}