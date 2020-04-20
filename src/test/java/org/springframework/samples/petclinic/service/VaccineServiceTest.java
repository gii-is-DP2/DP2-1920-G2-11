
package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.internal.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class VaccineServiceTest {

	@Autowired
	private VaccineService vaccineService;
	
	private SicknessService sicknessService;


	@Test
	void findVaccinesBySicknessIdTest() {
		List<Vaccine> vaccines = this.vaccineService.findVaccinesBySicknessId(6);
		Assertions.assertTrue(!vaccines.isEmpty() && vaccines.size() == 3 && vaccines.get(0).getName().equals("Vacuna F") && vaccines.get(2).getName().equals("Vacunote"));
	}

	@Test
	void findVaccineByIdTest() {
		Optional<Vaccine> vaccine = this.vaccineService.findVaccineById(1);
		Assertions.assertTrue(vaccine.get().getName().equals("Vacuna A") && vaccine.get().getMonths().equals(4) && vaccine.get().getComponents().equals("Q,W,E,R") && vaccine.get().getSickness().getName().equals("Otitis"));
	}
	
	
	@Test
	void deleteTest() {
		
		Optional<Vaccine> vaccine = this.vaccineService.findVaccineById(1);
		Iterable<Vaccine> vaccines = this.vaccineService.findAll(); 
		
		int n = 0;
		for(Vaccine v : vaccines) {
		      n++;
		}
		
		this.vaccineService.delete(vaccine.get());
		
		
		vaccines = this.vaccineService.findAll();
		int i = 0;
		for(Vaccine b : vaccines) {
		      i++;
		}
		
		Assertions.assertTrue(n==i+1);
	}
	
	
	@Test
	void createTest() {
		Iterable<Vaccine> vaccines = this.vaccineService.findAll(); 
		
		int n = 0;
		for(Vaccine v : vaccines) {
		      n++;
		}
		
		Vaccine vaccine = new Vaccine();
		vaccine.setComponents("H2O");
		vaccine.setId(27);
		vaccine.setMonths(2);
		vaccine.setName("Vacunón");
		Sickness sickness = new Sickness();
		sickness.setCause("ninguna");
		sickness.setId(33);
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
		for(Vaccine b : vaccines) {
		      i++;
		}
		
		Assertions.assertTrue(i==n+1 && vaccine.getId()>0);
	}
	
	
}
