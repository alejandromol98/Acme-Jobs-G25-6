<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.offer.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="authenticated.offer.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.offer.form.label.text" path="text"/>
	<acme:form-money code="authenticated.offer.form.label.min" path="min"/>
	<acme:form-money code="authenticated.offer.form.label.max" path="max"/>
	
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="authenticated.offer.form.label.moment" path="moment" readonly="true"/>
	</jstl:if>
	
	<acme:form-moment code="authenticated.offer.form.label.deadline" path="deadline"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox code="anonymous.user-account.label.accept" path="accept" />
	</jstl:if>
	
	<acme:form-submit test="${command=='create'}" code="authenticated.offer.form.button.create"
		action="/authenticated/offer/create" />	
	
	<acme:form-return code="authenticated.offer.form.button.return"/>

</acme:form>