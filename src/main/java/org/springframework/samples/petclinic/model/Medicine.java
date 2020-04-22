
package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "medicines")
public class Medicine extends NamedEntity {

	@NotBlank
	@Column(name = "name")
	private String		name;

	@NotBlank
	@Column(name = "components")
	private String		components;

	@NotBlank
	@Column(name = "treatment")
	private String		treatment;

	@OneToOne
	@Valid
	@JoinColumn(name = "type_id")
	private PetType		petType;

	@ManyToOne
	@Valid
	@JoinColumn(name = "sickness_id")
	private Sickness	sickness;


	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	public String getComponents() {
		return this.components;
	}

	public void setComponents(final String components) {
		this.components = components;
	}

	public String getTreatment() {
		return this.treatment;
	}

	public void setTreatment(final String treatment) {
		this.treatment = treatment;
	}

	public PetType getPetType() {
		return this.petType;
	}

	public void setPetType(final PetType petType) {
		this.petType = petType;
	}

	public Sickness getSickness() {
		return this.sickness;
	}

	public void setSickness(final Sickness sickness) {
		this.sickness = sickness;
	}
}
