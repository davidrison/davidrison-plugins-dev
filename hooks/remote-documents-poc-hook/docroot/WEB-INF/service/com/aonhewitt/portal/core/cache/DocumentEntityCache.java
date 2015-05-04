package com.aonhewitt.portal.core.cache;

import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.SessionFactory;

import java.io.Serializable;

public interface DocumentEntityCache {

	public void clearCache();

	public void clearCache(String className);

	public void clearLocalCache();

	public Object getResult(
			boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey);

	public void invalidate();

	public void putResult(
			boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey,
			Object result);

	public void removeCache(String className);

	public void removeResult(
			boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey);
}
