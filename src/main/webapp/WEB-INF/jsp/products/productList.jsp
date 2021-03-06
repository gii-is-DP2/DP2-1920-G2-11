
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="products">
    <h2>Products</h2>

    <table id="productsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
             <th>Clinic</th>
           <th>Delete</th>
             <th>Edit</th>
            
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>
                  <spring:url value="/products/{productId}" var="productUrl"> 
                        <spring:param name="productId" value="${product.id}"/>
                       
                    </spring:url>
                    <a href="${fn:escapeXml(productUrl)}">${product.name}</a>
                </td>
                
                 <td>
                  <spring:url value="/clinics/{clinicId}/products" var="productUrl"> 
                        <spring:param name="clinicId" value="${product.clinic.id}"/>
                       
                    </spring:url>
                    <a href="${fn:escapeXml(productUrl)}">${product.clinic}</a>
                </td>
                <td>
                    <spring:url value="/products/delete/{productId}" var="productUrl">
                        <spring:param name="productId" value="${product.id}"/>
                       
                    </spring:url>
                    <a href="${fn:escapeXml(productUrl)}">Delete</a>
                </td>
                <td>
                    <spring:url value="/products/edit/{productId}" var="productUrl">
                        <spring:param name="productId" value="${product.id}"/>
                       
                    </spring:url>
                    <a href="${fn:escapeXml(productUrl)}">Edit</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <spring:url value="/products/new" var="productUrl">
                    </spring:url>
                    <a href="${fn:escapeXml(productUrl)}">Create</a>
    
</petclinic:layout>
