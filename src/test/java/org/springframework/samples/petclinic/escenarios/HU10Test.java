
package org.springframework.samples.petclinic.escenarios;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU10Test {

	@Autowired
	private ProductService productService;


	//Caso positivo
	@Test
	void shouldFindProductWithCorrectClinicId() {
		List<Product> products = this.productService.findProductByClinicId(2);
		Assertions.assertTrue(!products.isEmpty());
	}

	//Caso negativo
	@Test
	void shouldProductsEmpty() {
		List<Product> product = this.productService.findProductByClinicId(3);
		Assertions.assertTrue(product.isEmpty());
	}
}
