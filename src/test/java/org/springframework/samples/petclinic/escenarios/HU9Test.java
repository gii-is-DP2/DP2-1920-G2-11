
package org.springframework.samples.petclinic.escenarios;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class HU9Test {

	@Autowired
	private ProductService productService;


	//Caso positivo
	@Test
	void shouldFindProductWithCorrectClinicId() {
		List<Product> products = this.productService.findProductsByClinicId(2);
		Assertions.assertTrue(!products.isEmpty());
	}

	//Caso negativo
	@Test
	void shouldProductsEmpty() {
		List<Product> product = this.productService.findProductsByClinicId(3);
		Assertions.assertTrue(product.isEmpty());
	}
}
