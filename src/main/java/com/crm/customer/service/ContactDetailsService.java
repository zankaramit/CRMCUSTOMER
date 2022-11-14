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
import com.crm.customer.model.ContactDetails;
import com.crm.customer.repository.ContactDetailsRepository;
import com.crm.customer.util.AccessToken;

@Service
public class ContactDetailsService {

	@Autowired
	ContactDetailsRepository contactDetailsRepository;
	
	@Autowired
	UserService userService;

	public Page<ContactDetails> getSearchAndPagination(String name,String owner, Long customerId, Pageable pageable) {
		List<String> checkAccessApi = userService.checkAccessApi(owner);
		Page<ContactDetails> contactList = null;

		if (!ObjectUtils.isEmpty(name)) {
			contactList = contactDetailsRepository
					.findByIsDeletedAndOwnerInAndCustomerCustomerIdAndFirstNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndMiddelNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndLastNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndMobileNumberLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndFaxLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndNationalityLikeIgnoreCase(
							false,checkAccessApi, customerId, "%" + name + "%", false,checkAccessApi, customerId, "%" + name + "%", false,checkAccessApi, customerId,
							"%" + name + "%", false,checkAccessApi, customerId, "%" + name + "%", false,checkAccessApi, customerId, "%" + name + "%",
							false,checkAccessApi, customerId, "%" + name + "%", pageable);

		} else {
			contactList = contactDetailsRepository.findByIsDeletedAndOwnerInAndCustomerCustomerId(false,checkAccessApi, customerId, pageable);
		}

		return contactList;
	}

	public Optional<ContactDetails> getById(Long id) {

		return contactDetailsRepository.findByContactDetailsIdAndIsDeleted(id, false);
	}

	public ContactDetails save(ContactDetails contactDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		contactDetails.setIsDeleted(false);
		contactDetails.setCreatedDate(dateTime);
		contactDetails.setOwner(contactDetails.getCreatedBy());
		return contactDetailsRepository.save(contactDetails);
	}

	public ContactDetails update(ContactDetails contactDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<ContactDetails> findById = contactDetailsRepository.findById(contactDetails.getContactDetailsId());
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Contact Details not found for ID :" + contactDetails.getContactDetailsId());
		}
		ContactDetails existingContact = findById.get();
		existingContact.setTitle(contactDetails.getTitle());
		existingContact.setFirstName(contactDetails.getFirstName());
		existingContact.setMiddelName(contactDetails.getMiddelName());
		existingContact.setLastName(contactDetails.getLastName());
		existingContact.setGender(contactDetails.getGender());
		existingContact.setMaritalStatus(contactDetails.getMaritalStatus());
		existingContact.setPreferredLanguage(contactDetails.getPreferredLanguage());
		existingContact.setMobileNumber(contactDetails.getMobileNumber());
		existingContact.setFax(contactDetails.getFax());
		existingContact.setNationality(contactDetails.getNationality());
		existingContact.setAddressType(contactDetails.getAddressType());
		existingContact.setAddress1(contactDetails.getAddress1());
		existingContact.setAddress2(contactDetails.getAddress2());
		existingContact.setCity(contactDetails.getCity());
		existingContact.setState(contactDetails.getState());
		existingContact.setCountry(contactDetails.getCountry());
		existingContact.setPostCode(contactDetails.getPostCode());
		existingContact.setAddressTenureMonth(contactDetails.getAddressTenureMonth());
		existingContact.setAddressTenureYears(contactDetails.getAddressTenureYears());
		existingContact.setUpdatedBy(contactDetails.getUpdatedBy());
		existingContact.setUpdatedDate(dateTime);
		return contactDetailsRepository.save(existingContact);
	}

	public ContactDetails softDelete(Long id, String updatedBy) {
		LocalDateTime dateTime = LocalDateTime.now();
		ContactDetails existingContact;
		Optional<ContactDetails> contactDetails = contactDetailsRepository.findById(id);
		if (contactDetails.isEmpty()) {
			throw new ResourceNotFoundException("Contact Details not found and ID :"+id);
		}
		existingContact = contactDetails.get();
		Boolean b = existingContact.getIsDeleted();
		if (Boolean.TRUE.equals(b)) {
			throw new PersistenceException("Contact Details already Deleted and ID :"+id);
		}
		existingContact.setIsDeleted(true);
		existingContact.setUpdatedBy(updatedBy);
		existingContact.setUpdatedDate(dateTime);
		return contactDetailsRepository.save(existingContact);
	}

}
