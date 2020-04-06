
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
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.service.SicknessService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = SicknessController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class SicknessControllerTest {

	@Autowired
	private SicknessController	sicknessController;

	@MockBean
	private SicknessService		sicknessService;

	@Autowired
	private MockMvc				mockMvc;

	private static final int	TEST_PET_ID			= 1;

	private static final int	TEST_SICKNESS_ID	= 1;


	@BeforeEach
	void setup() {
		List<Sickness> sicknesses = new ArrayList<Sickness>();
		Sickness sickness = new Sickness();
		sicknesses.add(sickness);
		BDDMockito.given(this.sicknessService.findSicknessesByPetId(SicknessControllerTest.TEST_PET_ID)).willReturn(sicknesses);
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
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses", SicknessControllerTest.TEST_PET_ID)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("sicknesses"))
			.andExpect(MockMvcResultMatchers.view().name("sicknesses/sicknessError"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowSicknessesShowHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}", SicknessControllerTest.TEST_PET_ID, SicknessControllerTest.TEST_SICKNESS_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeExists("sickness")).andExpect(MockMvcResultMatchers.view().name("sicknesses/sicknessShow"));
	}

}
