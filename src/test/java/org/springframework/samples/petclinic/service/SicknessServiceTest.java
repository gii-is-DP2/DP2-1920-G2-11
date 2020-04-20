
package org.springframework.samples.petclinic.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SicknessServiceTest {

	@Autowired
	private SicknessService	sicknessService;

	@Autowired
	private VaccineService	vaccineService;

	@Autowired
	private PetService		petService;


	@Test
	void findSicknessesByPetIdTest() {
		List<Sickness> sicknesses = this.sicknessService.findSicknessesByPetId(2);
		Assertions.assertTrue(!sicknesses.isEmpty() && sicknesses.size() == 5 && sicknesses.get(0).getName().equals("Ácaros y hongos") && sicknesses.get(1).getSeverity().equals(2) && sicknesses.get(2).getSymptom().equals("Diarrea, deshidratación")
			&& sicknesses.get(3).getCause().equals("Mala alimentación"));
	}

	@Test
	void findSicknessesByIdTest() {
		Sickness sickness = this.sicknessService.findSicknessesById(25);
		Assertions.assertTrue(sickness.getName().equals("Septicemia") && sickness.getCause().equals("Ácaros") && sickness.getSymptom().equals("Dificultad para respirar") && sickness.getSeverity().equals(2) && sickness.getType().getName().equals("snake"));

	}

	@Test
	void createSicknessTest() {
		Sickness sickness = new Sickness();
		Pet pet = this.petService.findPetById(3);
		sickness.setId(31);
		sickness.setName("Enfermedad X");
		sickness.setCause("Causa X");
		sickness.setSymptom("Síntoma X");
		sickness.setSeverity(2);
		sickness.setType(pet.getType());
		this.sicknessService.saveSickness(sickness);
		List<Sickness> sicknesses = this.sicknessService.findSicknessesByPetId(3);
		Assertions.assertTrue(!sicknesses.isEmpty() && sicknesses.size() == 11 && sicknesses.get(10).getName().equals("Enfermedad X") && sicknesses.get(10).getCause().equals("Causa X") && sicknesses.get(10).getSymptom().equals("Síntoma X")
			&& sicknesses.get(10).getSeverity().equals(2));

	}

	@Test
	void deleteSicknessTest() {
		Sickness sickness = this.sicknessService.findSicknessesById(13);
		this.sicknessService.deleteVaccineFromSickness(sickness);
		this.sicknessService.deleteSickness(sickness);
		List<Sickness> sicknesses = this.sicknessService.findSicknessesByPetId(3);
		Assertions.assertTrue(!sicknesses.isEmpty() && sicknesses.size() == 9);
	}

	@Test
	void deleteVaccineFromSicknessTest() {
		Sickness sickness = this.sicknessService.findSicknessesById(6);
		this.sicknessService.deleteVaccineFromSickness(sickness);
		List<Vaccine> vaccines = this.vaccineService.findVaccinesBySicknessId(sickness.getId());
		Assertions.assertTrue(vaccines.isEmpty());

	}

}
