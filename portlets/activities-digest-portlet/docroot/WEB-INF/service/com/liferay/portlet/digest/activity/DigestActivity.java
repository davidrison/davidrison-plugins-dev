package com.liferay.portlet.digest.activity;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.model.User;

import java.util.Date;

public interface DigestActivity {

	public DigestActivityType getActivityType();

	public Date getCreateDate();

	public String getExtraData();

	public JSONObject getExtraDataAsJSON();

	public long getGroupId();

	public long getId();

	public String getImageURL();

	public String getLink();

	public String getLinkDisplay();

	public String getName();

	public String getFriendlyName();

	public String getSummary();

	public int getType();

	public String getUuid();

	public User getUser();

	public void setCreateDate(Date createDate);

	public void setExtraData(String extraData);

	public void setGroupId(long groupId);

	public void setId(long id);

	public void setImageURL(String imageURL);

	public void setLink(String link);

	public void setLinkDisplay(String linkDisplay);

	public void setName(String name);

	public void setSummary(String summary);

	public void setUuid(String uuid);

	public void setUser(User user);

	public void setActivityType(DigestActivityType activityType);

	public void setType(int type);

	public JSONArray toJSONArray() throws Exception;

	public String toJSONString() throws Exception;
}