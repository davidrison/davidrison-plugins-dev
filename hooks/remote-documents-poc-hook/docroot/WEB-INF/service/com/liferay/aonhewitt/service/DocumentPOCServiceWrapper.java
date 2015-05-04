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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DocumentPOCService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DocumentPOCService
 * @generated
 */
public class DocumentPOCServiceWrapper implements DocumentPOCService,
	ServiceWrapper<DocumentPOCService> {
	public DocumentPOCServiceWrapper(DocumentPOCService documentPOCService) {
		_documentPOCService = documentPOCService;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DocumentPOCService getWrappedDocumentPOCService() {
		return _documentPOCService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDocumentPOCService(
		DocumentPOCService documentPOCService) {
		_documentPOCService = documentPOCService;
	}

	public DocumentPOCService getWrappedService() {
		return _documentPOCService;
	}

	public void setWrappedService(DocumentPOCService documentPOCService) {
		_documentPOCService = documentPOCService;
	}

	private DocumentPOCService _documentPOCService;
}