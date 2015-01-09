package com.meteor.sendmail.model;

public class Email {

	private String subject;
	private String content;
	private String regdate;
	private String reciver;

	public String getReciver() {
		return reciver;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	/**
	 * create_mail content
	 */
	public void createTestEmail() {
		String reciver = "doomgreen@paran.com";// target_addr
		String subject = "Test Mail";
		String content = "Mail content";

		this.setReciver(reciver);
		this.setSubject(subject);
		this.setContent(content);
	}
}
