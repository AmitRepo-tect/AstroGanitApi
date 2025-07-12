package com.astroganit.api.serviceImpl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.astroganit.api.service.KundliService;
import com.astroganit.lib.horo.GenerateKundliData;
import com.astroganit.lib.horo.model.BirthDetailBean;
import com.astroganit.lib.horo.model.KundliBean;



@Service
public class KundliServiceImpl implements KundliService{

	@Override
	public KundliBean getKundliData(BirthDetailBean birthDetailBean) throws IOException {
		GenerateKundliData generateKundliData=new GenerateKundliData();
		KundliBean kundliBean=generateKundliData.getPlanets(birthDetailBean);
		return kundliBean;
	}

}
