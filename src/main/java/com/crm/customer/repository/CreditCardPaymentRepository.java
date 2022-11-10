package com.crm.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.crm.customer.model.CreditCardPaymentDetails;

public interface CreditCardPaymentRepository extends RevisionRepository<CreditCardPaymentDetails, Long, Long>,
		JpaRepository<CreditCardPaymentDetails, Long>, PagingAndSortingRepository<CreditCardPaymentDetails, Long> {

	Optional<CreditCardPaymentDetails> findByBillingAccountBillingAccountIdAndIsDeleted(Long id, boolean b);

}
