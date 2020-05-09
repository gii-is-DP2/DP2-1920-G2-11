
package org.springframework.samples.petclinic.escenarios;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU12Test {

	@Autowired
	private VaccineService vaccineService;
	
	// Caso positivo
	@Test
	void deleteVaccineCorrectly() {
		Vaccine vaccine = this.vaccineService.findVaccineById(1);
		Iterable<Vaccine> vaccines1 = this.vaccineService.findAll();
		int n = 0;
		for(Vaccine a : vaccines1) {
		      n++;
		}
		this.vaccineService.deleteVaccine(vaccine);
		Iterable<Vaccine> vaccines2 = this.vaccineService.findAll();
		int i = 0;
		for(Vaccine b : vaccines2) {
		      i++;
		}
		
		Assertions.assertTrue(i == n-1);
	}

	//Caso negativo
	
	@Test
	void deleteVaccineNotCorrectly() {
		Vaccine vaccine = new Vaccine();
		vaccine.setId(50);
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			this.vaccineService.deleteVaccine(vaccine);
		});
	}

}
