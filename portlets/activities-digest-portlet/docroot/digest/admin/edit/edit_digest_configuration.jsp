<portlet:actionURL
	name="updateDigestConfiguration"
	var="updateDigestConfigurationURL" />

<%
	DigestConfiguration digestConfiguration = null;

	DigestConfiguration portalDigestConfiguration =
			(DigestConfiguration) request.getAttribute("portalDigestConfiguration");

	DigestConfiguration siteDigestConfiguration =
			(DigestConfiguration) request.getAttribute("siteDigestConfiguration");

	if (Validator.isNull(siteDigestConfiguration)) {
		siteDigestConfiguration = (DigestConfiguration) portalDigestConfiguration.clone();
		digestConfiguration = (DigestConfiguration) siteDigestConfiguration.clone();
	}

	long digestScopeGroupId = 0;
	long digestScopeUserId = 0;

	boolean isGroupAdmin =
			(category.equals(PortletCategoryKeys.CONTENT) &&
					permissionChecker.isGroupAdmin(layout.getGroup().getGroupId()) &&
					!layout.getGroup().isUser());

	boolean digestEnabled =
			portalDigestConfiguration.getEnabled();

	boolean uiEnabled = false;

	int digestFrequency = DigestConstants.FREQUENCY_NONE;

	// https://jira.netacad.net/jira/browse/NEX-8471
	if (Validator.isNotNull(portalDigestConfiguration.getFrequency())) {
		digestFrequency = portalDigestConfiguration.getFrequency();
	}
%>

