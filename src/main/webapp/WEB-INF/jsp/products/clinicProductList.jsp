<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="clinics">
    <h2>Clinic products</h2>

    <table id="clinicProductsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
           <th>Price</th>
            
            
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
                <c:out value="${product.price}" />
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
     <form:form modelAttribute="productType"
                   class="form-horizontal">
                     <div class="control-group">
                    <petclinic:selectField name="type" label="Type " names="${ProductType}" size="5"/>
                </div>
           
                   
                   </form:form>
</petclinic:layout>
