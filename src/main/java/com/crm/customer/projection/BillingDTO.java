package com.crm.customer.projection;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

public interface BillingDTO {

	Long getBillingAccountId();

	String getAccountName();

	String getBillingCycle();

	String getBillingPeriod();

	String getBillingFormat();

	String getDetailedBilling();

	@Value("#{target.invoiceDetails.invoiceDetailsId}")
	Long getInvoiceDetailsId();

	@Value("#{target.invoiceDetails.noOfCopies}")
	String getNoOfCopies();

	@Value("#{target.invoiceDetails.prefferedLanguage}")
	String getPrefferedLanguage();

	@Value("#{target.invoiceDetails.paymentMode}")
	String getPaymentMode();

	@Value("#{target.invoiceDetails.billMedia}")
	String getBillMedia();

	@Value("#{target.creditCardPaymentDetails.creditCardPaymentId}")
	Long getCreditCardPaymentId();

	@Value("#{target.creditCardPaymentDetails.creditCardIssuer}")
	String getCreditCardIssuer();

	@Value("#{target.creditCardPaymentDetails.creditCardNo}")
	String getCreditCardNo();

	@Value("#{target.creditCardPaymentDetails.creditCardExpiry}")
	LocalDate getCreditCardExpiry();

	@Value("#{target.bankDetails.bankDetailsId}")
	Long getBankDetailsId();

	@Value("#{target.bankDetails.bankName}")
	String getBankName();

	@Value("#{target.bankDetails.bankAccountNumber}")
	String getBankAccountNumber();

}
