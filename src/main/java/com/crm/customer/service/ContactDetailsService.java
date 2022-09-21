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
		Date date = new Date();
		contactDetails.setIsDeleted(false);
		contactDetails.setCreatedDate(date);
		return contactDetailsRepository.save(contactDetails);
	}

	public ContactDetails update(ContactDetails contactDetails) {
		Date date = new Date();
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
		existingContact.setUpdatedDate(date);
		return contactDetailsRepository.save(existingContact);
	}

	public ContactDetails softDelete(Long id, String updatedBy) {
		Date date = new Date();
		ContactDetails existingContact = contactDetailsRepository.findById(id).get();
		existingContact.setIsDeleted(true);
		existingContact.setUpdatedBy(updatedBy);
		existingContact.setUpdatedDate(date);
		return contactDetailsRepository.save(existingContact);
	}

}
