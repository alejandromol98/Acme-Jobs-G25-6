<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>
<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
<acme:form-textbox code="administrator.storage.form.label.firm" path="firm" readonly="true"/>
	<acme:form-textbox code="administrator.storage.form.label.responsibilityStatement" path="responsibilityStatement" readonly="true"/>
	<acme:form-select code="administrator.storage.form.label.status" path="status">
        <acme:form-option code="administrator.storage.form.label.status.pending" value="PENDING" selected="${status == 'PENDING'}"/>
        <acme:form-option code="administrator.storage.form.label.status.accepted" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
    </acme:form-select>
	
	<acme:form-submit code="administrator.storage.form.button.update" action="update"/>
	
	<acme:form-return code="administrator.storage.form.button.return"/>
	
</acme:form>