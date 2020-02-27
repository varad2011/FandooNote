package com.bridgelabz.FundooNote.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooNote.Util.JMSMail;
import com.bridgelabz.FundooNote.Util.TokenServiceUtil;
import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.repository.RegistrationRepository;
import com.bridgelabz.FundooNote.response.ErrorResponse;
import com.bridgelabz.FundooNote.response.Response;
import com.bridgelabz.FundooNote.repository.RegistrationPageRepository;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private RegistrationPageRepository registrationPageRepository;
	
	@Autowired
	private JMSMail email; 

	@Autowired
	TokenServiceUtil tokenService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Response newRegisterEntry(RegistrationModel model) {
		String emailId = model.getEmailId();
		String password = model.getPassword();
		Optional<RegistrationModel> newModel = registrationPageRepository.findByEmailId(emailId);
		System.out.println(newModel);
		if (newModel.isPresent()) {
			return new Response(401, "unsuccessRegistration", null);
		} else {
			model.setPassword(passwordEncoder.encode(password));
			registrationRepository.save(model);
			email.sendEmail(null, "Registration done successfully", emailId, null);
			return new Response(200, "use Registration success", null);
		}
	}
}
