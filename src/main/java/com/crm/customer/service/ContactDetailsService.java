package com.crm.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.crm.customer.model.ContactDetails;
import com.crm.customer.repository.ContactDetailsRepository;

@Service
public class ContactDetailsService {

	@Autowired
	ContactDetailsRepository contactDetailsRepository;

//	public Page<ContactDetails> contactDetailsDataTable(SearchDataTable searchDataTable) {
//		Pageable p = PageRequest.of(searchDataTable.getPage(), searchDataTable.getSize(),
//				Sort.by(Sort.Direction.DESC, searchDataTable.getSortby()));
//		Page<ContactDetails> searchResult = null;
//		if (searchDataTable.getCustomerId() != null) {
//			searchResult = contactDetailsRepository.findByCustomerCustomerId(searchDataTable.getCustomerId(), p);
//		} else {
//			searchResult = contactDetailsRepository
//					.findByFirstNameLikeIgnoreCaseOrMiddelNameLikeIgnoreCaseOrLastNameLikeIgnoreCaseOrGenderLikeIgnoreCaseOrMaritalStatusLikeIgnoreCaseOrMobileNumberLikeIgnoreCaseOrFaxLikeIgnoreCaseOrNationalityLikeIgnoreCase(
//							"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
//							"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
//							"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
//							"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
//							p);
//		}
//
//		return searchResult;
//	}

	public ContactDetails save(ContactDetails contactDetails) {

		return contactDetailsRepository.save(contactDetails);
	}

	public ContactDetails update(ContactDetails contactDetails) {

		ContactDetails ContactDetailsUpdate = contactDetailsRepository.findById(contactDetails.getContactDetailsId())
				.get();
		ContactDetailsUpdate.setFirstName(contactDetails.getFirstName());
		ContactDetailsUpdate.setMiddelName(contactDetails.getMiddelName());
		ContactDetailsUpdate.setLastName(contactDetails.getLastName());
		ContactDetailsUpdate.setGender(contactDetails.getGender());
		ContactDetailsUpdate.setMaritalStatus(contactDetails.getMaritalStatus());
		ContactDetailsUpdate.setPreferredLanguage(contactDetails.getPreferredLanguage());
		ContactDetailsUpdate.setMobileNumber(contactDetails.getMobileNumber());
		ContactDetailsUpdate.setFax(contactDetails.getFax());
		ContactDetailsUpdate.setNationality(contactDetails.getNationality());
		ContactDetailsUpdate.setLastUpdateDate(null);
		ContactDetailsUpdate.setLastUpdatedBy(null);
		ContactDetailsUpdate.setLastUpdateLogin(null);
		return contactDetailsRepository.save(ContactDetailsUpdate);
	}

	public Optional<ContactDetails> getContactDetailsById(Long id) {

		return contactDetailsRepository.findById(id);
	}

}
