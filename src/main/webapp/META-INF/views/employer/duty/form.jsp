<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.duty.form.label.title" path="title" />
	<acme:form-textbox code="employer.duty.form.label.description" path="description" />
	<acme:form-textbox code="employer.duty.form.label.percentage" path="percentage" />

	<acme:form-submit test="${command == 'show'}" code="employer.job.form.button.delete" action="/employer/duty/delete/" />


	<jstl:if test="${command =='create'}">
		<acme:form-submit test="${command == 'create'}" code="employer.duty.form.button.create" action="/employer/duty/create/" />
	</jstl:if>
	<acme:form-return code="employer.duty.form.return" />

</acme:form>