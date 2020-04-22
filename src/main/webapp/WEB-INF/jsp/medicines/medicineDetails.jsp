<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="medicine">

    <h2>Medicine Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${medicine.name}"/></b></td>
        </tr>
        <tr>
            <th>Components</th>
            <td><c:out value="${medicine.components}"/></td>
        </tr>
        <tr>
            <th>Treatment</th>
            <td><c:out value="${medicine.treatment}"/></td>
        </tr>
        <tr>
            <th>Pet Type</th>
            <td><c:out value="${medicine.petType.name}"/></td>
        </tr>
        <tr>
            <th>Sickness</th>
            <td><c:out value="${medicine.sickness.name}"/></td>
        </tr>
    </table>

</petclinic:layout>
