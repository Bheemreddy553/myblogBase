//======================================================================
//Copyright 2015, XYZ, Inc.
//======================================================================

package com.synycs.truckbay.common.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * This is an abstract class that a non-static inner class of a DAO
 * must extend to create a java.sql.PreparedStatement.
 *
 * A non-static nested class should be created for every SQL
 * statment used by the DAO and the nested class must extend
 * either the SQLStatement or the SQLPreparedStatement abstract classes.
 *
 * @author Created By Srahanj and Bhargav
 */
public abstract class SQLPreparedStatement
{
	protected String statementStr;
	private static final Logger logger = LoggerFactory.getLogger(SQLPreparedStatement.class);
	/**
	 * Returns a java.sql.PreparedStatement using the given connection
	 * and the object's SQL statement string.
	 *
	 * @param con A database connection
	 *
	 * @return PreparedStatment created with the given connection.
	 *
	 */
	public PreparedStatement getStatement(Connection con) throws Exception
	{
		logger.info("SQL: {}", statementStr);
		return con.prepareStatement(statementStr);
	}

	public PreparedStatement getStatementReturnKeys(Connection con) throws Exception
	{
		logger.info("SQL: {}", statementStr);
		return con.prepareStatement(statementStr,Statement.RETURN_GENERATED_KEYS);
	}

	public CallableStatement getCallableStatement(Connection con) throws Exception
	{
		logger.info("SQL: {}", statementStr);
		return con.prepareCall(statementStr);

	}

	
	/**
	 * Return the embedded SQL string associated with this statement.
	 * @return SQL string
	 */
	public String toString()
	{
		return this.statementStr;
	}
}
