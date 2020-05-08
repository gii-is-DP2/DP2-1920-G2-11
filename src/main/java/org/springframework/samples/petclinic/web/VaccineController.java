
package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

	private final VaccineService vaccineService;

	private final SicknessService sicknessService;

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

	/* listar tooodas las vucanas */

//	@GetMapping(value = "/vets/listVaccine")
//	public String listVaccine(final ModelMap modelMap) {
//		String view = "vaccines/vaccinesList";
//		Iterable<Vaccine> vaccines = this.vaccineService.findAll();
//		modelMap.addAttribute("vaccines", vaccines);
//		return view;
//	}

	/* eliminar una vacuna */

	/* */
	@GetMapping(path = "/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/delete")
	public String deleteVaccine(@PathVariable("vaccineId") final int vaccineId,
			final ModelMap modelMap) {
		String view = "welcome";
		
		Vaccine vaccine = this.vaccineService.findVaccineById(vaccineId);

		this.vaccineService.delete(vaccine);
		modelMap.addAttribute("message", "Event sucessfully deleted!");

		modelMap.addAttribute("message", "Event not found!");
		//view = this.showVaccines(sicknessId, modelMap);

		return view;

	}

	// Editar vacuna

	@GetMapping(value = "/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/edit")
	public String editVaccine(@PathVariable("vaccineId") int vaccineId, ModelMap modelMap) {
		Vaccine vaccines = this.vaccineService.findVaccineById(vaccineId);
		modelMap.addAttribute("sickness", this.sicknessService.findAll());
		modelMap.addAttribute("vaccines", vaccines);
		return "vaccines/editVaccine";
	}

	@PostMapping(value = "/owners/*/pets/*/sicknesses/*/vaccines/{vaccineId}/edit")
	public String editingVaccine(@RequestParam("sickness") final int sicknessId,
			@PathVariable("vaccineId") int vaccineId, @Valid Vaccine vaccine, BindingResult result, ModelMap modelMap) {
		String view;
		if (result.hasErrors()) {
			modelMap.addAttribute("message", "Operation failed!");
			modelMap.addAttribute("vaccines", vaccine);
			view = "vaccines/editVaccine";
		} else {

			vaccine.setId(vaccineId);
			this.vaccineService.saveVaccine(vaccine);
			view = "welcome";
		}
		return view;
	}

	// creación de una vacuna
	@GetMapping(path = "/owners/*/pets/*/sicknesses/newVaccine")
	public String createVaccine(final ModelMap modelMap) {
		String view = "vaccines/editVaccine";
		modelMap.addAttribute("vaccines", new Vaccine());
		modelMap.addAttribute("sickness", this.sicknessService.findAll());
		return view;
	}

	@PostMapping(path = "/owners/*/pets/*/sicknesses/newVaccine")
	public String saveVaccine(@RequestParam("sickness") final int sicknessId, @Valid final Vaccine vaccine,
			final BindingResult result, final ModelMap modelMap) {
		String view = "vaccines/vaccinesList";
		System.out.println(sicknessId);
		if (result.hasErrors()) {
			modelMap.addAttribute("vaccines", vaccine);
			return "vaccines/editVaccine";
		} else {
			Sickness enfermedad = this.sicknessService.findSicknessesById(sicknessId);
			vaccine.setSickness(enfermedad);
			this.vaccineService.saveVaccine(vaccine);
			modelMap.addAttribute("message", "Vaccine succesfully saved!");
			view = this.showVaccines(sicknessId, modelMap);
		}
		return view;
	}
}
