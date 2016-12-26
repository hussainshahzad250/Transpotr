package com.trux.service;

import org.springframework.web.multipart.MultipartFile;

import com.trux.model.FileBean;

public interface importService {

    public void importfile(MultipartFile fileData);

	public void save(FileBean fileBean);

	/*public void saveorupdate(FileBean fileBean);*/
}