package com.crm.customer.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Status {
	//used to ignore null fields in an object.
	
	private int code;
	private String message;
	private Object data;
	private Long recordCount;
	private Long totalCount;
	private Long allRecordsCount;

    public Status(HttpStatus httpStatus, Object object) {
		this.code = httpStatus.value();
		this.data = object;
		this.message = "success";
    }

    public Long getAllRecordsCount() {
		return allRecordsCount;
	}

	public void setAllRecordsCount(Long allRecordsCount) {
		this.allRecordsCount = allRecordsCount;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Status() {
	}

	public Status(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	@Override
	public String toString() {
		return "Status [code=" + code + ", message=" + message + ", data=" + data + ", recordCount=" + recordCount
				+ "]";
	}

}
