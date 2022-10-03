package com.crm.customer.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
		return occupationDetailsRepository.findByOccupationDetailsIdAndIsDeleted(id, false);
	}

	public OccupationDetails update(OccupationDetails occupationDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		OccupationDetails existingOccupation = occupationDetailsRepository
				.findById(occupationDetails.getOccupationDetailsId()).get();
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
		try {
			existingOccupation = occupationDetailsRepository.findById(id).get();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Occupation Details not found.");
		}
		if (existingOccupation.getIsDeleted()) {
			throw new PersistenceException("Occupation Details already Deleted");
		}
		existingOccupation.setIsDeleted(true);
		existingOccupation.setUpdatedBy(updatedBy);
		existingOccupation.setUpdatedDate(dateTime);
		return occupationDetailsRepository.save(existingOccupation);
	}

}
