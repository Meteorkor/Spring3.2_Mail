package com.meteor.sendmail.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.meteor.sendmail.model.Email;
import com.meteor.sendmail.module.MailSender;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
    
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	   @Autowired
	    private MailSender mailSender;
	   
	@RequestMapping(value="send", method=RequestMethod.GET)
	public ModelAndView sendEmailAction(){
		
		Email email = new Email();
		
		String reciver = "doomgreen@paran.com";
		String subject = "테스트 메일";
		String content = "이메일 내용";
		
		email.setReciver(reciver);
		email.setSubject(subject);
		email.setContent(content);
		
		try {
			mailSender.SendEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println( "e : " + e  );
			e.printStackTrace();
		}
		
		return new ModelAndView("success"); 
		
	}
	
	

	@RequestMapping(value="async_send", method=RequestMethod.GET)
	public Callable<String> async_sendEmailAction(){
		
		
		
		return  new Callable<String>() {
			
			@Override
			public String call() throws Exception {

				
				Email email = new Email();
				
				String reciver = "doomgreen@paran.com";
				String subject = "테스트 메일";
				String content = "이메일 내용";
				
				email.setReciver(reciver);
				email.setSubject(subject);
				email.setContent(content);
				
				try {
					mailSender.SendEmail(email);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println( "e : " + e  );
					e.printStackTrace();
				}
				
				return "success";
			}
		};
		
				
				
	}
	
	
	@RequestMapping(value="back_send", method=RequestMethod.GET)
	public Callable<String> back_sendEmailAction(){
		
		return  new Callable<String>() {
			
			@Override
			public String call() throws Exception {

				
				final Email email = new Email();
				
				String reciver = "doomgreen@paran.com";
				String subject = "테스트 메일";
				String content = "이메일 내용";
				
				email.setReciver(reciver);
				email.setSubject(subject);
				email.setContent(content);
				
				
				
				new Thread(  new Runnable() {
					public void run() {
						try {
							mailSender.SendEmail(email);
							System.out.println( "보냈음" );
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		
					}
				} ).start();
				
				
				return "success";
			}
		};
		
				
				
	}
	
}
