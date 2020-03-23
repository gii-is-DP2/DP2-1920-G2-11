
package org.springframework.samples.petclinic.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.SicknessRepository;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class VaccineServiceTest {

	@Autowired
	private VaccineRepository	vaccineRepository;

	@Autowired
	private SicknessRepository	sicknessRepository;


	@Test
	void shouldFindVaccinesWithCorrectId() {
		Vaccine vaccine4 = this.vaccineRepository.findById(4);
		Assertions.assertTrue(vaccine4.getName().equals("Vacuna D"));
		Assertions.assertTrue(vaccine4.getSickness().getName().equals("Leucemia felina"));
	}

	@Test
	void shouldNotFoundVaccinesWithCorrectId() {
		List<Vaccine> vaccines = this.vaccineRepository.findVaccinesBySicknessId(28);
		Assertions.assertTrue(vaccines.isEmpty());
	}
}
