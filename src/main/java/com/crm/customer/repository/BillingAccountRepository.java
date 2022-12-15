package com.crm.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.crm.customer.dto.BillingAddressDTO;
import com.crm.customer.dto.BillingDTO;
import com.crm.customer.model.BillingAccount;

public interface BillingAccountRepository extends RevisionRepository<BillingAccount, Long, Long>,
		JpaRepository<BillingAccount, Long>, PagingAndSortingRepository<BillingAccount, Long> {
	Optional<BillingAccount> findByBillingAccountIdAndIsDeleted(Long id, boolean b);

	Page<BillingAccount> findByIsDeletedAndOwner(boolean b, String owners, Pageable pageable);

	Page<BillingAccount> findByIsDeletedAndOwnerInAndCustomerCustomerId(boolean b, List<String> checkAccessApi,
			Long customerId, Pageable pageable);

	Page<BillingAccount> findByIsDeletedAndOwnerInAndCustomerCustomerIdAndAccountNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndBillingAccountLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndBillingCycleLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndAccountTypeLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndServiceTypeLikeIgnoreCase(
			boolean b, List<String> checkAccessApi, Long customerId, String string, boolean c,
			List<String> checkAccessApi2, Long customerId2, String string2, boolean d, List<String> checkAccessApi3,
			Long customerId3, String string3, boolean e, List<String> checkAccessApi4, Long customerId4, String string4,
			boolean f, List<String> checkAccessApi5, Long customerId5, String string5, Pageable pageable);

	

	BillingDTO findByBillingAccountId(Long id);

	

	BillingAddressDTO getByBillingAccountId(Long id);


	

}
