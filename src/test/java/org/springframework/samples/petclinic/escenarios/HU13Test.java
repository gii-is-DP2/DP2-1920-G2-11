package org.springframework.samples.petclinic.escenarios;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.model.VaccineValidator;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU13Test {

	//TODO MODIFICAR VACUNA
	@Autowired
	private VaccineService vaccineService;
	
	private VaccineValidator createValidator() {
		return new VaccineValidator();
	}



	//Caso positivo
	@Test
	void EditVaccineCorrect() {
		
	//vacuna: (2, 'Vacuna B', 'A,S,D,F', 6, 2);
	//sickness : (2, 'Conjuntivitis', 'Infecciones oculares, alergias, problemas genéticos', 
		//'Inflamación de la mucosa del ojo, pérdida de visión, lagrimeo', 2, 1);
		
   //petType:  (1, 'cat');
		
		Vaccine vaccine = this.vaccineService.findVaccineById(2);
	    vaccine.setName("lalala");
	    vaccine.setComponents("todos");
	    vaccine.setMonths(12);
//	    Sickness s = new Sickness();
//	    s.setId(2);
//	    s.setName("Conjuntivitis");
//	    s.setCause("Infecciones oculares, alergias, problemas genéticos");
//	    s.setSeverity(2);
//	    s.setSymptom("Inflamación de la mucosa del ojo, pérdida de visión, lagrimeo");
//	    PetType type = new PetType();
//	    type.setId(1);
//	    type.setName("cat");
//	    s.setType(type);
//	    vaccine.setSickness(s);
	    
		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);
		Assertions.assertThat(errors.getAllErrors()).isEmpty();
		
		this.vaccineService.saveVaccine(vaccine);
		
		
	}

//caso negativos
	
	
	@Test
	void editVaccineNameNotCorrectly() {
		Vaccine vaccine = this.vaccineService.findVaccineById(3);
	    vaccine.setName("");
	    vaccine.setComponents("todos");
	    vaccine.setMonths(12);
	    
		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("name")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must not be blank");
	}
	
	
	
	@Test
	void editVaccineMonthEmpty() {
		Vaccine vaccine = this.vaccineService.findVaccineById(4);
	    vaccine.setName("lalala");
	    vaccine.setComponents("todos");
	    vaccine.setMonths(null);
		
		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("months")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must not be null");
	}
	
	
	@Test
	void editVaccineComponentEmpty() {
		Vaccine vaccine = this.vaccineService.findVaccineById(9);
	    vaccine.setName("lalala");
	    vaccine.setComponents("");
	    vaccine.setMonths(12);

		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("components")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must not be blank");
	}
	
	
	@Test
	void editVaccineComponentBigEnough() {
		Vaccine vaccine = this.vaccineService.findVaccineById(5);
	    vaccine.setName("lalala");
	    vaccine.setComponents("fioafjodfoisjdfpsjfvoasijvosajvpsjvdpoijvvdaipsjvpasjvpaijc siofmsdio mfdsiofjdsofjm dsoifjosdfmosdifjomsdjdsoi mfosdifjmiodsfoidsmfiodsmfoidsj");
	    vaccine.setMonths(12);
		

		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("components")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must lower than 70");
	}
	
	
	@Test
	void editVaccineNameBigEnough() {
		Vaccine vaccine = this.vaccineService.findVaccineById(6);
	    vaccine.setName("lalalafioafjodfoisjdfpsjfvoasijvosajvpsjvdpoijvvdaipsjvpasjvpaijc siofmsdio mfdsiofjdsofjm dsoifjosdfmosdifjomsdjdsoi mfosdifjmiodsfoidsmfiodsmfoidsj");
	    vaccine.setComponents("todos");
	    vaccine.setMonths(12);
		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("name")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must lower than 70");
	}
	
	
	
	@Test
	void editVaccineNegativeMonth() {
		Vaccine vaccine = this.vaccineService.findVaccineById(7);
	    vaccine.setName("lalala");
	    vaccine.setComponents("todos");
	    vaccine.setMonths(-12);

		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("months")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must be positive number");
	}
	
	@Test
	void editVaccineMonthBigEnough() {
		Vaccine vaccine = this.vaccineService.findVaccineById(2);
	    vaccine.setName("lalala");
	    vaccine.setComponents("H2O");
	    vaccine.setMonths(120);

		VaccineValidator validator = this.createValidator();
		Errors errors = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine, errors);

		Assertions.assertThat(errors.getErrorCount()).isEqualTo(1);
		Assertions.assertThat(errors.hasFieldErrors("months")).isTrue();
		Assertions.assertThat(errors.getAllErrors().get(0).getCode()).isEqualTo("must lower than 60");
	}
	
}

