<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<acme:form>

	<acme:form-textbox code="authenticated.messageThread.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment 
		code="authenticated.messageThread.form.label.moment" 
		path="moment"
		readonly="true" />
	</jstl:if>
	
	<jstl:if test="${command != 'create'}">
		<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/message/list?id=${id}')"
				class="btn btn-default">
				<acme:message code="authenticated.messageThread.form.messages"/>
		</button>
		<jstl:if test="${author}">
			<button type="button" onclick="javascript: clearReturnUrl(); redirect('/authenticated/person/list?id=${id}')"
					class="btn btn-default">
					<acme:message code="authenticated.messageThread.form.persons"/>
			</button>
		</jstl:if>
	</jstl:if>

	<acme:form-submit test="${command == 'create'}"
	code="authenticated.messageThread.form.button.create"
	action="/authenticated/message-thread/create/" />
	
	
	<acme:form-return code="authenticated.messageThread.form.button.return"/>
	
</acme:form>