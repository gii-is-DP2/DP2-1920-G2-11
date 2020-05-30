/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Medicine;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
class MedicineServiceTests {

	@Autowired
	protected MedicineService medicineService;


	@Test
	void findById() {
		Medicine medicine = this.medicineService.findMedicineById(1);
		Assertions.assertThat(medicine.getName().equals("Medicina A") && medicine.getComponents().equals("Componente A") && medicine.getPetType().getId().equals(1) && medicine.getPetType().getName().equals("cat") && medicine.getSickness().getId().equals(1)
			&& medicine.getSickness().getName().equals("Otitis") && medicine.getTreatment().equals("1 cada 8 horas"));

		//Prueba negativa
		Medicine wrongMedicine = this.medicineService.findMedicineById(50);
		Assertions.assertThat(wrongMedicine.isNew());
	}

	@Test
	void findMedicines() {
		Collection<Medicine> medicines = this.medicineService.findMedicines();
		Assertions.assertThat(!medicines.isEmpty() && medicines.size() == 8);

	}

	@Test
	void findMedicinesBySicknessIdAndPetTypeId() {
		Collection<Medicine> medicines = this.medicineService.findMedicinesBySicknessIdAndPetTypeId(1, 1);
		Assertions.assertThat(medicines.size() == 1);
		Medicine medicine = medicines.stream().findFirst().get();
		Assertions.assertThat(medicine.getName().equals("Medicina A") && medicine.getComponents().equals("Componente A") && medicine.getPetType().getId().equals(1) && medicine.getPetType().getName().equals("cat") && medicine.getSickness().getId().equals(1)
			&& medicine.getSickness().getName().equals("Otitis") && medicine.getTreatment().equals("1 cada 8 horas"));

		//Prueba negativa
		Collection<Medicine> wrongMedicines = this.medicineService.findMedicinesBySicknessIdAndPetTypeId(43, 38);
		Assertions.assertThat(wrongMedicines.isEmpty());
	}

}
