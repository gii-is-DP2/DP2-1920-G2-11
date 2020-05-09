
package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vaccines")
public class Vaccine extends NamedEntity {

	
	
	
	@NotBlank
	@Column(name = "components")
	private String		components;

	@NotNull
	@Min(0)
	@Column(name = "months")
	private Integer		months;

	@ManyToOne
	@Valid
	@JoinColumn(name = "sickness_id")
	private Sickness	sickness;


	

	public String getComponents() {
		return this.components;
	}

	public void setComponents(final String components) {
		this.components = components;
	}

	public Integer getMonths() {
		return this.months;
	}

	public void setMonths(final Integer months) {
		this.months = months;
	}

	public Sickness getSickness() {
		return this.sickness;
	}

	public void setSickness(final Sickness sickness) {
		this.sickness = sickness;
	}

}
