package org.springframework.samples.petclinic.service;



import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.repository.ClinicRepository;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;




@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class ClinicServiceTests {

	@Autowired
	private ClinicService	clinicService;



	
	@Test
	void findClinicsTest() {
		Collection<Clinic> clinics  = this.clinicService.findClinics();
		Assertions.assertTrue(!clinics.isEmpty() && clinics.size()==11);
	}
	
	@Test 
	void findByIdTest() {
		Clinic clinic = this.clinicService.findById(1);
		Assertions.assertTrue(clinic.getName().equals("Winston Pet Cares") && clinic.getTelephone().equals("600033472")
				&& clinic.getEmail().equals("charles@mail.com") && clinic.getCity().equals("Pitsburg"));
	}
	
	
	@Test 
	void findByNameTest(){
		Collection<Clinic>clinic = this.clinicService.findByName("Veterinaria Nervion");
		Assertions.assertTrue(clinic.size()==1);
	}
	
	@Test
	@Transactional
	void createClinicTest() {
		Collection<Clinic> clinics = clinicService.findClinics();
		int size= clinics.size();
		Clinic clinic = new Clinic();
		clinic.setId(40);
		clinic.setName("Clinica Banini");
		clinic.setCity("Malaga");
		clinic.setAddress("Calle Paz 3");
		clinic.setTelephone("123654789");
		clinic.setEmail("lala@mail.com");
		
		
		this.clinicService.save(clinic);
		Assertions.assertTrue(size<clinicService.findClinics().size());

	}
}
