package com.aksoft.onlinebanking.dto;

public class Email {
	String from;
	String to;
	String subject;
	String login;
	String password;
	String message;
	String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFrom() {
		setFrom();
		return from;
	}
	public void setFrom() {
		this.from ="aksoftwaresolutionspvt.ltd@gmail.com";
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLogin() {
		setLogin();
		return login;
	}
	public void setLogin() {
		
		this.login = "icoer.guys@gmail.com";
	}
	public String getPassword() {
		setPassword();
		return password;
	}
	public void setPassword() {
		this.password = "aksoft@7884";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
