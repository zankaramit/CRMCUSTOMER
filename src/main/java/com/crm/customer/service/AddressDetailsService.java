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
import com.crm.customer.model.AddressDetails;
import com.crm.customer.repository.AddressDetailsRepository;
import com.crm.customer.util.AccessToken;

@Service
public class AddressDetailsService {

	@Autowired
	AddressDetailsRepository addressDetailsRepository;
	
	@Autowired
	UserService userService;

	public Page<AddressDetails> getSearchAndPagination(String name, Long customerId,String owner, Pageable pageable) {
		Page<AddressDetails> addressList = null;
		List<String> checkAccessApi = userService.checkAccessApi(owner);
		if (!ObjectUtils.isEmpty(name)) {
			addressList = addressDetailsRepository
					.findByIsDeletedAndOwnerInAndCustomerCustomerIdAndAddress1LikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndAddress2LikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndAddressTypeLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndCityLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndContactAddressLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndCountryLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndStateLikeIgnoreCase(
							false, checkAccessApi, customerId, "%" + name + "%", false, checkAccessApi, customerId, "%" + name + "%", false, checkAccessApi, customerId,
							"%" + name + "%", false, checkAccessApi, customerId, "%" + name + "%", false, checkAccessApi, customerId, "%" + name + "%",
							false, checkAccessApi, customerId, "%" + name + "%", false, checkAccessApi, customerId, "%" + name + "%", pageable);
		} else {

			addressList = addressDetailsRepository.findByIsDeletedAndOwnerInAndCustomerCustomerId(false,checkAccessApi, customerId, pageable);

		}
		return addressList;
	}

	public AddressDetails save(AddressDetails addressDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		addressDetails.setIsDeleted(false);
		addressDetails.setCreatedDate(dateTime);
		addressDetails.setOwner(addressDetails.getCreatedBy());
		return addressDetailsRepository.save(addressDetails);
	}

	public Optional<AddressDetails> getById(Long id) {

		return addressDetailsRepository.findByCustomerCustomerIdAndIsDeleted(id, false);
	}

	public AddressDetails update(AddressDetails addressDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<AddressDetails> findById = addressDetailsRepository.findById(addressDetails.getAddressDetailsId());
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException(
					"Address Details not found for ID :" + addressDetails.getAddressDetailsId());
		}
		AddressDetails existingAddress = findById.get();
		existingAddress.setAddress1(addressDetails.getAddress1());
		existingAddress.setAddress2(addressDetails.getAddress2());
		existingAddress.setAddressType(addressDetails.getAddressType());
		existingAddress.setIdentificationType(addressDetails.getIdentificationType());
		existingAddress.setCity(addressDetails.getCity());
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
		AddressDetails existingAddress = null;
		Optional<AddressDetails> findById = addressDetailsRepository.findById(id);
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Address Details not found and ID :" + id);
		}
		existingAddress = findById.get();
		Boolean b = existingAddress.getIsDeleted();
		if (Boolean.TRUE.equals(b)) {
			throw new PersistenceException("Address Details Already Deleted and ID :" + id);
		}
		existingAddress.setIsDeleted(true);
		existingAddress.setUpdatedBy(updatedBy);
		existingAddress.setUpdatedDate(dateTime);
		return addressDetailsRepository.save(existingAddress);
	}
}
