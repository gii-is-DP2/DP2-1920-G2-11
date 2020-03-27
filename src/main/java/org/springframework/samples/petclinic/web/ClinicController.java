package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClinicController {
	
	private final ClinicService clinicService;
	private final ProductService productService;


	@Autowired
	public ClinicController(final ClinicService clinicService, final ProductService productService) {
		this.clinicService = clinicService;
		this.productService = productService;
	}

	@GetMapping(value = "/clinics")
	public String showClinics(final Map<String, Object> model) {
		model.put("clinics", this.clinicService.findClinics());
		return "clinics/clinicsList";
	}

	

}
