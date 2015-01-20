package com.liferay.portlet.digest.util;


import com.liferay.portal.kernel.util.Validator;

public class PropsUtil {

    public static String get(String key) {

        String value = com.liferay.portal.kernel.util.PropsUtil.get(key);

        if (Validator.isNull(value)) {
            value = com.liferay.util.portlet.PortletProps.get(key);
        }

        return value;
    }
}
