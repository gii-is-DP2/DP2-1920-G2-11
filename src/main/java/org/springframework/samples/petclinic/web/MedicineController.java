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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Medicine;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.service.MedicineService;
import org.springframework.samples.petclinic.service.PetTypeService;
import org.springframework.samples.petclinic.service.SicknessService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Controller
public class MedicineController {

	private final MedicineService medicineService;
	private final PetTypeService petTypeService;
	private final SicknessService sicknessService;

	@Autowired
	public MedicineController(MedicineService clinicService,PetTypeService petTypeService,SicknessService sicknessService) {
		this.medicineService = clinicService;
		this.petTypeService = petTypeService;
		this.sicknessService = sicknessService;
	}

	@GetMapping(value = { "/medicines/admin/" })
	public String showMedicineList(Map<String, Object> model) {
		List<Medicine> medicines = new ArrayList<Medicine>();
		medicines.addAll(this.medicineService.findMedicines());
		model.put("medicines", medicines);
		return "medicines/medicineList";
	}
	
	@GetMapping(value = "/medicines/owner/petType/{petTypeId}/sickness/{sicknessId}/")
	public String showMedicinesBySicknessAndPetType(final Map<String, Object> model, @PathVariable final int sicknessId, @PathVariable final int petTypeId) {
		List<Sickness> sickness = new ArrayList<Sickness>();
		sickness.addAll(this.sicknessService.findSicknesses());
		model.put("sicknesses", sickness);
		List<PetType> petTypes = new ArrayList<PetType>();
		petTypes.addAll(this.petTypeService.findPetTypes());
		model.put("petTypes", petTypes);
		Collection<Medicine> medicines = this.medicineService.findMedicinesBySicknessIdAndPetTypeId(sicknessId, petTypeId);
		if (medicines.isEmpty()) {
			medicines = this.medicineService.findMedicines();
		}
		model.put("medicines", medicines);
		return "medicines/filterMedicines";

	}
}
