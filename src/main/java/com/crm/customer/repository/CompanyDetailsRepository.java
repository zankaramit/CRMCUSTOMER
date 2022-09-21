package com.crm.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crm.customer.model.CompanyDetails;

public interface CompanyDetailsRepository
		extends JpaRepository<CompanyDetails, Long>, PagingAndSortingRepository<CompanyDetails, Long> {

	Optional<CompanyDetails> findByCompanyDetailsIdAndIsDeleted(Long id, boolean b);

}
