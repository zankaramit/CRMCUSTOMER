package com.crm.customer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.customer.dto.ColumnMetadata;
import com.crm.customer.dto.ColumnType;
import com.crm.customer.dto.DataTableMetadata;

@RestController
@RequestMapping("reference-data-table-metadata")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReferenceMetadataController {

	private static final DataTableMetadata REFERENCE_METADATA;

	static {
		REFERENCE_METADATA = new DataTableMetadata()
				.addColumnMetadata(new ColumnMetadata("", "referenceDetailsId", ColumnType.RADIO, 10))
				.addColumnMetadata(new ColumnMetadata("First Name", "firstName", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("Last Name", "lastName", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("ADDRESS", "address1", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("CITY", "city", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("COUNTRY", "country", ColumnType.TEXT, 15))
				.addColumnMetadata(new ColumnMetadata("PHONE", "referencePhoneNumber", ColumnType.TEXT, 15));

	}

	@GetMapping("reference")
	public DataTableMetadata getReferenceDataTableMetadata() {
		return REFERENCE_METADATA;
	}
}
