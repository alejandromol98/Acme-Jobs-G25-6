<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.application.form.label.worker" path="worker.authorityName" readonly="true" />
	<acme:form-textbox code="employer.application.form.label.job" path="job.title" readonly="true" />
	<acme:form-textbox code="employer.application.form.label.reference" path="reference" readonly="true" />
	<acme:form-textbox code="employer.application.form.label.moment" path="moment" readonly="true" />
	<acme:form-textbox code="employer.application.form.label.status" path="status" readonly="true" />
	<acme:form-textbox code="employer.application.form.label.statement" path="statement" readonly="true" />
	<acme:form-textbox code="employer.application.form.label.skills" path="skills" readonly="true" />
	<acme:form-textbox code="employer.application.form.label.qualifications" path="qualifications" readonly="true" />

	<acme:form-hidden path="status" />
	<jstl:if test="${status == 'REJECTED'}">
		<acme:form-textarea code="employer.application.form.label.justification" path="justification" readonly="true" />
	</jstl:if>

	<jstl:if test="${status == 'PENDING'}">
		<acme:form-textarea code="employer.application.form.label.justification" path="justification" readonly="false" />
		<acme:form-submit code="employer.application.form.label.accept" method="post" action="/employer/application/accept" />
		<acme:form-submit code="employer.application.form.label.reject" method="post" action="/employer/application/reject" />
	</jstl:if>

	<acme:form-return code="employer.application.form.return" />
</acme:form>