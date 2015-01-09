package com.meteor.sendmail.controller;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.meteor.sendmail.model.Email;
import com.meteor.sendmail.module.MailSender;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MailSenderController {

	private static final Logger logger = LoggerFactory
			.getLogger(MailSenderController.class);

	@Autowired
	private MailSender mailSender;

	@RequestMapping(value = "send", method = RequestMethod.GET)
	public ModelAndView sendEmailAction() {

		Email email = new Email();
		email.createTestEmail();
		try {
			mailSender.SendEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("e : " + e);
			e.printStackTrace();
		}

		return new ModelAndView("success");

	}

	@RequestMapping(value = "async_send", method = RequestMethod.GET)
	public Callable<String> async_sendEmailAction() {

		return new Callable<String>() {

			@Override
			public String call() throws Exception {

				Email email = new Email();
				email.createTestEmail();

				try {
					mailSender.SendEmail(email);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("e : " + e);
					e.printStackTrace();
				}

				return "success";
			}
		};

	}

	@RequestMapping(value = "back_send", method = RequestMethod.GET)
	public String back_sendEmailAction() {

		final Email email = new Email();
		email.createTestEmail();

		new Thread(new Runnable() {
			public void run() {
				try {
					mailSender.SendEmail(email);
					logger.info("Child Thread Mail Send");

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		return "success";

	}

}
