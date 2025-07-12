package com.astroganit.lib.panchang.util;
import java.util.Comparator;

import com.astroganit.lib.panchang.model.FestivalDetail;

public class CustomCamparator implements Comparator<FestivalDetail> {
    @Override
    public int compare(FestivalDetail o1, FestivalDetail o2) {
        return Double.compare(o1.getFestDate(), o2.getFestDate());
    }
}
