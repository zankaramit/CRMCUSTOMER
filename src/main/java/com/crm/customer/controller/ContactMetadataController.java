package com.crm.customer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.customer.dto.ColumnMetadata;
import com.crm.customer.dto.ColumnType;
import com.crm.customer.dto.DataTableMetadata;

@RestController
@RequestMapping("contact-data-table-metadata")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContactMetadataController {

	private static final DataTableMetadata CONTACT_METADATA;

	static {
		CONTACT_METADATA = new DataTableMetadata()
				.addColumnMetadata(new ColumnMetadata("", "contactDetailsId", ColumnType.RADIO, 10))
				.addColumnMetadata(new ColumnMetadata("First Name", "firstName", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("Last Name", "lastName", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("GENDER", "gender", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("PHONE", "mobileNumber", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("FAX", "fax", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("DATE", "createdDate", ColumnType.TEXT, 15));

	}

	@GetMapping("contact")
	public DataTableMetadata getContactDataTableMetadata() {
		return CONTACT_METADATA;
	}
}
