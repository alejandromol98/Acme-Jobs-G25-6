<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<acme:form >
	<acme:form-textbox code="employer.job.form.label.referenceNumber" path="referenceNumber" />
	<acme:form-checkbox code="employer.job.form.label.finalMode" path="finalMode" />
	<acme:form-textarea code="employer.job.form.label.title" path="title" />
	<acme:form-textbox code="employer.job.form.label.deadline" path="deadline" />
	<acme:form-money code="employer.job.form.label.salary" path="salary" />
	<acme:form-textbox code="employer.job.form.label.moreInfo" path="moreInfo" />

	<security:authorize access="hasRole('Worker')">
		<acme:form-hidden path="jobId" />
		<acme:form-submit code="worker.job.form.label.application" method="get" action="/worker/application/create?jobId=${id}" />
	</security:authorize>
</acme:form>