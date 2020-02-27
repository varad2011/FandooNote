package com.bridgelabz.FundooNote.dto;


public class UserLoginDto {

	private String emailId;
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

