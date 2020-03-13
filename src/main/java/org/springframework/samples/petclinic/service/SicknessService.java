
package org.springframework.samples.petclinic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Sickness;
import org.springframework.samples.petclinic.repository.SicknessRepository;
import org.springframework.stereotype.Service;

@Service
public class SicknessService {

	@Autowired
	private SicknessRepository sicknessRepository;


	@Transactional
	public Iterable<Sickness> findAll() {

		return this.sicknessRepository.findAll();
	}
}
