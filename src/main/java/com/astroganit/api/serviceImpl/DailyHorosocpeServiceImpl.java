package com.astroganit.api.serviceImpl;

import com.astroganit.api.payload.DailyHoroscopeAspect;
import com.astroganit.api.payload.DailyHorosocpeSentence;
import com.astroganit.api.repository.DailHoroscopeRepository;
import com.astroganit.api.service.DailyHoroscopeService;
import com.astroganit.api.util.DailyHoroUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DailyHorosocpeServiceImpl implements DailyHoroscopeService {
   @Autowired
   private DailHoroscopeRepository dailyRepo;

   @Cacheable(
      cacheNames = {"DailyHoroscopeRashi"},
      key = "{#todate,#langCode}"
   )
   public List<DailyHoroscopeAspect> getHoroscopeByDay(String todate, String langCode) {
      System.out.println("fetching the details from db");
      byte lCode;
      if (!langCode.equals("") && langCode != null) {
         if (langCode.equals("1")) {
            lCode = 1;
         } else if (langCode.equals("2")) {
            lCode = 2;
         } else {
            lCode = 2;
         }
      } else {
         lCode = 2;
      }

      int[] arrAspect = new int[]{1, 2, 3, 4, 5};
      int[] arrSequenceId = new int[]{75, 137, 124, 107, 54, 51, 51, 96, 103, 79, 51, 55, 68, 156, 140, 95, 51, 51, 51, 117, 173, 99, 51, 51, 51, 69, 90, 106, 51, 51};
      double julianNumber = DailyHoroUtil.getJulianDay(todate);
      int totalRashi = 12;
      List<DailyHoroscopeAspect> listAspect = new ArrayList();

      for(int rashi = 1; rashi <= totalRashi; ++rashi) {
         DailyHoroscopeAspect DHA = new DailyHoroscopeAspect();
         int number = 0;

         for(int aspect = 0; aspect < arrAspect.length; ++aspect) {
            int sequenceNo = 0;
            double uniqueNumber = julianNumber * (double)arrAspect[aspect] * (double)rashi;
            Random rand = new Random((long)uniqueNumber);
            int rating = rand.nextInt(6) + 1;
            sequenceNo = sequenceNo + (6 - rating) + number;
            number += 6;
            int sentenceCount = arrSequenceId[sequenceNo];
            int sentenceId = rand.nextInt(sentenceCount);
            DailyHorosocpeSentence dailyHoro = this.dailyRepo.getAllLangSentence(sequenceNo, sentenceId, lCode);
            if (aspect == 0) {
               DHA.setLove(dailyHoro.getSentence());
            }

            if (aspect == 1) {
               DHA.setWealth(dailyHoro.getSentence());
            }

            if (aspect == 2) {
               DHA.setFamily(dailyHoro.getSentence());
            }

            if (aspect == 3) {
               DHA.setCarrer(dailyHoro.getSentence());
            }

            if (aspect == 4) {
               DHA.setHealth(dailyHoro.getSentence());
            }
         }

         double luckyNumber = julianNumber * (double)rashi;
         Random rand1 = new Random((long)luckyNumber);
         int lNumber = rand1.nextInt(9) + 1;
         DHA.setLuckyNumber(lNumber);
         listAspect.add(DHA);
      }

      return listAspect;
   }

   @Cacheable(
      cacheNames = {"DailyHoroscopeRashiWithSentenctId"},
      key = "#todate"
   )
   public List<DailyHoroscopeAspect> getHoroscopeByDayWithSenctenceId(String todate) {
      System.out.println("fetching the details from db with sentence id and sq no");
      int[] arrAspect = new int[]{1, 2, 3, 4, 5};
      int[] arrSequenceId = new int[]{75, 137, 124, 107, 54, 51, 51, 96, 103, 79, 51, 55, 68, 156, 140, 95, 51, 51, 51, 117, 173, 99, 51, 51, 51, 69, 90, 106, 51, 51};
      double julianNumber = DailyHoroUtil.getJulianDay(todate);
      int totalRashi = 12;
      List<DailyHoroscopeAspect> listAspect = new ArrayList();

      for(int rashi = 1; rashi <= totalRashi; ++rashi) {
         DailyHoroscopeAspect DHA = new DailyHoroscopeAspect();
         int number = 0;

         for(int aspect = 0; aspect < arrAspect.length; ++aspect) {
            int sequenceNo = 0;
            double uniqueNumber = julianNumber * (double)arrAspect[aspect] * (double)rashi;
            Random rand = new Random((long)uniqueNumber);
            int rating = rand.nextInt(6) + 1;
             sequenceNo = sequenceNo + (6 - rating) + number;
            number += 6;
            int sentenceCount = arrSequenceId[sequenceNo];
            int sentenceId = rand.nextInt(sentenceCount);
            DailyHorosocpeSentence dailyHoro = this.dailyRepo.getSentence(sequenceNo, sentenceId);
            if (aspect == 0) {
               DHA.setLove("SentenceId =" + sentenceId + " SequenceNo=" + sequenceNo + "  -> " + dailyHoro.getSentence());
            }

            if (aspect == 1) {
               DHA.setWealth("SentenceId =" + sentenceId + " SequenceNo=" + sequenceNo + "  -> " + dailyHoro.getSentence());
            }

            if (aspect == 2) {
               DHA.setFamily("SentenceId =" + sentenceId + " SequenceNo=" + sequenceNo + "  -> " + dailyHoro.getSentence());
            }

            if (aspect == 3) {
               DHA.setCarrer("SentenceId =" + sentenceId + " SequenceNo=" + sequenceNo + "  -> " + dailyHoro.getSentence());
            }

            if (aspect == 4) {
               DHA.setHealth("SentenceId =" + sentenceId + " SequenceNo=" + sequenceNo + "  -> " + dailyHoro.getSentence());
            }
         }

         double luckyNumber = julianNumber * (double)rashi;
         Random rand1 = new Random((long)luckyNumber);
         int lNumber = rand1.nextInt(9) + 1;
         DHA.setLuckyNumber(lNumber);
         listAspect.add(DHA);
      }

      return listAspect;
   }
}
