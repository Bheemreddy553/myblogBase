//======================================================================
//Copyright 2015, XYZ, Inc.
//======================================================================

package com.synycs.truckbay.server.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This interface provides methods to get the connections and others.
 *
 * @author Created By Srahanj and Bhargav
 */
/**
 * ToDo manage sql connection pool
 */
public class DatasourceManager implements TBDatabase
{
	/*
	 * MySQL	:::: com.mysql.jdbc.Driver 				::::	jdbc:mysql://hostname/ databaseName
	 * ORACLE	:::: oracle.jdbc.driver.OracleDriver	::::	jdbc:oracle:thin:@hostname:port Number:databaseName
	 * DB2		:::: COM.ibm.db2.jdbc.net.DB2Driver		::::	jdbc:db2:hostname:port Number/databaseName
	 * Sybase	:::: com.sybase.jdbc.SybDriver			::::	jdbc:sybase:Tds:hostname: port Number/databaseName
	 *
	 * */



	private static final Logger logger = LoggerFactory.getLogger(DatasourceManager.class);

	private final static String mysqlDriver = "com.mysql.jdbc.Driver";
	private final static String orclDriver = "oracle.jdbc.driver.OracleDriver";
	private final static String googleDriver="com.mysql.jdbc.GoogleDriver";

	public enum DatasourceType { MYSQL, ORACLE ,GOOGLE};

	private String databaseHostName 	= null;
	private String databaseName	 	= null;
	private int				portNumber			= -1;
	private String databaseUserName 	= null;
	private String databasePassword 	= null;
	private DatasourceType 	datasourceType	 	= DatasourceType.ORACLE;


	public DatasourceManager(String databaseName, String databaseHostName, int portNumber,
							 String databaseUserName, String databasePassword, DatasourceType datasourceType)
	{

		this.databaseName 		= databaseName;
		this.databaseHostName 	= databaseHostName;
		this.portNumber 		= portNumber;
		this.databaseUserName 	= databaseUserName;
		this.databasePassword 	= databasePassword;
		this.datasourceType		= datasourceType;
	}

	private String getConnectionString()
	{
		//MYSQL :: String connectionStr = "jdbc:mysql://localhost:3306/mkyongcom";
		// ORACLE : "jdbc:oracle:thin:@localhost:1521:mkyong",

		String dataSource = "mysql";
		if ( DatasourceType.ORACLE.compareTo(datasourceType)==0 )
			dataSource = "oracle";
		else if(DatasourceType.GOOGLE.compareTo(datasourceType)==0)
			dataSource="google:mysql";

		logger.info("Getting connection string for : {}", datasourceType);

		StringBuffer connectionStr = new StringBuffer();

		connectionStr.append("jdbc:");
		connectionStr.append(dataSource);

		if ( DatasourceType.ORACLE == datasourceType )
			connectionStr.append(":thin:@");
		else
			connectionStr.append("://");

		connectionStr.append(databaseHostName);
		if(portNumber!=-1) {
			connectionStr.append(":");
			connectionStr.append(portNumber);
		}
		if ( datasourceType == DatasourceType.ORACLE )
			connectionStr.append(":");
		else
			connectionStr.append("/");

		connectionStr.append(databaseName);
		connectionStr.append("?characterEncoding=UTF-8");

		logger.info("connection string : ", connectionStr);
		System.out.println("connection string "+ connectionStr);


		return connectionStr.toString();
	}

	@Override
	public Connection getConnection() throws SQLException
	{
		// TODO Auto-generated method stub

		Connection connection = null;

		try
		{

			if ( DatasourceType.ORACLE .compareTo(datasourceType)==0 )
				Class.forName(orclDriver);
			else if(DatasourceType.GOOGLE.compareTo(datasourceType)==0)
				Class.forName(googleDriver);
			else
				Class.forName(mysqlDriver);

		}
		catch (ClassNotFoundException e)
		{
			logger.error("JDBC Driver NOT Found for : ", datasourceType);
			logger.error(e.getMessage());
			return null;
		}

		logger.info("getting connection using username: {}", databaseUserName);
		connection = DriverManager.getConnection(getConnectionString(), databaseUserName, databasePassword);
		connection.setAutoCommit(false);

		if (null != connection)
		{
			logger.info("Database connection successful for  : {}", datasourceType);
		} else
		{
			logger.info("Database connection failed for  : {}", datasourceType);
		}
		//connection.setAutoCommit(false);
		logger.info("Connected to database");
		return connection;

	}

	@Override
	public void closeConnection(Connection connection) throws SQLException
	{
		// TODO Auto-generated method stub

		logger.info("Closing the connection: {}", databaseUserName);
		connection.close();
	}

}
