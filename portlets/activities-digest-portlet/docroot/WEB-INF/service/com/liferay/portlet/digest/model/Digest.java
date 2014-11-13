package com.liferay.portlet.digest.model;

import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;

import java.util.List;
import java.util.Map;

public interface Digest {
	public long getGroupId();

	public DigestConfiguration getConfiguration();

	public Map<String, List<DigestActivity>> getActivities();

	public List<DigestActivity> getActivities(String activityName);

	public Group getGroup();

	public User getUser();

	public long getUserId();

	public void setActivities(Map<String, List<DigestActivity>> activities);

	public void setGroup(Group group);

	public void setUser(User user);
}
