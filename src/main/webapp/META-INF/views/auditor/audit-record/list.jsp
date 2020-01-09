<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:list>
<acme:list-column code="auditor.auditRecord.list.label.auditor" path="auditor.authorityName" width="30%" />
	<acme:list-column code="auditor.auditRecord.list.label.title" path="title" width="30%" />
	<acme:list-column code="auditor.auditRecord.list.label.moment" path="moment" width="20%" />
	<acme:list-column code="auditor.auditRecord.list.label.status" path="status" width="20%" />
</acme:list>
	
<acme:form>
	<acme:form-return code="auditor.auditRecord.button.return"/>
</acme:form>