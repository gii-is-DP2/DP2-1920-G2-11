<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vaccine">
    <h2>
       <c:if test="${vaccine['new']}">New</c:if>
        Vaccine
    </h2>
    
    <form:form modelAttribute="vaccine"  class="form-horizontal" id="add-vaccine-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="name"/>
            <petclinic:inputField label="Components" name="components"/>
            <petclinic:inputField label="Months" name="months"/>
            <c:if test="${vaccine['new']}">
				<div class="control-group">
					<label>Sickness:</label>
					<form:select name="sicknessId" path="sickness"
						items="${sicknesses}" itemLabel="name" itemValue="id" />
				</div>
			</c:if>

			<c:if test="${!vaccine['new']}">
				<input type="hidden" name="sickness" value="${sickness.id}" />
			</c:if>
       

      
    </div>
        
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            
            
            <c:choose>
					<c:when test="${vaccine['new']}">
						<button class="btn btn-default" type="submit">Add Vaccine</button>
						<input class="btn btn-default" type="button" onclick="history.back()" name="return" value="Return">
					</c:when>
					<c:otherwise>	
						<button class="btn btn-default" type="submit">Update Vaccine</button>
						<input class="btn btn-default" type="button" onclick="history.back()" name="return" value="Return">
					</c:otherwise>
					</c:choose>
            
            
                <!-- <input type="hidden" name="id" value="${vaccines.id}"/>
                <button class="btn btn-default" type="submit">Save Vaccine</button>   -->
                
                
            </div>
        </div>
    </form:form>
</petclinic:layout>