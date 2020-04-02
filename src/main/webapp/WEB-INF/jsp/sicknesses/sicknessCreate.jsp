<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="sicknessCreate">
    <h2>
        New Sickness
    </h2>
    
    <form:form modelAttribute="sickness" class="form-horizontal" id="add-sickness-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="name"/>
            <petclinic:inputField label="Cause" name="cause"/>
            <petclinic:inputField label="Symptom" name="symptom"/>
            <petclinic:inputField label="Severity" name="severity"/>
            <div class="control-group">
            	<petclinic:selectField name="type" label="Type " names="${types}" size="5"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="id" value="${sickness.id}"/>
                <button class="btn btn-default" type="submit">Save Sickness</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>