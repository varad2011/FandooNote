package com.bridgelabz.fundooNotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooNotes.dto.LoginPageDto;
import com.bridgelabz.fundooNotes.model.RegistrationPageModel;

@Service
public class RegistrationPageService {
	/*
	 * @Autowired private RegistrationpageDao registrationPageDao;
	 * 
	 * //userAvalbality check from database public boolean
	 * userAvailabilityCheck(LoginPageDto loginPageDto) {
	 * 
	 * try { String emailId = loginPageDto.getEmailId();
	 * 
	 * if(registrationPageDao.findByEmailId(emailId) != null) { return true; }
	 * }catch(Exception e){ return false; } return false;
	 */
		/*
		 * String emailId = loginPageDto.getEmailId(); String password =
		 * loginPageDto.getPassword();
		 * 
		 * if((registrationPageDao.findByEmailId(emailId).equals(emailId)) &&
		 * (registrationPageDao.findByPassword(password).equals(password))){ return
		 * true; }
		 * 
		 * return false;
		 */
/*	}
	
	public boolean NewEntrySave(RegistrationPageModel registrationPageModel) {
		String emailID = registrationPageModel.getEmailId();
		System.out.println("registrationMode;"+registrationPageModel);
		
		 registrationPageDao.save(registrationPageModel); return true;
		  
		 */
		/*
		 * try { RegistrationPageModel model1 =
		 * registrationPageDao.findByEmailId(emailID); if(
		 * model1.getEmailId().equalsIgnoreCase(emailID)) {
		 * 
		 * return false;
		 * 
		 * }else {
		 * 
		 * registrationPageDao.save(registrationPageModel);
		 * 
		 * return true; }
		 * 
		 * 
		 * }catch(Exception e){ System.out.println("exception e"); return false; }
		 */
	//}
	
	
}
