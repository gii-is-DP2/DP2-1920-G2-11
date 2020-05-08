package org.springframework.samples.petclinic.escenarios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Sickness;
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
		
	//vacuna: (2, 'Vacuna B', 'A,S,D,F', 6, 2);
	//sickness : (2, 'Conjuntivitis', 'Infecciones oculares, alergias, problemas genéticos', 
		//'Inflamación de la mucosa del ojo, pérdida de visión, lagrimeo', 2, 1);
		
   //petType:  (1, 'cat');
		
		Vaccine vaccine = this.vaccineService.findVaccineById(2);
	    vaccine.setName("lalala");
	    vaccine.setComponents("todos");
	    vaccine.setMonths(12);
//	    Sickness s = new Sickness();
//	    s.setId(2);
//	    s.setName("Conjuntivitis");
//	    s.setCause("Infecciones oculares, alergias, problemas genéticos");
//	    s.setSeverity(2);
//	    s.setSymptom("Inflamación de la mucosa del ojo, pérdida de visión, lagrimeo");
//	    PetType type = new PetType();
//	    type.setId(1);
//	    type.setName("cat");
//	    s.setType(type);
//	    vaccine.setSickness(s);
	    

		this.vaccineService.saveVaccine(vaccine);
		
		
	}


}

