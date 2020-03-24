
package org.springframework.samples.petclinic.escenarios;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.repository.SicknessRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU1Test {

	@Autowired
	private SicknessRepository sicknessRepository;


	//Caso positivo
	@Test
	void shouldFindSicknessWithCorrectPetId() {
		List<Sickness> sicknesses = this.sicknessRepository.findByTypeId(6);
		Assertions.assertTrue(sicknesses.size() == 4 && sicknesses.get(0).getName().equals("Ácaros y hongos") && sicknesses.get(0).getSeverity().equals(2));
	}

	//Caso negativo
	@Test
	void shouldSicknessesEmpty() {
		List<Sickness> sickness = this.sicknessRepository.findByTypeId(7);
		Assertions.assertTrue(sickness.isEmpty());
	}
}
