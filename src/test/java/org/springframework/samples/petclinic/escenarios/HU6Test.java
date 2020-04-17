
package org.springframework.samples.petclinic.escenarios;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.SicknessRepository;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class HU6Test {

	@Autowired
	private SicknessRepository	sicknessRepository;

	@Autowired
	private VaccineRepository	vaccineRepository;


	//Caso positivo
	@Test
	void deleteSicknessCorrectly() {
		Optional<Sickness> findSickness = this.sicknessRepository.findById(13);
		Sickness sickness = findSickness.get();
		List<Vaccine> vaccines = this.vaccineRepository.findBySicknessId(sickness.getId());
		this.vaccineRepository.deleteAll(vaccines);
		this.sicknessRepository.delete(sickness);
		Optional<Sickness> findSickness2 = this.sicknessRepository.findById(13);
		List<Sickness> sicknesses = this.sicknessRepository.findAll();
		Assertions.assertThat(!findSickness2.isPresent() && sicknesses.size() == 29);
	}

	//Caso negativo
	//TODO: probar intentando borrar algo que no existe
	@Test
	void deleteSicknessNotCorrectly() {
		Optional<Sickness> findSickness = this.sicknessRepository.findById(40);
		Sickness sickness = findSickness.get();

		List<Vaccine> vaccines = this.vaccineRepository.findBySicknessId(sickness.getId());
		this.vaccineRepository.deleteAll(vaccines);
		this.sicknessRepository.delete(sickness);
		List<Sickness> sicknesses = this.sicknessRepository.findAll();

	}
}
