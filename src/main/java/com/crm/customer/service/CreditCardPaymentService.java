package com.crm.customer.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.CreditCardPaymentDetails;
import com.crm.customer.repository.CreditCardPaymentRepository;

@Service
public class CreditCardPaymentService {

	@Autowired
	CreditCardPaymentRepository creditCardPaymentRepository;

	public Optional<CreditCardPaymentDetails> getById(Long id) {
		return creditCardPaymentRepository.findByBillingAccountBillingAccountIdAndIsDeleted(id, false);
	}

	public CreditCardPaymentDetails create(CreditCardPaymentDetails creditCardPaymentDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		creditCardPaymentDetails.setIsDeleted(false);
		creditCardPaymentDetails.setCreatedDate(dateTime);
		creditCardPaymentDetails.setOwner(creditCardPaymentDetails.getCreatedBy());
		return creditCardPaymentRepository.save(creditCardPaymentDetails);
	}

	public CreditCardPaymentDetails update(CreditCardPaymentDetails creditCardPaymentDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<CreditCardPaymentDetails> findById = creditCardPaymentRepository
				.findById(creditCardPaymentDetails.getCreditCardPaymentId());
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Credit Card Payment Details not found for ID :"
					+ creditCardPaymentDetails.getCreditCardPaymentId());
		}
		CreditCardPaymentDetails existingCreditCardPaymentDetails = findById.get();
		existingCreditCardPaymentDetails.setCreditCardIssuer(creditCardPaymentDetails.getCreditCardIssuer());
		existingCreditCardPaymentDetails.setCreditCardName(creditCardPaymentDetails.getCreditCardName());
		existingCreditCardPaymentDetails.setCreditCardType(creditCardPaymentDetails.getCreditCardType());
		existingCreditCardPaymentDetails.setCreditCardNo(creditCardPaymentDetails.getCreditCardNo());
		existingCreditCardPaymentDetails.setCreditCardExpiry(creditCardPaymentDetails.getCreditCardExpiry());
		existingCreditCardPaymentDetails.setUpdatedBy(creditCardPaymentDetails.getUpdatedBy());
		existingCreditCardPaymentDetails.setUpdatedDate(dateTime);
		return creditCardPaymentRepository.save(existingCreditCardPaymentDetails);
	}

	public CreditCardPaymentDetails softDelete(Long id, String updatedBy) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<CreditCardPaymentDetails> findById = creditCardPaymentRepository.findById(id);
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Invoice Details not found and ID :" + id);
		}
		CreditCardPaymentDetails existingCreditCardPaymentDetails = findById.get();
		Boolean b = existingCreditCardPaymentDetails.getIsDeleted();
		if (Boolean.TRUE.equals(b)) {
			throw new PersistenceException("Invoice Details already Deleted and ID :" + id);
		}
		existingCreditCardPaymentDetails.setIsDeleted(true);
		existingCreditCardPaymentDetails.setUpdatedBy(updatedBy);
		existingCreditCardPaymentDetails.setUpdatedDate(dateTime);
		return creditCardPaymentRepository.save(existingCreditCardPaymentDetails);
	}

	
}
