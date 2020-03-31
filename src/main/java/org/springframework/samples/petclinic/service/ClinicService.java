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
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.ClinicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Aureliano Piqueras
 */
@Service
public class ClinicService {

	private ClinicRepository clinicRepository;


	@Autowired
	public ClinicService(final ClinicRepository clinicRepository) {
		this.clinicRepository = clinicRepository;
	}

	@Transactional//(readOnly = true)
	public Collection<Clinic> findClinics() throws DataAccessException {
		return this.clinicRepository.findAll();
	}
	@Transactional(readOnly = true)	
		public Collection<Product> findClinicProducts(int clinicId) throws DataAccessException {
			return clinicRepository.findClinicProducts(clinicId);
	}	
	
	@Transactional(readOnly = true)	
	public Clinic findClinicById(int clinicId) throws DataAccessException {
		return clinicRepository.findById(clinicId);
}	


	@Transactional(readOnly = true)	
	public Collection<Vet> findClinicVets(int clinicId) throws DataAccessException {
		return clinicRepository.findClinicVets(clinicId);
	} 
	
	@Transactional(readOnly = true)	
	public Collection<Clinic> findClinicsByProductTypeId(int productTypeId) throws DataAccessException {
		return clinicRepository.findClinicsByProductType(productTypeId);
}	

	public Clinic findById(final int id) throws DataAccessException {
		return this.clinicRepository.findById(id);
	}

}
