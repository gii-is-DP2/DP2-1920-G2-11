
package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.SicknessService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = SicknessController.class, includeFilters = @ComponentScan.Filter(value = PetTypeFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
	excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class SicknessControllerTest {

	@Autowired
	private SicknessController	sicknessController;

	@Autowired
	private PetTypeFormatter	petTypeFormatter;

	@MockBean
	private PetService			petService;

	@MockBean
	private SicknessService		sicknessService;

	@Autowired
	private MockMvc				mockMvc;

	private static final int	TEST_PET_ID				= 1;

	private static final int	TEST_PET_ERROR_ID		= 14;

	private static final int	TEST_SICKNESS_ID		= 1;

	private static final int	TEST_PET_SHOW_ERROR_ID	= 11;

	private static final int	TEST_SICKNESS_ERROR_ID	= 30;


	@BeforeEach
	void setup() {
		List<Sickness> sicknesses = new ArrayList<Sickness>();
		Sickness rabia = new Sickness();
		rabia.setId(3);
		rabia.setName("Rabia");
		rabia.setCause("Virus");
		rabia.setSymptom("Irritabilidad, exceso de salivación, fiebre, vómitos");
		rabia.setSeverity(1);
		PetType cat = new PetType();
		cat.setId(1);
		cat.setName("cat");
		rabia.setType(cat);
		sicknesses.add(rabia);

		// SHOW
		Sickness sickness = new Sickness();
		sickness.setId(1);
		sickness.setName("Otitis");
		sickness.setCause("Parásitos(ácaros), hongos, bacterias");
		sickness.setSymptom("Inflamación del conjunto auditivo, dolor, pérdida de audición");
		sickness.setSeverity(2);
		sickness.setType(cat);

		// LIST ERROR
		List<Sickness> sicknessesError = new ArrayList<Sickness>();

		// SHOW ERROR
		Sickness sicknessError = new Sickness();
		sicknessError.setId(30);
		sicknessError.setName("Pájaro Loco");
		sicknessError.setCause("");
		sicknessError.setSymptom("");
		sicknessError.setSeverity(0);
		PetType bird = new PetType();
		bird.setId(5);
		bird.setName("bird");
		sicknessError.setType(bird);

		BDDMockito.given(this.sicknessService.findSicknessesByPetId(SicknessControllerTest.TEST_PET_ID)).willReturn(sicknesses);
		BDDMockito.given(this.sicknessService.findSicknessesById(SicknessControllerTest.TEST_SICKNESS_ID)).willReturn(sickness);
		BDDMockito.given(this.sicknessService.findSicknessesByPetId(SicknessControllerTest.TEST_PET_ERROR_ID)).willReturn(sicknessesError);
		BDDMockito.given(this.sicknessService.findSicknessesById(SicknessControllerTest.TEST_SICKNESS_ERROR_ID)).willReturn(sicknessError);
		BDDMockito.given(this.petService.findPetTypes()).willReturn(Lists.newArrayList(cat));

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowSicknessesListHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses", SicknessControllerTest.TEST_PET_ID)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("sicknesses"))
			.andExpect(MockMvcResultMatchers.view().name("sicknesses/sicknessList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowSicknessesErrorHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses", SicknessControllerTest.TEST_PET_ERROR_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("sicknessesError")).andExpect(MockMvcResultMatchers.view().name("sicknesses/sicknessError"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowSicknessesShowHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}", SicknessControllerTest.TEST_PET_ID, SicknessControllerTest.TEST_SICKNESS_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeExists("sickness")).andExpect(MockMvcResultMatchers.view().name("sicknesses/sicknessShow"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowSicknessesShowErrorHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}", SicknessControllerTest.TEST_PET_SHOW_ERROR_ID, SicknessControllerTest.TEST_SICKNESS_ERROR_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("sicknessError")).andExpect(MockMvcResultMatchers.view().name("sicknesses/sicknessDetailsError"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testDeleteSickness() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/delete/{sicknessId}", SicknessControllerTest.TEST_PET_ID, SicknessControllerTest.TEST_SICKNESS_ID)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.view().name("welcome"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/vets/newSickness")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("sicknesses/editSickness"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.post("/vets/saveSickness").with(SecurityMockMvcRequestPostProcessors.csrf()).param("name", "Betty").param("cause", "Cause 1").param("symptom", "Symptom 1").param("severity", "1").param("type", "cat"))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.view().name("welcome"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors1() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.post("/vets/saveSickness").with(SecurityMockMvcRequestPostProcessors.csrf()).param("cause", "Cause 1").param("symptom", "Sintoma 1")).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeHasErrors("sickness")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "name"))
			.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "severity")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "type"))
			.andExpect(MockMvcResultMatchers.view().name("sicknesses/editSickness"));

	}

	@WithMockUser(value = "spring")
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

	// UPDATE TESTS

	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/*/sicknesses/{sicknessId}/editSickness", SicknessControllerTest.TEST_SICKNESS_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("sicknesses/updateSickness"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/owners/*/pets/*/sicknesses/{sicknessId}/updateSickness", SicknessControllerTest.TEST_SICKNESS_ID).with(SecurityMockMvcRequestPostProcessors.csrf()).param("name", "Betty").param("cause", "Cause 1")
			.param("symptom", "Symptom 1").param("severity", "1").param("type", "cat")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.view().name("welcome"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormHasErrors1() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/owners/*/pets/*/sicknesses/{sicknessId}/updateSickness", SicknessControllerTest.TEST_SICKNESS_ID).with(SecurityMockMvcRequestPostProcessors.csrf()).param("cause", "Cause 1").param("symptom", "Sintoma 1"))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeHasErrors("sickness")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "name"))
			.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "severity")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "type"))
			.andExpect(MockMvcResultMatchers.view().name("sicknesses/updateSickness"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormHasErrors2() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/owners/*/pets/*/sicknesses/{sicknessId}/updateSickness", SicknessControllerTest.TEST_SICKNESS_ID).with(SecurityMockMvcRequestPostProcessors.csrf())
				.param("name", "12345678901234567890123456789012345678901234567890123456789012345678901234567890")
				.param("cause", "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890")
				.param("symptom", "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890").param("severity", "5"))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeHasErrors("sickness")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "name"))
			.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "cause")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "symptom"))
			.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "severity")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("sickness", "type"))
			.andExpect(MockMvcResultMatchers.view().name("sicknesses/updateSickness"));

	}

}
