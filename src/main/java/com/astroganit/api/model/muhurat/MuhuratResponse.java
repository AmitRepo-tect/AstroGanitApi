package com.astroganit.api.model.muhurat;

import java.util.List;

public class MuhuratResponse {
	List<MonthlyMuhuratModel> list;

	public List<MonthlyMuhuratModel> getMuhuratDetail() {
		return list;
	}

	public void setMuhuratDetail(List<MonthlyMuhuratModel> list) {
		this.list = list;
	}
}
