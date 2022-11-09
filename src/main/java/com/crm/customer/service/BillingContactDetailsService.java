package com.crm.customer.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.BillingContactDetails;
import com.crm.customer.repository.BillingContactDetailsRepository;

@Service
public class BillingContactDetailsService {

	@Autowired
	BillingContactDetailsRepository billingContactDetailsRepository;
	
	@Autowired
	UserService userService;

	public Optional<BillingContactDetails> getById(Long id) {
		return billingContactDetailsRepository.findByContactDetailsIdAndIsDeleted(id, false);
	}

	public BillingContactDetails create(BillingContactDetails billingContactDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		billingContactDetails.setIsDeleted(false);
		billingContactDetails.setCreatedDate(dateTime);
		billingContactDetails.setOwner(billingContactDetails.getCreatedBy());
		return billingContactDetailsRepository.save(billingContactDetails);
	}

	public BillingContactDetails update(BillingContactDetails billingContactDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<BillingContactDetails> findById = billingContactDetailsRepository
				.findById(billingContactDetails.getContactDetailsId());
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException(
					"Billing Contact Details not found for ID :" + billingContactDetails.getContactDetailsId());
		}
		BillingContactDetails existingBillingContactDetails = findById.get();
		existingBillingContactDetails.setAccountName(billingContactDetails.getAccountName());
		existingBillingContactDetails.setServiceType(billingContactDetails.getServiceType());
		existingBillingContactDetails.setAddressType(billingContactDetails.getAddressType());
		existingBillingContactDetails.setAddress(billingContactDetails.getAddress());
		existingBillingContactDetails.setCity(billingContactDetails.getCity());
		existingBillingContactDetails.setZipCode(billingContactDetails.getZipCode());
		existingBillingContactDetails.setProvinceState(billingContactDetails.getProvinceState());
		existingBillingContactDetails.setCountry(billingContactDetails.getCountry());
		existingBillingContactDetails.setComments(billingContactDetails.getComments());
		existingBillingContactDetails.setOwnershipStatus(billingContactDetails.getOwnershipStatus());
		existingBillingContactDetails.setAddressTenureMonths(billingContactDetails.getAddressTenureMonths());
		existingBillingContactDetails.setAddressTenureYears(billingContactDetails.getAddressTenureYears());
		existingBillingContactDetails.setGender(billingContactDetails.getGender());
		existingBillingContactDetails.setMobile(billingContactDetails.getMobile());
		existingBillingContactDetails.setFax(billingContactDetails.getFax());
		existingBillingContactDetails.setEmail(billingContactDetails.getEmail());
		existingBillingContactDetails.setUpdatedBy(billingContactDetails.getUpdatedBy());
		existingBillingContactDetails.setUpdatedDate(dateTime);
		return billingContactDetailsRepository.save(existingBillingContactDetails);
	}

	public BillingContactDetails softDelete(Long id, String updatedBy) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<BillingContactDetails> findById = billingContactDetailsRepository.findById(id);
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Billing Contact Details not found and ID :" + id);
		}
		BillingContactDetails existingBillingContactDetails = findById.get();
		Boolean b = existingBillingContactDetails.getIsDeleted();
		if (Boolean.TRUE.equals(b)) {
			throw new PersistenceException("Billing Contact Details already Deleted and ID :" + id);
		}
		existingBillingContactDetails.setIsDeleted(true);
		existingBillingContactDetails.setUpdatedBy(updatedBy);
		existingBillingContactDetails.setUpdatedDate(dateTime);
		return billingContactDetailsRepository.save(existingBillingContactDetails);
	}

	public Page<BillingContactDetails> getSearchAndPagination(String name, String owner, Pageable pageable) {
		Page<BillingContactDetails>  billingContactDetails= null;
		List<String> checkAccessApi = userService.checkAccessApi(owner);
		
			if (ObjectUtils.isEmpty(name)) {
				billingContactDetails = billingContactDetailsRepository.findByIsDeletedAndOwnerIn(false, checkAccessApi, pageable);
			}else {
				billingContactDetails = billingContactDetailsRepository.findByIsDeletedAndOwnerInAndAccountNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndServiceTypeLikeIgnoreCaseOrIsDeletedAndOwnerInAndMobileLikeIgnoreCaseOrIsDeletedAndOwnerInAndEmailLikeIgnoreCase(false, checkAccessApi, "%" + name + "%", false, checkAccessApi, "%" + name + "%", false, checkAccessApi, "%" + name + "%", false, checkAccessApi, "%" + name + "%", pageable);
			}
	
		
		return billingContactDetails;
	}
}
