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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserDigestConfiguration}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserDigestConfiguration
 * @generated
 */
public class UserDigestConfigurationWrapper implements UserDigestConfiguration,
	ModelWrapper<UserDigestConfiguration> {
	public UserDigestConfigurationWrapper(
		UserDigestConfiguration userDigestConfiguration) {
		_userDigestConfiguration = userDigestConfiguration;
	}

	public Class<?> getModelClass() {
		return UserDigestConfiguration.class;
	}

	public String getModelClassName() {
		return UserDigestConfiguration.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("userId", getUserId());
		attributes.put("frequency", getFrequency());
		attributes.put("numInactiveSent", getNumInactiveSent());

		return attributes;
	}

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

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Integer frequency = (Integer)attributes.get("frequency");

		if (frequency != null) {
			setFrequency(frequency);
		}

		Integer numInactiveSent = (Integer)attributes.get("numInactiveSent");

		if (numInactiveSent != null) {
			setNumInactiveSent(numInactiveSent);
		}
	}

	/**
	* Returns the primary key of this user digest configuration.
	*
	* @return the primary key of this user digest configuration
	*/
	public long getPrimaryKey() {
		return _userDigestConfiguration.getPrimaryKey();
	}

	/**
	* Sets the primary key of this user digest configuration.
	*
	* @param primaryKey the primary key of this user digest configuration
	*/
	public void setPrimaryKey(long primaryKey) {
		_userDigestConfiguration.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ID of this user digest configuration.
	*
	* @return the ID of this user digest configuration
	*/
	public long getId() {
		return _userDigestConfiguration.getId();
	}

	/**
	* Sets the ID of this user digest configuration.
	*
	* @param id the ID of this user digest configuration
	*/
	public void setId(long id) {
		_userDigestConfiguration.setId(id);
	}

	/**
	* Returns the company ID of this user digest configuration.
	*
	* @return the company ID of this user digest configuration
	*/
	public long getCompanyId() {
		return _userDigestConfiguration.getCompanyId();
	}

	/**
	* Sets the company ID of this user digest configuration.
	*
	* @param companyId the company ID of this user digest configuration
	*/
	public void setCompanyId(long companyId) {
		_userDigestConfiguration.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this user digest configuration.
	*
	* @return the create date of this user digest configuration
	*/
	public java.util.Date getCreateDate() {
		return _userDigestConfiguration.getCreateDate();
	}

	/**
	* Sets the create date of this user digest configuration.
	*
	* @param createDate the create date of this user digest configuration
	*/
	public void setCreateDate(java.util.Date createDate) {
		_userDigestConfiguration.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this user digest configuration.
	*
	* @return the modified date of this user digest configuration
	*/
	public java.util.Date getModifiedDate() {
		return _userDigestConfiguration.getModifiedDate();
	}

	/**
	* Sets the modified date of this user digest configuration.
	*
	* @param modifiedDate the modified date of this user digest configuration
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_userDigestConfiguration.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the user ID of this user digest configuration.
	*
	* @return the user ID of this user digest configuration
	*/
	public long getUserId() {
		return _userDigestConfiguration.getUserId();
	}

	/**
	* Sets the user ID of this user digest configuration.
	*
	* @param userId the user ID of this user digest configuration
	*/
	public void setUserId(long userId) {
		_userDigestConfiguration.setUserId(userId);
	}

	/**
	* Returns the user uuid of this user digest configuration.
	*
	* @return the user uuid of this user digest configuration
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userDigestConfiguration.getUserUuid();
	}

	/**
	* Sets the user uuid of this user digest configuration.
	*
	* @param userUuid the user uuid of this user digest configuration
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_userDigestConfiguration.setUserUuid(userUuid);
	}

	/**
	* Returns the frequency of this user digest configuration.
	*
	* @return the frequency of this user digest configuration
	*/
	public int getFrequency() {
		return _userDigestConfiguration.getFrequency();
	}

	/**
	* Sets the frequency of this user digest configuration.
	*
	* @param frequency the frequency of this user digest configuration
	*/
	public void setFrequency(int frequency) {
		_userDigestConfiguration.setFrequency(frequency);
	}

	/**
	* Returns the num inactive sent of this user digest configuration.
	*
	* @return the num inactive sent of this user digest configuration
	*/
	public int getNumInactiveSent() {
		return _userDigestConfiguration.getNumInactiveSent();
	}

	/**
	* Sets the num inactive sent of this user digest configuration.
	*
	* @param numInactiveSent the num inactive sent of this user digest configuration
	*/
	public void setNumInactiveSent(int numInactiveSent) {
		_userDigestConfiguration.setNumInactiveSent(numInactiveSent);
	}

	public boolean isNew() {
		return _userDigestConfiguration.isNew();
	}

	public void setNew(boolean n) {
		_userDigestConfiguration.setNew(n);
	}

	public boolean isCachedModel() {
		return _userDigestConfiguration.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_userDigestConfiguration.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _userDigestConfiguration.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _userDigestConfiguration.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_userDigestConfiguration.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _userDigestConfiguration.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_userDigestConfiguration.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new UserDigestConfigurationWrapper((UserDigestConfiguration)_userDigestConfiguration.clone());
	}

	public int compareTo(
		com.liferay.portlet.digest.activity.model.UserDigestConfiguration userDigestConfiguration) {
		return _userDigestConfiguration.compareTo(userDigestConfiguration);
	}

	@Override
	public int hashCode() {
		return _userDigestConfiguration.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.digest.activity.model.UserDigestConfiguration> toCacheModel() {
		return _userDigestConfiguration.toCacheModel();
	}

	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration toEscapedModel() {
		return new UserDigestConfigurationWrapper(_userDigestConfiguration.toEscapedModel());
	}

	public com.liferay.portlet.digest.activity.model.UserDigestConfiguration toUnescapedModel() {
		return new UserDigestConfigurationWrapper(_userDigestConfiguration.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _userDigestConfiguration.toString();
	}

	public java.lang.String toXmlString() {
		return _userDigestConfiguration.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_userDigestConfiguration.persist();
	}

	public java.lang.String getFrequencyAsString() throws java.lang.Exception {
		return _userDigestConfiguration.getFrequencyAsString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserDigestConfigurationWrapper)) {
			return false;
		}

		UserDigestConfigurationWrapper userDigestConfigurationWrapper = (UserDigestConfigurationWrapper)obj;

		if (Validator.equals(_userDigestConfiguration,
					userDigestConfigurationWrapper._userDigestConfiguration)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public UserDigestConfiguration getWrappedUserDigestConfiguration() {
		return _userDigestConfiguration;
	}

	public UserDigestConfiguration getWrappedModel() {
		return _userDigestConfiguration;
	}

	public void resetOriginalValues() {
		_userDigestConfiguration.resetOriginalValues();
	}

	private UserDigestConfiguration _userDigestConfiguration;
}