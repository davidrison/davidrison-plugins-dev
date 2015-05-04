package com.aonhewitt.portal.core.cache;

import com.liferay.portal.kernel.dao.orm.FinderPath;

public interface DocumentFinderCache {

	public void clearCache();

	public void clearCache(String className);

	public void clearLocalCache();

	public Object getResult(
			FinderPath finderPath, Object[] args);

	public void invalidate();

	public void putResult(FinderPath finderPath, Object[] args, Object result);

	public void removeCache(String className);

	public void removeResult(FinderPath finderPath, Object[] args);
}
