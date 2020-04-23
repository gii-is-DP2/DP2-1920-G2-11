
package org.springframework.samples.petclinic.web.integration;

import java.util.Collections;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.web.SicknessController;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SicknessControllerIntegrationTest {

	private static final int	TEST_SICKNESS_ID		= 1;

	private static final int	TEST_SICKNESS_ID_DELETE	= 2;

	private static final int	TEST_PET_ID				= 1;

	private static final int	TEST_PET_ID_ERROR		= 14;

	private static final int	TEST_SICKNESS_ID_ERROR	= 30;

	@Autowired
	private SicknessController	sicknessController;

	@Autowired
	private PetService			petService;


	@Test
	void testCrearEnfermedad() throws Exception {
		ModelMap model = new ModelMap();

		String view = this.sicknessController.crearEnfermedad(model);

		Assert.assertEquals(view, "sicknesses/editSickness");
		Assert.assertNotNull(model.get("sickness"));
	}

	//TODO Se traga el que no pongamos un type
	@Test
	void testSalvarEnfermedad() throws Exception {
		ModelMap model = new ModelMap();
		Sickness sickness = new Sickness();
		PetType petType = this.petService.findPetTypes().iterator().next();
		sickness.setName("Name X");
		sickness.setCause("Cause X");
		sickness.setSymptom("Symptom X");
		sickness.setSeverity(2);
		sickness.setType(petType);
		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");

		String view = this.sicknessController.salvarEnfermedad(sickness, bindingResult, model);

		Assert.assertEquals(view, "welcome");
		Assert.assertNotNull(model.get("message"));
	}

	//Esto da error
	@Test
	void testSalvarEnfermedadError() throws Exception {
		ModelMap model = new ModelMap();
		Sickness sickness = new Sickness();
		PetType petType = this.petService.findPetTypes().iterator().next();
		sickness.setName("Name X"); //Funciona con esto añadido y no deberia
		sickness.setCause("Cause X");
		sickness.setSymptom("Symptom X");
		sickness.setSeverity(2);
		sickness.setType(petType);
		BindingResult bindingResult = new MapBindingResult(Collections.emptyMap(), "");
		bindingResult.reject("name", "Required!"); //Puede que sea por esto que haya algo que este mal escrito

		String view = this.sicknessController.salvarEnfermedad(sickness, bindingResult, model);

		Assert.assertEquals(view, "sicknesses/editSickness");
		Assert.assertNotNull(model.get("sickness"));
	}

	@Test
	void testShowSicknesses() throws Exception {
		ModelMap model = new ModelMap();

		String view = this.sicknessController.showSicknesses(SicknessControllerIntegrationTest.TEST_PET_ID, model);

		Assert.assertEquals(view, "sicknesses/sicknessList");
		Assert.assertNotNull(model.get("sicknesses"));
	}

	@Test
	void testShowSicknessesError() throws Exception {
		ModelMap model = new ModelMap();

		String view = this.sicknessController.showSicknesses(SicknessControllerIntegrationTest.TEST_PET_ID_ERROR, model);

		Assert.assertEquals(view, "sicknesses/sicknessError");
		Assert.assertTrue(model.isEmpty());
	}

	@Test
	void testShowSickness() throws Exception {
		ModelMap model = new ModelMap();

		String view = this.sicknessController.showSickness(SicknessControllerIntegrationTest.TEST_SICKNESS_ID, model);

		Assert.assertEquals(view, "sicknesses/sicknessShow");
		Assert.assertNotNull(model.get("sickness"));
	}

	@Test
	void testShowSicknessError() throws Exception {
		ModelMap model = new ModelMap();

		String view = this.sicknessController.showSickness(SicknessControllerIntegrationTest.TEST_SICKNESS_ID_ERROR, model);

		Assert.assertEquals(view, "sicknesses/sicknessDetailsError");
		Assert.assertTrue(model.isEmpty());
	}

	@Test
	void testDeteleSickness() throws Exception {
		ModelMap model = new ModelMap();

		String view = this.sicknessController.deleteSickness(SicknessControllerIntegrationTest.TEST_SICKNESS_ID_DELETE, model);

		Assert.assertEquals(view, "welcome");
		Assert.assertNotNull(model.get("message"));
	}
}
