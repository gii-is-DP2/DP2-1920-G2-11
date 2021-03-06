
package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Medicine;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.MedicineRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.SicknessRepository;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.stereotype.Service;

@Service
public class SicknessService {

	@Autowired
	private SicknessRepository	sicknessRepository;

	@Autowired
	private PetRepository		petRepository;

	@Autowired
	private VaccineRepository	vaccineRepository;

	@Autowired
	private MedicineRepository	medicineRepository;


	@Autowired
	public SicknessService(final SicknessRepository sicknessRepository, final PetRepository petRepository, final VaccineRepository vaccineRepository) {
		this.sicknessRepository = sicknessRepository;
		this.petRepository = petRepository;
		this.vaccineRepository = vaccineRepository;
	}

	@Transactional
	public List<Sickness> findAllSicknesses() {
		List<Sickness> res = this.sicknessRepository.findAll();

		return res;
	}

	@Transactional
	public List<Sickness> findSicknessesByPetId(final int petId) {

		Pet pet = this.petRepository.findById(petId);
		int petTypeId = pet.getType().getId();
		List<Sickness> res = this.sicknessRepository.findByTypeId(petTypeId);

		return res;
	}

	@Transactional
	public Sickness findSicknessesById(final int sicknessId) {

		Optional<Sickness> findSickness = this.sicknessRepository.findById(sicknessId);
		Sickness sickness = findSickness.get();

		return sickness;
	}

	@Transactional
	public Optional<Sickness> optionalFindSicknessesById(final int sicknessId) {

		Optional<Sickness> findSickness = this.sicknessRepository.findById(sicknessId);

		return findSickness;
	}

	@Transactional
	public void saveSickness(final Sickness sickness) throws DataAccessException {
		this.sicknessRepository.save(sickness);
	}

	@Transactional
	public Boolean sameNameAndPetType(final Sickness sickness, final List<Sickness> sicknesses) {
		Boolean res = false;
		for (Sickness s : sicknesses) {
			if (s.getName().equals(sickness.getName()) && s.getType().equals(sickness.getType())) {
				res = true;
			}
		}
		return res;
	}

	@Transactional
	public void deleteSickness(final Sickness sickness) throws DataAccessException {
		this.sicknessRepository.deleteById(sickness.getId());
	}

	@Transactional
	public void deleteVaccineFromSickness(final Sickness sickness) throws DataAccessException {
		Iterable<Vaccine> vaccines = this.vaccineRepository.findAll();
		for (Vaccine v : vaccines) {
			if (v.getSickness().getId().equals(sickness.getId())) {
				this.vaccineRepository.deleteById(v.getId());
			}
		}
	}

	@Transactional
	public void deleteMedicineFromSickness(final Sickness sickness) throws DataAccessException {
		Iterable<Medicine> medicines = this.medicineRepository.findAll();
		for (Medicine m : medicines) {
			if (m.getSickness().getId().equals(sickness.getId())) {
				this.medicineRepository.deleteById(m.getId());
			}
		}
	}

}
