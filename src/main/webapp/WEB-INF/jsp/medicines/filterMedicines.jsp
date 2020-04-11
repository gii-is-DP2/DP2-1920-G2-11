<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="medicines">
    <h2>Medicines</h2>
		<form id="medicinesform">
			<label for="petTypes">Choose a Type:</label>
			<select id="petTypeId" name="petTypeId" form="medicinesform">
				<c:forEach items="${petTypes}" var="petType">
			  			<option value="${petType.id}">${petType.name}</option>
			 	</c:forEach>
			</select>
			<label for="sickness">Choose a sickness:</label>
			<select id="sicknessId" name="sicknessId" form="medicinesform">
				<c:forEach items="${sicknesses}" var="sickness">
			  			<option value="${sickness.id}">${sickness.name}</option>
			 	</c:forEach>
			</select>
		  	<input type="submit">
		</form>
		
		    <table id="medicinesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Components</th>
            <th>Treatment</th>
            <th>Pet Type</th>
            <th>Sickness</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${medicines}" var="medicine">
            <tr>
               <td>
                    <c:out value="${medicine.name}"/>
                </td>
                <td>
                    <c:out value="${medicine.components}"/>
                </td>
                <td>
                    <c:out value="${medicine.treatment}"/>
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
