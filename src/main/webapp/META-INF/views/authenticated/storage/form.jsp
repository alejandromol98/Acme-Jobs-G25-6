<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.storage.form.label.firm" path="firm" />
	<acme:form-textbox code="authenticated.storage.form.label.responsibilityStatement" path="responsibilityStatement" />

	<acme:form-submit test="${command == 'create'}" code="authenticated.storage.form.button.create" action="/authenticated/storage/create"/>
	 

	<acme:form-return code="authenticated.storage.form.button.return" />
</acme:form>