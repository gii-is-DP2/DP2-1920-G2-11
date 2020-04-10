
package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Medicine;
import org.springframework.samples.petclinic.model.PetType;

public interface MedicineRepository extends CrudRepository<Medicine, Integer> {
	
	@Override
	Collection<Medicine> findAll() throws DataAccessException;
	
	Medicine findById(int medicineId) throws DataAccessException;
	
	Collection<Medicine> findBySicknessIdAndPetTypeId(int sicknessId, int typeId) throws DataAccessException;
	
}
