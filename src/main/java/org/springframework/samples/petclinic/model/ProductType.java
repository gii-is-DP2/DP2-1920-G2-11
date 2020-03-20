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
package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

/**
 * Simple JavaBean domain object representing an person.
 *
 * @author Aureliano Piqueras
 */
@Entity
@Table(name = "productTypes")
public class ProductType extends NamedEntity {
	
	@Column(name = "description")
	@NotEmpty
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productType")
	private Set<Product> products;
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getDescription() {
		return this.description;
	}
	

	protected Set<Product> getProductsInternal() {
		if (this.products == null) {
			this.products = new HashSet<>();
		}
		return this.products;
	}

	protected void setProductsInternal(Set<Product> products) {
		this.products = products;
	}

	public List<Product> getProducts() {
		List<Product> sortedProducts = new ArrayList<>(getProductsInternal());
		PropertyComparator.sort(sortedProducts, new MutableSortDefinition("price", false, false));
		return Collections.unmodifiableList(sortedProducts);
	}

	public void addProduct(Product product) {
		getProductsInternal().add(product);
		product.setProductType(this);
	}
}
