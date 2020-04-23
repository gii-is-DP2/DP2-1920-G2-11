
package org.springframework.samples.petclinic.web.integration;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.web.VaccineController;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VaccineControllerIntegrationTest {

	private static final int	TEST_SICKNESS_ID		= 1;

	private static final int	TEST_SICKNESS_ID_ERROR	= 7;

	private static final int	TEST_VACCINE_ID			= 1;

	private static final int	TEST_VACCINE_ID_ERROR	= 8;

	@Autowired
	private VaccineController	vaccineController;


	@Test
	void testShowVaccines() {
		ModelMap model = new ModelMap();

		String view = this.vaccineController.showVaccines(VaccineControllerIntegrationTest.TEST_SICKNESS_ID, model);

		Assert.assertEquals(view, "vaccines/vaccinesList");
		Assert.assertNotNull(model.get("vaccines"));
	}

	@Test
	void testShowVaccinesError() {
		ModelMap model = new ModelMap();

		String view = this.vaccineController.showVaccines(VaccineControllerIntegrationTest.TEST_SICKNESS_ID_ERROR, model);

		Assert.assertEquals(view, "vaccines/vaccineError");
		Assert.assertTrue(model.isEmpty());
	}

	@Test
	void testShowVaccine() {
		ModelMap model = new ModelMap();

		String view = this.vaccineController.showVaccine(VaccineControllerIntegrationTest.TEST_VACCINE_ID, model);

		Assert.assertEquals(view, "vaccines/vaccineShow");
		Assert.assertNotNull(model.get("vaccine"));
	}

	@Test
	void testShowVaccineError() {
		ModelMap model = new ModelMap();

		String view = this.vaccineController.showVaccine(VaccineControllerIntegrationTest.TEST_VACCINE_ID_ERROR, model);

		Assert.assertEquals(view, "vaccines/vaccineDetailsError");
		Assert.assertTrue(model.isEmpty());
	}
}
