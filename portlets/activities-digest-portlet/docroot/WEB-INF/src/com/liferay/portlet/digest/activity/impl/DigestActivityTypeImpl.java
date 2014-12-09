package com.liferay.portlet.digest.activity.impl;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portlet.digest.activity.DigestActivityType;
import com.liferay.portlet.digest.util.DigestHelperUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DigestActivityTypeImpl implements DigestActivityType, Serializable {

	public DigestActivityTypeImpl(int id, String name, int order, int[] actions) {
		_id = id;
		_name = name;
		_order = order;
		_actions = actions;
	}

	public DigestActivityTypeImpl(String json) {
		try {
			JSONObject digestActivityTypeObject =
					JSONFactoryUtil.createJSONObject(json);

			JSONArray digestActivityTypeActionsArray =
					digestActivityTypeObject.getJSONArray("actions");

			setId(digestActivityTypeObject.getInt("id"));
			setName(digestActivityTypeObject.getString("name"));

			List<Integer> actions = new ArrayList<Integer>();

			for (int i = 0 ; i < digestActivityTypeActionsArray.length() ; i++) {
				actions.add(
						digestActivityTypeActionsArray.getInt(i));

			}

			setActions(
					ArrayUtil.toArray(
							(Integer[]) actions.toArray(new Integer[0])));
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	@Override
	public boolean isEnabled() {

		return (_actions.length > 0);
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public String getLocalizedName(Locale locale) {
		return LanguageUtil.get(locale, _name);
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setOrder(int order) {
		_order = order;
	}

	@Override
	public int getOrder() {
		return _order;
	}

	@Override
	public void setId(int id) {
		_id = id;
	}

	@Override
	public int getId() {
		return _id;
	}

	@Override
	public void setActions(int[] actions) {
		_actions = actions;
	}

	@Override
	public int[] getActions() {
		return _actions;
	}

	@Override
	public List<Integer> getActionsAsList() {
		return Arrays.asList(
				ArrayUtil.toArray(_actions));
	}

	@Override
	public String getIconPath(long companyId) throws Exception {
		return DigestHelperUtil.getImagePath(companyId, getName());
	}

	public String toJSONString() throws Exception {
		JSONObject digestActivityType =
				JSONFactoryUtil.createJSONObject();

		JSONArray digestActivityTypeActions =
				JSONFactoryUtil.createJSONArray();

		for (Integer action : getActions()) {
			digestActivityTypeActions.put(action);
		}

		digestActivityType.put("id", getId());
		digestActivityType.put("name", getName());
		digestActivityType.put("order", getOrder());
		digestActivityType.put("actions", digestActivityTypeActions);

		return digestActivityType.toString();
	}

	private int _id;

	private int[] _actions;
	private String _name;
	private int _order;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		DigestActivityTypeImpl that = (DigestActivityTypeImpl) o;

		if (_id != that._id) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return _id;
	}

	@Override
	public String toString() {
		return "DigestActivityTypeImpl{" +
				"_id=" + _id +
				", _actions=" + Arrays.toString(_actions) +
				", _name='" + _name + '\'' +
				", _order='"+ _order + "\'"+
				'}';
	}

	private static final long serialVersionUID = 3653056830649520125L;

}
