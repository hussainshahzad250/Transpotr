package com.trux.model;

public class AssignedStands {
	
	private String standName;
	private String subStandName;
	private int errorCode;
	private String errorMessage;
	
	public AssignedStands(){
		this.errorCode=101;
		this.errorMessage="Stand not assigned";
	}
	
	public String getStandName() {
		return standName;
	}
	public void setStandName(String standName) {
		this.standName = standName;
	}
	public String getSubStandName() {
		return subStandName;
	}
	public void setSubStandName(String subStandName) {
		this.subStandName = subStandName;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

}
