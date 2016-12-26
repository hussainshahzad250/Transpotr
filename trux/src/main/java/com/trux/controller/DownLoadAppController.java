package com.trux.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import config.DownloadUrlProvider;

@Controller
@RequestMapping("/download")
public class DownLoadAppController {

	private InputStream fileInputStream;
	private static final int BUFFER_SIZE = 4096;

	@RequestMapping(value = "/app", method = RequestMethod.GET)
	public void downloadApp(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		try {
			String realPath = request.getSession().getServletContext().getRealPath(DownloadUrlProvider.getDownloadAppFile());
			File downloadFile = new File(realPath);
			fileInputStream = new FileInputStream(downloadFile);
			String mimeType = request.getSession().getServletContext().getMimeType(realPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			//System.out.println("MIME type: " + mimeType);
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			OutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = fileInputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			fileInputStream.close();
			outStream.close();
		} catch (Exception e) {
			throw e;
		}

	}

}
