package com.liferay.portlet.digest.activity.util.comparator;

import com.liferay.portlet.digest.activity.DigestActivityType;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;

import java.util.Comparator;

public class DigestActivityTypeComparator implements Comparator<String> {

	public DigestActivityTypeComparator() {}

	@Override
	public int compare(String o1, String o2) {
		int value = 0;

		try {
			DigestActivityType digestActivityType1 =
					DigestActivityFactoryUtil.getDigestActivityType(o1);

			DigestActivityType digestActivityType2 =
					DigestActivityFactoryUtil.getDigestActivityType(o2);

			if (digestActivityType1.getOrder() < digestActivityType2.getOrder()) {
				value = -1;
			}
			else if (digestActivityType1.getOrder() > digestActivityType2.getOrder()) {
				value = 1;
			}

		}
		catch (Throwable t) {

		}

		return value;
	}
}
