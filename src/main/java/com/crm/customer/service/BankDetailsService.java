package com.crm.customer.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.BankDetails;
import com.crm.customer.repository.BankDetailsRepository;

@Service
public class BankDetailsService {

	@Autowired
	BankDetailsRepository bankDetailsRepository;

	public Optional<BankDetails> getById(Long id) {
		return bankDetailsRepository.findByBillingAccountBillingAccountIdAndIsDeleted(id, false);
	}

	public BankDetails create(BankDetails bankDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		bankDetails.setIsDeleted(false);
		bankDetails.setCreatedDate(dateTime);
		bankDetails.setOwner(bankDetails.getCreatedBy());
		return bankDetailsRepository.save(bankDetails);
	}

	public BankDetails update(BankDetails bankDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<BankDetails> findById = bankDetailsRepository.findById(bankDetails.getBankDetailsId());
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException(
					"Bank Details not found for ID :" + bankDetails.getBankDetailsId());
		}
		BankDetails existingBankDetails = findById.get();
		existingBankDetails.setBankName(bankDetails.getBankName());
		existingBankDetails.setFinancialServicesCode(bankDetails.getFinancialServicesCode());
		existingBankDetails.setBankAccountNumber(bankDetails.getBankAccountNumber());
		existingBankDetails.setUpdatedBy(bankDetails.getUpdatedBy());
		existingBankDetails.setUpdatedDate(dateTime);
		return bankDetailsRepository.save(existingBankDetails);
	}

	public BankDetails softDelete(Long id, String updatedBy) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<BankDetails> findById = bankDetailsRepository.findById(id);
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Bank Details not found and ID :" + id);
		}
		BankDetails existingBankDetails = findById.get();
		Boolean b = existingBankDetails.getIsDeleted();
		if (Boolean.TRUE.equals(b)) {
			throw new PersistenceException("Bank Details already Deleted and ID :" + id);
		}
		existingBankDetails.setIsDeleted(true);
		existingBankDetails.setUpdatedBy(updatedBy);
		existingBankDetails.setUpdatedDate(dateTime);
		return bankDetailsRepository.save(existingBankDetails);
	}
	
}
