package com.request.dao;

import com.util.PayloadStatusEnum;

public class DaoException extends Exception{

	private static final long serialVersionUID = -7567796558709016922L;

	boolean formatted;
	private final Integer errorCode;

	public DaoException() {
		super();
		this.errorCode = PayloadStatusEnum.FAIL.getValue();
	}

	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		this.errorCode = PayloadStatusEnum.FAIL.getValue();
	}

	public DaoException(boolean hasFormattedMessage, String arg0) {
		super(arg0);

		this.formatted = hasFormattedMessage;
		this.errorCode = PayloadStatusEnum.FAIL.getValue();
	}

	public DaoException(String arg0) {
		super(arg0);
		this.errorCode = PayloadStatusEnum.FAIL.getValue();
	}

	public DaoException(Throwable arg0) {
		super(arg0);
		this.errorCode = PayloadStatusEnum.FAIL.getValue();
	}

	public DaoException(boolean hasFormattedMessage, String arg0, int code) {
		super(arg0);
		this.formatted = hasFormattedMessage;
		this.errorCode = code;
	}

	public boolean isFormatted() {
		return formatted;
	}

	public void setFormatted(boolean formatted) {
		this.formatted = formatted;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

}
