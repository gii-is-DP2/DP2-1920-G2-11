
package org.springframework.samples.petclinic.escenarios;

import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.SicknessValidator;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.SicknessService;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class HU7Test {

	@Autowired
	private SicknessService	sicknessService;

	@Autowired
	private PetService		petService;


	private SicknessValidator createValidator() {
		return new SicknessValidator();
	}

	//Caso positivo
	@Test
	@Transactional
	void updateSicknessCorrectly() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = this.sicknessService.findSicknessesById(1);
		Pet pet = this.petService.findPetById(3);
		sickness.setName("Enfermedad 1");
		sickness.setCause("Causa 1");
		sickness.setSymptom("Sintoma 1");
		sickness.setSeverity(2);
		sickness.setType(pet.getType());

		SicknessValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(sickness, "sickness");
		validator.validate(sickness, errors);
		Assertions.assertThat(errors.getAllErrors()).isEmpty();

		this.sicknessService.saveSickness(sickness);

	}

	//Casos negativos
	@Test
	@Transactional
	void updateSicknessNameNotCorrectly() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = this.sicknessService.findSicknessesById(1);
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
	@Transactional
	void updateSicknessSeverityNotCorrectly() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = this.sicknessService.findSicknessesById(1);
		sickness.setName("Sickness 1");
		sickness.setCause("Cause 1");
		sickness.setSymptom("Symptom 1");
		sickness.setSeverity(null);

		SicknessValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(sickness, "sickness");
		validator.validate(sickness, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("severity")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must not be null");
	}

	@Test
	@Transactional
	void updateSicknessSeverityOutOfMinRange() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = this.sicknessService.findSicknessesById(1);
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
	@Transactional
	void updateSicknessSeverityOutOfMaxRange() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = this.sicknessService.findSicknessesById(1);
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
	@Transactional
	void updateSicknessNameOutOfLength() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = this.sicknessService.findSicknessesById(1);
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
	@Transactional
	void updateSicknessCauseOutOfLength() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = this.sicknessService.findSicknessesById(1);
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
	@Transactional
	void updateSicknessSymptomOutOfLength() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sickness sickness = this.sicknessService.findSicknessesById(1);
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
