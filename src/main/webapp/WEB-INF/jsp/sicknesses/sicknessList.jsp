<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="sicknesses">
    <h2>Sicknesses</h2>

    <table id="sicknessesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            	 <sec:authorize access="hasAuthority('vet')">
            <th>Vaccines</th>
            </sec:authorize>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sicknesses}" var="sickness">
            <tr>
                <td>
                    <spring:url value="/owners/*/pets/*/sicknesses/{sicknessId}" var="sicknessUrl">
                        <spring:param name="sicknessId" value="${sickness.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(sicknessUrl)}">${sickness.name}</a>
                </td>
                 <sec:authorize access="hasAuthority('vet')">
                <td>
                    <spring:url value="/owners/*/pets/*/sicknesses/{sicknessId}/vaccines" var="vaccineUrl">
                        <spring:param name="sicknessId" value="${sickness.id}"/>
                    </spring:url>
                    <a
                     href="${fn:escapeXml(vaccineUrl)}">See vaccines</a>
                </td>
                </sec:authorize>
                <td>
                    <spring:url value="/owners/*/pets/*/sicknesses/delete/{sicknessId}" var="sicknessDeleteUrl">
                        <spring:param name="sicknessId" value="${sickness.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(sicknessDeleteUrl)}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
     
	
	
    <security:authorize access="hasRole('veterinarian')">
    	<spring:url value="/owners/*/pets/*/sicknesses/new" var="createSicknessUrl"></spring:url>
    	<a href="${fn:escapeXml(createSicknessUrl)}">Create a Sickness</a>
    </security:authorize>
</petclinic:layout>
