package com.bridgelabz.FundooNote.response;

public class Response {
	private long status;
	private String message;
	private Object data;
	
	Response(){
	}

	public Response(long status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + ", data=" + data + "]";
	}

	public long getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
	
	
	
}
