package com.crm.customer.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.Identification;
import com.crm.customer.repository.IdentificationDetailsRepository;
import com.crm.customer.util.AccessToken;
import com.crm.customer.util.UploadFile;

@Service
public class IdentificationDetailsService {

	@Autowired
	IdentificationDetailsRepository identificationDetailsRepository;

	@Autowired
	UserService userService;

	@Autowired
	UploadFileService uploadFileService;

	public Page<Identification> getSearchAndPagination(String name, String owner, Long customerId, Pageable pageable) {
		Page<Identification> identificationList = null;
		List<String> checkAccessApi = userService.checkAccessApi(owner);
		if (!ObjectUtils.isEmpty(name)) {
			identificationList = identificationDetailsRepository
					.findByIsDeletedAndOwnerInAndCustomerCustomerIdAndIdentificationNumberLikeIgnoreCaseOrIsDeletedAndOwnerInAndCustomerCustomerIdAndIdentificationTypeLikeIgnoreCase(
							false, checkAccessApi, customerId, "%" + name + "%", false, checkAccessApi, customerId,
							"%" + name + "%", pageable);
		} else {
			identificationList = identificationDetailsRepository.findByIsDeletedAndOwnerInAndCustomerCustomerId(false,
					checkAccessApi, customerId, pageable);
		}
		return identificationList;
	}

	public Identification save(Identification identification, MultipartFile file) {
		if (!file.getOriginalFilename().isEmpty()) {
		if (!file.isEmpty()) {
			String fileUploadLocation = uploadFileService.uploadFile(file);
			identification.setIDSoftcopy(fileUploadLocation);
		}
		}
		LocalDateTime dateTime = LocalDateTime.now();
		identification.setIsDeleted(false);
		identification.setCreatedDate(dateTime);
		identification.setOwner(identification.getCreatedBy());
		return identificationDetailsRepository.save(identification);
	}

	public Optional<Identification> getById(Long id) {

		return identificationDetailsRepository.findByCustomerCustomerIdAndIsDeleted(id, false);
	}

	public Identification update(Identification identification, MultipartFile file) {
		LocalDateTime dateTime = LocalDateTime.now();
		Optional<Identification> findById = identificationDetailsRepository
				.findById(identification.getIdentificationId());
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException(
					"Identification Details not found for ID :" + identification.getIdentificationId());
		}
		Identification identificationexisting = findById.get();
		identificationexisting.setIdentificationType(identification.getIdentificationType());
		identificationexisting.setIdentificationNumber(identification.getIdentificationNumber());
		identificationexisting.setIdExpiryDate(identification.getIdExpiryDate());
//		identificationexisting.setIDSoftcopy(identification.getIDSoftcopy());
		identificationexisting.setMothersMaidenName(identification.getMothersMaidenName());
		identificationexisting.setUpdatedBy(identification.getUpdatedBy());
		identificationexisting.setUpdatedDate(dateTime);
		if (!file.getOriginalFilename().isEmpty()) {
		if (!file.isEmpty()) {
			String fileUploadLocation = uploadFileService.uploadFile(file);
			identificationexisting.setIDSoftcopy(fileUploadLocation);
		}
		}
		return identificationDetailsRepository.save(identificationexisting);
	}

	public Identification softDelete(Long id, String updatedBy) {
		LocalDateTime dateTime = LocalDateTime.now();
		Identification existingIdentification;
		Optional<Identification> findById = identificationDetailsRepository.findById(id);
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Identification Details not found and ID :" + id);
		}
		existingIdentification = findById.get();
		Boolean b = existingIdentification.getIsDeleted();
		if (Boolean.TRUE.equals(b)) {
			throw new PersistenceException("Identification Details already Deleted and ID :" + id);
		}
		existingIdentification.setIsDeleted(true);
		existingIdentification.setUpdatedBy(updatedBy);
		existingIdentification.setUpdatedDate(dateTime);
		return identificationDetailsRepository.save(existingIdentification);
	}

}
