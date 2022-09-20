package com.crm.customer.service;

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


import com.crm.customer.model.CompanyAddressDetails;
import com.crm.customer.repository.CompanyAddressDetailsRepository;

@Service
public class CompanyAddressDetailsService {

	@Autowired
	CompanyAddressDetailsRepository companyAddressDetailsRepository;

	public CompanyAddressDetails save(CompanyAddressDetails companyAddressDetails) {

		return companyAddressDetailsRepository.save(companyAddressDetails);
	}

	public Optional<CompanyAddressDetails> getAddressDetailById(Long id) {

		return companyAddressDetailsRepository.findById(id);
	}

	public CompanyAddressDetails update(CompanyAddressDetails companyAddressDetails) {

		CompanyAddressDetails addressDetailsUpadte = companyAddressDetailsRepository
				.findById(companyAddressDetails.getAddressDetailsId()).get();
		addressDetailsUpadte.setAddress1(companyAddressDetails.getAddress1());
		addressDetailsUpadte.setAddress2(companyAddressDetails.getAddress2());
		addressDetailsUpadte.setAddressType(companyAddressDetails.getAddressType());
		addressDetailsUpadte.setContactAddress(companyAddressDetails.getContactAddress());
		addressDetailsUpadte.setCountry(companyAddressDetails.getCountry());
		addressDetailsUpadte.setOwnershipStatus(companyAddressDetails.getOwnershipStatus());
		addressDetailsUpadte.setPostCode(companyAddressDetails.getPostCode());
		addressDetailsUpadte.setState(companyAddressDetails.getState());
		addressDetailsUpadte.setAddressTenureMonth(companyAddressDetails.getAddressTenureMonth());
		addressDetailsUpadte.setAddressTenureYears(companyAddressDetails.getAddressTenureYears());
		addressDetailsUpadte.setLastUpdateDate(null);
		addressDetailsUpadte.setLastUpdatedBy(null);
		addressDetailsUpadte.setLastUpdateLogin(null);

		return companyAddressDetailsRepository.save(addressDetailsUpadte);

	}

//	public Page<CompanyAddressDetails> addressDetailsDataTable(SearchDataTable searchDataTable) {
//
//		Pageable p = PageRequest.of(searchDataTable.getPage(), searchDataTable.getSize(),
//				Sort.by(Sort.Direction.DESC, searchDataTable.getSortby()));
//		Page<CompanyAddressDetails> searchResult = null;
//		if (searchDataTable.getCustomerId() != null) {
//			searchResult = companyAddressDetailsRepository.findByCustomerCustomerId(searchDataTable.getCustomerId(), p);
//		} else {
//			searchResult = companyAddressDetailsRepository
//					.findByAddress1LikeIgnoreCaseOrAddress2LikeIgnoreCaseOrAddressTypeLikeIgnoreCaseOrContactAddressLikeIgnoreCaseOrCountryLikeIgnoreCaseOrStateLikeIgnoreCase(
//							"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
//							"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
//							"%" + searchDataTable.getSearchField() + "%", "%" + searchDataTable.getSearchField() + "%",
//							p);
//		}
//		return searchResult;
//	}

}
