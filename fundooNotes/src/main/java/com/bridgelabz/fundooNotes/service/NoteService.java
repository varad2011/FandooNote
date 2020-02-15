package com.bridgelabz.fundooNotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooNotes.dao.NoteDao;
import com.bridgelabz.fundooNotes.model.NoteModel;

@RestController
public class NoteService {

	@Autowired
	NoteDao noteDao;
	
	public void CreateNewNote(NoteModel noteModel) {
		noteDao.save(noteModel);
	}
	
	public void DeleteNoteModel(NoteModel noteModel) {
		noteDao.delete(noteModel);
	}
	
	public void updateNoteModel(NoteModel noteMOdel) {
		
	}
}
