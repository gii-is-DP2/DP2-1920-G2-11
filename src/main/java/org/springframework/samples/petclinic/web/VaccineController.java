
package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Vaccine;
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
		if (this.vaccineService.findVaccinesBySicknessId(sicknessId).isEmpty()) {
			return "vaccines/vaccineError";
		} else {
			model.put("vaccines", this.vaccineService.findVaccinesBySicknessId(sicknessId));
			return "vaccines/vaccinesList";
		}
	}

	@GetMapping(value = "/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines/{vaccineId}")
	public String showVaccine(@PathVariable final int vaccineId, final Map<String, Object> model) {
		Vaccine vaccine = this.vaccineService.findVaccineById(vaccineId);
		if (vaccine.getComponents().isEmpty() || vaccine.getMonths().equals(0)) {
			return "vaccines/vaccineDetailsError";
		} else {
			model.put("vaccine", this.vaccineService.findVaccineById(vaccineId));
			return "vaccines/vaccineShow";
		}
	}
}
