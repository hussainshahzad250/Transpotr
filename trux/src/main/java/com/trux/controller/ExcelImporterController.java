/*package com.trux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.trux.model.FileBean;
import com.trux.service.importService;

@Controller
@RequestMapping("/upload")
public class ExcelImporterController {

	@Autowired
	private importService importService;

	@RequestMapping(method = RequestMethod.POST)
	public String upload(FileBean fileBean, BindingResult result) {
		importService.importfile(fileBean);
		return "importsucces";
	}

}
*/