package com.liferay.portlet.digest.activity.util;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.DigestActivityConverter;
import com.liferay.portlet.digest.activity.DigestActivityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DigestActivityFactoryUtil {

	public static DigestActivity getDigestActivity(BaseModel model) throws Exception {
		return _digestActivityFactory.getDigestActivity(model);
	}

	public static DigestActivity getDigestActivity(String className) throws Exception {
		return _digestActivityFactory.getDigestActivity(className);
	}

	public static DigestActivityConverter getDigestActivityConverter(String className)
			throws Exception {
		return _digestActivityConverters.get(className);
	}

	public static List<String> getDigestActivityTypeNames() {
		return _digestActivityTypeNames;
	}

	public static DigestActivityType getDigestActivityType(String className) throws Exception {
		return _digestActivityFactory.getDigestActivityType(className);
	}

	public static List<Integer> getDigestActivityTypeActions(String className) throws Exception {
		DigestActivityConverter digestActivityConverter =
				_digestActivityConverters.get(className);

		if (Validator.isNotNull(digestActivityConverter)) {
			return digestActivityConverter.getActions();
		}

		return new ArrayList<Integer>();
	}

	public void setDigestActivityFactory(DigestActivityFactory digestActivityFactory) {
		_digestActivityFactory = digestActivityFactory;
	}

	public void setDigestActivityConverters(Map<String, DigestActivityConverter> digestActivityConverters) {
		_digestActivityConverters = digestActivityConverters;
	}

	public void setDigestActivityTypeNames(List<String> digestActivityTypeNames) {
		_digestActivityTypeNames = digestActivityTypeNames;
	}

	private static Map<String, DigestActivityConverter> _digestActivityConverters;

	private static List<String> _digestActivityTypeNames;

	private static DigestActivityFactory _digestActivityFactory;
}