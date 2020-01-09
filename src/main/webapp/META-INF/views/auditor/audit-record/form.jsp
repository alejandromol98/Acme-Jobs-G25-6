<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="jobId" />

	<acme:form-textbox code="auditor.auditRecord.form.label.title" path="title" />
	<acme:form-hidden path="moment" />
	<acme:form-select code="auditor.auditRecord.form.label.status" path="status" >
		<acme:form-option code="auditor.auditRecord.form.label.status.draft" value="DRAFT" selected="${status == 'DRAFT'}"/>
        <acme:form-option code="auditor.auditRecord.form.label.status.published" value="PUBLISHED" selected="${status == 'PUBLISHED'}"/>
    </acme:form-select>
	<acme:form-textarea code="auditor.auditRecord.form.label.body" path="body" />
	
	
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="auditor.auditRecord.form.label.title" path="title" readonly="true"/>
		<acme:form-textbox code="auditor.auditRecord.form.label.body" path="body" readonly="true"/>
		<acme:form-moment code="auditor.auditRecord.form.label.moment" path="moment" readonly="true"/>
		<acme:form-textbox code="auditor.auditRecord.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<acme:form-submit test="${command == 'create'}" code="auditor.auditRecord.form.button.create" action="create?jobId=${jobId}" />
	<acme:form-return code="auditor.auditRecord.button.return"/>
	
</acme:form>