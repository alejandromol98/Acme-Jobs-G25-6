<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.offer.list.label.ticker" path="ticker" width="20%" />
	<acme:list-column code="authenticated.offer.list.label.title" path="title" width="40%" />
	<acme:list-column code="authenticated.offer.list.label.min" path="min" width="10%" />
	<acme:list-column code="authenticated.offer.list.label.max" path="max" width="10%" />
	
	<acme:menu-suboption code="master.menu.authenticated.create.offer" action="/authenticated/offer/create" />
</acme:list>