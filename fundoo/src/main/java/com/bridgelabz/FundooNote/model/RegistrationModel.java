package com.bridgelabz.FundooNote.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class RegistrationModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotBlank
	@Pattern(regexp = "^[a-z0-9_-]{2,15}$", message = " userName : not in range " )
	private String userName;
	
	private String lastName;
	
	@NotBlank
	@Pattern(regexp = "^[0][1-9]\\d{9}$|^[1-9]\\d{9}$", message = "MobileNumber : only number accept here")
	private String mobileNumber;

	@NotBlank
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = " emailId : enter valid email ID ")
	private String emailId;
	
	@NotBlank
	@Size(min = 8,max = 100, message = "password : enter password in range {min} and {max}")
 	private String password;
 	
 	@ManyToMany(cascade=CascadeType.ALL)
 	@JoinTable(name="emailJoin", joinColumns=@JoinColumn(name="id")
	, inverseJoinColumns=@JoinColumn(name="noteId")) 
 	@JsonIgnoreProperties(value = "registrationModel")
 	private List<Note> noteModel;
 	
 	private String profilePic;
 	
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public List<Note> getNoteModel() {
		return noteModel;
	}
	public void setNoteModel(List<Note> noteModel) {
		this.noteModel = noteModel;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
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
		return "RegistrationModel [id=" + id + ", userName=" + userName + ", lastName=" + lastName + ", mobileNumber="
				+ mobileNumber + ", emailId=" + emailId + ", password=" + password + "]";
	}
 	
}
