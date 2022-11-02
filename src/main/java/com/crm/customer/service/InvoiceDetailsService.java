package com.crm.customer.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.InvoiceDetails;
import com.crm.customer.repository.InvoiceDetailsRepository;

@Service
public class InvoiceDetailsService {

	@Autowired
	InvoiceDetailsRepository invoiceDetailsRepository;

	public Optional<InvoiceDetails> getById(Long id) {
		return invoiceDetailsRepository.findByInvoiceDetailsIdAndIsDeleted(id, false);
	}

	public InvoiceDetails create(InvoiceDetails invoiceDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		invoiceDetails.setIsDeleted(false);
		invoiceDetails.setCreatedDate(dateTime);
		invoiceDetails.setOwner(invoiceDetails.getCreatedBy());
		return invoiceDetailsRepository.save(invoiceDetails);
	}

	public InvoiceDetails update(InvoiceDetails invoiceDetails) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<InvoiceDetails> findById = invoiceDetailsRepository.findById(invoiceDetails.getInvoiceDetailsId());
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException(
					"Invoice Details not found for ID :" + invoiceDetails.getInvoiceDetailsId());
		}
		InvoiceDetails existingInvoiceDetails = findById.get();
		existingInvoiceDetails.setPaymentMode(invoiceDetails.getPaymentMode());
		existingInvoiceDetails.setBillMedia(invoiceDetails.getBillMedia());
		existingInvoiceDetails.setNoOfCopies(invoiceDetails.getNoOfCopies());
		existingInvoiceDetails.setPrefferedLanguage(invoiceDetails.getPrefferedLanguage());
		existingInvoiceDetails.setUpdatedDate(dateTime);
		existingInvoiceDetails.setUpdatedBy(invoiceDetails.getUpdatedBy());
		return invoiceDetailsRepository.save(existingInvoiceDetails);
	}

	public InvoiceDetails softDelete(Long id, String updatedBy) {
		LocalDateTime dateTime = LocalDateTime.now();
		InvoiceDetails existingInvoiceDetails;
		Optional<InvoiceDetails> findById = invoiceDetailsRepository.findById(id);
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Invoice Details not found and ID :" + id);
		}
		existingInvoiceDetails = findById.get();
		Boolean b = existingInvoiceDetails.getIsDeleted();
		if (Boolean.TRUE.equals(b)) {
			throw new PersistenceException("Invoice Details already Deleted and ID :" + id);
		}
		existingInvoiceDetails.setIsDeleted(true);
		existingInvoiceDetails.setUpdatedBy(updatedBy);
		existingInvoiceDetails.setUpdatedDate(dateTime);
		return invoiceDetailsRepository.save(existingInvoiceDetails);
	}
}
