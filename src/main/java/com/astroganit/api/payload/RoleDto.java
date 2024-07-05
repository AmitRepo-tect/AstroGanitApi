package com.astroganit.api.payload;

public class RoleDto {
	private int id;
	private String name;

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean equals(final Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof RoleDto)) {
			return false;
		} else {
			RoleDto other = (RoleDto) o;
			if (!other.canEqual(this)) {
				return false;
			} else if (this.getId() != other.getId()) {
				return false;
			} else {
				Object this$name = this.getName();
				Object other$name = other.getName();
				if (this$name == null) {
					if (other$name != null) {
						return false;
					}
				} else if (!this$name.equals(other$name)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(final Object other) {
		return other instanceof RoleDto;
	}

	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		result = result * 59 + this.getId();
		Object $name = this.getName();
		result = result * 59 + ($name == null ? 43 : $name.hashCode());
		return result;
	}

	public String toString() {
		return "RoleDto(id=" + this.getId() + ", name=" + this.getName() + ")";
	}
}
