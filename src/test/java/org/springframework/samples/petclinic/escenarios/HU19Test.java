
package org.springframework.samples.petclinic.escenarios;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Medicine;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.MedicineService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU19Test {

	@Autowired
	private MedicineService medicineService;


	//Caso positivo
	@Test
	void shouldFindMedicinesWithCorrectSicknessAndPetTypeId() {
		Collection<Medicine> medicines = this.medicineService.findMedicinesBySicknessIdAndPetTypeId(1, 1);
		Integer size = medicines.size();
		Medicine medicine = medicines.stream().findFirst().get();
		Assertions.assertTrue(size==1 && medicine.getName().equals("Medicina A")
				&& medicine.getComponents().equals("Componente A")
				&& medicine.getPetType().getId().equals(1)&&medicine.getPetType().getName().equals("cat")
				&& medicine.getSickness().getId().equals(1)&&medicine.getSickness().getName().equals("Otitis")
				&& medicine.getTreatment().equals("1 cada 8 horas"));
	}

	//Caso negativo
	@Test
	void shouldNotFindMedicinesWithInCorrectSicknessAndPetTypeId() {
		Collection<Medicine> medicines = this.medicineService.findMedicinesBySicknessIdAndPetTypeId(43, 12);
		Assertions.assertTrue(medicines.isEmpty());
	}

}
