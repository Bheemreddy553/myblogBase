/**
 * 
 */
package com.synycs.truckbay.server;

/**
 * @author  Srahanj and Bhargav
 * for verification in cas eof forget password
 *
 */
public class Authentication 
{
	
	private int 		verificationCode			= -1;
	private int 		mobileNumber				= -1;
	
	 // It can be a boolean. but, we don't have DB data type for boolean hence using int.
	private int			isAuthenticated				= 0;
	
	public Authentication() { }

	/**
	 * @param verificationCode
	 * @param mobileNumber
	 * @param isAuthenticated
	 */
	public Authentication(int verificationCode, int mobileNumber,
			int isAuthenticated) {
		super();
		this.verificationCode = verificationCode;
		this.mobileNumber = mobileNumber;
		this.isAuthenticated = isAuthenticated;
	}

	/**
	 * @return the verificationCode
	 */
	public int getVerificationCode() {
		return verificationCode;
	}

	/**
	 * @param verificationCode the verificationCode to set
	 */
	public void setVerificationCode(int verificationCode) {
		this.verificationCode = verificationCode;
	}

	/**
	 * @return the mobileNumber
	 */
	public int getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the isAuthenticated
	 */
	public int getIsAuthenticated() {
		return isAuthenticated;
	}

	/**
	 * @param isAuthenticated the isAuthenticated to set
	 */
	public void setIsAuthenticated(int isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
}
