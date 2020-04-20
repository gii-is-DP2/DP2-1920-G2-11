
package org.springframework.samples.petclinic.model;

import org.apache.logging.log4j.util.Strings;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SicknessValidator implements Validator {

	@Override
	public boolean supports(final Class<?> clazz) {
		return Sickness.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {

		Sickness sickness = (Sickness) target;

		if (Strings.isBlank(sickness.getName())) {
			errors.rejectValue("name", "must not be blank", "must not be blank");
		}

		if (sickness.getSeverity() == null) {
			errors.rejectValue("severity", "must not be null", "must not be null");
		} else if (sickness.getSeverity() < 0) {
			errors.rejectValue("severity", "must be at least 0", "must be at least 0");
		} else if (sickness.getSeverity() > 3) {
			errors.rejectValue("severity", "must be a maximum of 3", "must be a maximum of 3");
		}

		if (sickness.getName().length() > 70) {
			errors.rejectValue("name", "must lower than 70", "must lower than 70");
		}

		if (sickness.getCause().length() > 150) {
			errors.rejectValue("cause", "must lower than 150", "must lower than 150");
		}

		if (sickness.getSymptom().length() > 150) {
			errors.rejectValue("symptom", "must lower than 150", "must lower than 150");
		}
	}

}
