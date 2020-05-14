package com.bridgelabz.FundooNote.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.FundooNote.Util.JMSMail;
import com.bridgelabz.FundooNote.Util.TokenGeneratorDecoder;
import com.bridgelabz.FundooNote.Util.TokenServiceUtil;
import com.bridgelabz.FundooNote.dto.ForgetPasswordDto;
import com.bridgelabz.FundooNote.dto.UserLoginDto;
import com.bridgelabz.FundooNote.model.RegistrationModel;
import com.bridgelabz.FundooNote.repository.RegistrationRepository;
import com.bridgelabz.FundooNote.response.RecordNotFoundException;
import com.bridgelabz.FundooNote.response.Response;

import javassist.NotFoundException;

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

	@Autowired
	private TokenGeneratorDecoder tokenGeneratorDecoder;
	
	@Autowired
	private TokenGeneratorDecoder tokenDecoder;

	@Value("${userLoginSuccessMsg}")
	private String loginSuccMsg;

	@Value("${userLoginSuccessCode}")
	private int successCode;

	public Response newRegisterEntry(RegistrationModel model) {
		String emailId = model.getEmailId();
		Optional<RegistrationModel> newModel = registrationPageRepository.findByEmailId(emailId);
		System.out.println(newModel);
		if (!newModel.isPresent()) {
			model.setPassword(passwordEncoder.encode(model.getPassword()));
			registrationRepository.save(model);
			//email.sendEmail(null, "Registration done successfully", emailId, null);
			return new Response(200, "use Registration success", null);
		}
		throw new RecordNotFoundException("unsuccessRegistration");
	}

	public Response userLoginCheck(UserLoginDto loginDto) throws Exception {
		RegistrationModel model = registrationRepository.findByEmailId(loginDto.getEmailId());
		if ((!model.getEmailId().isEmpty()) && (passwordEncoder.matches(loginDto.getPassword(), model.getPassword()))) {
			String token = tokenGeneratorDecoder.tokenGenerator(Long.toString(model.getId()));
			return new Response(successCode, loginSuccMsg, token);
		}
		if ((!model.getEmailId().isEmpty())
				&& (!passwordEncoder.matches(loginDto.getPassword(), model.getPassword()))) {
			throw new RecordNotFoundException("invalid password");
		}
		throw new RecordNotFoundException("not valid user");
	}

	public Response checkUserEmailId(ForgetPasswordDto passwordDto) throws NotFoundException {
		String emailId = passwordDto.getEmailId();
		Optional<RegistrationModel> model = registrationPageRepository.findByEmailId(passwordDto.getEmailId());
		if (model.isPresent()) {
			email.sendEmail(null, "click on link to forget pasword", emailId,
					"http://localhost:3000/registration/resetPassword");
			return new Response(200, "send link on mail to forget password", null);
		}
		throw new RecordNotFoundException("emailId not found");
	}

	public Response updatePassword(UserLoginDto resetPasswordDto) {
		RegistrationModel model = registrationRepository.findByEmailId(resetPasswordDto.getEmailId());
		model.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));
		registrationRepository.save(model);
		System.out.println(model);
		return new Response(200, "password reset successfully", null);
	}

	public Response updateProfilePic(String token) {
		long id = Long.parseLong(tokenDecoder.decodeToken(token));
		Optional<RegistrationModel> user = registrationPageRepository.findById(id);
		String profilePicUrl = user.get().getProfilePic();
		return new Response(200, "profile pic set successfully", profilePicUrl );
	}
}
