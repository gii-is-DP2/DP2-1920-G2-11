
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
import org.springframework.samples.petclinic.model.Medicine;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.MedicineService;
import org.springframework.samples.petclinic.service.PetTypeService;
import org.springframework.samples.petclinic.service.SicknessService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = MedicineController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class MedicineControllerTest {

	@Autowired
	private MedicineController	medicineController;

	@MockBean
	private MedicineService		medicineService;
	
	@MockBean
	private PetTypeService		petTypeService;
	
	@MockBean
	private SicknessService		sicknessService;

	@Autowired
	private MockMvc				mockMvc;

	private static final int	TEST_MEDICINE_ID				= 1;

	private static final int	TEST_MEDICINE_ERROR_ID			= 3;
	
	private static final int	TEST_PETTYPE_ID			= 1;
	
	private static final int	TEST_PETTYPE_ERROR_ID			= 3;
	
	private static final int	TEST_SICKNESS_ID			= 1;
	
	private static final int	TEST_SICKNESS_ERROR_ID			= 3;
	
	


	@BeforeEach
	void setup() {
		List<Sickness> sicknesses = new ArrayList<Sickness>();
		PetType cat = new PetType();
		cat.setId(1);
		cat.setName("cat");
		
		Sickness s1 = new Sickness();
		
		s1.setId(1);
		s1.setName("Rabia");
		s1.setCause("Virus");
		s1.setSymptom("Irritabilidad, exceso de salivación, fiebre, vómitos");
		s1.setSeverity(1);
		s1.setType(cat);
		
		Sickness s2 = new Sickness();
		
		s2.setId(2);
		s2.setName("Tifus");
		s2.setCause("Bacteria");
		s2.setSymptom("Sangrado, fiebre, vómitos");
		s2.setSeverity(1);
		s2.setType(cat);
		
		sicknesses.add(s1);
		sicknesses.add(s2);
		
		
		List<Medicine> medicines = new ArrayList<Medicine>();
		
		Medicine m1 = new Medicine();
		m1.setId(1);
		m1.setName("Medicine A");
		m1.setComponents("Componente A");
		m1.setTreatment("1 cada 8 horas");
		m1.setPetType(cat);
		m1.setSickness(s1);
		
		Medicine m2 = new Medicine();
		m2.setId(2);
		m2.setName("Medicine B");
		m2.setComponents("Componente B");
		m2.setTreatment("1 cada 24 horas");
		m2.setPetType(cat);
		m2.setSickness(s2);
		
		medicines.add(m1);
		medicines.add(m2);

		BDDMockito.given(this.medicineService.findMedicinesBySicknessIdAndPetTypeId(MedicineControllerTest.TEST_SICKNESS_ID, MedicineControllerTest.TEST_PETTYPE_ID)).willReturn(medicines);
		BDDMockito.given(this.medicineService.findById(TEST_MEDICINE_ID)).willReturn(m1);
		BDDMockito.given(this.medicineService.findMedicines()).willReturn(medicines);

	}

	@WithMockUser(value = "spring")
	@Test
	void testShowMedicineListHtml() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/admin/medicines/")).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeExists("medicines")).andExpect(MockMvcResultMatchers.view().name("medicines/medicineList"));

	}


}
