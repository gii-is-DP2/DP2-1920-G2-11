/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aureliano Piqueras
 */
@Controller
public class ClinicController {

	private final ClinicService clinicService;

	@Autowired
	public ClinicController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@GetMapping(value = { "/clinics" })
	public String showClinicList(Map<String, Object> model) {
		List<Clinic> clinics = new ArrayList<Clinic>();
		clinics.addAll(this.clinicService.findClinics());
		model.put("clinics", clinics);
		return "clinics/clinicList";
	}
	
	
	@GetMapping(value = "/clinics/producttype/{productTypeId}")
	public String showClinicsByProductType(@PathVariable int productTypeId, Map<String, Object> model) {
		List<Clinic> clinics = new ArrayList<Clinic>();
		clinics.addAll(this.clinicService.findClinicsByProductTypeId(productTypeId));
		model.put("clinics", clinics);
		return "clinics/clinicList";
	}


}
