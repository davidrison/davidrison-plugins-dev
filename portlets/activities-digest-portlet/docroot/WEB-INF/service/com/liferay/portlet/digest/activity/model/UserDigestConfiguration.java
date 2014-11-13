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
 * The extended model interface for the UserDigestConfiguration service. Represents a row in the &quot;Digest_UserDigestConfiguration&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see UserDigestConfigurationModel
 * @see com.liferay.portlet.digest.activity.model.impl.UserDigestConfigurationImpl
 * @see com.liferay.portlet.digest.activity.model.impl.UserDigestConfigurationModelImpl
 * @generated
 */
public interface UserDigestConfiguration extends UserDigestConfigurationModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.digest.activity.model.impl.UserDigestConfigurationImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.lang.String getFrequencyAsString() throws java.lang.Exception;
}