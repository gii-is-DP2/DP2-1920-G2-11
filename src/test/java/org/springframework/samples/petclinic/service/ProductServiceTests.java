
package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class ProductServiceTests {

	@Autowired
	private ProductService productService;


	@Test
	void shouldFindProductWithCorrectId() {
		Product product = this.productService.findProductById(1);
		Assertions.assertTrue(product.getName().equals("champu hidratante"));
		Assertions.assertTrue(product.getPrice().equals(4.00));
		Assertions.assertTrue(product.getDescription().equals("para pelo seco"));
		Assertions.assertTrue(product.getProductType().getId().equals(1));
		Assertions.assertTrue(product.getStock().equals(3));

	}

	@Test
	void shouldFindProductsCorrect() {
		Collection<Product> products = this.productService.findProducts();
		Assertions.assertTrue(!products.isEmpty());

	}
	@Test
	void shouldFindProductsByClinicError() {
		Collection<Product> products1 = this.productService.findProductsByClinicId(0);
		Assertions.assertTrue(products1.isEmpty());
	}

	@Test
	void shouldFindProductsByClinicCorrect() {
		Collection<Product> products2 = this.productService.findProductsByClinicId(1);
		Assertions.assertTrue(!products2.isEmpty());
	}

	@Test
	@Transactional
	void createProductTest() {
		Collection<Product> products = this.productService.findProducts();
		int size = products.size();
		Product product = new Product();
		product.setId(31);
		product.setName("Producto X");
		product.setDescription("Descripcion X");
		product.setPrice(2.0);
		product.setStock(2);
		this.productService.save(product);
		Assertions.assertTrue(size < this.productService.findProducts().size());

	}

	@Test
	@Transactional
	void deleteProductTest() {
		Collection<Product> products = this.productService.findProducts();
		int size = products.size();
		Product product = this.productService.findProductById(2);
		this.productService.delete(product);
		Assertions.assertTrue(size > this.productService.findProducts().size());
	}
}
