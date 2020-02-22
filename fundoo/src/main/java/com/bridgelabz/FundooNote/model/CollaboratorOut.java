package com.bridgelabz.FundooNote.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CollaboratorOut {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int colId;
	private int NoteId;
	private String colEmaiId;
	/*
	 * @ManyToMany
	 * 
	 * @JoinTable(name="ColJoin", joinColumns=@JoinColumn(name="colId") ,
	 * inverseJoinColumns=@JoinColumn(name="labelId"))
	 * 
	 * @JsonIgnoreProperties(value = "noteModel") private List<NoteModel> noteList;
	 */
	public int getColId() {
		return colId;
	}
	public void setColId(int colId) {
		this.colId = colId;
	}
	public int getNoteId() {
		return NoteId;
	}
	public void setNoteId(int noteId) {
		NoteId = noteId;
	}
	public String getColEmaiId() {
		return colEmaiId;
	}
	public void setColEmaiId(String colEmaiId) {
		this.colEmaiId = colEmaiId;
	}
	
}
