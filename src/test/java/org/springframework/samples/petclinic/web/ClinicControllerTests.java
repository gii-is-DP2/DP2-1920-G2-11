
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
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = ClinicController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class ClinicControllerTests {

	@Autowired
	private ClinicController	clinicController;

	@MockBean
	private ProductService		productService;

	@MockBean
	private ClinicService		clinicService;

	@Autowired
	private MockMvc				mockMvc;

	private static final int	TEST_CLINIC_ID	= 1;


	@BeforeEach
	void setup() {
		List<Clinic> clinics = new ArrayList<Clinic>();
		Clinic clinic1 = new Clinic();
		clinic1.setId(1);
		clinic1.setName("Winston Pet Cares");
		clinic1.setAddress("Evergreen Av. 4");
		clinic1.setCity("Pitsburg");
		clinic1.setEmail("charles@mail.com");
		clinic1.setTelephone("600033472");

		Clinic clinic2 = new Clinic();
		clinic2.setId(2);
		clinic2.setName("Veterinaria Nervión");
		clinic2.setAddress("Calle Juan nº10");
		clinic2.setCity("Sevilla");
		clinic2.setEmail("vetnervion@mail.com");
		clinic2.setTelephone("685123477");

		clinics.add(clinic1);
		clinics.add(clinic2);

		BDDMockito.given(this.clinicService.findById(ClinicControllerTests.TEST_CLINIC_ID)).willReturn(clinic1);
		

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowClinics() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("clinics")).andExpect(MockMvcResultMatchers.view().name("clinics/clinicsList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowProduct() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics/{clinicId}", ClinicControllerTests.TEST_CLINIC_ID)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("clinics"))
			.andExpect(MockMvcResultMatchers.view().name("clinics/clinicsShow"));

	}
	
	//TODO: casos negativos, crear clinica con error

}
