package com.crm.customer.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.crm.customer.model.ReferenceDetails;
import com.crm.customer.repository.ReferenceDetailsRepository;

@Service
public class ReferenceDetailsService {

	@Autowired
	ReferenceDetailsRepository referenceDetailsRepository;

	public Page<ReferenceDetails> getSearchAndPagination(String name, Long customerId, Pageable pageable) {
		Page<ReferenceDetails> referenceList = null;
		if (customerId != null) {

			referenceList = referenceDetailsRepository.findByIsDeletedAndCustomerCustomerId(false, customerId,
					pageable);

		} else if (ObjectUtils.isEmpty(name)) {
			referenceList = referenceDetailsRepository.findByIsDeleted(false, pageable);
		} else {
			referenceList = referenceDetailsRepository
					.findByIsDeletedAndFirstNameLikeIgnoreCaseOrIsDeletedAndMiddelNameLikeIgnoreCaseOrIsDeletedAndLastNameLikeIgnoreCaseOrIsDeletedAndAddressTypeLikeIgnoreCaseOrIsDeletedAndAddress1LikeIgnoreCaseOrIsDeletedAndAddress2LikeIgnoreCaseOrIsDeletedAndCityLikeIgnoreCaseOrIsDeletedAndProvinceStateLikeIgnoreCaseOrIsDeletedAndCountryLikeIgnoreCaseOrIsDeletedAndReferencePhoneNumberLikeIgnoreCase(
							false, "%" + name + "%", false, "%" + name + "%", false, "%" + name + "%", false,
							"%" + name + "%", false, "%" + name + "%", false, "%" + name + "%", false, "%" + name + "%",
							false, "%" + name + "%", false, "%" + name + "%", false, "%" + name + "%", pageable);
		}

		return referenceList;
	}


	public Optional<ReferenceDetails> getById(Long id) {

		return referenceDetailsRepository.findByReferenceDetailsIdAndIsDeleted(id, false);
	}

	public ReferenceDetails save(ReferenceDetails referenceDetails) {
		Date date = new Date();
		referenceDetails.setIsDeleted(false);
		referenceDetails.setCreatedDate(date);
		return referenceDetailsRepository.save(referenceDetails);
	}

	public ReferenceDetails update(ReferenceDetails referenceDetails) {
		Date date = new Date();
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
		existingReference.setUpdatedDate(date);

		return referenceDetailsRepository.save(existingReference);
	}

	public ReferenceDetails softDelete(Long id, String updatedBy) {
		Date date = new Date();
		ReferenceDetails existingReference = referenceDetailsRepository.findById(id).get();
		existingReference.setIsDeleted(true);
		existingReference.setUpdatedBy(updatedBy);
		existingReference.setUpdatedDate(date);
		return referenceDetailsRepository.save(existingReference);
	}

}
