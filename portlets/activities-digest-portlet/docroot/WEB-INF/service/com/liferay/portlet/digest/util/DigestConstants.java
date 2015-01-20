package com.liferay.portlet.digest.util;

public class DigestConstants {

	public static final int DAY_IN_HOURS = 24;

	public static final int DIGEST_ACTIVITY_DEFAULT_SUMMARY_LENGTH = 250;

	public static final int DIGEST_ACTIVITY_DEFAULT_USER_BLOCK_SIZE = 100;

	public static final String DIGEST_ACTIVITY_DEFAULT_TEMPLATE_ID = "ACTIVITYDIGEST";

	public static final String DIGEST_ACTIVITY_DEFAULT_INACTIVE_USER_TEMPLATE_ID = "INACTIVEUSERACTIVITYDIGEST";

	public static final String DIGEST_SERVLET_CONTEXT = "activities-digest-portlet";

	public static final int DIGEST_CONFIGURATION_USER = 1001;

	public static final int DIGEST_CONFIGURATION_SITE = 1002;

	public static final int DIGEST_CONFIGURATION_PORTAL = 1003;

	public static final int FREQUENCY_NONE = 0;

	public static final int FREQUENCY_DAILY = 1;

	public static final int FREQUENCY_WEEKLY = 2;

	public static final int MAX_ACTIVITIES = 50;

	public static final int MAX_INACTIVE_NUMBER_DAYS = 10;

	public static final int MAX_INACTIVE_NUMBER_EMAILS = 3;

	public static final int MAX_CONCURRENT_USER_PROCESS = 3;

	public static final String PREFERENCE_DIGEST_INACTIVE_USER_NUMBER_DAYS = "digest-inactive-user-number-days";

	public static final String PREFERENCE_DIGEST_INACTIVE_USER_MAX_NUMBER_EMAILS = "digest-inactive-user-max-number-emails";

	public static final int WEEK_IN_DAYS = 7;

}