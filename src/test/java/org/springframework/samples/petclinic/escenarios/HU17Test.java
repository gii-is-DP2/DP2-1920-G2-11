
package org.springframework.samples.petclinic.escenarios;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class HU17Test {

	@Autowired
	private ProductService productService;


	//Caso positivo
	@Test
	@Transactional
	void deleteProductCorrectly() {
		Collection<Product> products = this.productService.findProducts();
		int size = products.size();
		Product product = this.productService.findProductById(1);
		this.productService.delete(product);
		Assertions.assertTrue(size > this.productService.findProducts().size());
	}

	//Caso negativo
	@Test
	@Transactional
	void deleteProductNotCorrectly() {
		Product product = new Product();
		product.setId(111);
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			this.productService.delete(product);
		});
	}
}
