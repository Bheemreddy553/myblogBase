//======================================================================
//Copyright 2015, XYZ, Inc.
//======================================================================

package com.synycs.truckbay.server.datasource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This interface provides methods to get the connections and others.
 *
 * @author Created By Bhasker Reddy Amireddy
 */

public interface TBDatabase 
{
	
	public Connection getConnection() throws SQLException;

	public void closeConnection(Connection conn) throws SQLException;

}
