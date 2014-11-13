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
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.social.model.SocialRelation;
import com.liferay.portlet.social.model.SocialRelationConstants;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocialConnectionsProcessor extends BaseDigestActivityProcessor {

	@Override
	public Digest processActivity(Digest digest)
			throws Exception {

		addActivities(digest);

		return digest;
	}

	@Override
	protected void addActivities(Digest digest) throws Exception {

		if (!DigestHelperUtil.isPluginPackageInstalled(_CONTEXT_NAME)) {
			if (_log.isInfoEnabled()) {
				_log.info("The Social Office Activities Hook is not deployed, skipping connections processing.");
			}

			return;
		}

		DigestConfiguration digestConfiguration = digest.getConfiguration();

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

		List<SocialRelation> socialRelationList = getSocialRelations(digest.getConfiguration());

		for (SocialRelation socialRelation : socialRelationList) {
			List<DigestActivity> digestActivitiesList =
					digestActivitiesMap.get(_CLASSNAME);

			if (Validator.isNull(digestActivitiesList)) {
				if (_log.isWarnEnabled()) {
					_log.warn("A Digest Activity was found but is not allowed for the current configuration, Social Relation " + _CLASSNAME);
				}

				continue;
			}

			DigestActivityConverter digestActivityConverter =
					DigestActivityFactoryUtil.getDigestActivityConverter(_CLASSNAME);

			DigestActivity digestActivity =
					digestActivityConverter.convert(socialRelation, digest.getUser());

			if (Validator.isNotNull(digestActivity)) {
				digestActivitiesList.add(digestActivity);

				digestActivity.setLinkDisplay(digestActivityConverter.getLinkDisplay(digestActivity, digest.getUser()));
			}
		}

		// merge activities

		digest.setActivities(
				mergeActivities(
						digest, digestActivitiesMap));
	}

	@Override
	protected long getClassNameId() {
		return ClassNameLocalServiceUtil.getClassNameId(SocialRelation.class);
	}

	protected List<SocialRelation> getSocialRelations(DigestConfiguration digestConfiguration) throws Exception {
		DynamicQuery dynamicQuery =
				DynamicQueryFactoryUtil.forClass(SocialRelation.class);

		Property companyIdProperty = PropertyFactoryUtil.forName("companyId");

		dynamicQuery.add(
				companyIdProperty.eq(digestConfiguration.getCompanyId())
		);

		Property userId1Property = PropertyFactoryUtil.forName("userId1");
		Property userId2Property = PropertyFactoryUtil.forName("userId2");

		dynamicQuery.add(
				RestrictionsFactoryUtil.or(
						userId1Property.eq(digestConfiguration.getUserId()),
						userId2Property.eq(digestConfiguration.getUserId())
				)
		);

		Property typeProperty = PropertyFactoryUtil.forName("type");

		dynamicQuery.add(
				typeProperty.in(new Integer[]{SocialRelationConstants.TYPE_BI_CONNECTION, SocialRelationConstants.TYPE_UNI_FOLLOWER})
		);

		Property createDateProperty = PropertyFactoryUtil.forName("createDate");

		dynamicQuery.add(
				createDateProperty.between(digestConfiguration.getStartDate().getTime(), digestConfiguration.getEndDate().getTime())
		);

		return SocialRelationLocalServiceUtil.dynamicQuery(
					dynamicQuery, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

	}

	private static final String _CLASSNAME = "com.liferay.portlet.social.model.SocialRelation";

	private static final String _CONTEXT_NAME = "so-activities-hook";

	private static final Log _log = LogFactoryUtil.getLog(SocialConnectionsProcessor.class);

}