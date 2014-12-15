<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portlet.digest.activity.model.DigestConfiguration" %>
<%@ page import="com.liferay.portlet.digest.activity.service.DigestConfigurationLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.digest.util.DigestHelperUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.compat.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.model.Portlet" %>
<%@ page import="com.liferay.portal.model.PortletCategory" %>

<%@ page import="com.liferay.portlet.digest.activity.DigestActivityType" %>
<%@ page import="com.liferay.portlet.digest.util.DigestConstants" %>
<%@ page import="com.liferay.portlet.digest.activity.util.DigestActivityFactoryUtil" %>

<%@ page import="com.liferay.portal.util.PortletCategoryKeys" %>

<%@ page import="java.text.Format" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="com.liferay.portlet.digest.util.PropsValues" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="com.liferay.portlet.digest.util.PortletKeys" %>
<%@ page import="javax.portlet.PortletPreferences" %>

<%@ page import="com.liferay.portal.theme.ThemeDisplay" %>

<%@ page import="com.liferay.portlet.digest.util.DigestHelperUtil" %>
<%@ page import="javax.portlet.PortletResponse" %>
<%@ page import="com.liferay.portal.kernel.util.*" %>

<%@ page import="com.liferay.portlet.digest.activity.model.UserDigestConfiguration" %>
<%@ page import="com.liferay.portlet.digest.activity.service.UserDigestConfigurationLocalServiceUtil" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
	Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);

	String currentURL = PortalUtil.getCurrentURL(request);

	Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(themeDisplay.getLocale(), themeDisplay.getTimeZone());

	String category = PortalUtil.getControlPanelCategory
			(portlet.getPortletId(), themeDisplay);

    int digestInactiveUserNumberDays = DigestHelperUtil.getDigestInactiveNumberDays(company.getCompanyId());
	int digestInactiveUserNumberEmails = DigestHelperUtil.getDigestInactiveNumberEmails(company.getCompanyId());

	PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

	String namespace = portletResponse.getNamespace();
%>