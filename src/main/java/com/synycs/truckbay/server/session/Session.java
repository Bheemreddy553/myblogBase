/**
 * 
 */
package com.synycs.truckbay.server.session;
import com.synycs.truckbay.server.UserDetails;

/**
 * @author Srahanj and Bhargav
 *
 */
public class Session 
{

	private UserDetails		userDetails					= null;




	// We will add any session specific data here
	
	public Session(UserDetails userDetails)
	{
		this.userDetails 	= userDetails;
	}

	/**
	 * @return the userDetails
	 */
	public UserDetails getUserDetails() 
	{
		return userDetails;
	}

	/**
	 * @param userDetails the userDetails to set
	 */
	public void setUserDetails(UserDetails userDetails) 
	{
		this.userDetails = userDetails;
	}
}
