package com.liferay.portlet.digest.activity.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.DigestActivityType;

import java.io.Serializable;
import java.util.Date;

public class DigestActivityImpl implements DigestActivity, Serializable {

	public DigestActivityImpl() {}

	@Override
	public DigestActivityType getActivityType() {
		return _activityType;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public String getExtraData() {
		return _extraData;
	}

	@Override
	public JSONObject getExtraDataAsJSON() {
		JSONObject jsonObject = null;

		try {
			jsonObject = JSONFactoryUtil.createJSONObject(_extraData);
		}
		catch (Throwable t) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to parse extra data for digest activity "+getId());
			}
		}

		return jsonObject;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public long getId() {
		return _id;
	}

	@Override
	public String getImageURL() {
		return _imageURL;
	}

	@Override
	public String getLink() {
		return _link;
	}

	@Override
	public String getLinkDisplay() { return _linkDisplay; }

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getFriendlyName() {
		return _name;
	}

	@Override
	public String getSummary() {
		return _summary;
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public User getUser() {
		return _user;
	}

	@Override
	public void setActivityType(DigestActivityType activityType) {
		_activityType = activityType;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public void setExtraData(String extraData) {
		_extraData = extraData;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public void setId(long id) {
		_id = id;
	}

	@Override
	public void setImageURL(String imageURL) {
		_imageURL = imageURL;
	}

	@Override
	public void setLink(String link) {
		_link = link;
	}

	@Override
	public void setLinkDisplay(String linkDisplay) {
		_linkDisplay = linkDisplay;
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setSummary(String summary) {
		_summary = summary;
	}

	@Override
	public void setType(int type) {
		_type = type;
	}

	@Override
	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	@Override
	public void setUser(User user) {
		_user = user;
	}

	@Override
	public JSONArray toJSONArray() throws Exception {
		return JSONFactoryUtil.createJSONArray(this.toJSONString());
	}

	public String toJSONString() throws Exception {
		JSONObject digestActivity =
				JSONFactoryUtil.createJSONObject();

		JSONArray digestActivityTypes =
				JSONFactoryUtil.createJSONArray();

		digestActivity.put("id", getId());
		digestActivity.put("link", getLink());
		digestActivity.put("name", getName());
		digestActivity.put("summary", getSummary());
		digestActivity.put("uuid", getUuid());

		digestActivityTypes.put(_activityType.toString());

		return digestActivity.toString();
	}

	private Date _createDate;
	private String _extraData;
	private long _groupId;
	private long _id;
	private String _imageURL;
	private String _link;
	private String _linkDisplay;
	private String _name;
	private String _summary;
	private int _type;
	private DigestActivityType _activityType;
	private String _uuid;
	private User _user;

	private static final Log _log = LogFactoryUtil.getLog(DigestActivityImpl.class);

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		DigestActivityImpl that = (DigestActivityImpl) o;

		if (_id != that._id) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (_id ^ (_id >>> 32));
	}

	@Override
	public String toString() {
		return "DigestActivityImpl{" +
				"_createDate=" + _createDate +
				", _extraData='" + _extraData + '\'' +
				", _groupId=" + _groupId +
				", _id=" + _id +
				", _imageURL='" + _imageURL + '\'' +
				", _link='" + _link + '\'' +
				", _linkDisplay='" + _linkDisplay + '\'' +
				", _name='" + _name + '\'' +
				", _summary='" + _summary + '\'' +
				", _type=" + _type +
				", _activityType=" + _activityType +
				", _uuid='" + _uuid + '\'' +
				", _user=" + _user +
				'}';
	}

	private static final long serialVersionUID = 4272671151912806824L;

}