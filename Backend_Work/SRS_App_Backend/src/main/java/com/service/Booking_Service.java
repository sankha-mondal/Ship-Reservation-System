package com.service;

import java.awt.Font;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helper_classes.*;
import com.repository.Booking_Repository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class Booking_Service {
	
	 Connection con = null;
	 String url = "jdbc:mysql://localhost:3306/";
	 String dbName = "wiprodb";
	 String driver = "com.mysql.jdbc.Driver";
	 String userName = "root";
	 String password = "Sankha@12";
	 
	 @Autowired
	 Booking_Repository bookingRepo;
	 
	 
// ====================== Retrieve Passenger-Email of particular Journey-Date: Op:1C ======================================	 
	
	public List<jDate_email> getAll_BPD(String jDate) {   // BPD = Booking of particular Date
		
		 System.out.println(jDate);
		 List<jDate_email> jde_list = new ArrayList<>(); 
		 
		 try {
		  //  load the MySQL Driver | Step: 1
		  Class.forName(driver).newInstance();  
		  System.out.println("Driver load Successfully...");
		  
		  // Creating connection with my Credentials | Step:2
		  con = DriverManager.getConnection(url+dbName,userName,password); 
		  System.out.println("Connected Successfully...");
		  
		  // Statement st = con.createStatement();   
		  
		  
		  String qu = "select bk.p_email, bk.booking_date  \n"
			  		+ "from Booking bk\n"							//  Step: 3
			  		+ "Inner Join Ship_Schedule ss ON\n"
			  		+ "bk.ship_sch_id = ss.ss_Id and ss.journey_date=?";      //  "'2023-02-17'; "  //  jDate+";"
		  
		  // to send dynamic value to the query
		  PreparedStatement pstmt = con.prepareStatement(qu);   // Step: 4
		  
		  pstmt.setString(1, jDate);  // dynamic Journey-Date
		  
		  // Execute the Query | For Select clause | Step: 5
		  ResultSet rs = pstmt.executeQuery();   
		  
			  while( rs.next() ) {
				  String jdeEmail = rs.getString(1);
				  String boDate = rs.getString(2);    // Use | Step:6 
				  
				  jDate_email jde = new jDate_email();
				  
				  jde.setJdeEmail(jdeEmail);
				  jde.setBoDate(boDate);
				  
				  jde_list.add(jde);
				  
				  System.out.println(jdeEmail+" "+boDate);
			  }
		  System.out.println("Results");
		  // st.close();
		  con.close(); 
		  rs.close();         // Closing all the Connections | Step: 7
		  pstmt.close();
		  
		  }
		  catch( Exception e ) {
			  System.out.println(e.getMessage());
			  e.printStackTrace();
		  }
		 
		 return jde_list ;
		
	}
	
	
// ============================= Retrieve Booking Info for particular Email: Op:5 =================================================

	public List<bookingInfo_pEmail> getAll_BIPD(String pEmail) {   // BIPD = Booking Info for particular Details
		
		System.out.println(pEmail);
		List<bookingInfo_pEmail> poif_list = new ArrayList<>();   // poif = Passenger Order info
		
		try {
			  Class.forName(driver).newInstance();
			  con = DriverManager.getConnection(url+dbName,userName,password);
			  // Statement st = con.createStatement();
			  
			  String qu = "Select bk.booking_Id, bk.p_email, bk.p_name, bk.payment_amount, bk.booking_date, \n"
			  		+ "	   	ss.route_id, ss.journey_date, ss.journey_time, \n"
			  		+ "    	sd.ship_id, sd.ship_name, sd.ship_model \n"
			  		+ "		from Ship_Details sd \n"
			  		+ "     Inner join Ship_Schedule ss ON  \n"
			  		+ " 	ss.ship_details_id = sd.ship_id  \n"
			  		+ "		Inner join Booking bk ON  \n"
			  		+ "		bk.ship_sch_id = ss.ss_Id and bk.p_email = ?";
			  
			  PreparedStatement pstmt = con.prepareStatement(qu);
			  
			  pstmt.setString(1, pEmail);  // dynamic pEmail
			  
			  ResultSet rs = pstmt.executeQuery();
			  
			  while( rs.next() ) { 
					
				  bookingInfo_pEmail bie = new bookingInfo_pEmail();
				  
				  bie.setBookingId(rs.getString(1));
				  bie.setpEmail(rs.getString(2));
				  bie.setpName(rs.getString(3));
				  bie.setPaymentAmount(rs.getInt(4));
				  bie.setBookingDate(rs.getString(5));
				  
				  bie.setRouteId(rs.getString(6));
				  bie.setJourneyDate(rs.getString(7));
				  bie.setJourneyTime(rs.getString(8));
				  
				  bie.setShipId(rs.getString(9));
				  bie.setShipName(rs.getString(10));
				  bie.setShipModel(rs.getString(11));
				  
				  poif_list.add(bie);
				  // System.out.println(jdeEmail+" "+boDate);
			  }
			  System.out.println("Results");
			  // st.close();
			  con.close();
			  rs.close();
			  pstmt.close();
			  
		}
		catch( Exception e ) {
			  System.out.println(e.getMessage());
			  e.printStackTrace();
		}
			
		return poif_list;
	}
	
// ==============  Retrieve Passenger-Email ON particular Ship-Details ON particular Journey-Date : Op:6 ======================================	
	
	public List<jDate_ShipDet_email> getAll_JDSE(String jDate, String shipName) {   // JDSE = JDate_ShipDet_Email
		
		System.out.println(jDate);
		System.out.println(shipName);
		
		List<jDate_ShipDet_email> jse_list = new ArrayList<>();
		
		try {
			
			 Class.forName(driver).newInstance();
			 con = DriverManager.getConnection(url+dbName,userName,password);
			 // Statement st = con.createStatement();
			 
			 
			 String qu = "select bk.booking_id, bk.p_email, bk.p_name, bk.booking_date, "
			 		+ "bk.no_of_head_count, bk.payment_amount, ps.p_phone, ps.p_address \n"
			 		+ "from passenger ps\n"
			 		+ "Inner Join Booking bk ON\n"
			 		+ "bk.p_email = ps.p_email\n"
			 		+ "Inner Join Ship_Schedule ss ON\n"
			 		+ "bk.ship_sch_id = ss.ss_Id \n"
			 		+ "Inner join Ship_Details sd ON\n"
			 		+ "sd.ship_id = ss.ship_details_id\n"
			 		+ "and ss.journey_date=? and sd.ship_name=?" ;
			 
			 
			  PreparedStatement pstmt = con.prepareStatement(qu);
			  
			  pstmt.setString(1, jDate);     // dynamic jDate
			  pstmt.setString(2, shipName);  // dynamic shipName
			  
			  ResultSet rs = pstmt.executeQuery();
			 
			  while( rs.next() ) { 
					
				  jDate_ShipDet_email jdsde = new jDate_ShipDet_email();
				  
				  jdsde.setBookingId(rs.getInt(1));
				  jdsde.setpEmail(rs.getString(2));
				  jdsde.setpName(rs.getString(3));
				  jdsde.setbDate(rs.getString(4));
				  jdsde.setOnOfHC(rs.getInt(5));
				  jdsde.setPayableAmount(rs.getInt(6));
				  jdsde.setpPhone(rs.getString(7));
				  jdsde.setpAddress(rs.getString(8));
				  
				  jse_list.add(jdsde);
				  
			  }
			  System.out.println("Results");
			  // st.close();
			  con.close();
			  rs.close();
			  pstmt.close();
			  	
		}
		catch( Exception e ) {
			  System.out.println(e.getMessage());
			  e.printStackTrace();
		}
		
		
		return jse_list;
	}
	

	
// ========================== : Download : Retrieve Date for Booking-Receipt : Op:7  ======================================	

	public List<Res_Data_Booking_Receipt> get_Date_Booking_Receipt(int bookingId, HttpServletResponse response) throws IOException {
		
		System.out.println(bookingId);
		
		try {
			 Class.forName(driver).newInstance();
			 con = DriverManager.getConnection(url+dbName,userName,password);
			 // Statement st = con.createStatement();
			 
			 String qu1 = "select distinct bk.booking_id, ps.p_email, ps.p_name, ps.p_phone, ps.p_address, ps.url,\n"
			 		+ "	      ss.journey_date, ss.journey_time,\n"
			 		+ "       rd.source_point, rd.destination_point, rd.distance_km,\n"
			 		+ "       sd.ship_name, sd.ship_model,\n"
			 		+ "       bk.no_of_head_count, bk.payment_amount\n"
			 		+ "from passenger ps\n"
			 		+ "inner JOIN booking bk\n"
			 		+ "ON bk.p_email = ps.p_email\n"
			 		+ "inner JOIN ship_schedule ss\n"
			 		+ "ON ss.ss_id = bk.ship_sch_id\n"
			 		+ "inner JOIN head_count hc\n"
			 		+ "ON hc.hc_booking_id = bk.booking_id\n"
			 		+ "inner JOIN route_details rd\n"
			 		+ "ON rd.route_id = ss.route_id\n"
			 		+ "inner JOIN ship_details sd\n"
			 		+ "ON sd.ship_id = ss.ship_details_id\n"
			 		+ "and bk.booking_id=?;\n" ;
			 
			  PreparedStatement pstmt1 = con.prepareStatement(qu1);
			  pstmt1.setInt(1, bookingId);     // dynamic bookingId
			  ResultSet rs1 = pstmt1.executeQuery();
			  
			  	// Create a new PDF document
		        PDDocument document = new PDDocument();
		        PDPage page = new PDPage(PDRectangle.A4);
		        document.addPage(page);
		        
		        // Create a new content stream
		        PDPageContentStream contentStream = new PDPageContentStream(document, page);

		        // Set the font and font size for the content
		        contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 20);

		        // Set the starting position for the content
		        float y = page.getMediaBox().getHeight() - 50;
		        float x = 0 + 125;
		        System.out.println("y value"+y);
		        
		        // Write the booking details to the content stream
		        contentStream.beginText();
		        
		        String title = "   ... Wish you a Happy Journey ahead ...";
		        contentStream.newLineAtOffset(x, y);
		        contentStream.showText(title);
		        contentStream.newLineAtOffset(0, -20);
		        contentStream.showText("     ---------------------------------------------");
		        
		        // Set the font and font size for the content
		        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		        x = -80;
		        
			  while( rs1.next() ) { 
					  
				  System.out.println("======= Receipt Data Service =======");
					    System.out.println(y);
				        contentStream.newLineAtOffset(x, -40);
				        contentStream.showText("Booking ID: " + rs1.getInt(1));
				        contentStream.newLineAtOffset(0, -20);
				        contentStream.showText("============================================================");
			        
			     // ===================*******************=====================  //

					  y += 80;
			            contentStream.newLineAtOffset(0, -40);
			            contentStream.showText("User Email: " + rs1.getString(2));

			            contentStream.newLineAtOffset(0, -20);
			            contentStream.showText("User Name: " + rs1.getString(3));

			            contentStream.newLineAtOffset(0, -20);
			            contentStream.showText("User Phone No: " + rs1.getString(4));

			            contentStream.newLineAtOffset(0, -20);
			            contentStream.showText("User Address: " + rs1.getString(5));
		            
			            // contentStream.newLineAtOffset(0, -20);
			            // contentStream.showText("Photo URl: " + rs.getString(6));

			     // ===================*******************=====================  //	

			            contentStream.newLineAtOffset(0, -40);
			            contentStream.showText("Journey Date: " + rs1.getString(7));

			            contentStream.newLineAtOffset(0, -20);
			            contentStream.showText("Journey Time: " + rs1.getString(8));

				 // ===================*******************=====================  //	
			        
			            contentStream.newLineAtOffset(0, -40);
			            contentStream.showText("Source Point: " + rs1.getString(9));

			            contentStream.newLineAtOffset(0, -20);
			            contentStream.showText("Destination Point: " + rs1.getString(10));

			            contentStream.newLineAtOffset(0, -20);
			            contentStream.showText("Distance in km: " + rs1.getInt(11));
		            
		         // ===================*******************=====================  //	

					    contentStream.newLineAtOffset(0, -40);
			            contentStream.showText("Ship Name: " + rs1.getString(12));

					    contentStream.newLineAtOffset(0, -20);
			            contentStream.showText("Ship Model: " + rs1.getString(13));
			            
			     // ===================*******************=====================  //

					    contentStream.newLineAtOffset(0, -40);
			            contentStream.showText("No Of Seat Booked: " + rs1.getInt(14));

					    contentStream.newLineAtOffset(0, -20);
			            contentStream.showText("Payment Amount: " + rs1.getInt(15)+"/-");
			            contentStream.newLineAtOffset(0, -30);
				        contentStream.showText("============================================================");
			            
			      // ===================*******************=====================  //
				  
			  } 
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
		        
		        con.close();
			    rs1.close();
			    pstmt1.close();
		}
		catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
		}
		
		
		return null;
	}
	
