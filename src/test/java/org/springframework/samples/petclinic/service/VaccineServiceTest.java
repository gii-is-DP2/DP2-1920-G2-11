
package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class VaccineServiceTest {

	@Autowired
	private VaccineService vaccineService;


	@Test
	void findVaccinesBySicknessIdTest() {
		List<Vaccine> vaccines = this.vaccineService.findVaccinesBySicknessId(6);
		Assertions.assertTrue(!vaccines.isEmpty() && vaccines.size() == 3 && vaccines.get(0).getName().equals("Vacuna F") && vaccines.get(2).getName().equals("Vacunote"));
	}

	@Test
	void findVaccineByIdTest() {
		Optional<Vaccine> vaccine = this.vaccineService.findVaccineById(1);
		Assertions.assertTrue(vaccine.get().getName().equals("Vacuna A") && vaccine.get().getMonths().equals(4) && vaccine.get().getComponents().equals("Q,W,E,R") && vaccine.get().getSickness().getName().equals("Otitis"));
	}
}
