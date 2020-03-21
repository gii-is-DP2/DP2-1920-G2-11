
package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.stereotype.Repository;

@Repository
public interface SicknessRepository extends CrudRepository<Sickness, Integer> {

	@Query("select s from Sickness s where s.type.id = ?1")
	List<Sickness> findSicknessesByTypeId(Integer type_id);

	Sickness findById(int id) throws DataAccessException;
}
