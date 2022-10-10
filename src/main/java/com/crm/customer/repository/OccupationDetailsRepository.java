package com.crm.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.crm.customer.model.OccupationDetails;

public interface OccupationDetailsRepository
		extends RevisionRepository<OccupationDetails, Long, Long>, JpaRepository<OccupationDetails, Long>, PagingAndSortingRepository<OccupationDetails, Long> {

	Optional<OccupationDetails> findByCustomerCustomerIdAndIsDeleted(Long id, boolean b);

}
