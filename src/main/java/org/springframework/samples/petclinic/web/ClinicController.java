
package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.ProductType;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClinicController {

	private final ClinicService		clinicService;
	private final ProductService	productService;


	@Autowired
	public ClinicController(final ClinicService clinicService, final ProductService productService/* , final ProductTypeService productTypeService */) {
		this.clinicService = clinicService;
		this.productService = productService;
	}

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

	// @GetMapping(value="/clinics/{clinicId}/productType/{productTypeId}")
	// public String showProductsByClinicAndType(final Map<String, Object> model,
	// @PathVariable final int clinicId, @PathVariable final int productTypeId ) {
	//
	// }
	//
	// @GetMapping(value = "/productTypes")
	// public void showProductTypes(final Map<String, Object> model) {
	// model.put("productTypes", this.productTypeService.findAllProductTypes());
	// }

	
	
	@GetMapping(value = "clinics/new")
	public String createClinic(final ModelMap modelMap) {
		
		String view = "clinics/editClinic";
		modelMap.addAttribute("clinic", new Clinic());

		return view;

	}

	@PostMapping(value = "clinics/new")
	public String saveClinic(@Valid final Clinic clinic, final BindingResult result, final ModelMap modelMap) {
		String view = "clinics/clinicsList";
		if (result.hasErrors()) {
			modelMap.addAttribute("clinic", clinic);
			return "clinics/editClinic";
		} else {
			this.clinicService.save(clinic);
			modelMap.addAttribute("message", "Clinic saved!");
			view = this.showClinics(modelMap);
		}
		return view;
	}



}
