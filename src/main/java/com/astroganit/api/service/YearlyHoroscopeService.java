package com.astroganit.api.service;

import com.astroganit.api.payload.Response;
import com.astroganit.api.payload.YearlyHoroscopeAspect;
import com.astroganit.api.payload.YearlyHoroscopeAspect24;
import java.util.List;

public interface YearlyHoroscopeService {
	/* List<YearlyHoroscopeAspect> getYearlyHoroscope(String year); */

	List<YearlyHoroscopeAspect24> getYearlyHoroscopeHi(String year);

	Response getYearlyHoroscopeEn(String year);

	List<YearlyHoroscopeAspect24> getYearlyHoroscope24(String year, String langCode);
}
