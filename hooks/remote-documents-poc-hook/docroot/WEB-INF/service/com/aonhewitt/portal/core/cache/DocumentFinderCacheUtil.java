package com.aonhewitt.portal.core.cache;

import com.liferay.portal.kernel.dao.orm.FinderPath;

public class DocumentFinderCacheUtil {

	public static void clearCache() {
		getDocumentFinderCache().clearCache();
	}

	public static void clearCache(String className) {
		getDocumentFinderCache().clearCache(className);
	}

	public static void clearLocalCache() {
		getDocumentFinderCache().clearLocalCache();
	}

	public static Object getResult(FinderPath finderPath, Object[] args) {
		return getDocumentFinderCache().getResult(finderPath, args);
	}

	public static void invalidate() {
		getDocumentFinderCache().invalidate();
	}

	public static void putResult(FinderPath finderPath, Object[] args, Object result) {
		getDocumentFinderCache().putResult(finderPath, args, result);
	}

	public static void removeCache(String className) {
		getDocumentFinderCache().removeCache(className);
	}

	public static void removeResult(FinderPath finderPath, Object[] args) {
		getDocumentFinderCache().removeResult(finderPath, args);
	}

	public static DocumentFinderCache getDocumentFinderCache() {
		return _documentFinderCache;
	}

	public void setDocumentFinderCache(DocumentFinderCache documentFinderCache) {
		_documentFinderCache = documentFinderCache;
	}

	private static DocumentFinderCache _documentFinderCache;
}
