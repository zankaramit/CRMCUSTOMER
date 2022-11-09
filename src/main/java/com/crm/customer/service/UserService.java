package com.crm.customer.service;

import java.util.List;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.crm.customer.dto.Credentials;
import com.crm.customer.proxy.UserAccessTokenProxy;

@Service
@Component
public class UserService {

	@Autowired
	UserAccessTokenProxy userAccessTokenProxy;
	
//	static String userCreationURL = "http://192.168.1.11:8040/user/userId/";
//	static String accessTokenURL = "http://192.168.1.11:8040/token/accessToken";

	public AccessTokenResponse getAccessToken() {

		Credentials cred = new Credentials();
		cred.setUsername("admin");
		cred.setPassword("Reset@123");
		
		return userAccessTokenProxy.accessToken(cred);

//		RestTemplate http = new RestTemplate();
//		HttpHeaders header = new HttpHeaders();
//		header.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Credentials> httpEntity = new HttpEntity<Credentials>(cred, header);
//
//		ResponseEntity<AccessTokenResponse> postForEntity = http.exchange(accessTokenURL, HttpMethod.POST, httpEntity,
//				AccessTokenResponse.class);
//
//		return postForEntity.getBody();

	}

	public List<String> checkAccessApi(String obj) {

//		String userCreationURL1 = userCreationURL.concat(obj);

		String token = getAccessToken().getToken();
//		String token = null;
//		RestTemplate http = new RestTemplate();
//		HttpHeaders header = new HttpHeaders();
//		header.setContentType(MediaType.APPLICATION_JSON);
//		header.add("Authorization", "Bearer " + token);
//		HttpEntity httpEntity = new HttpEntity<>(null, header);
//
//		ResponseEntity<List<String>> postForEntity = http.exchange(userCreationURL1, HttpMethod.GET, httpEntity,
//				new ParameterizedTypeReference<List<String>>() {
//				});
//
//		return postForEntity.getBody();
		return userAccessTokenProxy.listOfUserAndReportee("Bearer "+ token, obj);

	}

}
