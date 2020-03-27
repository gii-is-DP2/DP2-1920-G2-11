
package org.springframework.samples.petclinic.escenarios;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU2Test {

	@Autowired
	private VaccineRepository vaccineRepository;


	//Caso positivo
	@Test
	void shouldFindVaccinesWithCorrectSicknessId() {
		List<Vaccine> vaccines = this.vaccineRepository.findBySicknessId(6);
		Assertions.assertTrue(vaccines.size() == 3 && vaccines.get(1).getName().equals("Vacuna F2"));
	}

	//Caso negativo
	@Test
	void shouldVaccinesEmpty() {
		List<Vaccine> vaccine = this.vaccineRepository.findBySicknessId(28);
		Assertions.assertTrue(vaccine.isEmpty());
	}
}
