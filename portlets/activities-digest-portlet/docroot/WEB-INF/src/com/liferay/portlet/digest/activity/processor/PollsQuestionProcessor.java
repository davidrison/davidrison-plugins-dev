package com.liferay.portlet.digest.activity.processor;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.DigestActivityConverter;
import com.liferay.portlet.digest.activity.DigestActivityType;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;
import com.liferay.portlet.digest.model.Digest;
import com.liferay.portlet.digest.util.DigestConstants;
import com.liferay.portlet.polls.model.PollsQuestion;
import com.liferay.portlet.polls.service.PollsQuestionLocalServiceUtil;

import java.util.*;

public class PollsQuestionProcessor extends BaseDigestActivityProcessor {

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
						PollsQuestion.class,
							getClass().getClassLoader());

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

		Property modifiedDateProperty = PropertyFactoryUtil.forName("modifiedDate");

		dynamicQuery.add(
				modifiedDateProperty.between(startDate, endDate)
		);


		Property expiredDateProperty = PropertyFactoryUtil.forName("expirationDate");

		dynamicQuery.add(
				RestrictionsFactoryUtil.or(expiredDateProperty.isNull(), expiredDateProperty.gt(startDate))
		);

		List<PollsQuestion> pollsQuestionList =
				PollsQuestionLocalServiceUtil.dynamicQuery(
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

		for (PollsQuestion pollsQuestion : pollsQuestionList) {
			List<DigestActivity> digestActivitiesList =
					digestActivitiesMap.get(_CLASSNAME);

			if (Validator.isNull(digestActivitiesList)) {
				if (_log.isWarnEnabled()) {
					_log.warn("A Digest Activity was found but is not allowed for the current configuration, Polls Question " + _CLASSNAME);
				}

				continue;
			}

			DigestActivityConverter digestActivityConverter =
					DigestActivityFactoryUtil.getDigestActivityConverter(_CLASSNAME);

			DigestActivity digestActivity =
					digestActivityConverter.convert(pollsQuestion, digest.getUser());

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
		return ClassNameLocalServiceUtil.getClassNameId(PollsQuestion.class);
	}

    private static final String _CLASSNAME = "com.liferay.portlet.polls.model.PollsQuestion";

    private static final Log _log = LogFactoryUtil.getLog(PollsQuestionProcessor.class);

}