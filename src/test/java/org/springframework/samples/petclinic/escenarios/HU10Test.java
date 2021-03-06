
package org.springframework.samples.petclinic.escenarios;

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
public class HU10Test {

	@Autowired
	private ProductService productService;


	// Caso positivo
	@Test
	void shouldFindProductWithCorrectId() {
		Product product = this.productService.findProductById(1);
		Assertions.assertTrue(product.getName().equals("champu hidratante") && product.getDescription().equals("para pelo seco") && product.getPrice().equals(4.00) && product.getStock().equals(3) && product.getClinic().getId().equals(1)
			&& product.getProductType().getId().equals(1));
	}

	// Caso negativo
	@Test
	void shouldProductEmpty() {
		Product product1 = this.productService.findProductById(4);
		Assertions.assertTrue(product1.getName().equals("champu para gato") && product1.getDescription().isEmpty()

		);
	}
}
