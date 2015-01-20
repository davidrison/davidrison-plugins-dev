package com.liferay.portlet.digest.util;

import com.liferay.portal.kernel.util.GetterUtil;

public class PropsValues {

	public static final String DEFAULT_REGULAR_THEME_ID = PropsUtil.get(
			com.liferay.portal.kernel.util.PropsKeys.DEFAULT_REGULAR_THEME_ID);

	public static final boolean DIGEST_DEVELOPER_MODE = GetterUtil.getBoolean(
			com.liferay.portlet.digest.util.PropsUtil.get(PropsKeys.DIGEST_DEVELOPER_MODE), false);

	public static final int DIGEST_ACTIVITY_SUMMARY_LENGTH =
			GetterUtil.getInteger(
					com.liferay.portlet.digest.util.PropsUtil.get(PropsKeys.DIGEST_ACTIVITY_SUMMARY_LENGTH),
						DigestConstants.DIGEST_ACTIVITY_DEFAULT_SUMMARY_LENGTH);

	public static final int DIGEST_ACTIVITY_MAX_CONCURRENT_USER_PROCESS =
			GetterUtil.getInteger(
					com.liferay.portlet.digest.util.PropsUtil.get(PropsKeys.DIGEST_ACTIVITY_MAX_CONCURRENT_USER_PROCESS), DigestConstants.MAX_CONCURRENT_USER_PROCESS);

	public static final String DIGEST_DATE_FORMAT =
			GetterUtil.getString(
					com.liferay.portlet.digest.util.PropsUtil.get(PropsKeys.DIGEST_DATE_FORMAT), "MM-dd-yyyy");

	public static final String DIGEST_EMAIL_FROM_NAME = com.liferay.portlet.digest.util.PropsUtil.get(PropsKeys.DIGEST_EMAIL_FROM_NAME);

	public static final String DIGEST_EMAIL_FROM_ADDRESS = com.liferay.portlet.digest.util.PropsUtil.get(PropsKeys.DIGEST_EMAIL_FROM_ADDRESS);

	public static final String DIGEST_ACTIVITY_TEMPLATE_ID = GetterUtil.get(
			com.liferay.portlet.digest.util.PropsUtil.get(PropsKeys.DIGEST_ACTIVITY_TEMPLATE_ID), DigestConstants.DIGEST_ACTIVITY_DEFAULT_TEMPLATE_ID);

	public static final String DIGEST_ACTIVITY_INACTIVE_USER_TEMPLATE_ID = GetterUtil.get(
			com.liferay.portlet.digest.util.PropsUtil.get(PropsKeys.DIGEST_ACTIVITY_INACTIVE_USER_TEMPLATE_ID), DigestConstants.DIGEST_ACTIVITY_DEFAULT_INACTIVE_USER_TEMPLATE_ID);

	public static final int DIGEST_ACTIVITY_INACTIVE_USER_NUMBER_DAYS = GetterUtil.getInteger(
			com.liferay.portlet.digest.util.PropsUtil.get(PropsKeys.DIGEST_ACTIVITY_INACTIVE_USER_NUMBER_DAYS), DigestConstants.MAX_INACTIVE_NUMBER_DAYS);

	public static final int DIGEST_ACTIVITY_INACTIVE_USER_MAX_NUMBER_EMAILS = GetterUtil.getInteger(
			com.liferay.portlet.digest.util.PropsUtil.get(PropsKeys.DIGEST_ACTIVITY_INACTIVE_USER_MAX_NUMBER_EMAILS), DigestConstants.MAX_INACTIVE_NUMBER_EMAILS);

	public static final boolean PERMISSIONS_VIEW_DYNAMIC_INHERITANCE = GetterUtil.getBoolean(
			PropsUtil.get(
					com.liferay.portal.kernel.util.PropsKeys.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE));

	public static String PORTAL_CTX;
	public static String PORTAL_PROXY_PATH;

	static {
		PORTAL_CTX = GetterUtil.getString(
				PropsUtil.get(
						com.liferay.portal.kernel.util.PropsKeys.PORTAL_CTX
				)
		);

		PORTAL_PROXY_PATH = GetterUtil.getString(
				PropsUtil.get(
						com.liferay.portal.kernel.util.PropsKeys.PORTAL_PROXY_PATH
				)
		);
	}
}
