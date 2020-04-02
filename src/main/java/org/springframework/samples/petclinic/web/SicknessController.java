
package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.service.SicknessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
		if (sickness.getCause().isEmpty() || sickness.getSymptom().isEmpty() || sickness.getSeverity().equals(0)) {
			return "sicknesses/sicknessDetailsError";
		} else {
			model.put("sickness", this.sicknessService.findSicknessesById(sicknessId));
			return "sicknesses/sicknessShow";
		}
	}

	@GetMapping(value = "/sickness/new")
	public String initCreationForm(final ModelMap model) {
		model.addAttribute("sickness", new Sickness());
		return "sickness/sicknessCreate";
	}

	@PostMapping(value = "/owners/*/pets/{petId}/sicknesses/sickness/save")
	public String processCreationForm(@Valid final Sickness sickness, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			model.put("sickness", sickness);
			return "sickness/sicknessCreate";
		} else {
			this.sicknessService.saveSickness(sickness);
			model.addAttribute("message", "Sickness succesfully saved!");
		}
		return "sicknesses/sicknessList";
	}

	@GetMapping(value = "/owners/*/pets/{petId}/sicknesses/delete/{sicknessId}")
	public String deleteSickness(@PathVariable("sicknessId") final int sicknessId, final ModelMap model) {
		Sickness sickness = this.sicknessService.findSicknessesById(sicknessId);
		this.sicknessService.deleteVaccineFromSickness(sickness);
		this.sicknessService.deleteSickness(sickness);
		model.addAttribute("message", "Sickness succesfully deleted!");

		return "welcome";
	}
}
