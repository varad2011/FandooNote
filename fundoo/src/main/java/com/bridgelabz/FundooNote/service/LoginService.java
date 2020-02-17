package com.bridgelabz.FundooNote.service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooNote.Util.TokenGeneratorDecoder;
import com.bridgelabz.FundooNote.Util.TokenServiceUtil;
import com.bridgelabz.FundooNote.dto.UserLoginDto;
import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.repository.RegistrationRepository;
import com.bridgelabz.FundooNote.response.Response;

@Service
public class LoginService {

	@Autowired
	private RegistrationRepository registrationDao;
	
	@Autowired
	private TokenGeneratorDecoder tokenGeneratorDecoder;
	
	//to check user availability from database 
	public boolean userAvailabilityCheck(UserLoginDto loginDto) throws UnsupportedEncodingException {
		
		String emailId = loginDto.getEmailId();
		try {
			RegistrationModel model = registrationDao.findByEmailId(emailId);
			System.out.println(model.getEmailId());

			if( model.getEmailId() != null) {
			   String token = tokenGeneratorDecoder.tokenGenerator(Long.toString(model.getId()));
			   System.out.println(token);
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}

	/*
	 * public Response userAvailabilityCheck1(UserLoginDto loginDto) throws
	 * UnsupportedEncodingException{
	 * 
	 * 
	 * 
	 * try { RegistrationModel model =
	 * registrationDao.findByEmailId(loginDto.getEmailId());
	 * 
	 * if(model.getEmailId() != null) {
	 * 
	 * if(model.getPassword().equals(loginDto.getPassword())) { String token =
	 * tokenGeneratorDecoder.tokenGenerator(Long.toString(model.getId())); return
	 * new Response("login successfully :"+token); } return new
	 * Response("password is invalid"); } } catch (Exception e) { return new
	 * Response("user is not available in database "); }
	 * 
	 * return null;
	 */
	

	public Response userLoginCheck(UserLoginDto loginDto) {

		try {
			RegistrationModel model = registrationDao.findByEmailId(loginDto.getEmailId());
			
			if(!model.getEmailId().isEmpty()) {
				
				if(model.getPassword().equals(loginDto.getPassword())) {
					String token = tokenGeneratorDecoder.tokenGenerator(Long.toString(model.getId()));
					return new Response(200, "user avilable", token);
					
				}else {
					
					return new Response(200, "password Wrong", null);
				}
			}else {
				
				return new Response(200, "user  not avilable", null);
			}
		} catch (Exception e) {
			return new Response(200, "nullpoint", null);
		}
	}
}
