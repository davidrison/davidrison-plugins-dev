package com.liferay.portlet.digest.activity.processor;

import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.DigestActivityConverter;
import com.liferay.portlet.digest.activity.DigestActivityType;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;
import com.liferay.portlet.digest.activity.parser.DigestActivityProcessor;
import com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil;
import com.liferay.portlet.digest.model.Digest;
import com.liferay.portlet.digest.util.DigestConstants;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseDigestActivityProcessor implements DigestActivityProcessor {

	@Override
	public Digest processActivity(Digest digest)
		throws Exception {

		addSocialActivities(digest);

		addActivities(digest);

		return digest;
	}

	protected DynamicQuery addActivityTypeActionFilter(
			DynamicQuery dynamicQuery, Map<Long, List<Integer>> classNameIdTypesMap) throws Exception {

		if (Validator.isNull(classNameIdTypesMap) ||
				classNameIdTypesMap.size() == 0) {
			return dynamicQuery;
		}
		else {
			Property typeProperty = PropertyFactoryUtil.forName("type");
			Property classNameIdProperty = PropertyFactoryUtil.forName("classNameId");

			Disjunction disjunction = null;

			for (Long classNameId : classNameIdTypesMap.keySet()) {
				List<Integer> types = classNameIdTypesMap.get(classNameId);

				Criterion restriction =
						RestrictionsFactoryUtil.and(
								typeProperty.in(types.toArray(new Integer[0])), classNameIdProperty.eq(classNameId));

				if (Validator.isNull(disjunction)) {
					disjunction = RestrictionsFactoryUtil.disjunction();
				}

				disjunction.add(RestrictionsFactoryUtil.disjunction().add(restriction));
			}

			if (Validator.isNotNull(disjunction)) {
				dynamicQuery.add(
						disjunction
				);
			}
		}

		return dynamicQuery;
	}

	protected void addSocialActivities(Digest digest)
		throws Exception {

		DigestConfiguration digestConfiguration = digest.getConfiguration();

		List<SocialActivity> socialActivityList = null;

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
				SocialActivity.class, getClass().getClassLoader());

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

		// class name ids

		List<Long> classNameIds =
				digestConfiguration.getActivityTypeIds();

		// filter activity types actions/social activity types

		Map<Long, List<Integer>> classNameIdTypesMap = filterActions(classNameIds, digestConfiguration);

		dynamicQuery = addActivityTypeActionFilter(dynamicQuery, classNameIdTypesMap);

		// date range

		Property createDateProperty =
				PropertyFactoryUtil.forName("createDate");

		dynamicQuery.add(
				createDateProperty.between(
						digest.getConfiguration().getStartDate().getTime(),
						digest.getConfiguration().getEndDate().getTime())
		);

		socialActivityList =
				SocialActivityLocalServiceUtil.dynamicQuery(
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

		for (SocialActivity socialActivity : socialActivityList) {

			List<DigestActivity> digestActivitiesList =
					digestActivitiesMap.get(socialActivity.getClassName());

			if (Validator.isNull(digestActivitiesList)) {
				if (_log.isWarnEnabled()) {
					_log.warn("A Digest Activity was found but is not allowed for the current configuration, Social Activity " + socialActivity.getClassName());
				}

				continue;
			}

			DigestActivityConverter digestActivityConverter =
					DigestActivityFactoryUtil.getDigestActivityConverter(socialActivity.getClassName());

			DigestActivity digestActivity =
					digestActivityConverter.convert(socialActivity, digest.getUser());

			if (Validator.isNotNull(digestActivity)) {
				digestActivitiesList.add(digestActivity);
			}
		}

		// digest activitiesMap

		digest.setActivities(
				mergeActivities(
						digest, digestActivitiesMap));
	}

	protected Map<Long, List<Integer>> filterActions(
			List<Long> classNameIds, DigestConfiguration digestConfiguration)
		throws Exception {

		// class name id to action id/type
		Map<Long, List<Integer>> filteredActivityTypes = new HashMap<Long, List<Integer>>();

		for (Long classNameId : classNameIds) {
			ClassName className = ClassNameLocalServiceUtil.getClassName(classNameId);

			Map<String, DigestActivityType> digestActivityTypesMap =
					digestConfiguration.getActivityTypesMap();

			DigestActivityType classAsDigestActivityType =
					digestActivityTypesMap.get(className.getClassName());

			List<Integer> actions =
					classAsDigestActivityType.getActionsAsList();

			if (Validator.isNotNull(actions) &&
				actions.size() > 0) {

				// do not continserIde with query if no actions are configured

				filteredActivityTypes.put(classNameId, actions);
			}
		}

		return filteredActivityTypes;
	}

	protected Map<String, List<DigestActivity>> mergeActivities(
			Digest digest, Map<String, List<DigestActivity>> activitiesMap) throws Exception {

		if (Validator.isNull(digest)) {
			if (_log.isWarnEnabled()) {
				_log.warn("Digest is null");
			}
		}

		Map<String, List<DigestActivity>> digestActivitiesMap =
				digest.getActivities();

		if (Validator.isNull(digestActivitiesMap)) {
			return activitiesMap;
		}

		for (String activityKey : activitiesMap.keySet()) {
			List<DigestActivity> activities =
					activitiesMap.get(activityKey);

			List<DigestActivity> digestActivities =
					digestActivitiesMap.get(activityKey);

			if (Validator.isNull(digestActivities)) {
				digestActivitiesMap.put(activityKey, activities);
			}
			else {
				for (DigestActivity activity : activities) {
					boolean found = false;

					for (DigestActivity digestActivity : digestActivities) {
						if ((Validator.isNotNull(digestActivity.getUuid()) && Validator.isNotNull(activity.getUuid())) &&
							digestActivity.getUuid().equals(activity.getUuid())) {
							found = true;
							break;
						}
					}

					if (!found) {
						if (Validator.isNotNull(digestActivities)) {
							digestActivities.add(activity);
						}
					}
				}
			}
		}

		return digestActivitiesMap;
	}

	protected abstract void addActivities(Digest digest)
		throws Exception;

	protected abstract long getClassNameId();

	private static final Log _log = LogFactoryUtil.getLog(BaseDigestActivityProcessor.class);

}