package com.liferay.portlet.digest.activity.portlet;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletCategoryKeys;
import com.liferay.portlet.BaseControlPanelEntry;
import com.liferay.portlet.digest.activity.model.DigestConfiguration;
import com.liferay.portlet.digest.util.DigestHelperUtil;

public class ActivityDigestControlPanelEntry extends BaseControlPanelEntry {

	@Override
	public boolean isVisible(Portlet portlet, String category, ThemeDisplay themeDisplay) throws Exception {
		boolean isVisible = super.isVisible(portlet, category, themeDisplay);


		if (!category.equals(PortletCategoryKeys.PORTAL)) {
			DigestConfiguration digestConfiguration =
					DigestHelperUtil.getActivePortalDigestConfiguration(
							themeDisplay.getCompanyId());

			if (Validator.isNotNull(digestConfiguration)) {
				if (!digestConfiguration.isEnabled()) {
					return false;
				}
			}
		}

		return isVisible;
	}

	@Override
	public boolean isVisible(PermissionChecker permissionChecker, Portlet portlet) throws Exception {

		DigestConfiguration digestConfiguration =
				DigestHelperUtil.getActivePortalDigestConfiguration(
						permissionChecker.getCompanyId());

		if (Validator.isNotNull(digestConfiguration)) {
			if (!digestConfiguration.isEnabled()) {
				return false;
			}
		}

		return PortletPermissionUtil.contains(
				permissionChecker, portlet.getPortletId(), ActionKeys.ACCESS_IN_CONTROL_PANEL);
	}
}
