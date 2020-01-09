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

<acme:form>
	<acme:form-textbox code="authenticated.request.form.label.title" path="title" />
	<jstl:if test="${command !='create'}">
		<acme:form-moment code="authenticated.request.form.label.moment" path="moment" readonly="true" />
	</jstl:if>
	<acme:form-moment code="authenticated.request.form.label.deadline" path="deadline" />
	<acme:form-textarea code="authenticated.request.form.label.description" path="description" />
	<acme:form-textarea code="authenticated.request.form.label.ticker" path="ticker" />
	<acme:form-money code="authenticated.request.form.label.reward" path="reward" />
	<acme:form-textbox code="authenticated.request.form.label.stars" path="stars" />

	
	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox code="anonymous.user-account.label.accept" path="accept" />
	</jstl:if>

	<acme:form-submit test="${command=='create' }" code="authenticated.request.form.button.create"
		action="/authenticated/request/create" />


	<acme:form-return code="authenticated.request.form.button.return" />
</acme:form>
