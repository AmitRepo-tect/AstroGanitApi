package com.astroganit.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

import com.astroganit.api.entities.StatusCategory;
import com.astroganit.api.entities.StatusImg;

@Repository
public interface StatusImgRepo extends JpaRepository<StatusImg, Integer>, JpaSpecificationExecutor<StatusImg> {
	/*
	 * @Query(nativeQuery = true, value =
	 * "select * from status_img  where lang=:langCode") List<StatusImg>
	 * getAllImages(@Param("langCode") String langCode);
	 * 
	 * @Query(nativeQuery = true, value =
	 * "select * from status_img  where cate_id=:cateId and lang=:langCode")
	 * List<StatusImg> getImagesByCateId(@Param("cateId") String
	 * cateId, @Param("langCode") String langCode);
	 */

	// List<StatusImg> findBySpecialDayIn(List<String> specialDays);
	List<StatusImg> findBySpecialDayInAndLang(List<String> specialDays, String lang);

	List<StatusImg> findBySpecialDayInAndLangOrderByCreateDateDesc(List<String> specialDays, String lang);

	List<StatusImg> findByCateIdAndLang(Long cateId, String lang);

	List<StatusImg> findBySubCateIdAndLang(int subCateId, String lang);

}
