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
	
	//new Registration method with mail configuration
	public String newRegister(RegistrationModel model) throws MessagingException, IOException {
		String token = tokenService.createToken(model.getEmailId());
		String subject = "registration successfully done";
		registrationRepository.save(model);
		email.sendEmail(token, subject, null, null);
	//	email.sendEmailWithAttachment();
		System.out.println("mail send successfully");
		
		return "user Registor successfully";
	}

	/*
	 * //new registration using response t //response return http request public
	 * Response newregister1(RegistrationModel model) throws
	 * UnsupportedEncodingException {
	 * 
	 * String token = tokenService.createToken(model.getEmailId()); String emailId =
	 * model.getEmailId(); System.out.println(emailId);
	 * 
	 * try { Optional<RegistrationModel> newModel =
	 * registrationPageRepository.findByEmailId(emailId); if(!newModel.isPresent())
	 * { registrationRepository.save(model); return new
	 * Response("register successfully"); } } catch (Exception e) { return new
	 * Response("nullvalue"); }
	 * 
	 * return new Response("user already register/n foget password"); }
	 */
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
