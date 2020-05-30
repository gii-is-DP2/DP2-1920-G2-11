
package org.springframework.samples.petclinic.escenarios;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.service.SicknessService;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class HU6Test {

	@Autowired
	private SicknessService sicknessService;


	//Caso positivo
	@Test
	void deleteSicknessCorrectly() {
		Sickness sickness = this.sicknessService.findSicknessesById(13);
		this.sicknessService.deleteVaccineFromSickness(sickness);
		this.sicknessService.deleteSickness(sickness);
		Optional<Sickness> findSickness = this.sicknessService.optionalFindSicknessesById(13);
		List<Sickness> sicknesses = this.sicknessService.findAllSicknesses();
		Assertions.assertTrue(!findSickness.isPresent() && sicknesses.size() == 29);
	}

	//Caso negativo
	@Test
	void deleteSicknessNotCorrectly() {
		Sickness sickness = new Sickness();
		sickness.setId(40);
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			this.sicknessService.deleteSickness(sickness);
		});
	}
}
