package com.bridgelabz.FundooNote.graphql.service.datafetcher;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.FundooNote.model.Note;
import com.bridgelabz.FundooNote.repository.NoteRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class GetNoteDataFetcher implements DataFetcher<Note> {

	@Autowired
	NoteRepository noteRepository;
	
	@Override
	public Note get(DataFetchingEnvironment environment) {
		 int noteId = environment.getArgument("noteId");
		Optional<Note> getNote = noteRepository.findById(noteId);
		return getNote.get();
	}

}
