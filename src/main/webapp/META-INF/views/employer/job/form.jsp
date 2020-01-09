<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.job.form.label.referenceNumber" path="referenceNumber" />
	<acme:form-checkbox code="employer.job.form.label.finalMode" path="finalMode" />
	<acme:form-textbox code="employer.job.form.label.title" path="title" />
	<acme:form-textbox code="employer.job.form.label.description" path="description" />
	<acme:form-textbox code="employer.job.form.label.deadline" path="deadline" />
	<acme:form-money code="employer.job.form.label.salary" path="salary" />
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo" />

	<acme:form-hidden path="id" />
	<acme:form-hidden path="employerId" />

	<jstl:if test="${finalMode == false}">
		<acme:form-submit test="${command == 'show'}" code="employer.job.form.button.update" action="/employer/job/update" />
	</jstl:if>


	<jstl:if test="${command !='create'}">
		<acme:form-submit code="employer.job.form.label.duty" method="get" action="/employer/duty/list?id=${id}" />
		<acme:form-submit code="employer.job.form.label.dutyCreate" method="get" action="/employer/duty/create?id=${id}" />
		<acme:form-submit test="${command =='show'}" code="employer.job.form.button.delete" action="/employer/job/delete/" />
	</jstl:if>

	<jstl:if test="${command =='create'}">
		<acme:form-submit test="${command == 'create'}" code="employer.job.form.button.create" action="/employer/job/create/" />
	</jstl:if>
	<acme:form-return code="employer.job.form.return" />
</acme:form>