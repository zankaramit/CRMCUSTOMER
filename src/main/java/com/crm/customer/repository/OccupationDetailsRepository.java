package com.crm.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.crm.customer.model.OccupationDetails;

public interface OccupationDetailsRepository
		extends JpaRepository<OccupationDetails, Long>, PagingAndSortingRepository<OccupationDetails, Long> {

	Optional<OccupationDetails> findByCustomerCustomerIdAndIsDeleted(Long id, boolean b);

}
