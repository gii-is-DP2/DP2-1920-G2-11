
package org.springframework.samples.petclinic.escenarios;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.SicknessRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU5Test {

	@Autowired
	private SicknessRepository	sicknessRepository;

	@Autowired
	private PetRepository		petRepository;


	//Caso positivo
	@Test
	void createSicknessCorrectly() {

		Sickness sickness = new Sickness();
		Pet pet = this.petRepository.findById(3);
		sickness.setId(31);
		sickness.setName("Enfermedad X");
		sickness.setCause("Causa X");
		sickness.setSymptom("Síntoma X");
		sickness.setSeverity(2);
		sickness.setType(pet.getType());
		this.sicknessRepository.save(sickness);
		List<Sickness> sicknesses = this.sicknessRepository.findByTypeId(pet.getType().getId());
		Assertions.assertTrue(!sicknesses.isEmpty() && sicknesses.size() == 11 && sicknesses.get(10).getName().equals("Enfermedad X") && sicknesses.get(10).getCause().equals("Causa X") && sicknesses.get(10).getSymptom().equals("Síntoma X")
			&& sicknesses.get(10).getSeverity().equals(2));
	}

	//Caso negativo
	@Test
	void createSicknessNotCorrectly() {

		Sickness sickness = new Sickness();
		Pet pet = this.petRepository.findById(3);
		sickness.setId(31);
		sickness.setName(""); // TODO: Peta el test por la constraint
		sickness.setCause("Causa X");
		sickness.setSymptom("Síntoma X");
		sickness.setSeverity(5); // TODO: Peta el test por la constraint
		sickness.setType(pet.getType());
		this.sicknessRepository.save(sickness);
		List<Sickness> sicknesses = this.sicknessRepository.findByTypeId(pet.getType().getId());
		Assertions.assertTrue(!sicknesses.isEmpty() && sicknesses.size() == 10);
	}
}
