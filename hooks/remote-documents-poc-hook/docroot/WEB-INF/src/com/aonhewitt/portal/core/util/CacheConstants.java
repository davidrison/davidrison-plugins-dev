package com.aonhewitt.portal.core.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class CacheConstants {

	public static boolean DOCUMENT_ENTITY_CACHE_ENABLED =
			GetterUtil.getBoolean(
				PropsUtil.get("document.entity.cache.enabled"), true
			);

	public static boolean DOCUMENT_ENTITY_CACHE_BLOCKING_ENABLED =
			GetterUtil.getBoolean(
				PropsUtil.get("document.entity.cache.blocking.enabled"), true
			);

	public static int DOCUMENT_ENTITY_CACHE_THREAD_LOCAL_CACHE_MAX_SIZE =
			GetterUtil.getInteger(
				PropsUtil.get("document.entity.cache.thread.local.cache.max.size"), 200
			);

	public static boolean DOCUMENT_FINDER_CACHE_ENABLED =
			GetterUtil.getBoolean(
					PropsUtil.get("document.finder.cache.enabled"), true
			);

	public static boolean DOCUMENT_FINDER_CACHE_BLOCKING_ENABLED =
			GetterUtil.getBoolean(
					PropsUtil.get("document.finder.cache.blocking.enabled"), true
			);
	public static int DOCUMENT_FINDER_CACHE_THREAD_LOCAL_CACHE_MAX_SIZE =
			GetterUtil.getInteger(
					PropsUtil.get("document.finder.cache.thread.local.cache.max.size"), 200
			);
}
