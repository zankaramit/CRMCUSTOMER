package com.crm.customer.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.crm.customer.model.AddressDetails;
import com.crm.customer.repository.AddressDetailsRepository;

@Service
public class AddressDetailsService {

	@Autowired
	AddressDetailsRepository addressDetailsRepository;
	
	public Page<AddressDetails> getSearchAndPagination(String name, Long customerId, Pageable pageable) {
		Page<AddressDetails> addressList = null;
		if (customerId != null) {
			addressList = addressDetailsRepository.findByIsDeletedAndCustomerCustomerId(false, customerId, pageable);

		} else if (ObjectUtils.isEmpty(name)) {
			addressList = addressDetailsRepository.findByIsDeleted(false, pageable);

		} else {
			addressList = addressDetailsRepository
					.findByIsDeletedAndAddress1LikeIgnoreCaseOrIsDeletedAndAddress2LikeIgnoreCaseOrIsDeletedAndAddressTypeLikeIgnoreCaseOrIsDeletedAndContactAddressLikeIgnoreCaseOrIsDeletedAndCountryLikeIgnoreCaseOrIsDeletedAndStateLikeIgnoreCase(
							false, "%" + name + "%", false, "%" + name + "%", false, "%" + name + "%", false,
							"%" + name + "%", false, "%" + name + "%", false, "%" + name + "%", pageable);

		}
		return addressList;
	}

	public AddressDetails save(AddressDetails addressDetails) {
		Date date = new Date();
		addressDetails.setIsDeleted(false);
		addressDetails.setCreatedDate(date);
		return addressDetailsRepository.save(addressDetails);
	}

	public Optional<AddressDetails> getById(Long id) {

		return addressDetailsRepository.findByAddressDetailsIdAndIsDeleted(id, false);
	}

	public AddressDetails update(AddressDetails addressDetails) {
		Date date = new Date();
		AddressDetails existingAddress = addressDetailsRepository.findById(addressDetails.getAddressDetailsId()).get();
		existingAddress.setAddress1(addressDetails.getAddress1());
		existingAddress.setAddress2(addressDetails.getAddress2());
		existingAddress.setAddressType(addressDetails.getAddressType());
		existingAddress.setContactAddress(addressDetails.getContactAddress());
		existingAddress.setCountry(addressDetails.getCountry());
		existingAddress.setOwnershipStatus(addressDetails.getOwnershipStatus());
		existingAddress.setPostCode(addressDetails.getPostCode());
		existingAddress.setState(addressDetails.getState());
		existingAddress.setAddressTenureMonth(addressDetails.getAddressTenureMonth());
		existingAddress.setAddressTenureYears(addressDetails.getAddressTenureYears());
		existingAddress.setUpdatedBy(addressDetails.getUpdatedBy());
		existingAddress.setUpdatedDate(date);

		return addressDetailsRepository.save(existingAddress);

	}

	public AddressDetails softDelete(Long id, String updatedBy) {
		Date date = new Date();
		AddressDetails existingAddress = addressDetailsRepository.findById(id).get();
		existingAddress.setIsDeleted(true);
		existingAddress.setUpdatedBy(updatedBy);
		existingAddress.setUpdatedDate(date);
		return addressDetailsRepository.save(existingAddress);
	}

	

}
