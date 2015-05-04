package com.aonhewitt.portlet.documentlibrary.service.impl;

import com.aonhewitt.portal.core.cache.DocumentEntityCacheUtil;
import com.aonhewitt.portal.core.cache.DocumentFinderCacheUtil;
import com.aonhewitt.portal.core.util.CacheConstants;
import com.aonhewitt.portal.core.util.RemoteDocumentThreadLocal;
import com.aonhewitt.portal.core.util.RemotingConstants;
import com.aonhewitt.portal.core.util.RemotingUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Lock;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.service.DLAppService;
import com.liferay.portlet.documentlibrary.service.DLAppServiceWrapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DLAppServiceImpl extends DLAppServiceWrapper {

	public DLAppServiceImpl(DLAppService dlAppService) {
		super(dlAppService);
	}

	@Override
	public FileEntry addFileEntry(long repositoryId, long folderId, String sourceFileName, String mimeType, String title, String description, String changeLog, byte[] bytes, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:addFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"addFileEntry",
						new Object[]{
								repositoryId,
								folderId,
								sourceFileName,
								mimeType,
								title,
								description,changeLog,bytes,serviceContext
						},
						new Class[] {long.class, long.class, java.lang.String.class,
                            java.lang.String.class, java.lang.String.class,
                            java.lang.String.class, java.lang.String.class, byte[].class,
                            com.liferay.portal.service.ServiceContext.class
                        });

				return (FileEntry) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.addFileEntry(repositoryId, folderId, sourceFileName, mimeType, title, description, changeLog, bytes, serviceContext);
		}
	}

	@Override
	public FileEntry addFileEntry(long repositoryId, long folderId, String sourceFileName, String mimeType, String title, String description, String changeLog, File file, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:addFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"addFileEntry",
						new Object[]{
								repositoryId,
								folderId,
								sourceFileName,
								mimeType,
								title,
								description,
								changeLog,
								file,
								serviceContext
						},
						new Class[] {
								long.class,
								long.class,
								java.lang.String.class,
								java.lang.String.class,
								java.lang.String.class,
								java.lang.String.class,
								java.lang.String.class,
								java.io.File.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (FileEntry) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.addFileEntry(repositoryId, folderId, sourceFileName, mimeType, title, description, changeLog, file, serviceContext);
		}
	}

	@Override
	public FileEntry addFileEntry(long repositoryId, long folderId, String sourceFileName, String mimeType, String title, String description, String changeLog, InputStream is, long size, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:addFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				return this.addFileEntry(
						repositoryId, folderId, sourceFileName, mimeType,
						title, description, changeLog, FileUtil.getBytes(is, (int) size), serviceContext);

				/** THIS DOES NOT WORK BECAUSE ByteArrayInputStream is not a serializable object **
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"addFileEntry",
						new Object[]{
								repositoryId,
								folderId,
								sourceFileName,
								mimeType,
								title,
								description,
								changeLog,
								is,
								size,
								serviceContext
						},
						new Class[]{
								long.class,
								long.class,
								java.lang.String.class,
								java.lang.String.class,
								java.lang.String.class,
								java.lang.String.class,
								java.lang.String.class,
								java.io.InputStream.class,
								long.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (FileEntry) retval;
			 	**/
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.addFileEntry(repositoryId, folderId, sourceFileName, mimeType, title, description, changeLog, is, size, serviceContext);
		}
	}

	@Override
	public DLFileShortcut addFileShortcut(long repositoryId, long folderId, long toFileEntryId, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:addFileShortcut");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"addFileShortcut",
						new Object[]{
								repositoryId,
								folderId,
								toFileEntryId,
								serviceContext
						},
						new Class[] {
								long.class,
								long.class,
								long.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (DLFileShortcut) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.addFileShortcut(repositoryId, folderId, toFileEntryId, serviceContext);
		}
	}

	@Override
	public Folder addFolder(long repositoryId, long parentFolderId, String name, String description, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:addFolder");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"addFolder",
						new Object[]{
								repositoryId,
								parentFolderId,
								name,
								description,
								serviceContext
						},
						new Class[] {
								long.class,
								long.class,
								String.class,
								String.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (Folder) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.addFolder(repositoryId, parentFolderId, name, description, serviceContext);
		}
	}

	@Override
	public String addTempFileEntry(long groupId, long folderId, String fileName, String tempFolderName, File file) throws PortalException, SystemException, IOException {
		_log.info("DLAppServiceImpl:addTempFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"addTempFileEntry",
						new Object[]{
								groupId,
								folderId,
								fileName,
								tempFolderName,
								file
						},
						new Class[] {
								long.class,
								long.class,
								String.class,
								String.class,
								java.io.File.class
						});

				return (String) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.addTempFileEntry(groupId, folderId, fileName, tempFolderName, file);
		}
	}

	@Override
	public String addTempFileEntry(long groupId, long folderId, String fileName, String tempFolderName, InputStream inputStream) throws PortalException, SystemException, IOException {
		_log.info("DLAppServiceImpl:addTempFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				return this.addTempFileEntry(
						groupId, folderId, fileName, tempFolderName, FileUtil.createTempFile(inputStream));

				/** THIS DOES NOT WORK BECAUSE ByteArrayInputStream is not a serializable object **
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"addTempFileEntry",
						new Object[]{
								groupId,
								folderId,
								fileName,
								tempFolderName,
								inputStream
						},
						new Class[]{
								long.class,
								long.class,
								String.class,
								String.class,
								java.io.InputStream.class
						});

				return (String) retval;
			 	*/
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.addTempFileEntry(groupId, folderId, fileName, tempFolderName, inputStream);
		}
	}

	@Override
	public void cancelCheckOut(long fileEntryId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:cancelCheckOut");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"cancelCheckOut",
						new Object[]{
								fileEntryId
						},
						new Class[] {
								long.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.cancelCheckOut(fileEntryId);
		}
	}

	@Override
	public void checkInFileEntry(long fileEntryId, boolean majorVersion, String changeLog, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:checkInFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"checkInFileEntry",
						new Object[]{
								fileEntryId,
								majorVersion,
								changeLog,
								serviceContext
						},
						new Class[] {
								long.class,
								boolean.class,
								String.class,
								ServiceContext.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.checkInFileEntry(fileEntryId, majorVersion, changeLog, serviceContext);
		}
	}

	@Override
	public void checkInFileEntry(long fileEntryId, String lockUuid) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:checkInFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"checkInFileEntry",
						new Object[]{
								fileEntryId,
								lockUuid
						},
						new Class[] {
								long.class,
								String.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.checkInFileEntry(fileEntryId, lockUuid);
		}
	}

	@Override
	public void checkOutFileEntry(long fileEntryId, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:checkOutFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"checkOutFileEntry",
						new Object[]{
								fileEntryId,
								serviceContext
						},
						new Class[] {
								long.class,
								ServiceContext.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.checkOutFileEntry(fileEntryId, serviceContext);
		}
	}

	@Override
	public FileEntry checkOutFileEntry(long fileEntryId, String owner, long expirationTime, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:checkOutFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"checkOutFileEntry",
						new Object[]{
								fileEntryId,
								owner,
								expirationTime,
								serviceContext
						},
						new Class[] {
								long.class,
								String.class,
								long.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (FileEntry) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.checkOutFileEntry(fileEntryId, owner, expirationTime, serviceContext);
		}
	}

	@Override
	public Folder copyFolder(long repositoryId, long sourceFolderId, long parentFolderId, String name, String description, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:copyFolder");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"copyFolder",
						new Object[]{
								repositoryId,
								sourceFolderId,
								parentFolderId,
								name,
								description,
								serviceContext
						},
						new Class[] {
								long.class,
								long.class,
								long.class,
								String.class,
								String.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (Folder) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.copyFolder(repositoryId, sourceFolderId, parentFolderId, name, description, serviceContext);
		}
	}

	@Override
	public void deleteFileEntry(long fileEntryId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:deleteFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"deleteFileEntry",
						new Object[]{
								fileEntryId
						},
						new Class[] {
								long.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.deleteFileEntry(fileEntryId);
		}
	}

	@Override
	public void deleteFileEntryByTitle(long repositoryId, long folderId, String title) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:deleteFileEntryByTitle");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"deleteFileEntryByTitle",
						new Object[]{
								repositoryId,
								folderId,
								title
						},
						new Class[] {
								long.class,
								long.class,
								java.lang.String.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.deleteFileEntryByTitle(repositoryId, folderId, title);
		}
	}

	@Override
	public void deleteFileShortcut(long fileShortcutId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:deleteFileShortcut");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"deleteFileShortcut",
						new Object[]{
								fileShortcutId
						},
						new Class[] {
								long.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.deleteFileShortcut(fileShortcutId);
		}
	}

	@Override
	public void deleteFolder(long folderId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:deleteFolder");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"deleteFolder",
						new Object[]{
								folderId
						},
						new Class[]{
								long.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.deleteFolder(folderId);
		}
	}

	@Override
	public void deleteFolder(long repositoryId, long parentFolderId, String name) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:deleteFolder");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"deleteFolder",
						new Object[]{
								repositoryId,
								parentFolderId,
								name
						},
						new Class[] {
								long.class,
								long.class,
								java.lang.String.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.deleteFolder(repositoryId, parentFolderId, name);
		}
	}

	@Override
	public void deleteTempFileEntry(long groupId, long folderId, String fileName, String tempFolderName) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:deleteTempFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"deleteTempFileEntry",
						new Object[]{
								groupId,
								folderId,
								fileName,
								tempFolderName
						},
						new Class[] {
								long.class,
								long.class,
								String.class,
								String.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.deleteTempFileEntry(groupId, folderId, fileName, tempFolderName);
		}
	}

	@Override
	public List<FileEntry> getFileEntries(long repositoryId, long folderId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntries");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			Object[] finderArgs = new Object[]{repositoryId, folderId};

			List<FileEntry> fileEntries = (List<FileEntry>) DocumentFinderCacheUtil.getResult(
					FINDER_PATH_GET_FILES_BY_R_F, finderArgs);

			if (fileEntries == null || fileEntries.size() == 0) {
				try {
					Object retval = RemotingUtil.invoke(
							PropsUtil.get("poc.remote.host.name"),
							GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
							false,
							"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
							"getFileEntries",
							new Object[]{
									repositoryId,
									folderId
							},
							new Class[]{
									long.class,
									long.class
							});

					fileEntries = (List<FileEntry>) retval;
				} catch (Throwable t) {
					t.printStackTrace();

					throw new PortalException(t);
				} finally {
					if (fileEntries != null && fileEntries.size() > 0) {
						cacheResult(fileEntries);

						DocumentFinderCacheUtil.putResult(FINDER_PATH_GET_FILES_BY_R_F, finderArgs, fileEntries);
					}
				}
			}

			return fileEntries;
		}else
		{
			return super.getFileEntries(repositoryId, folderId);
		}
	}

	@Override
	public List<FileEntry> getFileEntries(long repositoryId, long folderId, int start, int end) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntries");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			Object[] finderArgs = new Object[]{repositoryId, folderId, start, end};

			List<FileEntry> fileEntries = (List<FileEntry>) DocumentFinderCacheUtil.getResult(
					FINDER_PATH_GET_FILES_BY_R_F_S_E, finderArgs);

			if (fileEntries == null || fileEntries.size() == 0) {
				try {
					Object retval = RemotingUtil.invoke(
							PropsUtil.get("poc.remote.host.name"),
							GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
							false,
							"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
							"getFileEntries",
							new Object[]{
									repositoryId,
									folderId,
									start,
									end
							},
							new Class[]{
									long.class,
									long.class,
									int.class,
									int.class
							});

					fileEntries = (List<FileEntry>) retval;
				} catch (Throwable t) {
					t.printStackTrace();

					throw new PortalException(t);
				} finally {
					if (fileEntries != null && fileEntries.size() > 0) {
						cacheResult(fileEntries);

						DocumentFinderCacheUtil.putResult(FINDER_PATH_GET_FILES_BY_R_F_S_E, finderArgs, fileEntries);
					}
				}
			}

			return fileEntries;
		}else
		{
			return super.getFileEntries(repositoryId, folderId, start, end);
		}
	}

	@Override
	public List<FileEntry> getFileEntries(long repositoryId, long folderId, int start, int end, OrderByComparator obc) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntries");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			Object[] finderArgs = new Object[]{repositoryId, folderId, start, end, obc};

			List<FileEntry> fileEntries = (List<FileEntry>) DocumentFinderCacheUtil.getResult(
					FINDER_PATH_GET_FILES_BY_R_F_S_E_OBC, finderArgs);

			if (fileEntries == null || fileEntries.size() == 0) {
				try {
					Object retval = RemotingUtil.invoke(
							PropsUtil.get("poc.remote.host.name"),
							GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
							false,
							"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
							"getFileEntries",
							new Object[]{
									repositoryId,
									folderId,
									start,
									end,
									obc
							},
							new Class[]{
									long.class,
									long.class,
									int.class,
									int.class,
									com.liferay.portal.kernel.util.OrderByComparator.class
							});

					fileEntries = (List<FileEntry>) retval;
				} catch (Throwable t) {
					t.printStackTrace();

					throw new PortalException(t);
				} finally {
					if (fileEntries != null && fileEntries.size() > 0) {
						cacheResult(fileEntries);

						DocumentFinderCacheUtil.putResult(FINDER_PATH_GET_FILES_BY_R_F_S_E_OBC, finderArgs, fileEntries);
					}
				}
			}

			return fileEntries;
		}else
		{
			return super.getFileEntries(repositoryId, folderId, start, end, obc);
		}
	}

	@Override
	public List<FileEntry> getFileEntries(long repositoryId, long folderId, long fileEntryTypeId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntries");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFileEntries",
						new Object[]{
								repositoryId,
								folderId,
								fileEntryTypeId
						},
						new Class[] {
								long.class,
								long.class,
								long.class
						});

				return (List<FileEntry>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getFileEntries(repositoryId, folderId, fileEntryTypeId);
		}
	}

	@Override
	public List<FileEntry> getFileEntries(long repositoryId, long folderId, long fileEntryTypeId, int start, int end) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntries");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFileEntries",
						new Object[]{
								repositoryId,
								folderId,
								fileEntryTypeId,
								start,
								end
						},
						new Class[] {
								long.class,
								long.class,
								long.class,
								int.class,
								int.class
						});

				return (List<FileEntry>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getFileEntries(repositoryId, folderId, fileEntryTypeId, start, end);
		}
	}

	@Override
	public List<FileEntry> getFileEntries(long repositoryId, long folderId, long fileEntryTypeId, int start, int end, OrderByComparator obc) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntries");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFileEntries",
						new Object[]{
								repositoryId,
								folderId,
								fileEntryTypeId,
								start,
								end,
								obc
						},
						new Class[] {
								long.class,
								long.class,
								long.class,
								int.class,
								int.class,
								com.liferay.portal.kernel.util.OrderByComparator.class
						});

				return (List<FileEntry>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getFileEntries(repositoryId, folderId, fileEntryTypeId, start, end, obc);
		}
	}

	@Override
	public List<Object> getFileEntriesAndFileShortcuts(long repositoryId, long folderId, int status, int start, int end) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntriesAndFileShortcuts");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			Object[] finderArgs = new Object[]{repositoryId, folderId, status, start, end};

			List<Object> fileEntriesAndFileShortcuts = (List<Object>) DocumentFinderCacheUtil.getResult(
					FINDER_PATH_GET_FILES_SHORTCUTS_R_F_S_S_E, finderArgs);

			if (fileEntriesAndFileShortcuts == null || fileEntriesAndFileShortcuts.size() == 0) {
				try {
					Object retval = RemotingUtil.invoke(
							PropsUtil.get("poc.remote.host.name"),
							GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
							false,
							"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
							"getFileEntriesAndFileShortcuts",
							new Object[]{
									repositoryId,
									folderId,
									status,
									start,
									end
							},
							new Class[]{
									long.class,
									long.class,
									int.class,
									int.class,
									int.class
							});

					fileEntriesAndFileShortcuts = (List<Object>) retval;
				} catch (Throwable t) {
					t.printStackTrace();

					throw new PortalException(t);
				}
				finally {
					if (fileEntriesAndFileShortcuts != null && fileEntriesAndFileShortcuts.size() > 0) {
						cacheResult(fileEntriesAndFileShortcuts);

						DocumentFinderCacheUtil.putResult(FINDER_PATH_GET_FILES_SHORTCUTS_R_F_S_S_E, finderArgs, fileEntriesAndFileShortcuts);
					}
				}
			}

			return fileEntriesAndFileShortcuts;
		} else {
			return super.getFileEntriesAndFileShortcuts(repositoryId, folderId, status, start, end);
		}
	}

	@Override
	public int getFileEntriesAndFileShortcutsCount(long repositoryId, long folderId, int status) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntriesAndFileShortcutsCount");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object count = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFileEntriesAndFileShortcutsCount",
						new Object[]{
								repositoryId,
								folderId,
								status
						},
						new Class[]{
								long.class,
								long.class,
								int.class
						});

				return ((Integer) count).intValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		} else {
			return super.getFileEntriesAndFileShortcutsCount(repositoryId, folderId, status);
		}
	}

	@Override
	public int getFileEntriesAndFileShortcutsCount(long repositoryId, long folderId, int status, String[] mimeTypes) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntriesAndFileShortcutsCount");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object count = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFileEntriesAndFileShortcutsCount",
						new Object[]{
								repositoryId,
								folderId,
								status,
								mimeTypes
						},
						new Class[]{
								long.class,
								long.class,
								int.class,
								String[].class
						});

				return ((Integer) count).intValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		} else {
			return super.getFileEntriesAndFileShortcutsCount(repositoryId, folderId, status, mimeTypes);
		}
	}

	@Override
	public int getFileEntriesCount(long repositoryId, long folderId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntriesCount");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFileEntriesCount",
						new Object[]{
								repositoryId,
								folderId
						},
						new Class[] {
								long.class,
								long.class
						});

				return ((Integer) retval).intValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getFileEntriesCount(repositoryId, folderId);
		}
	}

	@Override
	public int getFileEntriesCount(long repositoryId, long folderId, long fileEntryTypeId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntriesCount");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFileEntriesCount",
						new Object[]{
								repositoryId,
								folderId,
								fileEntryTypeId
						},
						new Class[] {
								long.class,
								long.class,
								long.class
						});

				return ((Integer) retval).intValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getFileEntriesCount(repositoryId, folderId, fileEntryTypeId);
		}
	}

	@Override
	public FileEntry getFileEntry(long fileEntryId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			FileEntry fileEntry = (FileEntry) DocumentEntityCacheUtil.getResult(
					CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED, FileEntry.class, fileEntryId
			);

			if (fileEntry == null) {
				try {
					Object retval = RemotingUtil.invoke(
							PropsUtil.get("poc.remote.host.name"),
							GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
							false,
							"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
							"getFileEntry",
							new Object[]{
									fileEntryId
							},
							new Class[]{
									long.class
							});

					fileEntry = (FileEntry) retval;
				} catch (Throwable t) {
					t.printStackTrace();

					throw new PortalException(t);
				}
				finally {
					if (fileEntry != null) {
						cacheResult(fileEntry);
					}
				}
			}

			return fileEntry;
		} else {
			return super.getFileEntry(fileEntryId);
		}
	}

	@Override
	public FileEntry getFileEntry(long groupId, long folderId, String title) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFileEntry",
						new Object[]{
								groupId,
								folderId,
								title
						},
						new Class[]{
								long.class,
								long.class,
								String.class
						});

				return (FileEntry) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		} else {
			return super.getFileEntry(groupId, folderId, title);
		}
	}

	@Override
	public FileEntry getFileEntryByUuidAndGroupId(String uuid, long groupId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileEntryByUuidAndGroupId");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFileEntryByUuidAndGroupId",
						new Object[]{
								uuid,
								groupId
						},
						new Class[]{
								String.class,
								long.class
						});

				return (FileEntry) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		} else {
			return super.getFileEntryByUuidAndGroupId(uuid, groupId);
		}
	}

	@Override
	public DLFileShortcut getFileShortcut(long fileShortcutId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFileShortcut");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFileShortcut",
						new Object[]{
								fileShortcutId
						},
						new Class[] {
								long.class
						});

				return (DLFileShortcut) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getFileShortcut(fileShortcutId);
		}
	}

	@Override
	public Folder getFolder(long folderId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFolder");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			Folder folder = (Folder) DocumentEntityCacheUtil.getResult(
					CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED, Folder.class, folderId
			);

			if (folder == null) {
				try {
					Object retval = RemotingUtil.invoke(
							PropsUtil.get("poc.remote.host.name"),
							GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
							false,
							"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
							"getFolder",
							new Object[]{
									folderId
							},
							new Class[]{
									long.class
							});

					folder = (Folder) retval;
				} catch (Throwable t) {
					t.printStackTrace();

					throw new PortalException(t);
				}
				finally {
					if (folder != null) {
						cacheResult(folder);
					}
				}
			}

			return folder;
		} else {
			return super.getFolder(folderId);
		}
	}

	@Override
	public Folder getFolder(long repositoryId, long parentFolderId, String name) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFolder");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFolder",
						new Object[]{
								repositoryId,
								parentFolderId,
								name
						},
						new Class[]{
								long.class,
								long.class,
								String.class
						});

				return (Folder) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		} else {
			return super.getFolder(repositoryId, parentFolderId, name);
		}
	}

	@Override
	public List<Folder> getFolders(long repositoryId, long parentFolderId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFolders");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			Object[] finderArgs = new Object[]{repositoryId, parentFolderId};

			List<Folder> folders = (List<Folder>) DocumentFinderCacheUtil.getResult(
					FINDER_PATH_GET_FOLDERS_BY_R_P, finderArgs);

			if (folders == null || folders.size() == 0) {
				try {
					Object retval = RemotingUtil.invoke(
							PropsUtil.get("poc.remote.host.name"),
							GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
							false,
							"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
							"getFolders",
							new Object[]{
									repositoryId,
									parentFolderId
							},
							new Class[]{
									long.class,
									long.class
							});

					folders = (List<Folder>) retval;
				} catch (Throwable t) {
					t.printStackTrace();

					throw new PortalException(t);
				} finally {
					if (folders != null && folders.size() > 0) {
						cacheResult(folders);

						DocumentFinderCacheUtil.putResult(FINDER_PATH_GET_FOLDERS_BY_R_P, finderArgs, folders);
					}
				}
			}

			return folders;
		} else {
			return super.getFolders(repositoryId, parentFolderId);
		}
	}

	@Override
	public List<Folder> getFolders(long repositoryId, long parentFolderId, boolean includeMountFolders) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFolders");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFolders",
						new Object[]{
								repositoryId,
								parentFolderId,
								includeMountFolders
						},
						new Class[]{
								long.class,
								long.class,
								boolean.class
						});

				return (List<Folder>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		} else {
			return super.getFolders(repositoryId, parentFolderId, includeMountFolders);
		}
	}

	@Override
	public List<Folder> getFolders(long repositoryId, long parentFolderId, boolean includeMountFolders, int start, int end) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFolders");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFolders",
						new Object[]{
								repositoryId,
								parentFolderId,
								includeMountFolders,
								start,
								end
						},
						new Class[]{
								long.class,
								long.class,
								boolean.class,
								int.class,
								int.class
						});

				return (List<Folder>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		} else {
			return super.getFolders(repositoryId, parentFolderId, includeMountFolders, start, end);
		}
	}

	@Override
	public List<Folder> getFolders(long repositoryId, long parentFolderId, boolean includeMountFolders, int start, int end, OrderByComparator obc) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFolders");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {

			Object[] finderArgs = new Object[]{repositoryId, parentFolderId, includeMountFolders, start, end, obc};

			List<Folder> folders = (List<Folder>) DocumentFinderCacheUtil.getResult(
					FINDER_PATH_GET_FOLDERS_BY_R_P_M_S_E_OBC, finderArgs);

			if (folders == null || folders.size() == 0) {

				try {
					Object retval = RemotingUtil.invoke(
							PropsUtil.get("poc.remote.host.name"),
							GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
							false,
							"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
							"getFolders",
							new Object[]{
									repositoryId,
									parentFolderId,
									includeMountFolders,
									start,
									end,
									obc
							},
							new Class[]{
									long.class,
									long.class,
									boolean.class,
									int.class,
									int.class,
									OrderByComparator.class
							});

					folders = (List<Folder>) retval;
				} catch (Throwable t) {
					t.printStackTrace();

					throw new PortalException(t);
				} finally {
					if (folders != null && folders.size() > 0) {
						cacheResult(folders);

						DocumentFinderCacheUtil.putResult(FINDER_PATH_GET_FOLDERS_BY_R_P_M_S_E_OBC, finderArgs, folders);
					}
				}
			}

			return folders;
		} else {
			return super.getFolders(repositoryId, parentFolderId, includeMountFolders, start, end, obc);
		}
	}

	@Override
	public List<Folder> getFolders(long repositoryId, long parentFolderId, int start, int end) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFolders");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			Object[] finderArgs = new Object[]{repositoryId, parentFolderId, start, end};

			List<Folder> folders = (List<Folder>) DocumentFinderCacheUtil.getResult(
					FINDER_PATH_GET_FOLDERS_BY_R_P_S_E, finderArgs);

			if (folders == null || folders.size() == 0) {
				try {
					Object retval = RemotingUtil.invoke(
							PropsUtil.get("poc.remote.host.name"),
							GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
							false,
							"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
							"getFolders",
							new Object[]{
									repositoryId,
									parentFolderId,
									start,
									end
							},
							new Class[]{
									long.class,
									long.class,
									int.class,
									int.class
							});

					folders = (List<Folder>) retval;
				} catch (Throwable t) {
					t.printStackTrace();

					throw new PortalException(t);
				} finally {
					if (folders != null && folders.size() > 0) {
						cacheResult(folders);

						DocumentFinderCacheUtil.putResult(FINDER_PATH_GET_FOLDERS_BY_R_P_S_E, finderArgs, folders);
					}
				}
			}

			return folders;
		} else {
			return super.getFolders(repositoryId, parentFolderId, start, end);
		}
	}

	@Override
	public List<Folder> getFolders(long repositoryId, long parentFolderId, int start, int end, OrderByComparator obc) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFolders");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			Object[] finderArgs = new Object[]{repositoryId, parentFolderId, start, end};

			List<Folder> folders = (List<Folder>) DocumentFinderCacheUtil.getResult(
					FINDER_PATH_GET_FOLDERS_BY_R_P_S_E_OBC, finderArgs);

			if (folders == null || folders.size() == 0) {
				try {
					Object retval = RemotingUtil.invoke(
							PropsUtil.get("poc.remote.host.name"),
							GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
							false,
							"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
							"getFolders",
							new Object[]{
									repositoryId,
									parentFolderId,
									start,
									end,
									obc
							},
							new Class[]{
									long.class,
									long.class,
									int.class,
									int.class,
									OrderByComparator.class
							});

					folders = (List<Folder>) retval;
				} catch (Throwable t) {
					t.printStackTrace();

					throw new PortalException(t);
				} finally {
					if (folders != null) {
						cacheResult(folders);

						DocumentFinderCacheUtil.putResult(FINDER_PATH_GET_FOLDERS_BY_R_P_S_E_OBC, finderArgs, folders);
					}
				}
			}

			return folders;
		} else {
			return super.getFolders(repositoryId, parentFolderId, start, end, obc);
		}
	}

	@Override
	public List<Object> getFoldersAndFileEntriesAndFileShortcuts(long repositoryId, long folderId, int status, boolean includeMountFolders, int start, int end) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFoldersAndFileEntriesAndFileShortcuts");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFoldersAndFileEntriesAndFileShortcuts",
						new Object[]{
								repositoryId,
								folderId,
								status,
								includeMountFolders,
								start,
								end
						},
						new Class[] {
								long.class,
								long.class,
								int.class,
								boolean.class,
								int.class,
								int.class
						});

				return (List<Object>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getFoldersAndFileEntriesAndFileShortcuts(repositoryId, folderId, status, includeMountFolders, start, end);
		}
	}

	@Override
	public List<Object> getFoldersAndFileEntriesAndFileShortcuts(long repositoryId, long folderId, int status, boolean includeMountFolders, int start, int end, OrderByComparator obc) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFoldersAndFileEntriesAndFileShortcuts");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFoldersAndFileEntriesAndFileShortcuts",
						new Object[]{
								repositoryId,
								folderId,
								status,
								includeMountFolders,
								start,
								end,
								obc
						},
						new Class[] {
								long.class,
								long.class,
								int.class,
								boolean.class,
								int.class,
								int.class,
								com.liferay.portal.kernel.util.OrderByComparator.class
						});

				return (List<Object>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getFoldersAndFileEntriesAndFileShortcuts(repositoryId, folderId, status, includeMountFolders, start, end, obc);
		}
	}

	@Override
	public List<Object> getFoldersAndFileEntriesAndFileShortcuts(long repositoryId, long folderId, int status, String[] mimeTypes, boolean includeMountFolders, int start, int end, OrderByComparator obc) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFoldersAndFileEntriesAndFileShortcuts");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFoldersAndFileEntriesAndFileShortcuts",
						new Object[]{
								repositoryId,
								folderId,
								status,
								mimeTypes,
								includeMountFolders,
								start,
								end,
								obc
						},
						new Class[] {
								long.class,
								long.class,
								int.class,
								String[].class,
								boolean.class,
								int.class,
								int.class,
								com.liferay.portal.kernel.util.OrderByComparator.class
						});

				return (List<Object>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getFoldersAndFileEntriesAndFileShortcuts(repositoryId, folderId, status, mimeTypes, includeMountFolders, start, end, obc);
		}
	}

	@Override
	public int getFoldersAndFileEntriesAndFileShortcutsCount(long repositoryId, long folderId, int status, boolean includeMountFolders) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFoldersAndFileEntriesAndFileShortcutsCount");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFoldersAndFileEntriesAndFileShortcutsCount",
						new Object[]{
								repositoryId,
								folderId,
								status,
								includeMountFolders
						},
						new Class[] {
								long.class,
								long.class,
								int.class,
								boolean.class
						});

				return ((Integer) retval).intValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getFoldersAndFileEntriesAndFileShortcutsCount(repositoryId, folderId, status, includeMountFolders);
		}
	}

	@Override
	public int getFoldersAndFileEntriesAndFileShortcutsCount(long repositoryId, long folderId, int status, String[] mimeTypes, boolean includeMountFolders) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFoldersAndFileEntriesAndFileShortcutsCount");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFoldersAndFileEntriesAndFileShortcutsCount",
						new Object[]{
								repositoryId,
								folderId,
								status,
								mimeTypes,
								includeMountFolders
						},
						new Class[] {
								long.class,
								long.class,
								int.class,
								String[].class,
								boolean.class
						});

				return ((Integer) retval).intValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getFoldersAndFileEntriesAndFileShortcutsCount(repositoryId, folderId, status, mimeTypes, includeMountFolders);
		}
	}

	@Override
	public int getFoldersCount(long repositoryId, long parentFolderId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFoldersCount, repositoryId=" + repositoryId + " parentFolderId=" + parentFolderId);

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object count = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFoldersCount",
						new Object[]{
								repositoryId,
								parentFolderId
						},
						new Class[]{
								long.class,
								long.class
						});

				return ((Integer) count).intValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		} else {
			return super.getFoldersCount(repositoryId, parentFolderId);
		}
	}

	@Override
	public int getFoldersCount(long repositoryId, long parentFolderId, boolean includeMountFolders) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFoldersCount");
		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object count = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFoldersCount",
						new Object[]{
								repositoryId,
								parentFolderId,
								includeMountFolders
						},
						new Class[]{
								long.class,
								long.class,
								boolean.class
						});

				return ((Integer) count).intValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		} else {
			return super.getFoldersCount(repositoryId, parentFolderId, includeMountFolders);
		}

	}

	@Override
	public int getFoldersFileEntriesCount(long repositoryId, List<Long> folderIds, int status) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getFoldersFileEntriesCount");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getFoldersFileEntriesCount",
						new Object[]{
								repositoryId,
								folderIds,
								status
						},
						new Class[] {
								long.class,
								List.class,
								int.class
						});

				return ((Integer) retval).intValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getFoldersFileEntriesCount(repositoryId, folderIds, status);
		}
	}

	@Override
	public List<FileEntry> getGroupFileEntries(long groupId, long userId, int start, int end) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getGroupFileEntries");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getGroupFileEntries",
						new Object[]{
								groupId,
								userId,
								start,
								end
						},
						new Class[] {
								long.class,
								long.class,
								int.class,
								int.class
						});

				return (List<FileEntry>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getGroupFileEntries(groupId, userId, start, end);
		}
	}

	@Override
	public List<FileEntry> getGroupFileEntries(long groupId, long userId, int start, int end, OrderByComparator obc) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getGroupFileEntries");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getGroupFileEntries",
						new Object[]{
								groupId,
								userId,
								start,
								end,
								obc
						},
						new Class[] {
								long.class,
								long.class,
								int.class,
								int.class,
								com.liferay.portal.kernel.util.OrderByComparator.class
						});

				return (List<FileEntry>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getGroupFileEntries(groupId, userId, start, end, obc);
		}
	}

	@Override
	public List<FileEntry> getGroupFileEntries(long groupId, long userId, long rootFolderId, int start, int end) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getGroupFileEntries");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getGroupFileEntries",
						new Object[]{
								groupId,
								userId,
								rootFolderId,
								start,
								end
						},
						new Class[] {
								long.class,
								long.class,
								long.class,
								int.class,
								int.class
						});

				return (List<FileEntry>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getGroupFileEntries(groupId, userId, rootFolderId, start, end);
		}
	}

	@Override
	public List<FileEntry> getGroupFileEntries(long groupId, long userId, long rootFolderId, int start, int end, OrderByComparator obc) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getGroupFileEntries");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getGroupFileEntries",
						new Object[]{
								groupId,
								userId,
								rootFolderId,
								start,
								end,
								obc
						},
						new Class[] {
								long.class,
								long.class,
								long.class,
								int.class,
								int.class,
								com.liferay.portal.kernel.util.OrderByComparator.class
						});

				return (List<FileEntry>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getGroupFileEntries(groupId, userId, rootFolderId, start, end, obc);
		}
	}

	@Override
	public List<FileEntry> getGroupFileEntries(long groupId, long userId, long rootFolderId, String[] mimeTypes, int status, int start, int end, OrderByComparator obc) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getGroupFileEntries");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getGroupFileEntries",
						new Object[]{
								groupId,
								userId,
								rootFolderId,
								mimeTypes,
								status,
								start,
								end,
								obc
						},
						new Class[] {
								long.class,
								long.class,
								long.class,
								String[].class,
								int.class,
								int.class,
								int.class,
								com.liferay.portal.kernel.util.OrderByComparator.class
						});

				return (List<FileEntry>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getGroupFileEntries(groupId, userId, rootFolderId, mimeTypes, status, start, end, obc);
		}
	}

	@Override
	public int getGroupFileEntriesCount(long groupId, long userId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getGroupFileEntriesCount");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getGroupFileEntriesCount",
						new Object[]{
								groupId,
								userId
						},
						new Class[] {
								long.class,
								long.class
						});

				return ((Integer) retval).intValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getGroupFileEntriesCount(groupId, userId);
		}
	}

	@Override
	public int getGroupFileEntriesCount(long groupId, long userId, long rootFolderId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getGroupFileEntriesCount");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getGroupFileEntriesCount",
						new Object[]{
								groupId,
								userId,
								rootFolderId
						},
						new Class[] {
								long.class,
								long.class,
								long.class
						});

				return ((Integer) retval).intValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getGroupFileEntriesCount(groupId, userId, rootFolderId);
		}
	}

	@Override
	public int getGroupFileEntriesCount(long groupId, long userId, long rootFolderId, String[] mimeTypes, int status) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getGroupFileEntriesCount");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getGroupFileEntriesCount",
						new Object[]{
								groupId,
								userId,
								rootFolderId,
								mimeTypes,
								status
						},
						new Class[] {
								long.class,
								long.class,
								long.class,
								String[].class,
								int.class
						});

				return ((Integer) retval).intValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getGroupFileEntriesCount(groupId, userId, rootFolderId, mimeTypes, status);
		}
	}

	@Override
	public List<Folder> getMountFolders(long repositoryId, long parentFolderId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getMountFolders");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getMountFolders",
						new Object[]{
								repositoryId,
								parentFolderId
						},
						new Class[]{
								long.class,
								long.class
						});

				return (List<Folder>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		} else {
			return super.getMountFolders(repositoryId, parentFolderId);
		}
	}

	@Override
	public List<Folder> getMountFolders(long repositoryId, long parentFolderId, int start, int end) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getMountFolders");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getMountFolders",
						new Object[]{
								repositoryId,
								parentFolderId,
								start,
								end
						},
						new Class[]{
								long.class,
								long.class,
								int.class,
								int.class
						});

				return (List<Folder>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		} else {
			return super.getMountFolders(repositoryId, parentFolderId, start, end);
		}
	}

	@Override
	public List<Folder> getMountFolders(long repositoryId, long parentFolderId, int start, int end, OrderByComparator obc) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getMountFolders");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getMountFolders",
						new Object[]{
								repositoryId,
								parentFolderId,
								start,
								end
						},
						new Class[]{
								long.class,
								long.class,
								int.class,
								int.class,
								OrderByComparator.class
						});

				return (List<Folder>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		} else {
			return super.getMountFolders(repositoryId, parentFolderId, start, end, obc);
		}
	}

	@Override
	public int getMountFoldersCount(long repositoryId, long parentFolderId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getMountFoldersCount");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getMountFoldersCount",
						new Object[]{
								repositoryId,
								parentFolderId
						},
						new Class[]{
								long.class,
								long.class
						});

				return ((Integer) retval).intValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		} else {
			return super.getMountFoldersCount(repositoryId, parentFolderId);
		}

	}

	@Override
	public void getSubfolderIds(long repositoryId, List<Long> folderIds, long folderId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getSubfolderIds");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getSubfolderIds",
						new Object[]{
								repositoryId,
								folderId
						},
						new Class[] {
								long.class,
								long.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.getSubfolderIds(repositoryId, folderIds, folderId);
		}
	}

	@Override
	public List<Long> getSubfolderIds(long repositoryId, long folderId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getSubfolderIds");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getSubfolderIds",
						new Object[]{
								repositoryId,
								folderId
						},
						new Class[] {
								long.class,
								long.class
						});

				return (List<Long>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getSubfolderIds(repositoryId, folderId);
		}
	}

	@Override
	public List<Long> getSubfolderIds(long repositoryId, long folderId, boolean recurse) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getSubfolderIds");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getSubfolderIds",
						new Object[]{
								repositoryId,
								folderId,
								recurse
						},
						new Class[] {
								long.class,
								long.class,
								boolean.class
						});

				return (List<Long>) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getSubfolderIds(repositoryId, folderId, recurse);
		}
	}

	@Override
	public String[] getTempFileEntryNames(long groupId, long folderId, String tempFolderName) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:getTempFileEntryNames");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"getTempFileEntryNames",
						new Object[]{
								groupId,
								folderId,
								tempFolderName
						},
						new Class[] {
								long.class,
								long.class,
								String.class
						});

				return (String[]) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.getTempFileEntryNames(groupId, folderId, tempFolderName);
		}
	}

	@Override
	public Lock lockFileEntry(long fileEntryId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:lockFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			Lock lock = (Lock) DocumentEntityCacheUtil.getResult(
					CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED, Lock.class, fileEntryId
			);

			if (lock == null) {
				try {
					Object retval = RemotingUtil.invoke(
							PropsUtil.get("poc.remote.host.name"),
							GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
							false,
							"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
							"lockFileEntry",
							new Object[]{
									fileEntryId
							},
							new Class[]{
									long.class
							});

					lock = (Lock) retval;
				} catch (Throwable t) {
					t.printStackTrace();

					throw new PortalException(t);
				}
				finally {
					if (lock != null) {
						cacheResult(lock);
					}
				}
			}

			return lock;
		}else
		{
			return super.lockFileEntry(fileEntryId);
		}
	}

	@Override
	public Lock lockFileEntry(long fileEntryId, String owner, long expirationTime) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:lockFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"lockFileEntry",
						new Object[]{
								fileEntryId,
								owner,
								expirationTime
						},
						new Class[] {
								long.class,
								String.class,
								long.class
						});

				return (Lock) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.lockFileEntry(fileEntryId, owner, expirationTime);
		}
	}

	@Override
	public Lock lockFolder(long repositoryId, long folderId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:lockFolder");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"lockFolder",
						new Object[]{
								repositoryId,
								folderId
						},
						new Class[] {
								long.class,
								long.class
						});

				return (Lock) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.lockFolder(repositoryId, folderId);
		}
	}

	@Override
	public Lock lockFolder(long repositoryId, long folderId, String owner, boolean inheritable, long expirationTime) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:lockFolder");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"lockFolder",
						new Object[]{
								repositoryId,
								folderId,
								owner,
								inheritable,
								expirationTime
						},
						new Class[] {
								long.class,
								long.class,
								String.class,
								boolean.class,
								long.class
						});

				return (Lock) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.lockFolder(repositoryId, folderId, owner, inheritable, expirationTime);
		}
	}

	@Override
	public FileEntry moveFileEntry(long fileEntryId, long newFolderId, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:moveFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"moveFileEntry",
						new Object[]{
								fileEntryId,
								newFolderId,
								serviceContext
						},
						new Class[] {
								long.class,
								long.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (FileEntry) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.moveFileEntry(fileEntryId, newFolderId, serviceContext);
		}
	}

	@Override
	public Folder moveFolder(long folderId, long parentFolderId, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:moveFolder");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"moveFolder",
						new Object[]{
								folderId,
								parentFolderId,
								serviceContext
						},
						new Class[] {
								long.class,
								long.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (Folder) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.moveFolder(folderId, parentFolderId, serviceContext);
		}
	}

	@Override
	public Lock refreshFileEntryLock(String lockUuid, long expirationTime) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:refreshFileEntryLock");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"refreshFileEntryLock",
						new Object[]{
								lockUuid,
								expirationTime
						},
						new Class[] {
								long.class,
								long.class
						});

				return (Lock) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.refreshFileEntryLock(lockUuid, expirationTime);
		}
	}

	@Override
	public Lock refreshFolderLock(String lockUuid, long expirationTime) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:refreshFolderLock");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"refreshFolderLock",
						new Object[]{
								lockUuid,
								expirationTime
						},
						new Class[] {
								long.class,
								long.class
						});

				return (Lock) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.refreshFolderLock(lockUuid, expirationTime);
		}
	}

	@Override
	public void revertFileEntry(long fileEntryId, String version, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:revertFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"revertFileEntry",
						new Object[]{
								fileEntryId,
								version,
								serviceContext
						},
						new Class[] {
								long.class,
								String.class,
								com.liferay.portal.service.ServiceContext.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.revertFileEntry(fileEntryId, version, serviceContext);
		}
	}

	@Override
	public Hits search(long repositoryId, SearchContext searchContext) throws SearchException {
		_log.info("DLAppServiceImpl:search");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"search",
						new Object[]{
								repositoryId,
								searchContext
						},
						new Class[] {
								long.class,
								com.liferay.portal.kernel.search.SearchContext.class
						});

				return (Hits) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new SearchException(t);
			}
		}else
		{
			return super.search(repositoryId, searchContext);
		}
	}

	@Override
	public Hits search(long repositoryId, SearchContext searchContext, Query query) throws SearchException {
		_log.info("DLAppServiceImpl:search");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"search",
						new Object[]{
								repositoryId,
								searchContext,
								query
						},
						new Class[] {
								long.class,
								com.liferay.portal.kernel.search.SearchContext.class,
								com.liferay.portal.kernel.search.Query.class
						});

				return (Hits) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new SearchException(t);
			}
		}else
		{
			return super.search(repositoryId, searchContext, query);
		}
	}

	@Override
	public void unlockFileEntry(long fileEntryId) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:unlockFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"unlockFileEntry",
						new Object[]{
								fileEntryId
						},
						new Class[] {
								long.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.unlockFileEntry(fileEntryId);
		}
	}

	@Override
	public void unlockFileEntry(long fileEntryId, String lockUuid) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:unlockFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"unlockFileEntry",
						new Object[]{
								fileEntryId,
								lockUuid
						},
						new Class[] {
								long.class,
								String.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.unlockFileEntry(fileEntryId, lockUuid);
		}
	}

	@Override
	public void unlockFolder(long repositoryId, long folderId, String lockUuid) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:unlockFolder");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"unlockFolder",
						new Object[]{
								repositoryId,
								folderId,
								lockUuid
						},
						new Class[] {
								long.class,
								long.class,
								java.lang.String.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.unlockFolder(repositoryId, folderId, lockUuid);
		}
	}

	@Override
	public void unlockFolder(long repositoryId, long parentFolderId, String name, String lockUuid) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:unlockFolder");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"unlockFolder",
						new Object[]{
								repositoryId,
								parentFolderId,
								name,
								lockUuid
						},
						new Class[] {
								long.class,
								long.class,
								String.class,
								String.class
						});
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			super.unlockFolder(repositoryId, parentFolderId, name, lockUuid);
		}
	}

	@Override
	public FileEntry updateFileEntry(long fileEntryId, String sourceFileName, String mimeType, String title, String description, String changeLog, boolean majorVersion, byte[] bytes, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:updateFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"updateFileEntry",
						new Object[]{
								fileEntryId,
								sourceFileName,
								mimeType,
								title,
								description,
								changeLog,
								majorVersion,
								bytes,
								serviceContext
						},
						new Class[] {
								long.class,
								String.class,
								String.class,
								String.class,
								String.class,
								String.class,
								boolean.class,
								byte[].class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (FileEntry) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.updateFileEntry(fileEntryId, sourceFileName, mimeType, title, description, changeLog, majorVersion, bytes, serviceContext);
		}
	}

	@Override
	public FileEntry updateFileEntry(long fileEntryId, String sourceFileName, String mimeType, String title, String description, String changeLog, boolean majorVersion, File file, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:updateFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"updateFileEntry",
						new Object[]{
								fileEntryId,
								sourceFileName,
								mimeType,
								title,
								description,
								changeLog,
								majorVersion,
								file,
								serviceContext
						},
						new Class[] {
								long.class,
								String.class,
								String.class,
								String.class,
								String.class,
								String.class,
								boolean.class,
								java.io.File.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (FileEntry) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.updateFileEntry(fileEntryId, sourceFileName, mimeType, title, description, changeLog, majorVersion, file, serviceContext);
		}
	}

	@Override
	public FileEntry updateFileEntry(long fileEntryId, String sourceFileName, String mimeType, String title, String description, String changeLog, boolean majorVersion, InputStream is, long size, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:updateFileEntry");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				return this.updateFileEntry(fileEntryId, sourceFileName, mimeType, title, description, changeLog,
						majorVersion, FileUtil.getBytes(is, (int)size), serviceContext);

				/** THIS DOES NOT WORK BECAUSE ByteArrayInputStream is not a serializable object **
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"updateFileEntry",
						new Object[]{
								fileEntryId,
								sourceFileName,
								mimeType,
								title,
								description,
								changeLog,
								majorVersion,
								is,
								size,
								serviceContext
						},
						new Class[] {long.class,
								String.class,
								String.class,
								String.class,
								String.class,
								String.class,
								boolean.class,
								java.io.InputStream.class,
								long.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (FileEntry) retval;
			 	*/
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.updateFileEntry(fileEntryId, sourceFileName, mimeType, title, description, changeLog, majorVersion, is, size, serviceContext);
		}
	}

	@Override
	public FileEntry updateFileEntryAndCheckIn(long fileEntryId, String sourceFileName, String mimeType, String title, String description, String changeLog, boolean majorVersion, File file, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:updateFileEntryAndCheckIn");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"updateFileEntryAndCheckIn",
						new Object[]{
								fileEntryId,
								sourceFileName,
								mimeType,
								title,
								description,
								changeLog,
								majorVersion,
								file,
								serviceContext
						},
						new Class[] {
								long.class,
								String.class,
								String.class,
								String.class,
								String.class,
								String.class,
								boolean.class,
								java.io.File.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (FileEntry) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.updateFileEntryAndCheckIn(fileEntryId, sourceFileName, mimeType, title, description, changeLog, majorVersion, file, serviceContext);
		}
	}

	@Override
	public FileEntry updateFileEntryAndCheckIn(long fileEntryId, String sourceFileName, String mimeType, String title, String description, String changeLog, boolean majorVersion, InputStream is, long size, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:updateFileEntryAndCheckIn");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				return this.updateFileEntryAndCheckIn(fileEntryId, sourceFileName, mimeType,
						title, description, changeLog, majorVersion, FileUtil.createTempFile(is), serviceContext);

				/** THIS DOES NOT WORK BECAUSE ByteArrayInputStream is not a serializable object **
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"updateFileEntryAndCheckIn",
						new Object[]{
								fileEntryId,
								sourceFileName,
								mimeType,
								title,
								description,
								changeLog,
								majorVersion,
								is,
								size,
								serviceContext
						},
						new Class[] {
								long.class,
								String.class,
								String.class,
								String.class,
								String.class,
								String.class,
								boolean.class,
								java.io.InputStream.class,
								long.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (FileEntry) retval;
			 	*/
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.updateFileEntryAndCheckIn(fileEntryId, sourceFileName, mimeType, title, description, changeLog, majorVersion, is, size, serviceContext);
		}
	}

	@Override
	public DLFileShortcut updateFileShortcut(long fileShortcutId, long folderId, long toFileEntryId, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:updateFileShortcut");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"updateFileShortcut",
						new Object[]{
								fileShortcutId,
								folderId,
								toFileEntryId,
								serviceContext
						},
						new Class[] {
								long.class,
								long.class,
								long.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (DLFileShortcut) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.updateFileShortcut(fileShortcutId, folderId, toFileEntryId, serviceContext);
		}
	}

	@Override
	public Folder updateFolder(long folderId, String name, String description, ServiceContext serviceContext) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:updateFolder");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"updateFolder",
						new Object[]{
								folderId,
								name,
								description,
								serviceContext
						},
						new Class[] {
								long.class,
								java.lang.String.class,
								java.lang.String.class,
								com.liferay.portal.service.ServiceContext.class
						});

				return (Folder) retval;
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.updateFolder(folderId, name, description, serviceContext);
		}
	}

	@Override
	public boolean verifyFileEntryCheckOut(long repositoryId, long fileEntryId, String lockUuid) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:verifyFileEntryCheckOut");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"verifyFileEntryCheckOut",
						new Object[]{
								repositoryId,
								fileEntryId,
								lockUuid
						},
						new Class[] {
								long.class,
								long.class,
								java.lang.String.class
						});

				return ((Boolean) retval).booleanValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.verifyFileEntryCheckOut(repositoryId, fileEntryId, lockUuid);
		}
	}

	@Override
	public boolean verifyFileEntryLock(long repositoryId, long fileEntryId, String lockUuid) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:verifyFileEntryLock");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"verifyFileEntryLock",
						new Object[]{
								repositoryId,
								fileEntryId,
								lockUuid
						},
						new Class[] {
								long.class,
								long.class,
								java.lang.String.class
						});

				return ((Boolean) retval).booleanValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.verifyFileEntryLock(repositoryId, fileEntryId, lockUuid);
		}
	}

	@Override
	public boolean verifyInheritableLock(long repositoryId, long folderId, String lockUuid) throws PortalException, SystemException {
		_log.info("DLAppServiceImpl:verifyInheritableLock");

		if (RemotingConstants.REMOTE_HOST_ENABLED && RemoteDocumentThreadLocal.isRemote()) {
			try {
				Object retval = RemotingUtil.invoke(
						PropsUtil.get("poc.remote.host.name"),
						GetterUtil.getInteger(PropsUtil.get("poc.remote.host.port"), 8080),
						false,
						"com.liferay.portlet.documentlibrary.service.http.DLAppServiceHttp",
						"verifyInheritableLock",
						new Object[]{
								repositoryId,
								folderId,
								lockUuid
						},
						new Class[] {
								long.class,
								long.class,
								String.class
						});

				return ((Boolean) retval).booleanValue();
			} catch (Throwable t) {
				t.printStackTrace();

				throw new PortalException(t);
			}
		}else
		{
			return super.verifyInheritableLock(repositoryId, folderId, lockUuid);
		}
	}

	public void cacheResult(Object o) {
		if (_log.isDebugEnabled()) {
			_log.debug("Caching result for object type "+o.getClass().getName());
		}

		if (o instanceof FileEntry) {
			FileEntry fileEntry = (FileEntry) o;

			DocumentFinderCacheUtil.putResult(FINDER_PATH_GET_FILES_BY_R_F,
					new Object[]{fileEntry.getRepositoryId(), fileEntry.getFolderId()}, o);

			DocumentEntityCacheUtil.putResult(CacheConstants.DOCUMENT_ENTITY_CACHE_ENABLED,
					FileEntry.class, ((FileEntry) o).getPrimaryKey(), o);
		} else if (o instanceof Folder) {
			Folder folder = (Folder) o;

			DocumentFinderCacheUtil.putResult(FINDER_PATH_GET_FOLDERS_BY_R_P,
					new Object[]{Long.valueOf(folder.getRepositoryId()), Long.valueOf(folder.getParentFolderId())}, o);

			DocumentEntityCacheUtil.putResult(CacheConstants.DOCUMENT_ENTITY_CACHE_ENABLED,
					Folder.class, ((Folder) o).getPrimaryKey(), o);
		}
	}

	public void cacheResult(List objects) {

		for (Object o : objects) {
			cacheResult(o);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(DLAppServiceImpl.class);

	private static final String _FINDER_CLASS_NAME_ENTITY = FileEntry.class.getName();

	private static final FinderPath FINDER_PATH_GET_FILES_BY_R_F = new FinderPath(CacheConstants.DOCUMENT_ENTITY_CACHE_ENABLED,
			CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED, FileEntry.class, _FINDER_CLASS_NAME_ENTITY, "getFileEntries",
			new String[]{Long.class.getName(), Long.class.getName()});

	private static final FinderPath FINDER_PATH_GET_FILES_BY_R_F_S_E = new FinderPath(CacheConstants.DOCUMENT_ENTITY_CACHE_ENABLED,
			CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED, FileEntry.class, _FINDER_CLASS_NAME_ENTITY, "getFileEntries",
			new String[]{Long.class.getName(), Long.class.getName(), "java.lang.Integer", "java.lang.Integer"});

	private static final FinderPath FINDER_PATH_GET_FILES_BY_R_F_S_E_OBC = new FinderPath(CacheConstants.DOCUMENT_ENTITY_CACHE_ENABLED,
			CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED, FileEntry.class, _FINDER_CLASS_NAME_ENTITY, "getFileEntries",
			new String[]{Long.class.getName(), Long.class.getName(), "java.lang.Integer", "java.lang.Integer", "com.liferay.portal.kernel.util.OrderByComparator"});

	private static final FinderPath FINDER_PATH_GET_FILES_SHORTCUTS_R_F_S_S_E = new FinderPath(CacheConstants.DOCUMENT_ENTITY_CACHE_ENABLED,
			CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED, FileEntry.class, _FINDER_CLASS_NAME_ENTITY, "getFileEntriesAndFileShortcuts",
			new String[]{Long.class.getName(), Long.class.getName(), "java.lang.Integer", "java.lang.Integer", "java.lang.Integer"});

	private static final FinderPath FINDER_PATH_GET_FOLDERS_BY_R_P = new FinderPath(CacheConstants.DOCUMENT_ENTITY_CACHE_ENABLED,
			CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED, FileEntry.class, _FINDER_CLASS_NAME_ENTITY, "getFolders",
			new String[]{Long.class.getName(), Long.class.getName()});

	private static final FinderPath FINDER_PATH_GET_FOLDERS_BY_R_P_M_S_E_OBC = new FinderPath(CacheConstants.DOCUMENT_ENTITY_CACHE_ENABLED,
			CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED, FileEntry.class, _FINDER_CLASS_NAME_ENTITY, "getFolders",
			new String[]{Long.class.getName(), Long.class.getName(), "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer", "com.liferay.portal.kernel.util.OrderByComparator"});

	private static final FinderPath FINDER_PATH_GET_FOLDERS_BY_R_P_S_E = new FinderPath(CacheConstants.DOCUMENT_ENTITY_CACHE_ENABLED,
			CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED, FileEntry.class, _FINDER_CLASS_NAME_ENTITY, "getFolders",
			new String[]{Long.class.getName(), Long.class.getName(), "java.lang.Integer", "java.lang.Integer"});

	private static final FinderPath FINDER_PATH_GET_FOLDERS_BY_R_P_S_E_OBC = new FinderPath(CacheConstants.DOCUMENT_ENTITY_CACHE_ENABLED,
			CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED, FileEntry.class, _FINDER_CLASS_NAME_ENTITY, "getFolders",
			new String[]{Long.class.getName(), Long.class.getName(), "java.lang.Integer", "java.lang.Integer", "com.liferay.portal.kernel.util.OrderByComparator"});


}
