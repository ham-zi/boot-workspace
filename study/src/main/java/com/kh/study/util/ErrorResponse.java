package com.kh.study.util;

public class ErrorResponse {
	private int status;
	private String message;
	private Object data;
	// 간혹가다가 에러발생시간도 넣어주기도함
	public ErrorResponse(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ErrorResponse() {
		super();
	}
	@Override
	public String toString() {
		return "ErrorResponse [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
	
}