<c:choose>
	<c:when test="<%= category.equals(PortletCategoryKeys.MY)%>">
		<%
			digestConfiguration = (DigestConfiguration) request.getAttribute("userDigestConfiguration");

			if (Validator.isNull(digestConfiguration)) {
				digestConfiguration = (DigestConfiguration) siteDigestConfiguration.clone();
				digestConfiguration.setEnabled(false);
			}

			digestScopeUserId = themeDisplay.getUserId();

			digestEnabled = (
					digestConfiguration.getEnabled() &&
							siteDigestConfiguration.getEnabled() &&
							portalDigestConfiguration.getEnabled()
			);

			uiEnabled = portalDigestConfiguration.getEnabled();
		%>
	</c:when>
	<c:when test="<%= category.equals(PortletCategoryKeys.CONTENT)%>">
		<%
			digestConfiguration = siteDigestConfiguration;

			digestScopeGroupId = themeDisplay.getScopeGroupId();

			digestEnabled = (
					siteDigestConfiguration.getEnabled() &&
							portalDigestConfiguration.getEnabled()
			);

			uiEnabled = (portalDigestConfiguration.getEnabled());
		%>
	</c:when>
	<c:otherwise>
		<%
			digestConfiguration = portalDigestConfiguration;
		%>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="<%= (!uiEnabled && !category.equals(PortletCategoryKeys.PORTAL)) %>">
		<c:choose>
			<c:when test="<%= category.equals(PortletCategoryKeys.MY)%>">
				<liferay-ui:message key="digest-is-not-enabled-for-user" />
			</c:when>
			<c:when test="<%= category.equals(PortletCategoryKeys.CONTENT)%>">
				<liferay-ui:message key="digest-is-not-enabled-for-site" />
			</c:when>
		</c:choose>
	</c:when>
	<c:otherwise>
		<aui:fieldset label="asset-defaults">
			<aui:form action="<%= updateDigestConfigurationURL.toString() %>" method="post" name="fm" useNamespace="true">
				<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.EDIT %>" />
				<aui:input name="digestConfigurationId" type="hidden" value="<%= digestConfiguration.getId() %>" />
				<aui:input name="digestScopeGroupId" type="hidden" value="<%= digestScopeGroupId %>" />
				<aui:input name="digestScopeUserId" type="hidden" value="<%= digestScopeUserId %>" />

				<c:choose>
					<c:when test="<%= !category.equals(PortletCategoryKeys.MY) %>">
						<aui:fieldset name="digest-enabled" >
							<aui:input label="enable-digest" name="digestEnabled" type="checkbox" checked="<%= digestEnabled %>" />
						</aui:fieldset>
					</c:when>
					<c:otherwise>
						<aui:input label="enable-digest" name="digestEnabled" type="hidden" value="<%= true %>" />
					</c:otherwise>
				</c:choose>

				<aui:fieldset column="true">
					<aui:select name="digestFrequency" label="digest-frequency">
						<aui:option label="<%= DigestHelperUtil.getFrequencyAsString(DigestConstants.FREQUENCY_NONE) %>"
									value="<%= DigestConstants.FREQUENCY_NONE %>"
									selected="<%= digestFrequency == DigestConstants.FREQUENCY_NONE%>" />
						<aui:option label="<%= DigestHelperUtil.getFrequencyAsString(DigestConstants.FREQUENCY_DAILY) %>"
									value="<%= DigestConstants.FREQUENCY_DAILY %>"
									selected="<%= digestFrequency == DigestConstants.FREQUENCY_DAILY%>" />
						<aui:option label="<%= DigestHelperUtil.getFrequencyAsString(DigestConstants.FREQUENCY_WEEKLY) %>"
									value="<%= DigestConstants.FREQUENCY_WEEKLY %>"
									selected="<%= digestFrequency == DigestConstants.FREQUENCY_WEEKLY%>" />
					</aui:select>
					<c:if test="<%= category.equals(PortletCategoryKeys.PORTAL )%>">


						<aui:input	name="digestSummaryLength"
									  label="digest-summary-length"
									  type="text"
									  value="<%= digestConfiguration.getSummaryLength()%>" />

						<aui:input	name="digestInactiveUserNumberDays"
									  label="digest-inactive-user-number-days"
									  type="text"
									  value="<%= digestInactiveUserNumberDays %>" />
					</c:if>
				</aui:fieldset>

				<c:if test="<%= !category.equals(PortletCategoryKeys.MY) %>">
					<%
						List<DigestActivityType> availableDigestActivityTypes =
								DigestHelperUtil.getAvailableDigestActivityTypes();

						List<DigestActivityType> digestActivityTypes =
								digestConfiguration.getActivityTypesList();

						for (DigestActivityType digestActivityType : availableDigestActivityTypes) {
							List<Integer> actionsList = digestActivityType.getActionsAsList();

							Map<String, DigestActivityType> portalActivityTypesMap =
									portalDigestConfiguration.getActivityTypesMap();

							if (category.equals(PortletCategoryKeys.CONTENT)) {
								// if action is disabled in portal, disable in site ui

								DigestActivityType portalDigestActivityType =
										portalActivityTypesMap.get(digestActivityType.getName());

								if (portalDigestActivityType.getActionsAsList().isEmpty()) {
									continue;
								}
							}

					%>
						<aui:field-wrapper
								label="<%= digestActivityType.getLocalizedName(themeDisplay.getLocale()) %>"
								name="<%= digestActivityType.getName() %>"
								inlineField="false"
								inlineLabel="false">
							<%
								for (Integer action : actionsList) {
									boolean checked = false;
									boolean disabled = false;

									if (category.equals(PortletCategoryKeys.CONTENT)) {
										// if action is disabled in portal, disable in site ui

										DigestActivityType portalDigestActivityType =
												portalActivityTypesMap.get(digestActivityType.getName());

										if (!portalDigestActivityType.getActionsAsList().contains(action)) {
											disabled = true;
											checked = false;
											continue;
										}
									}

									String activityTypeActionKey = digestActivityType.getName() + "_ACTION_" + action;

									Map<String, DigestActivityType> digestActivityTypeMap =
											digestConfiguration.getActivityTypesMap();

									DigestActivityType activeDigestActivityType =
											digestActivityTypeMap.get(digestActivityType.getName());

									if (Validator.isNotNull(activeDigestActivityType)) {
										List<Integer> activeActions =
												activeDigestActivityType.getActionsAsList();
										for (Integer activeAction : activeActions) {
											if (activeAction.intValue() == action.intValue()) {
												checked = true;
											}
										}
									}
							%>

							<aui:input	name="<%= activityTypeActionKey %>"
										  type="checkbox"
										  label="<%= DigestActivityFactoryUtil.getDigestActivityConverter(digestActivityType.getName()).getActionName(action) %>"
										  checked="<%= checked %>"
										  disabled="<%= disabled %>"/>


							<%

								}
							%>
						</aui:field-wrapper>
					<%
						}

					%>
				</c:if>

				<aui:button-row>
					<aui:button name="save" type="submit" value="save"/>
					<aui:button href="<%= currentURL %>" type="cancel"/>
				</aui:button-row>
			</aui:form>
		</aui:fieldset>
	</c:otherwise>
</c:choose>

