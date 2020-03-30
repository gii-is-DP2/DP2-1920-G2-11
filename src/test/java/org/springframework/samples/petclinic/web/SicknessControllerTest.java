
package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.PetType;
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


	@BeforeEach
	void setup() {

		Sickness otitis = new Sickness();
		otitis.setName("Otitis");
		otitis.setCause("Parásitos(ácaros), hongos, bacterias");
		otitis.setSymptom("Inflamación del conjunto auditivo, dolor, pérdida de audición");
		otitis.setSeverity(2);
		otitis.setId(1);
		PetType cat = new PetType();
		cat.setId(1);
		cat.setName("cat");
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowSicknessListHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sicknesses")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("sicknesses"))
			.andExpect(MockMvcResultMatchers.view().name("sicknesses/sicknessList"));
	}

}
