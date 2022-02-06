package com.mardeveloper.wine.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GNT")
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private Map<String, String> details;
	private String paht;
	
	public StandardError() {		
	}

	public StandardError(Instant timestamp, Integer status, String error, String message, Map<String, String> details, String paht) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.details = details;
		this.paht = paht;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	public String getPaht() {
		return paht;
	}

	public void setPaht(String paht) {
		this.paht = paht;
	}
	
	
}
