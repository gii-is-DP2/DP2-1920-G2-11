
package org.springframework.samples.petclinic.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.stereotype.Service;

//TODO: revisar

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class VaccineServiceTest {

	@Autowired

	private VaccineService vaccineService;
	
	@Autowired
	private SicknessService sicknessService;



	@Test
	void findVaccinesBySicknessIdTest() {
		List<Vaccine> vaccines = this.vaccineService.findVaccinesBySicknessId(6);
		Assertions.assertTrue(!vaccines.isEmpty() && vaccines.size() == 3 && vaccines.get(0).getName().equals("Vacuna F") && vaccines.get(2).getName().equals("Vacunote"));
	}

	@Test
	void findVaccineByIdTest() {

		Vaccine vaccine = this.vaccineService.findVaccineById(1);

		Assertions.assertTrue(vaccine.getName().equals("Vacuna A") && vaccine.getMonths().equals(4) && vaccine.getComponents().equals("Q,W,E,R") && vaccine.getSickness().getName().equals("Otitis"));
	}

	@Test
	void deleteTest() {

		Vaccine vaccine = this.vaccineService.findVaccineById(1);
		Iterable<Vaccine> vaccines = this.vaccineService.findAll();

		int n = 0;
		for (Vaccine v : vaccines) {
			n++;
		}

		this.vaccineService.delete(vaccine);

		vaccines = this.vaccineService.findAll();
		int i = 0;
		for (Vaccine b : vaccines) {
			i++;
		}

		Assertions.assertTrue(n == i + 1);
	}

	@Test

	void saveVaccineTest() {
		Iterable<Vaccine> vaccines = this.vaccineService.findAll(); 
		

		int n = 0;
		for (Vaccine v : vaccines) {
			n++;
		}

		Vaccine vaccine = new Vaccine();
		vaccine.setComponents("H2O");
		vaccine.setId(27);
		vaccine.setMonths(2);
		vaccine.setName("Vacunón");
		Sickness sickness = new Sickness();
		sickness.setCause("ninguna");
		sickness.setId(3);
		sickness.setName("Inventado");
		sickness.setSeverity(2);
		sickness.setSymptom("Cuentitis");
		PetType type = new PetType();
		type.setId(33);
		type.setName("dragón");
		sickness.setType(type);
		vaccine.setSickness(sickness);

		this.vaccineService.saveVaccine(vaccine);

		vaccines = this.vaccineService.findAll();
		int i = 0;
		for (Vaccine b : vaccines) {
			i++;
		}

		Assertions.assertTrue(i == n + 1 && vaccine.getId() > 0);
	}

}
