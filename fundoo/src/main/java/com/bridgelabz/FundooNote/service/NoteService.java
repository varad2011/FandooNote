package com.bridgelabz.FundooNote.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooNote.Util.TokenGeneratorDecoder;
import com.bridgelabz.FundooNote.dto.NoteDto;
import com.bridgelabz.FundooNote.model.NoteModel;
import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.repository.NoteRepository;
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
	
	//user availability check
	  public boolean checkUserAvailability(String token) {
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
	
	  //new note create
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
	
	//deleteNote
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

	//update create notes
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

	// display all user create Notes
	public List<NoteModel> displayNote(String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		
		if(registrationModel.isPresent()) {
			List<NoteModel> noteModel = noteRepsitory.findAll();
			List<NoteModel> noteModel1 = noteModel.stream().filter(t -> (t.getModel().getId())==(id)).collect(Collectors.toList());
			
			//System.out.println(noteModel);
			return noteModel1;
		}else {
			return null;
		}
	}

	//Sort note by list
	public List<NoteModel> displayUserNotes(String name, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		
		if(registrationModel.isPresent()) {
			List<NoteModel> noteModel = noteRepsitory.findAll();
			System.out.println(noteModel);
			List<NoteModel> noteModel1 = noteModel.stream().filter(t -> t.getTitle().equals(name)).sorted().collect(Collectors.toList());
			return noteModel1;
		}else {
			return  null;
		}
	}

	//store note to trash
	public Response storeToTrash(long noteId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<NoteModel> noteModel = noteRepsitory.findByNoteId((int) noteId);
		
		if(registrationModel.isPresent()) {
			
			if(noteModel.get().isPinUnpin()) {
				
				noteModel.get().setPinUnpin(false);
			}
			noteModel.get().setTrash(true);
			noteModel.get().setAtModified();
			noteRepsitory.save(noteModel.get());
			return new Response(200, "trash successfully", null);
		}else{
			return new Response(400, "not trush", null);
		}
	}

	//untrash note
	public Response trashToList(long noteId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<NoteModel> noteModel = noteRepsitory.findByNoteId((int) noteId);
		
		if(registrationModel.isPresent()) {
			noteModel.get().setTrash(false);
			noteModel.get().setAtModified();
			noteRepsitory.save(noteModel.get());
			return new Response(200, "trash to list store successfully", null);
		}else{
			return new Response(400, "not trush to list", null);
		}
		
	}
	//delete notes from trash permanent deleted data 
	public Response deleteFromTrash(int noteId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<NoteModel> noteModel = noteRepsitory.findByNoteId( noteId);
		
		if((registrationModel.isPresent()) && (noteModel.get().isTrash())) {
			noteRepsitory.deleteById(noteId);
		
			return new Response(200, "delete successfully from trash", null);
		}else {
			return new Response(400, "not delete from trush", null);
		}
	}

	//pin unpin  notes
	//if note is pin then unpin it or if note is unpin then pin it 
	public Response pinUnpin(int noteId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<NoteModel> noteModel = noteRepsitory.findByNoteId( noteId);
		if(registrationModel.isPresent()) {
			
			if(!noteModel.get().isPinUnpin()) {
				noteModel.get().setPinUnpin(true);
			}else {
				noteModel.get().setPinUnpin(false);
			}
			noteRepsitory.save(noteModel.get());
			
			return new Response(200, "note pin Status change", null);
		}else {
			return new Response(400, " not status change", null);
		}
	}

	//note added to archieve 
	public Response addToArchieve(int noteId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<NoteModel> noteModel = noteRepsitory.findByNoteId((int) noteId);
		
		if((registrationModel.isPresent())&& (!noteModel.get().isTrash())) {
			
			if(noteModel.get().isPinUnpin()) {
				noteModel.get().setPinUnpin(false);
				noteModel.get().setArchievePin(true);
			}
			noteModel.get().setArchieve(true);
			noteModel.get().setAtModified();
			noteRepsitory.save(noteModel.get());
			
			return new Response(200, "note added to archieve", null);	
		}else {
			return new Response(400, "not added to archieve", null);
		}
	}

	//note unArchieve 
	public Response unArchieveNote(int noteId, String token) {
		
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> registrationModel = registrationPagerepository.findById(id);
		Optional<NoteModel> noteModel = noteRepsitory.findByNoteId((int) noteId);
		
		if(registrationModel.isPresent())  {
			
			if(noteModel.get().isArchievePin()) {
				noteModel.get().setPinUnpin(true);
			}
			noteModel.get().setArchievePin(false);
			noteModel.get().setArchieve(false);
			noteRepsitory.save(noteModel.get());
			
			return new Response(200, " unArchieve successfully", null);
		}else {
			return new Response(200, "not  unArchieve", null);
		}
	}

	public boolean checkList(Optional<NoteModel> noteModel, String emailId) {
		boolean condition = false;
		for( int i = 0; i < noteModel.get().getRegistrationModel().size(); i++ ) {
			if((noteModel.get().getRegistrationModel().get(i).getEmailId()).equals(emailId)) {
				System.out.println("123 " +noteModel.get().getRegistrationModel().get(i).getEmailId());
				return true;
			}else {
				return false;
			}
		}	
		return condition;
	}
	//note collaborate
	public Response collaborateWithEmailId(int noteId, String emailId, String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> owner = registrationPagerepository.findById(id);
		Optional<NoteModel> noteModel = noteRepsitory.findByNoteId(noteId);
		Optional<RegistrationModel> user1 = registrationPagerepository.findByEmailId(emailId);
		System.out.println(owner);
		System.out.println(user1.toString());
		System.out.println(noteModel.get().getModel());
		System.out.println(noteModel.get().getRegistrationModel());
		System.out.println();
		System.out.println(((noteModel.get().getModel().getEmailId()).equalsIgnoreCase(emailId)));
		System.out.println((noteModel.get().getRegistrationModel()).equals(user1));
		
		NoteService noteService = new NoteService();
		boolean check = noteService.checkList(noteModel, emailId);
			//System.out.println("loss " +noteModel.get().getRegistrationModel().get(i).getEmailId());
		if(owner.isPresent()) {
			if(check) {
			
				return new Response(400, "user already collaborate", null);
			}else {
				noteModel.get().getRegistrationModel().add(user1.get());
			//	System.out.println(noteModel);
				noteRepsitory.save(noteModel.get());
				return new Response(200, "collabearator ", null);
			}
		}else {
			return new Response(400, "user is not available", null);
		}
	}
}