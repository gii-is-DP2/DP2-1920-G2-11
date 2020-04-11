<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Vaccine">
    <h2>
        New Vaccine
    </h2>
    
    <form:form modelAttribute="vaccines" action="saveVaccines" class="form-horizontal" id="add-vaccine-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="name"/>
            <petclinic:inputField label="Components" name="components"/>
            <petclinic:inputField label="Months" name="months"/>
            <div class="control-group">
                    <petclinic:selectField name="sickness.name" label="Sickness" names="${sickness}" size="5"/>
                </div>
            
       

      
    </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="id" value="${vaccines.id}"/>
                <button class="btn btn-default" type="submit">Save Vaccine</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>