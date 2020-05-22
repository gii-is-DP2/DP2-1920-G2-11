<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<petclinic:layout pageName="clinics">
	<h2>Clinics</h2>
	

	<table id="clinicsTable" class="table table-striped">
		<thead>
			<tr>
				<th>Name</th>
				
				  <th>Action</th>
           
				<th>Products</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${clinics}" var="clinic">

				<tr>
					<td>
						 <spring:url
							value="/clinics/{clinicId}" var ="clinicUrl">
							<spring:param name="clinicId" value="${clinic.id}" />
						</spring:url> <a href="${fn:escapeXml(clinicUrl)}">${clinic.name}</a>

					</td>
					<td>
					
                    <spring:url value="/clinics/{clinicId}/delete" var ="clinicUrl1">
                        <spring:param name="clinicId" value="${clinic.id}"/>
                       
                    </spring:url>
                    <a href="${fn:escapeXml(clinicUrl1)}">Delete</a>
                    
                </td>
					<td><spring:url value="/clinics/{clinicId}/products"
							var="productUrl">
							<spring:param name="clinicId" value="${clinic.id}" />
						</spring:url> <a href="${fn:escapeXml(productUrl)}">See products</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</petclinic:layout>
