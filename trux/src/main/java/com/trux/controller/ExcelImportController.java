/*package com.trux.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.trux.model.FileBean;
@Controller
public class ExcelImportController {

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String importExcel(ModelMap modelMap,HttpServletRequest request,	HttpServletResponse response,@RequestParam MultipartFile fileData ) {
		
		modelMap.addAttribute("fileBean", new FileBean());
		return "importExcel";
	}

}
*/