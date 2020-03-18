package com.bridgelabz.FundooNote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/service")
public class LabelController {

	@Autowired
	LabelService labelService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/labelCreate")
	public ResponseEntity<Response> AddLabel(@RequestBody LabelsModel labelModel, @RequestParam String token) {
		System.out.println(labelModel);
		Response response = labelService.labelCreate(labelModel, token);
		return new ResponseEntity<Response>(response, HttpStatus.FOUND);
	}
	
	@PostMapping(value = "/labelDelete" )
	public ResponseEntity<Response> deleteLabel(@RequestBody LabelsModel labelsModel,@RequestParam String token) {
		Response response = labelService.labelDelete(labelsModel, token);
		return new ResponseEntity<Response>(response, HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/labelDisplay" )
	public ResponseEntity<Response> displayLabel(@RequestParam String token) {
		return  new ResponseEntity<Response>(labelService.displayLabel(token),HttpStatus.FOUND);
	}
	
	@PostMapping(value = "/labelUpdate" )
	public ResponseEntity<Response> updateLabel(@RequestBody LabelsModel labelsModel, @RequestParam String token) {
		Response response = labelService.labelUpdate(labelsModel, token);
		return new ResponseEntity<Response>(response, HttpStatus.FOUND);
	}
	
	@PostMapping(value = "/AddNoteToLabel" )
	public ResponseEntity<Response> AddNoteToLabel(@RequestParam int noteId, @RequestParam int labelId, @RequestParam String token) {
		Response response = labelService.addNoteToLabel(noteId, labelId, token);
		return new ResponseEntity<Response>(response, HttpStatus.FOUND);
	}
	
	@PostMapping(value = "/AddToLabelToNote" )
	public ResponseEntity<Response> addToLabelToNote(@RequestParam int noteId, @RequestParam int labelId, @RequestParam String token) {
		Response response = labelService.addLabelToNote(noteId,  labelId, token);
		return new ResponseEntity<Response>(response, HttpStatus.FOUND);
	}
}
