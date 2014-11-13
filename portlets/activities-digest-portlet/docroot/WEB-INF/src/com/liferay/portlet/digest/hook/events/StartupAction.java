package com.liferay.portlet.digest.hook.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.*;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.digest.util.PropsValues;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.journal.NoSuchStructureException;
import com.liferay.portlet.journal.NoSuchTemplateException;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.model.JournalTemplateConstants;
import com.liferay.portlet.journal.service.JournalStructureLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun(GetterUtil.getLong(ids[0]));
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void addImages(long companyId, String imagesDirName) throws Exception {
		ServletContext servletContext = DigestHelperUtil.getServletContext();

		Set<String> resourcePaths = servletContext.getResourcePaths(
				_RESOURCES_DIR.concat(imagesDirName));

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			if (resourcePath.endsWith(StringPool.SLASH)) {
				continue;
			}

			String name = FileUtil.getShortFileName(resourcePath);

			URL url = servletContext.getResource(resourcePath);

			URLConnection urlConnection = url.openConnection();

			addImages(companyId,
					name, urlConnection.getInputStream());
		}
	}

	protected void addImages(long companyId, String name, InputStream inputStream) {

		String contentType = MimeTypesUtil.getContentType(name);
		System.out.println("ADDING IMAGE " + name);
/** TODO FIXME ASAP
		DLFileEntry fileEntry = DLAppServiceUtil.addFileEntry(
				repositoryId, folderId, sourceFileName, contentType, name,
				description, changeLog, inputStream, size, serviceContext); **/
	}

	protected void addCSS(long companyId, String cssDirName) throws Exception {

	}

	@SuppressWarnings("unchecked")
	protected void addJournalStructures(
			long companyId, String parentStructureId, String structuresDirName)
			throws Exception {

		ServletContext servletContext = DigestHelperUtil.getServletContext();

		Set<String> resourcePaths = servletContext.getResourcePaths(
			_RESOURCES_DIR.concat(structuresDirName));

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			if (resourcePath.endsWith(StringPool.SLASH)) {
				continue;
			}

			String name = FileUtil.getShortFileName(resourcePath);

			URL url = servletContext.getResource(resourcePath);

			URLConnection urlConnection = url.openConnection();

			addJournalStructures(companyId,
					parentStructureId, name, urlConnection.getInputStream());
		}
	}

	protected void addJournalStructures(
			long companyId, String parentStructureId, String fileName, InputStream inputStream)
			throws Exception {

		String journalStructureId = getJournalId(fileName);

		String name = FileUtil.stripExtension(fileName);

		Map<Locale, String> nameMap = DigestHelperUtil.getMap(name);

		String xsd = StringUtil.read(inputStream);

		JournalStructure journalStructure = null;

		try {
			journalStructure =
					JournalStructureLocalServiceUtil.getStructure(
							DigestHelperUtil.getGlobalGroup(companyId).getGroupId(), journalStructureId);

			if (_log.isInfoEnabled()) {
				_log.info("Not Adding. Journal Structure " + journalStructure.getStructureId() + " already exists.");
			}
		}
		catch(NoSuchStructureException e) {
			journalStructure =
					JournalStructureLocalServiceUtil.addStructure(
							DigestHelperUtil.getDefaultAdminUser(companyId).getUserId(), DigestHelperUtil.getGlobalGroup(companyId).getGroupId(),
							journalStructureId, false, parentStructureId, nameMap,
							null, xsd, DigestHelperUtil.getServiceContext(companyId));
		}

		addJournalTemplates(companyId, journalStructure.getStructureId(), _JOURNAL_TEMPLATES_DIR_NAME + name);

		if (Validator.isNull(parentStructureId)) {
			addJournalStructures(companyId, journalStructure.getStructureId(), _IMAGES_DIR_NAME + name);
		}
	}

	@SuppressWarnings("unchecked")
	protected void addJournalTemplates(
			long companyId, String journalStructureId, String templatesDirName)
			throws Exception {

		Set<String> resourcePaths = DigestHelperUtil.getServletContext().getResourcePaths(
				_RESOURCES_DIR.concat(templatesDirName));

		if (resourcePaths == null) {
			return;
		}

		for (String resourcePath : resourcePaths) {
			if (resourcePath.endsWith(StringPool.SLASH)) {
				continue;
			}

			String name = FileUtil.getShortFileName(resourcePath);

			URL url = DigestHelperUtil.getServletContext().getResource(resourcePath);

			URLConnection urlConnection = url.openConnection();

			addJournalTemplates(companyId, journalStructureId, name, urlConnection.getInputStream());
		}
	}

	protected void addJournalTemplates(
			long companyId, String journalStructureId, String fileName, InputStream inputStream)
			throws Exception {

		String journalTemplateId = getJournalId(fileName);

		String name = FileUtil.stripExtension(fileName);

		Map<Locale, String> nameMap = DigestHelperUtil.getMap(name);

		String xsl = StringUtil.read(inputStream);

		xsl = replaceFileEntryURL(xsl);

		try {
			JournalTemplate journalTemplate =
					JournalTemplateLocalServiceUtil.getTemplate(
						DigestHelperUtil.getGlobalGroup(companyId).getGroupId(), journalTemplateId);

			if (_log.isInfoEnabled()) {
				_log.info("Not Adding. Journal Template " + journalTemplate.getTemplateId() + " already exists.");
			}
		}
		catch (NoSuchTemplateException e) {
			JournalTemplateLocalServiceUtil.addTemplate(
					DigestHelperUtil.getDefaultAdminUser(companyId).getUserId(), DigestHelperUtil.getGlobalGroup(companyId).getGroupId(),
					journalTemplateId, false, journalStructureId,
					nameMap, null, xsl, true, JournalTemplateConstants.LANG_TYPE_VM,
					false, false, StringPool.BLANK, null, DigestHelperUtil.getServiceContext(companyId));
		}

	}

	protected void doRun(long companyId) throws Exception {

		boolean developerMode =
				PropsValues.DIGEST_DEVELOPER_MODE;

		setupDefaultDigestConfiguration(companyId);
		setupDigestImages(companyId);
		setupDigestTemplates(companyId);
	}


	protected String getJournalId(String fileName) {
		String id = FileUtil.stripExtension(fileName);

		id = id.toUpperCase();

		return StringUtil.replace(id, StringPool.SPACE, StringPool.DASH);
	}

	protected String replaceFileEntryURL(String content) throws Exception {
		Matcher matcher = _fileEntryPattern.matcher(content);

		while (matcher.find()) {
			String fileName = matcher.group(1);

			FileEntry fileEntry = _fileEntries.get(fileName);

			String fileEntryURL = StringPool.BLANK;

			if (fileEntry != null) {
				fileEntryURL = DLUtil.getPreviewURL(
						fileEntry, fileEntry.getFileVersion(), null,
						StringPool.BLANK);
			}

			content = matcher.replaceFirst(fileEntryURL);

			matcher.reset(content);
		}

		return content;
	}

	protected void setupDefaultDigestConfiguration(long companyId) throws Exception {
		DigestHelperUtil.getDefaultPortalDigestConfiguration(companyId);
	}

	protected void setupDigestImages(long companyId) throws Exception {
		ServletContext servletContext = DigestHelperUtil.getServletContext();

		URL url = servletContext.getResource(_RESOURCES_DIR);

		if (url == null) {
			return;
		}

		Set<String> resourcePaths = servletContext.getResourcePaths(
				_RESOURCES_DIR);

		addImages(companyId, _IMAGES_DIR_NAME);
	}

	protected void setupDigestTemplates(long companyId) throws Exception {
		ServletContext servletContext = DigestHelperUtil.getServletContext();

		URL url = servletContext.getResource(_RESOURCES_DIR);

		if (url == null) {
			return;
		}

		Set<String> resourcePaths = servletContext.getResourcePaths(
				_RESOURCES_DIR);

		addJournalStructures(companyId, StringPool.BLANK, _JOURNAL_STRUCTURES_DIR_NAME);
	}

	private static final String _IMAGES_DIR_NAME =
			"/documents/images/";

	private static final String _JOURNAL_STRUCTURES_DIR_NAME =
			"/journal/structures/";

	private static final String _JOURNAL_TEMPLATES_DIR_NAME =
			"/journal/templates/";

	private static final long _RANGE_MIN = 50000;

	private static final String _RESOURCES_DIR = "/WEB-INF/classes/resources/";

	private Map<String, FileEntry> _fileEntries =
			new HashMap<String, FileEntry>();
	private Pattern _fileEntryPattern = Pattern.compile(
			"\\[\\$FILE=([^\\$]+)\\$\\]");

	private static final Log _log = LogFactoryUtil.getLog(StartupAction.class);
}