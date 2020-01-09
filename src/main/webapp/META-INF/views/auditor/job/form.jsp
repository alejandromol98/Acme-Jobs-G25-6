<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="auditor.job.form.label.referenceNumber" path="referenceNumber" />
	<acme:form-checkbox code="auditor.job.form.label.finalMode" path="finalMode" />
	<acme:form-textbox code="auditor.job.form.label.title" path="title" />
	<acme:form-textbox code="auditor.job.form.label.deadline" path="deadline" />
	<acme:form-money code="auditor.job.form.label.salary" path="salary" />
	<acme:form-textarea code="auditor.job.form.label.description" path="description" />
	<acme:form-url code="auditor.job.form.label.moreInfo" path="moreInfo" />
	
	<acme:form-hidden path="id" />
	
	<acme:form-submit code="auditor.job.form.label.auditRecord" method="get" action="/auditor/audit-record/list?id=${id}"/>
	<acme:form-submit test="${command == 'show'}" method = "get" code="auditor.auditRecord.form.button.create" action="/auditor/audit-record/create?jobId=${id}" />
	<acme:form-return code="auditor.auditRecord.button.return"/>
</acme:form>