package com.astroganit.api.payload;

public class JwtAuthRequest {
	private String username;
	private String password;

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof JwtAuthRequest)) {
			return false;
		} else {
			JwtAuthRequest other = (JwtAuthRequest) o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$username = this.getUsername();
				Object other$username = other.getUsername();
				if (this$username == null) {
					if (other$username != null) {
						return false;
					}
				} else if (!this$username.equals(other$username)) {
					return false;
				}

				Object this$password = this.getPassword();
				Object other$password = other.getPassword();
				if (this$password == null) {
					if (other$password != null) {
						return false;
					}
				} else if (!this$password.equals(other$password)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof JwtAuthRequest;
	}

	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		Object $username = this.getUsername();
		result = result * 59 + ($username == null ? 43 : $username.hashCode());
		Object $password = this.getPassword();
		result = result * 59 + ($password == null ? 43 : $password.hashCode());
		return result;
	}

	public String toString() {
		return "JwtAuthRequest(username=" + this.getUsername() + ", password=" + this.getPassword() + ")";
	}
}
