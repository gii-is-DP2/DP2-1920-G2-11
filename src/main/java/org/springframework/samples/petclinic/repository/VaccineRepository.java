
package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends CrudRepository<Vaccine, Integer> {

	@Query("select v from Vaccine v where v.sickness.id = ?1")
	List<Vaccine> findVaccinesBySicknessId(Integer sickness_id);

	Vaccine findById(int id) throws DataAccessException;
}
