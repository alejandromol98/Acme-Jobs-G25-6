<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.investor.form.label.name" path="name"/>
	<acme:form-textbox code="anonymous.investor.form.label.sector" path="sector"/>
	<acme:form-textarea code="anonymous.investor.form.label.statement" path="statement"/>
	<acme:form-textbox code="anonymous.investor.form.label.stars" path="stars"/>
	
	<acme:form-return code="administrator.investor.button.return"/>
</acme:form>