package com.bridgelabz.FundooNote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.FundooNote.dto.ForgetPasswordDto;
import com.bridgelabz.FundooNote.dto.UserLoginDto;
import com.bridgelabz.FundooNote.response.Response;
import com.bridgelabz.FundooNote.service.ForgetPasswordService;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class ForgetPasswordController {

	@Autowired
	ForgetPasswordService forgetPasswordService;

	
	
}
