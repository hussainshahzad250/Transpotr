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

import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.SubsidiaryClientOfOrg;
import com.trux.service.SubsidiaryClientUserService;

@Controller
@RequestMapping(value = "/subapi")
public class SubsidiaryClientController {

	
	@Autowired
	SubsidiaryClientUserService  subsidiaryClientUserService;
	@ResponseBody
	@RequestMapping(value = "/subclient", method = RequestMethod.GET)
	private List<SubsidiaryClientOfOrg> subclient(HttpServletRequest request,HttpServletResponse response, @RequestParam Integer userId) {
    List<SubsidiaryClientOfOrg> subList = new ArrayList<SubsidiaryClientOfOrg>();
 	try {
		if(userId != null && userId!=0) { 
			subList=subsidiaryClientUserService.getSubsidiaryClientOfOrgListByUserId(userId);
			 }
		if(subList!=null && subList.size()>0){
			 return subList;
		} else {
			SubsidiaryClientOfOrg dto=new SubsidiaryClientOfOrg();
			dto.setErrorCode("200");
			dto.setErrorMessage("Subsidiary client does not exist");
			subList = new ArrayList<SubsidiaryClientOfOrg>();
			subList.add(dto);
			 return subList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			SubsidiaryClientOfOrg dto=new SubsidiaryClientOfOrg();
			dto.setErrorCode("200");
			dto.setErrorMessage("Subsidiary client does not exist");
			subList = new ArrayList<SubsidiaryClientOfOrg>();
		 subList.add(dto);
		 return subList;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/cleintWithsubclient", method = RequestMethod.GET)
	private List<SubsidiaryClientOfOrg> cleintWithsubclient(HttpServletRequest request,HttpServletResponse response, @RequestParam Integer userId,@RequestParam Integer cleintId) {
    List<SubsidiaryClientOfOrg> subList = new ArrayList<SubsidiaryClientOfOrg>();
 	try {
		if(userId != null && userId!=0) { 
			subList=subsidiaryClientUserService.getSubsidiaryClientOfOrgListByUserId(userId,cleintId);
			 }
		if(subList!=null && subList.size()>0){
			 return subList;
		} else {
			SubsidiaryClientOfOrg dto=new SubsidiaryClientOfOrg();
			dto.setErrorCode("402");
			dto.setErrorMessage("Subsidiary client does not exist");
			subList = new ArrayList<SubsidiaryClientOfOrg>();
			subList.add(dto);
			 return subList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			SubsidiaryClientOfOrg dto=new SubsidiaryClientOfOrg();
			dto.setErrorCode("200");
			dto.setErrorMessage("Subsidiary client does not exist");
			subList = new ArrayList<SubsidiaryClientOfOrg>();
		 subList.add(dto);
		 return subList;
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/driverMobileVehicleList", method = RequestMethod.GET)
	private List<DriverDeviceVehicleMapping> driverMobileVehicleList(HttpServletRequest request,HttpServletResponse response, @RequestParam Integer subClientId) {
    List<DriverDeviceVehicleMapping> subList = new ArrayList<DriverDeviceVehicleMapping>();
 	try {
		if(subClientId != null && subClientId!=0) { 
			subList=subsidiaryClientUserService.getDriverModileVehicle(subClientId);
			 }
		if(subList!=null && subList.size()>0){
			 return subList;
		} else {
			DriverDeviceVehicleMapping dto=new DriverDeviceVehicleMapping();
			dto.setErrorCode("200");
			dto.setErrorMessage("Subsidiary client related records does not exist");
			subList.add(dto);
			 return subList;
			}
		} catch (Exception er) {
			er.printStackTrace();
			DriverDeviceVehicleMapping dto=new DriverDeviceVehicleMapping();
			dto.setErrorCode("200");
			dto.setErrorMessage("Subsidiary client related records does not exist");
			subList.add(dto);
			 return subList;
		}
	}

	
}
