package com.bridgelabz.FundooNote.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.FundooNote.model.NoteModel;

@Repository
public interface NoteRepository extends JpaRepository<NoteModel, Integer>{

	Optional<NoteModel> findByNoteId(int noteId);


}
