<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="jobId" />
	

	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="worker.application.form.label.job" path="job.title" readonly="true"/>
		<acme:form-textbox code="worker.application.form.label.worker" path="worker.authorityName" readonly="true"/>
		<acme:form-moment code="worker.application.form.label.moment" path="moment" readonly="true"/>
		<acme:form-textbox code="worker.application.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="worker.application.form.label.reference" path="reference" />
	<acme:form-textbox code="worker.application.form.label.statement" path="statement" />
	<acme:form-textbox code="worker.application.form.label.skills" path="skills" />
	<acme:form-textbox code="worker.application.form.label.qualifications" path="qualifications" />
	<acme:form-hidden path="status" />
	<jstl:if test="${status == 'REJECTED'}">
		<acme:form-textarea code="worker.application.form.label.justification" path="justification"/>
	</jstl:if>
	<acme:form-submit test="${command == 'create'}" code="worker.application.form.button.create" action="/worker/application/create" />
	<acme:form-return code="worker.application.form.return" />
</acme:form>