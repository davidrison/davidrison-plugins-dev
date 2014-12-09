package com.liferay.portlet.digest.hook.events;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.digest.activity.service.UserDigestConfigurationLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPostAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) throws ActionException {

		try {
			if (_log.isDebugEnabled()) {
				_log.debug("Running " + request.getRemoteUser());
			}

			long userId = PortalUtil.getUserId(request);

			resetNumberInactiveSent(userId);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void resetNumberInactiveSent(long userId) {
		try {
			// reset number inactive sent

			UserDigestConfigurationLocalServiceUtil.resetNumberInactiveSent(userId);
		}
		catch (Throwable t) {
			if (_log.isInfoEnabled()) {
				_log.info("Unable to reset number inactive sent for user id " + userId);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(LoginPostAction.class);

}
