<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Sickness">
    <h2>
        Update Sickness
    </h2>
    
    <form:form modelAttribute="sickness" action="updateSickness" class="form-horizontal" id="add-sickness-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="name"/>
            <petclinic:inputField label="Cause" name="cause"/>
            <petclinic:inputField label="Symptom" name="symptom"/>
            <petclinic:inputField label="Severity" name="severity"/>
             <div class="form-group ">
        <label class="col-sm-2 control-label">Type </label>

        <div class="col-sm-10">
            <select id="type" name="type" class="form-control" size="5" required>
            <option value="bird">bird</option>
            <option value="cat">cat</option>
            <option value="dog">dog</option>
            <option value="hamster">hamster</option>
            <option value="komodo dragon">komodo dragon</option>
            <option value="lizard">lizard</option>
            <option value="snake">snake</option></select>
            
                <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
            
            
        </div>
    </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input type="hidden" name="id" value="${sickness.id}"/>
                <button class="btn btn-default" type="submit">Update Sickness</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>