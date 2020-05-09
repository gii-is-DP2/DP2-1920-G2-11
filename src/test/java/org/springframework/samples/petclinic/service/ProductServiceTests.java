
package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.repository.ProductRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ProductServiceTests {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;


	@Test
	void shouldFindProductWithCorrectId() {
		Product product = this.productRepository.findById(1);
		Assertions.assertTrue(product.getName().equals("chámpu hidratante"));
		Assertions.assertTrue(product.getPrice().equals(4.00));
		Assertions.assertTrue(product.getDescription().equals("para pelo seco"));
		Assertions.assertTrue(product.getProductType().getId().equals(1));
		Assertions.assertTrue(product.getStock().equals(3));

	}
	
	@Test
	void createProductTest() {
		Collection<Product> products = productService.findProducts();
		int size= products.size();
		Product product = new Product();
		product.setId(31);
		product.setName("Producto X");
		product.setDescription("Descripcion X");
		product.setPrice(2.0);
		product.setStock(2);
		this.productService.save(product);
		Assertions.assertTrue(size<productService.findProducts().size());

	}

	@Test
	void deleteProductTest() {
		Collection<Product> products = productService.findProducts();
		int size= products.size();
		Product product = this.productService.findProductsById(1);
		this.productService.delete(product);
		Assertions.assertTrue(size>productService.findProducts().size());
	}
}
