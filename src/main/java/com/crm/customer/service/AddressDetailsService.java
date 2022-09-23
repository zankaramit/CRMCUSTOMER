package com.crm.customer.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.AddressDetails;
import com.crm.customer.repository.AddressDetailsRepository;

@Service
public class AddressDetailsService {

	@Autowired
	AddressDetailsRepository addressDetailsRepository;

	public Page<AddressDetails> getSearchAndPagination(String name, Long customerId, Pageable pageable) {
		Page<AddressDetails> addressList = null;
		if (!ObjectUtils.isEmpty(name)) {
			addressList = addressDetailsRepository
					.findByIsDeletedAndCustomerCustomerIdAndAddress1LikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndAddress2LikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndAddressTypeLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndContactAddressLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndCountryLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndStateLikeIgnoreCase(
							false, customerId, "%" + name + "%", false, customerId, "%" + name + "%", false, customerId,
							"%" + name + "%", false, customerId, "%" + name + "%", false, customerId, "%" + name + "%",
							false, customerId, "%" + name + "%", pageable);
		} else {

			addressList = addressDetailsRepository.findByIsDeletedAndCustomerCustomerId(false, customerId, pageable);

		}
		return addressList;
	}

	public AddressDetails save(AddressDetails addressDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		addressDetails.setIsDeleted(false);
		addressDetails.setCreatedDate(dateTime);
		return addressDetailsRepository.save(addressDetails);
	}

	public Optional<AddressDetails> getById(Long id) {

		return addressDetailsRepository.findByAddressDetailsIdAndIsDeleted(id, false);
	}

	public AddressDetails update(AddressDetails addressDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		AddressDetails existingAddress = addressDetailsRepository.findById(addressDetails.getAddressDetailsId()).get();
		existingAddress.setAddress1(addressDetails.getAddress1());
		existingAddress.setAddress2(addressDetails.getAddress2());
		existingAddress.setAddressType(addressDetails.getAddressType());
		existingAddress.setIdentificationType(addressDetails.getIdentificationType());
		existingAddress.setContactAddress(addressDetails.getContactAddress());
		existingAddress.setCountry(addressDetails.getCountry());
		existingAddress.setOwnershipStatus(addressDetails.getOwnershipStatus());
		existingAddress.setPostCode(addressDetails.getPostCode());
		existingAddress.setState(addressDetails.getState());
		existingAddress.setAddressTenureMonth(addressDetails.getAddressTenureMonth());
		existingAddress.setAddressTenureYears(addressDetails.getAddressTenureYears());
		existingAddress.setUpdatedBy(addressDetails.getUpdatedBy());
		existingAddress.setUpdatedDate(dateTime);

		return addressDetailsRepository.save(existingAddress);

	}

	public AddressDetails softDelete(Long id, String updatedBy) {

		LocalDateTime dateTime = LocalDateTime.now();
		AddressDetails existingAddress;
		try {
			existingAddress = addressDetailsRepository.findById(id).get();
		} catch (Exception e) {
			throw new ResourceNotFoundException("Address Details not found.");
		}
		if (existingAddress.getIsDeleted()) {
			throw new PersistenceException("Address Details already Deleted");
		}
		existingAddress.setIsDeleted(true);
		existingAddress.setUpdatedBy(updatedBy);
		existingAddress.setUpdatedDate(dateTime);
		return addressDetailsRepository.save(existingAddress);

	}

}
