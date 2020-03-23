<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="vaccinesError">
   <p>Tu enfermedad es algo rara, por lo tanto de no disponemos de un listado de vacunas para su enfermedad.</p>
   <a href="mailto:petclinicDP2@hotmail.com">Para mas información haz click aquí</a>
</petclinic:layout>
