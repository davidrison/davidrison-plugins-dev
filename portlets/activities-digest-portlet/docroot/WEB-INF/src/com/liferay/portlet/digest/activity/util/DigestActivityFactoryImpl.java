package com.liferay.portlet.digest.activity.util;

import com.liferay.portal.model.BaseModel;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.DigestActivityType;
import com.liferay.portlet.digest.activity.impl.DigestActivityImpl;
import com.liferay.portlet.digest.activity.impl.DigestActivityTypeImpl;

public class DigestActivityFactoryImpl implements DigestActivityFactory {

	public DigestActivity getDigestActivity(BaseModel model) throws Exception {
		return new DigestActivityImpl();
	}

	public DigestActivity getDigestActivity(String className) throws Exception {
		return new DigestActivityImpl();
	}

	public DigestActivityType getDigestActivityType(String className) throws Exception {
		return new DigestActivityTypeImpl(0, className, new int[0]);
	}
}