package com.helper_classes;

public class jDate_email {
	
	
	String jdeEmail;
	String boDate;
	
	public String getJdeEmail() {
		return jdeEmail;
	}
	public void setJdeEmail(String jdeEmail) {
		this.jdeEmail = jdeEmail;
	}
	public String getBoDate() {
		return boDate;
	}
	public void setBoDate(String boDate) {
		this.boDate = boDate;
	}
	
	@Override
	public String toString() {
		return "jDate_email [jdeEmail=" + jdeEmail + ", boDate=" + boDate + "]";
	}
	
	public jDate_email(String jdeEmail, String boDate) {
		super();
		this.jdeEmail = jdeEmail;
		this.boDate = boDate;
	}
	
	public jDate_email() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	
}
