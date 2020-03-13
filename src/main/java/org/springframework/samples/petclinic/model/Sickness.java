
package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "sicknesses")
public class Sickness extends NamedEntity {

	@NotEmpty
	@Column(name = "name")
	private String	name;

	@NotEmpty
	@Column(name = "symptom")
	private String	symptom;

	@Range(min = 1, max = 3)
	@NotNull
	private Integer	severity;

	@ManyToOne
	@Valid
	@JoinColumn(name = "pet_id")
	private Pet		pet;


	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	public String getSymptom() {
		return this.symptom;
	}

	public void setSymptom(final String symptom) {
		this.symptom = symptom;
	}

	public Integer getSeverity() {
		return this.severity;
	}

	public void setSeverity(final Integer severity) {
		this.severity = severity;
	}

	public Pet getPet() {
		return this.pet;
	}

	public void setPet(final Pet pet) {
		this.pet = pet;
	}
}
