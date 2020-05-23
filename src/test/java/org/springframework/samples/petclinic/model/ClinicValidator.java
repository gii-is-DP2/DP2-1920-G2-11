package org.springframework.samples.petclinic.model;

import org.apache.logging.log4j.util.Strings;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ClinicValidator implements Validator {

	@Override
	public boolean supports(final Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(final Object target, final Errors errors) {

		Clinic clinic = (Clinic) target;
		
		if (Strings.isBlank(clinic.getName())) {
			errors.rejectValue("name", "must not be blank", "must not be blank");
		}	
}
} 
