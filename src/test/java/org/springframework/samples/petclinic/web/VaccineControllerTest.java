
package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Sickness;
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


	@BeforeEach
	void setup() {

		Vaccine vacunaA = new Vaccine();
		vacunaA.setName("Vacuna A");
		vacunaA.setComponents("Q,W,E,R");
		vacunaA.setMonths(4);
		vacunaA.setId(1);
		Sickness otitis = new Sickness();
		otitis.setId(1);
		otitis.setName("otitis");
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowVaccineListHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/vaccines")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("vaccines"))
			.andExpect(MockMvcResultMatchers.view().name("vaccines/vaccinesList"));
	}
}
