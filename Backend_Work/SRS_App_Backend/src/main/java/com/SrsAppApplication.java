package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SrsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrsAppApplication.class, args);
		System.out.println("Ship Reservation System Application running on port No: 8585...");
	}
	
		/*
		 * Dependencies: Spring web, JPA, jdbc, MySQL, Devtools,   
		 * 				 pdfbox, jstl, servlet-api,  starter-mail
		 */

}
