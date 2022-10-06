package com.crm.customer.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
	private UploadFile(){}
	public static String uploadFile(MultipartFile file) {

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://114.143.224.42:8020/uploadFile";

		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> multiValMap = new LinkedMultiValueMap<>();
		multiValMap.add("file", file.getResource());

		HttpEntity<MultiValueMap<String, Object>> requestBody = new HttpEntity<>(
				multiValMap, httpHeader);

		String responseBody = restTemplate.postForEntity(url, requestBody, String.class).getBody();
		return responseBody.substring(responseBody.indexOf("http"), responseBody.length());
	}
}
