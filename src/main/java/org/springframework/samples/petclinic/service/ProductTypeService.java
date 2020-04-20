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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.ProductType;
import org.springframework.samples.petclinic.repository.ProductRepository;
import org.springframework.samples.petclinic.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petproduct controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Aureliano Piqueras
 */
@Service
public class ProductTypeService {

	private ProductTypeRepository productTypeRepository;


	@Autowired
	public ProductTypeService(final ProductTypeRepository productTypeRepository) {
		this.productTypeRepository = productTypeRepository;
	}

	@Transactional(readOnly = true)
	public Collection<ProductType> findProductTypes() throws DataAccessException {
		return this.productTypeRepository.findAll();
	}


	@Transactional
	public ProductType findProductsById(final int productId) {
		ProductType res = this.productTypeRepository.findById(productId);
		return res;
	}

}
