package com.bridgelabz.FundooNote.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class LabelsModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int  labelId;
	private String labelName;
	@NotBlank
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
	@ManyToOne
	@JoinColumn(name = "id") 
	private RegistrationModel registrationModel;
	
	@ManyToMany()
	@JoinTable(name="LableJoin", joinColumns=@JoinColumn(name="labelId")
    , inverseJoinColumns=@JoinColumn(name="noteId"))  
	 @JsonIgnoreProperties(value = "labelModel")
	private List<Note> noteModel;

	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public RegistrationModel getRegistrationModel() {
		return registrationModel;
	}

	public void setRegistrationModel(RegistrationModel registrationModel) {
		this.registrationModel = registrationModel;
	}

	public List<Note> getNoteModel() {
		return noteModel;
	}

	public void setNoteModel(List<Note> noteModel) {
		this.noteModel = noteModel;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}


	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate() {
		this.createDate = createDate.now();
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate() {
		this.updateDate = updateDate.now();
	}



	@Override
	public String toString() {
		return "LabelsModel [labelId=" + labelId + ", labelName=" + labelName + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", registrationModel=" + registrationModel + ", noteModel=" + noteModel
				+ "]";
	}

	

	
}
