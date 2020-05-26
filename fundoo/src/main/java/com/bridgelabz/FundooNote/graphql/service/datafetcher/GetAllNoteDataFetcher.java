package com.bridgelabz.FundooNote.graphql.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.FundooNote.model.Note;
import com.bridgelabz.FundooNote.repository.NoteRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class GetAllNoteDataFetcher implements DataFetcher<List<Note>> {

	@Autowired
	NoteRepository noteRepository;
	
	@Override
	public List<Note> get(DataFetchingEnvironment environment) {
		List<Note> getAllNotes = noteRepository.findAll();
		return getAllNotes;
	}

}
