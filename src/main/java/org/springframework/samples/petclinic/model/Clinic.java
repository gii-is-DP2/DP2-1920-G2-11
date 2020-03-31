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
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Simple JavaBean domain object representing a clinic.
 *
 * @author Aureliano Piqueras
 */
@Entity
@Table(name = "clinics")
public class Clinic extends NamedEntity {

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(final String ciudad) {
		this.city = ciudad;
	}


	@Column(name = "address")
	@NotEmpty
	private String	address;

	@Column(name = "telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String	telephone;

	@Column(name = "email")
	@Email
	private String	email;

	@Column(name = "name")
	@NotBlank
	private String	name;


	@OneToMany(cascade = CascadeType.ALL)
	private Set<Product> products;
	@Column(name = "city")
	@NotNull
	private String	city;


	//	@ManyToMany(fetch = FetchType.EAGER)
	//	@JoinTable(name = "clinic_products", joinColumns = @JoinColumn(name = "clinic_id"),
	//			inverseJoinColumns = @JoinColumn(name = "product_id"))
	//	private Set<Product> products;

	//	@OneToMany(cascade = CascadeType.ALL)
	//	private Set<Vet> vets;


	public String getAddress() {
		return this.address;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setTelephone(final String telephone) {
		this.telephone = telephone;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	//	protected Set<Product> getProductsInternal() {
	//		if (this.products == null) {
	//			this.products = new HashSet<>();
	//		}
	//		return this.products;
	//	}
	//
	//	protected void setProductsInternal(final Set<Product> products) {
	//		this.products = products;
	//	}

	//	@XmlElement
	//	public List<Product> getProducts() {
	//		final List<Product> sortedProds = new ArrayList<>(getProductsInternal());
	//		PropertyComparator.sort(sortedProds, new MutableSortDefinition("name", true, true));
	//		return Collections.unmodifiableList(sortedProds);
	//	}

	//	public int getNrOfProducts() {
	//		return getProducts().size();
	//	}

	//	public void addProduct(final Product product) {
	//		getProductsInternal().add(product);
	//	}

	//	protected Set<Vet> getVetsInternal() {
	//		if (this.vets == null) {
	//			this.vets = new HashSet<>();
	//		}
	//		return this.vets;
	//	}
	//
	//	protected void setVetsInternal(final Set<Vet> vets) {
	//		this.vets = vets;
	//	}

	//	@XmlElement
	//	public List<Vet> getVets() {
	//		final List<Vet> sortedVets = new ArrayList<>(getVetsInternal());
	//		PropertyComparator.sort(sortedVets, new MutableSortDefinition("name", true, true));
	//		return Collections.unmodifiableList(sortedVets);
	//	}
	//
	//	public int getNrOfVets() {
	//		return getVets().size();
	//	}
	//
	//	public void addVet(final Vet vet) {
	//		getVetsInternal().add(vet);
	//	}

}
