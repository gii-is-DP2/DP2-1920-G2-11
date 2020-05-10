package org.springframework.samples.petclinic.web.e2e;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc

public class MedicineControllerE2ETest {

	@Autowired
	private MockMvc				mockMvc;

	private static final int	TEST_MEDICINE_ID				= 2;

	private static final int	TEST_MEDICINE_ERROR_ID			= 33;
	
	private static final int	TEST_PETTYPE_ID			= 2;
	
	private static final int	TEST_PETTYPE_ERROR_ID			= 33;
	
	private static final int	TEST_SICKNESS_ID			= 2;
	
	private static final int	TEST_SICKNESS_ERROR_ID			= 33;

	@WithMockUser(username = "admin1", authorities = {
		"admin"
	})
	@Test
	void testShowMedicinesList() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/admin/medicines/")).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeExists("medicines")).andExpect(MockMvcResultMatchers.view().name("medicines/medicineList"));
	}

	@WithMockUser(username = "owner1", authorities = {
		"owner"
	})
	@Test
	void testShowMedicine() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/owner/medicine/{medicineId}", MedicineControllerE2ETest.TEST_MEDICINE_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeExists("medicine")).andExpect(MockMvcResultMatchers.view().name("medicines/medicineDetails"));
	}
	
	@WithMockUser(username = "owner1", authorities = {
			"owner"
		})
	@Test
		void testShowMedicineError() throws Exception {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/owner/medicine/{medicineId}", MedicineControllerE2ETest.TEST_MEDICINE_ERROR_ID)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("medicine")).andExpect(MockMvcResultMatchers.view().name("medicines/medicineDetailsError"));
		}

	
	@WithMockUser(username = "owner1", authorities = {
			"owner"
		})
	@Test
		void testShowMedicineBySicknessAndPetTypeId() throws Exception {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<String,String>();
			params.add("petTypeId", Integer.toString(MedicineControllerE2ETest.TEST_PETTYPE_ID));
			params.add("sicknessId", Integer.toString(MedicineControllerE2ETest.TEST_SICKNESS_ID));
		
			this.mockMvc.perform(MockMvcRequestBuilders.get("/owner/medicines/").queryParams(params)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("medicines")).andExpect(MockMvcResultMatchers.view().name("medicines/filterMedicines"));
		}

	@WithMockUser(username = "owner1", authorities = {
			"owner"
		})
	@Test
		void testShowMedicineBySicknessAndPetTypeIdError() throws Exception {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<String,String>();
			params.add("petTypeId", Integer.toString(MedicineControllerE2ETest.TEST_PETTYPE_ERROR_ID));
			params.add("sicknessId", Integer.toString(MedicineControllerE2ETest.TEST_SICKNESS_ERROR_ID));
		
			this.mockMvc.perform(MockMvcRequestBuilders.get("/owner/medicines/").queryParams(params)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("medicines")).andExpect(MockMvcResultMatchers.view().name("medicines/filterMedicinesError"));
		}




}