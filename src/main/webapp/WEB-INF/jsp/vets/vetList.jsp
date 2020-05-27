<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="vets">
    <h2>Veterinarians</h2>

    <table id="vetsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Specialties</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vets.vetList}" var="vet">
            <tr>
                <td>
                    <c:out value="${vet.firstName} ${vet.lastName}"/>
                </td>
                <td>
                    <c:forEach var="specialty" items="${vet.specialties}">
                        <c:out value="${specialty.name} "/>
                    </c:forEach>
                    <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    

    <table class="table-buttons">
        <tr>
			<td><spring:url value="/vets/newSickness" var="sicknessNewUrl">
				</spring:url> <a href="${fn:escapeXml(sicknessNewUrl)}">Create a sickness for a pet type</a></td>
				 <sec:authorize access="hasAuthority('veterinarian')">
				 <spring:url value="/vets/newVaccine" var="createVaccineUrl"></spring:url>
				
		<a class="btn btn-default" href="${fn:escapeXml(createVaccineUrl)}" >Add Vaccine</a>
		 </sec:authorize>
		</tr>
    </table>
</petclinic:layout>
