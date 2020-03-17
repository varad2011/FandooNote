package com.bridgelabz.FundooNote.dto;

import javax.validation.constraints.NotEmpty;

public class UserLoginDto {

	@NotEmpty(message = " emailId: not empty")
	private String emailId;
	@NotEmpty(message = " password: not empty")
 	private String password;
 	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserLoginDto [emailId=" + emailId + ", password=" + password + "]";
	}
 	
}

