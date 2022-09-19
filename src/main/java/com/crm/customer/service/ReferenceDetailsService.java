package com.crm.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.crm.customer.dto.SearchDataTable;
import com.crm.customer.model.ReferenceDetails;
import com.crm.customer.repository.ReferenceDetailsRepository;

@Service
public class ReferenceDetailsService {

	@Autowired
	ReferenceDetailsRepository referenceDetailsRepository;

	public Page<ReferenceDetails> referenceDetailsDataTable(SearchDataTable searchDataTable) {
		Pageable p = PageRequest.of(searchDataTable.getPage(), searchDataTable.getSize(),
				Sort.by(Sort.Direction.DESC, searchDataTable.getSortby()));
		Page<ReferenceDetails> searchResult = null;
		if (searchDataTable.getCustomerId() != null) {
			searchResult = referenceDetailsRepository.findByCustomerCustomerId(searchDataTable.getCustomerId(), p);
		} else {
			searchResult = referenceDetailsRepository
					.findByFirstNameLikeIgnoreCaseOrMiddelNameLikeIgnoreCaseOrLastNameLikeIgnoreCaseOrAddressTypeLikeIgnoreCaseOrAddress1LikeIgnoreCaseOrAddress2LikeIgnoreCaseOrCityLikeIgnoreCaseOrProvinceStateLikeIgnoreCaseOrCountryLikeIgnoreCaseOrReferencePhoneNumberLikeIgnoreCaseOrRelationshipLikeIgnoreCase(
							"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
							"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
							"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
							"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
							"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
							"%" + searchDataTable.getSearchField() + "%", p);
		}
		return searchResult;
	}

	public ReferenceDetails save(ReferenceDetails referenceDetails) {

		return referenceDetailsRepository.save(referenceDetails);
	}

	public ReferenceDetails update(ReferenceDetails referenceDetails) {

		ReferenceDetails referenceDetailsUpdate = referenceDetailsRepository
				.findById(referenceDetails.getReferenceDetailsId()).get();

		referenceDetailsUpdate.setFirstName(referenceDetails.getFirstName());
		referenceDetailsUpdate.setMiddelName(referenceDetails.getMiddelName());
		referenceDetailsUpdate.setLastName(referenceDetails.getLastName());
		referenceDetailsUpdate.setAddressType(referenceDetails.getAddressType());
		referenceDetailsUpdate.setAddress1(referenceDetails.getAddress1());
		referenceDetailsUpdate.setAddress2(referenceDetails.getAddress2());
		referenceDetailsUpdate.setCity(referenceDetails.getCity());
		referenceDetailsUpdate.setProvinceState(referenceDetails.getProvinceState());
		referenceDetailsUpdate.setCountry(referenceDetails.getCountry());
		referenceDetailsUpdate.setPostCode(referenceDetails.getPostCode());
		referenceDetailsUpdate.setReferencePhoneNumber(referenceDetails.getReferencePhoneNumber());
		referenceDetailsUpdate.setRelationship(referenceDetails.getRelationship());
		referenceDetailsUpdate.setLastUpdateDate(null);
		referenceDetailsUpdate.setLastUpdatedBy(null);
		referenceDetailsUpdate.setLastUpdateLogin(null);

		return referenceDetailsRepository.save(referenceDetailsUpdate);
	}

	public Optional<ReferenceDetails> getReferenceDetailsById(Long id) {

		return referenceDetailsRepository.findById(id);
	}

}
