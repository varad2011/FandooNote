package com.bridgelabz.FundooNote.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.response.Response;
import com.bridgelabz.FundooNote.service.RegistrationService;

@RestController
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/userRegistration")
	public Response registration(@Valid @RequestBody RegistrationModel model) {
		Response response = registrationService.newRegisterEntry(model);
		return response;
	}
}
