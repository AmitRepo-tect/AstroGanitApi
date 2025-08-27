package com.astroganit.api.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.astroganit.api.model.muhurat.MonthlyMuhuratModel;
import com.astroganit.api.model.muhurat.MuhuratResponse;
import com.astroganit.api.payload.Response;
import com.astroganit.api.service.PanchangService;
import com.astroganit.api.util.HUtil;
import com.astroganit.lib.panchang.BhadraCalculation;
import com.astroganit.lib.panchang.ChogdiyaCalculation;
import com.astroganit.lib.panchang.DailyPanchangCalculation;
import com.astroganit.lib.panchang.DoGhatiCalculation;
import com.astroganit.lib.panchang.FestivalCalculation;
import com.astroganit.lib.panchang.FestivalCalculationNew;
import com.astroganit.lib.panchang.HoraCalculation;
import com.astroganit.lib.panchang.PanchakCalculation;
import com.astroganit.lib.panchang.RahuKaalCalculation;
import com.astroganit.lib.panchang.model.BhadraBean;
import com.astroganit.lib.panchang.model.BhadraResponse;
import com.astroganit.lib.panchang.model.ChogdiyaResponse;
import com.astroganit.lib.panchang.model.DoGhatiResponse;
import com.astroganit.lib.panchang.model.FestivalResponse;
import com.astroganit.lib.panchang.model.FestivalResponseNew;
import com.astroganit.lib.panchang.model.HoraResponse;
import com.astroganit.lib.panchang.model.PanchakResponse;
import com.astroganit.lib.panchang.model.PanchakTimeBean;
import com.astroganit.lib.panchang.model.PanchangInputModel;
import com.astroganit.lib.panchang.model.PanchangRequest;
import com.astroganit.lib.panchang.model.PanchangResponse;
import com.astroganit.lib.panchang.model.RahukaalResponse;
import com.astroganit.lib.panchang.model.SampurnPanchangModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PanchangServiceImpl implements PanchangService {

	@Override
	public PanchangResponse getPanchang(PanchangRequest panchangRequest) throws IOException {
		Calendar calendar = Calendar.getInstance();
		int languageCode = panchangRequest.getLangCode();
		PanchangInputModel panchangInputModel = panchangRequest.getPanchangInputModel();
		calendar.set(Calendar.DATE, panchangInputModel.getDateTimeInfo().getDay());
		calendar.set(Calendar.MONTH, panchangInputModel.getDateTimeInfo().getMonth());
		calendar.set(Calendar.YEAR, panchangInputModel.getDateTimeInfo().getYear());
		PanchangResponse panchangResponse = new PanchangResponse();
		DailyPanchangCalculation dailyPanchangCalculation = new DailyPanchangCalculation(calendar,
				panchangInputModel.getPlace(), languageCode);
		SampurnPanchangModel SampurnPanchangModel = dailyPanchangCalculation.getSampurnPanchangModel(calendar);

		HoraResponse horaResponse = new HoraResponse();
		HoraCalculation horaCalculation = new HoraCalculation(languageCode);
		horaResponse.setHoraList(horaCalculation.getHoradata(calendar.get(Calendar.DAY_OF_WEEK) - 1, calendar));
		horaResponse.setDayHoraList(horaCalculation.getDayHoraData(calendar.get(Calendar.DAY_OF_WEEK) - 1, calendar));
		horaResponse
				.setNightHoraList(horaCalculation.getNightHoraData(calendar.get(Calendar.DAY_OF_WEEK) - 1, calendar));

		ChogdiyaResponse chogdiyaResponse = new ChogdiyaResponse();
		ChogdiyaCalculation chogdiyaCalculation = new ChogdiyaCalculation(languageCode);
		chogdiyaResponse.setChogdiyaList(chogdiyaCalculation.getDayChogdiaData(calendar));
		chogdiyaResponse.setDayChogdiyaList(chogdiyaCalculation.getDayChogdiaData(calendar));
		chogdiyaResponse.setNightChogdiyaList(chogdiyaCalculation.getNightChogdiaData(calendar));

		DoGhatiResponse doGhatiResponse = new DoGhatiResponse();
		DoGhatiCalculation doGhatiCalculation = new DoGhatiCalculation(languageCode);
		doGhatiResponse.setDoGhatiList(doGhatiCalculation.getDoGhatiData(calendar));
		doGhatiResponse.setDayDoGhatiList(doGhatiCalculation.getDayDoGhatiData(calendar));
		doGhatiResponse.setNightDoGhatiList(doGhatiCalculation.getNightDoGhatiData(calendar));

		RahukaalResponse rahukaalResponse = new RahukaalResponse();
		RahuKaalCalculation rahuKaalCalculation = new RahuKaalCalculation();
		rahukaalResponse.setArrayList(rahuKaalCalculation.getRahuKaalData(calendar));

		panchangResponse.setSampurnPanchangModel(SampurnPanchangModel);
		panchangResponse.setHoraResponse(horaResponse);
		panchangResponse.setChogdiyaResponse(chogdiyaResponse);
		panchangResponse.setDoGhatiResponse(doGhatiResponse);
		panchangResponse.setRahukaalResponse(rahukaalResponse);
		return panchangResponse;

	}

	@Override
	public PanchakResponse getPanchakData(PanchangRequest panchangRequest) throws IOException {
		Calendar calendar = Calendar.getInstance();
		int languageCode = panchangRequest.getLangCode();
		PanchangInputModel panchangInputModel = panchangRequest.getPanchangInputModel();
		calendar.set(Calendar.DATE, panchangInputModel.getDateTimeInfo().getDay());
		calendar.set(Calendar.MONTH, panchangInputModel.getDateTimeInfo().getMonth());
		calendar.set(Calendar.YEAR, panchangInputModel.getDateTimeInfo().getYear());

		PanchakResponse panchakResponse = new PanchakResponse();
		PanchakCalculation panchakCalculation = new PanchakCalculation();
		ArrayList<PanchakTimeBean> arrayList = panchakCalculation.getPanchakData(calendar,
				panchangInputModel.getDateTimeInfo().getMonth());
		panchakResponse.setArrayList(arrayList);
		return panchakResponse;
	}

	@Override
	public BhadraResponse getBhadraData(PanchangRequest panchangRequest) throws IOException {
		Calendar calendar = Calendar.getInstance();
		int languageCode = panchangRequest.getLangCode();
		PanchangInputModel panchangInputModel = panchangRequest.getPanchangInputModel();
		calendar.set(Calendar.DATE, 0);
		calendar.set(Calendar.MONTH, panchangInputModel.getDateTimeInfo().getMonth());
		calendar.set(Calendar.YEAR, panchangInputModel.getDateTimeInfo().getYear());
		BhadraResponse bhadraResponse = new BhadraResponse();
		BhadraCalculation bhadraCalculation = new BhadraCalculation(languageCode);
		ArrayList<BhadraBean> arrayList = bhadraCalculation.getBhadraData(calendar, languageCode);
		bhadraResponse.setArrayList(arrayList);
		return bhadraResponse;
	}

	@Override
	public FestivalResponse getFestDetail(PanchangRequest panchangRequest) throws IOException {
		PanchangInputModel panchangInputModel = panchangRequest.getPanchangInputModel();
		int languageCode = panchangRequest.getLangCode();
		FestivalResponse festivalResponse = new FestivalResponse();
		FestivalCalculation festivalCalculation = new FestivalCalculation(languageCode);
		festivalResponse.setFestDetail(festivalCalculation
				.getFestivalList(2031/* panchangInputModel.getDateTimeInfo().getYear() */, languageCode));
		return festivalResponse;
	}

	@Override
	@Cacheable(cacheNames = { "VivahMuhurat" }, key = "#sId")
	public MuhuratResponse getMuhurats(String sId) {
		MuhuratResponse muhuratResponse = new MuhuratResponse();
		int id = HUtil.strToInt(sId);
		String path = "";
		if (id == 1) {
			path = "json/muhurat/muhurat_vivah2025" + ".json";
		} else if (id == 2) {
			path = "json/muhurat/muhurat_car_2025" + ".json";
		} else if (id == 3) {
			path = "json/muhurat/muhurat_namkarna_2025" + ".json";
		} else if (id == 4) {
			path = "json/muhurat/muhurat_bhumi_pujan_2025" + ".json";
		} else if (id == 5) {
			path = "json/muhurat/muhurat_grah_pravesh_2025" + ".json";
		} else if (id == 6) {
			path = "json/muhurat/muhurat_mundan2025" + ".json";
		} else if (id == 7) {
			path = "json/muhurat/muhurat_bhumi_pujan_2025" + ".json";
		}

		try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream is = new ClassPathResource(path).getInputStream();
			List<MonthlyMuhuratModel> list = mapper.readValue(is, new TypeReference<List<MonthlyMuhuratModel>>() {
			});
			muhuratResponse.setMuhuratDetail(list);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to read muhurat data", e);
		}
		return muhuratResponse;
	}

	@Override
	public FestivalResponseNew getFestDetailNew(int year, int language) throws IOException {
		FestivalResponseNew festivalResponse = new FestivalResponseNew();
		FestivalCalculationNew festivalCalculationNew = new FestivalCalculationNew(language);
		festivalResponse.setFestDetail(festivalCalculationNew.getFestivalList(year, language));
		return festivalResponse;
	}

	@Override
	public FestivalResponseNew getVrat(int year, int language) throws IOException {
		FestivalResponseNew festivalResponse = new FestivalResponseNew();
		FestivalCalculationNew festivalCalculationNew = new FestivalCalculationNew(language);
		festivalResponse.setFestDetail(festivalCalculationNew.getVratList(year, language));
		return festivalResponse;
	}

}
