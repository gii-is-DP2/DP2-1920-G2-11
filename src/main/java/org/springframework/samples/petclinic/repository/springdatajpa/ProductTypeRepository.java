package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.List;

import org.springframework.samples.petclinic.model.Product;
import org.springframework.samples.petclinic.model.ProductType;

public interface ProductTypeRepository {
	
	ProductType findProductType(Integer productTypeId);

	
	List<ProductType> findAll();
}
