package com.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.http.HttpServletResponse;

import com.entity.Booking;
import com.entity.Passenger;
import com.repository.Booking_Repository;
import com.repository.Passenger_Repository;


@Service
public class Passenger_Service {
	
	@Autowired
	Passenger_Repository passengerRepo;
	
	@Autowired
	Booking_Repository bookingRepo;
	
// ==========================================================================================
	
	//  Insert Operation by Id-Unique Email:-    Op:3
	
	public String storePassenger(Passenger passenger) {
		boolean res = passengerRepo.existsById(passenger.getpEmail());
		System.out.println(res);
		if(res) {
			return "Passenger details didn't store...\nYou have already Registered...";
		}
		else {
			passengerRepo.save(passenger);
			return "Passenger("+ passenger.getpName() +") Registered successfully...";
		}
	}	
	
	
// ===========================================================================================================================================
	
	//  Retrieve Message by Email & password | Login Operation :-   Op: 7

	public String login(Passenger passenger) {
		String email = passenger.getpEmail();
		String password = passenger.getpPassword();
		
		Optional<Passenger> op = passengerRepo.findById(passenger.getpEmail());
		System.out.println("**************************"+op);
		
		if(op.isPresent()) {
			Passenger p = op.get();
			
			if(p.getpPassword().equals(passenger.getpPassword())) {
				return "WELCOME";
			} else {
				return "Password may be worng";
			}
		} else {
			return "Email or Password may be worng";
		}
	}

// ==========================================================================================================================================

			public void generateBookingReceipt(int bookingId, HttpServletResponse response) throws IOException {
		        
				Optional<Booking> bookingOptional = bookingRepo.findById(bookingId);
		        if (bookingOptional.isPresent()) {
		            Booking booking = bookingOptional.get();

		            // Create a new PDF document
		            PDDocument document = new PDDocument();
		            PDPage page = new PDPage(PDRectangle.A4);
		            document.addPage(page);

		            // Create a new content stream
		            PDPageContentStream contentStream = new PDPageContentStream(document, page);

		            // Set the font and font size for the content
		            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

		            // Set the starting position for the content
		            float y = page.getMediaBox().getHeight() - 50;

		            // Write the booking details to the content stream
		            contentStream.beginText();
		            
		            contentStream.newLineAtOffset(50, y);
		            contentStream.showText("Booking ID: " + booking.getBookingId());
		            
		            contentStream.newLine();
		            y -= 20;
		            contentStream.newLineAtOffset(0, -20);
		            contentStream.showText("Booking Date: " + booking.getBookingDate());
		            
		            contentStream.newLine();
		            y -= 20;
		            contentStream.newLineAtOffset(0, -20);
		            contentStream.showText("Passenger Name: " + booking.getpName());
		            
		            contentStream.newLine();
		            y -= 20;
		            contentStream.newLineAtOffset(0, -20);
		            contentStream.showText("No of Head-Counts: " + booking.getNoOfHeadCount());
		            
		            contentStream.newLine();
		            y -= 20;
		            contentStream.newLineAtOffset(0, -20);
		            contentStream.showText("Payment Amount: " + booking.getPaymentAmount()+"/-");
		            
		            
		            // Add more booking details here as needed
		            contentStream.endText();

		            // Close the content stream
		            contentStream.close();

		            // Set the response headers for the PDF file
		            response.setContentType("application/pdf");
		            response.setHeader("Content-Disposition", "inline; filename=booking_receipt.pdf");

		            // Save the PDF document to the response output stream
		            document.save(response.getOutputStream());

		            // Close the PDF document
		            document.close();
		        }
		    }


// =======================================================================================================
// =======================================================================================================
			

}
