/*
package com.synycs.truckbay.server.session;

import com.synycs.truckbay.common.exception.TBException;
import com.synycs.truckbay.server.Login;
import com.synycs.truckbay.server.UserDetails;
import com.synycs.truckbay.server.dao.retrievers.LoginDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager 
{
	private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);

	private Map<String, Session> sessionManager = new HashMap<String,Session>();
	private Map<String, Session> adminSessionManager = new HashMap<String,Session>();
	private Map<String,Session> transportCompanySession=new HashMap<>();
	private Map<String,Session> customerCareSession=new HashMap<>();

	private SessionManager() {}

	private static final SessionManager instance = new SessionManager();
	
	public static SessionManager getSessionManager()
	{
		return instance;
	}

	private String GenerateUniqueSessionID(boolean isItForAdmin)
	{
		UUID sessionID = null;
		String sessionIDStr = null;
		
		// Check if there are any existing sessions with this sessionID
		while(true)
		{
			sessionID = UUID.randomUUID();
			if(null != sessionID)
				sessionIDStr = sessionID.toString();
			if(null != sessionIDStr)
			{
				if((false == isItForAdmin) && (false == sessionManager.containsKey(sessionIDStr)))
					break;
				else if((true == isItForAdmin) && (false == adminSessionManager.containsKey(sessionIDStr)))
					break;
			} 
		}
		
		return sessionIDStr;
	}


	private String GenerateTransportSession()
	{
		UUID sessionID = null;
		String sessionIDStr = null;

		// Check if there are any existing sessions with this sessionID
		while(true)
		{
			sessionID = UUID.randomUUID();
			if(null != sessionID)
				sessionIDStr = sessionID.toString();
			if(null != sessionIDStr)
			{
				if(!transportCompanySession.containsKey(sessionIDStr)){
					break;
				}
			}
		}

		return sessionIDStr;
	}
	private String GenerateCustomerCareSession()
	{
		UUID sessionID = null;
		String sessionIDStr = null;

		// Check if there are any existing sessions with this sessionID
		while(true)
		{
			sessionID = UUID.randomUUID();
			if(null != sessionID)
				sessionIDStr = sessionID.toString();
			if(null != sessionIDStr)
			{
				if(!customerCareSession.containsKey(sessionIDStr)){
					break;
				}
			}
		}

		return sessionIDStr;
	}
	
	public String createSession(String loginName, String password) throws TBException
	{
		if((null != loginName) && (null != password) && 
		   (false == loginName.isEmpty()) && (false == password.isEmpty()) )
		{
			LoginDAO loginInfo = new LoginDAO(new Login(loginName,password));
			UserDetails userDetails = loginInfo.getLoginDetails();
			String sessionID = GenerateUniqueSessionID(false);
			sessionManager.put(sessionID, new Session(userDetails));
			
			return sessionID;
		}
		
		// throw
    	StringBuffer errorStr = new StringBuffer();
    	errorStr.append("Either login name or password is empty");
    	logger.error(errorStr.toString());
		throw new TBException("", errorStr.toString());
	}

	public Session getSession(String sessionID)
	{
		return sessionManager.get(sessionID);
	}
	
	public Session getAdminSession(String sessionID)
	{
		return adminSessionManager.get(sessionID);
	}

	public Session getTransportCompanySession(String sessionID){
		return transportCompanySession.get(sessionID);
	}
	public Session getCustomerCareSession(String sessionID)
	{
		return customerCareSession.get(sessionID);
	}

	
	public void updateSession(String sessionID) {
		// Is this required - lets review this later
	}

	public void deleteSession(String sessionID)
	{
		sessionManager.remove(sessionID);
	}
	
	public void deleteAdminSession(String sessionID)
	{
		adminSessionManager.remove(sessionID);
	}
	public void deleteTransportSession(String sessionID){
		transportCompanySession.remove(sessionID);
	}
	public void deleteCustomerCareSession(String sessionID)
	{
		customerCareSession.remove(sessionID);
	}
}

*/
