
package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.SicknessService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = VaccineController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class VaccineControllerTest {

	@Autowired
	private VaccineController vaccineController;

	@MockBean
	private VaccineService vaccineService;

	@MockBean
	private SicknessService sicknessService;

	@Autowired
	private MockMvc mockMvc;
	
	private Vaccine vaccine;

	private static final int TEST_PET_ID = 1;

	private static final int TEST_PET_ERROR_ID = 3;

	private static final int TEST_SICKNESS_ID = 1;

	private static final int TEST_SICKNESS_ERROR_ID = 7;

	private static final int TEST_VACCINE_ID = 1;

	private static final int TEST_PET_SHOW_ERROR_ID = 1;

	private static final int TEST_SICKNESS_SHOW_ERROR_ID = 6;

	private static final int TEST_VACCINE_ERROR_ID = 8;

	@BeforeEach
	void setup() {

		List<Vaccine> vaccines = new ArrayList<Vaccine>();
		Vaccine f = new Vaccine();
		f.setId(6);
		f.setName("Vacuna F");
		f.setComponents("Componente A");
		f.setMonths(12);
		Vaccine f2 = new Vaccine();
		f2.setId(7);
		f2.setName("Vacuna F2");
		f2.setComponents("Componente A");
		f2.setMonths(18);
		Sickness iF = new Sickness();
		iF.setId(6);
		iF.setName("Inmunodeficiencia felina");
		iF.setCause("Lentivirus");
		iF.setSymptom("Infecciones bucales, patologÃ­as respiratorias, infecciones intestinales");
		iF.setSeverity(3);
		PetType cat = new PetType();
		cat.setId(1);
		cat.setName("cat");
		iF.setType(cat);
		f.setSickness(iF);
		f2.setSickness(iF);
		vaccines.add(f);
		vaccines.add(f2);

		// SHOW
		Vaccine vaccine = new Vaccine();
		vaccine.setId(1);
		vaccine.setName("Vacuna A");
		vaccine.setComponents("Q,W,E,R");
		vaccine.setMonths(4);
		Sickness sickness = new Sickness();
		sickness.setId(1);
		sickness.setName("Otitis");
		sickness.setCause("ParÃ¡sitos(Ã¡caros), hongos, bacterias");
		sickness.setSymptom("InflamaciÃ³n del conjunto auditivo, dolor, pÃ©rdida de audiciÃ³n");
		sickness.setSeverity(2);
		sickness.setType(cat);
		vaccine.setSickness(sickness);

		// LIST ERROR
		List<Vaccine> vaccinesError = new ArrayList<Vaccine>();

		// SHOW ERROR
		Vaccine vaccineError = new Vaccine();
		vaccineError.setId(8);
		vaccineError.setName("Vacunote");
		vaccineError.setComponents("");
		vaccineError.setMonths(0);
		vaccineError.setSickness(iF);

		BDDMockito.given(this.vaccineService.findVaccinesBySicknessId(VaccineControllerTest.TEST_SICKNESS_ID))
				.willReturn(vaccines);
		BDDMockito.given(this.vaccineService.findVaccineById(VaccineControllerTest.TEST_VACCINE_ID))
				.willReturn(vaccine);
		BDDMockito.given(this.vaccineService.findVaccinesBySicknessId(VaccineControllerTest.TEST_SICKNESS_ERROR_ID))
				.willReturn(vaccinesError);
		BDDMockito.given(this.vaccineService.findVaccineById(VaccineControllerTest.TEST_VACCINE_ERROR_ID))
				.willReturn(vaccineError);
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowVaccineListHtml() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines",
						VaccineControllerTest.TEST_PET_ID, VaccineControllerTest.TEST_SICKNESS_ID))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("vaccines"))
				.andExpect(MockMvcResultMatchers.view().name("vaccines/vaccinesList"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowVaccineErrorHtml() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines",
						VaccineControllerTest.TEST_PET_ERROR_ID, VaccineControllerTest.TEST_SICKNESS_ERROR_ID))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("vaccinesError"))
				.andExpect(MockMvcResultMatchers.view().name("vaccines/vaccineError"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowVaccineShowHtml() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get(
						"/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines/{vaccineId}",
						VaccineControllerTest.TEST_PET_ID, VaccineControllerTest.TEST_SICKNESS_ID,
						VaccineControllerTest.TEST_VACCINE_ID))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("vaccine"))
				.andExpect(MockMvcResultMatchers.view().name("vaccines/vaccineShow"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowVaccineShowErrorHtml() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get(
						"/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines/{vaccineId}",
						VaccineControllerTest.TEST_PET_SHOW_ERROR_ID, VaccineControllerTest.TEST_SICKNESS_SHOW_ERROR_ID,
						VaccineControllerTest.TEST_VACCINE_ERROR_ID))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("vaccineError"))
				.andExpect(MockMvcResultMatchers.view().name("vaccines/vaccineDetailsError"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testDeleteVaccine() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/delete",
						VaccineControllerTest.TEST_VACCINE_ID))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/vets/newVaccine")
						.with(SecurityMockMvcRequestPostProcessors.csrf()).param("name", "Vacuna RR")
						.param("sickness", "Otitis").param("months", "9").param("components", "H20"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	// TODO casos negativos

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors1() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/vets/newVaccine")
						.with(SecurityMockMvcRequestPostProcessors.csrf()).param("name", "Vacuna RR")
						.param("sickness", "Otitis").param("months", "8").param("components", "H2E"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeHasErrors("vaccine"))
				.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("vaccine", "name"))
				.andExpect(MockMvcResultMatchers.view().name("vaccines/editVaccine"));
	}

//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessCreationFormHasErrors1() throws Exception {
//		this.mockMvc
//				.perform(MockMvcRequestBuilders.post("/vets/newVaccine")
//						.with(SecurityMockMvcRequestPostProcessors.csrf())
//						.param("sickness", "Otitis").param("months", "9").param("components", "H20"))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.model().attributeHasErrors("vaccine"))
//				.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("vaccine", "name"))
//				.andExpect(MockMvcResultMatchers.view().name("vaccines/editVaccine"));
//	}

//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessCreationFormHasErrorsNameTooLong() throws Exception {
//		this.mockMvc
//				.perform(MockMvcRequestBuilders.post("/vets/newVaccines")
//						.with(SecurityMockMvcRequestPostProcessors.csrf())
//						.param("name",
//								"12345678901234567890123456789012345678901234567890123456789012345678901234567890")
//						.param("sickness", "Otitis").param("months", "9").param("components", "H20"))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.model().attributeHasErrors("vaccine"))
//				.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("vaccine", "name"))
//				.andExpect(MockMvcResultMatchers.view().name("vaccines/editVaccine"));
//	}
	
	
	
}
