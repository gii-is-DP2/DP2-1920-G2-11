package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.samples.petclinic.service.ProductTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClinicController {

	private final ClinicService			clinicService;
	private final ProductService		productService;
	private final ProductTypeService	productTypeService;


	@Autowired
	public ClinicController(final ClinicService clinicService, final ProductService productService, final ProductTypeService productTypeService) {
		this.clinicService = clinicService;
		this.productService = productService;
		this.productTypeService = productTypeService;
	}

	@GetMapping(value = "/clinics")
	public String showClinics(final Map<String, Object> model) {
		model.put("clinics", this.clinicService.findClinics());
		return "clinics/clinicsList";
	}

		@GetMapping(value = "/clinics/producttype/{productTypeId}")
	public String showClinicsByProductType(@PathVariable int productTypeId, Map<String, Object> model) {
		List<Clinic> clinics = new ArrayList<Clinic>();
		clinics.addAll(this.clinicService.findClinicsByProductTypeId(productTypeId));
		model.put("clinics", clinics);
		return "clinics/clinicList";
	}

	@GetMapping(value = "/productTypes")
	public void showProductTypes(final Map<String, Object> model) {
		model.put("productTypes", this.productTypeService.findProductTypes());
	}

	@GetMapping(value = "/clinics/{clinicId}")
	public String showClinic(final Map<String, Object> model, @PathVariable final int clinicId) {
		model.put("clinics", this.clinicService.findById(clinicId));
		return "clinics/clinicsShow";

	}

}