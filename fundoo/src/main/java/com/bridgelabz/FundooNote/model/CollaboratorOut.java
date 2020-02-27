package com.bridgelabz.FundooNote.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class CollaboratorOut {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int colId;
	private String colEmaiId;
	
	@ManyToMany
	@JoinTable(name="colJoin", joinColumns=@JoinColumn(name="colId")
	, inverseJoinColumns=@JoinColumn(name="noteId")) 
	@JsonIgnoreProperties(value = "collaboratorOutsList")
	private List<Note> noteList;
	
	public List<Note> getNoteList() {
		return noteList;
	}
	public void setNoteList(List<Note> noteList) {
		this.noteList = noteList;
	}
	public int getColId() {
		return colId;
	}
	public void setColId(int colId) {
		this.colId = colId;
	}
	public String getColEmaiId() {
		return colEmaiId;
	}
	public void setColEmaiId(String colEmaiId) {
		this.colEmaiId = colEmaiId;
	}
	
}
