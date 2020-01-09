<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<acme:form>
	<acme:form-textbox code="authenticated.investor.form.label.name" path="name"/>
	<acme:form-textbox code="authenticated.investor.form.label.sector" path="sector"/>
	<acme:form-textarea code="authenticated.investor.form.label.statement" path="statement"/>
	<acme:form-textbox code="authenticated.investor.form.label.stars" path="stars"/>
	
	<security:authorize access="hasRole('Administrator')">
		<acme:form-submit test="${command == 'show'}" code="administrator.investor.form.button.update"
			action="/administrator/investor/update/" />
		<acme:form-submit test="${command == 'show'}" code="administrator.investor.form.button.delete"
			action="/administrator/investor/delete/" />
		<acme:form-submit test="${command == 'update'}" code="administrator.investor.form.button.update"
			action="/administrator/investor/update/" />
			<acme:form-submit test="${command == 'create'}" code="administrator.investor.form.button.create"
		action="/administrator/investor/create/" />
		<acme:form-submit test="${command == 'delete'}" code="administrator.investor.form.button.delete"
			action="/administrator/investor/delete/" />
	</security:authorize>
	
	<acme:form-return code="administrator.investor.button.return"/>
</acme:form>