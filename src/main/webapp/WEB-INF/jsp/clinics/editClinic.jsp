<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="Clinics">

	<jsp:body>
        <h2>
            New Clinic
        </h2>
        <form:form modelAttribute="clinic" action="save" class="form-horizontal">
            <input type="hidden" name="id" value="${clinic.id}" />
            <div class="form-group has-feedback">
                <petclinic:inputField label="Name" name="name" />
                <petclinic:inputField label="City" name="city" />
                <petclinic:inputField label="Email" name="email" />
                <petclinic:inputField label="Address" name="address" />
                 <petclinic:inputField label="Telephone" name="telephone" />
                </div>
                
                
                
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    
                        
                            <button class="btn btn-default"
						type="submit">Add Clinic</button>
                       
                       
                  
                </div>
            </div>
        </form:form>
       
    </jsp:body>
</petclinic:layout>
