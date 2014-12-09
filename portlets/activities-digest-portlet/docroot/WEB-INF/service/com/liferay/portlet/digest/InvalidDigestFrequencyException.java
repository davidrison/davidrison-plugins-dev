package com.liferay.portlet.digest;

import com.liferay.portal.kernel.exception.PortalException;

public class InvalidDigestFrequencyException extends PortalException {

	public InvalidDigestFrequencyException() {
		super();
	}

	public InvalidDigestFrequencyException(String msg) {
		super(msg);
	}

	public InvalidDigestFrequencyException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public InvalidDigestFrequencyException(Throwable cause) {
		super(cause);
	}
}
