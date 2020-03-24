<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="vaccineShow">

	<h2>Vaccine Information</h2>
	
    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${vaccine.name}"/></b></td>
        </tr>
        <tr>
            <th>Months</th>
            <td><c:out value="${vaccine.months}"/></td>
        </tr>
        <tr>
            <th>Components</th>
            <td><c:out value="${vaccine.components}"/></td>
        </tr>
    </table>
</petclinic:layout>
