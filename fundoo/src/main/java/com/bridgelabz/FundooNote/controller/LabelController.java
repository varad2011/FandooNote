package com.bridgelabz.FundooNote.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.FundooNote.model.LabelsModel;
import com.bridgelabz.FundooNote.response.Response;
import com.bridgelabz.FundooNote.service.LabelService;

@RestController 
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

	@Autowired
	LabelService labelService;

	@RequestMapping(method = RequestMethod.POST, value = "/labelCreate") 
	public ResponseEntity<Response> AddLabel( @Valid @RequestBody LabelsModel labelModel, @RequestParam String token) {
		Response response = labelService.labelCreate(labelModel, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/labelDelete")
	public ResponseEntity<Response> deleteLabel(@RequestBody LabelsModel labelsModel, @RequestParam String token) {
		Response response = labelService.labelDelete(labelsModel, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/labelDisplay")
	public ResponseEntity<Response> displayLabel(@RequestParam String token) {
		return new ResponseEntity<Response>(labelService.displayLabel(token), HttpStatus.OK);
	}


	@GetMapping(value = "/labelDisplayAddedToNote")
	public ResponseEntity<Response> displayLabelAddedToNote(@RequestParam String token, @RequestParam int noteId) {
		return new ResponseEntity<Response>(labelService.displayLabelAddedToNote(token, noteId ), HttpStatus.OK);
	}
	
	@GetMapping(value = "/labelDisplayUnAddedToNote")
	public ResponseEntity<Response> displayLabelUnAddedToNote(@RequestParam String token, @RequestParam int noteId) {
		return new ResponseEntity<Response>(labelService.displayLabelUnAddedToNote(token, noteId ), HttpStatus.OK);
	}
	
	@PostMapping(value = "/labelUpdate")
	public ResponseEntity<Response> updateLabel(@Valid @RequestBody LabelsModel labelsModel, @RequestParam String token) {
		Response response = labelService.labelUpdate(labelsModel, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/AddNoteToLabel")
	public ResponseEntity<Response> AddNoteToLabel(@RequestParam int noteId, @RequestParam int labelId,
			@RequestParam String token) {
		Response response = labelService.addNoteToLabel(noteId, labelId, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/AddToLabelToNote")
	public ResponseEntity<Response> addToLabelToNote(@RequestParam int noteId, @RequestParam int labelId,
			@RequestParam String token) {
		Response response = labelService.addLabelToNote(noteId, labelId, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getNoteBylabelId")
	public ResponseEntity<Response>getallNoteByLabel(@RequestParam int labelId, @RequestParam String  token){
		Response response = labelService.getAllNoteByLabelId(labelId,token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	@GetMapping(value = "/getPinNoteByLabelId")
	public ResponseEntity<Response>getpinNoteByLabel(@RequestParam int labelId, @RequestParam String  token){
		Response response = labelService.getAllPinNoteByLabelId(labelId, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/removeLabelFromNotes")
	public ResponseEntity<Response> removeToLabelFromNote(@RequestParam int noteId, @RequestParam int labelId,
			@RequestParam String token) {
		Response response = labelService.labelRemoveFromNote(noteId, labelId, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
