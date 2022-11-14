package com.crm.customer.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.ReferenceDetails;
import com.crm.customer.repository.ReferenceDetailsRepository;
import com.crm.customer.util.AccessToken;

@Service
public class ReferenceDetailsService {

	@Autowired
	ReferenceDetailsRepository referenceDetailsRepository;

	@Autowired
	UserService userService;

	public Page<ReferenceDetails> getSearchAndPagination(String name, String owner, Long customerId,
			Pageable pageable) {
		List<String> checkAccessApi = userService.checkAccessApi(owner);
		Page<ReferenceDetails> referenceList = null;

		if (!ObjectUtils.isEmpty(name)) {
			referenceList = referenceDetailsRepository
					.findByIsDeletedAndOwnerInAndCustomerCustomerIdAndFirstNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndMiddelNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndLastNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndAddressTypeLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndAddress1LikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndAddress2LikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndCityLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndProvinceStateLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndCountryLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndReferencePhoneNumberLikeIgnoreCase(
							false, checkAccessApi, customerId, "%" + name + "%", false, checkAccessApi, customerId,
							"%" + name + "%", false, checkAccessApi, customerId, "%" + name + "%", false,
							checkAccessApi, customerId, "%" + name + "%", false, checkAccessApi, customerId,
							"%" + name + "%", false, checkAccessApi, customerId, "%" + name + "%", false,
							checkAccessApi, customerId, "%" + name + "%", false, checkAccessApi, customerId,
							"%" + name + "%", false, checkAccessApi, customerId, "%" + name + "%", false,
							checkAccessApi, customerId, "%" + name + "%", pageable);

		} else {
			referenceList = referenceDetailsRepository.findByIsDeletedAndOwnerInAndCustomerCustomerId(false,
					checkAccessApi, customerId, pageable);

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
		referenceDetails.setOwner(referenceDetails.getCreatedBy());
		return referenceDetailsRepository.save(referenceDetails);
	}

	public ReferenceDetails update(ReferenceDetails referenceDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<ReferenceDetails> findById = referenceDetailsRepository
				.findById(referenceDetails.getReferenceDetailsId());
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException(
					"Reference Details not found for ID :" + referenceDetails.getReferenceDetailsId());
		}
		ReferenceDetails existingReference = findById.get();
		existingReference.setTitle(referenceDetails.getTitle());
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
		Optional<ReferenceDetails> findById = referenceDetailsRepository.findById(id);
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Reference Details not found and ID :" + id);
		}
		existingReference = findById.get();
		Boolean b = existingReference.getIsDeleted();
		if (Boolean.TRUE.equals(b)) {
			throw new PersistenceException("Reference Details already Deleted and ID :" + id);
		}
		existingReference.setIsDeleted(true);
		existingReference.setUpdatedBy(updatedBy);
		existingReference.setUpdatedDate(dateTime);
		return referenceDetailsRepository.save(existingReference);
	}

}
