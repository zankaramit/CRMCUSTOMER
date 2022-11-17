package com.crm.customer.dto;



public class ColumnMetadata {
	private String name;
	private String mappedBy;
	private ColumnType type;
	private int width;
	
	public ColumnMetadata(String name, String mappedBy, ColumnType type, int width) {
		this.name = name;
		
		this.mappedBy = mappedBy;
		this.type = type;
		this.width = width;
	}

	public String getName() {
		return name;
	}

	public String getMappedBy() {
		return mappedBy;
	}

	public ColumnType getType() {
		return type;
	}

	public int getWidth() {
		return width;
	}
}
