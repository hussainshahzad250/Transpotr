package com.trux.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.VehicleLocation;
import com.trux.utils.DateFormaterUtils;
@Controller

@RequestMapping(value="/location")
public class HearBeetController {
	@ResponseBody
	@RequestMapping(value = "/sendVehicleHeartBeatLease", method = RequestMethod.POST)
	public VehicleLocation  sendVehicleHeartBeatLease(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer driverId,
			@RequestParam Integer bookingId,
			@RequestParam String driverMobile,
			@RequestParam Integer companySubId,
			@RequestParam Double vehicleLat,
			@RequestParam Double vehicleLong){
try{
		if(driverMobile == null)
			return new VehicleLocation(TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getCode(), TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getDescription());
		
		if(vehicleLat == null)
			return new VehicleLocation(TruxErrorCodes.INVALID_LATITUDE.getCode(), TruxErrorCodes.INVALID_LATITUDE.getDescription());
		
		if(vehicleLong == null)
			return new VehicleLocation(TruxErrorCodes.INVALID_LONGITUDE.getCode(), TruxErrorCodes.INVALID_LONGITUDE.getDescription());
		
		 
		
		try {
			 Session cassandraSession = null;
			/*if(CassandraConnector.getCassandraSession()!=null){
				   cassandraSession = CassandraConnector.getCassandraSession();
				   long epochTMS = System.currentTimeMillis()/1000;
					String ls_vh_loct_id =""+epochTMS;
					if(driverId!=null && !driverId.equals("")){
					  ls_vh_loct_id = ls_vh_loct_id+""+driverId;
					}
					String query1 = "INSERT INTO leased_vehicle_location (ls_vh_loct_id, booking_id, driver_id, driver_mobile, company_sub_id, vehicle_lat, vehicle_long, loct_time) VALUES("+ls_vh_loct_id+", "+bookingId+","+driverId+", "+driverMobile+", "+companySubId+", "+ vehicleLat+", "+vehicleLong+", '"+ DateFormaterUtils.convertGMTToISTWithDates(new Date().toString()).replace(".0", "").trim()+"');" ;
					System.out.println(query1);
				    cassandraSession.execute(query1);
				    cassandraSession.close();
			}else{*/
	 	Cluster.Builder cassandraCluster = Cluster.builder().addContactPoints("54.169.184.149").withPort(9042).withCredentials("cassandra", "password@123");
	     cassandraSession = cassandraCluster.build().connect("hb");
		long epochTMS = System.currentTimeMillis()/1000;
		String ls_vh_loct_id =""+epochTMS;
		if(driverId!=null && !driverId.equals("")){
		  ls_vh_loct_id = ls_vh_loct_id+""+driverId;
		}
		String query1 = "INSERT INTO leased_vehicle_location (ls_vh_loct_id, booking_id, driver_id, driver_mobile, company_sub_id, vehicle_lat, vehicle_long, loct_time) VALUES("+ls_vh_loct_id+", "+bookingId+","+driverId+", "+driverMobile+", "+companySubId+", "+ vehicleLat+", "+vehicleLong+", '"+ DateFormaterUtils.convertGMTToISTWithDates(new Date().toString()).replace(".0", "").trim()+"');" ;
		System.out.println(query1);
	    cassandraSession.execute(query1);
	    cassandraSession.close();
	    /*}*/
	    VehicleLocation dto=new VehicleLocation();
	    dto.setDriverNumber(driverMobile);
	    dto.setLatitude(vehicleLat);
	    dto.setLongitude(vehicleLong);
	    return dto;
		//--CASSANDRA--//
		} catch (Exception e) {
			e.printStackTrace();
			return new VehicleLocation(TruxErrorCodes.INVALID_LONGITUDE.getCode(), TruxErrorCodes.INVALID_LONGITUDE.getDescription());
			
		}	
}catch(Exception er){
	return new VehicleLocation(TruxErrorCodes.INVALID_LONGITUDE.getCode(), TruxErrorCodes.INVALID_LONGITUDE.getDescription());
	
}
		
	}
}
