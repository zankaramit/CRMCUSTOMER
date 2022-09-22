package com.crm.customer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.customer.dto.ColumnMetadata;
import com.crm.customer.dto.ColumnType;
import com.crm.customer.dto.DataTableMetadata;

@RestController
@RequestMapping("customer-data-table-metadata")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerMetadataController {

	private static final DataTableMetadata CUSTOMER_METADATA;

	static {
		CUSTOMER_METADATA = new DataTableMetadata()
				.addColumnMetadata(new ColumnMetadata("", "customerId", ColumnType.RADIO, 10))
				
				.addColumnMetadata(new ColumnMetadata("First Name", "firstName", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("Last Name", "lastName", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("TYPE", "customerType", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("PHONE", "mobileNumber", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("EMAIL", "emailAddress", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("DATE", "createdDate", ColumnType.TEXT, 15));

	}
	
	@GetMapping("customer")
	public DataTableMetadata getCustomerDataTableMetadata() {
		return CUSTOMER_METADATA;
	}
}
