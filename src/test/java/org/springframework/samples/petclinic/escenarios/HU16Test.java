

package org.springframework.samples.petclinic.escenarios;

import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.ProductValidator;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.SicknessValidator;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.ProductRepository;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU16Test {

	@Autowired
	private VaccineRepository vaccineRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductService productService;

	
	private ProductValidator createValidator() {
		return new ProductValidator();
	}
	//Caso positivo
	@Test
	void createProductCorrectly() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Product product = new Product();
		product.setId(31);
		product.setName("Producto X");
		product.setDescription("Descripcion X");
		product.setPrice(2.0);
		product.setStock(2);
		this.productService.save(product);

	}

	//Casos negativos
	@Test
	void createProductNotCorrectly() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Product product = new Product();
		product.setId(31);
		product.setName("Producto X");
		product.setDescription("Descripcion X");
		product.setPrice(2.0);
		product.setStock(-1);
		
		ProductValidator pv = new ProductValidator();
		Errors errors = new BeanPropertyBindingResult(product, "product");
		pv.validate(product, errors);

		
		Assertions.assertTrue(errors.getErrorCount()==1);
	}
}
