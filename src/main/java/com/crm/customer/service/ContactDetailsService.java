package com.crm.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.crm.customer.model.ContactDetails;
import com.crm.customer.repository.ContactDetailsRepository;

@Service
public class ContactDetailsService {

	@Autowired
	ContactDetailsRepository contactDetailsRepository;

	public Page<ContactDetails> getSearchAndPagination(String name, Long customerId, Pageable pageable) {

		Page<ContactDetails> contactList = null;
		if (customerId != null) {
			contactList = contactDetailsRepository.findByIsDeletedAndCustomerCustomerId(false, customerId, pageable);
		} else if (ObjectUtils.isEmpty(name)) {
			contactList = contactDetailsRepository.findByIsDeleted(false, pageable);
		} else {
			contactList = contactDetailsRepository
					.findByIsDeletedAndFirstNameLikeIgnoreCaseOrIsDeletedAndMiddelNameLikeIgnoreCaseOrIsDeletedAndLastNameLikeIgnoreCaseOrIsDeletedAndMobileNumberLikeIgnoreCaseOrIsDeletedAndFaxLikeIgnoreCaseOrIsDeletedAndNationalityLikeIgnoreCase(
							false, "%" + name + "%", false, "%" + name + "%", false, "%" + name + "%", false,
							"%" + name + "%", false, "%" + name + "%", false, "%" + name + "%", pageable);
		}

		return contactList;
	}

	public Optional<ContactDetails> getById(Long id) {

		return contactDetailsRepository.findByContactDetailsIdAndIsDeleted(id, false);
	}

	public ContactDetails save(ContactDetails contactDetails) {
		contactDetails.setIsDeleted(false);
		return contactDetailsRepository.save(contactDetails);
	}

	public ContactDetails update(ContactDetails contactDetails) {

		ContactDetails existingContact = contactDetailsRepository.findById(contactDetails.getContactDetailsId()).get();
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
		existingContact.setUpdatedDate(contactDetails.getUpdatedDate());
		return contactDetailsRepository.save(existingContact);
	}

	public ContactDetails softDelete(Long id, String updatedBy) {
		ContactDetails existingContact = contactDetailsRepository.findById(id).get();
		existingContact.setIsDeleted(true);
		existingContact.setUpdatedBy(updatedBy);
		return contactDetailsRepository.save(existingContact);
	}

}
