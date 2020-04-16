
package org.springframework.samples.petclinic.escenarios;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU4Test {

	@Autowired
	private VaccineRepository vaccineRepository;


	//Caso positivo
	@Test
	void shouldFindVaccineWithCorrectId() {
		Optional<Vaccine> vaccine = this.vaccineRepository.findById(2);
		Assertions.assertTrue(vaccine.get().getName().equals("Vacuna B") && vaccine.get().getComponents().equals("A,S,D,F") && vaccine.get().getMonths().equals(6) && vaccine.get().getSickness().getName().equals("Conjuntivitis"));
	}

	//Caso negativo
	@Test
	void shouldVaccineWithoutDetails() {
		Optional<Vaccine> vaccine = this.vaccineRepository.findById(8);
		Assertions.assertTrue(vaccine.get().getName().equals("Vacunote") && vaccine.get().getComponents().isEmpty() && vaccine.get().getMonths().equals(0));
	}
}
