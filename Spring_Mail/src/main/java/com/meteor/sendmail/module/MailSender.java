package com.meteor.sendmail.module;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.meteor.sendmail.model.Email;

@Component
public class MailSender {
	
	private MailSender(){
		
	}
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	 
	 public void SendEmail(Email email) throws Exception {
    
	       MimeMessage msg = javaMailSender.createMimeMessage();
	        msg.setSubject(email.getSubject());
	        msg.setText(email.getContent());
	        msg.setRecipient(RecipientType.TO , new InternetAddress(email.getReciver()));
	         
	        
	        javaMailSender.send(msg); 
	 
	    }
    
}
