package com.bridgelabz.FundooNote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	private NoteService service;
	
	@RequestMapping(method = RequestMethod.POST, value = "/noteCreate")
	public Response createNote(@RequestBody NoteModel model, @RequestParam String token) {
		
		Response response = service.createNewNote(model, token);
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteNote")
	public Response deleteNote(@RequestBody NoteDto model, @RequestParam String token) {
		Response response = service.deleteNote(model, token);
		return response;
	}
	
	@RequestMapping(method =  RequestMethod.POST, value = "/updateNote")
	public Response update(@RequestBody NoteDto model, @RequestParam String token) {
		Response response = service.updateNote(model, token);
		return response;
	}
	
	@RequestMapping(method =  RequestMethod.GET, value = "/getAllNote")
	public List<NoteModel> NoteList(String token) {
		List<NoteModel> noteModel = service.Displaydata(token);
		return  noteModel;
	}
	
}
