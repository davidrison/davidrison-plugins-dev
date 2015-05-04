/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.aonhewitt.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the document p o c remote service. This utility wraps {@link com.liferay.aonhewitt.service.impl.DocumentPOCServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DocumentPOCService
 * @see com.liferay.aonhewitt.service.base.DocumentPOCServiceBaseImpl
 * @see com.liferay.aonhewitt.service.impl.DocumentPOCServiceImpl
 * @generated
 */
public class DocumentPOCServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.aonhewitt.service.impl.DocumentPOCServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static void clearService() {
		_service = null;
	}

	public static DocumentPOCService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					DocumentPOCService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					DocumentPOCService.class.getName(), portletClassLoader);

			_service = new DocumentPOCServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(DocumentPOCServiceUtil.class,
				"_service");
			MethodCache.remove(DocumentPOCService.class);
		}

		return _service;
	}

	public void setService(DocumentPOCService service) {
		MethodCache.remove(DocumentPOCService.class);

		_service = service;

		ReferenceRegistry.registerReference(DocumentPOCServiceUtil.class,
			"_service");
		MethodCache.remove(DocumentPOCService.class);
	}

	private static DocumentPOCService _service;
}