
package org.springframework.samples.petclinic.escenarios;

import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.SicknessValidator;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.SicknessRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU5Test {

	@Autowired
	private SicknessRepository	sicknessRepository;

	@Autowired
	private PetRepository		petRepository;


	private SicknessValidator createValidator() {
		return new SicknessValidator();
	}

	//Caso positivo
	@Test
	void createSicknessCorrectly() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = new Sickness();
		Pet pet = this.petRepository.findById(3);
		sickness.setName("Enfermedad 1");
		sickness.setCause("Causa 1");
		sickness.setSymptom("Sintoma 1");
		sickness.setSeverity(2);
		sickness.setType(pet.getType());

		SicknessValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(sickness, "sickness");
		validator.validate(sickness, errors);
		Assertions.assertThat(errors.getAllErrors()).isEmpty();

		this.sicknessRepository.save(sickness);

	}

	//Casos negativo
	@Test
	void createSicknessNameNotCorrectly() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = new Sickness();
		sickness.setName("");
		sickness.setCause("Cause 1");
		sickness.setSymptom("Symptom 1");
		sickness.setSeverity(2);

		SicknessValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(sickness, "sickness");
		validator.validate(sickness, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("name")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must not be blank");
	}

	@Test
	void createSicknessSeverityNotCorrectly() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = new Sickness();
		sickness.setName("Sickness 1");
		sickness.setCause("Cause 1");
		sickness.setSymptom("Symptom 1");

		SicknessValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(sickness, "sickness");
		validator.validate(sickness, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("severity")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must not be null");
	}

	@Test
	void createSicknessSeverityOutOfMinRange() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = new Sickness();
		sickness.setName("Sickness 1");
		sickness.setCause("Cause 1");
		sickness.setSymptom("Symptom 1");
		sickness.setSeverity(-1);

		SicknessValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(sickness, "sickness");
		validator.validate(sickness, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("severity")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must be at least 0");
	}

	@Test
	void createSicknessSeverityOutOfMaxRange() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = new Sickness();
		sickness.setName("Sickness 1");
		sickness.setCause("Cause 1");
		sickness.setSymptom("Symptom 1");
		sickness.setSeverity(4);

		SicknessValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(sickness, "sickness");
		validator.validate(sickness, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("severity")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must be a maximum of 3");
	}

	@Test
	void createSicknessNameOutOfLength() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = new Sickness();
		sickness.setName("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		sickness.setCause("Cause 1");
		sickness.setSymptom("Symptom 1");
		sickness.setSeverity(1);

		SicknessValidator sv = new SicknessValidator();
		Errors errors = new BeanPropertyBindingResult(sickness, "sickness");
		sv.validate(sickness, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("name")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must lower than 70");
	}

	@Test
	void createSicknessCauseOutOfLength() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = new Sickness();
		sickness.setName("Name 1");
		sickness.setCause("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		sickness.setSymptom("Symptom 1");
		sickness.setSeverity(1);

		SicknessValidator sv = new SicknessValidator();
		Errors errors = new BeanPropertyBindingResult(sickness, "sickness");
		sv.validate(sickness, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("cause")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must lower than 150");
	}

	@Test
	void createSicknessSymptomOutOfLength() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = new Sickness();
		sickness.setName("Name 1");
		sickness.setCause("Cause 1");
		sickness.setSymptom("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		sickness.setSeverity(1);

		SicknessValidator sv = new SicknessValidator();
		Errors errors = new BeanPropertyBindingResult(sickness, "sickness");
		sv.validate(sickness, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("symptom")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must lower than 150");
	}
}
