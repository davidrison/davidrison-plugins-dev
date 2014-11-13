package com.liferay.portlet.digest.model.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;
import com.liferay.portlet.digest.model.Digest;

import java.util.List;
import java.util.Map;

public class DigestImpl implements Digest {

	public DigestImpl(DigestConfiguration digestConfiguration) {
		_digestConfiguration = digestConfiguration;

		if (digestConfiguration.getScopeGroupId() > 0) {
			try {
				_group = GroupLocalServiceUtil.fetchGroup(
						digestConfiguration.getGroupId());
			} catch (SystemException se) {
				_log.warn("Unable to locate group with group id " + digestConfiguration.getScopeGroupId());
			}
		}

		if (digestConfiguration.getScopeUserId() > 0) {
			try {
				_user = UserLocalServiceUtil.fetchUser(
						digestConfiguration.getScopeUserId()
				);
			} catch (SystemException se) {
				_log.warn("Unable to locate user with user id " + digestConfiguration.getScopeUserId());
			}
		}
	}

	@Override
	public long getGroupId() {
		return _group.getGroupId();
	}

	@Override
	public Group getGroup() {
		return _group;
	}

	@Override
	public long getUserId() {
		return _user.getUserId();
	}

	@Override
	public User getUser() {
		return _user;
	}

	@Override
	public DigestConfiguration getConfiguration() {
		return _digestConfiguration;
	}

	@Override
	public Map<String, List<DigestActivity>> getActivities() {
		return _activities;
	}

	@Override
	public List<DigestActivity> getActivities(String activityName) {
		return (List<DigestActivity>) _activities.get(activityName);
	}

	@Override
	public void setActivities(Map<String, List<DigestActivity>> activities) {
		_activities = activities;
	}

	@Override
	public void setGroup(Group group) {
		_group = group;
	}

	@Override
	public void setUser(User user) {
		_user = user;
	}

	private Map<String, List<DigestActivity>> _activities;

	private DigestConfiguration _digestConfiguration;

	private Group _group;

	private User _user;

	private static final Log _log = LogFactoryUtil.getLog(DigestImpl.class);

}
