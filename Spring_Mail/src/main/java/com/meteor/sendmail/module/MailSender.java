package com.meteor.sendmail.module;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.meteor.sendmail.model.Email;

@Component
public class MailSender {

	private MailSender() {

	}

	@Autowired
	public JavaMailSender javaMailSender;

	public void SendEmail(Email email) throws Exception {

		MimeMessage msg = javaMailSender.createMimeMessage();

		MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true,
				"UTF-8");
		messageHelper.setSubject(email.getSubject());
		messageHelper.setTo(new InternetAddress(email.getReciver()));
		messageHelper.setCc(new InternetAddress(email.getReciver()));
		// messageHelper.setFrom( "abs" ) ;
		messageHelper.setText(email.getContent(), true);
		javaMailSender.send(msg);

	}

}
