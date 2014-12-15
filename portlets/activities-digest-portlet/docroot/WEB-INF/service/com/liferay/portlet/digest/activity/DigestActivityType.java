package com.liferay.portlet.digest.activity;

import java.util.List;
import java.util.Locale;

public interface DigestActivityType {

	public void setActions(int[] actions);

	public int[] getActions();

	public List<Integer> getActionsAsList();

	public boolean isEnabled();

	public void setId(int id);

	public int getId();

	public void setName(String name);

	public String getLocalizedName(Locale locale);

	public String getName();

	public String getIconPath(long companyId) throws Exception;

	public void setOrder(int order);

	public int getOrder();

	public String toJSONString() throws Exception;
}
