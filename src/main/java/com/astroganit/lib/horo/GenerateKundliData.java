package com.astroganit.lib.horo;

import org.json.JSONArray;
import org.json.JSONObject;

import com.astroganit.lib.horo.model.BirthDetailBean;
import com.astroganit.lib.horo.model.KundliBean;



public class GenerateKundliData {
    String lang = "0";

   
    public static DesktopHoroNew initializeHoraNew1(BirthDetailBean birthDetailBean) {
        DesktopHoroNew args1 = new DesktopHoroNew();

        try {
            args1.setBirthDetail(birthDetailBean);
            args1.initialize();

        } catch (Exception e) {

        }
        //VarshfalTEst.INSTANCE.calculateVarshfal(args1);
        return args1;
    }

  
    public static KundliBean getPlanets( BirthDetailBean birthDetailBean) {
        JSONArray jsonArray = new JSONArray();
        KundliBean kundliBean=new KundliBean();
        try {
            //InputStream inputStream = setPath(assetManager);
            DesktopHoroNew args1 = initializeHoraNew1(birthDetailBean);

            //DesktopHoro args1 = initializeHora(birthDetailBean);
            //inputStream.close();
            int[] planetArray = args1.getPositionForShodasvarg(0);
            int[] horaArray = args1.getPositionForShodasvarg(1);
            int[] drekkanaArray = args1.getPositionForShodasvarg(2);
            int[] chaturthamanshArray = args1.getPositionForShodasvarg(3);
            int[] saptamamshaArray = args1.getPositionForShodasvarg(4);
            int[] navmanshArray = args1.getPositionForShodasvarg(5);
            int[] dashamamshaArray = args1.getPositionForShodasvarg(6);
            int[] dwadashamamshaArray = args1.getPositionForShodasvarg(7);
            int[] shodashamshaArray = args1.getPositionForShodasvarg(8);
            int[] vimshamshaArray = args1.getPositionForShodasvarg(9);
            int[] chaturvimshamshaArray = args1.getPositionForShodasvarg(10);
            int[] saptavimshamshaArray = args1.getPositionForShodasvarg(11);
            int[] trimshamshaArray = args1.getPositionForShodasvarg(12);
            int[] khavedamshaArray = args1.getPositionForShodasvarg(13);
            int[] akshvedamshaArray = args1.getPositionForShodasvarg(14);
            int[] shashtiamshaArray = args1.getPositionForShodasvarg(15);
            int[] combustArr = getCombustArr(args1);
            int[] retrograteArr = getRetrograteArr(args1);
            double[] planetDegreeArray = {args1.getAsc(), args1.getSun(), args1.getMoon(), args1.getMars(),
                    args1.getMercury(), args1.getJupitor(), args1.getVenus(), args1.getSaturn(), args1.getRahu(),
                    args1.getKetu(), args1.getUranus(), args1.getNeptune(), args1.getPluto()};
            double[] kpCuspDegreeArray = {args1.getKPCuspLongitude(1), args1.getKPCuspLongitude(2),
                    args1.getKPCuspLongitude(3), args1.getKPCuspLongitude(4), args1.getKPCuspLongitude(5), args1.getKPCuspLongitude(6), args1.getKPCuspLongitude(7),
                    args1.getKPCuspLongitude(8), args1.getKPCuspLongitude(9), args1.getKPCuspLongitude(10), args1.getKPCuspLongitude(11), args1.getKPCuspLongitude(12)};
            
            int[] kpPlanetSignificationArray1 = args1.getKPPlanetSignification(1);
            int[] kpPlanetSignificationArray2 = args1.getKPPlanetSignification(2);
            int[] kpPlanetSignificationArray3 = args1.getKPPlanetSignification(3);
            int[] kpPlanetSignificationArray4 = args1.getKPPlanetSignification(4);
            int[] kpPlanetSignificationArray5 = args1.getKPPlanetSignification(5);
            int[] kpPlanetSignificationArray6 = args1.getKPPlanetSignification(6);
            int[] kpPlanetSignificationArray7 = args1.getKPPlanetSignification(7);
            int[] kpPlanetSignificationArray8 = args1.getKPPlanetSignification(8);
            int[] kpPlanetSignificationArray9 = args1.getKPPlanetSignification(9);
            args1.getTotalAshtakVargaValue();
            StringBuilder lagnaPlanetArray = new StringBuilder();
            StringBuilder combustStr = new StringBuilder();
            StringBuilder retrograteStr = new StringBuilder();
            StringBuilder navmanshPlanetArray = new StringBuilder();
            StringBuilder horaStr = new StringBuilder();
            StringBuilder drekkanaStr = new StringBuilder();
            StringBuilder chaturthamanshStr = new StringBuilder();
            StringBuilder saptamamshaStr = new StringBuilder();
            StringBuilder dashamamshaStr = new StringBuilder();
            StringBuilder dwadashamamshaStr = new StringBuilder();
            StringBuilder shodashamshaStr = new StringBuilder();
            StringBuilder vimshamshaStr = new StringBuilder();
            StringBuilder saptavimshamshaStr = new StringBuilder();
            StringBuilder chaturvimshamshaStr = new StringBuilder();
            StringBuilder trimshamshaStr = new StringBuilder();
            StringBuilder khavedamshaStr = new StringBuilder();
            StringBuilder akshvedamshaStr = new StringBuilder();
            StringBuilder shashtiamshaStr = new StringBuilder();
            StringBuilder planetDegreeStr = new StringBuilder();
            StringBuilder kpCuspDegreeStr = new StringBuilder();
            StringBuilder kpPlanetSignificationStr1 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr2 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr3 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr4 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr5 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr6 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr7 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr8 = new StringBuilder();
            StringBuilder kpPlanetSignificationStr9 = new StringBuilder();
            String[] ashtakvargaRashi = new String[12];

            for (int i = 0; i < planetArray.length; i++) {
                if (i == planetArray.length - 1) {
                    lagnaPlanetArray.append(planetArray[i]);
                    combustStr.append(combustArr[i]);
                    retrograteStr.append(retrograteArr[i]);
                    navmanshPlanetArray.append(navmanshArray[i]);
                    horaStr.append(horaArray[i]);
                    drekkanaStr.append(drekkanaArray[i]);
                    chaturthamanshStr.append(chaturthamanshArray[i]);
                    saptamamshaStr.append(saptamamshaArray[i]);
                    dashamamshaStr.append(dashamamshaArray[i]);

                    dwadashamamshaStr.append(dwadashamamshaArray[i]);
                    shodashamshaStr.append(shodashamshaArray[i]);
                    vimshamshaStr.append(vimshamshaArray[i]);
                    saptavimshamshaStr.append(saptavimshamshaArray[i]);
                    chaturvimshamshaStr.append(chaturvimshamshaArray[i]);
                    trimshamshaStr.append(trimshamshaArray[i]);
                    khavedamshaStr.append(khavedamshaArray[i]);
                    akshvedamshaStr.append(akshvedamshaArray[i]);
                    shashtiamshaStr.append(shashtiamshaArray[i]);
                    planetDegreeStr.append(planetDegreeArray[i]);

                } else {
                    lagnaPlanetArray.append(planetArray[i]).append(",");
                    combustStr.append(combustArr[i]).append(",");
                    retrograteStr.append(retrograteArr[i]).append(",");
                    navmanshPlanetArray.append(navmanshArray[i]).append(",");
                    horaStr.append(horaArray[i]).append(",");
                    drekkanaStr.append(drekkanaArray[i]).append(",");
                    chaturthamanshStr.append(chaturthamanshArray[i]).append(",");
                    saptamamshaStr.append(saptamamshaArray[i]).append(",");
                    dashamamshaStr.append(dashamamshaArray[i]).append(",");
                    dwadashamamshaStr.append(dwadashamamshaArray[i]).append(",");
                    shodashamshaStr.append(shodashamshaArray[i]).append(",");
                    vimshamshaStr.append(vimshamshaArray[i]).append(",");
                    saptavimshamshaStr.append(saptavimshamshaArray[i]).append(",");
                    chaturvimshamshaStr.append(chaturvimshamshaArray[i]).append(",");
                    trimshamshaStr.append(trimshamshaArray[i]).append(",");
                    khavedamshaStr.append(khavedamshaArray[i]).append(",");
                    akshvedamshaStr.append(akshvedamshaArray[i]).append(",");
                    shashtiamshaStr.append(shashtiamshaArray[i]).append(",");
                    planetDegreeStr.append(planetDegreeArray[i]).append(",");
                }
            }
            for (int i = 0; i < kpCuspDegreeArray.length; i++) {
                if (i == kpCuspDegreeArray.length - 1) {
                    kpCuspDegreeStr.append(kpCuspDegreeArray[i]);
                } else {
                    kpCuspDegreeStr.append(kpCuspDegreeArray[i]).append(",");
                }
            }
            for (int i = 0; i < kpPlanetSignificationArray1.length; i++) {
                if (i == kpPlanetSignificationArray1.length - 1) {
                    kpPlanetSignificationStr1.append(kpPlanetSignificationArray1[i]);
                    kpPlanetSignificationStr2.append(kpPlanetSignificationArray2[i]);
                    kpPlanetSignificationStr3.append(kpPlanetSignificationArray3[i]);
                    kpPlanetSignificationStr4.append(kpPlanetSignificationArray4[i]);
                    kpPlanetSignificationStr5.append(kpPlanetSignificationArray5[i]);
                    kpPlanetSignificationStr6.append(kpPlanetSignificationArray6[i]);
                    kpPlanetSignificationStr7.append(kpPlanetSignificationArray7[i]);
                    kpPlanetSignificationStr8.append(kpPlanetSignificationArray8[i]);
                    kpPlanetSignificationStr9.append(kpPlanetSignificationArray9[i]);
                } else {
                    kpPlanetSignificationStr1.append(kpPlanetSignificationArray1[i]).append(",");
                    kpPlanetSignificationStr2.append(kpPlanetSignificationArray2[i]).append(",");
                    kpPlanetSignificationStr3.append(kpPlanetSignificationArray3[i]).append(",");
                    kpPlanetSignificationStr4.append(kpPlanetSignificationArray4[i]).append(",");
                    kpPlanetSignificationStr5.append(kpPlanetSignificationArray5[i]).append(",");
                    kpPlanetSignificationStr6.append(kpPlanetSignificationArray6[i]).append(",");
                    kpPlanetSignificationStr7.append(kpPlanetSignificationArray7[i]).append(",");
                    kpPlanetSignificationStr8.append(kpPlanetSignificationArray8[i]).append(",");
                    kpPlanetSignificationStr9.append(kpPlanetSignificationArray9[i]).append(",");

                }
            }
            for (int i = 0; i < 12; i++) {
                StringBuilder ashatakVarga = new StringBuilder();
                for (int j = 0; j < 7; j++) {
                    ashatakVarga.append(args1.getAshtakvargaBinduForSignAndPlanet(j, i)).append(",");
                }
                ashtakvargaRashi[i] = ashatakVarga.toString();

            }

            JSONObject jsonObject = new JSONObject();
            kundliBean.setLagna(lagnaPlanetArray.toString());
            jsonObject.put("lagna", lagnaPlanetArray.toString());
            kundliBean.setCombust(combustStr.toString());
            jsonObject.put("combust", combustStr);
            kundliBean.setRetrograte(retrograteStr.toString());
            jsonObject.put("retrograte", retrograteStr);
            kundliBean.setNavmansh(navmanshPlanetArray.toString());
            jsonObject.put("navmansh", navmanshPlanetArray.toString());
            kundliBean.setHora(horaStr.toString());
            jsonObject.put("hora", horaStr.toString());
            kundliBean.setDrekkana(drekkanaStr.toString());
            jsonObject.put("drekkana", drekkanaStr.toString());
            kundliBean.setChaturthamansh(chaturthamanshStr.toString());
            jsonObject.put("chaturthamansh", chaturthamanshStr.toString());
            kundliBean.setSaptamamsha(saptamamshaStr.toString());
            jsonObject.put("saptamamsha", saptamamshaStr.toString());
            kundliBean.setDashamamsha(dashamamshaStr.toString());
            jsonObject.put("dashamamsha", dashamamshaStr.toString());
            kundliBean.setDwadashamamsha(dwadashamamshaStr.toString());
            jsonObject.put("dwadashamamsha", dwadashamamshaStr.toString());
            kundliBean.setShodashamsha(shodashamshaStr.toString());
            jsonObject.put("shodashamsha", shodashamshaStr.toString());
            kundliBean.setVimshamsha(vimshamshaStr.toString());
            jsonObject.put("vimshamsha", vimshamshaStr.toString());
            kundliBean.setSaptavimshamsha(saptavimshamshaStr.toString());
            jsonObject.put("saptavimshamsha", saptavimshamshaStr.toString());
            kundliBean.setChaturvimshamsha(chaturvimshamshaStr.toString());
            jsonObject.put("chaturvimshamsha", chaturvimshamshaStr.toString());
            jsonObject.put("trimshamsha", trimshamshaStr.toString());
            kundliBean.setTrimshamsha(trimshamshaStr.toString());
            jsonObject.put("khavedamsha", khavedamshaStr.toString());
            kundliBean.setKhavedamsha(khavedamshaStr.toString());
            jsonObject.put("akshvedamsha", akshvedamshaStr.toString());
            kundliBean.setAkshvedamsha(akshvedamshaStr.toString());
            jsonObject.put("shashtiamsha", shashtiamshaStr.toString());
            kundliBean.setShashtiamsha(shashtiamshaStr.toString());
            jsonObject.put("planetDegree", planetDegreeStr.toString());
            kundliBean.setPlanetDegree(planetDegreeStr.toString());
            jsonObject.put("kpCusp", kpCuspDegreeStr.toString());
            kundliBean.setKpCusp(kpCuspDegreeStr.toString());
            jsonObject.put("kpayan", args1.getKPAyanamsaLongitude());
            kundliBean.setKpayan(String.valueOf(args1.getKPAyanamsaLongitude()));
            jsonObject.put("fortuna", args1.getKPFortunaLongitude());
            kundliBean.setFortuna(String.valueOf(args1.getKPFortunaLongitude()));
            jsonObject.put("RPDayLord", args1.getKPDayLordName());
            kundliBean.setRPDayLord( args1.getKPDayLordName());
            jsonObject.put("planetsignification1", kpPlanetSignificationStr1.toString());
            kundliBean.setPlanetSignification1(kpPlanetSignificationStr1.toString());
            jsonObject.put("planetsignification2", kpPlanetSignificationStr2.toString());
            kundliBean.setPlanetSignification2(kpPlanetSignificationStr2.toString());
            jsonObject.put("planetsignification3", kpPlanetSignificationStr3.toString());
            kundliBean.setPlanetSignification3(kpPlanetSignificationStr3.toString());
            jsonObject.put("planetsignification4", kpPlanetSignificationStr4.toString());
            kundliBean.setPlanetSignification4(kpPlanetSignificationStr4.toString());
            jsonObject.put("planetsignification5", kpPlanetSignificationStr5.toString());
            kundliBean.setPlanetSignification5(kpPlanetSignificationStr5.toString());
            jsonObject.put("planetsignification6", kpPlanetSignificationStr6.toString());
            kundliBean.setPlanetSignification6(kpPlanetSignificationStr6.toString());
            jsonObject.put("planetsignification7", kpPlanetSignificationStr7.toString());
            kundliBean.setPlanetSignification7(kpPlanetSignificationStr7.toString());
            jsonObject.put("planetsignification8", kpPlanetSignificationStr8.toString());
            kundliBean.setPlanetSignification8(kpPlanetSignificationStr8.toString());
            jsonObject.put("planetsignification9", kpPlanetSignificationStr9.toString());
            kundliBean.setPlanetSignification9(kpPlanetSignificationStr9.toString());
            jsonObject.put("ashtakvargar1", ashtakvargaRashi[0]);
            kundliBean.setAshtakvargar1(ashtakvargaRashi[0]);
            jsonObject.put("ashtakvargar2", ashtakvargaRashi[1]);
            kundliBean.setAshtakvargar2(ashtakvargaRashi[1]);
            jsonObject.put("ashtakvargar3", ashtakvargaRashi[2]);
            kundliBean.setAshtakvargar3(ashtakvargaRashi[2]);
            jsonObject.put("ashtakvargar4", ashtakvargaRashi[3]);
            kundliBean.setAshtakvargar4(ashtakvargaRashi[3]);
            jsonObject.put("ashtakvargar5", ashtakvargaRashi[4]);
            kundliBean.setAshtakvargar5(ashtakvargaRashi[4]);
            jsonObject.put("ashtakvargar6", ashtakvargaRashi[5]);
            kundliBean.setAshtakvargar6(ashtakvargaRashi[5]);
            jsonObject.put("ashtakvargar7", ashtakvargaRashi[6]);
            kundliBean.setAshtakvargar7(ashtakvargaRashi[6]);
            jsonObject.put("ashtakvargar8", ashtakvargaRashi[7]);
            kundliBean.setAshtakvargar8(ashtakvargaRashi[7]);
            jsonObject.put("ashtakvargar9", ashtakvargaRashi[8]);
            kundliBean.setAshtakvargar9(ashtakvargaRashi[8]);
            jsonObject.put("ashtakvargar10", ashtakvargaRashi[9]);
            kundliBean.setAshtakvargar10(ashtakvargaRashi[9]);
            jsonObject.put("ashtakvargar11", ashtakvargaRashi[10]);
            kundliBean.setAshtakvargar11(ashtakvargaRashi[10]);
            jsonObject.put("ashtakvargar12", ashtakvargaRashi[11]);
            kundliBean.setAshtakvargar12(ashtakvargaRashi[11]);
            jsonObject.put("ayan", args1.getAyanamsa());
            kundliBean.setAyan(String.valueOf(args1.getAyanamsa()));
            jsonObject.put("paksha", args1.getPakshaName());
            kundliBean.setPaksha(args1.getPakshaName());
            jsonObject.put("tithi", args1.getTithiName());
            kundliBean.setTithi(args1.getTithiName());
            jsonObject.put("nakshatra", args1.getNakshatraName());
            kundliBean.setNakshatra(args1.getNakshatraName());
            jsonObject.put("hinduWeekDay", args1.getHinduWeekdayName());
            kundliBean.setHinduWeekDay(args1.getHinduWeekdayName());
            jsonObject.put("englishWeekDay", args1.getHinduWeekdayName());
            kundliBean.setEnglishWeekDay(args1.getHinduWeekdayName());
            jsonObject.put("yoga", args1.getYoganame());
            kundliBean.setYoga(args1.getYoganame());
            jsonObject.put("karan", args1.getKaranName());
            kundliBean.setKaran(args1.getKaranName());
            jsonObject.put("sunRiseTime", args1.getSunRiseTimeHms());
            kundliBean.setSunRiseTime(args1.getSunRiseTimeHms());
            jsonObject.put("sunSetTime", args1.getSunSetTimeHms());
            kundliBean.setSunSetTime(args1.getSunSetTimeHms());
            JSONArray prastharashtakvargaJsonArray = new JSONArray();
            String[] bindu = {"SU", "MO", "MA", "ME", "JU", "VE", "SA", "AS"};
            String[] plaNo = {"SUN", "MOON", "MARS", "MERCURY", "JUPITER", "VENUS", "SATURN", "RAHU"};
            for (int i = 1; i <= 8; i++) {
                JSONObject jsonObject1 = new JSONObject();
                for (int j = 1; j <= bindu.length; j++) {
                    StringBuilder str = new StringBuilder();
                    for (int k = 1; k < 13; k++) {
                        str.append(args1.getPrastharashtakvargaTables(i, j, k)).append(",");
                    }
                    jsonObject1.put(bindu[j - 1], str);
                }
                prastharashtakvargaJsonArray.put(jsonObject1);
            }
            jsonObject.put("prastharashtakvarga", prastharashtakvargaJsonArray);

            jsonObject.put("paya", args1.getPayaName());
            kundliBean.setPaya(args1.getPayaName());
            jsonObject.put("varna", args1.getVarnaName());
            kundliBean.setVarna(args1.getVarnaName());
            jsonObject.put("yoni", args1.getYoniName());
            kundliBean.setYoni(args1.getYoniName());
            jsonObject.put("gana", args1.getGanaName());
            kundliBean.setGana(args1.getGanaName());
            jsonObject.put("vasya", args1.getVasyaName());
            kundliBean.setVasya(args1.getVasyaName());
            jsonObject.put("nadi", args1.getNadiName());
            kundliBean.setNadi(args1.getNadiName());
            jsonObject.put("balanceOfDasha", args1.getBalanceOfDasaString());
            kundliBean.setBalanceOfDasha(args1.getBalanceOfDasaString());
            jsonObject.put("lagnaA", args1.getLagnaSign());
            kundliBean.setLagnaA(args1.getLagnaSign());
            jsonObject.put("lagnaLord", args1.getLagnaLordName());
            kundliBean.setLagnaLord(args1.getLagnaLordName());
            jsonObject.put("rasi", args1.getRasiName());
            kundliBean.setRasi(args1.getRasiName());
            jsonObject.put("rasiLord", args1.getRasiLordName());
            kundliBean.setRasiLord(args1.getRasiLordName());
            jsonObject.put("nakshatraPada", args1.getNakshatraName());
            kundliBean.setNakshatraPada(args1.getNakshatraName());
            jsonObject.put("nakshatraLord", args1.getNakshatraLordName());
            kundliBean.setNakshatraLord(args1.getNakshatraLordName());
            jsonObject.put("julianDay", args1.getJulianDayValue());
            kundliBean.setJulianDay(args1.getJulianDayValue());
            jsonObject.put("sunSignIndian", args1.getIndianSunSignName());
            kundliBean.setSunSignIndian(args1.getIndianSunSignName());
            jsonObject.put("sunSignWestern", args1.getSunSignName());
            kundliBean.setSunSignWestern(args1.getSunSignName());
            jsonObject.put("ayanamsa", args1.getAyanamsaDms("0"));
            kundliBean.setAyanamsa(args1.getAyanamsaDms("0"));
            jsonObject.put("ayanamsaName", args1.getAyanamsa());
            kundliBean.setAyanamsaName(String.valueOf(args1.getAyanamsa()));
            jsonObject.put("obliquity", args1.getObliquityDms("0"));
            kundliBean.setObliquity(args1.getObliquityDms("0"));
            jsonObject.put("sideralTime", args1.getSiderealTimeHms());
            kundliBean.setSideralTime(args1.getSiderealTimeHms());
            jsonArray.put(jsonObject);
            
        } catch (Exception var1) {
            var1.printStackTrace();
        }
        //return jsonArray.toString();
    return kundliBean;
    }

