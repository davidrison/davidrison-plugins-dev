package com.aonhewitt.portlet.documentlibrary.util;

import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;

public class DLIndexerPostProcessor extends BaseIndexerPostProcessor {

	@Override
	public void postProcessDocument(Document document, Object obj) throws Exception {
		DLFileEntry dlFileEntry = (DLFileEntry)obj;

		Group group = GroupLocalServiceUtil.fetchGroup(
				dlFileEntry.getGroupId());

		boolean isRemoteDocument = false;
		if (group.isOrganization()) {
			isRemoteDocument = true;
		}

		document.addKeyword("isRemoteDocument", isRemoteDocument);

		super.postProcessDocument(document, obj);
	}
}
