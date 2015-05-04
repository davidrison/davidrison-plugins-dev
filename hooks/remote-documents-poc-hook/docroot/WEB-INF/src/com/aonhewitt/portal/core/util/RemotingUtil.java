package com.aonhewitt.portal.core.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.lang.reflect.Constructor;

public class RemotingUtil {

	public static Object invoke(
			long companyId, String className, String methodName, Object[] arguments, Class[] argumentTypes)
		throws Exception {

		String hostName = PrefsPropsUtil.getString(companyId, "poc.remote.host.name", RemotingConstants.DEFAULT_REMOTE_HOST_NAME);
		int port = PrefsPropsUtil.getInteger(companyId, "poc.remote.host.port", RemotingConstants.DEFAULT_REMOTE_HOST_PORT);

		return invoke(hostName, port, false, className, methodName, arguments, argumentTypes);
	}

	public static Object invoke(
			String hostName, int port, boolean secure,
			String className, String methodName, Object[] arguments, Class[] argumentTypes)
		throws Exception {

		try {
			Object httpPrincipal = null;

			StringBundler sb = new StringBundler(4);

			if (secure) {
				sb.append(Http.HTTPS_WITH_SLASH);
			} else {
				sb.append(Http.HTTP_WITH_SLASH);
			}

			sb.append(hostName);
			sb.append(StringPool.COLON);

			sb.append("" + port);

			String url = sb.toString();

			PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();

			User user = UserLocalServiceUtil.getUser(permissionChecker.getUserId());

			Class declaringClass = PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.security.auth.HttpPrincipal");

			Constructor c = declaringClass.getConstructor(
					new Class[]{
							String.class, String.class, String.class, boolean.class
					}
			);

			httpPrincipal = c.newInstance(
					new Object[]{
							url, user.getEmailAddress(), user.getPassword(),
							user.getPasswordEncrypted()
					}
			);

			Class[] newArgumentTypes = new Class[argumentTypes.length + 1];

			newArgumentTypes[0] = c.getDeclaringClass();

			for (int i = 1; i < argumentTypes.length+1; i++) {
				newArgumentTypes[i] = argumentTypes[i - 1];
			}

			Object[] newArguments = new Object[arguments.length + 1];

			newArguments[0] = httpPrincipal;

			for (int i = 1; i < arguments.length+1; i++) {
				newArguments[i] = arguments[i - 1];
			}

			MethodKey methodKey = new MethodKey(
					className, methodName, newArgumentTypes);

			return PortalClassInvoker.invoke(false, methodKey, newArguments);
		}
		catch (PortalException e1) {
			e1.printStackTrace();
		}
		catch (SystemException e1) {
			e1.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}