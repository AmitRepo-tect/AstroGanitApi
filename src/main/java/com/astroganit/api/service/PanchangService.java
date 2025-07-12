package com.astroganit.api.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.astroganit.api.model.muhurat.MuhuratResponse;
import com.astroganit.api.payload.Response;
import com.astroganit.lib.panchang.model.BhadraResponse;
import com.astroganit.lib.panchang.model.FestivalResponse;
import com.astroganit.lib.panchang.model.PanchakResponse;
import com.astroganit.lib.panchang.model.PanchangRequest;
import com.astroganit.lib.panchang.model.PanchangResponse;

@Service
public interface PanchangService {
	PanchangResponse getPanchang(PanchangRequest panchangInputModel) throws IOException;

	PanchakResponse getPanchakData(PanchangRequest panchangInputModel) throws IOException;

	BhadraResponse getBhadraData(PanchangRequest panchangInputModel) throws IOException;

	MuhuratResponse getMuhurats(String sId) throws IOException;
	
	FestivalResponse getFestDetail(PanchangRequest panchangRequest) throws IOException;
}
