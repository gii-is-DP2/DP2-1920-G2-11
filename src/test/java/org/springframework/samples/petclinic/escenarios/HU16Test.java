
package org.springframework.samples.petclinic.escenarios;

import java.util.Collection;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.ProductValidator;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class HU16Test {

	@Autowired
	private ProductService productService;


	private ProductValidator createValidator() {
		return new ProductValidator();
	}

	//Caso positivo
	@Test
	@Transactional
	void createProductCorrectly() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Collection<Product> products = this.productService.findProducts();
		int size = products.size();
		Product product = new Product();
		product.setId(20);
		product.setName("Producto X");
		product.setDescription("Descripcion X");
		product.setStock(2);
		product.setPrice(2.0);
		product.setStock(2);
		this.productService.save(product);
		Assertions.assertTrue(size < this.productService.findProducts().size());

	}

	//Casos negativos
	@Test
	@Transactional
	void createProductNotCorrectly() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Product product = new Product();
		product.setId(20);
		product.setName("Producto X");
		product.setDescription("Descripcion X");
		product.setPrice(2.0);
		product.setStock(-1);

		ProductValidator pv = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(product, "product");
		pv.validate(product, errors);

		Assertions.assertTrue(errors.getErrorCount() == 1);
	}
}
