package com.bridgelabz.FundooNote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.FundooNote.Util.JMSMail;
import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.response.Response;
import com.bridgelabz.FundooNote.service.RegistrationService;

@RestController
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	//Register new entry method controller
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public void registerEntry(@RequestBody RegistrationModel model) throws Exception {
		service.newRegister(model);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/userRegistration")
	public Response registration(@RequestBody RegistrationModel model) {
		
		Response response = service.newRegisterEntry(model);
		return response;
	}
	/*
	 * //Register new entry using reponse method controller
	 * 
	 * @RequestMapping(method = RequestMethod.POST, value = "/register1") public
	 * ResponseEntity<Response> registerEntry1(@RequestBody RegistrationModel model)
	 * throws Exception{ Response response = service.newregister1(model); return new
	 * ResponseEntity<Response>(response, HttpStatus.OK); }
	 */
}
