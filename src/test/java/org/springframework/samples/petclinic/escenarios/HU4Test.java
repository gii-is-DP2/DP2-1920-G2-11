
package org.springframework.samples.petclinic.escenarios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class HU4Test {

	@Autowired
	private VaccineRepository vaccineRepository;


	//Caso positivo
	@Test
	void shouldFindVaccineWithCorrectId() {
		Vaccine vaccine = this.vaccineRepository.findById(2);
		Assertions.assertTrue(vaccine.getName().equals("Vacuna B") && vaccine.getComponents().equals("A,S,D,F") && vaccine.getMonths().equals(6) && vaccine.getSickness().getName().equals("Conjuntivitis"));
	}

	//Caso negativo
	@Test
	void shouldVaccineWithoutDetails() {
		Vaccine vaccine = this.vaccineRepository.findById(8);
		Assertions.assertTrue(vaccine.getName().equals("Vacunote") && vaccine.getComponents().isEmpty() && vaccine.getMonths().equals(0));
	}
}
