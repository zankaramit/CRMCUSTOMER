package com.crm.customer.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
public class Credentials {

	private String username;
	private String password;
}
