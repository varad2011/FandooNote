package com.bridgelabz.FundooNote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.FundooNote.dto.ForgetPasswordDto;
import com.bridgelabz.FundooNote.dto.UserLoginDto;
import com.bridgelabz.FundooNote.response.Response;
import com.bridgelabz.FundooNote.service.ForgetPasswordService;
import com.bridgelabz.FundooNote.service.RegistrationService;

@RestController
public class ForgetPasswordController {

	@Autowired
	ForgetPasswordService service;
	
	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "/emailId") public
	 * ResponseEntity<Response> forgetPassword(ForgetPasswordDto passwordDto){
	 * service.checkEmailId(passwordDto); return null;
	 * 
	 * }
	 */
	
	@RequestMapping(method = RequestMethod.POST, value = "/resetLink")
	public Response resetPassword(@RequestBody ForgetPasswordDto passwordDto, String  token ) {
		Response response = service.checkUserEmailId(passwordDto, token);
		return response;
	}
	@RequestMapping(method = RequestMethod.POST, value = "/resetPage")
	public Response forgetUserPassword(@RequestBody UserLoginDto resetPasswordDto) {
		Response response = service.updatePassword(resetPasswordDto);
		return response;
	}
}
