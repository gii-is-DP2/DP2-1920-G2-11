
package org.springframework.samples.petclinic.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SicknessServiceTest {

	@Autowired
	private SicknessService sicknessService;


	@Test
	void findSicknessesByPetIdTest() {
		List<Sickness> sicknesses = this.sicknessService.findSicknessesByPetId(2);
		Assertions.assertTrue(!sicknesses.isEmpty() && sicknesses.size() == 5 && sicknesses.get(0).getName().equals("Ácaros y hongos") && sicknesses.get(1).getSeverity().equals(2) && sicknesses.get(2).getSymptom().equals("Diarrea, deshidratación")
			&& sicknesses.get(3).getCause().equals("Mala alimentación"));
	}

	@Test
	void findSicknessesByIdTest() {
		Sickness sickness = this.sicknessService.findSicknessesById(25);
		Assertions.assertTrue(sickness.getName().equals("Septicemia") && sickness.getCause().equals("Ácaros") && sickness.getSymptom().equals("Dificultad para respirar") && sickness.getSeverity().equals(2) && sickness.getType().getName().equals("snake"));

	}
}
