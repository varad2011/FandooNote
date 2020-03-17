package com.bridgelabz.FundooNote.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.FundooNote.dto.NoteDto;
import com.bridgelabz.FundooNote.model.Note;
import com.bridgelabz.FundooNote.response.Response;
import com.bridgelabz.FundooNote.service.NoteService;

@RestController
@CrossOrigin
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@RequestMapping(method = RequestMethod.POST, value = "/noteCreate")
	public Response createNote(@Valid @RequestBody Note model, @RequestParam String token) {
		System.out.println(model.toString());
		System.out.println(token);
		Response response = noteService.createNewNote(model, token);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteNote")
	public Response deleteNote(@RequestBody NoteDto model, @RequestParam String token) {
		Response response = noteService.deleteNote(model, token);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateNote")
	public Response update(@RequestBody NoteDto model, @RequestParam String token) {
		Response response = noteService.updateNote(model, token);
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/noteListById")
	public Response sortList(@RequestParam String token) {
		return noteService.displayUserNote(token);
	}
		
	@RequestMapping(method = RequestMethod.GET, value = "/noteListName")
	public Response sortListByName(@RequestParam String noteName, @RequestParam String token) {
		return noteService.displayUserNotesName(noteName, token);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/trash")
	public Response trash(@RequestParam Long noteId, @RequestParam String token) {
		Response response = noteService.storeToTrash(noteId, token);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/trashToList")
	public Response trashToList(@RequestParam Long noteId, @RequestParam String token) {
		Response response = noteService.trashToList(noteId, token);
		return response;
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteFromTrash")
	public Response deleteFromTrash(@RequestParam int noteId, @RequestParam String token) {
		Response response = noteService.deleteFromTrash(noteId, token);
		return response;
	}
	@RequestMapping(method = RequestMethod.GET, value = "/getTrashList")
	public Response displayTrashList(@RequestParam String token){
		return noteService.getTrashList(token);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/pinUnpin")
	public Response pinUnpin(@RequestParam int noteId, @RequestParam String token) {
		System.out.println("NOTEiD"+noteId);
		Response response = noteService.pinUnpin(noteId, token);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addToArchieve")
	public Response addToArchieve(@RequestParam int noteId, @RequestParam String token) {
		Response response = noteService.addToArchieve(noteId, token);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/unArchieve")
	public Response unArchieve(@RequestParam int noteId, @RequestParam String token) {
		Response response = noteService.unArchieveNote(noteId, token);
		return response;
	}
	@RequestMapping(method = RequestMethod.GET, value = "/getArchieveList")
	public Response displayArchievedList(@RequestParam String token){
		return noteService.getArchivedList(token);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/collaborate")
	public Response collaborator(@RequestParam int noteId, @RequestParam String emailId, @RequestParam String token) {
		Response response = noteService.collaborateWithEmailId(noteId, emailId, token);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/collaborateEmailId")
	public Response collaboratorWithEmailList(@RequestParam int noteId, @RequestParam String emailId, @RequestParam String token) {
		Response response = noteService.collaboratorUsingJoinTable(noteId, emailId, token);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/reminderAdd")
	public Response reminderAddToNote(@RequestParam int noteId, @RequestParam String datetime,
			@RequestParam String token) {

		Response response = noteService.addReminder(noteId, datetime, token);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reminderRemove")
	public Response removeReminderFromNote(@Valid @RequestParam int noteId, @RequestParam String token) {
		Response response = noteService.removeReminder(noteId, token);
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAllReminderNote")
	public Response getAllReminderNote(@RequestParam String token) {
		return noteService.getAllReminderList(token);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/setProfilePic")
	public Response removeReminderFromNote(@RequestParam String token, @RequestHeader MultipartFile file) throws IOException {
		return noteService.uploadProPic(file, token);
	}
}
