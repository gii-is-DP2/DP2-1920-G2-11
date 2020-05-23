/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.repository.ClinicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 *
 */
@Service
public class ClinicService {

	private ClinicRepository clinicRepository;


	@Autowired
	public ClinicService(final ClinicRepository clinicRepository) {
		this.clinicRepository = clinicRepository;
	}

	@Transactional
	public Collection<Clinic> findClinics() throws DataAccessException {
		return this.clinicRepository.findAll();
	}

	public Clinic findById(final int id) throws DataAccessException {
		return this.clinicRepository.findById(id);
	}

	public Collection<Clinic> findByName(final String name) throws DataAccessException {
		return this.clinicRepository.findByName(name);
	}
	
	@Transactional
	public void save(final Clinic clinic) {
		this.clinicRepository.save(clinic);
	}

}
