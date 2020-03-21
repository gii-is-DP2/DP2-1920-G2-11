
package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VaccineController {

	private final VaccineService vaccineService;


	@Autowired
	public VaccineController(final VaccineService vaccineService) {
		this.vaccineService = vaccineService;
	}

	@GetMapping(value = "/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines")
	public String showVaccines(@PathVariable final int sicknessId, final Map<String, Object> model) {
		model.put("vaccines", this.vaccineService.findVaccinesBySicknessId(sicknessId));
		return "vaccines/vaccinesList";
	}
}
