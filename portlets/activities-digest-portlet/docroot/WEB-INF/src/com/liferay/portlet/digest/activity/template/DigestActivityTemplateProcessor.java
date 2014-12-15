package com.liferay.portlet.digest.activity.template;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.velocity.VelocityContext;
import com.liferay.portal.kernel.velocity.VelocityEngineUtil;
import com.liferay.portal.kernel.velocity.VelocityVariablesUtil;
import com.liferay.portal.model.Account;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;
import com.liferay.portlet.digest.activity.util.comparator.DigestActivityTypeComparator;
import com.liferay.portlet.digest.model.Digest;
import com.liferay.portlet.digest.util.DigestConstants;
import com.liferay.portlet.digest.util.DigestFrequencyThreadLocal;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;
import com.liferay.portlet.social.model.SocialRelation;

import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.util.*;

public class DigestActivityTemplateProcessor {

	public static String mergeDigest(long companyId, String templateId, List<Digest> digests) throws Exception {

		// merge

		VelocityContext velocityContext = getVelocityContext(companyId, templateId, digests);

		Group globalGroup = DigestHelperUtil.getGlobalGroup(companyId);

		JournalTemplate journalTemplate = JournalTemplateLocalServiceUtil.getTemplate(
				globalGroup.getGroupId(), templateId, true);

		String xsl = journalTemplate.getXsl();

		Writer writer = new StringWriter();

		VelocityEngineUtil.mergeTemplate(
				templateId, xsl, velocityContext, writer);

		return writer.toString();

	}

	protected static VelocityContext getVelocityContext(long companyId, String templateId, List<Digest> digests) throws Exception {

		//DigestConfiguration digestConfiguration = null;

		Company company = CompanyLocalServiceUtil.getCompany(companyId);

		Account account = company.getAccount();

		Group group = GroupLocalServiceUtil.getCompanyGroup(companyId);

		Locale locale = LocaleUtil.getDefault();

		DateFormat simpleDateFormat =
				DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

		Date startDate = new Date();
		Date endDate = new Date();

		String portalURL = DigestHelperUtil.getPortalURL(group.getGroupId());
		String pathMain = DigestHelperUtil.getPathMain();

		String url1 = "mailto:#";
		String url2 = DigestHelperUtil.getPortalURL(group.getGroupId());

		String footerText = LanguageUtil.format(
				locale, "footer-text", new Object[]{url1, "#", url2});

		String digestTitle = account.getName();

		if (Validator.isNotNull(digests) &&
			digests.size() > 0) {

			// sort

		/*	for (Digest digest : digests) {
				Map<String, List<DigestActivity>> digestActivitiesMap =
						digest.getActivities();

				Map<String, List<DigestActivity>> treeMap = new TreeMap<String, List<DigestActivity>>(
						new DigestActivityTypeComparator());

//				treeMap.putAll(digestActivitiesMap);

				for (String digestActivity : digestActivitiesMap.keySet()) {
					List<DigestActivity> digestActivities = digestActivitiesMap.get(digestActivity);

					treeMap.put(digestActivity, digestActivities);
				}

				digest.setActivities(treeMap);
			}
*/
			// digest

			Digest digest = digests.get(0);

			// user

			User user = digest.getUser();

			startDate = digest.getConfiguration().getStartDate();
			endDate = digest.getConfiguration().getEndDate();

			group = digests.get(0).getGroup();

			portalURL = DigestHelperUtil.getPortalURL(group.getGroupId());

			url1 = "mailto: " + user.getEmailAddress();
			url2 = user.getDisplayURL(DigestHelperUtil.getPortalURL(group.getGroupId()), DigestHelperUtil.getPathMain());

			locale = user.getLocale();

			int currentFrequency = DigestFrequencyThreadLocal.getDigestFrequency();

			String digestFrequency = LanguageUtil.format(locale, "daily-snapshot", null);

			if (currentFrequency == DigestConstants.FREQUENCY_WEEKLY) {
				digestFrequency = LanguageUtil.format(locale, "weekly-snapshot", null);
			}

			String digestTitleKey = "digest-title-daily";
			if (currentFrequency == DigestConstants.FREQUENCY_DAILY) {
				digestTitleKey = "digest-title-daily";
			}
			else {
				digestTitleKey = "digest-title-weekly";
			}

			digestTitle =
					LanguageUtil.format(
							locale, digestTitleKey,
							new Object[]{
									account.getName(),
									digestFrequency,
									simpleDateFormat.format(startDate),
									simpleDateFormat.format(endDate)
							}
					);

			footerText =
					LanguageUtil.format(
							locale, "footer-text", new Object[]{url1, user.getEmailAddress(), url2});

		}
		else {
			if (_log.isWarnEnabled()) {
				_log.warn("Invalid Digest Array!");
			}
		}

		VelocityContext velocityContext =
				VelocityEngineUtil.getEmptyContext();

		VelocityVariablesUtil.insertHelperUtilities(velocityContext, null);

		velocityContext.put("account", account);
		velocityContext.put("company", company);
		velocityContext.put("group", group);
		velocityContext.put("locale", locale);
		velocityContext.put("mainPath", pathMain);
		velocityContext.put("portalURL", portalURL);

		velocityContext.put("digestActivityFactory", DigestActivityFactoryUtil.class);
		velocityContext.put("digestTitle", digestTitle);
		velocityContext.put("digestHelperUtil", DigestHelperUtil.class);
		velocityContext.put("digests", digests);
		velocityContext.put("contacts", getContactActivityList(digests));

		velocityContext.put("footerText", footerText);

		velocityContext.put("startDate", startDate);
		velocityContext.put("endDate", endDate);

		return velocityContext;
	}

	protected static List<DigestActivity> getContactActivityList(List<Digest> digests) throws Exception {
		List<DigestActivity> contactActivityList = new ArrayList<DigestActivity>();

		for (Digest digest : digests) {
			Map<String, List<DigestActivity>> digestActivitiesMap =
					digest.getActivities();

			for (String digestActivityTypeKey : digestActivitiesMap.keySet()) {
				List<DigestActivity> digestActivities =
						digestActivitiesMap.get(digestActivityTypeKey);

				for (DigestActivity digestActivity : digestActivities) {
					String digestActivityTypeName = digestActivity.getActivityType().getName();

					if (digestActivityTypeName.equals(SocialRelation.class.getName()) &&
							!contactActivityList.contains(digestActivity)) {

						if (digestActivity.getType() == 1) {
							if (digestActivity.getExtraDataAsJSON().getString("userId1").equals(""+digest.getUserId())) {
								contactActivityList.add(digestActivity);
							}
						}
						else {
							contactActivityList.add(digestActivity);
						}
					}
				}
			}
		}

		return contactActivityList;
	}

	private static final String _SERVLET_SEPARATOR = "_SERVLET_CONTEXT_";

	private static final Log _log = LogFactoryUtil.getLog(DigestActivityTemplateProcessor.class);

}
