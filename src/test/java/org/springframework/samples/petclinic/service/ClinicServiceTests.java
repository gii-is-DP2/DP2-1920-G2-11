
package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class ClinicServiceTests {

	@Autowired
	private ClinicService clinicService;


	@Test
	void findClinicsTest() {
		List<Clinic> clinics = this.clinicService.findClinics();
		Assertions.assertTrue(!clinics.isEmpty() && clinics.get(0).getName().equals("Winston Pet Cares") && clinics.get(1).getName().equals("Veterinaria Nervion"));}

	@Test
	void findByIdTest() {
		Clinic clinic = this.clinicService.findById(1);
		Assertions.assertTrue(clinic.getName().equals("Winston Pet Cares") && clinic.getTelephone().equals("600033472") && clinic.getEmail().equals("charles@mail.com") && clinic.getCity().equals("Pitsburg"));
	}

	@Test
	void findByNameTest() {
		Collection<Clinic> clinic = this.clinicService.findByName("Veterinaria Nervion");
		Assertions.assertTrue(clinic.size() == 1);
	}

	@Test
	@Transactional
	void createClinicTest() {
		Collection<Clinic> clinics = this.clinicService.findClinics();
		int size = clinics.size();
		Clinic clinic = new Clinic();
		clinic.setId(40);
		clinic.setName("Clinica Banini");
		clinic.setCity("Malaga");
		clinic.setAddress("Calle Paz 3");
		clinic.setTelephone("123654789");
		clinic.setEmail("lala@mail.com");

		this.clinicService.save(clinic);
		Assertions.assertTrue(size < this.clinicService.findClinics().size());

	}
}
