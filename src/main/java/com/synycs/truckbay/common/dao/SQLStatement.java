//======================================================================
//Copyright 2015, XYZ, Inc.
//======================================================================

package com.synycs.truckbay.common.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This is an abstract class that a non-static inner class of a DAO
 * must extend to create a java.sql.Statement.
 * 
 * A non-static nested class should be created for every SQL
 * statement used by the DAO and the nested class must extend
 * either the SQLStatement or the SQLPreparedStatement abstract classes.
 *
 * @author Created By Srahanj and Bhargav
 */
public abstract class SQLStatement
{
	protected String statementStr;
	private static final Logger logger = LoggerFactory.getLogger(SQLStatement.class);
	/**
	 * Executes the query for this class' SQL statement
	 * 
	 * @param con A database connection
	 * 
	 * @return ResultSet from executing the query
	 * 
	 */
	public ResultSet executeQuery(Connection con) throws Exception
	{
		logger.info("SQL: {}", statementStr);
		Statement stmt = con.createStatement();
		return stmt.executeQuery(statementStr);
	}

	/**
	 * Executes this class' SQL statement
	 * 
	 * @param con A database connection
	 * 
	 * @return int row count of records processed by the statement
	 * 
	 */
	public int executeUpdate(Connection con) throws Exception
	{
		logger.info("SQL: {}", statementStr);
		Statement stmt = con.createStatement();
		return stmt.executeUpdate(statementStr);
	}
}

