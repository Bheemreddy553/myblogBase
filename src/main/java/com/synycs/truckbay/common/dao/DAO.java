//======================================================================
//Copyright 2015, XYZ, Inc.
//======================================================================

package com.synycs.truckbay.common.dao;

import java.lang.reflect.Constructor;
import java.sql.CallableStatement;

/**
 * This is an abstract class that provides common DAO routines.
 * All DAO classes must extend this abstract class.
 *
 * @author Created By Srahanj and Bhargav
 */
public abstract class DAO
{

	// For tracing when exceptions are caught
	protected String lastStatementStr;

	/**
	 * Use reflection to determine if this instance's actual
	 * class is the generic SQL class or a database specific one such
	 * as for Oracle.  Return an instance of the non-static inner class
	 * corresponding to the actual outer class.
	 * 
	 * @param SQLInnerClassName The name of a non-static nested class which
	 *                          implements a SQLStatement
	 * 
	 * @return SQLStatment interface implemented by a non-static inner
	 * class of this instance's actual class.
	 * 
	 */
	public SQLStatement getSQLStatement(String SQLInnerClassName) throws Exception
	{
		return(SQLStatement) getSQLClass(SQLInnerClassName);
	}

	/**
	 * Use reflection to determine if this instance's actual
	 * class is the generic SQL class or a database specific one such
	 * as for Oracle.  Return an instance of the non-static inner class
	 * corresponding to the actual outer class.
	 * 
	 * @param SQLInnerClassName The name of a non-static nested class which
	 *                          implements a SQLPreparedStatement
	 * 
	 * @return SQLPreparedStatment interface implemented by a non-static inner
	 * class of this instance's actual class.
	 * 
	 */
	public SQLPreparedStatement getPreparedSQLStatement(String SQLInnerClassName) throws Exception
	{
		return(SQLPreparedStatement) getSQLClass(SQLInnerClassName);
	}
	public SQLPreparedStatement getCallableSQLStatement(String SQLInnerClassName) throws Exception
	{
		return(SQLPreparedStatement) getSQLClass(SQLInnerClassName);
	}


	/**
	 * Method to find the correct inner class subclass with this instance of the
	 * outer class.
	 * 
	 * Performance implications were considered but this approach is much easier than
	 * having to define subclassed methods to create the SQLStatement inner class
	 * objects of the correct type.
	 * 
	 */
	private Object getSQLClass(String SQLInnerClassName) throws Exception
	{
		Class outerClass = getClass();
		String outerClassName = outerClass.getName();
		String innerClassName = outerClassName + "$" + SQLInnerClassName;
		Class innerClass = Class.forName(innerClassName);
		Constructor c = innerClass.getConstructor(new Class[]{outerClass});
		return c.newInstance(new Object[]{this});
	}

	/**
	 * This method appends the Oracle specific clause "rownum < ##".  This
	 * allows us to perform deletes or updates in batches to avoid exhausting the
	 * rollback segment.
	 * @param origSql The origional delete or update statement.  Note:  It must have an
	 *    existing where clause.
	 * @param commitFreq The number of rows which will be added to the "rownum < ##" clause.
	 * @return The origional delete or update statement including the "rownum" clause.
	 */
	protected final static String appendCommitLimit(String origSql, int commitFreq)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(origSql);
		buffer.append(" and rownum < ");
		buffer.append(commitFreq);
		return buffer.toString();
	}
}


