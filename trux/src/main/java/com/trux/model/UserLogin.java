package com.trux.model;


/*@Component
@Scope("session")*/
public class UserLogin {
private int userId;
private String userName;
private String userPassword;
private String gcmId;
private String userRole;


public UserLogin() {
	super();
}
 
public UserLogin(String userName, String userPassword, String gcmId) {
	super();
	this.userName = userName;
	this.userPassword = userPassword;
	this.gcmId = gcmId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserPassword() {
	return userPassword;
}
public void setUserPassword(String userPassword) {
	this.userPassword = userPassword;
}
public String getUserRole() {
	return userRole;
}
public void setUserRole(String userRole) {
	this.userRole = userRole;
}
public String getGcmId() {
	return gcmId;
}
public void setGcmId(String gcmId) {
	this.gcmId = gcmId;
}

}
