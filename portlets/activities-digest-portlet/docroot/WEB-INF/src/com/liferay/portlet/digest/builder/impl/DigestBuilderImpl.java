package com.liferay.portlet.digest.builder.impl;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.DigestActivityType;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;
import com.liferay.portlet.digest.activity.model.UserDigestConfiguration;
import com.liferay.portlet.digest.activity.parser.DigestActivityProcessor;
import com.liferay.portlet.digest.activity.service.DigestConfigurationLocalServiceUtil;
import com.liferay.portlet.digest.activity.service.DigestLocalServiceUtil;
import com.liferay.portlet.digest.activity.service.UserDigestConfigurationLocalServiceUtil;
import com.liferay.portlet.digest.activity.template.DigestActivityTemplateProcessor;
import com.liferay.portlet.digest.builder.DigestBuilder;
import com.liferay.portlet.digest.model.Digest;
import com.liferay.portlet.digest.model.impl.DigestImpl;
import com.liferay.portlet.digest.util.DigestConstants;
import com.liferay.portlet.digest.util.DigestHelperUtil;
import com.liferay.portlet.digest.util.PropsValues;
import org.apache.commons.lang.time.StopWatch;

import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class DigestBuilderImpl implements DigestBuilder {

	public void buildDigest(long companyId, int frequency) throws Exception {
		if (!_digestRunning) {
			synchronized (_digestLock) {
				try {
					if (!_digestRunning) {
						_digestRunning = true;

						StopWatch stopWatch = null;

						if (_log.isDebugEnabled()) {
							stopWatch = new StopWatch();

							stopWatch.start();

							_log.debug("Building Digest...");
						}

						DigestLocalServiceUtil.processUsers(companyId, frequency);

						if (_log.isInfoEnabled()) {
							_log.info("Digest Builder has completed execution in " + stopWatch.getTime() + " ms.");
						}
					}
				}
				catch (Throwable t) {
					throw new Exception(t);
				}
				finally {
					_digestRunning = false;
				}
			}
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info("Digest is already running, exiting.");
			}

		}
	}

	@Override
	public void buildInactiveUserDigest(long companyId, int frequency) throws Exception {
		if (!_digestRunning) {
			synchronized (_digestLock) {
				try {
					if (!_digestRunning) {
						_digestRunning = true;

						StopWatch stopWatch = null;

						if (_log.isDebugEnabled()) {
							stopWatch = new StopWatch();

							stopWatch.start();

							if (_log.isInfoEnabled()) {
								_log.info("Building Inactive User Digest...");
							}
						}


						DigestLocalServiceUtil.processInactiveUsers(companyId, frequency);

						if (_log.isInfoEnabled()) {
							_log.info("Inactive User Digest has been built in " + stopWatch.getTime() + " ms.");
						}
					}
				}
				catch (Throwable t) {
					throw new Exception(t);
				}
				finally {
					_digestRunning = false;
				}
			}
		}
	}

	public void processDigest(List<User> users, int frequency, String templateId) throws Exception {

		for (User user : users) {

			StopWatch stopWatch = null;

			if (_log.isDebugEnabled()) {
				stopWatch = new StopWatch();

				stopWatch.start();

				_log.debug("Starting digest processing for user " + user.getFullName());
			}

			try {
				// skip default user

				if (user.isDefaultUser()) {

					if (_log.isDebugEnabled()) {
						_log.debug("Default User, skipping.");
					}

					continue;
				}

				// user digest configuration

				UserDigestConfiguration userDigestConfiguration =
						UserDigestConfigurationLocalServiceUtil.fetchUserDigestConfigurationByUserId(user.getUserId());

				if (Validator.isNull(userDigestConfiguration) ||
						userDigestConfiguration.getFrequency() != frequency) {

					if (_log.isInfoEnabled()) {
						_log.info("User " + user.getFullName() + " frequency is not configured to run at this frequency " + DigestHelperUtil.getFrequencyAsString(frequency) + ", skipping.");
					}

					continue;
				}

				// digest configuration

				DigestConfiguration portalDigestConfiguration =
						DigestHelperUtil.getActivePortalDigestConfiguration(user.getCompanyId());

				DigestConfiguration digestConfiguration = null;

				// site digest

				List<Digest> siteDigestList =
						new ArrayList<Digest>();

				List<Group> groups = GroupLocalServiceUtil.getUserGroups(
						user.getUserId(), true);

				for (Group group : groups) {

					if (!group.isSite()) {

						if (_log.isDebugEnabled()) {
							_log.debug("Group " + group.getName() + " is not a site, skipping.");
						}

						continue;
					}

					// site digest configuration overrides portal settings

					digestConfiguration =
							DigestConfigurationLocalServiceUtil.fetchDigestConfigurationByScopeGroupId(
									group.getGroupId());

					if (Validator.isNull(digestConfiguration)) {
						digestConfiguration = _copyDigestConfiguration(portalDigestConfiguration, user);
					}
					else {
						digestConfiguration = _copyDigestConfiguration(digestConfiguration, user);
					}

					if (isSkipDigestConfiguration(digestConfiguration, user.getUserId(), 0)) {
						_addEmptyDigest(user, group, digestConfiguration, siteDigestList);

						continue;
					}

					// digest

					Digest digest = new DigestImpl(digestConfiguration);
					digest.setGroup(group);
					digest.setUser(user);

					// inactive users time frame based on number of max days inactive

					if (templateId.equals(PropsValues.DIGEST_ACTIVITY_INACTIVE_USER_TEMPLATE_ID)) {
						Date currentDate = DateUtil.newDate();

						Calendar cal = Calendar.getInstance();
						cal.setTime(currentDate);

						cal.add(Calendar.DAY_OF_WEEK,
								-DigestHelperUtil.getDigestInactiveNumberDays(digestConfiguration.getCompanyId()));

						cal.set(Calendar.HOUR_OF_DAY, 0);
						cal.set(Calendar.MINUTE, 0);
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);

						digestConfiguration.setStartDate(cal.getTime());
						digestConfiguration.setEndDate(currentDate);
					}

					// filter actions

					digestConfiguration = filterActivityTypeActions(
							DigestHelperUtil.getActivePortalDigestConfiguration(digestConfiguration.getCompanyId()), digestConfiguration);

					// for every digest configuration, build a digest

					for (String digestActivityProcessorName : _digestActivityProcessors.keySet()) {
						DigestActivityProcessor digestActivityProcessor = _digestActivityProcessors.get(digestActivityProcessorName);

						StopWatch stopWatch3 = null;

						if (_log.isDebugEnabled()) {
							stopWatch3 = new StopWatch();

							stopWatch3.start();

							_log.debug("Start Process Activity for processor " + digestActivityProcessorName);
						}


						digest = digestActivityProcessor.processActivity(digest);

						if (_log.isDebugEnabled()) {
							_log.debug(
									"Finished activity processing for processor " + digestActivityProcessorName + " in " +
											stopWatch3.getTime() + " ms");
						}
					}

					siteDigestList.add(digest);
				}

				if (Validator.isNotNull(siteDigestList)) {

					// merge digest with template

					StopWatch stopWatch2 = null;

					if (_log.isDebugEnabled()) {
						stopWatch2 = new StopWatch();

						stopWatch2.start();

						_log.debug("Merging digest template for user " + user.getFullName());
					}

					String html = DigestActivityTemplateProcessor.mergeDigest(digestConfiguration.getCompanyId(),
							templateId, siteDigestList);

					if (_log.isDebugEnabled()) {
						_log.debug(
								"Finished merging digest template for user " + user.getFullName() + " in " +
										stopWatch2.getTime() + " ms");
					}

					// email digest template result

					sendDigestEmail(user.getUserId(), digestConfiguration, html);
				}
				else {
					if (_log.isWarnEnabled()) {
						_log.warn("Digest Content not found for user " + user.getFullName());
					}
				}
			}
			finally {
				if (_log.isDebugEnabled()) {
					_log.debug(
							"Finished digest processing for user " + user.getFullName() + " in " +
									stopWatch.getTime() + " ms");
				}
			}
		}
	}

	protected void doBuildDigest(long companyId, int frequency, String templateId, List<User> users) throws Exception {
		while (_concurrentUserCounter < PropsValues.DIGEST_ACTIVITY_MAX_CONCURRENT_USER_PROCESS) {

			try {
				sendBuildDigest(users, frequency, templateId);
			}
			finally {
				_concurrentUserCounter++;
			}
		}
	}

	protected DigestConfiguration filterActivityTypeActions(
		DigestConfiguration portalDigestConfiguration, DigestConfiguration siteDigestConfiguration)
		throws Exception {

		if (portalDigestConfiguration.getId() == siteDigestConfiguration.getId()) {
			return portalDigestConfiguration;
		}

		JSONArray mergedActivityTypesJSONArray =
				JSONFactoryUtil.createJSONArray();

		Map<String, DigestActivityType> portalDigestActivityTypesMap =
				portalDigestConfiguration.getActivityTypesMap();

		List<DigestActivityType> siteDigestActivityTypeList =
				siteDigestConfiguration.getActivityTypesList();

		for (DigestActivityType siteActivityType : siteDigestActivityTypeList) {
			List<Integer> newList = new ArrayList<Integer>();

			List<Integer> portalActions = portalDigestActivityTypesMap.get(siteActivityType.getName()).getActionsAsList();
			List<Integer> siteActions = siteActivityType.getActionsAsList();

			for (Integer action : siteActions) {
				if (portalActions.contains(action)) {
					newList.add(action);
				}
			}

			siteActivityType.setActions(
					ArrayUtil.toArray(
							newList.toArray(new Integer[0])));

			mergedActivityTypesJSONArray.put(siteActivityType.toJSONString());
		}

		siteDigestConfiguration.setActivityTypes(mergedActivityTypesJSONArray.toString());

		return siteDigestConfiguration;
	}

	protected List<User> filterInactiveUsers(List<User> inactiveUsers) throws Exception {
		List<User> filteredUsers = new ArrayList<User>();

		for (User user : inactiveUsers) {
			if (Validator.isNull(
					DigestConfigurationLocalServiceUtil.fetchDigestConfigurationByScopeUserId(user.getUserId()))) {
				filteredUsers.add(user);
			}
		}

		return filteredUsers;
	}

	protected boolean isSkipDigestConfiguration(
			DigestConfiguration digestConfiguration, long scopeUserId, long scopeGroupId) throws Exception {

		User scopeUser = UserLocalServiceUtil.fetchUser(scopeUserId);

		if (scopeUser.isDefaultUser()) {
			return true;
		}

		if (!digestConfiguration.isEnabled()) {
			if (_log.isInfoEnabled()) {
				_log.info("Digest Configuration (" + digestConfiguration.getId() + ") is disabled, skipping..");
			}

			return true;
		}

		if (digestConfiguration.getFrequency() == DigestConstants.FREQUENCY_NONE) {
			if (_log.isInfoEnabled()) {
				_log.info("Digest Configuration (" + digestConfiguration.getId() + ") is set to NOT run, skipping..");
			}

			return true;
		}

		return false;
	}

	@Override
	public void sendBuildDigest(List<User> users, int frequency, String templateId) throws Exception {
/* TODO REPLACE WITH CLUSTER EVENT NOTIFICATION
		Message message = new Message();

		message.put("frequency", frequency);
		message.put("templateId", templateId);

		message.setPayload(users);

		MessageBusUtil.sendMessage("liferay/digest_activity_builder", message);*/

		processDigest(users, frequency, templateId);
	}

	protected void sendDigestEmail(long userId, DigestConfiguration digestConfiguration, String body) throws Exception {

		if (PropsValues.DIGEST_DEVELOPER_MODE) {
			File tempFile = FileUtil.createTempFile();

			FileWriter fileWriter = new FileWriter(tempFile);

			fileWriter.write(body);

			fileWriter.close();
		}

		// email

		User user = UserLocalServiceUtil.fetchUser(
				userId);

		InternetAddress from = new InternetAddress(
				PropsValues.DIGEST_EMAIL_FROM_ADDRESS,
				PropsValues.DIGEST_EMAIL_FROM_NAME);

		InternetAddress[] to = new InternetAddress[]{
				new InternetAddress(
					user.getEmailAddress(),
					user.getFullName())
		};

		String subject = digestConfiguration.getFrequency() == DigestConstants.FREQUENCY_DAILY ?
				LanguageUtil.get(user.getLocale(), "digest-frequency-daily-email-subject") :
				LanguageUtil.get(user.getLocale(), "digest-frequency-weekly-email-subject");

		MailMessage mailMessage = new MailMessage();

		mailMessage.setFrom(from);
		mailMessage.setSubject(subject);
		mailMessage.setBody(body);
		mailMessage.setHTMLFormat(true);

		mailMessage.setTo(to);

		MailServiceUtil.sendEmail(mailMessage);
	}

	public void setDigestActivityProcessors(Map<String, DigestActivityProcessor> digestActivityParsers) {
		_digestActivityProcessors = digestActivityParsers;
	}

	private void _addEmptyDigest(User user, Group group, DigestConfiguration digestConfiguration, List<Digest> siteDigestList) throws Exception {
		if (Validator.isNotNull(siteDigestList)) {
			Digest digest = new DigestImpl(digestConfiguration);

			digest.setGroup(group);
			digest.setUser(user);

			Map<String, List<DigestActivity>> digestActivityMap =
					new HashMap<String, List<DigestActivity>>();

			List<DigestActivityType> digestActivityTypeList =
					DigestHelperUtil.getAvailableDigestActivityTypes();

			for (DigestActivityType digestActivityType : digestActivityTypeList) {
				digestActivityMap.put(digestActivityType.getName(), new ArrayList<DigestActivity>());
			}

			digest.setActivities(digestActivityMap);

			siteDigestList.add(digest);
		}
	}

	private DigestConfiguration _copyDigestConfiguration(DigestConfiguration digestConfiguration, User user) throws Exception {
		DigestConfiguration userDigestConfiguration =
				(DigestConfiguration) digestConfiguration.clone();

		userDigestConfiguration.setActivityTypes(digestConfiguration.getActivityTypes());
		userDigestConfiguration.setCompanyId(digestConfiguration.getCompanyId());
		userDigestConfiguration.setCreateDate(digestConfiguration.getCreateDate());
		userDigestConfiguration.setEnabled(digestConfiguration.getEnabled());
		userDigestConfiguration.setEndDate(digestConfiguration.getEndDate());
		userDigestConfiguration.setFrequency(digestConfiguration.getFrequency());
		userDigestConfiguration.setGroupId(digestConfiguration.getGroupId());
		userDigestConfiguration.setId(digestConfiguration.getId());
		userDigestConfiguration.setModifiedDate(digestConfiguration.getModifiedDate());
		userDigestConfiguration.setScopeGroupId(digestConfiguration.getScopeGroupId());
		userDigestConfiguration.setStartDate(digestConfiguration.getStartDate());
		userDigestConfiguration.setSummaryLength(digestConfiguration.getSummaryLength());

		// current user

		userDigestConfiguration.setUserId(user.getUserId());
		userDigestConfiguration.setScopeUserId(user.getUserId());

		return userDigestConfiguration;
	}

	private static int 	_concurrentUserCounter = 0;

	private static boolean _digestRunning = false;

	private int[] _digestLock = new int[0];

	private static Map<String, DigestActivityProcessor> _digestActivityProcessors;

	private static final Log _log = LogFactoryUtil.getLog(DigestBuilderImpl.class);

}