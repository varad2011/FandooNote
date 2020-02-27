package com.bridgelabz.FundooNote.service;


import java.io.InputStream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.bridgelabz.FundooNote.Util.TokenGeneratorDecoder;
import com.bridgelabz.FundooNote.dto.UserLoginDto;
import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.repository.RegistrationRepository;
import com.bridgelabz.FundooNote.response.Response;

@Service
public class LoginService {

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private TokenGeneratorDecoder tokenGeneratorDecoder;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Response userLoginCheck(UserLoginDto loginDto) {
		try {
			RegistrationModel model = registrationRepository.findByEmailId(loginDto.getEmailId());
			if(!model.getEmailId().isEmpty()) {
				if(passwordEncoder.matches(loginDto.getPassword(), model.getPassword())) {
					String token = tokenGeneratorDecoder.tokenGenerator(Long.toString(model.getId()));
					return new Response(200, "user avilable", token);
				}else {
					return new Response(200, "password Wrong", null);
				}
			}else {
				return new Response(200, "user  not available", null);
			}
		} catch (Exception e) {
			return new Response(200, "nullpoint", null);
		}
	}
}
