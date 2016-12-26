package com.trux.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trux.model.TrackFlashedBooking;
import com.trux.service.TrackFlashedBookingService;

@Controller
@RequestMapping(value = "/request")
public class TrackFlashedBookingController {

	Collection<Object> trackbooking=new ArrayList<Object>();
	@Autowired
	TrackFlashedBookingService trackFlashedBookingService;

	@ResponseBody
	@RequestMapping(value = "/trackFlashedBooking", method = RequestMethod.GET)
	public TrackFlashedBooking trackFlashedBooking(HttpServletRequest request,	HttpServletResponse response, @RequestParam long bookingId,	@RequestParam String mobile) {		
		TrackFlashedBooking dto = new TrackFlashedBooking(mobile, bookingId,new Date());
		try{
		trackbooking.add(dto);
		trackFlashedBookingService.trackFlashedBooking(trackbooking);
		trackbooking=new ArrayList<Object>();
		}catch(Exception er){er.printStackTrace();}
		return dto;
	}
}
