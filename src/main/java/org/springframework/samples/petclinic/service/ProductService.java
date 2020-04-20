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
import org.springframework.samples.petclinic.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petproduct controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Aureliano Piqueras
 */
@Service
public class ProductService {

	private ProductRepository productRepository;


	@Autowired
	public ProductService(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional(readOnly = true)
	public Collection<Product> findProducts() throws DataAccessException {
		return this.productRepository.findAll();
	}

	@Transactional
	public Collection<Product> findProductsByProductTypeId(final int productTypeId) {

		List<Product> res = this.productRepository.findByProductTypeId(productTypeId);
		return res;
	}

	@Transactional
	public Product findProductsById(final int productId) {
		Product res = this.productRepository.findById(productId);
		return res;
	}

	@Transactional
	public List<Product> findProductByClinicId(final int clinicId) {
		List<Product> res = this.productRepository.findByClinicId(clinicId);
		return res;
	}
	
	@Transactional
	public void save(Product product) {
		productRepository.save(product);
	}
	
	public void delete(Product product) {
		productRepository.delete(product);
	}


}
