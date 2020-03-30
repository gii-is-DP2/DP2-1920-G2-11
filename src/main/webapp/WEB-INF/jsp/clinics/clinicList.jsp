<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="clinics">
    <h2>Clinics</h2>

    <table id="clinicsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Telephone</th>
            <th>Email</th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clinics}" var="clinic">
            <tr>
                <td>
                    <c:out value="${clinic.name}"/>
                </td>
                <td>
                    <c:out value="${clinic.address}"/>
                </td>
                <td>
                    <c:out value="${clinic.telephone}"/>
                </td>
                <td>
                    <c:out value="${clinic.email}"/>
                </td>
                <td>
                    <spring:url value="/clinics/{clinicId}" var="clinicUrl">
                        <spring:param name="clinicsId" value="${clinic.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(clinicUrl)}">See clinic</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
