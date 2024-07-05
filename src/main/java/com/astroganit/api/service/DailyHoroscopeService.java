package com.astroganit.api.service;

import com.astroganit.api.payload.DailyHoroscopeAspect;
import java.util.List;

public interface DailyHoroscopeService {
   List<DailyHoroscopeAspect> getHoroscopeByDay(String todate, String langCode);

   List<DailyHoroscopeAspect> getHoroscopeByDayWithSenctenceId(String todate);
}
