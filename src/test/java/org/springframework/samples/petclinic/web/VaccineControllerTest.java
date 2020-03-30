
package org.springframework.samples.petclinic.web;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = VaccineController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class VaccineControllerTest {

	@Autowired
	private VaccineController	vaccineController;

	@MockBean
	private VaccineService		vaccineService;

	@Autowired
	private MockMvc				mockMvc;

	private static final int	TEST_PET_ID			= 1;
	private static final int	TEST_SICKNESS_ID	= 1;
	private static final int	TEST_VACCINE_ID		= 1;


	@BeforeEach
	void setup() {

		BDDMockito.given(this.vaccineService.findVaccinesBySicknessId(VaccineControllerTest.TEST_SICKNESS_ID)).willReturn(new ArrayList<Vaccine>());
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowVaccineListHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines", VaccineControllerTest.TEST_PET_ID, VaccineControllerTest.TEST_SICKNESS_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeExists("vaccines")).andExpect(MockMvcResultMatchers.view().name("vaccines/vaccineList"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowVaccineErrorHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines", VaccineControllerTest.TEST_PET_ID, VaccineControllerTest.TEST_SICKNESS_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("vaccines")).andExpect(MockMvcResultMatchers.view().name("vaccines/vaccineError"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowVaccineShowHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines/{vaccineId}", VaccineControllerTest.TEST_PET_ID, VaccineControllerTest.TEST_SICKNESS_ID, VaccineControllerTest.TEST_VACCINE_ID))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("vaccine")).andExpect(MockMvcResultMatchers.view().name("vaccines/vaccineShow"));

	}

}
