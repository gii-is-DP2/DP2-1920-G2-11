<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="medicines">
    <h2>Medicines</h2>

    <table id="medicinesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Pet Type</th>
            <th>Sickness</th>
            <th>View</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${medicines}" var="medicine">
            <tr>
               <td>
                    <c:out value="${medicine.name}"/>
                </td>
               	<td>
                    <c:out value="${medicine.petType}"/>
                </td>
               <td>
                    <c:out value="${medicine.sickness}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
</petclinic:layout>
