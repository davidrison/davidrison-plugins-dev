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

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the DigestConfiguration service. Represents a row in the &quot;Digest_DigestConfiguration&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DigestConfigurationModel
 * @see com.liferay.portlet.digest.activity.model.impl.DigestConfigurationImpl
 * @see com.liferay.portlet.digest.activity.model.impl.DigestConfigurationModelImpl
 * @generated
 */
public interface DigestConfiguration extends DigestConfigurationModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.digest.activity.model.impl.DigestConfigurationImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public boolean isCompanyDigest();

	public boolean isGroupDigest();

	public boolean isUserDigest();

	public java.util.List<java.lang.Long> getActivityTypeIds()
		throws java.lang.Exception;

	public java.util.List<com.liferay.portlet.digest.activity.DigestActivityType> getActivityTypesList()
		throws java.lang.Exception;

	public java.util.Map<java.lang.String, com.liferay.portlet.digest.activity.DigestActivityType> getActivityTypesMap()
		throws java.lang.Exception;

	public java.lang.String getFrequencyAsString() throws java.lang.Exception;

	public java.util.Date getStartDate();

	public java.util.Date getEndDate();

	public void setStartDate(java.util.Date startDate);

	public void setEndDate(java.util.Date endDate);

	public java.lang.Object clone();
}