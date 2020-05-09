<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="medicineDetailsError">
    <h2>Medicine Error</h2>
   <p>Esta medicina no existe en el servidor</p>
   <spring:url value="/owner/medicines/" var="medicinesUrl">      
   </spring:url>
   <p>Pulse <a href="${fn:escapeXml(medicinesUrl)}">aquí</a> para volver a la lista completa.</p>
</petclinic:layout>