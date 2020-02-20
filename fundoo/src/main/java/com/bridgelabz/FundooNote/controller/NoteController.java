package com.bridgelabz.FundooNote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.FundooNote.dto.NoteDto;
import com.bridgelabz.FundooNote.model.NoteModel;
import com.bridgelabz.FundooNote.response.Response;
import com.bridgelabz.FundooNote.service.NoteService;

@RestController
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/noteCreate")
	public Response createNote(@RequestBody NoteModel model, @RequestParam String token) {
		Response response = noteService.createNewNote(model, token);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteNote")
	public Response deleteNote(@RequestBody NoteDto model, @RequestParam String token) {
		Response response = noteService.deleteNote(model, token);
		return response;
	}
	
	@RequestMapping(method =  RequestMethod.POST, value = "/updateNote")
	public Response update(@RequestBody NoteDto model, @RequestParam String token) {
		Response response = noteService.updateNote(model, token);
		return response;
	}
	
	@RequestMapping(method =  RequestMethod.GET, value = "/noteName")
	public List<NoteModel> sortList( @RequestParam String noteName, @RequestParam String token) {
		List<NoteModel> noteModel = noteService.displayUserNotes(noteName, token);
		return noteModel ;
	}
	
	@RequestMapping(method =  RequestMethod.POST, value = "/trash")
	public Response trash(@RequestParam Long noteId, @RequestParam String token) {
		Response response = noteService.storeToTrash(noteId, token);
		return response;
	}
	
	@RequestMapping(method =  RequestMethod.POST, value = "/trashToList")
	public Response trashToList(@RequestParam Long noteId, @RequestParam String token) {
		Response response = noteService.trashToList(noteId, token);
		return response;
	}
	@RequestMapping(method =  RequestMethod.POST, value = "/deleteFromTrash")
	public Response deleteFromTrash(@RequestParam int noteId, @RequestParam String token) {
		Response response = noteService.deleteFromTrash(noteId, token);
		return response;
	}
	
	@RequestMapping(method =  RequestMethod.POST, value = "/pinUnpin")
	public Response pinUnpin(@RequestParam int noteId, @RequestParam String token) {
		Response response = noteService.pinUnpin(noteId, token);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addToArchieve")
	public Response addToArchieve (@RequestParam int noteId, @RequestParam String token) {
		Response response = noteService.addToArchieve(noteId, token);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/unArchieve")
	public Response unArchieve(@RequestParam int noteId, @RequestParam String token) {
		Response response = noteService.unArchieveNote(noteId, token);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/collaborate")
	public Response collaborator(@RequestParam int  noteId, @RequestParam String emailId, @RequestParam String token) {
		Response response = noteService.collaborateWithEmailId(noteId, emailId, token);
		return response;
	}
}
