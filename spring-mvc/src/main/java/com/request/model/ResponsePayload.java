package com.request.model;

public class ResponsePayload {

	boolean success;
	String msg;
	int status;
	Object result;
	int totalAvailableRecords;
	static String broadcast;
	Object header;

	public ResponsePayload() {

	}

	public ResponsePayload(String msg) {
		this.success = true;
		this.status = 200;
		this.msg = msg;
		this.result = "";
	}

	public ResponsePayload(int code, String msg) {
		this.success = false;
		this.status = code;
		this.msg = msg;
		this.result = "";
	}

	public ResponsePayload(int code, String msg, Object result) {
		this.success = false;
		this.status = code;
		this.msg = msg;
		this.result = result;
	}

	public ResponsePayload(String msg, Object result) {
		this.success = true;
		this.status = 200;
		this.msg = msg;
		this.result = result;
	}

	public ResponsePayload(String msg, Object result, Object header, int code, int totalAvailableRecords) {
		this.header = header;
		this.success = true;
		this.status = code;
		this.msg = msg;
		this.result = result;
		this.totalAvailableRecords = totalAvailableRecords;
	}

	public ResponsePayload(String msg, int code, Object header) {
		this.header = header;
		this.success = false;
		this.status = code;
		this.msg = msg;
		this.result = null;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public int getTotalAvailableRecords() {
		return totalAvailableRecords;
	}

	public void setTotalAvailableRecords(int totalAvailableRecords) {
		this.totalAvailableRecords = totalAvailableRecords;
	}

	public String getBroadcast() {
		return ResponsePayload.broadcast;
	}

	public void setBroadcast(String broadcast) {
		ResponsePayload.broadcast = broadcast;
	}

	public Object getHeader() {
		return header;
	}

	public void setHeader(Object header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "ResponsePayload [success=" + success + ", msg=" + msg + ", status=" + status + ", result=" + result
				+ ", totalAvailableRecords=" + totalAvailableRecords + ", header=" + header + "]";
	}

	
}
