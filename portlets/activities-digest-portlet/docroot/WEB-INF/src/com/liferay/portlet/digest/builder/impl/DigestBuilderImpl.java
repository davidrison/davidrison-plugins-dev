package com.liferay.portlet.digest.builder.impl;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.digest.InvalidDigestFrequencyException;
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
import com.liferay.portlet.digest.util.DigestFrequencyThreadLocal;
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

							_log.debug("Building Digest for company id " + companyId + "...");
						}

						DigestFrequencyThreadLocal.setDigestFrequency(frequency);

						DigestLocalServiceUtil.processUsers(companyId, frequency);

						if (_log.isDebugEnabled()) {
							_log.debug("Digest Builder has completed execution for company id " + companyId + " in " + stopWatch.getTime() + " ms.");
						}
					}
				}
				catch (Throwable t) {
					throw new Exception(t);
				}
				finally {
					DigestFrequencyThreadLocal.setDigestFrequency(DigestConstants.FREQUENCY_NONE);

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
		if (!_inactiveUserDigestRunning) {
			synchronized (_inactiveUserDigestLock) {
				try {
					if (!_inactiveUserDigestRunning) {
						_inactiveUserDigestRunning = true;

						StopWatch stopWatch = null;

						if (_log.isDebugEnabled()) {
							stopWatch = new StopWatch();

							stopWatch.start();

							if (_log.isInfoEnabled()) {
								_log.info("Building Inactive User Digest...");
							}
						}

						DigestFrequencyThreadLocal.setDigestFrequency(frequency);

						DigestLocalServiceUtil.processInactiveUsers(companyId, frequency);

						if (_log.isDebugEnabled()) {
							_log.debug("Inactive User Digest has been built in " + stopWatch.getTime() + " ms.");
						}
					}
				}
				catch (Throwable t) {
					throw new Exception(t);
				}
				finally {
					DigestFrequencyThreadLocal.setDigestFrequency(DigestConstants.FREQUENCY_NONE);

					_inactiveUserDigestRunning = false;
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


				// digest configuration

				DigestConfiguration portalDigestConfiguration =
						DigestHelperUtil.getActivePortalDigestConfiguration(user.getCompanyId());

				DigestConfiguration digestConfiguration = _copyDigestConfiguration(portalDigestConfiguration, user);;

				if (isSkipUserDigestConfiguration(digestConfiguration, frequency, user.getUserId(), isInactiveDigest(templateId))) {
					if (_log.isDebugEnabled()) {
						_log.debug("User configuration is not valid, skipping.");
					}

					continue;
				}

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

					DigestConfiguration siteDigestConfiguration =
							DigestHelperUtil.getActiveSiteDigestConfiguration(group.getGroupId());

					if (Validator.isNotNull(siteDigestConfiguration)) {
						digestConfiguration = _copyDigestConfiguration(siteDigestConfiguration, user);
					}

					if (isSkipSiteDigestConfiguration(digestConfiguration, frequency, group.getGroupId(), isInactiveDigest(templateId))) {
						continue;
					}

					// digest

					Digest digest = new DigestImpl(digestConfiguration);
					digest.setGroup(group);
					digest.setUser(user);

					// inactive users time frame based on number of max days inactive

					if (isInactiveDigest(templateId)) {
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

						UserDigestConfiguration userDigestConfiguration =
								UserDigestConfigurationLocalServiceUtil.fetchUserDigestConfigurationByUserId(user.getUserId());

						if (Validator.isNull(userDigestConfiguration)) {
							userDigestConfiguration = UserDigestConfigurationLocalServiceUtil.addUserDigestConfiguration(
									user.getCompanyId(), user.getUserId(), portalDigestConfiguration.getFrequency());
						}

						int numInactiveSent = userDigestConfiguration.getNumInactiveSent();

						if (numInactiveSent > PropsValues.DIGEST_ACTIVITY_INACTIVE_USER_MAX_NUMBER_EMAILS) {
							if (_log.isInfoEnabled()) {
								_log.info("User " + user.getFullName() + "("+user.getUserId()+") has been sent a digest due to inactivity that exceeds the maximum number of emails, skipping.");
							}

							continue;
						}

						updateInactiveUserCount(user, portalDigestConfiguration);
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

					if (siteDigestList.size() == 0) {
						if (_log.isInfoEnabled()) {
							// Per https://jira.netacad.net/jira/browse/NEX-8321
							_log.info("No Activities found for any digest for user " + user.getFullName() + ", not sending email.");
						}
						continue;
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

	protected boolean isInactiveDigest(String templateId) {
		return (templateId.equals(PropsValues.DIGEST_ACTIVITY_INACTIVE_USER_TEMPLATE_ID));
	}

	protected boolean isSkipUserDigestConfiguration(
			DigestConfiguration digestConfiguration, int frequency, long scopeUserId, boolean inactive)
		throws Exception {

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

		// portal digest configuration

		DigestConfiguration portalDigestConfiguration =
				DigestHelperUtil.getActivePortalDigestConfiguration(digestConfiguration.getCompanyId());

		if (!inactive) {
			// configuration frequency

			int digestConfigurationFrequency;

			// user digest configuration(frequency only)

			UserDigestConfiguration userDigestConfiguration =
					UserDigestConfigurationLocalServiceUtil.fetchUserDigestConfigurationByUserId(scopeUserId);

			if (Validator.isNotNull(userDigestConfiguration)) {
				digestConfigurationFrequency = userDigestConfiguration.getFrequency();
			}
			else {
				// https://jira.netacad.net/jira/browse/NEX-8471
				digestConfigurationFrequency = portalDigestConfiguration.getFrequency();
			}

			// validate frequency

			try {
				DigestHelperUtil.validateFrequency(digestConfigurationFrequency, frequency);
			}
			catch (InvalidDigestFrequencyException idfe) {
				if (_log.isInfoEnabled()) {
					_log.info("Digest Frequency "+digestConfigurationFrequency+" is not valid or does not match frequency "+frequency);
				}

				return true;
			}

			digestConfiguration.setFrequency(digestConfigurationFrequency);

			// skip if user is in-active and currently processing active template

			if (!DigestHelperUtil.isUserActive(scopeUserId)) {
				if (_log.isInfoEnabled()) {
					_log.info("User is not active and currently processing only active template, skipping.");
				}

				return true;
			}
		}
		else {
			// skip if user is active and currently processing inactive template

			if (DigestHelperUtil.isUserActive(scopeUserId)) {
				if (_log.isInfoEnabled()) {
					_log.info("User is active and currently processing only in-active template, skipping.");
				}

				return true;
			}
		}

		return false;
	}

	protected boolean isSkipSiteDigestConfiguration(
			DigestConfiguration digestConfiguration, int frequency, long scopeGroupId, boolean inactive) throws Exception {

		if (!digestConfiguration.isEnabled()) {
			if (_log.isInfoEnabled()) {
				_log.info("Digest Configuration (" + digestConfiguration.getId() + ") is disabled, skipping..");
			}

			return true;
		}

		// portal digest configuration

		DigestConfiguration portalDigestConfiguration =
				DigestHelperUtil.getActivePortalDigestConfiguration(digestConfiguration.getCompanyId());

		// site digest configuration

		DigestConfiguration siteDigestConfiguration =
				DigestHelperUtil.getActiveSiteDigestConfiguration(scopeGroupId);

		if (Validator.isNull(siteDigestConfiguration)) {
			// site is not enabled by default, so no entry means not enabled
			if (_log.isInfoEnabled()) {
				_log.info("DigestConfiguration group " + scopeGroupId +
						" does not have an entry, disabled by default, skipping.");
			}

			return true;
		}

		if (!inactive) {
			// configuration frequency

			int digestConfigurationFrequency;

			// a site admin does not have a frequency setting

			digestConfigurationFrequency = portalDigestConfiguration.getFrequency();

			try {
				DigestHelperUtil.validateFrequency(digestConfigurationFrequency, frequency);
			}
			catch (InvalidDigestFrequencyException idfe) {
				if (_log.isInfoEnabled()) {
					_log.info("Digest Frequency "+digestConfigurationFrequency+" is not valid or does not match frequency "+frequency);
				}

				return true;
			}

			digestConfiguration.setFrequency(digestConfigurationFrequency);
		}

		return false;
	}

	@Override
	public void sendBuildDigest(List<User> users, int frequency, String templateId) throws Exception {
		Message message = new Message();

		message.put("frequency", frequency);
		message.put("templateId", templateId);

		message.setPayload(users);

		MessageBusUtil.sendMessage("liferay/digest_activity_builder", message);
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

		String subject = DigestFrequencyThreadLocal.getDigestFrequency() == DigestConstants.FREQUENCY_DAILY ?
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

	protected void updateInactiveUserCount(User user, DigestConfiguration digestConfiguration) throws Exception {
		UserDigestConfiguration userDigestConfiguration =
				UserDigestConfigurationLocalServiceUtil.fetchUserDigestConfigurationByUserId(user.getUserId());

		UserDigestConfigurationLocalServiceUtil.incrementNumberInactiveSent(userDigestConfiguration.getId());
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
	private static boolean _inactiveUserDigestRunning = false;

	private int[] _digestLock = new int[0];
	private int[] _inactiveUserDigestLock = new int[0];


	private static Map<String, DigestActivityProcessor> _digestActivityProcessors;

	private static final Log _log = LogFactoryUtil.getLog(DigestBuilderImpl.class);

}