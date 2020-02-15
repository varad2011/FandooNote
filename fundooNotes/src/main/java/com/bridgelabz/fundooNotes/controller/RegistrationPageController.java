package com.bridgelabz.fundooNotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooNotes.dto.LoginPageDto;
import com.bridgelabz.fundooNotes.model.RegistrationPageModel;
import com.bridgelabz.fundooNotes.service.RegistrationPageService;

@RestController
public class RegistrationPageController {
	/*
	 * @Autowired private RegistrationPageService registrationPageService;
	 * 
	 * @RequestMapping(method = RequestMethod.GET, value = "/login") public String
	 * LoginCheck(@RequestBody LoginPageDto loginPageDto) { String status;
	 * 
	 * if(registrationPageService.userAvailabilityCheck(loginPageDto) == true) {
	 * status = "welcome"; }else { status = "wrong entry"; }
	 * 
	 * return status; }
	 * 
	 * @RequestMapping(method =RequestMethod.POST, value = "/registor" ) public
	 * String newRegistration(@RequestBody RegistrationPageModel model) { String
	 * status; System.out.println(model);
	 * if(registrationPageService.NewEntrySave(model) == true) { status =
	 * "successfully registor"; }else { status =
	 * " already avaliable email id forget password"; } return status; }
	 */
}
