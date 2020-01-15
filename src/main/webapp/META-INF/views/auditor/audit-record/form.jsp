<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="jobId" />

	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="auditor.auditRecord.form.label.job" path="job.title" readonly="true" />
		<acme:form-textbox code="auditor.auditRecord.form.label.auditor" path="auditor.authorityName" readonly="true" />
		<acme:form-textbox code="auditor.auditRecord.form.label.moment" path="moment" readonly="true" />
	</jstl:if>

	<acme:form-textbox code="auditor.auditRecord.form.label.title" path="title" />
	<acme:form-textbox code="auditor.auditRecord.form.label.body" path="body" />
	<acme:form-select code="auditor.auditRecord.form.label.status" path="status" >
		<acme:form-option code="auditor.auditRecord.form.label.status.draft" value="DRAFT" selected="${status == 'DRAFT'}"/>
        <acme:form-option code="auditor.auditRecord.form.label.status.published" value="PUBLISHED" selected="${status == 'PUBLISHED'}"/>
    </acme:form-select>
	
	<acme:form-submit test="${command == 'create'}" code="auditor.auditRecord.form.button.create" action="/auditor/audit-record/create" />
	<jstl:if test="${status == 'DRAFT' }">
		<acme:form-submit test="${command == 'show'}" code="auditor.auditRecord.form.button.update" action="/auditor/audit-record/update" />
	</jstl:if>
	
	<acme:form-return code="auditor.auditRecord.form.return" />

</acme:form>