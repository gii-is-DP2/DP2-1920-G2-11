
package org.springframework.samples.petclinic.escenarios;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU8Test {

	@Autowired
	private ClinicService clinicService;


	//Caso positivo
	@Test
	void shouldFindAllClinics() {
		Collection<Clinic> clinics = this.clinicService.findClinics();
		Assertions.assertTrue(!clinics.isEmpty());
	}

	// hay un único caso

	//	//Caso negativo
	//	@Test
	//	void shouldClinicsEmpty() {
	//		Collection<Clinic> clinics = this.clinicRepository.findAll();
	//		Assertions.assertTrue(clinics.isEmpty());
	//	}
}
