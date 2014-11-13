package com.liferay.portlet.digest.activity.messaging;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portlet.digest.builder.DigestBuilderUtil;
import com.liferay.portlet.digest.util.DigestConstants;

public class DigestActivityDailyListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
        long[] companyIds = PortalUtil.getCompanyIds();

        for (long companyId : companyIds) {
            DigestBuilderUtil.buildDigest(companyId, DigestConstants.FREQUENCY_DAILY);
        }
	}
}
