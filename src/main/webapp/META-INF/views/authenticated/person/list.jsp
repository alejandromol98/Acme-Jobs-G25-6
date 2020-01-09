<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.person.list.label.username" path="authenticated.userAccount.username" width="40%"/>
	<acme:list-column code="authenticated.person.list.label.title" path="messageThread.title" width="40%"/>
</acme:list>

<acme:form>
	<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/person/create?messageThread.id=${param.id}')"
	class="btn btn-default">
		<acme:message code="authenticated.person.form.label.create" />
	</button>
	<acme:form-return code="authenticated.person.form.button.return"/>
</acme:form>