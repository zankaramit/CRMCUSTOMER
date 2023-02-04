package com.crm.customer.dto;



import com.crm.customer.model.Collaterals;
import com.crm.customer.model.Customer;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class CustomerServiceRequest {

	private Customer customer;
	
	private Collaterals collaterals;
}
