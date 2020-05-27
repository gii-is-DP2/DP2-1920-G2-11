<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vaccine">
    <h2>
       
        Vaccine
    </h2>
    
    <form:form modelAttribute="vaccine"  class="form-horizontal" id="add-vaccine-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="name"/>
            <petclinic:inputField label="Components" name="components"/>
            <petclinic:inputField label="Months" name="months"/>
           

			
				<input type="hidden" name="sickness" value="${sickness}" />
		
       

      
    </div>
        
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            
            
           
									
						<button class="btn btn-default" type="submit">Update Vaccine</button>
						<input class="btn btn-default" type="button" onclick="history.back()" name="return" value="Return">
					
				
            
            
                <!-- <input type="hidden" name="id" value="${vaccines.id}"/>
                <button class="btn btn-default" type="submit">Save Vaccine</button>   -->
                
                
            </div>
        </div>
    </form:form>
</petclinic:layout>