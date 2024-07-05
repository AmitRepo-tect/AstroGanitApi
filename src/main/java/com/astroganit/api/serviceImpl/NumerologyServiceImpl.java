package com.astroganit.api.serviceImpl;

import com.astroganit.api.payload.NumerologyDto;
import com.astroganit.api.payload.NumerologyResponse;
import com.astroganit.api.service.NumerologyService;
import com.astroganit.api.util.NumerologyUtil;
import org.springframework.stereotype.Service;

@Service
public class NumerologyServiceImpl implements NumerologyService {
   public NumerologyResponse getNumerologyDetail(NumerologyDto numerologyDto) {
      int getDay = numerologyDto.getDay();
      int getMonth = numerologyDto.getMonth();
      int getYear = numerologyDto.getYear();
      String getName = numerologyDto.getName();
      int getCType = numerologyDto.getType();
      int langCode = numerologyDto.getLangCode();
      if (getCType != 0 && getCType != 1 && getCType != 2 && getCType != 3) {
         getCType = 0;
      }

      System.out.println("ctype= " + getCType + " " + getDay);
      String fullDob = getDay + "-" + getMonth + "-" + getYear;
      String[] getFirstName = getName.split(" ");
      String firstName = getFirstName[0];
      int mulank = NumerologyUtil.getMulank(String.valueOf(getDay));
      int destineyNumber = NumerologyUtil.getDestinyNumber(fullDob);
      int namank = NumerologyUtil.getNameMulank(getName, getCType);
      String[] arrFavRashi = NumerologyUtil.numerologyfavourableRashi.split("##");
      String[] arrFavAlphabet = NumerologyUtil.numerologyfavourableAlphabet.split("##");
      String[] arrGemstone = NumerologyUtil.numerologyGemstone.split("##");
      String[] arrFavDays = NumerologyUtil.numerologyDays.split("##");
      String[] arrFavNumber = NumerologyUtil.numerologyFavNumber.split("##");
      String[] arrDisha = NumerologyUtil.numerologyDisha.split("##");
      String[] arrAuspColor = NumerologyUtil.numerologyAuspicious_color.split("##");
      String[] arrRulingPlanet = NumerologyUtil.numerologyPlanet.split("##");
      String[] arrGod = NumerologyUtil.numerologyGod.split("##");
      String[] arrFast = NumerologyUtil.numerologyFast.split("##");
      String[] arrFavDate = NumerologyUtil.numerologyFavDate.split("##");
      NumerologyResponse nr = new NumerologyResponse();
      nr.setRadicalNumber(mulank);
      nr.setDestineyNumber(destineyNumber);
      nr.setNameNumber(namank);
      nr.setFavRashi(arrFavRashi[mulank - 1]);
      nr.setFavAlphabet(arrFavAlphabet[mulank - 1]);
      nr.setFavGemstone(arrGemstone[mulank - 1]);
      nr.setFavDays(arrFavDays[mulank - 1]);
      nr.setFavNumber(arrFavNumber[mulank - 1]);
      nr.setFavDisha(arrDisha[mulank - 1]);
      nr.setAuspColor(arrAuspColor[mulank - 1]);
      nr.setRulingPlanet(arrRulingPlanet[mulank - 1]);
      nr.setFavGod(arrGod[mulank - 1]);
      nr.setFavFast(arrFast[mulank - 1]);
      nr.setFavDate(arrFavDate[mulank - 1]);
      return nr;
   }
}
