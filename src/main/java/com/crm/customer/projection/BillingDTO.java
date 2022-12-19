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

	@Value("#{(target.invoiceDetails)==null?null:(target.invoiceDetails.invoiceDetailsId)}")
	Long getInvoiceDetailsId();

	@Value("#{(target.invoiceDetails)==null?null:(target.invoiceDetails.noOfCopies)}")
	String getNoOfCopies();

	@Value("#{(target.invoiceDetails)==null?null:(target.invoiceDetails.prefferedLanguage)}")
	String getPrefferedLanguage();

	@Value("#{(target.invoiceDetails)==null?null:(target.invoiceDetails.paymentMode)}")
	String getPaymentMode();

	@Value("#{(target.invoiceDetails)==null?null:(target.invoiceDetails.billMedia)}")
	String getBillMedia();

	@Value("#{(target.creditCardPaymentDetails)==null?null:(target.creditCardPaymentDetails.creditCardPaymentId)}")
	Long getCreditCardPaymentId();

	@Value("#{(target.creditCardPaymentDetails)==null?null:(target.creditCardPaymentDetails.creditCardIssuer)}")
	String getCreditCardIssuer();

	@Value("#{(target.creditCardPaymentDetails)==null?null:(target.creditCardPaymentDetails.creditCardNo)}")
	String getCreditCardNo();

	@Value("#{(target.creditCardPaymentDetails)==null?null:(target.creditCardPaymentDetails.creditCardExpiry).toString()}")
	LocalDate getCreditCardExpiry();

	@Value("#{(target.bankDetails)==null?null:(target.bankDetails.bankDetailsId)}")
	Long getBankDetailsId();

	@Value("#{(target.bankDetails)==null?null:(target.bankDetails.bankName)}")
	String getBankName();

	@Value("#{(target.bankDetails)==null?null:(target.bankDetails.bankAccountNumber)}")
	String getBankAccountNumber();

}
