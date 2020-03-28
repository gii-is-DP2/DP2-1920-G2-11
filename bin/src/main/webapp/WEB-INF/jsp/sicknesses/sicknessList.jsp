<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="sicknesses">
    <h2>Sicknesses</h2>

    <table id="sicknessesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Sympton</th>
            <th>Severity</th>
            <th>Vaccines</th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sicknesses}" var="sickness">
            <tr>
                <td>
                    <c:out value="${sickness.name}"/>
                </td>
                <td>
                    <c:out value="${sickness.symptom}"/>
                </td>
                <td>
                    <c:out value="${sickness.severity}"/>
                </td>
                <td>
                    <spring:url value="/owners/*/pets/*/sicknesses/{sicknessId}/vaccines" var="vaccineUrl">
                        <spring:param name="sicknessId" value="${sickness.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(vaccineUrl)}">See vaccines</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>