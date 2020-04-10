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
import org.springframework.samples.petclinic.service.MedicineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class MedicineController {

	private final MedicineService medicineService;

	@Autowired
	public MedicineController(MedicineService clinicService) {
		this.medicineService = clinicService;
	}

	@GetMapping(value = { "/medicines" })
	public String showMedicineList(Map<String, Object> model) {
		List<Medicine> medicines = new ArrayList<Medicine>();
		medicines.addAll(this.medicineService.findMedicines());
		model.put("medicines", medicines);
		return "medicines/medicineList";
	}
}
