package com.astroganit.lib.panchang.util;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<String> {
	Map<String, Double> base;

	public ValueComparator(Map<String, Double> base) {
		this.base = base;
	}

	public int compare(String a, String b) {
		return (Double) this.base.get(a) >= (Double) this.base.get(b) ? 1 : -1;
	}
}
