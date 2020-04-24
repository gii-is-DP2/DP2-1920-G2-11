
package org.springframework.samples.petclinic.escenarios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU9Test {

	@Autowired
	private ClinicService clinicService;


	//Caso positivo
	@Test
	void shouldFindClinicWithCorrectId() {
		Clinic clinic = this.clinicService.findById(2);
		Assertions.assertTrue(clinic.getName().equals("Veterinaria Nervión"));
	}

	//Caso negativo
	@Test
	void shouldClinicsEmpty() {
		Clinic clinics = this.clinicService.findById(3);
		Assertions.assertTrue(clinics.getName().equals("Clínica Los Arcos"));
		Assertions.assertTrue(clinics.getAddress().isEmpty() && clinics.getTelephone().isEmpty() && clinics.getEmail().isEmpty() && clinics.getCity().isEmpty());
	}
}
