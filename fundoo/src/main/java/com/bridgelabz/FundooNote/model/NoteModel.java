package com.bridgelabz.FundooNote.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class NoteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int noteId;
	
	private String title;
	private String content;
	private LocalDateTime atCreated;
	private LocalDateTime atModified;
	private boolean trash;
	private boolean pinUnpin;
	private boolean archieve;
	private boolean archievePin; 
	
	public boolean isArchieve() {
		return archieve;
	}
	public void setArchieve(boolean archieve) {
		this.archieve = archieve;
	}
	public boolean isArchievePin() {
		return archievePin;
	}
	public void setArchievePin(boolean archievePin) {
		this.archievePin = archievePin;
	}
	public boolean isTrash() {
		return trash;
	}
	public void setTrash(boolean trash) {
		this.trash = trash;
	}
	public boolean isPinUnpin() {
		return pinUnpin;
	}
	public void setPinUnpin(boolean pinUnpin) {
		this.pinUnpin = pinUnpin;
	}

	@ManyToOne
	@JoinColumn(name = "id") 
	private RegistrationModel model;
	  
	@ManyToMany
	@JoinTable(name="LableJoin", joinColumns=@JoinColumn(name="noteId")
	, inverseJoinColumns=@JoinColumn(name="labelId")) 
	@JsonIgnoreProperties(value = "noteModel")
	private List<LabelsModel> labelModel;
	  
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public RegistrationModel getModel() {
		return model;
	}
	public void setModel(RegistrationModel model) {
		this.model = model;
	}
	public List<LabelsModel> getLabelModel() {
		return labelModel;
	}
	public void setLabelModel(List<LabelsModel> labelModel) {
		this.labelModel = labelModel;
	}
	public LocalDateTime getAtCreated() {
		return atCreated;
	}
	public void setAtCreated() {
		this.atCreated = atCreated.now();
	}
	public LocalDateTime getAtModified() {
		return atModified;
	}
	public void setAtModified() {
		this.atModified = atModified.now();
	}

	@Override
	public String toString() {
		return "NoteModel [noteId=" + noteId + ", title=" + title + ", content=" + content + ", atCreated=" + atCreated
				+ ", atModified=" + atModified + ", model=" + model + ", labelModel=" + labelModel + "]";
	}


	
}
