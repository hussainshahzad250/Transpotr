package com.trux.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.trux.model.ConsumerRegistration;
import com.trux.model.ForgetPassword;
import com.trux.service.ConsumerService;
import com.trux.service.RegistrationService;
import com.trux.utils.TruxUtils;

@Controller
@RequestMapping(value="/forgetPassword")
public class ForgetPasswordController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private ConsumerService consumerService; 
	
	
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	private ModelAndView forgetPasswordPAge(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
	return new ModelAndView("forgotPassword");//	TruxUtils.pageRedisrection(TruxConstant.FORGET_PASSWORD_PAGE, request, response);

	}
	
	
	@RequestMapping(value = "/email/submit", method = RequestMethod.GET)
	private void sendForgetPass(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		try{
		String email = request.getParameter("email");
		String passKey = null;
		try {
			passKey = TruxUtils.generateForgotPaswordCode(email);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		ForgetPassword forgetPassword = null;
		try {
			forgetPassword = new ForgetPassword(email, URLEncoder.encode(passKey,"UTF-8"), new Date());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		registrationService.saveOrUpdate(forgetPassword);
		
		TruxUtils.sendMail(email, passKey, 0, null, null);
		//  added by mithlesh
		ConsumerRegistration consumerRegistration=	consumerService.getUserDetailsByEmail(email);
		try {
		//	SMSTemplates.sendMessageToForgetPassword(consumerRegistration);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}}catch(Exception er){er.printStackTrace();}
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	private ModelAndView resetPassword(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		
		String passwordKey = request.getParameter("passKey");
		request.setAttribute("passwordKey", passwordKey);
		
	return new ModelAndView("resetPassword");//TruxUtils.pageRedisrection(TruxConstant.RESET_PASSWORD_PAGE, request, response);
			
	}
	
	@RequestMapping(value = "/setNewPassword", method = RequestMethod.POST)
	private ModelAndView setNewPAssword(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		try{
		String newPassword = request.getParameter("newPassword");
		String passwordKey = null;
		ForgetPassword forgetPassword = null;
		try {
			passwordKey = request.getParameter("passwordKey");
			if(passwordKey!=null)
			forgetPassword = registrationService.getEmailByPassKey(URLEncoder.encode(passwordKey.replaceAll(" ", "+"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if(forgetPassword != null){
			ConsumerRegistration consumerRegistration = consumerService.getUserDetailsByEmail(forgetPassword.getEmail());
			
			String newEncryptedPassword = TruxUtils.computeMD5Hash(newPassword);
			
			consumerRegistration.setPassword(newEncryptedPassword);
			
			consumerService.saveOrUpdate(consumerRegistration);
			
			return new ModelAndView ("forgetPassFinal");//TruxUtils.pageRedisrection("/pages/forgetPassFinal.jsp", request, response);
		}
		return new ModelAndView ("massege");
		}catch(Exception er){er.printStackTrace();return new ModelAndView ("massege");}
	}
	
	
}
