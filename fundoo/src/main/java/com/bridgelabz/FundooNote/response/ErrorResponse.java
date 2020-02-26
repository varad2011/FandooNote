package com.bridgelabz.FundooNote.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name =  "error")
public class ErrorResponse {

	private String msg;
	private List<String> details;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	public ErrorResponse(String msg, List<String> details) {
		super();
		this.msg = msg;
		this.details = details;
	}
	
}
