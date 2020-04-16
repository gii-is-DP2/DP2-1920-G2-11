
package org.springframework.samples.petclinic.web;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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

	private final VaccineService vaccineService;
	
	private final SicknessService sicknessService;


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
	
	
	@GetMapping()
	public String listVaccine(ModelMap modelMap) {
		String view = "vaccines/vaccinesList";
		Iterable<Vaccine> vaccines = vaccineService.findAll();
		modelMap.addAttribute("vaccines", vaccines);
		return view;
	}
	
	
	@GetMapping(path="vets/delete/{vaccineId}")
	public String deleteVaccine(@PathVariable("vaccineId") int vaccineId,
			ModelMap modelMap) {
		String view = "vaccines/vaccinesList";
		Optional<Vaccine> vaccine = vaccineService.findVaccineById(vaccineId);
		if (vaccine.isPresent()) {
			vaccineService.delete(vaccine.get());
			modelMap.addAttribute("message", "Event sucessfullly deleted!");
			
			
		}else {

			modelMap.addAttribute("message", "Event not found!");
			view = listVaccine(modelMap);
		}
				return view;
		
	}
	
	@GetMapping(path = "/vets/newVaccines")
	public String createVaccine(final ModelMap modelMap) {
		String view = "vaccines/editVaccine";
		modelMap.addAttribute("vaccines", new Vaccine());
		modelMap.addAttribute("sickness", sicknessService.findAll());
		return view;
	}

	@PostMapping(path = "/vets/saveVaccines")
	public String saveVaccine(@RequestParam("sickness") int sicknessId,@Valid final Vaccine vaccine, final BindingResult result, final ModelMap modelMap) {
		String view = "vaccines/vaccinesList";
		System.out.println(sicknessId);
		if (result.hasErrors()) {
			modelMap.addAttribute("vaccines", vaccine);
			return "vaccines/editVaccine";
		} else {
			this.vaccineService.saveVaccine(vaccine);
			modelMap.addAttribute("message", "Vaccine succesfully saved!");
			view = listVaccine(modelMap);
		}
		return view;
	}
}
