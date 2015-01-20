package com.liferay.portlet.digest.activity.util.comparator;

import com.liferay.portlet.digest.activity.DigestActivityType;

import java.util.Comparator;

public class DigestActivityTypeComparator implements Comparator<DigestActivityType> {

	public DigestActivityTypeComparator() {}

	@Override
	public int compare(DigestActivityType digestActivityType1, DigestActivityType digestActivityType2) {
		int value = 0;

		try {
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
