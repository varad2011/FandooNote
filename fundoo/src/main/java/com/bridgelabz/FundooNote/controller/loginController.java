package com.bridgelabz.FundooNote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.FundooNote.dto.UserLoginDto;
import com.bridgelabz.FundooNote.response.Response;
import com.bridgelabz.FundooNote.service.LoginService;

@RestController
public class loginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/userLogin")
	public Response loginUser(@RequestBody UserLoginDto loginDto) {
			Response response = loginService.userLoginCheck(loginDto);
		return response;
	}
}
