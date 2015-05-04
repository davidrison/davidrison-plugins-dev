package com.aonhewitt.portal.core.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class RemotingConstants {

	public static final String DEFAULT_REMOTE_HOST_NAME = "localhost";

	public static final int DEFAULT_REMOTE_HOST_PORT = 9080;

	public static boolean REMOTE_HOST_ENABLED = false;

	static {
		REMOTE_HOST_ENABLED = GetterUtil.getBoolean(
				PropsUtil.get("poc.remote.host.enabled"), false);
	}
}
