package com.crm.customer.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime; 

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.ReferenceDetails;
import com.crm.customer.repository.ReferenceDetailsRepository;

@Service
public class ReferenceDetailsService {

	@Autowired
	ReferenceDetailsRepository referenceDetailsRepository;

	public Page<ReferenceDetails> getSearchAndPagination(String name, Long customerId, Pageable pageable) {
		Page<ReferenceDetails> referenceList = null;

		if (!ObjectUtils.isEmpty(name)) {
			referenceList = referenceDetailsRepository
					.findByIsDeletedAndCustomerCustomerIdAndFirstNameLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndMiddelNameLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndLastNameLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndAddressTypeLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndAddress1LikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndAddress2LikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndCityLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndProvinceStateLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndCountryLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndReferencePhoneNumberLikeIgnoreCase(
							false,customerId, "%" + name + "%", false,customerId, "%" + name + "%", false,customerId, "%" + name + "%", false,customerId,
							"%" + name + "%", false,customerId, "%" + name + "%", false,customerId, "%" + name + "%", false,customerId, "%" + name + "%",
							false,customerId, "%" + name + "%", false,customerId, "%" + name + "%", false, customerId,"%" + name + "%", pageable);

		} else {
			referenceList = referenceDetailsRepository.findByIsDeletedAndCustomerCustomerId(false, customerId,
					pageable);

		}

		return referenceList;
	}

	public Optional<ReferenceDetails> getById(Long id) {

		return referenceDetailsRepository.findByReferenceDetailsIdAndIsDeleted(id, false);
	}

	public ReferenceDetails save(ReferenceDetails referenceDetails) {
		LocalDateTime dateTime = LocalDateTime.now();  
		referenceDetails.setIsDeleted(false);
		referenceDetails.setCreatedDate(dateTime);
		return referenceDetailsRepository.save(referenceDetails);
	}

	public ReferenceDetails update(ReferenceDetails referenceDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		ReferenceDetails existingReference = referenceDetailsRepository
				.findById(referenceDetails.getReferenceDetailsId()).get();

		existingReference.setFirstName(referenceDetails.getFirstName());
		existingReference.setMiddelName(referenceDetails.getMiddelName());
		existingReference.setLastName(referenceDetails.getLastName());
		existingReference.setAddressType(referenceDetails.getAddressType());
		existingReference.setAddress1(referenceDetails.getAddress1());
		existingReference.setAddress2(referenceDetails.getAddress2());
		existingReference.setCity(referenceDetails.getCity());
		existingReference.setProvinceState(referenceDetails.getProvinceState());
		existingReference.setCountry(referenceDetails.getCountry());
		existingReference.setPostCode(referenceDetails.getPostCode());
		existingReference.setReferencePhoneNumber(referenceDetails.getReferencePhoneNumber());
		existingReference.setRelationship(referenceDetails.getRelationship());
		existingReference.setUpdatedBy(referenceDetails.getUpdatedBy());
		existingReference.setUpdatedDate(dateTime);

		return referenceDetailsRepository.save(existingReference);
	}

	public ReferenceDetails softDelete(Long id, String updatedBy) {
		LocalDateTime dateTime = LocalDateTime.now();
		ReferenceDetails existingReference;
		try {
		existingReference = referenceDetailsRepository.findById(id).get();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Reference Details not found.");
		}
		if (existingReference.getIsDeleted()) {
			throw new PersistenceException("Reference Details already Deleted");
		}
		existingReference.setIsDeleted(true);
		existingReference.setUpdatedBy(updatedBy);
		existingReference.setUpdatedDate(dateTime);
		return referenceDetailsRepository.save(existingReference);
	}

}
