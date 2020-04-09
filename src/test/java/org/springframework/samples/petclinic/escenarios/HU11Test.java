package org.springframework.samples.petclinic.escenarios;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.repository.ProductRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU11Test {

	@Autowired
	private ProductRepository productRepository;


	//Caso positivo
	@Test
	void shouldFindProductWithCorrectId() {
		Product product = this.productRepository.findById(1);
		Assertions.assertTrue( product.getName().equals("champú hidratante"));
	}

	//Caso negativo
	@Test
	void shouldSicknessesEmpty() {
		Product product = this.productRepository.findById(4);
		Assertions.assertTrue(product.getName().equals(("champú para gato"))
				&& product.getDescription().isEmpty() 
				//&& product.getPrice().equals(null)
				);
	}
}
