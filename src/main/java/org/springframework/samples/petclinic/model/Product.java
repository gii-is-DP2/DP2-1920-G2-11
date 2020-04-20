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

package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * Simple JavaBean domain object representing an person.
 *
 * @author Lizseth y Jaime
 */

@Entity
@Table(name = "products")
public class Product extends NamedEntity {

	@Column(name = "price")
	@Digits(fraction = 2, integer = 10)
	@Min(value = 1)
	private Double	price;

	@Column(name = "stock")
	@Min(value = 0)
	private Integer	stock;

	@Column(name = "description")
	@NotEmpty
	private String	description;


	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}


	@ManyToOne
	@JoinColumn(name = "productType_id")
	private ProductType	productType;

	@ManyToOne
	@JoinColumn(name = "clinic_id")
	private Clinic		clinic;


	public void setPrice(final Double price) {
		this.price = price;
	}

	public void setStock(final Integer stock) {
		this.stock = stock;
	}

	public void setProductType(final ProductType productType) {
		this.productType = productType;
	}

	public Double getPrice() {
		return this.price;
	}

	public Integer getStock() {
		return this.stock;
	}

	public ProductType getProductType() {
		return this.productType;
	}
	
	public Clinic getClinic() {
		return this.clinic;
	}

}