// ================== : Download : Retrieve Date for Head-Count-Receipt : Op:8 =====================================================
	
	public List<Res_Data_Booking_Receipt> get_Data_HC_Receipt(int bookingId, HttpServletResponse response) throws IOException {
		
		try {
			 Class.forName(driver).newInstance();
			 con = DriverManager.getConnection(url+dbName,userName,password);
			 
			 String qu2 = "select hc.hc_name, hc.hc_age, hc.hc_gender\n"
				 		+ "from passenger ps\n"
				 		+ "inner JOIN booking bk\n"
				 		+ "ON bk.p_email = ps.p_email\n"
				 		+ "inner JOIN ship_schedule ss\n"
				 		+ "ON ss.ss_id = bk.ship_sch_id\n"
				 		+ "inner JOIN head_count hc\n"
				 		+ "ON hc.hc_booking_id = bk.booking_id\n"
				 		+ "inner JOIN route_details rd\n"
				 		+ "ON rd.route_id = ss.route_id\n"
				 		+ "inner JOIN ship_details sd\n"
				 		+ "ON sd.ship_id = ss.ship_details_id\n"
				 		+ "and bk.booking_id=?;";
				 
			  PreparedStatement pstmt2 = con.prepareStatement(qu2);  
			  pstmt2.setInt(1, bookingId);     // dynamic bookingId
			  ResultSet rs2 = pstmt2.executeQuery();
				  
		        PDDocument document = new PDDocument();
		        PDPage page = new PDPage(PDRectangle.A4);
		        document.addPage(page);
		        
		        PDPageContentStream contentStream = new PDPageContentStream(document, page);

		        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);

		        float y = page.getMediaBox().getHeight() - 30;  // 30
		        System.out.println("y value"+y);
		        
		        contentStream.beginText();
		        int count =0;

			  while( rs2.next() ) {
				  System.out.println("======= Receipt HC Service =======");
				    y -= 250;
				  System.out.println("y value"+y);
				    contentStream.newLineAtOffset(50, y);
		            contentStream.showText("Head-Count Name: " + rs2.getString(1));

		            y -= 50;
				    contentStream.newLineAtOffset(0, -20);
		            contentStream.showText("Head-Count Age: " + rs2.getInt(2));

			        contentStream.newLineAtOffset(0, -20);
		            contentStream.showText("Head-Count Gender: " + rs2.getString(3));
		            
		            contentStream.newLineAtOffset(0, -20);
		            contentStream.showText("**************************************"+count);
		            count++;
		            //y = page.getMediaBox().getHeight() - 100;
		            
		            System.out.println(rs2.getString(1));
			  }
			  
			    System.out.println("Results");
			    // st.close();
			    con.close();
			    rs2.close();
			    pstmt2.close();
			    
			    // Add more booking details here as needed
		        contentStream.endText();

		        // Close the content stream
		        contentStream.close();

		        // Set the response headers for the PDF file
		        response.setContentType("application/pdf");
		        response.setHeader("Content-Disposition", "inline; filename=hc_receipt.pdf");

		        // Save the PDF document to the response output stream
		        document.save(response.getOutputStream());

		        // Close the PDF document
		        document.close();	 
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
// ========================== : Retrieve Booking Date for Email-Sending : Op:9  ======================================	

	public List<Res_Data_Booking_Receipt> get_Date_Booking_for_Email(int bookingId) {
		
		System.out.println(bookingId);
		
		List<Res_Data_Booking_Receipt> rdbr_list = new ArrayList<>();
		
		try {
			Class.forName(driver).newInstance();
			 con = DriverManager.getConnection(url+dbName,userName,password);
			 // Statement st = con.createStatement();
			 
			 String qu1 = "select bk.booking_id, ps.p_email, ps.p_name, ps.p_phone, ps.p_address, ps.url,\n"
			 		+ "	      ss.journey_date, ss.journey_time,\n"
			 		+ "       rd.source_point, rd.destination_point, rd.distance_km,\n"
			 		+ "       sd.ship_name, sd.ship_model,\n"
			 		+ "       hc.hc_name, hc.hc_age, hc.hc_gender,\n"
			 		+ "       bk.no_of_head_count, bk.payment_amount\n"
			 		+ "from passenger ps\n"
			 		+ "inner JOIN booking bk\n"
			 		+ "ON bk.p_email = ps.p_email\n"
			 		+ "inner JOIN ship_schedule ss\n"
			 		+ "ON ss.ss_id = bk.ship_sch_id\n"
			 		+ "inner JOIN head_count hc\n"
			 		+ "ON hc.hc_booking_id = bk.booking_id\n"
			 		+ "inner JOIN route_details rd\n"
			 		+ "ON rd.route_id = ss.route_id\n"
			 		+ "inner JOIN ship_details sd\n"
			 		+ "ON sd.ship_id = ss.ship_details_id\n"
			 		+ "and bk.booking_id=?;\n" ;
			 
			  PreparedStatement pstmt = con.prepareStatement(qu1);
			  pstmt.setInt(1, bookingId);     // dynamic bookingId
			  ResultSet rs = pstmt.executeQuery();
			  
			  while( rs.next() ) {
				  Res_Data_Booking_Receipt rdbr = new Res_Data_Booking_Receipt();
				  
				  rdbr.setBokingId(rs.getInt(1));
				  rdbr.setpEmail(rs.getString(2));
				  rdbr.setpName(rs.getString(3));
				  rdbr.setpPhone(rs.getString(4));
				  rdbr.setpAddress(rs.getString(5));
				// rdbr.setUrl(rs.getString(6));
				  
				  rdbr.setJourneyDate(rs.getNString(7));
				  rdbr.setJourneyTime(rs.getString(8));
				  
				  rdbr.setSourcePoint(rs.getString(9));
				  rdbr.setDestinationPoint(rs.getString(10));
				  rdbr.setDistancekm(rs.getInt(11));
				  
				  rdbr.setShipName(rs.getString(12));
				  rdbr.setShipModel(rs.getString(13));
				  
				  rdbr.setHcName(rs.getString(14));
				  rdbr.setHcAge(rs.getInt(15));
				  rdbr.setHcGender(rs.getString(16));
				  
				  rdbr.setNoOfHeadCount(rs.getInt(17));
				  rdbr.setPaymentAmount(rs.getInt(18));
				  
				  rdbr_list.add(rdbr);
			  }
			  System.out.println("Results");
			  // st.close();
			  con.close();
			  rs.close();
			  pstmt.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return rdbr_list;
	}
	
// ==============================================================================================================
// ==============================================================================================================
	
	
}
