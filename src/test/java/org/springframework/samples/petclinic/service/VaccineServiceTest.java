
package org.springframework.samples.petclinic.service;

import java.util.List;

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
		Vaccine vaccine = this.vaccineService.vaccineById(1);
		Assertions.assertTrue(vaccine.getName().equals("Vacuna A") && vaccine.getMonths().equals(4) && vaccine.getComponents().equals("Q,W,E,R") && vaccine.getSickness().getName().equals("Otitis"));
	}
}
