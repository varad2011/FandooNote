package com.bridgelabz.FundooNote.dto;

import java.time.LocalDateTime;

public class NoteDto {
	private long noteId;
	private String title;
	private String content;
	private LocalDateTime atCreated;
	private LocalDateTime atModified;
	
	public long getNoteId() {
		return noteId;
	}
	public void setNoteId(long id) {
		this.noteId = id;
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
	public LocalDateTime getAtCreated() {
		return atCreated;
	}
	public void setAtCreated(LocalDateTime atCreated) {
		this.atCreated = atCreated;
	}
	public LocalDateTime getAtModified() {
		return atModified;
	}
	public void setAtModified(LocalDateTime atModified) {
		this.atModified = atModified;
	}

	
}
