
package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "sicknesses")
public class Sickness extends NamedEntity {

	@NotBlank
	@Column(name = "name")
	@Length(max = 70)
	private String	name;

	@Column(name = "cause")
	@Length(max = 150)
	private String	cause;

	@Column(name = "symptom")
	@Length(max = 150)
	private String	symptom;

	@Range(min = 0, max = 3)
	@NotNull
	private Integer	severity;

	@ManyToOne
	@Valid
	@JoinColumn(name = "type_id")
	private PetType	type;


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

	public PetType getType() {
		return this.type;
	}

	public void setType(final PetType type) {
		this.type = type;
	}

	public String getCause() {
		return this.cause;
	}

	public void setCause(final String cause) {
		this.cause = cause;
	}

}
