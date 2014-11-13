package com.liferay.portlet.digest.builder;

import com.liferay.portal.model.User;

import java.util.List;

public class DigestBuilderUtil {

	public static void buildInactiveUserDigest(long companyId, int frequency) throws Exception {
		_digestBuilder.buildInactiveUserDigest(companyId, frequency);
	}

	public static void buildDigest(long companyId, int frequency) throws Exception {
		_digestBuilder.buildDigest(companyId, frequency);
	}

	public static void processDigest(List<User> users, int frequency, String templateId) throws Exception {
		_digestBuilder.processDigest(users, frequency, templateId);
	}

	public static void sendBuildDigest(List<User> users, int frequency, String templateId) throws Exception {
		_digestBuilder.sendBuildDigest(users, frequency, templateId);
	}

	public void setDigestBuilder(DigestBuilder digestBuilder) {
		_digestBuilder = digestBuilder;
	}

	private static DigestBuilder _digestBuilder;
}