package com.crm.customer.proxy;

import java.util.List;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.crm.customer.dto.Credentials;
@FeignClient(name = "KEYCLOAK")
public interface UserAccessTokenProxy {

	@GetMapping("/user/userId/{userid}")
	public List<String> listOfUserAndReportee(@RequestHeader("Authorization") String token,
			@PathVariable(name = "userid") String userid);
	
	@PostMapping("/token/accessToken")
	public AccessTokenResponse accessToken(@RequestBody Credentials cred);
	
	

}
