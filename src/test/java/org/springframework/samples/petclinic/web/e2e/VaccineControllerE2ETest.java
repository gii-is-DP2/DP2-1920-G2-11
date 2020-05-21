
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
public class VaccineControllerE2ETest {

	@Autowired
	private MockMvc				mockMvc;

	private static final int	TEST_PET_ID					= 7;

	private static final int	TEST_PET_ERROR_ID			= 3;

	private static final int	TEST_SICKNESS_ID			= 2;

	private static final int	TEST_SICKNESS_ERROR_ID		= 7;

	private static final int	TEST_VACCINE_ID				= 2;

	private static final int	TEST_PET_SHOW_ERROR_ID		= 1;

	private static final int	TEST_SICKNESS_SHOW_ERROR_ID	= 6;

	private static final int	TEST_VACCINE_ERROR_ID		= 8;


	@WithMockUser(username = "owner1", authorities = {
		"veterinarian", "owner", "admin"
	})
	@Test
	void testShowVaccineListHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines", VaccineControllerE2ETest.TEST_PET_ID, VaccineControllerE2ETest.TEST_SICKNESS_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeExists("vaccines")).andExpect(MockMvcResultMatchers.view().name("vaccines/vaccinesList"));

	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian", "admin"
	})
	@Test
	void testShowVaccineErrorHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines", VaccineControllerE2ETest.TEST_PET_ERROR_ID, VaccineControllerE2ETest.TEST_SICKNESS_ERROR_ID))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("vaccinesError")).andExpect(MockMvcResultMatchers.view().name("vaccines/vaccineError"));

	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian", "admin"
	})
	@Test
	void testShowVaccineShowHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines/{vaccineId}", VaccineControllerE2ETest.TEST_PET_ID, VaccineControllerE2ETest.TEST_SICKNESS_ID, VaccineControllerE2ETest.TEST_VACCINE_ID))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("vaccine")).andExpect(MockMvcResultMatchers.view().name("vaccines/vaccineShow"));

	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian", "admin"
	})
	@Test
	void testShowVaccineShowErrorHtml() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines/{vaccineId}", VaccineControllerE2ETest.TEST_PET_SHOW_ERROR_ID, VaccineControllerE2ETest.TEST_SICKNESS_SHOW_ERROR_ID,
				VaccineControllerE2ETest.TEST_VACCINE_ERROR_ID))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("vaccineError")).andExpect(MockMvcResultMatchers.view().name("vaccines/vaccineDetailsError"));
	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian", "admin"
	})
	@Test
	void testDeleteVaccine() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/delete", VaccineControllerE2ETest.TEST_VACCINE_ID)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.view().name("welcome"));
	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian", "admin"
	})
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/vets/newVaccine").with(SecurityMockMvcRequestPostProcessors.csrf()).param("name", "Vacuna RR").param("sickness", "Otitis").param("months", "9").param("components", "H20"))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

}
