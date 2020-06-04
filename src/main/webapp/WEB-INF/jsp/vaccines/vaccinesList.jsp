<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="vaccines">
    <h2>Vaccines</h2>

    <table id="vaccinesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <sec:authorize access="hasAuthority('veterinarian')">
            <th>Actions</th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vaccines}" var="vaccine">
            <tr>
                <td>
                    <spring:url value="/owners/*/pets/*/sicknesses/{sicknessId}/vaccines/{vaccineId}" var="vaccineUrl">
                        <spring:param name="vaccineId" value="${vaccine.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(vaccineUrl)}">${vaccine.name}</a>
                </td>
                
               <sec:authorize access="hasAuthority('veterinarian')">
                <td>
                    <spring:url value="/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/delete" var="vaccineDel">
                        <spring:param name="vaccineId" value="${vaccine.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(vaccineDel)}">Delete</a>
                    
                    
                    <spring:url value="/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/edit" var="vaccineEdit">
                        <spring:param name="vaccineId" value="${vaccine.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(vaccineEdit)}">Edit</a>
                </td>
                </sec:authorize>
              
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
</petclinic:layout>
