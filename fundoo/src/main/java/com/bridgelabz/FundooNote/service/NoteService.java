package com.bridgelabz.FundooNote.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooNote.Util.TokenGeneratorDecoder;
import com.bridgelabz.FundooNote.dto.NoteDto;
import com.bridgelabz.FundooNote.model.NoteModel;
import com.bridgelabz.FundooNote.model.RecycleBinModel;
import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.repository.NoteRepository;
import com.bridgelabz.FundooNote.repository.RecycleBinRepository;
import com.bridgelabz.FundooNote.repository.RegistrationPageRepository;
import com.bridgelabz.FundooNote.response.Response;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepsitory;
	
	@Autowired
	private TokenGeneratorDecoder tokenDecoder;
	
	@Autowired
	private RegistrationPageRepository registrationPagerepository;
	/*
	 * 
	 * @Autowired private RecycleBinRepository recyclebinRepository;
	 * 
	 * @Autowired private RecycleBinModel recycleBinModel;
	 */
	  
	  public boolean checkUserAvailabilty(String token) {
		  System.out.println(token);
			String userId = tokenDecoder.decodeToken(token);
			System.out.println(userId);
			long id = Long.parseLong(userId);
			Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
			
			if(registrationModel.isPresent()) {
				return true;
			}else {
				return false;
			}
	  }
	
	public Response createNewNote(NoteModel model, String token) {
		
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		
		try {
			Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
			
			if(registrationModel.isPresent()) {
				model.setModel(registrationModel.get());
				model.setAtCreated();
				noteRepsitory.save(model);
				
				return new Response(200,"saveNote",token); 
			}else {
				return new Response(400, "envalid token", token);
			}
		}catch(Exception e) {
			return new Response(500, "e", token);
		}
	}
	
	public Response deleteNote(NoteDto model, String token) {
		
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		long noteId = model.getNoteId();
		
		try {
			
			Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		
			if(registrationModel.isPresent()) {
				noteRepsitory.deleteById((int) noteId);
				
				return new Response(200,"deleteNote",token); 
			}else {
				
				return new Response(400, "envalid token", token);
			}
		}catch(Exception e) {
			return new Response(500, "e", token);
		}
	}

	public Response updateNote(NoteDto model, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		long noteId = model.getNoteId();
		
		try {
			Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
			Optional<NoteModel> noteModel = noteRepsitory.findById((int) noteId);
			
			if(registrationModel.isPresent()) {
				noteModel.get().setTitle(model.getTitle());
				noteModel.get().setContent(model.getContent());
				noteModel.get().setAtModified();
				noteRepsitory.save(noteModel.get());
				
				return new Response(200,"update successfully",token); 
			}else {
				
				return new Response(400, "envalid token", token);
			}
		}catch(Exception e) {
			return new Response(500, "e", token);
		}
	}

	public List<NoteModel> Displaydata(String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		
		if(registrationModel.isPresent()) {
			List<NoteModel> noteModel = noteRepsitory.findAll();
			return noteModel ;
		}else {
			return null;
		}
		
	}
	
	
}
