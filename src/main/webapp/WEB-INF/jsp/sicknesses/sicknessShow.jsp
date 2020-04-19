<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="sicknessShow">

	<h2>Sickness Information</h2>
	
    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><c:out value="${sickness.name}"/></td>
        </tr>
        <tr>
            <th>Cause</th>
            <td><c:out value="${sickness.cause}"/></td>
        </tr>
        <tr>
            <th>Severity</th>
            <td><c:out value="${sickness.severity}"/></td>
        </tr>
        <tr>
            <th>Symptom</th>
            <td><c:out value="${sickness.symptom}"/></td>
        </tr>
        <tr>
            <th>Type</th>
            <td><c:out value="${sickness.type}"/></td>
        </tr>
    </table>
</petclinic:layout>
