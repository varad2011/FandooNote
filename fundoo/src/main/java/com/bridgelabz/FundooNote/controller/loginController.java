package com.bridgelabz.FundooNote.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private LoginService service;
	
	@RequestMapping(method = RequestMethod.POST, value = "login")
	public String loginCheck(@RequestBody UserLoginDto loginDto) throws UnsupportedEncodingException {
		System.out.println(loginDto);
		if(service.userAvailabilityCheck(loginDto) == true) {
			return "login success";
		}else {
			return "invalid user";
		}
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "login1") public
	 * ResponseEntity<Response> login(@RequestBody UserLoginDto loginDto ) throws
	 * UnsupportedEncodingException{ Response response =
	 * service.userAvailabilityCheck1(loginDto);
	 * 
	 * return new ResponseEntity<Response>(response, HttpStatus.OK); }
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/userLogin")
	public Response loginUser(@RequestBody UserLoginDto loginDto) {
			Response response = service.userLoginCheck(loginDto);
		return response;
	}
}
