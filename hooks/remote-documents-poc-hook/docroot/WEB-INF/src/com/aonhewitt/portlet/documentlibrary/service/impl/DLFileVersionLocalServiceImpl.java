package com.aonhewitt.portlet.documentlibrary.service.impl;

import com.aonhewitt.portal.core.util.RemotingConstants;
import com.aonhewitt.portal.core.util.RemotingUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceWrapper;

public class DLFileVersionLocalServiceImpl extends DLFileVersionLocalServiceWrapper {

	public DLFileVersionLocalServiceImpl(DLFileVersionLocalService dlFileVersionLocalService) {
		super(dlFileVersionLocalService);
	}

	@Override
	public DLFileVersion getFileVersion(long fileVersionId) throws PortalException, SystemException {

		_log.info("DLFileVersionLocalServiceImpl:getFileVersion");

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

	/** TODO THIS MAY NEED TO BE ADDED TO THE CORE SERVICE VIA AN EXT PLUGIN **/
	@Override
	public DLFileVersion getFileVersion(long fileEntryId, String version) throws PortalException, SystemException {

		_log.info("DLFileVersionLocalServiceImpl:getFileVersion");

		if (RemotingConstants.REMOTE_HOST_ENABLED) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLFileVersionServiceHttp",
						"getLatestFileVersion", // TODO this is a workaround because this doesn't exist in the remote http class
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
			return super.getFileVersion(fileEntryId, version);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(DLFileVersionLocalServiceImpl.class);

}
