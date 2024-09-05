package com.astroganit.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.astroganit.api.entities.StatusCategory;
import com.astroganit.api.entities.StatusImg;

@Repository
public interface StatusImgCategoryRepo extends JpaRepository<StatusCategory, Integer>, JpaSpecificationExecutor<StatusCategory> {
	
	List<StatusCategory> findByLang(String lang);

}
