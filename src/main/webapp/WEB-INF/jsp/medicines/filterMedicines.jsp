<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="medicines">
    <h2>Medicines</h2>

   		<form id="medicinesform">
			<label for="petTypes">Choose a Type:</label>
			<select id="petTypeId" name="petTypeId" form="medicinesform">
				<c:forEach items="${petTypes}" var="petType">
			  			<option value="${petType.id}" ${petType.id == petTypeId ? 'selected="select"' : ''} >${petType.name}</option>
			 	</c:forEach>
			</select>
			<label for="sickness">Choose a Sickness:</label>
			<select id="sicknessId" name="sicknessId" form="medicinesform">
				<c:forEach items="${sicknesses}" var="sickness">
						<option value="${sickness.id}" ${sickness.id == sicknessId ? 'selected="select"' : ''} >${sickness.name}</option>
				</c:forEach>
			</select>
		  	<input type="submit">
		</form>

   

		
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
                 <td>
                  
                    <spring:url value="/owner/medicine/{medicineId}" var="medicineUrl">
                        <spring:param name="medicineId" value="${medicine.id}"/>        
                    </spring:url>
                     <a href="${fn:escapeXml(medicineUrl)}">Go</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
