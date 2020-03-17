package com.bridgelabz.FundooNote.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooNote.Util.JMSMail;
import com.bridgelabz.FundooNote.dto.ForgetPasswordDto;
import com.bridgelabz.FundooNote.dto.UserLoginDto;
import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.repository.RegistrationPageRepository;
import com.bridgelabz.FundooNote.repository.RegistrationRepository;
import com.bridgelabz.FundooNote.response.Response;

@Service
public class ForgetPasswordService {
	 
	@Autowired
	private RegistrationPageRepository registrationPageRepository;
	
	@Autowired
	private RegistrationRepository RegistrationRepository;
	
	@Autowired
	private JMSMail email; 

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public boolean checkEmailId(ForgetPasswordDto passwordDto) {
		String emailId = passwordDto.getEmailId();
		Optional<RegistrationModel> model1 = registrationPageRepository.findByEmailId(emailId);
		if(model1.isPresent()) {
			return  true;
		}
		return false;
	}

	

	public Response checkUserEmailId(ForgetPasswordDto passwordDto) {
		System.out.println(passwordDto.getEmailId());
		String emailId = passwordDto.getEmailId();
		Optional<RegistrationModel> model = registrationPageRepository.findByEmailId(emailId);
		System.out.println(model);
			if(!model.isPresent()) {
				return new Response(200, "wrong emailId Enter",null);
			}else {
				email.sendEmail(null, "click on link to forget pasword", emailId, "http://localhost:3000/login/resetPassword");
				return new Response(200, "send link on mail to forget password",null);
			}
	}

	public Response updatePassword(UserLoginDto resetPasswordDto) {
		String emailId = resetPasswordDto.getEmailId();
		String password = resetPasswordDto.getPassword();
		RegistrationModel model = RegistrationRepository.findByEmailId(emailId);
		model.setPassword(passwordEncoder.encode(password));
		RegistrationRepository.save(model);
		System.out.println(model);
		return new Response(200, "password reset successfully", null);
	}
}
