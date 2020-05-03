
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
public class HU21Test {

	@Autowired
	private MedicineService medicineService;


	//Caso positivo
	@Test
	void shouldFindAllMedicines() {
		Collection<Medicine> meds = medicineService.findMedicines();
		Assertions.assertTrue(!meds.isEmpty()
		&& meds.size()==8);
		Medicine medicine = medicineService.findById(1);
		Assertions.assertTrue(meds.contains(medicine));
	}

	//Caso negativo
	@Test
	void shouldNotFindWrongMedicines() {
		Collection<Medicine> meds = medicineService.findMedicines();
		Assertions.assertFalse(meds.isEmpty());
		Medicine medicine = medicineService.findById(50);
		Assertions.assertFalse(meds.contains(medicine));
	}
}
