<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="clinicsShow">

	<h2>Clinic Information</h2>
	
    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${clinics.name}"/></b></td>
        </tr>
     <tr>
            <th>Addres</th>
            <td><c:out value="${clinics.address}"/></td>
        </tr>
        <tr>
            <th>Phone</th>
            <td><c:out value="${clinics.telephone}"/></td>
        </tr>
        
        <tr>
            <th>E-mail</th>
            <td><c:out value="${clinics.email}"/></td>
        </tr> 
        
     
        
    </table>
</petclinic:layout>