<portlet:actionURL
		name="updateUserDigestConfiguration"
		var="updateUserDigestConfigurationURL" />

<%
	DigestConfiguration portalDigestConfiguration =
			DigestHelperUtil.getActivePortalDigestConfiguration(company.getCompanyId());

	DigestConfiguration siteDigestConfiguration =
			DigestHelperUtil.getActiveSiteDigestConfiguration(themeDisplay.getScopeGroupId());

	UserDigestConfiguration userDigestConfiguration =
			UserDigestConfigurationLocalServiceUtil.fetchUserDigestConfigurationByUserId(
					user.getUserId());

	long userDigestConfigurationId = 0;

	int userDigestFrequency = DigestConstants.FREQUENCY_NONE;

	// check site first
	if (Validator.isNotNull(siteDigestConfiguration)) {
		userDigestFrequency = siteDigestConfiguration.getFrequency();
	}
	// otherwise, portal
	else if (Validator.isNotNull(portalDigestConfiguration.getFrequency())) {
		userDigestFrequency = portalDigestConfiguration.getFrequency();
	}

	// if user has configuraiton, use frequency

	if (Validator.isNotNull(userDigestConfiguration)) {
		userDigestConfigurationId = userDigestConfiguration.getId();
		userDigestFrequency = userDigestConfiguration.getFrequency();
	}
%>


<aui:form action="<%= updateUserDigestConfigurationURL.toString() %>" method="post" name="fm" useNamespace="true">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.EDIT %>" />
	<aui:input name="userDigestConfigurationId" type="hidden" value="<%= userDigestConfigurationId %>" />

	<aui:fieldset column="true">
		<aui:select name="digestFrequency" label="digest-frequency">
			<aui:option label="<%= DigestHelperUtil.getFrequencyAsString(DigestConstants.FREQUENCY_NONE) %>"
						value="<%= DigestConstants.FREQUENCY_NONE %>"
						selected="<%= userDigestFrequency == DigestConstants.FREQUENCY_NONE %>" />
			<aui:option label="<%= DigestHelperUtil.getFrequencyAsString(DigestConstants.FREQUENCY_DAILY) %>"
						value="<%= DigestConstants.FREQUENCY_DAILY %>"
						selected="<%= userDigestFrequency == DigestConstants.FREQUENCY_DAILY %>" />
			<aui:option label="<%= DigestHelperUtil.getFrequencyAsString(DigestConstants.FREQUENCY_WEEKLY) %>"
						value="<%= DigestConstants.FREQUENCY_WEEKLY %>"
						selected="<%= userDigestFrequency == DigestConstants.FREQUENCY_WEEKLY %>" />
		</aui:select>
	</aui:fieldset>

	<aui:button-row>
		<aui:button name="save" type="submit" value="save"/>
		<aui:button href="<%= currentURL %>" type="cancel"/>
	</aui:button-row>
</aui:form>
