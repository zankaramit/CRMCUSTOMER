package com.crm.customer.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.OccupationDetails;
import com.crm.customer.repository.OccupationDetailsRepository;

@Service
public class OccupationDetailsService {

	@Autowired
	OccupationDetailsRepository occupationDetailsRepository;

	public OccupationDetails save(OccupationDetails occupationDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		occupationDetails.setIsDeleted(false);
		occupationDetails.setCreatedDate(dateTime);
		return occupationDetailsRepository.save(occupationDetails);

	}

	public Optional<OccupationDetails> getById(Long id) {
		return occupationDetailsRepository.findByCustomerCustomerIdAndIsDeleted(id, false);
	}

	public OccupationDetails update(OccupationDetails occupationDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<OccupationDetails> findById = occupationDetailsRepository.findById(occupationDetails.getOccupationDetailsId());
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Occupation Details not found for ID :" + occupationDetails.getOccupationDetailsId());
		}
		OccupationDetails existingOccupation = findById.get();
		existingOccupation.setCurrentOccupation(occupationDetails.getCurrentOccupation());
		existingOccupation.setCurrentOccupationMonth(occupationDetails.getCurrentOccupationMonth());
		existingOccupation.setCurrentOccupationYears(occupationDetails.getCurrentOccupationYears());
		existingOccupation.setLastEducationLevel(occupationDetails.getLastEducationLevel());
		existingOccupation.setHobby(occupationDetails.getHobby());
		existingOccupation.setNumPersonsHousehold(occupationDetails.getNumPersonsHousehold());
		existingOccupation.setIncomeGroup(occupationDetails.getIncomeGroup());
		existingOccupation.setUpdatedBy(occupationDetails.getUpdatedBy());
		existingOccupation.setUpdatedDate(dateTime);
		return occupationDetailsRepository.save(existingOccupation);
	}

	public OccupationDetails softDelete(Long id, String updatedBy) {

		LocalDateTime dateTime = LocalDateTime.now();
		OccupationDetails existingOccupation;
		Optional<OccupationDetails> findById = occupationDetailsRepository.findById(id);
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Occupation Details not found and ID :" + id);
		}
		existingOccupation = findById.get();	
		Boolean b = existingOccupation.getIsDeleted();
		if (Boolean.TRUE.equals(b)) {
			throw new PersistenceException("Occupation Details already Deleted and ID :" + id);
		}
		existingOccupation.setIsDeleted(true);
		existingOccupation.setUpdatedBy(updatedBy);
		existingOccupation.setUpdatedDate(dateTime);
		return occupationDetailsRepository.save(existingOccupation);
	}

}
