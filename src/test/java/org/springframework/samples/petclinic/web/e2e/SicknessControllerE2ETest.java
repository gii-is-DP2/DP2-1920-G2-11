
package org.springframework.samples.petclinic.web.e2e;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc

//@TestPropertySource(locations = "classpath:application-mysql.properties")

public class SicknessControllerE2ETest {

	@Autowired
	private MockMvc				mockMvc;

	private static final int	TEST_PET_ID				= 1;

	private static final int	TEST_PET_ERROR_ID		= 14;

	private static final int	TEST_SICKNESS_ID		= 1;

	private static final int	TEST_PET_SHOW_ERROR_ID	= 11;

	private static final int	TEST_SICKNESS_ERROR_ID	= 30;

	private static final int	TEST_SICKNESS_UPDATE_ID	= 25;

	private static final int	TEST_SICKNESS_UPDATE_ERROR_ID	= 26;


	@WithMockUser(username = "owner1", authorities = {
		"owner"
	})
	@Test
	void testShowSicknessesListHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses", SicknessControllerE2ETest.TEST_PET_ID)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("sicknesses"))
			.andExpect(MockMvcResultMatchers.view().name("sicknesses/sicknessList"));
	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian"
	})
	@Test
	void testShowSicknessesErrorHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses", SicknessControllerE2ETest.TEST_PET_ERROR_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("sicknessesError")).andExpect(MockMvcResultMatchers.view().name("sicknesses/sicknessError"));
	}

	@WithMockUser(username = "admin1", authorities = {
		"admin"
	})
	@Test
	void testShowSicknessesShowHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}", SicknessControllerE2ETest.TEST_PET_ID, SicknessControllerE2ETest.TEST_SICKNESS_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeExists("sickness")).andExpect(MockMvcResultMatchers.view().name("sicknesses/sicknessShow"));
	}

	@WithMockUser(username = "owner1", authorities = {
		"owner"
	})
	@Test
	void testShowSicknessesShowErrorHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}", SicknessControllerE2ETest.TEST_PET_SHOW_ERROR_ID, SicknessControllerE2ETest.TEST_SICKNESS_ERROR_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("sicknessError")).andExpect(MockMvcResultMatchers.view().name("sicknesses/sicknessDetailsError"));
	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian"
	})
	@Test
	void testDeleteSicknessWithVacccine() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/delete/{sicknessId}", SicknessControllerE2ETest.TEST_PET_ID, SicknessControllerE2ETest.TEST_SICKNESS_ID)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.view().name("welcome"));
	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian"
	})
	@Test
	void testInitCreationForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/vets/newSickness")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("sicknesses/editSickness"));
	}

	@WithMockUser(username = "owner1", authorities = {
		"veterinarian"
	})
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/vets/saveSickness").with(SecurityMockMvcRequestPostProcessors.csrf()).param("name", "Gastroenteritis").param("cause", "Causa 1").param("symptom", "Sintoma 1").param("severity", "1").param("type", "cat"))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.view().name("welcome"));
	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian"
	})
	@Test
	void testProcessCreationFormHasErrors1() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/vets/saveSickness").with(SecurityMockMvcRequestPostProcessors.csrf()).param("cause", "Cause 1").param("symptom", "Sintoma 1")).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeHasErrors("sickness")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "name"))
			.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "severity")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "type"))
			.andExpect(MockMvcResultMatchers.view().name("sicknesses/editSickness"));
	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian"
	})
	@Test
	void testProcessCreationFormHasErrors2() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/vets/saveSickness").with(SecurityMockMvcRequestPostProcessors.csrf()).param("name", "12345678901234567890123456789012345678901234567890123456789012345678901234567890")
				.param("cause", "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890")
				.param("symptom", "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890").param("severity", "5"))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeHasErrors("sickness")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "name"))
			.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "cause")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "symptom"))
			.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "severity")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "type"))
			.andExpect(MockMvcResultMatchers.view().name("sicknesses/editSickness"));
	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian"
	})
	@Test
	void testInitUpdateForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/*/sicknesses/{sicknessId}/editSickness", SicknessControllerE2ETest.TEST_SICKNESS_UPDATE_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("sicknesses/updateSickness"));
	}

	@WithMockUser(username = "owner1", authorities = {
		"veterinarian"
	})
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/owners/*/pets/*/sicknesses/{sicknessId}/updateSickness", SicknessControllerE2ETest.TEST_SICKNESS_UPDATE_ID).with(SecurityMockMvcRequestPostProcessors.csrf()).param("name", "Gastroenteritis")
			.param("cause", "Causa 1").param("symptom", "Sintoma 1").param("severity", "1").param("type", "cat")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.view().name("welcome"));
	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian"
	})
	@Test
	void testProcessUpdateFormHasErrors1() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/owners/*/pets/*/sicknesses/{sicknessId}/updateSickness", SicknessControllerE2ETest.TEST_SICKNESS_UPDATE_ERROR_ID).with(SecurityMockMvcRequestPostProcessors.csrf()).param("cause", "Cause 1")
				.param("symptom", "Sintoma 1"))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeHasErrors("sickness")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "name"))
			.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "severity")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "type"))
			.andExpect(MockMvcResultMatchers.view().name("sicknesses/updateSickness"));
	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian"
	})
	@Test
	void testProcessUpdateFormHasErrors2() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/owners/*/pets/*/sicknesses/{sicknessId}/updateSickness", SicknessControllerE2ETest.TEST_SICKNESS_UPDATE_ERROR_ID).with(SecurityMockMvcRequestPostProcessors.csrf())
				.param("name", "12345678901234567890123456789012345678901234567890123456789012345678901234567890")
				.param("cause", "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890")
				.param("symptom", "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890").param("severity", "5"))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeHasErrors("sickness")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "name"))
			.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "cause")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "symptom"))
			.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "severity")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "type"))
			.andExpect(MockMvcResultMatchers.view().name("sicknesses/updateSickness"));
	}

}
