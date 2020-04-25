
package org.springframework.samples.petclinic.model;

import org.apache.logging.log4j.util.Strings;
import org.assertj.core.internal.Integers;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VaccineValidator implements Validator {

	@Override
	public boolean supports(final Class<?> clazz) {
		return Vaccine.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {

		Vaccine vaccine = (Vaccine) target;

		if (Strings.isBlank(vaccine.getName())) {
			errors.rejectValue("name", "must not be blank", "must not be blank");
		}

		if (Strings.isBlank(vaccine.getComponents())) {
			errors.rejectValue("components", "must not be blank", "must not be blank");
		}
		
		if (vaccine.getComponents().length() > 70) {
			errors.rejectValue("components", "must lower than 70", "must lower than 70");
		}
		
		if (vaccine.getName().length() > 70) {
			errors.rejectValue("name", "must lower than 70", "must lower than 70");
		}

		if (vaccine.getMonths()<0) {
			errors.rejectValue("months", "must be positive number", "must be positive number");
		}
		
		if (vaccine.getMonths()>60) {
			errors.rejectValue("months", "must lower than 60", "must lower than 60");
		}
		
		if(vaccine.getMonths()== null) {
			errors.rejectValue("months", "must not be null", "must not be null");
		}
		
		

		
	}

}
