package com.bridgelabz.FundooNote.dto;

import org.springframework.stereotype.Component;

public class LabelDto {

	private String labelName;

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	@Override
	public String toString() {
		return "LabelDto [labelName=" + labelName + "]";
	}
	

}
