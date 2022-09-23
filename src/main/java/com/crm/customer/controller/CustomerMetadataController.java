package com.crm.customer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.customer.dto.ColumnMetadata;
import com.crm.customer.dto.ColumnType;
import com.crm.customer.dto.DataTableMetadata;

@RestController
@RequestMapping("data-table-metadata")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerMetadataController {

	private static final DataTableMetadata CUSTOMER_METADATA;
	private static final DataTableMetadata ADDRESS_METADATA;
	private static final DataTableMetadata CONTACT_METADATA;
	private static final DataTableMetadata REFERENCE_METADATA;

	static {
		CUSTOMER_METADATA = new DataTableMetadata()
				.addColumnMetadata(new ColumnMetadata("", "customerId", ColumnType.RADIO, 10))
				.addColumnMetadata(new ColumnMetadata("CUSTOMER", "firstName", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("Account Name", "accountName", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("TYPE", "customerType", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("PHONE", "mobileNumber", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("EMAIL", "emailAddress", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("DATE", "createdDate", ColumnType.TEXT, 15));

		ADDRESS_METADATA = new DataTableMetadata()
				.addColumnMetadata(new ColumnMetadata("", "addressDetailsId", ColumnType.RADIO, 10))
                .addColumnMetadata(new ColumnMetadata("TYPE", "addressType", ColumnType.TEXT, 18))
				.addColumnMetadata(new ColumnMetadata("ADDRESS", "address1", ColumnType.TEXT, 18))
				.addColumnMetadata(new ColumnMetadata("STATE", "state", ColumnType.TEXT, 18))
				.addColumnMetadata(new ColumnMetadata("COUNTRY", "country", ColumnType.TEXT, 18))
				.addColumnMetadata(new ColumnMetadata("POST CODE", "postCode", ColumnType.TEXT, 18));
		
		CONTACT_METADATA = new DataTableMetadata()
				.addColumnMetadata(new ColumnMetadata("", "contactDetailsId", ColumnType.RADIO, 10))
				.addColumnMetadata(new ColumnMetadata("First Name", "firstName", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("Last Name", "lastName", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("GENDER", "gender", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("PHONE", "mobileNumber", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("FAX", "fax", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("DATE", "createdDate", ColumnType.TEXT, 15));
		
		REFERENCE_METADATA = new DataTableMetadata()
				.addColumnMetadata(new ColumnMetadata("", "referenceDetailsId", ColumnType.RADIO, 10))
				.addColumnMetadata(new ColumnMetadata("First Name", "firstName", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("Last Name", "lastName", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("ADDRESS", "address1", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("CITY", "city", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("COUNTRY", "country", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("PHONE", "referencePhoneNumber", ColumnType.TEXT, 15));



	}
	
	@GetMapping("customer")
	public DataTableMetadata getCustomerDataTableMetadata() {
		return CUSTOMER_METADATA;
	}
	
	@GetMapping("address")
	public DataTableMetadata getAddressDataTableMetadata() {
		return ADDRESS_METADATA;
	}
	
	@GetMapping("contact")
	public DataTableMetadata getContactDataTableMetadata() {
		return CONTACT_METADATA;
	}
	
	@GetMapping("reference")
	public DataTableMetadata getReferenceDataTableMetadata() {
		return REFERENCE_METADATA;
	}
}
