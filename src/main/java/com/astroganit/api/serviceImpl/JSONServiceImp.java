package com.astroganit.api.serviceImpl;

import com.astroganit.api.payload.Response;
import com.astroganit.api.service.JSONService;
import com.astroganit.api.util.HUtil;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class JSONServiceImp implements JSONService {
   @Cacheable(
      cacheNames = {"BaalKandBySlok"},
      key = "#sId"
   )
   public Response getBaalKandBySlok(String sId) {
      Response response = new Response();
      int id = HUtil.strToInt(sId);
      String path = "json/baalkand/bk" + id + ".json";
      String file = "";

      try {
         file = HUtil.readFile(path);
         response.setResultCode(1);
         response.setMessage("");
         response.setErrorMessage("");
         response.setStatus(HttpStatus.OK);
         response.setData(Arrays.asList(file));
      } catch (IOException var7) {
         response.setResultCode(0);
         response.setMessage("getting exception while getting the json on given slok number");
         response.setErrorMessage(var7.getMessage());
         response.setStatus(HttpStatus.BAD_REQUEST);
         response.setData(Arrays.asList());
      }

      return response;
   }

   @Cacheable(
      cacheNames = {"AranyaKandBySlok"},
      key = "#sId"
   )
   public Response getAranyaKandBySlok(String sId) {
      Response response = new Response();
      int id = HUtil.strToInt(sId);
      String path = "json/aranyakand/aranyaK" + id + ".json";
      String file = "";

      try {
         file = HUtil.readFile(path);
         response.setResultCode(1);
         response.setMessage("");
         response.setErrorMessage("");
         response.setStatus(HttpStatus.OK);
         response.setData(Arrays.asList(file));
      } catch (IOException var7) {
         response.setResultCode(0);
         response.setMessage("getting exception while getting the json on given slok number");
         response.setErrorMessage(var7.getMessage());
         response.setStatus(HttpStatus.BAD_REQUEST);
         response.setData(Arrays.asList());
      }

      return response;
   }

   @Cacheable(
      cacheNames = {"AyodhyaKandBySlok"},
      key = "#sId"
   )
   public Response getAyodhyaKandBySlok(String sId) {
      Response response = new Response();
      int id = HUtil.strToInt(sId);
      String path = "json/ayodhyakand/ayodhyaK" + id + ".json";
      String file = "";

      try {
         file = HUtil.readFile(path);
         response.setResultCode(1);
         response.setMessage("");
         response.setErrorMessage("");
         response.setStatus(HttpStatus.OK);
         response.setData(Arrays.asList(file));
      } catch (IOException var7) {
         response.setResultCode(0);
         response.setMessage("getting exception while getting the json on given slok number");
         response.setErrorMessage(var7.getMessage());
         response.setStatus(HttpStatus.BAD_REQUEST);
         response.setData(Arrays.asList());
      }

      return response;
   }

   @Cacheable(
      cacheNames = {"KisgandhaKandBySlok"},
      key = "#sId"
   )
   public Response getKisgandhaKandBySlok(String sId) {
      Response response = new Response();
      int id = HUtil.strToInt(sId);
      String path = "json/kisgandhakand/kisgKand" + id + ".json";
      String file = "";

      try {
         file = HUtil.readFile(path);
         response.setResultCode(1);
         response.setMessage("");
         response.setErrorMessage("");
         response.setStatus(HttpStatus.OK);
         response.setData(Arrays.asList(file));
      } catch (IOException var7) {
         response.setResultCode(0);
         response.setMessage("getting exception while getting the json on given slok number");
         response.setErrorMessage(var7.getMessage());
         response.setStatus(HttpStatus.BAD_REQUEST);
         response.setData(Arrays.asList());
      }

      return response;
   }

   @Cacheable(
      cacheNames = {"LankaKandBySlok"},
      key = "#sId"
   )
   public Response getLankaKandBySlok(String sId) {
      Response response = new Response();
      int id = HUtil.strToInt(sId);
      String path = "json/lankakand/lanka_kand" + id + ".json";
      String file = "";

      try {
         file = HUtil.readFile(path);
         response.setResultCode(1);
         response.setMessage("");
         response.setErrorMessage("");
         response.setStatus(HttpStatus.OK);
         response.setData(Arrays.asList(file));
      } catch (IOException var7) {
         response.setResultCode(0);
         response.setMessage("getting exception while getting the json on given slok number");
         response.setErrorMessage(var7.getMessage());
         response.setStatus(HttpStatus.BAD_REQUEST);
         response.setData(Arrays.asList());
      }

      return response;
   }

   @Cacheable(
      cacheNames = {"UttraKandBySlok"},
      key = "#sId"
   )
   public Response getUttraKandBySlok(String sId) {
      Response response = new Response();
      int id = HUtil.strToInt(sId);
      String path = "json/uttrakand/uttraK" + id + ".json";
      String file = "";

      try {
         file = HUtil.readFile(path);
         response.setResultCode(1);
         response.setMessage("");
         response.setErrorMessage("");
         response.setStatus(HttpStatus.OK);
         response.setData(Arrays.asList(file));
      } catch (IOException var7) {
         response.setResultCode(0);
         response.setMessage("getting exception while getting the json on given slok number");
         response.setErrorMessage(var7.getMessage());
         response.setStatus(HttpStatus.BAD_REQUEST);
         response.setData(Arrays.asList());
      }

      return response;
   }
}
