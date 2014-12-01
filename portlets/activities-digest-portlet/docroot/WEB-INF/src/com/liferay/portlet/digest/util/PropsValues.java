package com.liferay.portlet.digest.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class PropsValues {

	public static final String DEFAULT_REGULAR_THEME_ID = PropsUtil.get(
			com.liferay.portal.kernel.util.PropsKeys.DEFAULT_REGULAR_THEME_ID);

	public static final boolean DIGEST_DEVELOPER_MODE = GetterUtil.getBoolean(
			PropsUtil.get(PropsKeys.DIGEST_DEVELOPER_MODE), false);

	public static final int DIGEST_ACTIVITY_SUMMARY_LENGTH =
			GetterUtil.getInteger(
					PropsUtil.get(PropsKeys.DIGEST_ACTIVITY_SUMMARY_LENGTH),
						DigestConstants.DIGEST_ACTIVITY_DEFAULT_SUMMARY_LENGTH);

	public static final int DIGEST_ACTIVITY_USER_BLOCK_SIZE =
			GetterUtil.getInteger(
					PropsUtil.get(PropsKeys.DIGEST_ACTIVITY_USER_BLOCK_SIZE), DigestConstants.DIGEST_ACTIVITY_DEFAULT_USER_BLOCK_SIZE);

	public static final int DIGEST_ACTIVITY_MAX_CONCURRENT_USER_PROCESS =
			GetterUtil.getInteger(
					PropsUtil.get(PropsKeys.DIGEST_ACTIVITY_MAX_CONCURRENT_USER_PROCESS), 3);

	public static final String DIGEST_DATE_FORMAT =
			GetterUtil.getString(
					PropsUtil.get(PropsKeys.DIGEST_DATE_FORMAT), "MM-dd-yyyy");

	public static final String DIGEST_EMAIL_FROM_NAME = PropsUtil.get(PropsKeys.DIGEST_EMAIL_FROM_NAME);

	public static final String DIGEST_EMAIL_FROM_ADDRESS = PropsUtil.get(PropsKeys.DIGEST_EMAIL_FROM_ADDRESS);

	public static final String DIGEST_ACTIVITY_TEMPLATE_ID = PropsUtil.get(PropsKeys.DIGEST_ACTIVITY_TEMPLATE_ID);

	public static final String DIGEST_ACTIVITY_INACTIVE_USER_TEMPLATE_ID = PropsUtil.get(PropsKeys.DIGEST_ACTIVITY_INACTIVE_USER_TEMPLATE_ID);

	public static final int DIGEST_ACTIVITY_INACTIVE_USER_NUMBER_DAYS = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.DIGEST_ACTIVITY_INACTIVE_USER_NUMBER_DAYS));

	public static final int DIGEST_ACTIVITY_INACTIVE_USER_MAX_NUMBER_EMAILS = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.DIGEST_ACTIVITY_INACTIVE_USER_MAX_NUMBER_EMAILS), 3);

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
