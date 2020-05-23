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
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.model.ClinicValidator;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.model.VaccineValidator;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class HU14 {

	//TODO añadir clinica
	@Autowired
	private ClinicService clinicService;
	
	private ClinicValidator createValidator() {
		return new ClinicValidator();
	}
	
	//caso positivo
	@Test
	@Transactional
	void createClinicCorrectly() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Clinic clinic = new Clinic();
		clinic.setName("Clinica Banani");
		clinic.setCity("Sevilla");
		clinic.setEmail("email@mail.com");
		clinic.setAddress("Calle Locura 5");
		clinic.setTelephone("653214785");
		clinic.setId(90);
		
		ClinicValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(clinic, "clinic");
		validator.validate(clinic, errors);
		Assertions.assertThat(errors.getAllErrors()).isEmpty();
		
		this.clinicService.save(clinic);
	 }		
	//caso negativo
		
		@Test
		@Transactional
		
		void createClinicNameBlank() {
			LocaleContextHolder.setLocale(Locale.ENGLISH);
			Clinic clinic = new Clinic();
			//clinic.setName("");
			clinic.setCity("Sevilla");
			clinic.setEmail("mal@mail.com");
			clinic.setAddress("Calle Lora 5");
			clinic.setTelephone("689214785");
			clinic.setId(80);
			
			ClinicValidator validator = this.createValidator();
			Errors errors = new BeanPropertyBindingResult(clinic, "clinic");
			validator.validate(clinic, errors);

			Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
			Assertions.assertThat(errors.hasFieldErrors("name")).isTrue();
			Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must not be blank");
		}
			
}

