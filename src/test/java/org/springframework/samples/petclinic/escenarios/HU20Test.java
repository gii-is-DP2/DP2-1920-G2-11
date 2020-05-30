
package org.springframework.samples.petclinic.escenarios;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Medicine;
import org.springframework.samples.petclinic.service.MedicineService;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class HU20Test {

	@Autowired
	private MedicineService medicineService;


	//Caso positivo
	@Test
	void shouldFindAllMedicines() {
		Collection<Medicine> meds = this.medicineService.findMedicines();
		Assertions.assertTrue(!meds.isEmpty() && meds.size() == 8);
		Medicine medicine = this.medicineService.findMedicineById(1);
		Assertions.assertTrue(meds.contains(medicine));
	}

	//Caso negativo
	@Test
	void shouldNotFindWrongMedicines() {
		Collection<Medicine> meds = this.medicineService.findMedicines();
		Medicine medicine = this.medicineService.findMedicineById(50);
		Assertions.assertFalse(meds.contains(medicine));
		Assertions.assertTrue(medicine.isNew());
	}
}
