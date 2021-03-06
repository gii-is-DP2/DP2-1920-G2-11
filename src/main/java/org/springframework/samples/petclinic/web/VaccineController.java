
package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.SicknessService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VaccineController {

	private final VaccineService	vaccineService;

	private final SicknessService	sicknessService;


	@Autowired
	public VaccineController(final VaccineService vaccineService, final SicknessService sicknessService) {
		this.vaccineService = vaccineService;
		this.sicknessService = sicknessService;
	}

	@GetMapping(value = "/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines")
	public String showVaccines(@PathVariable final int sicknessId, final Map<String, Object> model) {
		List<Vaccine> vaccines = this.vaccineService.findVaccinesBySicknessId(sicknessId);
		if (vaccines.isEmpty()) {
			return "vaccines/vaccineError";
		} else {
			model.put("vaccines", vaccines);
			return "vaccines/vaccinesList";
		}
	}

	@GetMapping(value = "/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines/{vaccineId}")
	public String showVaccine(@PathVariable final int vaccineId, final Map<String, Object> model) {

		Vaccine vaccine = this.vaccineService.findVaccineById(vaccineId);

		if (vaccine.getComponents().isEmpty() || vaccine.getMonths().equals(0)) {
			return "vaccines/vaccineDetailsError";
		} else {
			model.put("vaccine", vaccine);
			return "vaccines/vaccineShow";

		}
	}

	/* eliminar una vacuna */

	@GetMapping(path = "/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/delete")
	public String deleteVaccine(@PathVariable("vaccineId") final int vaccineId, final ModelMap modelMap) {
		String view;
		Vaccine vaccine = this.vaccineService.findVaccineById(vaccineId);
		if (vaccine != null) {
			view = "welcome";
			this.vaccineService.delete(vaccine);
			modelMap.addAttribute("message", "Event sucessfully deleted!");
		} else {
			modelMap.addAttribute("message", "Event not found!");
			view = "vaccines/vaccineDontExist";
		}

		return view;

	}

	// Editar vacuna

	@GetMapping(value = "/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/edit")
	public String editVaccine(@PathVariable("vaccineId") final int vaccineId, final ModelMap modelMap) {

		Vaccine vaccines = this.vaccineService.findVaccineById(vaccineId);
		Sickness sickness = vaccines.getSickness();
		modelMap.addAttribute("vaccine", vaccines);
		modelMap.addAttribute("sickness", sickness);
		return "vaccines/updateVaccine";
	}

	@PostMapping(value = "/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/edit")
	public String editingVaccine(@Valid final Vaccine vaccine, final BindingResult result, @PathVariable("vaccineId") final int vaccineId, @RequestParam("sickness") final int sicknessId, final ModelMap modelMap) {

		String view;
		if (result.hasErrors()) {
			modelMap.addAttribute("message", "Operation failed!");
			Sickness enfermedad = this.sicknessService.findSicknessesById(sicknessId);
			vaccine.setSickness(enfermedad);
			modelMap.addAttribute("sickness", enfermedad);
			// modelMap.addAttribute("sickness", this.sicknessService.findAll());
			modelMap.addAttribute("vaccine", vaccine);
			view = "vaccines/updateVaccine";

		} else {
			Sickness enfermedad = this.sicknessService.findSicknessesById(sicknessId);
			vaccine.setSickness(enfermedad);
			modelMap.addAttribute("sickness", enfermedad);
			vaccine.setId(vaccineId);
			this.vaccineService.saveVaccine(vaccine);
			view = "welcome";
		}
		return view;
	}

	// creación de una vacuna
	@GetMapping(path = "/vets/newVaccine")
	public String createVaccine(final ModelMap modelMap) {
		String view = "vaccines/editVaccine";
		modelMap.addAttribute("vaccine", new Vaccine());
		modelMap.addAttribute("sicknesses", this.sicknessService.findAllSicknesses());
		return view;
	}

	@PostMapping(path = "/vets/newVaccine")
	public String saveVaccine(@Valid final Vaccine vaccine, final BindingResult result, @RequestParam("sickness") final int sicknessId, final ModelMap modelMap) {
		String view = "welcome";

		if (result.hasErrors()) {
			modelMap.addAttribute("vaccine", vaccine);
			modelMap.addAttribute("sicknesses", this.sicknessService.findAllSicknesses());
			return "vaccines/editVaccine";
		} else {
			Sickness enfermedad = this.sicknessService.findSicknessesById(sicknessId);
			vaccine.setSickness(enfermedad);
			this.vaccineService.saveVaccine(vaccine);
			modelMap.addAttribute("message", "Vaccine succesfully saved!");
		}
		return view;
	}
}
