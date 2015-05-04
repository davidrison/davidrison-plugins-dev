package com.aonhewitt.portal.core.cache;

import com.aonhewitt.portal.core.util.CacheConstants;
import com.liferay.portal.kernel.cache.CacheRegistryItem;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.dao.shard.ShardUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import org.apache.commons.collections.map.LRUMap;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DocumentEntityCacheImpl implements CacheRegistryItem, DocumentEntityCache {

	public static final String CACHE_NAME = DocumentEntityCacheImpl.class.getName();

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

	public Object getResult(boolean documentCacheEnabled, Class<?> clazz, Serializable primaryKey) {
		if (!CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED ||
				!documentCacheEnabled || !CacheRegistryUtil.isActive()) {

			return null;
		}

		Object result = null;

		Map<Serializable, Object> localCache = null;

		Serializable localCacheKey = null;

		if (_localCacheAvailable) {
			localCache = _localCache.get();

			localCacheKey = _encodeLocalCacheKey(clazz, primaryKey);

			result = localCache.get(localCacheKey);
		}

		if (result == null) {
			PortalCache portalCache = _getPortalCache(clazz.getName(), true);

			Serializable cacheKey = _encodeCacheKey(primaryKey);

			result = portalCache.get(cacheKey);

			if (result == null) {
				result = StringPool.BLANK;
			}

			if (_localCacheAvailable) {
				localCache.put(localCacheKey, result);
			}
		}

		if (Validator.isNull(result)) {
			return null;
		}

		return result;
	}

	public void invalidate() {
		clearCache();
	}

	public void putResult(boolean documentCacheEnabled, Class<?> clazz, Serializable primaryKey, Object result) {
		if (!CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED ||
				!documentCacheEnabled || !CacheRegistryUtil.isActive() ||
				(result == null)) {

			return;
		}

		if (_localCacheAvailable) {
			Map<Serializable, Object> localCache = _localCache.get();

			Serializable localCacheKey = _encodeLocalCacheKey(clazz,
					primaryKey);

			localCache.put(localCacheKey, result);
		}

		PortalCache portalCache = _getPortalCache(clazz.getName(), true);

		Serializable cacheKey = _encodeCacheKey(primaryKey);

		portalCache.put(cacheKey, result);
	}

	public void removeCache(String className) {
		_portalCaches.remove(className);

		String groupKey = _GROUP_KEY_PREFIX.concat(className);

		_multiVMPool.removeCache(groupKey);
	}

	public void removeResult(boolean documentCacheEnabled, Class<?> clazz, Serializable primaryKey) {
		if (!CacheConstants.DOCUMENT_FINDER_CACHE_ENABLED ||
				!documentCacheEnabled || !CacheRegistryUtil.isActive()) {

			return;
		}

		if (_localCacheAvailable) {
			Map<Serializable, Object> localCache = _localCache.get();

			Serializable localCacheKey = _encodeLocalCacheKey(clazz,
					primaryKey);

			localCache.remove(localCacheKey);
		}

		PortalCache portalCache = _getPortalCache(clazz.getName(), true);

		Serializable cacheKey = _encodeCacheKey(primaryKey);

		portalCache.remove(cacheKey);
	}

	public void setMultiVMPool(MultiVMPool multiVMPool) {
		_multiVMPool = multiVMPool;
	}

	private Serializable _encodeCacheKey(Serializable primaryKey) {
		return new CacheKey(ShardUtil.getCurrentShardName(), primaryKey);
	}

	private Serializable _encodeLocalCacheKey(
			Class<?> clazz, Serializable primaryKey) {

		return new LocalCacheKey(
				ShardUtil.getCurrentShardName(), clazz.getName(), primaryKey);
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
					DocumentEntityCacheImpl.class + "._localCache",
					new LRUMap(
							CacheConstants.DOCUMENT_FINDER_CACHE_THREAD_LOCAL_CACHE_MAX_SIZE));
			_localCacheAvailable = true;
		}
	}

	private MultiVMPool _multiVMPool;
	private ConcurrentMap<String, PortalCache> _portalCaches =
			new ConcurrentHashMap<String, PortalCache>();

	private static class CacheKey implements Serializable {

		public CacheKey(String shardName, Serializable primaryKey) {
			_shardName = shardName;
			_primaryKey = primaryKey;
		}

		@Override
		public boolean equals(Object obj) {
			CacheKey cacheKey = (CacheKey)obj;

			if (cacheKey._shardName.equals(_shardName) &&
					cacheKey._primaryKey.equals(_primaryKey)) {

				return true;
			}
			else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			return _shardName.hashCode() * 11 + _primaryKey.hashCode();
		}

		private static final long serialVersionUID = 1L;

		private final Serializable _primaryKey;
		private final String _shardName;

	}

	private static class LocalCacheKey implements Serializable {

		public LocalCacheKey(
				String shardName, String className, Serializable primaryKey) {

			_shardName = shardName;
			_className = className;
			_primaryKey = primaryKey;
		}

		@Override
		public boolean equals(Object obj) {
			LocalCacheKey localCacheKey = (LocalCacheKey) obj;

			if (localCacheKey._shardName.equals(_shardName) &&
					localCacheKey._className.equals(_className) &&
					localCacheKey._primaryKey.equals(_primaryKey)) {

				return true;
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			int hashCode = HashUtil.hash(0, _shardName);

			hashCode = HashUtil.hash(hashCode, _className);
			hashCode = HashUtil.hash(hashCode, _primaryKey);

			return hashCode;
		}

		private static final long serialVersionUID = 1L;

		private final String _className;
		private final Serializable _primaryKey;
		private final String _shardName;
	}

	private static final Log _log = LogFactoryUtil.getLog(DocumentEntityCacheImpl.class);

}
