/**
 * 
 */
package com.synycs.truckbay.server;
 

import javax.xml.bind.annotation.XmlRootElement; 
import java.util.Date;

/**
 * @author honey
 * maintains user details
 *
 */
@XmlRootElement
public class UserDetails
{
	private String userName;
	private String email;
	private String password;
	private String mobileNumber;
	private Date   registeredTime;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Date getRegisteredTime() {
		return registeredTime;
	}
	public void setRegisteredTime(Date registeredTime) {
		this.registeredTime = registeredTime;
	}
	
	
}
