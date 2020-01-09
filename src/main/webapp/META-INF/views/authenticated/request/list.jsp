<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
<acme:list-column code="authenticated.request.list.label.moment" path="moment" width="20%"/>
<acme:list-column code="authenticated.request.list.label.title" path="title" width="40%"/>
</acme:list>
<acme:menu-suboption code="master.menu.authenticated.create.request" action="/authenticated/request/create" />
