package com.astroganit.api.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.astroganit.api.entities.StatusCategory;
import com.astroganit.api.entities.StatusImg;
import com.astroganit.api.payload.Response;
import com.astroganit.api.repository.StatusImgCategoryRepo;
import com.astroganit.api.repository.StatusImgRepo;
import com.astroganit.api.service.StatusImageService;
import com.astroganit.api.util.StatusImgUtil;
import org.springframework.data.domain.Sort;

import specification.StatusImageSpecifications;

@Service
public class StatusImageServiceImpl implements StatusImageService {
	Logger logger = LoggerFactory.getLogger(CityDetailServiceImpl.class);
	@Autowired
	private StatusImgRepo statusImgRepo;

	@Autowired
	private StatusImgCategoryRepo statusImgCategoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Response findBySpecialDayInAndLang(long time, int lang) {
		List<String> specialDyas = new StatusImgUtil().getSpecialDaysList(time);
		List<StatusImg> list = statusImgRepo.findBySpecialDayInAndLang(specialDyas, String.valueOf(lang));
		Response response = new Response();
		response.setData(list);
		response.setResultCode(1);
		response.setMessage("");
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		return response;
	}

	@Override
	public Response findBySpecialDayInAndLangOrderByCreateDateDesc(long time, int lang) {
		List<String> specialDyas = new StatusImgUtil().getSpecialDaysList(time);
		List<StatusImg> list = statusImgRepo.findBySpecialDayInAndLangOrderByCreateDateDesc(specialDyas,
				String.valueOf(lang));
		Response response = new Response();
		response.setData(list);
		response.setResultCode(1);
		response.setMessage("");
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		return response;
	}

	@Override
	public Response findByCateIdAndLang(Long cateId, int lang) {
		List<StatusImg> list = statusImgRepo.findByCateIdAndLang(cateId, String.valueOf(lang));
		Response response = new Response();
		response.setData(list);
		response.setResultCode(1);
		response.setMessage("");
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		return response;
	}

	@Override
	public Response findBySubCateIdAndLang(int subCateId, int lang) {
		List<StatusImg> list = statusImgRepo.findBySubCateIdAndLang(subCateId, String.valueOf(lang));
		Response response = new Response();
		response.setData(list);
		response.setResultCode(1);
		response.setMessage("");
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		return response;
	}

	public Response getStatusImages(List<String> specialDays, int page, int size) {
		// Pageable pageable = PageRequest.of(page, size);
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));
		Specification<StatusImg> spec = Specification.where(StatusImageSpecifications.hasCategoryIn(specialDays));
		Page<StatusImg> pages = statusImgRepo.findAll(spec, pageable);
		List<StatusImg> list = pages.getContent();
		Response response = new Response();
		response.setData(list);
		response.setResultCode(1);
		response.setMessage("");
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		return response;
	}

	@Override
	public Response getAllCategory(int lang) {
		List<StatusCategory> list = statusImgCategoryRepo.findByLang(String.valueOf(lang));
		Response response = new Response();
		response.setData(list);
		response.setResultCode(1);
		response.setMessage("");
		response.setErrorMessage("");
		response.setStatus(HttpStatus.OK);
		return response;
	}

}
