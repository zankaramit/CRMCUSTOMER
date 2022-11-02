package com.crm.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.crm.customer.model.BankDetails;

public interface BankDetailsRepository extends RevisionRepository<BankDetails, Long, Long>, JpaRepository<BankDetails, Long>, PagingAndSortingRepository<BankDetails, Long> {

	Optional<BankDetails> findByBankDetailsIdAndIsDeleted(Long id, boolean b);

}
