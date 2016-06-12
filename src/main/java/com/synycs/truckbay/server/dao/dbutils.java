/**
 * 
 */
package com.synycs.truckbay.server.dao;

import com.synycs.truckbay.common.dao.DAO;
import com.synycs.truckbay.common.dao.SQLPreparedStatement;
import com.synycs.truckbay.common.exception.TBException;
import com.synycs.truckbay.common.exception.TBExceptionFactory;
import com.synycs.truckbay.server.ServerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Srahanj and Bhargav
 *
 */
public class dbutils extends DAO
{
	private static final Logger logger = LoggerFactory.getLogger(dbutils.class);

	private Map<String, Integer> idManager 			= new HashMap<String,Integer>();
	private Map<String, Integer> lorryTypesMap		= new HashMap<String,Integer>();
	private Map<String, Integer> materialTypeMap	= new HashMap<String,Integer>();

	
	private String tableName				= null;
	public static final dbutils instance 	= new dbutils();
	
	private dbutils() {}
	
	public static dbutils getInstance()
	{
		return instance;
	}
	
	public int getMaxID(String tableName)
	{
		return idManager.get(tableName).intValue();
	}
	
	public void setMaxID(String tableName, int maxID)
	{
		idManager.put(this.tableName, maxID);				
	}
	
	public int updateAndGetNextAvailableID(String tableName)
	{
		int maxID = getMaxID(tableName);
		maxID++;
		setMaxID(tableName, maxID);
		return maxID;
	}
	
	public void initialize() throws TBException
	{
		updateIDs("USER");
		updateIDs("AUTHENTICATE_USER");
		updateIDs("FORGOT_PASSWORD");
		updateIDs("ADMINISTRATOR");
		updateIDs("CUSTOMER");
		updateIDs("CUSTOMER_CARE");
		updateIDs("SUGGESTION_BOX");
		updateIDs("ROUTE");
		updateIDs("VEHICLE_TYPE");
		updateIDs("SAVE_SEARCH");
		updateIDs("COMPLAINTS");
		
		updateIDs("COMPLAINT_TYPE");
		updateIDs("MATERIAL_TYPE");
		updateIDs("CARRIER_SUB_TYPE");
		updateIDs("CARRIER_TYPE");
		updateIDs("TRANSPORT_TYPE");
		updateIDs("AUTHENTICATE");
		updateIDs("CUSTOMERTYPE");
		updateIDs("USERTYPE");
		
		// Initializing Lorry SubTypes
		initializeLorrySubTypes();
		initializeMaterialTypes();
	}

	public int getLorrySubTypeID(String lorrySubType)
	{
		return lorryTypesMap.get(lorrySubType).intValue();
	}
	
	public int getMaterialTypeID(String materialType)
	{
		return materialTypeMap.get(materialType).intValue();
	}
	
	public void updateIDs(String tableName) throws TBException
	{
		try
		{
			this.tableName = tableName;
			idManager.put(this.tableName, getMaxID());
		}
		catch(TBException ex)
		{
			idManager.put(this.tableName, 400001); // records starts with 400001
			throw ex;
		}
	}

