package com.bridgelabz.FundooNote.dto;

import javax.validation.constraints.NotEmpty;

public class ForgetPasswordDto {

	@NotEmpty(message = "not blank")
	private String emailId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}

