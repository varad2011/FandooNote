package com.bridgelabz.fundooNotes.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
public class NoteModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int userId;
	private String title;
	private String content;
	private LocalDateTime atCreatedDate;
	private LocalDateTime atModifiedDate;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
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
	
	public LocalDateTime getAtCreatedDate() {
		return atCreatedDate;
	}
	
	public void setAtCreatedDate() {
		this.atCreatedDate = atCreatedDate.now();
	}
	
	public LocalDateTime getAtModifiedDate() {
		return atModifiedDate;
	}
	
	public void setAtModified() {
		this.atModifiedDate = atModifiedDate.now();
	}

	@Override
	public String toString() {
		return "NoteModel [id=" + id + ", userId=" + userId +  ", title=" + title
				+ ", content=" + content + ", atCreated=" + atCreatedDate + ", atModified=" + atModifiedDate + "]";
	}
	
	
}
