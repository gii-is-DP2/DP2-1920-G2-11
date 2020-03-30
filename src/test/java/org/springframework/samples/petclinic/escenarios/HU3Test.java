
package org.springframework.samples.petclinic.escenarios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.repository.SicknessRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU3Test {

	@Autowired
	private SicknessRepository sicknessRepository;


	//Caso positivo
	@Test
	void shouldFindSicknessWithCorrectId() {
		Sickness sickness = this.sicknessRepository.findById(16);
		Assertions.assertTrue(sickness.getName().equals("Coronavirus") && sickness.getCause().equals("Contacto con secreciones orales y fecales infectadas") && sickness.getSymptom().equals("Deshidratación, vómitos, diarrea")
			&& sickness.getSeverity().equals(1) && sickness.getType().getName().equals("dog"));
	}

	//Caso negativo
	@Test
	void shouldSicknessesWithoutDetails() {
		Sickness sickness = this.sicknessRepository.findById(30);
		Assertions.assertTrue(sickness.getName().equals("Pájaro Loco") && sickness.getCause().isEmpty() && sickness.getSymptom().isEmpty() && sickness.getSeverity().equals(0));
	}
}