    public static double[] getPlanetForVarshfal(BirthDetailBean birthDetailBean) {
        double[] planetDegreeArray = new double[13];
        try {
            DesktopHoroNew args1 = initializeHoraNew1(birthDetailBean);
            planetDegreeArray = new double[]{args1.getAsc(), args1.getSun(), args1.getMoon(), args1.getMars(),
                    args1.getMercury(), args1.getJupitor(), args1.getVenus(), args1.getSaturn(), args1.getRahu(),
                    args1.getKetu(), args1.getUranus(), args1.getNeptune(), args1.getPluto()};
        } catch (Exception e) {

        }
        return planetDegreeArray;
    }

  

    public static int getAsc(BirthDetailBean birthDetailBean) {
        DesktopHoroNew args1 = initializeHoraNew1(birthDetailBean);
        return (int) (args1.getAsc() / 30.00);
    }

    public static int getNakshatra(BirthDetailBean birthDetailBean) {
        DesktopHoroNew args1 = initializeHoraNew1(birthDetailBean);
        return args1.getNakshatra();
    }

    public int[] calculateTransit(BirthDetailBean birthDetailBean) {
        DesktopHoroNew args1 = initializeHoraNew1(birthDetailBean);
        return args1.getPositionForShodasvarg(0);
    }

    public static DesktopHoroNew getDesktopHoro(BirthDetailBean birthDetailBean) {
        return initializeHoraNew1(birthDetailBean);
    }

    public static int[] getCombustArr(DesktopHoroNew desktopHoro) {
        int[] combustArr = new int[13];
        for (int i = 0; i <= 12; i++) {
            if (desktopHoro.isCombust(i)) {
                combustArr[i] = 1;
            } else {
                combustArr[i] = 0;
            }
        }
        return combustArr;
    }

    public static int[] getRetrograteArr(DesktopHoroNew desktopHoro) {
        int[] retrograteArr = new int[13];
        for (int i = 1; i <= 12; i++) {
            if (!desktopHoro.isPlanetDirect(i)) {
                retrograteArr[i] = 1;
            } else {
                retrograteArr[i] = 0;
            }
        }

        return retrograteArr;
    }
}
