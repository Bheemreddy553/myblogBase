/**
 * 
 */
package com.synycs.truckbay.server.services;

import com.synycs.truckbay.common.exception.TBException;
import com.synycs.truckbay.server.dao.dbutils;

/**
 * @author  Srahanj and Bhargav
 *
 */
public class InitializeService 
{

	public static final InitializeService initService = new InitializeService();
	
	public static InitializeService getInstance() 
	{
		return initService;
	}

	private InitializeService()
	{
		
	}

	public void initializeIDs() throws TBException
	{
		dbutils.getInstance().initialize();
	}
}

