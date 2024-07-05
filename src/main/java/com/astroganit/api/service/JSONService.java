package com.astroganit.api.service;

import com.astroganit.api.payload.Response;

public interface JSONService {
   Response getBaalKandBySlok(String sId);

   Response getAranyaKandBySlok(String sId);

   Response getAyodhyaKandBySlok(String sId);

   Response getKisgandhaKandBySlok(String sId);

   Response getLankaKandBySlok(String sId);

   Response getUttraKandBySlok(String sId);
}
