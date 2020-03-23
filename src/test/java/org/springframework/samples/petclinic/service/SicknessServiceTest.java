
package org.springframework.samples.petclinic.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.SicknessRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SicknessServiceTest {

	@Autowired
	private SicknessRepository	sicknessRepository;

	@Autowired
	private PetRepository		petRepository;


	@Test
	void shouldFindSicknessWithCorrectId() {
		Sickness sickness3 = this.sicknessRepository.findById(3);
		Assertions.assertTrue(sickness3.getName().equals("Rabia"));
		Assertions.assertTrue(sickness3.getSymptom().equals("Exceso de salivación"));
		Assertions.assertTrue(sickness3.getSeverity().equals(1));
		Assertions.assertTrue(sickness3.getType().getName().equals("cat"));
	}

	@Test
	void shouldNotFoundSicknessWithCorrectId() {
		List<Sickness> sicknesses = this.sicknessRepository.findSicknessesByTypeId(7);
		Assertions.assertTrue(sicknesses.isEmpty());
	}
}
