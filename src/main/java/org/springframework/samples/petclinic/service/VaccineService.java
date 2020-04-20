
package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.stereotype.Service;

@Service
public class VaccineService {

	@Autowired
	private VaccineRepository vaccineRepository;


	@Transactional
	public List<Vaccine> findVaccinesBySicknessId(final int sicknessId) {

		List<Vaccine> res = this.vaccineRepository.findBySicknessId(sicknessId);

		return res;
	}

	@Transactional
	public Optional<Vaccine> findVaccineById(final int vaccineId) {

		Optional<Vaccine> vaccine = this.vaccineRepository.findById(vaccineId);

		return vaccine;
	}
	
	@Transactional
	public void saveVaccine(final Vaccine vaccine) throws DataAccessException {
		this.vaccineRepository.save(vaccine);
	}

	@Transactional
	public void deleteVaccine(final Vaccine vaccine) throws DataAccessException {
		this.vaccineRepository.deleteById(vaccine.getId());
	}
	
	public void delete(Vaccine vaccine) {
		vaccineRepository.delete(vaccine);
	}

	public Iterable<Vaccine> findAll() {
		// TODO Auto-generated method stub
		return this.vaccineRepository.findAll();
	}
}
