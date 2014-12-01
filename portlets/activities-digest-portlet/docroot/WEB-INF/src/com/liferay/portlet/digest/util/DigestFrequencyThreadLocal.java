package com.liferay.portlet.digest.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;

public class DigestFrequencyThreadLocal {

	public static Integer getDigestFrequency() {
		Integer digestFrequency = _digestFrequency.get();

		if (_log.isDebugEnabled()) {
			_log.debug("getDigestFrequency " + digestFrequency);
		}

		return digestFrequency;
	}

	public static void setDigestFrequency(int digestFrequency) {
		setDigestFrequency(Integer.valueOf(digestFrequency));
	}

	public static void setDigestFrequency(Integer digestFrequency) {
		if (_log.isDebugEnabled()) {
			_log.debug("setDigestFrequency " + digestFrequency);
		}

		if (digestFrequency >= 0) {
			_digestFrequency.set(digestFrequency);
		}
		else {
			_digestFrequency.set(DigestConstants.FREQUENCY_NONE);
		}
	}
	private static ThreadLocal<Integer> _digestFrequency =
			new AutoResetThreadLocal<Integer>(
					DigestFrequencyThreadLocal.class + "._digestFrequency", DigestConstants.FREQUENCY_NONE);

	private static Log _log = LogFactoryUtil.getLog(DigestFrequencyThreadLocal.class);

}
