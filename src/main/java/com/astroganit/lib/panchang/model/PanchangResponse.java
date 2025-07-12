package com.astroganit.lib.panchang.model;

import java.util.ArrayList;

public class PanchangResponse {
	SampurnPanchangModel sampurnPanchangModel;
	HoraResponse horaResponse;
	ChogdiyaResponse chogdiyaResponse;
	DoGhatiResponse doGhatiResponse;
	RahukaalResponse rahukaalResponse;

	public SampurnPanchangModel getSampurnPanchangModel() {
		return sampurnPanchangModel;
	}

	public void setSampurnPanchangModel(SampurnPanchangModel sampurnPanchangModel) {
		this.sampurnPanchangModel = sampurnPanchangModel;
	}

	public HoraResponse getHoraResponse() {
		return horaResponse;
	}

	public void setHoraResponse(HoraResponse horaResponse) {
		this.horaResponse = horaResponse;
	}

	public ChogdiyaResponse getChogdiyaResponse() {
		return chogdiyaResponse;
	}

	public void setChogdiyaResponse(ChogdiyaResponse chogdiyaResponse) {
		this.chogdiyaResponse = chogdiyaResponse;
	}

	public DoGhatiResponse getDoGhatiResponse() {
		return doGhatiResponse;
	}

	public void setDoGhatiResponse(DoGhatiResponse doGhatiResponse) {
		this.doGhatiResponse = doGhatiResponse;
	}

	public RahukaalResponse getRahukaalResponse() {
		return rahukaalResponse;
	}

	public void setRahukaalResponse(RahukaalResponse rahukaalResponse) {
		this.rahukaalResponse = rahukaalResponse;
	}

}
