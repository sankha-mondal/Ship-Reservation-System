package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Ship_Schedule;
import com.helper_classes.Res_SD_SS_on_Sour_Dest;
import com.repository.ShipSchedule_Repository;

@Service
public class Ship_Schedule_Service {
	
	@Autowired
	ShipSchedule_Repository shipSchRepo;

//===============================================================================================================
	
	//  Retrieve data by journeyDate:-  Op: 1D
	
	public List<Ship_Schedule> findSSbyjDate(String jDate) {
		return shipSchRepo.findSSbyjDate(jDate);
	}
	
//  ============================================================================================================
//  ========================================: JDBC Operations :=================================================	
	
	 Connection con = null;
	 String url = "jdbc:mysql://localhost:3306/";
	 String dbName = "wiprodb";
	 String driver = "com.mysql.jdbc.Driver";
	 String userName = "root";
	 String password = "Sankha@12";
	 
// ============== : Retrieve Ship_Details & Ship_Schedule based ON Source and Destination : Op:7 ===================================	

		public List<Res_SD_SS_on_Sour_Dest> getAll_SD_SS(String source, String destination) {
			
			System.out.println(source+ "*******"+ destination );
			
			List<Res_SD_SS_on_Sour_Dest> rsssd_list = new ArrayList<>();
			
			try {
				
				 Class.forName(driver).newInstance();
				 con = DriverManager.getConnection(url+dbName,userName,password);
				 // Statement st = con.createStatement();
				 
				 
				 String qu = "select sd.ship_name, sd.ship_model, sd.ship_capacity, sd.ship_reservation_capacity, sd.per_km_rate, \n"
				 		+ "	   ss.journey_date, ss.journey_time, ss.seat_availability, ss.ss_id, sd.ship_id\n"
				 		+ "    from Ship_Details sd\n"
				 		+ "    Inner join Ship_Schedule ss ON\n"
				 		+ "    ss.ship_details_id = sd.ship_id\n"
				 		+ "    Inner join Route_Details rd ON\n"
				 		+ "    rd.source_point =? and rd.destination_point =? "
				 		+ "    and rd.route_id = ss.route_id and ss.seat_availability >10 \n"
				 		+ "    order by ss.journey_date;" ;
				 
				 
				  PreparedStatement pstmt = con.prepareStatement(qu);
				  
				  pstmt.setString(1, source);     // dynamic source
				  pstmt.setString(2, destination);  // dynamic destination
				  
				  ResultSet rs = pstmt.executeQuery();
				 
				  while( rs.next() ) { 
						
					  Res_SD_SS_on_Sour_Dest ressd = new Res_SD_SS_on_Sour_Dest();
					  
					  ressd.setShipName(rs.getString(1));
					  ressd.setShipModel(rs.getString(2));
					  ressd.setShipCapacity(rs.getInt(3));
					  ressd.setShipResCapacity(rs.getInt(4));
					  ressd.setPerKmRate(rs.getInt(5));
					  
					  ressd.setJourneyDate(rs.getString(6));
					  ressd.setJourneyTime(rs.getString(7));
					  ressd.setSeatAvailability(rs.getInt(8));
					  ressd.setSsId(rs.getString(9));
					  ressd.setShipId(rs.getString(10));
					  
					  rsssd_list.add(ressd);
					  
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
			
			
			return rsssd_list;
		}
			
	
//===============================================================================================================
//===============================================================================================================

}
