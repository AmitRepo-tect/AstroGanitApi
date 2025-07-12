package com.astroganit.lib.panchang.model;

import java.io.Serializable;

public class RahuKaalBean implements Serializable {
	private String date;
	private String from;
	private String to;

	public RahuKaalBean(String date, String from, String to) {
		this.date = date;
		this.from = from;
		this.to = to;
	}

	public String getDate() {
		return date;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "RahuKaalBean{" + "date='" + date + '\'' + ", from='" + from + '\'' + ", to='" + to + '\'' + '}';
	}
}
