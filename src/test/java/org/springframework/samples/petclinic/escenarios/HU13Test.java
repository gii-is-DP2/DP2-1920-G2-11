package org.springframework.samples.petclinic.escenarios;

package org.springframework.samples.petclinic.escenarios;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU13Test {

	//TODO MODIFICAR VACUNA
	@Autowired
	private VaccineService vaccineService;


	//Caso positivo
	@Test
	void EditVaccineCorrect() {
		
		Vaccine vaccine = this.vaccineService.findVaccineById(2);
		this.vaccineService.
		
		
	}

	

		//Caso negativo
		@Test
		void EditVaccineWhitError() {
			
		}
}

