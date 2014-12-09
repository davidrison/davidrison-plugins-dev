package com.liferay.portlet.digest.activity.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portlet.digest.builder.DigestBuilderUtil;
import com.liferay.portlet.digest.util.DigestConstants;
import com.liferay.portlet.digest.util.DigestFrequencyThreadLocal;
import org.apache.commons.lang.time.StopWatch;

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
			StopWatch stopWatch = null;

			if (_log.isDebugEnabled()) {
				stopWatch = new StopWatch();
				stopWatch.start();

				_log.debug("Processing " + users.size() + " users.");
			}

			try {
				DigestFrequencyThreadLocal.setDigestFrequency(frequency);

				DigestBuilderUtil.processDigest(users, frequency, templateId);
			}
			catch (Throwable t) {
				throw new Exception(t);
			}
			finally {
				DigestFrequencyThreadLocal.setDigestFrequency(DigestConstants.FREQUENCY_NONE);
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
						"Processing " + users.size() + " users completed in " +
								stopWatch.getTime() + " ms.");
			}
		}

	}

	private static final Log _log = LogFactoryUtil.getLog(DigestActivityBuilderListener.class);

}
