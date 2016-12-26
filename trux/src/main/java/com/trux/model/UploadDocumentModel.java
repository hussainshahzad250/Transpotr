package com.trux.model;

public class UploadDocumentModel {

private String fileName;
private String uploadURL;
private String uploadStatus;
private String errorMessage;

public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public String getUploadURL() {
	return uploadURL;
}
public void setUploadURL(String uploadURL) {
	this.uploadURL = uploadURL;
}
public String getUploadStatus() {
	return uploadStatus;
}
public void setUploadStatus(String uploadStatus) {
	this.uploadStatus = uploadStatus;
}
public String getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}


}
