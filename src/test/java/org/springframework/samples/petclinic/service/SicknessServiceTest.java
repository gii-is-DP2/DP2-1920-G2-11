
package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Medicine;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class SicknessServiceTest {

	@Autowired
	private SicknessService	sicknessService;

	@Autowired
	private VaccineService	vaccineService;

	@Autowired
	private MedicineService	medicineService;

	@Autowired
	private PetService		petService;


	@Test
	void findSicknessesByPetIdTest() {
		List<Sickness> sicknesses = this.sicknessService.findSicknessesByPetId(2);
		Assertions.assertTrue(!sicknesses.isEmpty() && sicknesses.size() == 5 && sicknesses.get(0).getName().equals("Acaros y hongos") && sicknesses.get(1).getSeverity().equals(2) && sicknesses.get(2).getSymptom().equals("Diarrea, deshidratacion")
			&& sicknesses.get(3).getCause().equals("Mala alimentacion"));
	}

	@Test
	void findAllSicknessesTest() {
		List<Sickness> sicknesses = this.sicknessService.findAllSicknesses();
		Assertions.assertTrue(!sicknesses.isEmpty() && sicknesses.size() == 30);
	}

	@Test
	void findSicknessesByIdTest() {
		Sickness sickness = this.sicknessService.findSicknessesById(25);
		Assertions.assertTrue(sickness.getName().equals("Septicemia") && sickness.getCause().equals("Acaros") && sickness.getSymptom().equals("Dificultad para respirar") && sickness.getSeverity().equals(2) && sickness.getType().getName().equals("snake"));

	}

	@Test
	void optionalFindSicknessesByIdTest() {
		Optional<Sickness> findSickness = this.sicknessService.optionalFindSicknessesById(25);
		Sickness sickness = findSickness.get();
		Assertions.assertTrue(sickness.getName().equals("Septicemia") && sickness.getCause().equals("Acaros") && sickness.getSymptom().equals("Dificultad para respirar") && sickness.getSeverity().equals(2) && sickness.getType().getName().equals("snake"));
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
		Sickness sickness = this.sicknessService.findSicknessesById(24);
		this.sicknessService.deleteVaccineFromSickness(sickness);
		this.sicknessService.deleteMedicineFromSickness(sickness);
		this.sicknessService.deleteSickness(sickness);
		List<Sickness> sicknesses = this.sicknessService.findSicknessesByPetId(6);
		Assertions.assertTrue(!sicknesses.isEmpty() && sicknesses.size() == 3);
	}

	@Test
	void deleteVaccineFromSicknessTest() {
		Sickness sickness = this.sicknessService.findSicknessesById(6);
		this.sicknessService.deleteVaccineFromSickness(sickness);
		List<Vaccine> vaccines = this.vaccineService.findVaccinesBySicknessId(sickness.getId());
		Assertions.assertTrue(vaccines.isEmpty());

	}

	@Test
	void deleteMedicineFromSicknessTest() {
		Sickness sickness = this.sicknessService.findSicknessesById(1);
		this.sicknessService.deleteMedicineFromSickness(sickness);
		Collection<Medicine> medicines = this.medicineService.findMedicinesBySicknessIdAndPetTypeId(sickness.getId(), sickness.getType().getId());
		Assertions.assertTrue(medicines.isEmpty());
	}

	@Test
	void sameNameAndPetTypeTest() {
		List<Sickness> sicknesses = this.sicknessService.findAllSicknesses();
		Sickness sickness = new Sickness();
		sickness.setName("Otitis");
		sickness.setType(this.petService.findPetById(1).getType());
		Boolean res = this.sicknessService.sameNameAndPetType(sickness, sicknesses);
		Assertions.assertTrue(res == true);
	}

}
