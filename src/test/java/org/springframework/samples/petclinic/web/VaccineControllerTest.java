
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
		iF.setSymptom("Infecciones bucales, patologías respiratorias, infecciones intestinales");
		iF.setSeverity(3);
		PetType cat = new PetType();
		cat.setId(1);
		cat.setName("cat");
		iF.setType(cat);
		f.setSickness(iF);
		f2.setSickness(iF);
		vaccines.add(f);
		vaccines.add(f2);

		//SHOW
		Vaccine vaccine = new Vaccine();
		vaccine.setId(1);
		vaccine.setName("Vacuna A");
		vaccine.setComponents("Q,W,E,R");
		vaccine.setMonths(4);
		Sickness sickness = new Sickness();
		sickness.setId(1);
		sickness.setName("Otitis");
		sickness.setCause("Parásitos(ácaros), hongos, bacterias");
		sickness.setSymptom("Inflamación del conjunto auditivo, dolor, pérdida de audición");
		sickness.setSeverity(2);
		sickness.setType(cat);
		vaccine.setSickness(sickness);

		BDDMockito.given(this.vaccineService.findVaccinesBySicknessId(VaccineControllerTest.TEST_SICKNESS_ID)).willReturn(vaccines);
		BDDMockito.given(this.vaccineService.findVaccineById(VaccineControllerTest.TEST_VACCINE_ID)).willReturn(vaccine);
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowVaccineListHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines", VaccineControllerTest.TEST_PET_ID, VaccineControllerTest.TEST_SICKNESS_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeExists("vaccines")).andExpect(MockMvcResultMatchers.view().name("vaccines/vaccinesList"));

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
