package com.bridgelabz.fundooNotes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooNotes.model.NoteModel;
@Repository
public interface NoteDao extends JpaRepository<NoteModel, Long> {
	
}
