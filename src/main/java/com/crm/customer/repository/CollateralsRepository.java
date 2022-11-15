package com.crm.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.crm.customer.model.Collaterals;

public interface CollateralsRepository extends RevisionRepository<Collaterals, Long, Long>,
		JpaRepository<Collaterals, Long>, PagingAndSortingRepository<Collaterals, Long> {

	List<Collaterals> findByReferenceIdAndFlagIgnoreCaseAndIsDeleted(Long refId, String flag, boolean b);

}
