<portlet:actionURL
		name="updateUserDigestConfiguration"
		var="updateUserDigestConfigurationURL" />

<%
	UserDigestConfiguration userDigestConfiguration =
			UserDigestConfigurationLocalServiceUtil.fetchUserDigestConfigurationByUserId(
					user.getUserId());
%>


<aui:form action="<%= updateUserDigestConfigurationURL.toString() %>" method="post" name="fm" useNamespace="true">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.EDIT %>" />
	<aui:input name="userDigestConfigurationId" type="hidden" value="<%= userDigestConfiguration != null ? userDigestConfiguration.getId() : 0%>" />

	<aui:fieldset column="true">
		<aui:select name="digestFrequency" label="digest-frequency">
			<aui:option label="<%= DigestHelperUtil.getFrequencyAsString(DigestConstants.FREQUENCY_NONE) %>"
						value="<%= DigestConstants.FREQUENCY_NONE %>"
						selected="<%= userDigestConfiguration != null ? (userDigestConfiguration.getFrequency() == DigestConstants.FREQUENCY_NONE) : true %>" />
			<aui:option label="<%= DigestHelperUtil.getFrequencyAsString(DigestConstants.FREQUENCY_DAILY) %>"
						value="<%= DigestConstants.FREQUENCY_DAILY %>"
						selected="<%= userDigestConfiguration != null ? (userDigestConfiguration.getFrequency() == DigestConstants.FREQUENCY_DAILY) : false %>" />
			<aui:option label="<%= DigestHelperUtil.getFrequencyAsString(DigestConstants.FREQUENCY_WEEKLY) %>"
						value="<%= DigestConstants.FREQUENCY_WEEKLY %>"
						selected="<%= userDigestConfiguration != null ? (userDigestConfiguration.getFrequency() == DigestConstants.FREQUENCY_WEEKLY) : false%>" />
		</aui:select>
	</aui:fieldset>

	<aui:button-row>
		<aui:button name="save" type="submit" value="save"/>
		<aui:button href="<%= currentURL %>" type="cancel"/>
	</aui:button-row>
</aui:form>
