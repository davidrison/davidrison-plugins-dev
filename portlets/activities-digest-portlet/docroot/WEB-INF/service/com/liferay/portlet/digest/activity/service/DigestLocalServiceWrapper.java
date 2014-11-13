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

package com.liferay.portlet.digest.activity.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DigestLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DigestLocalService
 * @generated
 */
public class DigestLocalServiceWrapper implements DigestLocalService,
	ServiceWrapper<DigestLocalService> {
	public DigestLocalServiceWrapper(DigestLocalService digestLocalService) {
		_digestLocalService = digestLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _digestLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_digestLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _digestLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	public void processInactiveUsers(long companyId, int frequency)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_digestLocalService.processInactiveUsers(companyId, frequency);
	}

	public void processUsers(long companyId, int frequency)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_digestLocalService.processUsers(companyId, frequency);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DigestLocalService getWrappedDigestLocalService() {
		return _digestLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDigestLocalService(
		DigestLocalService digestLocalService) {
		_digestLocalService = digestLocalService;
	}

	public DigestLocalService getWrappedService() {
		return _digestLocalService;
	}

	public void setWrappedService(DigestLocalService digestLocalService) {
		_digestLocalService = digestLocalService;
	}

	private DigestLocalService _digestLocalService;
}