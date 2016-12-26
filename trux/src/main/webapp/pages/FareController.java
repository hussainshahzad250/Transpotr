package com.trux.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trux.model.FareEstimates;
import com.trux.model.FareRates;
import com.trux.service.FareService;

@Controller
@RequestMapping(value="/fare")
public class FareController {

	
	@Autowired
	private FareService fareService;
	
	@ResponseBody
	@RequestMapping(value="/getallfare", method=RequestMethod.GET)
	private List<FareRates> registration(HttpServletRequest request,HttpServletResponse response, @RequestParam(required=false) String truckcategory){
		 List<FareRates> fareRates =  fareService.getFareList();
		if(truckcategory == null || truckcategory.trim().length()==0)
		return fareRates;
		else {
			List<FareRates> rates = new ArrayList<FareRates>();
			for(int i = 0 ;i < fareRates.size(); i++) {
				if(fareRates.get(i).getTruck_category().equalsIgnoreCase(truckcategory))
				{
					rates.add(fareRates.get(i));
					break;
				}
			}
			
			return rates;
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/estimate", method=RequestMethod.POST)
	private  FareEstimates estimate(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required=false) String truckcategory, @RequestParam(required=false) String source, @RequestParam(required=false) String destination){
		
		     return  FareEstimates.getHardCodedInstance(truckcategory);
		     
	}
	
	

	

}
