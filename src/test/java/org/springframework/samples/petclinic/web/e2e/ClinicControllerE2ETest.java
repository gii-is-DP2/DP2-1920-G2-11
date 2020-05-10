package org.springframework.samples.petclinic.web.e2e;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.web.ClinicControllerTests;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ClinicControllerE2ETest {
	
	
	@Autowired
	private MockMvc				mockMvc;

	private static final int	TEST_CLINIC_ID	= 2;
	
	
	@WithMockUser(username = "owner1", authorities = {
			"veterinarian", "admin"
		})
	@Test
	void testShowClinics() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("clinics")).andExpect(MockMvcResultMatchers.view().name("clinics/clinicsList"));
	}

	@WithMockUser(username = "owner1", authorities = {
			"veterinarian", "admin"
		})
	@Test
	void testShowProduct() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics/{clinicId}", ClinicControllerE2ETest.TEST_CLINIC_ID)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("clinics"))
			.andExpect(MockMvcResultMatchers.view().name("clinics/clinicsShow"));

	}

}
