package com.aonhewitt.portlet.documentlibrary.service.impl;

import com.aonhewitt.portal.core.util.RemotingConstants;
import com.aonhewitt.portal.core.util.RemotingUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.DLFileVersionService;
import com.liferay.portlet.documentlibrary.service.DLFileVersionServiceWrapper;

public class DLFileVersionServiceImpl extends DLFileVersionServiceWrapper {

	public DLFileVersionServiceImpl(DLFileVersionService dlFileVersionService) {
		super(dlFileVersionService);
	}

	@Override
	public DLFileVersion getFileVersion(long fileVersionId) throws PortalException, SystemException {
		_log.info("DLFileVersionServiceImpl:getFileVersion");

		if (RemotingConstants.REMOTE_HOST_ENABLED) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLFileVersionServiceHttp",
						"getFileVersion",
						new Object[]{
								fileVersionId
						},
						new Class[]{
								long.class
						});

				return (DLFileVersion) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getFileVersion(fileVersionId);
		}
	}

	@Override
	public DLFileVersion getLatestFileVersion(long fileEntryId) throws PortalException, SystemException {
		_log.info("DLFileVersionServiceImpl:getLatestFileVersion");

		if (RemotingConstants.REMOTE_HOST_ENABLED) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLFileVersionServiceHttp",
						"getLatestFileVersion",
						new Object[]{
								fileEntryId
						},
						new Class[]{
								long.class
						});

				return (DLFileVersion) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getLatestFileVersion(fileEntryId);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(DLFileVersionServiceImpl.class);

}
