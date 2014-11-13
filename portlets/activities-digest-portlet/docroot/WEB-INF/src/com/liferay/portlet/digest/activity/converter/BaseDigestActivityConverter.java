package com.liferay.portlet.digest.activity.converter;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portlet.digest.activity.DigestActivity;
import com.liferay.portlet.digest.activity.DigestActivityConverter;
import com.liferay.portlet.digest.util.DigestHelperUtil;

import java.util.List;

public abstract class BaseDigestActivityConverter implements DigestActivityConverter {

	@Override
	public DigestActivity convert(BaseModel model, User digestUser) throws Exception {

        PermissionChecker contextPermissionChecker =
                PermissionThreadLocal.getPermissionChecker();

        String contextName = PrincipalThreadLocal.getName();

        try {
            PermissionChecker permissionChecker =
                    getPermissionChecker(digestUser);

            PermissionThreadLocal.setPermissionChecker(permissionChecker);

            PrincipalThreadLocal.setName(digestUser.getUserId());

            if (hasPermission(
                    permissionChecker, model, ActionKeys.VIEW)) {
                return doConvert(model, digestUser);
            } else {
                if (_log.isInfoEnabled()) {
                    _log.info("User " + digestUser.getFullName() + " does not have permission to view model with PK " + model.getPrimaryKeyObj());
                }
            }
        } catch (Throwable t) {
            if (_log.isErrorEnabled()) {
                _log.error(t, t);
            }

            throw new Exception(t);
        } finally {
            PermissionThreadLocal.setPermissionChecker(contextPermissionChecker);
            PrincipalThreadLocal.setName(contextName);
        }

        return null;
    }

	public abstract String getActionDisplay(DigestActivity activity);

	@Override
	public List<Integer> getActions() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getActionName(int action) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getLink(BaseModel model) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getLinkDisplay(DigestActivity digestActivity, User digestUser) throws Exception {
		StringBundler linkDetail = new StringBundler();

		linkDetail.append("<a href=\""+digestActivity.getLink()+"\" class=\"detail-link\" style=\"color: #619FED;text-decoration: none;\">");

		linkDetail.append(digestActivity.getName());

		linkDetail.append("</a >");

		linkDetail.append(" " + getActionDisplay(digestActivity) + " ");

		linkDetail.append("<a href = \"" + digestActivity.getUser().getDisplayURL(DigestHelperUtil.getPortalURL(digestActivity.getGroupId()), DigestHelperUtil.getPathMain()) + "\"");

		linkDetail.append(" class=\"detail-author\">" + digestActivity.getUser().getFullName() + " </a >");

		linkDetail.append(LanguageUtil.get(digestActivity.getUser().getLocale(), " on "));

		linkDetail.append("<span class=\"detail-date\" >" + DigestHelperUtil.formatDate(digestActivity.getCreateDate()) + "</span>");

		return linkDetail.toString();
	}

	protected PermissionChecker getPermissionChecker(User user) throws Exception {
		return PermissionCheckerFactoryUtil.create(user);
	}

	protected abstract DigestActivity doConvert(BaseModel model, User digestUser) throws Exception;

	protected abstract boolean hasPermission(
			PermissionChecker permissionChecker, BaseModel model, String actionId);

	private static final Log _log = LogFactoryUtil.getLog(BaseDigestActivityConverter.class);

}
