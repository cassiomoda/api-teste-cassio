package com.moda.apitestecassio.exceptions;

public class ApiException extends Exception {

	private static final long serialVersionUID = 1L;

	private Integer status;

	public ApiException(String msg, Integer status) {

		super(msg);
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
