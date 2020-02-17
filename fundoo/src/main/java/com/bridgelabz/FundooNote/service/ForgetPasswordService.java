package com.bridgelabz.FundooNote.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	private RegistrationPageRepository registrationDao;
	
	@Autowired
	private RegistrationRepository RegistrationRepository;
	
	@Autowired
	private JMSMail email; 

	
	public boolean checkEmailId(ForgetPasswordDto passwordDto) {
		//RegistrationModel model = registrationDao.findByEmailId(passwordDto.getEmailId());
		String emailId = passwordDto.getEmailId();
		Optional<RegistrationModel> model1 = registrationDao.findByEmailId(emailId);
		if(model1.isPresent()) {
			return  true;
		}
		return false;
	}

	public void update(String emailId, String password) {
		
	//RegistrationModel model = registrationDao.findByEmailId(emailId);
	
	}

	public Response checkUserEmailId(ForgetPasswordDto passwordDto, String token) {
		System.out.println(passwordDto.getEmailId());
		String emailId = passwordDto.getEmailId();
		Optional<RegistrationModel> model = registrationDao.findByEmailId(emailId);
		//RegistrationModel model = registrationDao.findByEmailId(passwordDto.getEmailId());
		System.out.println(model);
		
		try {
			if(!model.isPresent()) {
				return new Response(200, "wrong emailId Enter",null);
			}else {
				email.sendEmail(null, "click on link to forget pasword", emailId, "http://localhost:8080/resetPage");
				return new Response(200, "send link on mail to forget password",null);
			}
		}catch(Exception e){
			return  new Response(200, "exception", null);
		}
	}

	public Response updatePassword(UserLoginDto resetPasswordDto) {
		String emailId = resetPasswordDto.getEmailId();
		String password = resetPasswordDto.getPassword();
		RegistrationModel model = RegistrationRepository.findByEmailId(emailId);
	//	RegistrationModel model1 = RegistrationRepository.findByEmailId(emailId);
	//	RegistrationRepository.delete(model1);
		model.setPassword(password);
		RegistrationRepository.save(model);
		System.out.println(model);
		return new Response(200, "password reset successfully", null);
	}
}
