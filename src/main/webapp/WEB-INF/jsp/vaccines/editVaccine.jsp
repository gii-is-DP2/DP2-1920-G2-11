<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="vaccine">


<script>
function validateForm()
{
    if(document.frm.name.value=="")
    {
      alert("Name shouldn't be left blank");
      document.frm.name.focus();
      return false;
    }
    else if(document.frm.components.value=="")
    {
      alert("Components shouldn't be left blank");
      document.frm.components.focus();
      return false;
    } 
    
    else if(document.frm.months.value=="")
    {
      alert("Months shouldn't be left blank");
      document.frm.months.focus();
      return false;
    } 
    
    else if(isNaN(document.frm.months.value))
    {
      alert("Months must be a number");
      document.frm.months.focus();
      return false;
    } 
    
    else if(document.frm.months.value<0)
    {
      alert("Months must be greater than zero");
      document.frm.months.focus();
      return false;
    } 
    else if(document.frm.name.value.length<3 ||  document.frm.name.value.length>50 )
    {
      alert("Name must have a length between 3 and 50 characters");
      document.frm.name.focus();
      return false;
    } 
}

</script>



	<h2>
		<c:if test="${vaccines['new']}">New</c:if>
		Vaccine
	</h2>

	<form:form modelAttribute="vaccines" name="frm" onsubmit="return validateForm()" class="form-horizontal"
		id="add-vaccine-form">
		<div class="form-group has-feedback">
			<petclinic:inputField  label="Name" name="name" />
			<petclinic:inputField  label="Components" name="components" />
			<petclinic:inputField  label="Months" name="months" />


			<c:if test="${vaccines['new']}">
				<div class="control-group">
					<label>Sickness:</label>
					<form:select name="sicknessId" path="sickness"
						items="${sicknesses}" itemLabel="name" itemValue="id" />
				</div>
			</c:if>

			<c:if test="${!vaccines['new']}">
				<input type="hidden" name="sickness" value="${sickness.id}" />
			</c:if>





		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">


				<c:choose>
					<c:when test="${vaccines['new']}">
						<button class="btn btn-default" type="submit">Add Vaccine</button>
						<input class="btn btn-default" type="button"
							onclick="history.back()" name="return" value="Return">
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" type="submit">Update
							Vaccine</button>
						<input class="btn btn-default" type="button"
							onclick="history.back()" name="return" value="Return">
					</c:otherwise>
				</c:choose>


				<!-- <input type="hidden" name="id" value="${vaccines.id}"/>
                <button class="btn btn-default" type="submit">Save Vaccine</button>   -->


			</div>
		</div>
	</form:form>
</petclinic:layout>