package com.liferay.portlet.digest.builder;

import com.liferay.portal.model.User;

import java.util.List;

public interface DigestBuilder {

	public void buildDigest(long companyId, int frequency) throws Exception;

	public void buildInactiveUserDigest(long companyId, int frequency) throws Exception;

	public void processDigest(List<User> users, int frequency, String templateId) throws Exception;

	public void sendBuildDigest(List<User> users, int frequency, String templateId) throws Exception;
}