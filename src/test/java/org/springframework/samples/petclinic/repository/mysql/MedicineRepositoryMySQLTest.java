/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.repository.mysql;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Medicine;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.MedicineRepository;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestPropertySource(locations = "classpath:application-mysql.properties")

class MedicineRepositoryMySQLTest {                
        @Autowired
	protected MedicineRepository medicineRepository;


	@Test
	void findMedicinesBySicknessIdAndPetTypeId() {
		Collection<Medicine> medicines = this.medicineRepository.findBySicknessIdAndPetTypeId(1, 1);
		assertThat(medicines.size()==1);
		Medicine medicine = medicines.stream().findFirst().get();
		assertThat(medicine.getName().equals("Medicina A")
		&& medicine.getComponents().equals("Componente A")
		&& medicine.getPetType().getId().equals(1)&&medicine.getPetType().getName().equals("cat")
		&& medicine.getSickness().getId().equals(1)&&medicine.getSickness().getName().equals("Otitis")
		&& medicine.getTreatment().equals("1 cada 8 horas"));
		
			//Prueba negativa
		Collection<Medicine> wrongMedicines = this.medicineRepository.findBySicknessIdAndPetTypeId(43, 38);
		assertThat(wrongMedicines.isEmpty());
	}

	

}
