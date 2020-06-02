package org.springframework.samples.petclinic.web.e2e;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.web.ClinicControllerTests;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@DirtiesContext
public class ClinicControllerE2ETest {

	@Autowired
	private MockMvc mockMvc;

	private static final int TEST_CLINIC_ID = 2;
	private static final int TEST_CLINIC_ID_ERROR = 3;

	@WithMockUser(username = "owner1", authorities = { "owner" })
	@Test
	void testShowClinics() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("clinics"))
				.andExpect(MockMvcResultMatchers.view().name("clinics/clinicsList"));
	}

	@WithMockUser(username = "owner1", authorities = { "owner" })
	@Test
	void testShowClinic() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics/{clinicId}", ClinicControllerE2ETest.TEST_CLINIC_ID))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("clinics"))
				.andExpect(MockMvcResultMatchers.view().name("clinics/clinicsShow"));

	}



	@WithMockUser(username = "owner1", authorities = { "owner" })
	@Test
	void testShowClinicError() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics/{clinicId}", ClinicControllerE2ETest.TEST_CLINIC_ID_ERROR))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("clinics"))
				.andExpect(MockMvcResultMatchers.view().name("clinics/clinicDetailsError"));

	}
	
	
	
	@WithMockUser(username = "owner1", authorities = { "owner" })
	@Test
	void testInitCreationForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics/new"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("clinics/editClinic"));
	}

	@WithMockUser(username = "owner1", authorities = { "owner" })
	@Test
	void testCreationProccessFormHasError() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/clinics/save")
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.param("name", "")
				.param("address", "nombre")
				.param("city", "marbella")
				.param("email", "")
				.param("telephone", ""))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeHasErrors("clinic"))
				.andExpect(MockMvcResultMatchers.view().name("clinics/editClinic"));

	}


}
