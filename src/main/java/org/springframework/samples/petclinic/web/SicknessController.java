
package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.service.SicknessService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SicknessController {

	private final SicknessService sicknessService;


	@Autowired
	public SicknessController(final SicknessService sicknessService) {
		this.sicknessService = sicknessService;
	}

	@GetMapping(value = "/owners/*/pets/{petId}/sicknesses")
	public String showSicknesses(@PathVariable final int petId, final Map<String, Object> model) {
		if (this.sicknessService.findSicknessesByPetId(petId).isEmpty()) {
			return "sicknesses/sicknessError";
		} else {
			model.put("sicknesses", this.sicknessService.findSicknessesByPetId(petId));
			return "sicknesses/sicknessList";
		}
	}

	@GetMapping(value = "/owners/*/pets/{petId}/sicknesses/{sicknessId}")
	public String showSickness(@PathVariable final int sicknessId, final Map<String, Object> model) {
		Sickness sickness = this.sicknessService.findSicknessesById(sicknessId);
		if (sickness.getSymptom().isEmpty() || sickness.getSeverity().equals(0)) {
			return "sicknesses/sicknessDetailsError";
		} else {
			model.put("sickness", this.sicknessService.findSicknessesById(sicknessId));
			return "sicknesses/sicknessShow";
		}
	}
}
