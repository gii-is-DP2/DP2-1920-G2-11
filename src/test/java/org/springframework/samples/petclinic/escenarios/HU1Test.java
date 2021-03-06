
package org.springframework.samples.petclinic.escenarios;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.service.SicknessService;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class HU1Test {

	@Autowired
	private SicknessService sicknessService;


	//Caso positivo
	@Test
	void shouldFindSicknessWithCorrectPetId() {
		List<Sickness> sicknesses = this.sicknessService.findSicknessesByPetId(2);
		Assertions.assertTrue(sicknesses.size() == 5 && sicknesses.get(0).getName().equals("Acaros y hongos"));
	}

	//Caso negativo
	@Test
	void shouldSicknessesEmpty() {
		List<Sickness> sickness = this.sicknessService.findSicknessesByPetId(14);
		Assertions.assertTrue(sickness.isEmpty());
	}
}
