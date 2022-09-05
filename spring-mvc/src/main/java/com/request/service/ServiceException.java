package com.request.service;

import com.util.PayloadStatusEnum;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	private final boolean formatted;
	private final Integer errorCode;

	public ServiceException() {
		super();
		formatted = false;
		this.errorCode = PayloadStatusEnum.FAIL.getValue();
	}

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		formatted = false;
		this.errorCode = PayloadStatusEnum.FAIL.getValue();
	}

	public ServiceException(boolean hasFormattedMessage, String arg0) {
		super(arg0);
		formatted = hasFormattedMessage;
		this.errorCode = PayloadStatusEnum.FAIL.getValue();
	}

	public ServiceException(String arg0) {
		super(arg0);
		formatted = false;
		this.errorCode = PayloadStatusEnum.FAIL.getValue();
	}

	public ServiceException(boolean hasFormattedMessage, Integer errorCode, String arg0) {
		super(arg0);
		formatted = hasFormattedMessage;
		this.errorCode = errorCode;
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
		formatted = false;
		this.errorCode = PayloadStatusEnum.FAIL.getValue();
	}

	public ServiceException(Integer errorCode, Throwable arg0) {
		super(arg0);
		formatted = false;
		this.errorCode = errorCode;
	}

	public boolean isFormatted() {
		return formatted;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

}