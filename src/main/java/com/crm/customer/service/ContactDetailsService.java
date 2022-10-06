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
import com.crm.customer.model.ContactDetails;
import com.crm.customer.repository.ContactDetailsRepository;

@Service
public class ContactDetailsService {

	@Autowired
	ContactDetailsRepository contactDetailsRepository;

	public Page<ContactDetails> getSearchAndPagination(String name, Long customerId, Pageable pageable) {

		Page<ContactDetails> contactList = null;

		if (!ObjectUtils.isEmpty(name)) {
			contactList = contactDetailsRepository
					.findByIsDeletedAndCustomerCustomerIdAndFirstNameLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndMiddelNameLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndLastNameLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndMobileNumberLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndFaxLikeIgnoreCaseOrIsDeletedAndCustomerCustomerIdAndNationalityLikeIgnoreCase(
							false, customerId, "%" + name + "%", false, customerId, "%" + name + "%", false, customerId,
							"%" + name + "%", false, customerId, "%" + name + "%", false, customerId, "%" + name + "%",
							false, customerId, "%" + name + "%", pageable);

		} else {
			contactList = contactDetailsRepository.findByIsDeletedAndCustomerCustomerId(false, customerId, pageable);
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
		return contactDetailsRepository.save(contactDetails);
	}

	public ContactDetails update(ContactDetails contactDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<ContactDetails> findById = contactDetailsRepository.findById(contactDetails.getContactDetailsId());
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Contact Details not found for ID :" + contactDetails.getContactDetailsId());
		}
		ContactDetails existingContact = findById.get();
		existingContact.setFirstName(contactDetails.getFirstName());
		existingContact.setMiddelName(contactDetails.getMiddelName());
		existingContact.setLastName(contactDetails.getLastName());
		existingContact.setGender(contactDetails.getGender());
		existingContact.setMaritalStatus(contactDetails.getMaritalStatus());
		existingContact.setPreferredLanguage(contactDetails.getPreferredLanguage());
		existingContact.setMobileNumber(contactDetails.getMobileNumber());
		existingContact.setFax(contactDetails.getFax());
		existingContact.setNationality(contactDetails.getNationality());
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
