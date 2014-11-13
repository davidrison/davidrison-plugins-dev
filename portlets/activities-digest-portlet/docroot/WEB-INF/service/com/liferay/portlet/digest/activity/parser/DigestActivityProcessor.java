package com.liferay.portlet.digest.activity.parser;

import com.liferay.portlet.digest.model.Digest;

public interface DigestActivityProcessor {

	public Digest processActivity(Digest digest)
			throws Exception;
}