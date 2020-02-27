package com.bridgelabz.FundooNote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class LabelController {

	@Autowired
	LabelService labelService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/labelCreate")
	public Response AddLabel(@RequestBody LabelsModel labelModel, @RequestParam String token) {
		System.out.println(labelModel);
		Response response = labelService.labelCreate(labelModel, token);
		return response;
	}
	
	@PostMapping(value = "/labelDelete" )
	public Response deleteLabel(@RequestBody LabelsModel labelsModel,@RequestParam String token) {
		Response response = labelService.labelDelete(labelsModel, token);
		return response;
	}
	
	@GetMapping(value = "/labelDisplay" )
	public List<LabelsModel> displayLabel(@RequestParam String token) {
		
		return  labelService.displayLabel(token);
	}
	
	@PostMapping(value = "/labelUpdate" )
	public Response updateLabel(@RequestBody LabelsModel labelsModel, @RequestParam String token) {
		Response response = labelService.labelUpdate(labelsModel, token);
		return response;
	}
	
	@PostMapping(value = "/AddNoteToLabel" )
	public Response AddNoteToLabel(@RequestParam int noteId, @RequestParam int labelId, @RequestParam String token) {
		Response response = labelService.addNoteToLabel(noteId, labelId, token);
		return response;
	}
	
	@PostMapping(value = "/AddToLabelToNote" )
	public Response addToLabelToNote(@RequestParam int noteId, @RequestParam int labelId, @RequestParam String token) {
		Response response = labelService.addLabelToNote(noteId,  labelId, token);
		return response;
	}
}
