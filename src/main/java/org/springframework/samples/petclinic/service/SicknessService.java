
package org.springframework.samples.petclinic.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.SicknessRepository;
import org.springframework.stereotype.Service;

@Service
public class SicknessService {

	@Autowired
	private SicknessRepository	sicknessRepository;

	@Autowired
	private PetRepository		petRepository;


	@Autowired
	public SicknessService(final SicknessRepository sicknessRepository, final PetRepository petRepository) {
		this.sicknessRepository = sicknessRepository;
		this.petRepository = petRepository;
	}

	@Transactional
	public List<Sickness> findSicknessesByPetId(final int petId) {

		Pet pet1 = this.petRepository.findById(petId);
		int type_id = pet1.getType().getId();
		List<Sickness> res = this.sicknessRepository.findByTypeId(type_id);

		return res;
	}

	@Transactional
	public Sickness findSicknessesById(final int sicknessId) {

		Sickness sickness = this.sicknessRepository.findById(sicknessId);

		return sickness;
	}

	@Transactional
	public void saveSickness(final Sickness sickness) throws DataAccessException {
		this.sicknessRepository.save(sickness);
	}
}
