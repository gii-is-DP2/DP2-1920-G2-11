<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 

<petclinic:layout pageName="clinics">
	<h2>Clinics</h2>


	</br>
	




	<table id="clinicsTable" class="table table-striped">
		<thead>
			<tr>
				<th>Name</th>
				<!--     <th>City</th>
            <th>Phone</th> -->
				<th>Products</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${clinics}" var="clinic">

				<tr>
					<td>
						<%--  <c:out value="${clinic.name}"/> --%> <spring:url
							value="/clinics/{clinicId}" var="clinicUrl">
							<spring:param name="clinicId" value="${clinic.id}" />
						</spring:url> <a href="${fn:escapeXml(clinicUrl)}">${clinic.name}</a>

					</td>
					<%--   <td>
                    <c:out value="${clinic.city}"/>
                </td>
                <td>
                    <c:out value="${clinic.telephone}"/>
                </td> --%>
					<td><spring:url value="/clinics/{clinicId}/products"
							var="productUrl">
							<spring:param name="clinicId" value="${clinic.id}" />
						</spring:url> <a href="${fn:escapeXml(productUrl)}">See products</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sec:authorize access="hasAuthority('admin')">
	<spring:url value="/clinics/new" var="clinicUrl">
                    </spring:url>
                    <a class="btn btn-default" href="${fn:escapeXml(clinicUrl)}">Create</a>
						
							</sec:authorize>
</petclinic:layout>
