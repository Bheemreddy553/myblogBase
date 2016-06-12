/**
 * 
 */
package com.synycs.truckbay.common.exception;

/**
 * @author Srahanj and Bhargav
 *
 */
public class TBException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1440980871620381930L;
	
	private String errorCode			= null;
	private String errorMessage			= null;
	private String additionalDetails	= null;
	
	public TBException() { }

	public TBException(String errorCode, String errorMessage)
	{
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.additionalDetails = "";
	}
	
	public TBException(String errorCode, String errorMessage, String additionalDetails)
	{ 
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.additionalDetails = additionalDetails;		
	}
	
	/**
	 * @return the errorCode
	 */
	public String getErrorCode()
	{
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the additionalDetails
	 */
	public String getAdditionalDetails()
	{
		return additionalDetails;
	}

	/**
	 * @param additionalDetails the additionalDetails to set
	 */
	public void setAdditionalDetails(String additionalDetails)
	{
		this.additionalDetails = additionalDetails;
	}
}
