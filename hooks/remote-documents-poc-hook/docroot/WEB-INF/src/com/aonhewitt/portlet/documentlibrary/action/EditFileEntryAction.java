package com.aonhewitt.portlet.documentlibrary.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;

public class EditFileEntryAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(StrutsPortletAction originalStrutsPortletAction,
							  javax.portlet.PortletConfig portletConfig,
							  javax.portlet.ActionRequest actionRequest,
							  javax.portlet.ActionResponse actionResponse) throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("EditFileEntryAction::processAction");
		}

		super.processAction(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
	}

	@Override
	public String render(StrutsPortletAction originalStrutsPortletAction,
						 javax.portlet.PortletConfig portletConfig,
						 javax.portlet.RenderRequest renderRequest,
						 javax.portlet.RenderResponse renderResponse) throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("EditFileEntryAction::render");
		}

		return super.render(originalStrutsPortletAction, portletConfig, renderRequest, renderResponse);
	}

	@Override
	public void serveResource(StrutsPortletAction originalStrutsPortletAction,
							  javax.portlet.PortletConfig portletConfig,
							  javax.portlet.ResourceRequest resourceRequest,
							  javax.portlet.ResourceResponse resourceResponse) throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("EditFileEntryAction::serveResource");
		}

		super.serveResource(originalStrutsPortletAction, portletConfig, resourceRequest, resourceResponse);
	}

	private static final Log _log = LogFactoryUtil.getLog(EditFileEntryAction.class);
}
