
package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClinicController {

	private final ClinicService		clinicService;
	private final ProductService	productService;


	@Autowired
	public ClinicController(final ClinicService clinicService, final ProductService productService/* , final ProductTypeService productTypeService */) {
		this.clinicService = clinicService;
		this.productService = productService;
	}
 //lista de clinicas
	@GetMapping(value = "/clinics") 
	public String showClinics(final Map<String, Object> model) {
		model.put("clinics", this.clinicService.findClinics());
		return "clinics/clinicsList";
	}

	@GetMapping(value = "/clinics/{clinicId}")
	public String showClinic(final Map<String, Object> model, @PathVariable final int clinicId) {
		Clinic clinic = this.clinicService.findById(clinicId);
		if(clinic.getTelephone().isEmpty() && clinic.getAddress().isEmpty()
				&& clinic.getCity().isEmpty() && clinic.getEmail().isEmpty()) {
			return "clinics/clinicDetailsError";
		}
		model.put("clinics", this.clinicService.findById(clinicId));
		return "clinics/clinicsShow";

	}

	@GetMapping(value = "clinics/{clinicId}/delete")
	public String deleteClinic(@PathVariable("clinicId") final int clinicId, final ModelMap modelMap) {
		String view = "clinics/clinicList";
		Clinic clinic = this.clinicService.findById(clinicId);
		if (clinic != null) {
			this.clinicService.delete(clinic);
			modelMap.addAttribute("message", "Clinic deleted!");
			view = this.showClinics(modelMap);
		} else {
			modelMap.addAttribute("message", "ERROR!");
		}
		return view;

	}

	



}
