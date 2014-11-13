package com.liferay.portlet.digest.activity;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;

import java.util.List;

public interface DigestActivityConverter {

	public DigestActivity convert(BaseModel model, User digestUser) throws Exception;

	public List<Integer> getActions() throws Exception;

	public String getActionName(int action) throws Exception;

	public String getLink(BaseModel model) throws Exception;

	public String getLinkDisplay(DigestActivity digestActivity, User digestUser) throws Exception;
}
