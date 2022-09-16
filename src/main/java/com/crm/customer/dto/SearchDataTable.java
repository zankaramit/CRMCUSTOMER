package com.crm.customer.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class SearchDataTable {
	
	private String searchField;
	
	private int page; 
	private int size;
	private String sortby;
	
}
