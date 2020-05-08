
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
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.model.VaccineValidator;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU11Test {

	@Autowired
	private VaccineService vaccineService;

	@Autowired
	private PetService petService;

	private VaccineValidator createValidator() {
		return new VaccineValidator();
	}

	// caso positivo: se crea correctamente una vacuna

	@Test
	void createVaccineCorrectly() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vaccine vaccine = new Vaccine();
		vaccine.setMonths(1);
		vaccine.setComponents("H1, Jk");
		vaccine.setId(100);
		vaccine.setName("Vacuna test");
		Sickness sickness = new Sickness();
		Pet pet = this.petService.findPetById(3);
		sickness.setName("Listeriosis");
		sickness.setCause("Carne podrida");
		sickness.setSymptom("Dolor de estomago");
		sickness.setSeverity(2);
		sickness.setType(pet.getType());

		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);
		Assertions.assertThat(errors.getAllErrors()).isEmpty();

		this.vaccineService.saveVaccine(vaccine);
	}

	// Casos negativo

	@Test
	void createVaccineNameNotCorrectly() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vaccine vaccine = new Vaccine();
		Sickness sickness = new Sickness();
		Pet pet = this.petService.findPetById(3);
		sickness.setName("Listeriosis");
		sickness.setCause("Carne podrida");
		sickness.setSymptom("Dolor de estomago");
		sickness.setSeverity(2);
		sickness.setType(pet.getType());
		vaccine.setName("");
		vaccine.setMonths(10);
		vaccine.setSickness(sickness);
		vaccine.setComponents("h1");

		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("name")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must not be blank");
	}
	
	
	
	@Test
	void createVaccineMonthEmpty() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vaccine vaccine = new Vaccine();
		Sickness sickness = new Sickness();
		Pet pet = this.petService.findPetById(3);
		sickness.setName("Listeriosis");
		sickness.setCause("Carne podrida");
		sickness.setSymptom("Dolor de estomago");
		sickness.setSeverity(2);
		sickness.setType(pet.getType());
		vaccine.setName("Vacune test");
		vaccine.setSickness(sickness);
		vaccine.setComponents("h1");
		
		
		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("months")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must not be null");
	}
	
	
	@Test
	void createVaccineComponentEmpty() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vaccine vaccine = new Vaccine();
		Sickness sickness = new Sickness();
		Pet pet = this.petService.findPetById(3);
		sickness.setName("Listeriosis");
		sickness.setCause("Carne podrida");
		sickness.setSymptom("Dolor de estomago");
		sickness.setSeverity(2);
		sickness.setType(pet.getType());
		vaccine.setName("Vacuna 1");
		vaccine.setMonths(10);
		vaccine.setSickness(sickness);
		vaccine.setComponents("");

		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("components")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must not be blank");
	}
	
	
	@Test
	void createVaccineComponentBigEnough() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vaccine vaccine = new Vaccine();
		Sickness sickness = new Sickness();
		Pet pet = this.petService.findPetById(3);
		sickness.setName("Listeriosis");
		sickness.setCause("Carne podrida");
		sickness.setSymptom("Dolor de estomago");
		sickness.setSeverity(2);
		sickness.setType(pet.getType());
		vaccine.setName("Vacuna 1");
		vaccine.setMonths(10);
		vaccine.setSickness(sickness);
		vaccine.setComponents("fioafjodfoisjdfpsjfvoasijvosajvpsjvdpoijvvdaipsjvpasjvpaijc siofmsdio mfdsiofjdsofjm dsoifjosdfmosdifjomsdjdsoi mfosdifjmiodsfoidsmfiodsmfoidsj");

		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("components")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must lower than 70");
	}
	
	
	@Test
	void createVaccineNameBigEnough() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vaccine vaccine = new Vaccine();
		Sickness sickness = new Sickness();
		Pet pet = this.petService.findPetById(3);
		sickness.setName("Listeriosis");
		sickness.setCause("Carne podrida");
		sickness.setSymptom("Dolor de estomago");
		sickness.setSeverity(2);
		sickness.setType(pet.getType());
		vaccine.setName("Vacuna 1 adskjesjkdfndofnkdjcn 190380843040328943 jfghjdshjkdshfjkdsfhd r989efjwosfijapdohcnrcecuoundisuh");
		vaccine.setMonths(10);
		vaccine.setSickness(sickness);
		vaccine.setComponents("H1");

		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("name")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must lower than 70");
	}
	
	
	
	@Test
	void createVaccineNegativeMonth() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vaccine vaccine = new Vaccine();
		Sickness sickness = new Sickness();
		Pet pet = this.petService.findPetById(3);
		sickness.setName("Listeriosis");
		sickness.setCause("Carne podrida");
		sickness.setSymptom("Dolor de estomago");
		sickness.setSeverity(2);
		sickness.setType(pet.getType());
		vaccine.setName("Vacuna 1");
		vaccine.setMonths(-10);
		vaccine.setSickness(sickness);
		vaccine.setComponents("H1");

		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("months")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must be positive number");
	}
	
	@Test
	void createVaccineMonthBigEnough() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vaccine vaccine = new Vaccine();
		Sickness sickness = new Sickness();
		Pet pet = this.petService.findPetById(3);
		sickness.setName("Listeriosis");
		sickness.setCause("Carne podrida");
		sickness.setSymptom("Dolor de estomago");
		sickness.setSeverity(2);
		sickness.setType(pet.getType());
		vaccine.setName("Vacuna 1");
		vaccine.setMonths(80);
		vaccine.setSickness(sickness);
		vaccine.setComponents("H1");

		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("months")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must lower than 60");
	}
	

}