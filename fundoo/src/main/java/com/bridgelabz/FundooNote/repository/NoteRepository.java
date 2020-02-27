package com.bridgelabz.FundooNote.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.FundooNote.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{

	Optional<Note> findByNoteId(int noteId);
	
}
