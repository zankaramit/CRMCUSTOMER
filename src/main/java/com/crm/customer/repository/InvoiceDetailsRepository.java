package com.crm.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;


import com.crm.customer.model.InvoiceDetails;

public interface InvoiceDetailsRepository extends RevisionRepository<InvoiceDetails, Long, Long>, JpaRepository<InvoiceDetails, Long>, PagingAndSortingRepository<InvoiceDetails, Long>{

	Optional<InvoiceDetails> findByInvoiceDetailsIdAndIsDeleted(Long id, boolean b);

	

}
