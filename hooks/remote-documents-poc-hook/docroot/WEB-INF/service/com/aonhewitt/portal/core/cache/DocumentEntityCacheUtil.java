package com.aonhewitt.portal.core.cache;

import java.io.Serializable;

public class DocumentEntityCacheUtil {
	public static void clearCache() {
		getDocumentEntityCache().clearCache();
	}

	public static void clearCache(String className) {
		getDocumentEntityCache().clearCache(className);
	}

	public static void clearLocalCache() {
		getDocumentEntityCache().clearLocalCache();
	}

	public static DocumentEntityCache getDocumentEntityCache() {
		return _documentEntityCache;
	}

	public static Object getResult(
			boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey) {
		return getDocumentEntityCache().getResult(entityCacheEnabled, clazz, primaryKey);
	}

	public static void invalidate() {
		getDocumentEntityCache().invalidate();
	}

	public static void putResult(
			boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey,
			Object result) {
		getDocumentEntityCache().putResult(entityCacheEnabled, clazz, primaryKey, result);
	}

	public static void removeCache(String className) {
		getDocumentEntityCache().removeCache(className);
	}

	public static void removeResult(
			boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey) {
		getDocumentEntityCache().removeResult(entityCacheEnabled, clazz, primaryKey);
	}

	public void setDocumentEntityCache(DocumentEntityCache documentCache) {
		_documentEntityCache = documentCache;
	}

	private static DocumentEntityCache _documentEntityCache;
}
