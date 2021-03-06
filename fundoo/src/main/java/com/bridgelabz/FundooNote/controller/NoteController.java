package com.bridgelabz.FundooNote.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.bridgelabz.FundooNote.service.LabelService;
import com.bridgelabz.FundooNote.service.NoteService;

@RestController
@CrossOrigin
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@RequestMapping(method = RequestMethod.POST, value = "/noteCreate")
	public ResponseEntity<Response> createNote(@Valid @RequestBody Note model, @RequestParam String token) {
		System.out.println(model.toString());
		System.out.println(token);
		Response response = noteService.createNewNote(model, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteNote")
	public ResponseEntity<Response> deleteNote(@RequestBody NoteDto model, @RequestParam String token) {
		Response response = noteService.deleteNote(model, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateNote")
	public ResponseEntity<Response> update(@RequestBody NoteDto model, @RequestParam String token) {
		Response response = noteService.updateNote(model, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/noteListById")
	public ResponseEntity<Response> sortList(@RequestParam String token) {
		Response response = noteService.displayUserNote(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/noteListBySearchText")
	public ResponseEntity<Response> displayNoteListBySearchKey(@RequestParam String token, @RequestParam String typeText) {
		Response response = noteService.getListOfNoteBySearchField(token, typeText);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="displayNoteById")
	public ResponseEntity<Response> getSingleNote(@RequestParam int noteId,@RequestParam String token){
	Response response = noteService.getSingleNote(noteId,token);
	return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/noteListName")
	public ResponseEntity<Response> sortListByName(@RequestParam String noteName, @RequestParam String token) {
		return new ResponseEntity<Response>(noteService.displayUserNotesName(noteName, token), HttpStatus.FOUND);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/trash")
	public ResponseEntity<Response> trash(@RequestParam int noteId, @RequestParam String token) {
		Response response = noteService.storeToTrash(noteId, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/trashToList")
	public ResponseEntity<Response> trashToList(@RequestParam Long noteId, @RequestParam String token) {
		Response response = noteService.trashToList(noteId, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFromTrash")
	public ResponseEntity<Response> deleteFromTrash(@RequestParam int noteId, @RequestParam String token) {
		Response response = noteService.deleteFromTrash(noteId, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/deleteAllFromTrash")
	public ResponseEntity<Response> deleteAllFromTrash( @RequestParam String token) {
		Response response = noteService.deleteAllFromTrash(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getTrashList")
	public ResponseEntity<Response> displayTrashList(@RequestParam String token) {
		return new ResponseEntity<Response>(noteService.getTrashList(token), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/pinUnpin")
	public ResponseEntity<Response> pinUnpin(@RequestParam int noteId, @RequestParam String token) {
		System.out.println("NOTEiD" + noteId);
		Response response = noteService.pinUnpin(noteId, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	@GetMapping(value ="/getPinNotes")
	public ResponseEntity<Response> getpinNotes(@RequestParam String token){
		Response response = noteService.getAllPinNotes( token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addToArchieve")
	public ResponseEntity<Response> addToArchieve(@RequestParam int noteId, @RequestParam String token) {
		Response response = noteService.addToArchieve(noteId, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/unArchieve")
	public ResponseEntity<Response> unArchieve(@RequestParam int noteId, @RequestParam String token) {
		Response response = noteService.unArchieveNote(noteId, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getArchieveList")
	public ResponseEntity<Response> displayArchievedList(@RequestParam String token) {
		return new ResponseEntity<Response>(noteService.getArchivedList(token), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/collaborate")
	public ResponseEntity<Response> collaborator(@RequestParam int noteId, @RequestParam String emailId,
			@RequestParam String token) {
		Response response = noteService.collaborateWithEmailId(noteId, emailId, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/collaborateEmailId")
	public ResponseEntity<Response> collaboratorWithEmailList(@RequestParam int noteId, @RequestParam String emailId,
			@RequestParam String token) {
		Response response = noteService.collaboratorUsingJoinTable(noteId, emailId, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reminderAdd")
	public ResponseEntity<Response> reminderAddToNote(@RequestParam int noteId, @RequestParam String datetime,
			@RequestParam String token) {
		Response response = noteService.addReminder(noteId, datetime, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reminderRemove")
	public ResponseEntity<Response> removeReminderFromNote(@Valid @RequestParam int noteId,
			@RequestParam String token) {
		Response response = noteService.removeReminder(noteId, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/reminderCompleteNoteCount")
	public ResponseEntity<Response> reminderCompleteNoteCount(@RequestParam String token) {
		return new ResponseEntity<Response>(noteService.remindNotesToUser(token), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getReminderCompleteNote")
	public ResponseEntity<Response> getReminderCompleteNoteList(@RequestParam String token) {
		return new ResponseEntity<Response>(noteService.getListOfAllReminderCompleteNotes(token), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAllReminderNote")
	public ResponseEntity<Response> getAllReminderNote(@RequestParam String token) {
		return new ResponseEntity<Response>(noteService.getAllReminderList(token), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/AllPinReminderNote")
	public ResponseEntity<Response> AllPinReminderNote(@RequestParam String token) {
		return new ResponseEntity<Response>(noteService.AllReminderPinNoteList(token), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/setProfilePic")
	public ResponseEntity<Response> removeReminderFromNote(@RequestParam String token,
			@RequestHeader MultipartFile file) throws IOException {
		return new ResponseEntity<Response>(noteService.uploadProPic(file, token), HttpStatus.OK);
	}

	@Autowired
	LabelService labelService;
	
	@GetMapping(value = "/labelDisplay")
	public ResponseEntity<Response> displayLabel(@RequestParam String token) {
		return new ResponseEntity<Response>(labelService.displayLabel(token), HttpStatus.OK);
	}
	@PostMapping(value = "/BackgroundColorUpdate")
	public ResponseEntity<Response> setBackgroundColor(@RequestBody Note noteModel, @RequestParam String token){ 
		Response response = noteService.setColor(noteModel,token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
