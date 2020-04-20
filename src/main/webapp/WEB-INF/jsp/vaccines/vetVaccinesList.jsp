<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="vaccines">
    <h2>Vaccines</h2>

    <table id="vaccinesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Prueba</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vaccines}" var="vaccine">
            <tr>
                <td>
                    <spring:url value="/vets/sickness/{sicknessId}/vaccines" var="vaccineUrl">
                        <spring:param name="sicknessId" value="${sickness.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(vaccineUrl)}">${vaccine.name}</a>
                </td>
                
            </tr>
        </c:forEach>
           <c:forEach items="${vaccines}" var="vaccine">
            <tr>
                <td>
                    <c:out value="${vaccine.months}" />
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
