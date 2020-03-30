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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlElement;
import javax.persistence.OneToMany;


import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

/**
 * Simple JavaBean domain object representing a clinic.
 *
 * @author Aureliano Piqueras
 */
@Entity
@Table(name = "clinics")
public class Clinic extends NamedEntity {

	@Column(name = "address")
	@NotEmpty
	private String address;

	@Column(name = "telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;

	@Column(name = "email")
	@Email
	private String email;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Product> products;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Vet> vets;


	public String getAddress() {
		return this.address;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	protected Set<Product> getProductsInternal() {
		if (this.products == null) {
			this.products = new HashSet<>();
		}
		return this.products;
	}

	protected void setProductsInternal(final Set<Product> products) {
		this.products = products;
	}

	@XmlElement
	public List<Product> getProducts() {
		final List<Product> sortedProds = new ArrayList<>(getProductsInternal());
		PropertyComparator.sort(sortedProds, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedProds);
	}

	public int getNrOfProducts() {
		return getProducts().size();
	}

	public void addProduct(final Product product) {
		getProductsInternal().add(product);
	}

	protected Set<Vet> getVetsInternal() {
		if (this.vets == null) {
			this.vets = new HashSet<>();
		}
		return this.vets;
	}

	protected void setVetsInternal(final Set<Vet> vets) {
		this.vets = vets;
	}

	@XmlElement
	public List<Vet> getVets() {
		final List<Vet> sortedVets = new ArrayList<>(getVetsInternal());
		PropertyComparator.sort(sortedVets, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedVets);
	}

	public int getNrOfVets() {
		return getVets().size();
	}

	public void addVet(final Vet vet) {
		getVetsInternal().add(vet);
	}

}
