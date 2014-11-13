package com.liferay.portlet.digest.activity.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portlet.digest.builder.DigestBuilderUtil;

import java.util.List;

public class DigestActivityBuilderListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("Digest Activity Builder event received.");
		}

		List<User> users = (List<User>) message.getPayload();

		int frequency = message.getInteger("frequency");

		String templateId = message.getString("templateId");

		if (Validator.isNotNull(users)) {
			DigestBuilderUtil.processDigest(users, frequency, templateId);
		}

	}

	private static final Log _log = LogFactoryUtil.getLog(DigestActivityBuilderListener.class);

}
