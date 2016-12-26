package com.trux.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.trux.model.DesboardInfo;
import com.trux.service.DesboardInfoService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private DesboardInfoService desboardInfoService;
	private List<DesboardInfo> desboardInfo;

	@RequestMapping(value = "/login")
	private ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

		desboardInfo = desboardInfoService.getDesboardInfo();
		request.setAttribute("desboardInfo", desboardInfo);
		ModelAndView model = new ModelAndView("login");
		return model;

	}

	public List<DesboardInfo> getDesboardInfo() {
		return desboardInfo;
	}

	public void setDesboardInfo(List<DesboardInfo> desboardInfo) {
		this.desboardInfo = desboardInfo;
	}

}
