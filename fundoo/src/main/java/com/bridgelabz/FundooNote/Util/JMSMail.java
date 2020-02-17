package com.bridgelabz.FundooNote.Util;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class JMSMail  {
	
	@Autowired
	private JavaMailSender javaMailSender;	
	
	// send simple mail method
	public void sendEmail(String token, String subject, String emailId, String text) {
		SimpleMailMessage msg = new SimpleMailMessage();
		//msg.setTo("patilvarad2011@gmail.com");
		msg.setTo(emailId);
		msg.setSubject(subject);
	
			//http://localhost:8080/"+"token?
			msg.setText(text +token);
	
		javaMailSender.send(msg);
	}
	
	//send mail with attachment
	public void sendEmailWithAttachment() throws MessagingException, IOException {

	        MimeMessage msg = javaMailSender.createMimeMessage();

	        // true = multipart message
	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
	        helper.setTo("patilvarad2011@gmail.com");

	        helper.setSubject("Testing from Spring Boot");

	        // default = text/plain
	        //helper.setText("Check attachment for image!");

	        // true = text/html
	        helper.setText("<h1>Check attachment for image!</h1>"+"localhost:8080/", true);

	        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

	        javaMailSender.send(msg);
	    }
}
