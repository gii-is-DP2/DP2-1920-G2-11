
package org.springframework.samples.petclinic.web.e2e;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.web.VaccineControllerTest;
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
		"veterinarian", "owner"
	})
	@Test
	void testShowVaccineErrorHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines", VaccineControllerE2ETest.TEST_PET_ERROR_ID, VaccineControllerE2ETest.TEST_SICKNESS_ERROR_ID))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("vaccinesError")).andExpect(MockMvcResultMatchers.view().name("vaccines/vaccineError"));

	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian", "owner"
	})
	@Test
	void testShowVaccineShowHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines/{vaccineId}", VaccineControllerE2ETest.TEST_PET_ID, VaccineControllerE2ETest.TEST_SICKNESS_ID, VaccineControllerE2ETest.TEST_VACCINE_ID))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("vaccine")).andExpect(MockMvcResultMatchers.view().name("vaccines/vaccineShow"));

	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian", "owner"
	})
	@Test
	void testShowVaccineShowErrorHtml() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines/{vaccineId}", VaccineControllerE2ETest.TEST_PET_SHOW_ERROR_ID, VaccineControllerE2ETest.TEST_SICKNESS_SHOW_ERROR_ID,
				VaccineControllerE2ETest.TEST_VACCINE_ERROR_ID))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("vaccineError")).andExpect(MockMvcResultMatchers.view().name("vaccines/vaccineDetailsError"));
	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian"
	})
	@Test
	void testDeleteVaccine() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/delete", VaccineControllerE2ETest.TEST_VACCINE_ID)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.view().name("welcome"));
	}
	
	
	@WithMockUser(username = "vet1", authorities = {
			"veterinarian"
		})
	@Test
	void testInitUpdateForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/edit",
				VaccineControllerE2ETest.TEST_VACCINE_ID))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("vaccines/updateVaccine"));
	}
	
	@WithMockUser(username = "vet1", authorities = {
			"veterinarian"
		})
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/edit"
										,VaccineControllerE2ETest.TEST_VACCINE_ID)
						.with(SecurityMockMvcRequestPostProcessors.csrf())
						.param("name", "Vacuna 11")
						.param("components", "koipesol")
						.param("months", "3")
						.param("sickness", "1"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	
	@WithMockUser(username = "vet1", authorities = {
			"veterinarian"
		})
	void testInitCreationForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/vets/newVaccine"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("vaccines/editVaccine"));
	}

	@WithMockUser(username = "vet1", authorities = {
		"veterinarian"
	})
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/vets/newVaccine").with(SecurityMockMvcRequestPostProcessors.csrf()).param("name", "Vacuna RR").param("sickness", "Otitis").param("months", "9").param("components", "H20"))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	@WithMockUser(username = "vet1", authorities = {
			"veterinarian"
		})
	@Test
	void testProcessUpdateVaccineErrors() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/edit", 
				VaccineControllerE2ETest.TEST_VACCINE_ID)
						.with(SecurityMockMvcRequestPostProcessors.csrf())
						.param("name", "")
						.param("components", "kop")
						.param("months", "3")
						.param("sickness", "1"))
			   .andExpect(status().isOk())
			   .andExpect(model().attributeHasErrors("vaccine"))
			   //.andExpect(model().attributeHasFieldErrors("vaccine","name"))
			   .andExpect(view().name("vaccines/updateVaccine"));
	}
	
	
	@WithMockUser(username = "vet1", authorities = {
			"veterinarian"
		})
	@Test
	void testProcessCreationFormHasErrors1() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/vets/newVaccine")
						.with(SecurityMockMvcRequestPostProcessors.csrf()).param("name", "Vacuna RR")
						.param("sickness", "1").param("components", "H2E"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeHasErrors("vaccine"))
				// .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("months"))
				.andExpect(MockMvcResultMatchers.view().name("vaccines/editVaccine"));
	}

	
	
	
}
