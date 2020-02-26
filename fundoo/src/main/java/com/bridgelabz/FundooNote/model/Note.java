package com.bridgelabz.FundooNote.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int noteId;
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	@NotBlank
	private LocalDateTime atCreated;
	private LocalDateTime atModified;
	private boolean trash;
	private boolean pinUnpin;
	private boolean archieve;
	private boolean archievePin; 
	private boolean noteReminder;
	private LocalDateTime reminderDatTime; 
	
	@ManyToOne
	@JoinColumn(name = "id") 
	private RegistrationModel model;
	 
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="emailJoin", joinColumns=@JoinColumn(name="noteId")
	, inverseJoinColumns=@JoinColumn(name="id")) 
	@JsonIgnoreProperties(value = "noteModel")
	private List<RegistrationModel> registrationModel;
	
	@ManyToMany
	@JoinTable(name="LableJoin", joinColumns=@JoinColumn(name="noteId")
	, inverseJoinColumns=@JoinColumn(name="labelId")) 
	@JsonIgnoreProperties(value = "noteModel")
	private List<LabelsModel> labelModel;
	  
	@ManyToMany
	@JoinTable(name="colJoin", joinColumns=@JoinColumn(name="noteId")
	, inverseJoinColumns=@JoinColumn(name="colId")) 
	@JsonIgnoreProperties(value = "noteList")
	private List<CollaboratorOut> collaboratorOutsList;
	
	public List<CollaboratorOut> getCollaboratorOutsList() {
		return collaboratorOutsList;
	}
	public void setCollaboratorOutsList(List<CollaboratorOut> collaboratorOutsList) {
		this.collaboratorOutsList = collaboratorOutsList;
	}
	public boolean isNoteReminder() {
		return noteReminder;
	}
	public void setNoteReminder(boolean noteReminder) {
		this.noteReminder = noteReminder;
	}
	public LocalDateTime getReminderDatTime() {
		return reminderDatTime;
	}
	public void setReminderDatTime(LocalDateTime reminderDatTime) {
		this.reminderDatTime = reminderDatTime;
	}
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

	public List<RegistrationModel> getRegistrationModel() {
		return registrationModel;
	}
	public void setRegistrationModel(List<RegistrationModel> registrationModel) {
		this.registrationModel = registrationModel;
	}
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
