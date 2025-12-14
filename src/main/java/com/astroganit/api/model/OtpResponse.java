package com.astroganit.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OtpResponse {
	@JsonProperty("Status")
    private String Status;
	@JsonProperty("Details")
    private String Details;

    // Getters & Setters
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }

    public String getDetails() {
        return Details;
    }
    public void setDetails(String details) {
        Details = details;
    }
}
