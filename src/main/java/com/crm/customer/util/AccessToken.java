package com.crm.customer.util;

import java.util.List;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.crm.customer.dto.Credentials;

public class AccessToken {
	static String userCreationURL = "http://192.168.1.11:8040/user/userId/";
	static String accessTokenURL = "http://192.168.1.11:8040/token/accessToken";

	public static AccessTokenResponse getAccessToken() {

		Credentials cred = new Credentials();
		cred.setUsername("admin");
		cred.setPassword("Reset@123");

		RestTemplate http = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Credentials> httpEntity = new HttpEntity<Credentials>(cred, header);

		ResponseEntity<AccessTokenResponse> postForEntity = http.exchange(accessTokenURL, HttpMethod.POST, httpEntity,
				AccessTokenResponse.class);

		return postForEntity.getBody();

	}

	public static List<String> checkAccessApi(String obj) {

		String userCreationURL1 = userCreationURL.concat(obj);

		String token = getAccessToken().getToken();
//		String token = null;
		RestTemplate http = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.add("Authorization", "Bearer " + token);
		HttpEntity httpEntity = new HttpEntity<>(null, header);

		ResponseEntity<List<String>> postForEntity = http.exchange(userCreationURL1, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<String>>() {
				});

		return postForEntity.getBody();

	}

}
