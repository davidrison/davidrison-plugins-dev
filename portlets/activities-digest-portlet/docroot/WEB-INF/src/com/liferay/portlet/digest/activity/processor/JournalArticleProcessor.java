package com.liferay.portlet.digest.activity.processor;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.DigestActivityConverter;
import com.liferay.portlet.digest.activity.DigestActivityType;
import com.liferay.portlet.digest.activity.converter.JournalArticleConverter;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;
import com.liferay.portlet.digest.model.Digest;
import com.liferay.portlet.digest.util.DigestConstants;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

import java.util.*;

public class JournalArticleProcessor extends BaseDigestActivityProcessor {

	@Override
	public Digest processActivity(Digest digest)
			throws Exception {

		addActivities(digest);

		return digest;
	}

	@Override
	protected void addActivities(Digest digest) throws Exception {

		Date startDate = digest.getConfiguration().getStartDate();
		Date endDate = digest.getConfiguration().getEndDate();

        DigestConfiguration digestConfiguration = digest.getConfiguration();

		DynamicQuery dynamicQuery =
				DynamicQueryFactoryUtil.forClass(
						com.liferay.portlet.journal.model.JournalArticle.class,
							getClass().getClassLoader());

		Map<String, DigestActivityType> activityTypesMap =
				digestConfiguration.getActivityTypesMap();

		DigestActivityType journalArticleActivityType = activityTypesMap.get(_CLASSNAME);

		Property createDateProperty = PropertyFactoryUtil.forName("createDate");

		Property modifiedDateProperty = PropertyFactoryUtil.forName("modifiedDate");

		if (journalArticleActivityType.getActionsAsList().contains(JournalArticleConverter.ADD_WEB_CONTENT) &&
				journalArticleActivityType.getActionsAsList().contains(JournalArticleConverter.UPDATE_WEB_CONTENT)) {
			dynamicQuery.add(
					RestrictionsFactoryUtil.disjunction().add(
							RestrictionsFactoryUtil.or(
									modifiedDateProperty.between(startDate, endDate),
									createDateProperty.between(startDate, endDate))));
		}
		else
		if (journalArticleActivityType.getActionsAsList().contains(JournalArticleConverter.ADD_WEB_CONTENT)) {
			dynamicQuery.add(
					RestrictionsFactoryUtil.disjunction().add(
							RestrictionsFactoryUtil.and(
									createDateProperty.eqProperty(modifiedDateProperty),
									createDateProperty.between(startDate, endDate))
					)
			);
		}
		else
		if (journalArticleActivityType.getActionsAsList().contains(JournalArticleConverter.UPDATE_WEB_CONTENT)) {
			dynamicQuery.add(
					RestrictionsFactoryUtil.disjunction().add(
							RestrictionsFactoryUtil.and(
									createDateProperty.neProperty(modifiedDateProperty),
									modifiedDateProperty.between(startDate, endDate))
					)
			);
		}
		else {
			return;
		}

		Property companyIdProperty = PropertyFactoryUtil.forName("companyId");

		dynamicQuery.add(
			companyIdProperty.eq(digest.getConfiguration().getCompanyId())
		);

		if (digest.getGroupId() > 0) {
			Property groupIdProperty = PropertyFactoryUtil.forName("groupId");

			// group id

			dynamicQuery.add(
					groupIdProperty.eq(digest.getGroupId())
			);
		}

		Property statusProperty = PropertyFactoryUtil.forName("status");

		dynamicQuery.add(
				statusProperty.eq(WorkflowConstants.STATUS_APPROVED)
		);

		List<JournalArticle> journalArticleList =
				JournalArticleLocalServiceUtil.dynamicQuery(
						dynamicQuery, 0, DigestConstants.MAX_ACTIVITIES);


		// digest activities map

		Map<String, List<DigestActivity>> digestActivitiesMap =
				new HashMap<String, List<DigestActivity>>();

        // digest activity types available

        List<DigestActivityType> digestActivityTypesList =
                digestConfiguration.getActivityTypesList();

        for (DigestActivityType digestActivityType : digestActivityTypesList) {
            if (Validator.isNull(
                    digestActivitiesMap.get(digestActivityType.getName()))) {

                digestActivitiesMap.put(
                        digestActivityType.getName(), new ArrayList<DigestActivity>());
            }
        }

		for (JournalArticle journalArticle : journalArticleList) {
			List<DigestActivity> digestActivitiesList =
					digestActivitiesMap.get(_CLASSNAME);

			if (Validator.isNull(digestActivitiesList)) {
				if (_log.isWarnEnabled()) {
					_log.warn("A Digest Activity was found but is not allowed for the current configuration, Journal Article " + _CLASSNAME);
				}

				continue;
			}

			DigestActivityConverter digestActivityConverter =
					DigestActivityFactoryUtil.getDigestActivityConverter(_CLASSNAME);

			DigestActivity digestActivity =
					digestActivityConverter.convert(journalArticle, digest.getUser());

			if (Validator.isNotNull(digestActivity)) {
				digestActivitiesList.add(digestActivity);
			}
		}

		// merge activities

		digest.setActivities(
				mergeActivities(
						digest, digestActivitiesMap));
	}

	@Override
	protected long getClassNameId() {
		return ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class);
	}

	private static final String _CLASSNAME = "com.liferay.portlet.journal.model.JournalArticle";

	private static final Log _log = LogFactoryUtil.getLog(JournalArticleProcessor.class);

}