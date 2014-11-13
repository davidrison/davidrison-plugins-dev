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
 * This class is a wrapper for {@link DigestConfiguration}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DigestConfiguration
 * @generated
 */
public class DigestConfigurationWrapper implements DigestConfiguration,
	ModelWrapper<DigestConfiguration> {
	public DigestConfigurationWrapper(DigestConfiguration digestConfiguration) {
		_digestConfiguration = digestConfiguration;
	}

	public Class<?> getModelClass() {
		return DigestConfiguration.class;
	}

	public String getModelClassName() {
		return DigestConfiguration.class.getName();
	}

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

	/**
	* Returns the primary key of this digest configuration.
	*
	* @return the primary key of this digest configuration
	*/
	public long getPrimaryKey() {
		return _digestConfiguration.getPrimaryKey();
	}

	/**
	* Sets the primary key of this digest configuration.
	*
	* @param primaryKey the primary key of this digest configuration
	*/
	public void setPrimaryKey(long primaryKey) {
		_digestConfiguration.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ID of this digest configuration.
	*
	* @return the ID of this digest configuration
	*/
	public long getId() {
		return _digestConfiguration.getId();
	}

	/**
	* Sets the ID of this digest configuration.
	*
	* @param id the ID of this digest configuration
	*/
	public void setId(long id) {
		_digestConfiguration.setId(id);
	}

	/**
	* Returns the company ID of this digest configuration.
	*
	* @return the company ID of this digest configuration
	*/
	public long getCompanyId() {
		return _digestConfiguration.getCompanyId();
	}

	/**
	* Sets the company ID of this digest configuration.
	*
	* @param companyId the company ID of this digest configuration
	*/
	public void setCompanyId(long companyId) {
		_digestConfiguration.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this digest configuration.
	*
	* @return the create date of this digest configuration
	*/
	public java.util.Date getCreateDate() {
		return _digestConfiguration.getCreateDate();
	}

	/**
	* Sets the create date of this digest configuration.
	*
	* @param createDate the create date of this digest configuration
	*/
	public void setCreateDate(java.util.Date createDate) {
		_digestConfiguration.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this digest configuration.
	*
	* @return the modified date of this digest configuration
	*/
	public java.util.Date getModifiedDate() {
		return _digestConfiguration.getModifiedDate();
	}

	/**
	* Sets the modified date of this digest configuration.
	*
	* @param modifiedDate the modified date of this digest configuration
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_digestConfiguration.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the group ID of this digest configuration.
	*
	* @return the group ID of this digest configuration
	*/
	public long getGroupId() {
		return _digestConfiguration.getGroupId();
	}

	/**
	* Sets the group ID of this digest configuration.
	*
	* @param groupId the group ID of this digest configuration
	*/
	public void setGroupId(long groupId) {
		_digestConfiguration.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this digest configuration.
	*
	* @return the user ID of this digest configuration
	*/
	public long getUserId() {
		return _digestConfiguration.getUserId();
	}

	/**
	* Sets the user ID of this digest configuration.
	*
	* @param userId the user ID of this digest configuration
	*/
	public void setUserId(long userId) {
		_digestConfiguration.setUserId(userId);
	}

	/**
	* Returns the user uuid of this digest configuration.
	*
	* @return the user uuid of this digest configuration
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _digestConfiguration.getUserUuid();
	}

	/**
	* Sets the user uuid of this digest configuration.
	*
	* @param userUuid the user uuid of this digest configuration
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_digestConfiguration.setUserUuid(userUuid);
	}

	/**
	* Returns the enabled of this digest configuration.
	*
	* @return the enabled of this digest configuration
	*/
	public boolean getEnabled() {
		return _digestConfiguration.getEnabled();
	}

	/**
	* Returns <code>true</code> if this digest configuration is enabled.
	*
	* @return <code>true</code> if this digest configuration is enabled; <code>false</code> otherwise
	*/
	public boolean isEnabled() {
		return _digestConfiguration.isEnabled();
	}

	/**
	* Sets whether this digest configuration is enabled.
	*
	* @param enabled the enabled of this digest configuration
	*/
	public void setEnabled(boolean enabled) {
		_digestConfiguration.setEnabled(enabled);
	}

	/**
	* Returns the frequency of this digest configuration.
	*
	* @return the frequency of this digest configuration
	*/
	public int getFrequency() {
		return _digestConfiguration.getFrequency();
	}

	/**
	* Sets the frequency of this digest configuration.
	*
	* @param frequency the frequency of this digest configuration
	*/
	public void setFrequency(int frequency) {
		_digestConfiguration.setFrequency(frequency);
	}

	/**
	* Returns the scope group ID of this digest configuration.
	*
	* @return the scope group ID of this digest configuration
	*/
	public long getScopeGroupId() {
		return _digestConfiguration.getScopeGroupId();
	}

	/**
	* Sets the scope group ID of this digest configuration.
	*
	* @param scopeGroupId the scope group ID of this digest configuration
	*/
	public void setScopeGroupId(long scopeGroupId) {
		_digestConfiguration.setScopeGroupId(scopeGroupId);
	}

	/**
	* Returns the scope user ID of this digest configuration.
	*
	* @return the scope user ID of this digest configuration
	*/
	public long getScopeUserId() {
		return _digestConfiguration.getScopeUserId();
	}

	/**
	* Sets the scope user ID of this digest configuration.
	*
	* @param scopeUserId the scope user ID of this digest configuration
	*/
	public void setScopeUserId(long scopeUserId) {
		_digestConfiguration.setScopeUserId(scopeUserId);
	}

	/**
	* Returns the scope user uuid of this digest configuration.
	*
	* @return the scope user uuid of this digest configuration
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getScopeUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _digestConfiguration.getScopeUserUuid();
	}

	/**
	* Sets the scope user uuid of this digest configuration.
	*
	* @param scopeUserUuid the scope user uuid of this digest configuration
	*/
	public void setScopeUserUuid(java.lang.String scopeUserUuid) {
		_digestConfiguration.setScopeUserUuid(scopeUserUuid);
	}

	/**
	* Returns the summary length of this digest configuration.
	*
	* @return the summary length of this digest configuration
	*/
	public int getSummaryLength() {
		return _digestConfiguration.getSummaryLength();
	}

	/**
	* Sets the summary length of this digest configuration.
	*
	* @param summaryLength the summary length of this digest configuration
	*/
	public void setSummaryLength(int summaryLength) {
		_digestConfiguration.setSummaryLength(summaryLength);
	}

	/**
	* Returns the activity types of this digest configuration.
	*
	* @return the activity types of this digest configuration
	*/
	public java.lang.String getActivityTypes() {
		return _digestConfiguration.getActivityTypes();
	}

	/**
	* Sets the activity types of this digest configuration.
	*
	* @param activityTypes the activity types of this digest configuration
	*/
	public void setActivityTypes(java.lang.String activityTypes) {
		_digestConfiguration.setActivityTypes(activityTypes);
	}

	public boolean isNew() {
		return _digestConfiguration.isNew();
	}

	public void setNew(boolean n) {
		_digestConfiguration.setNew(n);
	}

	public boolean isCachedModel() {
		return _digestConfiguration.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_digestConfiguration.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _digestConfiguration.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _digestConfiguration.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_digestConfiguration.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _digestConfiguration.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_digestConfiguration.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DigestConfigurationWrapper((DigestConfiguration)_digestConfiguration.clone());
	}

	public int compareTo(
		com.liferay.portlet.digest.activity.model.DigestConfiguration digestConfiguration) {
		return _digestConfiguration.compareTo(digestConfiguration);
	}

	@Override
	public int hashCode() {
		return _digestConfiguration.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.digest.activity.model.DigestConfiguration> toCacheModel() {
		return _digestConfiguration.toCacheModel();
	}

	public com.liferay.portlet.digest.activity.model.DigestConfiguration toEscapedModel() {
		return new DigestConfigurationWrapper(_digestConfiguration.toEscapedModel());
	}

	public com.liferay.portlet.digest.activity.model.DigestConfiguration toUnescapedModel() {
		return new DigestConfigurationWrapper(_digestConfiguration.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _digestConfiguration.toString();
	}

	public java.lang.String toXmlString() {
		return _digestConfiguration.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_digestConfiguration.persist();
	}

	public boolean isCompanyDigest() {
		return _digestConfiguration.isCompanyDigest();
	}

	public boolean isGroupDigest() {
		return _digestConfiguration.isGroupDigest();
	}

	public boolean isUserDigest() {
		return _digestConfiguration.isUserDigest();
	}

	public java.util.List<java.lang.Long> getActivityTypeIds()
		throws java.lang.Exception {
		return _digestConfiguration.getActivityTypeIds();
	}

	public java.util.List<com.liferay.portlet.digest.activity.DigestActivityType> getActivityTypesList()
		throws java.lang.Exception {
		return _digestConfiguration.getActivityTypesList();
	}

	public java.util.Map<java.lang.String, com.liferay.portlet.digest.activity.DigestActivityType> getActivityTypesMap()
		throws java.lang.Exception {
		return _digestConfiguration.getActivityTypesMap();
	}

	public java.lang.String getFrequencyAsString() throws java.lang.Exception {
		return _digestConfiguration.getFrequencyAsString();
	}

	public java.util.Date getStartDate() {
		return _digestConfiguration.getStartDate();
	}

	public java.util.Date getEndDate() {
		return _digestConfiguration.getEndDate();
	}

	public void setStartDate(java.util.Date startDate) {
		_digestConfiguration.setStartDate(startDate);
	}

	public void setEndDate(java.util.Date endDate) {
		_digestConfiguration.setEndDate(endDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DigestConfigurationWrapper)) {
			return false;
		}

		DigestConfigurationWrapper digestConfigurationWrapper = (DigestConfigurationWrapper)obj;

		if (Validator.equals(_digestConfiguration,
					digestConfigurationWrapper._digestConfiguration)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public DigestConfiguration getWrappedDigestConfiguration() {
		return _digestConfiguration;
	}

	public DigestConfiguration getWrappedModel() {
		return _digestConfiguration;
	}

	public void resetOriginalValues() {
		_digestConfiguration.resetOriginalValues();
	}

	private DigestConfiguration _digestConfiguration;
}