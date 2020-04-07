package org.springframework.samples.petclinic.service;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.repository.ClinicRepository;
import org.springframework.stereotype.Service;




@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ClinicServiceTests {

	@Autowired
	private ClinicRepository	clinicRepository;

	@Test
	void shouldFindClinicWithCorrectId() {
		Clinic clinic = this.clinicRepository.findById(1);
		Assertions.assertTrue(clinic.getName().equals("Winston Pet Cares"));
		Assertions.assertTrue(clinic.getAddress().equals("Evergreen Av. 4"));
		Assertions.assertTrue(clinic.getCity().equals("Pitsburg"));
		Assertions.assertTrue(clinic.getEmail().equals("charles@mail.com"));
		Assertions.assertTrue(clinic.getTelephone().equals("600033472"));
		
		
	}
}
