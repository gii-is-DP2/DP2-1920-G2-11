package org.springframework.samples.petclinic.escenarios;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.ProductValidator;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class HU15Test {

	//TODO MODIFICAR VACUNA
	@Autowired
	private ProductService productService;
	
	private ProductValidator createValidator() {
		return new ProductValidator();
	}



	//Caso positivo
	@Test
	void EditProductCorrect() {
		
		Product product = this.productService.findProductById(2);
	    product.setName("producto cambiado");
	    
		ProductValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(product, "product");
		validator.validate(product, errors);
		Assertions.assertThat(errors.getAllErrors()).isEmpty();
		
		this.productService.save(product);
		
		
	}

//caso negativos
	
	
	@Test
	void editProductNameNotCorrectly() {
		Product product = this.productService.findProductById(3);
	    product.setName("");
		ProductValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(product, "product");
		validator.validate(product, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("name")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must not be blank");
	}
	
	
}

