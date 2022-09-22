package com.crm.customer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.customer.dto.ColumnMetadata;
import com.crm.customer.dto.ColumnType;
import com.crm.customer.dto.DataTableMetadata;

@RestController
@RequestMapping("address-data-table-metadata")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressMetadataController {

	private static final DataTableMetadata ADDRESS_METADATA;

	static {
		ADDRESS_METADATA = new DataTableMetadata()
				.addColumnMetadata(new ColumnMetadata("", "addressDetailsId", ColumnType.RADIO, 10))
                .addColumnMetadata(new ColumnMetadata("TYPE", "addressType", ColumnType.TEXT, 18))
				.addColumnMetadata(new ColumnMetadata("ADDRESS", "address1", ColumnType.TEXT, 18))
				.addColumnMetadata(new ColumnMetadata("STATE", "state", ColumnType.TEXT, 18))
				.addColumnMetadata(new ColumnMetadata("COUNTRY", "country", ColumnType.TEXT, 18))
				.addColumnMetadata(new ColumnMetadata("POST CODE", "postCode", ColumnType.TEXT, 18));

	}

	@GetMapping("address")
	public DataTableMetadata getAddressDataTableMetadata() {
		return ADDRESS_METADATA;
	}
}