	private void initializeLorrySubTypes() throws TBException
	{
		logger.info("Entering getLorrySubTypes()");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SQLPreparedStatement SQLClass = null;
        
        try 
        {
            SQLClass = getPreparedSQLStatement("TruckSubTypesRetrievalDAOQuery");

            logger.debug("SQL Statment: {}", SQLClass.toString());

            stmt = SQLClass.getStatement(ServerContext.getInstance().getConnection());
            //Execute Query
            rs = stmt.executeQuery();
            
            while (rs.next()) 
            {            	
            	String name = rs.getString(TruckSubTypesRetrievalDAOQuery.NAME);
            	int id = rs.getInt(TruckSubTypesRetrievalDAOQuery.ID);
            	
            	idManager.put(name, id);
            }           
        }
        catch(SQLException e)
        {
        	logger.error(" TruckSubTypesRetrievalDAOQuery SQL: {}", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
        	logger.error(" TruckSubTypesRetrievalDAOQuery SQL: {}", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, stmt);
            logger.info("Leaving getLorrySubTypes()");
        }
        
	}
	
	private void initializeMaterialTypes() throws TBException
	{
		logger.info("Entering initializeMaterialTypes()");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SQLPreparedStatement SQLClass = null;
        
        try 
        {
            SQLClass = getPreparedSQLStatement("MaterialTypesRetrievalDAOQuery");

            logger.debug("SQL Statment: {}", SQLClass.toString());

            stmt = SQLClass.getStatement(ServerContext.getInstance().getConnection());
            //Execute Query
            rs = stmt.executeQuery();
            
            while (rs.next()) 
            {            	
            	String name = rs.getString(MaterialTypesRetrievalDAOQuery.MATERIAL_TYPE_NAME);
            	int id = rs.getInt(MaterialTypesRetrievalDAOQuery.ID);
            	
            	materialTypeMap.put(name, id);
            }           
        }
        catch(SQLException e)
        {
        	logger.error(" MaterialTypesRetrievalDAOQuery SQL: {}", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
        	logger.error(" MaterialTypesRetrievalDAOQuery SQL: {}", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, stmt);
            logger.info("Leaving initializeMaterialTypes()");
        }
        
	}
	
	private int getMaxID() throws TBException
	{
		logger.info("Entering getMaxID()");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SQLPreparedStatement SQLClass = null;
        int maxID		  = -1;
        
        try 
        {
            SQLClass = getPreparedSQLStatement("IDRetrievalDAOQuery");

            logger.debug("SQL Statment: {}", SQLClass.toString());

            stmt = SQLClass.getStatement(ServerContext.getInstance().getConnection());
            //Execute Query
            rs = stmt.executeQuery();
            
            int numObjsLoaded = 1;

            while (rs.next()) 
            {
            	if (numObjsLoaded > 1)
                {
            		break;
                }
            	
            	maxID = rs.getInt(IDRetrievalDAOQuery.ID);
                numObjsLoaded++;
            }
            
           	if (1 != numObjsLoaded)
            {
            	StringBuffer errorStr = new StringBuffer();
            	errorStr.append("More than one record exists with max ID ");                	
            	logger.error(errorStr.toString());
                ServerContext.getInstance().close(rs, stmt);
                               	
            	throw new TBException("", errorStr.toString());
            }
        }
        catch(SQLException e)
        {
        	logger.error(" ID MAX FETCH SQL: {}", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
        	logger.error(" ID MAX FETCH SQL: {}", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, stmt);
            logger.info("Leaving getMaxID()");
        }
        
        return maxID;
	}

	public class IDRetrievalDAOQuery extends SQLPreparedStatement 
	{
		/** Bind Constants */

		/** Column Positional Constants */
		public static final int CUSTOMERNAME          	= 1;
		public static final int ID				     	= 2;
		
		public IDRetrievalDAOQuery()
		{
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT MAX(ID) FROM  ");
			sql.append(tableName);	
			
			statementStr= sql.toString();
		}
	}

	
	public class TruckSubTypesRetrievalDAOQuery extends SQLPreparedStatement 
	{
		/** Bind Constants */

		/** Column Positional Constants */
		public static final int NAME		= 1;
		public static final int ID			= 2;
		
		public TruckSubTypesRetrievalDAOQuery()
		{
			StringBuffer sql = new StringBuffer();
			sql.append(" select ");
			sql.append(" CARRIER_SUB_TYPE.NAME, ");
			sql.append(" CARRIER_SUB_TYPE.ID ");			
			sql.append(" FROM CARRIER_SUB_TYPE WHERE CARRIER_SUB_TYPE.CARRIER_TYPE_ID = ( select CARRIER_TYPE.ID from CARRIER_TYPE WHERE CARRIER_TYPE.NAME = 'Lorries' ) ");
				
			statementStr= sql.toString();
		}
	}

	
	public class MaterialTypesRetrievalDAOQuery extends SQLPreparedStatement 
	{
		/** Bind Constants */

		/** Column Positional Constants */
		public static final int MATERIAL_TYPE_NAME	= 1;
		public static final int ID					= 2;
		
		public MaterialTypesRetrievalDAOQuery()
		{
			StringBuffer sql = new StringBuffer();
			sql.append(" select ");
			sql.append(" MATERIAL_TYPE.MATERIAL_TYPE_NAME, ");
			sql.append(" MATERIAL_TYPE.ID ");			
			sql.append(" FROM MATERIAL_TYPE ");
				
			statementStr= sql.toString();
		}
	}
	
}
