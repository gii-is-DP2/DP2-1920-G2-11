<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="productShow">

	<h2>Product Information</h2>
	
    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${product.name}"/></b></td>
        </tr>
        <tr>
            <th>Price</th>
            <td><c:out value="${product.price}"/></td>
        </tr>
        <tr>
            <th>Stock</th>
            <td><c:out value="${product.stock}"/></td>
        </tr>
    </table>
</petclinic:layout>