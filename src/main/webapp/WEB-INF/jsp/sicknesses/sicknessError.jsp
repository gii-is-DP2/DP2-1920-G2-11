<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="sicknessesError">
	
	<spring:url value="/resources/images/frankomodo.png" var="petsImage"/>
    <img src="${petsImage}"/>
   <p>Tu mascota es algo exótica, por lo tanto no disponemos de un listado de enfermedades para su especie.</p>
   <a href="mailto:petclinicDP2@hotmail.com">Para mas información haz click aquí</a>
</petclinic:layout>
