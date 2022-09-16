package com.crm.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crm.customer.model.CompanyDetails;

public interface CompanyDetailsRepository
		extends JpaRepository<CompanyDetails, Long>, PagingAndSortingRepository<CompanyDetails, Long> {

}
