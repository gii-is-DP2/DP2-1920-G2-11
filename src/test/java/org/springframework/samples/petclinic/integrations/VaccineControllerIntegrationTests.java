package org.springframework.samples.petclinic.integrations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.SicknessService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.samples.petclinic.web.VaccineController;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;

import javax.validation.Valid;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VaccineControllerIntegrationTests {
	
	private static final int TEST_VACCINE_ID = 1;
	
	private static final int TEST_SICKNESS_ID = 1;
	
	
	@Autowired
	private VaccineController vaccineController;
	
	@Autowired
	private VaccineService vaccineService;
	
	
	@Autowired
	private SicknessService sicknessService;
	

	
	@Test
	void testInitCreationForm() throws Exception {
		
		ModelMap model=new ModelMap();

		String view=vaccineController.createVaccine(model);

		assertEquals(view,"vaccines/editVaccine");
		assertNotNull(model.get("vaccine"));		
	}
	
	
	
	@Test
	void testProcessCreationFormSuccess() throws Exception {
    	ModelMap model=new ModelMap();
    	
    	Vaccine newVaccine=new Vaccine();
    	Sickness sickness = sicknessService.findSicknessesById(TEST_SICKNESS_ID);
    	newVaccine.setName("Panacea");
		newVaccine.setMonths(2);
		newVaccine.setComponents("Muchos");
		newVaccine.setSickness(sickness);
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");

		String view=vaccineController.saveVaccine(TEST_SICKNESS_ID, newVaccine, bindingResult, model);

		assertEquals(view,"vaccines/vaccinesList");				
	}

}
