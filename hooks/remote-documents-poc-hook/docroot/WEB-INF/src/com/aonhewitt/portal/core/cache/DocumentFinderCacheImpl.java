package com.aonhewitt.portal.core.cache;

import com.aonhewitt.portal.core.util.CacheConstants;
import com.liferay.portal.kernel.cache.CacheRegistryItem;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.StringPool;
import org.apache.commons.collections.map.LRUMap;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DocumentFinderCacheImpl implements CacheRegistryItem, DocumentFinderCache {

	public static final String CACHE_NAME = DocumentFinderCacheImpl.class.getName();

	public void afterPropertiesSet() {
		CacheRegistryUtil.register(this);
	}

	public void clearCache() {
		clearLocalCache();

		for (PortalCache portalCache : _portalCaches.values()) {
			portalCache.removeAll();
		}
	}

	public void clearCache(String className) {
		clearLocalCache();

		PortalCache portalCache = _getPortalCache(className, false);

		if (portalCache != null) {
			portalCache.removeAll();
		}
	}

	public void clearLocalCache() {
		if (_localCacheAvailable) {
			_localCache.remove();
		}
	}

	public String getRegistryName() {
		return CACHE_NAME;
	}

	public Object getResult(
			FinderPath finderPath, Object[] args) {

		if (!CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED ||
				!finderPath.isFinderCacheEnabled() ||
				!CacheRegistryUtil.isActive()) {

			return null;
		}

		Object result = null;

		Map<Serializable, Object> localCache = null;

		Serializable localCacheKey = null;

		if (_localCacheAvailable) {
			localCache = _localCache.get();

			localCacheKey = finderPath.encodeLocalCacheKey(args);

			result = localCache.get(localCacheKey);
		}

		if (result == null) {
			PortalCache portalCache = _getPortalCache(
					finderPath.getCacheName(), true);

			Serializable cacheKey = finderPath.encodeCacheKey(args);

			result = portalCache.get(cacheKey);

			if (result != null) {
				if (_localCacheAvailable) {
					localCache.put(localCacheKey, result);
				}
			}
		}

		return result;
	}

	public void invalidate() {
		clearCache();
	}

	public void putResult(FinderPath finderPath, Object[] args, Object result) {
		if (!CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED ||
				!finderPath.isFinderCacheEnabled() ||
				!CacheRegistryUtil.isActive() ||
				(result == null)) {

			return;
		}

		if (_localCacheAvailable) {
			Map<Serializable, Object> localCache = _localCache.get();

			Serializable localCacheKey = finderPath.encodeLocalCacheKey(args);

			localCache.put(localCacheKey, result);
		}

		PortalCache portalCache = _getPortalCache(
				finderPath.getCacheName(), true);

		Serializable cacheKey = finderPath.encodeCacheKey(args);

		portalCache.put(cacheKey, result);
	}

	public void removeCache(String className) {
		_portalCaches.remove(className);

		String groupKey = _GROUP_KEY_PREFIX.concat(className);

		_multiVMPool.removeCache(groupKey);
	}

	public void removeResult(FinderPath finderPath, Object[] args) {
		if (!CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED ||
				!finderPath.isFinderCacheEnabled() ||
				!CacheRegistryUtil.isActive()) {

			return;
		}

		if (_localCacheAvailable) {
			Map<Serializable, Object> localCache = _localCache.get();

			Serializable localCacheKey = finderPath.encodeLocalCacheKey(args);

			localCache.remove(localCacheKey);
		}

		PortalCache portalCache = _getPortalCache(
				finderPath.getCacheName(), true);

		Serializable cacheKey = finderPath.encodeCacheKey(args);

		portalCache.remove(cacheKey);
	}

	public void setMultiVMPool(MultiVMPool multiVMPool) {
		_multiVMPool = multiVMPool;
	}

	private PortalCache _getPortalCache(
			String className, boolean createIfAbsent) {

		PortalCache portalCache = _portalCaches.get(className);

		if ((portalCache == null) && createIfAbsent) {
			String groupKey = _GROUP_KEY_PREFIX.concat(className);

			portalCache = _multiVMPool.getCache(
					groupKey, CacheConstants.DOCUMENT_FINDER_CACHE_BLOCKING_ENABLED);

			PortalCache previousPortalCache = _portalCaches.putIfAbsent(
					className, portalCache);

			if (previousPortalCache != null) {
				portalCache = previousPortalCache;
			}
		}

		return portalCache;
	}

	private static final String _GROUP_KEY_PREFIX = CACHE_NAME.concat(
			StringPool.PERIOD);

	private static ThreadLocal<LRUMap> _localCache;
	private static boolean _localCacheAvailable;

	static {
		if (CacheConstants.DOCUMENT_FINDER_CACHE_THREAD_LOCAL_CACHE_MAX_SIZE > 0) {
			_localCache = new AutoResetThreadLocal<LRUMap>(
					DocumentFinderCacheImpl.class + "._localCache",
					new LRUMap(
							CacheConstants.DOCUMENT_FINDER_CACHE_THREAD_LOCAL_CACHE_MAX_SIZE));
			_localCacheAvailable = true;
		}
	}

	private MultiVMPool _multiVMPool;
	private ConcurrentMap<String, PortalCache> _portalCaches =
			new ConcurrentHashMap<String, PortalCache>();


}
