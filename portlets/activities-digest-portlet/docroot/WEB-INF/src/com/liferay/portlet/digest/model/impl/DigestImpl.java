package com.liferay.portlet.digest.model.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.DigestActivityType;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;
import com.liferay.portlet.digest.model.Digest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DigestImpl implements Digest, Serializable {

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
	public List<DigestActivityType> getActivityTypes() throws Exception {
		return _digestConfiguration.getActivityTypesList();
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		DigestImpl digest = (DigestImpl) o;

		if (_group != null ? !_group.equals(digest._group) : digest._group != null) {
			return false;
		}
		if (_user != null ? !_user.equals(digest._user) : digest._user != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = _group != null ? _group.hashCode() : 0;
		result = 31 * result + (_user != null ? _user.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "DigestImpl{" +
				"_activities=" + _activities +
				", _digestConfiguration=" + _digestConfiguration +
				", _group=" + _group +
				", _user=" + _user +
				'}';
	}

	private static final long serialVersionUID = 5602250253794953970L;

}
