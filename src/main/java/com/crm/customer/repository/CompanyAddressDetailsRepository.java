package com.crm.customer.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.util.Streamable;

import com.crm.customer.model.CompanyAddressDetails;
import com.crm.customer.model.CompanyDetails;

public interface CompanyAddressDetailsRepository
		extends JpaRepository<CompanyAddressDetails, Long>, PagingAndSortingRepository<CompanyAddressDetails, Long> {

	Page<CompanyAddressDetails> findByCustomerCustomerId(Long customerId, Pageable p);

	Page<CompanyAddressDetails> findByAddress1LikeIgnoreCaseOrAddress2LikeIgnoreCaseOrAddressTypeLikeIgnoreCaseOrContactAddressLikeIgnoreCaseOrCountryLikeIgnoreCaseOrStateLikeIgnoreCase(
			String string, String string2, String string3, String string4, String string5, String string6, Pageable p);

}
