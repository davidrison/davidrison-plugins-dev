package com.aonhewitt.portlet.documentlibrary.action;

import com.aonhewitt.portal.core.util.RemoteDocumentThreadLocal;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class ViewAction extends BaseStrutsPortletAction {

	@Override
	public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("ViewAction::render(portletConfig, renderRequest, renderResponse)");
		}

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Group scopeGroup = themeDisplay.getScopeGroup();

		if (scopeGroup.isOrganization()) {
			RemoteDocumentThreadLocal.setRemote(true);
		}

		return super.render(portletConfig, renderRequest, renderResponse);
	}

	@Override
	public String render(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("ViewAction::render(originalStrutsPortletAction, portletConfig, renderRequest, renderResponse)");
		}

		return super.render(originalStrutsPortletAction, portletConfig, renderRequest, renderResponse);
	}

	private static Log _log = LogFactoryUtil.getLog(ViewAction.class);

}
