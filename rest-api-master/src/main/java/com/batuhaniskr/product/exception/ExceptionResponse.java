package com.batuhaniskr.product.exception;

import java.util.Date;

public class ExceptionResponse {
	private Date timestamp;
	private String message;
	private String path;
	private int status;
	
	public ExceptionResponse(Date timestamp, String message, String path, int status) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.path = path;
		this.status = status;
	}

	public Date getTimestamp() {
		 return timestamp;
	 }

	public String getMessage() {
	    return message;
	}

	public String getPath() {
	   return path;
	}
	
	public int getStatus() {
		return status;
	}
	
}
