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
package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.ProductType;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.ProductRepository;
import org.springframework.samples.petclinic.repository.springdatajpa.ProductTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Mostly used as a facade for all Petproduct controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Aureliano Piqueras
 */
@Service
public class ProductTypeService {

	private ProductTypeRepository productTypeRepository;

//
//	@Autowired
//	public ProductTypeService(ProductRepository productRepository) {
//		this.productRepository = productRepository;
//	}		
//
//	@Transactional(readOnly = true)	
//	public Collection<Product> findProducts() throws DataAccessException {
//		return productRepository.findAll();
//	}	
	
	

//	@Transactional
//	public Collection<Vaccine> findVaccinesBySicknessId(final int sicknessId) {
//
//		Sickness sickness = this.sicknessRepository.findById(sicknessId);
//		int sickness_id = sickness.getId();
//		List<Vaccine> res = this.vaccineRepository.findVaccinesBySicknessId(sickness_id);
//
//		return res;
//	}
	
	@Transactional
	public Collection<Product> findByProductTypeId(int productId){
	
		List<Product> res = this.productTypeRepository.findProductType(productId).getProducts();
//		int type_id = product.getProductType().getId();
		return res;
	}
	
	
	
	@Transactional 
	public List<ProductType> findAllProductTypes(){
		List<ProductType> res = this.productTypeRepository.findAll();
		return res;
	}

}



