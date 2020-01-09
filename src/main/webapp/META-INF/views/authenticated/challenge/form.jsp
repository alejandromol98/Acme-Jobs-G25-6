<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title" />
	<acme:form-moment code="authenticated.challenge.form.deadline" path="deadline" />
	<acme:form-textarea code="authenticated.challenge.form.description" path="description" />
	<acme:form-money code="authenticated.challenge.form.rewardGoalGold" path="rewardGoalGold" />
	<acme:form-money code="authenticated.challenge.form.rewardGoalSilver" path="rewardGoalSilver" />
	<acme:form-money code="authenticated.challenge.form.rewardGoalBronze" path="rewardGoalBronze" />

	<security:authorize access="hasRole('Administrator')">
		<acme:form-submit test="${command == 'show'}" code="administrator.challenge.form.button.update"
			action="/administrator/challenge/update/" />
		<acme:form-submit test="${command == 'show'}" code="administrator.challenge.form.button.delete"
			action="/administrator/challenge/delete/" />
		<acme:form-submit test="${command == 'update'}" code="administrator.challenge.form.button.update"
			action="/administrator/challenge/update/" />
		<acme:form-submit test="${command == 'delete'}" code="administrator.challenge.form.button.delete"
			action="/administrator/challenge/delete/" />
	</security:authorize>

	<acme:form-return code="authenticated.challenge.form.return" />
</acme:form>
