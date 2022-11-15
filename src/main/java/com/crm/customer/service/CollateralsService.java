package com.crm.customer.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crm.customer.exception.ResourceNotFoundException;
import com.crm.customer.model.Collaterals;
import com.crm.customer.repository.CollateralsRepository;

@Service
public class CollateralsService {

	@Autowired
	CollateralsRepository collateralsRepository;

	@Autowired
	UploadFileService uploadFileService;

	public Collaterals save(Collaterals collaterals, MultipartFile file) {
		if (file != null) {
			String fileUploadLocation = uploadFileService.uploadFile(file);
			collaterals.setFileName(fileUploadLocation);
		}
		LocalDateTime dateTime = LocalDateTime.now();
		collaterals.setIsDeleted(false);
		collaterals.setCreatedDate(dateTime);
		collaterals.setOwner(collaterals.getCreatedBy());
		return collateralsRepository.save(collaterals);

	}

	public List<Collaterals> getByCustomerIdAndFlag(Long refId, String flag) {

		return collateralsRepository.findByReferenceIdAndFlagIgnoreCaseAndIsDeleted(refId, flag, false);
	}

	public Collaterals softDelete(Long id, String updatedBy) {
		LocalDateTime dateTime = LocalDateTime.now();
		Collaterals existingCollaterals;
		Optional<Collaterals> findById = collateralsRepository.findById(id);
		if (findById.isEmpty()) {
			throw new ResourceNotFoundException("Documents not found and ID :" + id);
		}
		existingCollaterals =findById.get();
		Boolean b = existingCollaterals.getIsDeleted();
		if (Boolean.TRUE.equals(b)) {
			throw new PersistenceException("Documents already Deleted and ID :" + id);
		}
		existingCollaterals.setIsDeleted(true);
		existingCollaterals.setUpdatedBy(updatedBy);
		existingCollaterals.setUpdatedDate(dateTime);
		return collateralsRepository.save(existingCollaterals);
	}

}