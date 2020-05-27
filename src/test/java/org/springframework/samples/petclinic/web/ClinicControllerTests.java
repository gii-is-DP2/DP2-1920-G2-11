
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
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = ClinicController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class ClinicControllerTests {

	@Autowired
	private ClinicController clinicController;

	@MockBean
	private ProductService productService;

	@MockBean
	private ClinicService clinicService;

	@Autowired
	private MockMvc mockMvc;

	private static final int TEST_CLINIC_ID = 1;

	private static final int TEST_CLINIC_ERROR_ID = 3;

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

		// SHOW
		Clinic c = new Clinic();
		c.setId(2);
		c.setName("Veterinaria Nervión");
		c.setAddress("Calle Juan 10");
		c.setCity("Sevilla");
		c.setEmail("vetnervion@mail.com");
		c.setTelephone("685123477");

		// SHOW ERROR
		Clinic c1 = new Clinic();
		c1.setId(3);
		c1.setName("Clínica Los Arcos");
		c1.setAddress("");
		c1.setCity("");
		c1.setEmail("");
		c1.setTelephone("");

		// SHOW ERROR
		Clinic cError = new Clinic();
		cError.setId(30);
		cError.setName("");
		cError.setAddress("calle Estrella");
		cError.setCity("Sevilla");
		cError.setEmail("");
		cError.setTelephone("");

		BDDMockito.given(this.clinicService.findById(ClinicControllerTests.TEST_CLINIC_ID)).willReturn(clinic1);
		BDDMockito.given(this.clinicService.findById(ClinicControllerTests.TEST_CLINIC_ERROR_ID)).willReturn(c1);

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowClinics() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("clinics"))
				.andExpect(MockMvcResultMatchers.view().name("clinics/clinicsList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowClinic() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics/{clinicId}", ClinicControllerTests.TEST_CLINIC_ID))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("clinics"))
				.andExpect(MockMvcResultMatchers.view().name("clinics/clinicsShow"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowClinicErrorHtml() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/clinics/{clinicId}", ClinicControllerTests.TEST_CLINIC_ERROR_ID))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("c1"))
				.andExpect(MockMvcResultMatchers.view().name("clinics/clinicDetailsError"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/clinics/new"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("clinics/editClinic"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationFormHasError() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/clinics/save")
				.with(SecurityMockMvcRequestPostProcessors.csrf())
				.param("name", "")
				.param("address", "nombre")
				.param("city", "marbella")
				.param("email", "")
				.param("telephone", ""))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeHasErrors("clinic"))
				.andExpect(MockMvcResultMatchers.view().name("clinics/editClinic"));

	}
}
