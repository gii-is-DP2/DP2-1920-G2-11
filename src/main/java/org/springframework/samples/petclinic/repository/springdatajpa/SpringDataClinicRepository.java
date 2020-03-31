/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Clinic;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.ProductType;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.ClinicRepository;
import org.springframework.samples.petclinic.repository.PetRepository;

/**
 * Spring Data JPA specialization of the {@link PetRepository} interface
 *
 * @author Aureliano Piqueras
 */
public interface SpringDataClinicRepository extends ClinicRepository, Repository<Clinic, Integer> {
	
	@Override
	@Query("SELECT c.products FROM Clinic c WHERE c.id =:id")
	public Collection<Product> findClinicProducts(@Param("id") int id);
	
	@Override
	@Query("SELECT DISTINCT C FROM Clinic C INNER JOIN Product P WHERE P.clinic.id = C.id AND P.productType.id =:id")
	public Collection<Clinic> findClinicsByProductType(@Param("id") int id);
	
	@Override
	@Query("SELECT c.vets FROM Clinic c WHERE c.id =:id")
	public Collection<Vet> findClinicVets(@Param("id") int id);

}
