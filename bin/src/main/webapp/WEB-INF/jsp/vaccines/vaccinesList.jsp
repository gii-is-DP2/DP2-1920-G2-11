<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="sicknesses">
    <h2>Vaccines</h2>

    <table id="vaccinesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Components</th>
            <th>Months of age</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vaccines}" var="vaccine">
            <tr>
                <td>
                    <c:out value="${vaccine.name}"/>
                </td>
                <td>
                    <c:out value="${vaccine.components}"/>
                </td>
                <td>
                    <c:out value="${vaccine.months}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
