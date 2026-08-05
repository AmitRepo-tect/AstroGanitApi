package com.astroganit.api.payload;

import java.util.Date;

public class DeleteAccountResponse {

	private boolean deleted;
	private Date restoreUntil;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getRestoreUntil() {
		return restoreUntil;
	}

	public void setRestoreUntil(Date restoreUntil) {
		this.restoreUntil = restoreUntil;
	}
}