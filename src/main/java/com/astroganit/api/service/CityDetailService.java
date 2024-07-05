package com.astroganit.api.service;

import com.astroganit.api.payload.CityDetailsDto;
import java.util.List;

public interface CityDetailService {
   List<CityDetailsDto> getCities(String place);
}
