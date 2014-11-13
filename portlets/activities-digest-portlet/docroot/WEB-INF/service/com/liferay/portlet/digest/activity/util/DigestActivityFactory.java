package com.liferay.portlet.digest.activity.util;

import com.liferay.portal.model.BaseModel;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.DigestActivityType;

public interface DigestActivityFactory {
	public DigestActivity getDigestActivity(BaseModel model) throws Exception;

	public DigestActivity getDigestActivity(String className) throws Exception;

	public DigestActivityType getDigestActivityType(String className) throws Exception;
}