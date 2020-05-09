<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="productTypes">
    <h2>ProductTypes</h2>

    <table id="productsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
           <th>Price</th>
            
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${productTypes}" var="productType">
            <tr>
                <td>
                    <spring:url value="/products/productType/{productTypeId}" var="productTypeUrl">
                        <spring:param name="productTypeId" value="${productType.id}"/>
                       
                    </spring:url>
                    <a href="${fn:escapeXml(productTypeUrl)}">${productType.description}</a>
                </td>
                <td>
                </td>
                <td>
                </td>
                <td>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
</petclinic:layout>
