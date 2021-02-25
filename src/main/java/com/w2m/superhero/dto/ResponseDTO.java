package com.w2m.superhero.dto;

import com.google.gson.Gson;
import com.w2m.superhero.enums.StatusCode;

public class ResponseDTO {

	private Integer statusCode;
	private String description;
	
	public ResponseDTO(Integer statusCode, String description) {
		super();
		this.statusCode = statusCode;
		this.description = description;
	}
	
	public ResponseDTO(StatusCode statusCode) {
		super();
		this.statusCode = statusCode.getStatusCode();
		this.description = statusCode.getDescription();
	}
	
	public ResponseDTO(StatusCode statusCode, Object args) {
		super();
		this.statusCode = statusCode.getStatusCode();
		this.description = statusCode.getDescription().concat(args.toString());
	}
	
	public Integer getStatusCode() {
		return statusCode;
	}
	
	public String getDescription() {
		return description;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseDTO other = (ResponseDTO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
