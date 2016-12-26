package com.trux.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/getAdditionalRideDetails")
public class RideAdditionalDetailsController {
	
	@RequestMapping(value = "/bookingDetails", method = RequestMethod.GET)
	private ModelAndView bookUserRide(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {

		String bookingId = request.getParameter("bookingId");
		request.setAttribute("bookingId", bookingId);
		return new ModelAndView("vehicleLocation");//TruxUtils.pageRedisrection("/pages/vehicleLocation.jsp", request, response);

	}

}
