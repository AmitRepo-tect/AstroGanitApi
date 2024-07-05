package com.astroganit.api.service;

import com.astroganit.api.payload.NumerologyDto;
import com.astroganit.api.payload.NumerologyResponse;

public interface NumerologyService {
   NumerologyResponse getNumerologyDetail(NumerologyDto numerologyDto);
}
