
package org.springframework.samples.petclinic.escenarios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.service.SicknessService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU3Test {

	@Autowired
	private SicknessService sicknessService;


	//Caso positivo
	@Test
	void shouldFindSicknessWithCorrectId() {
		Sickness sickness = this.sicknessService.findSicknessesById(16);
		Assertions.assertTrue(sickness.getName().equals("Coronavirus") && sickness.getCause().equals("Contacto con secreciones orales y fecales infectadas") && sickness.getSymptom().equals("Deshidratacion, vomitos, diarrea")
			&& sickness.getSeverity().equals(1) && sickness.getType().getName().equals("dog"));
	}

	//Caso negativo
	@Test
	void shouldSicknessesWithoutDetails() {
		;
		Sickness sickness = this.sicknessService.findSicknessesById(30);
		Assertions.assertTrue(sickness.getName().equals("Pajaro Loco") && sickness.getCause().isEmpty() && sickness.getSymptom().isEmpty() && sickness.getSeverity().equals(0));
	}
}
