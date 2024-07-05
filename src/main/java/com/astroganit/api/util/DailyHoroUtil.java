package com.astroganit.api.util;

public class DailyHoroUtil {
   public static double getJulianDay(String todate) {
      double JD = 0.0D;

      try {
         int dd = Integer.parseInt(todate.substring(0, 2));
         int mm = Integer.parseInt(todate.substring(2, 4));
         int yy = Integer.parseInt(todate.substring(4));
         int A = yy / 100;
         int B = A / 4;
         int C = 2 - A + B;
         double E = 365.25D * (double)(yy + 4716);
         double F = 30.6001D * (double)mm;
         JD = (double)(C + dd) + E + F - 1524.5D;
         return JD;
      } catch (Exception var13) {
         return JD;
      }
   }
}
