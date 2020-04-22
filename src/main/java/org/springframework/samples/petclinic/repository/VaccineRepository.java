
package org.springframework.samples.petclinic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends CrudRepository<Vaccine, Integer> {

	List<Vaccine> findBySicknessId(Integer sickness_id) throws DataAccessException;

	//Optional<Vaccine> findById(int id) throws DataAccessException;
	Vaccine findById(int id) throws DataAccessException;
}
