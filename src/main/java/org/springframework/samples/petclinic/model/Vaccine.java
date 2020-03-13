
package org.springframework.samples.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "vaccines")
public class Vaccine extends NamedEntity {

	@NotBlank
	@Column(name = "name")
	private String		name;

	@NotBlank
	@Column(name = "components")
	private String		components;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "age")
	private Date		age;

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

	public Date getAge() {
		return this.age;
	}

	public void setAge(final Date age) {
		this.age = age;
	}

	public Sickness getSickness() {
		return this.sickness;
	}

	public void setSickness(final Sickness sickness) {
		this.sickness = sickness;
	}

	public PetType getPetType() {
		return this.petType;
	}

	public void setPetType(final PetType petType) {
		this.petType = petType;
	}

}
