package com.aonhewitt.portlet.documentlibrary.service.impl;

import com.aonhewitt.portal.core.util.RemoteDocumentThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceWrapper;

import java.io.Serializable;
import java.util.Map;

public class DLFileEntryLocalServiceImpl extends DLFileEntryLocalServiceWrapper {

	public DLFileEntryLocalServiceImpl(DLFileEntryLocalService dlFileEntryLocalService) {
		super(dlFileEntryLocalService);
	}

	@Override
	public DLFileEntry updateStatus(long userId, long fileVersionId, int status, Map<String, Serializable> workflowContext, ServiceContext serviceContext) throws PortalException, SystemException {

		if (_log.isDebugEnabled()) {
			_log.debug("DLFileEntryLocalServiceImpl:: updateStatus");
		}

		if (RemoteDocumentThreadLocal.isRemote()) {
			_log.info("DLFileEntryLocalServiceImpl:: updateStatus - Invoke Remote ");
			return super.updateStatus(userId, fileVersionId, status, workflowContext, serviceContext);
		}
		else {
			return super.updateStatus(userId, fileVersionId, status, workflowContext, serviceContext);
		}
	}


	private static Log _log = LogFactoryUtil.getLog(DLFileEntryLocalServiceImpl.class);

}
