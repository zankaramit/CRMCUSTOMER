package com.crm.customer.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "EMAIL-SERVICE")
public interface FileServerProxy {

	@PostMapping(path = "/uploadFile", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//  @Headers("Content-Type: multipart/form-data")
  public String uploadFile(@RequestPart(name = "file") MultipartFile file);
}
