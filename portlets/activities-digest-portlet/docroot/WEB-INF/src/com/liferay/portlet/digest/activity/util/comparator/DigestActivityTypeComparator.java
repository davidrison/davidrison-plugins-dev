package com.liferay.portlet.digest.activity.util.comparator;

import com.liferay.portlet.digest.activity.DigestActivityType;

import java.util.Comparator;

public class DigestActivityTypeComparator implements Comparator<DigestActivityType> {
	@Override
	public int compare(DigestActivityType o1, DigestActivityType o2) {
		int value = 0;

		if (o1.getOrder() < o2.getOrder()) {
			value = -1;
		}
		else if (o1.getOrder() > o2.getOrder()) {
			value = 1;
		}

		return value;
	}
}
