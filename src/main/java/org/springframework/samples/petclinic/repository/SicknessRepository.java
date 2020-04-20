
package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.stereotype.Repository;

@Repository
public interface SicknessRepository extends CrudRepository<Sickness, Integer> {

	List<Sickness> findByTypeId(Integer type_id) throws DataAccessException;

	@Override
	List<Sickness> findAll() throws DataAccessException;
}
