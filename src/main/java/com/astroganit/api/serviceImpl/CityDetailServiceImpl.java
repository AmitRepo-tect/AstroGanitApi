package com.astroganit.api.serviceImpl;

import com.astroganit.api.entities.CityDetails;
import com.astroganit.api.payload.CityDetailsDto;
import com.astroganit.api.repository.CityDetailsRepo;
import com.astroganit.api.service.CityDetailService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CityDetailServiceImpl implements CityDetailService {
	Logger logger = LoggerFactory.getLogger(CityDetailServiceImpl.class);
   @Autowired
   private CityDetailsRepo cityDetailRepo;
   @Autowired
   private ModelMapper modelMapper;

   @Cacheable(
      cacheNames = {"CityDetailsDto"},
      key = "#place"
   )
   public List<CityDetailsDto> getCities(String place) {
      this.logger.info("into city method to get city list.");
      List<CityDetails> cityList = this.cityDetailRepo.findByPlaceStartsWith(place);
      List<CityDetailsDto> cityDtolist = (List)cityList.stream().map((cities) -> {
         return (CityDetailsDto)this.modelMapper.map(cities, CityDetailsDto.class);
      }).collect(Collectors.toList());
      return cityDtolist;
   }
}
