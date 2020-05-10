

package org.springframework.samples.petclinic.escenarios;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU17Test {

	@Autowired
	private ProductService productService;


	//Caso positivo
	@Test
	void deleteProductCorrectly() {
		Collection<Product> products = productService.findProducts();
		int size= products.size();
		Product product = this.productService.findProductById(1);
		this.productService.delete(product);
		Assertions.assertTrue(size>productService.findProducts().size());
	}

	//Caso negativo
	//TODO: Sale bien pero preguntar por si acaso
	@Test
	void deleteProductNotCorrectly() {
		Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			this.productService.delete(productService.findProductById(111));
		});
	}
}
