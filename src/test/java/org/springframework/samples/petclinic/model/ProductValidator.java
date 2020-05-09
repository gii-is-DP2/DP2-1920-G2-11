
package org.springframework.samples.petclinic.model;

import org.apache.logging.log4j.util.Strings;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(final Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {

		Product product = (Product) target;

		if (Strings.isBlank(product.getName())) {
			errors.rejectValue("name", "must not be blank", "must not be blank");
		}

		if (product.getStock() < 0) {
			errors.rejectValue("stock", "must be at least 0", "must be at least 0");
		}
		if (product.getPrice() < 0) {
				errors.rejectValue("price", "must be at least 0", "must be at least 0");
		}	

		if (product.getName().length() > 70) {
			errors.rejectValue("name", "must lower than 70", "must lower than 70");
		}

		
	}
}


