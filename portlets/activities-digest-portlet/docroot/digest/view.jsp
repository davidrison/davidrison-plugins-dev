<%@include file="init.jsp" %>

<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<c:choose>
	<c:when test="<%= category.equals(PortletCategoryKeys.MY) %>">
		<%@ include file="admin/edit/edit_user_configuration.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="admin/edit/edit_digest_configuration.jsp" %>
	</c:otherwise>
</c:choose>

<script type="text/javascript">
	function <portlet:namespace />toggleForm(hideId, showId) {
		document.getElementById(showId).style.display = "";
		document.getElementById(hideId).style.display = "none";
	}
</script>