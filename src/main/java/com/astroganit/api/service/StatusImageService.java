package com.astroganit.api.service;

import com.astroganit.api.payload.Response;
import java.util.List;

import org.springframework.data.domain.Page;

import com.astroganit.api.entities.StatusImg;

public interface StatusImageService {
	/*
	 * Response getAllImages(int langCode);
	 * 
	 * Response getImagesByCateId(int cateId, int langCode);
	 */

	Response findBySpecialDayInAndLang(long date,int lang);

	Response findBySpecialDayInAndLangOrderByCreateDateDesc(long date,int lang);

	Response findByCateIdAndLang(Long cateId, int lang);

	Response findBySubCateIdAndLang(int subCateId, int lang);

	Response getStatusImages(List<String> specialDays, int page, int size);

	Response getAllCategory(int lang);

}
