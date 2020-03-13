
package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Vaccine;

public interface VaccineRepository extends CrudRepository<Vaccine, Integer> {

}
