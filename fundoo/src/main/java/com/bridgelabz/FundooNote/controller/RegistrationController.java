package com.bridgelabz.FundooNote.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.FundooNote.dto.ForgetPasswordDto;
import com.bridgelabz.FundooNote.dto.UserLoginDto;
import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.response.Response;
import com.bridgelabz.FundooNote.service.LoginService;
import com.bridgelabz.FundooNote.service.RegistrationService;

import javassist.NotFoundException;

@RestController
@CrossOrigin
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(method = RequestMethod.POST, value = "/userRegistration")
	public ResponseEntity<Response> registration(@Valid @RequestBody RegistrationModel model) {
		Response response = registrationService.newRegisterEntry(model);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/userLogin")
	public ResponseEntity<Response> loginUser(@RequestBody UserLoginDto loginDto) throws Exception {
		Response response = registrationService.userLoginCheck(loginDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/resetLink")
	public ResponseEntity<Response> linksendToResetPassword(@RequestBody ForgetPasswordDto passwordDto)
			throws NotFoundException {
		Response response = registrationService.checkUserEmailId(passwordDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/resetemailId")
	public Response forgetUserPassword(@RequestBody UserLoginDto resetPasswordDto) {
		System.out.println(resetPasswordDto.toString());
		Response response = registrationService.updatePassword(resetPasswordDto);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/updateUserProfilePic")
	public Response userProfilePicSet(@RequestParam String token) {
		Response response = registrationService.updateProfilePic(token);
		return response;
	}
}
