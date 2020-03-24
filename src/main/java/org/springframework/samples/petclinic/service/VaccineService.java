
package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.SicknessRepository;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.stereotype.Service;

@Service
public class VaccineService {

	@Autowired
	private VaccineRepository	vaccineRepository;

	@Autowired
	private SicknessRepository	sicknessRepository;


	@Transactional
	public Collection<Vaccine> findVaccinesBySicknessId(final int sicknessId) {

		Sickness sickness = this.sicknessRepository.findById(sicknessId);
		int sickness_id = sickness.getId();
		List<Vaccine> res = this.vaccineRepository.findBySicknessId(sickness_id);

		return res;
	}

	@Transactional
	public Vaccine findVaccineById(final int vaccineId) {

		Vaccine vaccine = this.vaccineRepository.findById(vaccineId);

		return vaccine;
	}
}
