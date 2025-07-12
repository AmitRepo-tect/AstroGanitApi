package com.astroganit.api.model.muhurat;

import java.util.List;

public class MonthlyMuhuratModel {
	private String heading;
    private List<MuhuratModel> muhurat;
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public List<MuhuratModel> getMuhurat() {
		return muhurat;
	}
	public void setMuhurat(List<MuhuratModel> muhurat) {
		this.muhurat = muhurat;
	}
    
}
