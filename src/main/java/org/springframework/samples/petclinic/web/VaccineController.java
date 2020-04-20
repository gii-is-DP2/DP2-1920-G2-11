
package org.springframework.samples.petclinic.web;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
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
		if (this.vaccineService.findVaccinesBySicknessId(sicknessId).isEmpty()) {
			return "vaccines/vaccineError";
		} else {
			model.put("vaccines", this.vaccineService.findVaccinesBySicknessId(sicknessId));
			return "vaccines/vaccinesList";
		}
	}

	@GetMapping(value = "/owners/*/pets/{petId}/sicknesses/{sicknessId}/vaccines/{vaccineId}")
	public String showVaccine(@PathVariable final int vaccineId, final Map<String, Object> model) {
		Optional<Vaccine> vaccine = this.vaccineService.findVaccineById(vaccineId);
		if (vaccine.get().getComponents().isEmpty() || vaccine.get().getMonths().equals(0)) {
			return "vaccines/vaccineDetailsError";
		} else {
			model.put("vaccine", this.vaccineService.findVaccineById(vaccineId));
			return "vaccines/vaccineShow";

		}
	}

	/* Crear nueva vacuna */

	@GetMapping(value="/vets/listVaccine")
	public String listVaccine(final ModelMap modelMap) {
		String view = "vaccines/vaccinesList";
		Iterable<Vaccine> vaccines = this.vaccineService.findAll();
		modelMap.addAttribute("vaccines", vaccines);
		return view;
	}

	
	
	@GetMapping(path="vets/delete/{vaccineId}")
	public String deleteVaccine(@PathVariable("vaccineId") int vaccineId,
			ModelMap modelMap) {
		String view = "redirect:/vets/listVaccine";
		Optional<Vaccine> vaccine = this.vaccineService.findVaccineById(vaccineId);
		if (vaccine.isPresent()) {
			this.vaccineService.delete(vaccine.get());
			modelMap.addAttribute("message", "Event sucessfully deleted!");

		} else {

			modelMap.addAttribute("message", "Event not found!");
			view = this.listVaccine(modelMap);
		}
		return view;

	}
	
	// Editar vacuna 
	
	@GetMapping(value = "/vets/edit/{vaccineId}")
	public String editVaccine(@PathVariable("vaccineId") int vaccineId, ModelMap modelMap) {
		Optional<Vaccine> vaccines = this.vaccineService.findVaccineById(vaccineId);
		modelMap.addAttribute(vaccines);
		return "vaccines/editVaccine";
	}
	
	@PostMapping(value="/vets/edit/{vaccineId}")
	public String editingVaccine(@Valid Vaccine vaccines,
			@PathVariable("vaccineId") int vaccineId, BindingResult result, ModelMap modelMap) {
		String view;
		if(result.hasErrors()) {
			modelMap.addAttribute("message", "Operation failed!");
			view = "vaccines/editVaccine";
		}else {
			vaccines.setId(vaccineId);
			this.vaccineService.saveVaccine(vaccines);
			view = "redirect:/vets/listVaccine";
		}
		return view;
	}
	

	//creación de una vacuna
	@GetMapping(path = "/vets/newVaccines")
	public String createVaccine(final ModelMap modelMap) {
		String view = "vaccines/editVaccine";
		modelMap.addAttribute("vaccines", new Vaccine());
		modelMap.addAttribute("sickness", this.sicknessService.findAll());
		return view;
	}

	@PostMapping(path = "/vets/saveVaccines")
	public String saveVaccine(@RequestParam("sickness") final int sicknessId, @Valid final Vaccine vaccine, final BindingResult result, final ModelMap modelMap) {
		String view = "vaccines/vaccinesList";
		System.out.println(sicknessId);
		if (result.hasErrors()) {
			modelMap.addAttribute("vaccines", vaccine);
			return "vaccines/editVaccine";
		} else {
			this.vaccineService.saveVaccine(vaccine);
			modelMap.addAttribute("message", "Vaccine succesfully saved!");
			view = this.listVaccine(modelMap);
		}
		return view;
	}
}
