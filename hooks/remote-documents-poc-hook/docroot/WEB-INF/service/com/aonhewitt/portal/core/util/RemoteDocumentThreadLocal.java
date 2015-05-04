package com.aonhewitt.portal.core.util;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

public class RemoteDocumentThreadLocal {

	public static boolean isRemote() {
		return _remote.get().booleanValue();
	}

	public static void setRemote(boolean remote) {
		_remote.set(remote);
	}

	private static ThreadLocal<Boolean> _remote =
			new AutoResetThreadLocal<Boolean>(
					RemoteDocumentThreadLocal.class + "._remote", true);
}
