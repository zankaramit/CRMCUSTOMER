package com.crm.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crm.customer.proxy.FileServerProxy;

@Service
@Component
public class UploadFileService {

	@Autowired
	FileServerProxy fileServerProxy;

	public String uploadFile(MultipartFile file) {
		String uploadFile = fileServerProxy.uploadFile(file);
		return uploadFile.substring(uploadFile.indexOf("http"));
	}
}
