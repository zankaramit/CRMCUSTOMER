package com.crm.customer.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.crm.customer.dto.Credentials;
import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.BillingAccount;
import com.crm.customer.repository.BillingAccountRepository;
import com.crm.customer.util.AccessToken;

@Service
public class BillingAccountService {

	@Autowired
	BillingAccountRepository billingAccountRepository;
	
	@Autowired
	UserService userService;

	public Optional<BillingAccount> getById(Long id) {
		return billingAccountRepository.findByBillingAccountIdAndIsDeleted(id, false);
	}

	public BillingAccount create(BillingAccount billingAccount) {
		LocalDateTime dateTime = LocalDateTime.now();
		billingAccount.setIsDeleted(false);
		billingAccount.setCreatedDate(dateTime);
		billingAccount.setOwner(billingAccount.getCreatedBy());
		return billingAccountRepository.save(billingAccount);
	}

	public BillingAccount update(BillingAccount billingAccount) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<BillingAccount> findById = billingAccountRepository.findById(billingAccount.getBillingAccountId());
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException(
					"Billing Account not found for ID :" + billingAccount.getBillingAccountId());
		}
		BillingAccount existingBillingAccount = findById.get();
		existingBillingAccount.setBillingAccount(billingAccount.getBillingAccount());
		existingBillingAccount.setBillingCycle(billingAccount.getBillingCycle());
		existingBillingAccount.setAccountType(billingAccount.getAccountType());
		existingBillingAccount.setServiceType(billingAccount.getServiceType());
		existingBillingAccount.setCreditRating(billingAccount.getCreditRating());
		existingBillingAccount.setCreditLimit(billingAccount.getCreditLimit());
		existingBillingAccount.setOverrideDunning(billingAccount.getOverrideDunning());
		existingBillingAccount.setAccountClass(billingAccount.getAccountClass());
		existingBillingAccount.setAccountStatus(billingAccount.getAccountStatus());
		existingBillingAccount.setAccountName(billingAccount.getAccountName());
		existingBillingAccount.setBillingPeriod(billingAccount.getBillingPeriod());
		existingBillingAccount.setBillingFormat(billingAccount.getBillingFormat());
		existingBillingAccount.setDetailedBilling(billingAccount.getDetailedBilling());
		existingBillingAccount.setAddressType(billingAccount.getAddressType());
		existingBillingAccount.setAddress(billingAccount.getAddress());
		existingBillingAccount.setCity(billingAccount.getCity());
		existingBillingAccount.setZipCode(billingAccount.getZipCode());
		existingBillingAccount.setProvinceState(billingAccount.getProvinceState());
		existingBillingAccount.setCountry(billingAccount.getCountry());
		existingBillingAccount.setUpdatedDate(dateTime);
		existingBillingAccount.setUpdatedBy(billingAccount.getUpdatedBy());
		return billingAccountRepository.save(existingBillingAccount);
	}

	public BillingAccount softDelete(Long id, String updatedBy) {
		LocalDateTime dateTime = LocalDateTime.now();
		BillingAccount existingBillingAccount;
		Optional<BillingAccount> findById = billingAccountRepository.findById(id);
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Billing Account not found and ID :" + id);
		}
		existingBillingAccount = findById.get();
		Boolean b = existingBillingAccount.getIsDeleted();
		if (Boolean.TRUE.equals(b)) {
			throw new PersistenceException("Billing Account already Deleted and ID :" + id);
		}
		existingBillingAccount.setIsDeleted(true);
		existingBillingAccount.setUpdatedBy(updatedBy);
		existingBillingAccount.setUpdatedDate(dateTime);
		return billingAccountRepository.save(existingBillingAccount);
	}

	public Page<BillingAccount> getSearchAndPagination(String name, String owner, Pageable pageable) {
		Page<BillingAccount> billingAccount = null;
		List<String> checkAccessApi = AccessToken.checkAccessApi(owner);

		if (ObjectUtils.isEmpty(name)) {

			billingAccount = billingAccountRepository.findByIsDeletedAndOwnerIn(false, checkAccessApi, pageable);
		} else {
			billingAccount = billingAccountRepository
					.findByIsDeletedAndOwnerInAndBillingAccountLikeIgnoreCaseOrIsDeletedAndOwnerInAndBillingCycleLikeIgnoreCaseOrIsDeletedAndOwnerInAndAccountTypeLikeIgnoreCaseOrIsDeletedAndOwnerInAndServiceTypeLikeIgnoreCase(
							false, checkAccessApi, "%" + name + "%", false, checkAccessApi, "%" + name + "%", false,
							checkAccessApi, "%" + name + "%", false, checkAccessApi, "%" + name + "%", pageable);
		}

		return billingAccount;
	}

}
