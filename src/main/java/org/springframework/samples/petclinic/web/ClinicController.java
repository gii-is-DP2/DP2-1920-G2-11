
package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.ProductService;
import org.springframework.stereotype.Controller;
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

	@GetMapping(value = "/clinics")
	public String showClinics(final Map<String, Object> model) {
		model.put("clinics", this.clinicService.findClinics());
		return "clinics/clinicsList";
	}

	@GetMapping(value = "/clinics/{clinicId}")
	public String showClinic(final Map<String, Object> model, @PathVariable final int clinicId) {

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

	@GetMapping(value = "/clinics/find")
	public String initFindForm(final Map<String, Object> model) {
		model.put("clinic", new Clinic());
		return "clinics/findClinics";
	}

	@GetMapping(value = "/findClinic")
	public String processFindForm(Clinic clinic, final BindingResult result, final Map<String, Object> model) {

		// allow parameterless GET request for /owners to return all records
		if (clinic.getName() == null) {
			clinic.setName(""); // empty string signifies broadest possible search
		}

		// find owners by last name
		Collection<Clinic> results = this.clinicService.findByName(clinic.getName());
		if (results.isEmpty()) {
			// no owners found
			result.rejectValue("name", "notFound", "not found");
			return "clinics/findClinics";
		} else if (results.size() == 1) {
			// 1 owner found
			clinic = results.iterator().next();
			return "redirect:/findClinic/" + clinic.getId();
		} else {
			// multiple owners found
			model.put("selections", results);
			return "clinics/findclinicsList";
		}
	}

}
