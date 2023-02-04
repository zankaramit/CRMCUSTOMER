package com.crm.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import com.crm.customer.dto.SearchCustomer;
import com.crm.customer.model.Customer;
import com.crm.customer.projection.CustomerDTO;

public interface CustomerRepository extends RevisionRepository<Customer, Long, Long>, JpaRepository<Customer, Long>,
		PagingAndSortingRepository<Customer, Long> {

	Optional<Customer> findByCustomerIdAndIsDeleted(Long id, boolean b);

	Page<Customer> findByIsDeletedAndCustomerTypeIgnoreCase(boolean b, String customerType, Pageable pageable);

	Page<Customer> findByIsDeletedAndCustomerTypeAndFirstNameLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndMiddelNameLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndLastNameLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndEmailAddressLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndMobileNumberLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndAccountNameLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndCompanyRegistrationNumberLikeIgnoreCaseOrIsDeletedAndCustomerTypeAndWebsiteDetailsLikeIgnoreCase(
			boolean b, String string, String string2, boolean c, String string3, String string4, boolean d,
			String string5, String string6, boolean e, String string7, String string8, boolean f, String string9,
			String string10, boolean g, String string11, String string12, boolean h, String string13, String string14,
			boolean i, String string15, String string16, Pageable pageable);

	Page<Customer> findByIsDeletedAndOwnerIn(boolean b, List<String> checkAccessApi, Pageable pageable);

	Page<Customer> findByIsDeletedAndOwnerInAndFirstNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndMiddelNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndLastNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndEmailAddressLikeIgnoreCaseOrIsDeletedAndOwnerInAndMobileNumberLikeIgnoreCaseOrIsDeletedAndOwnerInAndAccountNameLikeIgnoreCaseOrIsDeletedAndOwnerInAndCompanyRegistrationNumberLikeIgnoreCaseOrIsDeletedAndOwnerInAndWebsiteDetailsLikeIgnoreCase(
			boolean b, List<String> checkAccessApi, String string, boolean c, List<String> checkAccessApi2,
			String string2, boolean d, List<String> checkAccessApi3, String string3, boolean e,
			List<String> checkAccessApi4, String string4, boolean f, List<String> checkAccessApi5, String string5,
			boolean g, List<String> checkAccessApi6, String string6, boolean h, List<String> checkAccessApi7,
			String string7, boolean i, List<String> checkAccessApi8, String string8, Pageable pageable);

	@Query(value = "select new com.crm.customer.dto.SearchCustomer(cust,billingAccount,identi,adds,cola) "
			+ "from Customer cust left join BillingAccount billingAccount ON cust.customerId =billingAccount.customer.customerId "
			+ " left join Identification identi ON cust.customerId=identi.customer.customerId "
			+ " left join AddressDetails adds ON cust.customerId=adds.customer.customerId "
			+ "left join Collaterals cola ON cust.customerId=cola.referenceId and cola.isDeleted = :f and cola.documentType = :doctyp  "
			+ "where cust.isDeleted = :f and  billingAccount.isDeleted = :f and"
			+ "(lower(cust.firstName) like lower(concat('%', :name,'%'))"
			+ "or (lower(cust.middelName) like lower(concat('%', :name,'%')))"
			+ "or (lower(cust.lastName) like lower(concat('%', :name,'%')))"
			+ "or (lower(cust.mobileNumber) like lower(concat('%', :name,'%')))"
			+ "or (lower(cust.accountName) like lower(concat('%', :name,'%')))"
			+ "or (lower(billingAccount.billingAccount) like lower(concat('%', :name,'%')))"
			+ "or (lower(identi.identificationNumber) like lower(concat('%', :name,'%')))"
			+ "or (lower(cola.documentType) like lower(concat('%',:docType,'%')) and lower(cola.documentInfo) like lower(concat('%',:name,'%'))))")

	Page<SearchCustomer> searchByInput(@Param("f") boolean b, @Param("doctyp") String documentTypeCollaterals, @Param("name") String name,
			@Param("docType") String docType, Pageable pageable);

	CustomerDTO findByCustomerId(Long id);

	Optional<Customer> findByIsDeletedAndUserId(boolean b, String userid);

	@Query(value = "select new com.crm.customer.dto.SearchCustomer(cust,billingAccount,identi,adds,cola) "
			+ "from Customer cust left join BillingAccount billingAccount ON cust.customerId =billingAccount.customer.customerId "
			+ " left join Identification identi ON cust.customerId=identi.customer.customerId "
			+ " left join AddressDetails adds ON cust.customerId=adds.customer.customerId "
			+ "left join Collaterals cola ON cust.customerId=cola.referenceId and cola.isDeleted = :f and cola.documentType = :doctyp  "
			+ "where cust.isDeleted = :f and  billingAccount.isDeleted = :f and"
			+ "(lower(cust.firstName) like lower(concat('%', :name,'%'))"
			+ "or (lower(cust.middelName) like lower(concat('%', :name,'%')))"
			+ "or (lower(cust.lastName) like lower(concat('%', :name,'%')))"
			+ "or (lower(cust.mobileNumber) like lower(concat('%', :name,'%'))))")
	Page<SearchCustomer> searchByConsumerName(@Param("f") boolean b, @Param("doctyp") String documentTypeCollaterals, @Param("name") String input,
			Pageable pageable);

	@Query(value = "select new com.crm.customer.dto.SearchCustomer(cust,billingAccount,identi,adds,cola) "
			+ "from Customer cust left join BillingAccount billingAccount ON cust.customerId =billingAccount.customer.customerId "
			+ " left join Identification identi ON cust.customerId=identi.customer.customerId "
			+ " left join AddressDetails adds ON cust.customerId=adds.customer.customerId "
			+ "left join Collaterals cola ON cust.customerId=cola.referenceId and cola.isDeleted = :f and cola.documentType = :doctyp  "
			+ "where cust.isDeleted = :f and  billingAccount.isDeleted = :f and"
			+ "(lower(cust.mobileNumber) like lower(concat('%', :name,'%'))"
			+ "or (lower(cust.accountName) like lower(concat('%', :name,'%'))))")
	Page<SearchCustomer> searchByCorporateName(@Param("f") boolean b, @Param("doctyp")  String documentTypeCollaterals, @Param("name") String input,
			Pageable pageable);

	@Query(value = "select new com.crm.customer.dto.SearchCustomer(cust,billingAccount,identi,adds,cola) "
			+ "from Customer cust left join BillingAccount billingAccount ON cust.customerId =billingAccount.customer.customerId "
			+ " left join Identification identi ON cust.customerId=identi.customer.customerId "
			+ " left join AddressDetails adds ON cust.customerId=adds.customer.customerId "
			+ "left join Collaterals cola ON cust.customerId=cola.referenceId and cola.isDeleted = :f and cola.documentType = :doctyp  "
			+ "where cust.isDeleted = :f and  billingAccount.isDeleted = :f and"
			+ "((lower(billingAccount.billingAccount) like lower(concat('%', :name,'%'))))")
	Page<SearchCustomer> searchByBillingAccount(@Param("f") boolean b, @Param("doctyp") String documentTypeCollaterals, @Param("name") String input,
			Pageable pageable);

	@Query(value = "select new com.crm.customer.dto.SearchCustomer(cust,billingAccount,identi,adds,cola) "
			+ "from Customer cust left join BillingAccount billingAccount ON cust.customerId =billingAccount.customer.customerId "
			+ " left join Identification identi ON cust.customerId=identi.customer.customerId "
			+ " left join AddressDetails adds ON cust.customerId=adds.customer.customerId "
			+ "left join Collaterals cola ON cust.customerId=cola.referenceId and cola.isDeleted = :f and cola.documentType = :doctyp  "
			+ "where cust.isDeleted = :f and  billingAccount.isDeleted = :f and"
			+ "((lower(cola.documentInfo) like lower(concat('%',:name,'%'))))")
	Page<SearchCustomer> searchByID(@Param("f") boolean b, @Param("doctyp") String documentTypeCollaterals, @Param("name") String input, Pageable pageable);

}
