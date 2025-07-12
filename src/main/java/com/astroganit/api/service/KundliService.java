package com.astroganit.api.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.astroganit.lib.horo.model.BirthDetailBean;
import com.astroganit.lib.horo.model.KundliBean;





@Service
public interface KundliService {
	KundliBean getKundliData(BirthDetailBean birthDetailBean) throws IOException;

}
