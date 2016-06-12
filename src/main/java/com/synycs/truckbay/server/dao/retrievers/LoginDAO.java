/**
 * 
 *//*

package com.synycs.truckbay.server.dao.retrievers;

import com.synycs.truckbay.common.BCrypt;
import com.synycs.truckbay.common.dao.DAO;
import com.synycs.truckbay.common.dao.SQLPreparedStatement;
import com.synycs.truckbay.common.exception.TBException;
import com.synycs.truckbay.common.exception.TBExceptionFactory;
import com.synycs.truckbay.server.Login;
import com.synycs.truckbay.server.ServerContext;
import com.synycs.truckbay.server.TruckBayBOTypes;
import com.synycs.truckbay.server.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


*/
/**
 * @author Srahanj and Bhargav
 * Login for all type of users
 *
 *//*

public class LoginDAO extends DAO
{
	private static final Logger logger = LoggerFactory.getLogger(LoginDAO.class);
	
	private String customerLoginName 			= null;
	private String customerPassword 			= null;
	
	public LoginDAO(Login login)
	{
		this.customerLoginName 	= login.getUserName();
		this.customerPassword  	= login.getPassWord();
	}

	public UserDetails getLoginDetails() throws TBException

	{

		String regex = "[0-9]+";
		if(customerLoginName.matches(regex)){
			return validateUserPhoneAndGetDetails();
		}
		else {
			return validateUserAndGetDetails();
		}
		

	}
	
	private UserDetails validateUserAndGetDetails() throws TBException
	{
		logger.info("Entering LoginRetrievalDAO");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SQLPreparedStatement SQLClass = null;

        UserDetails userDetails = null;
        
        try (Connection connection=ServerContext.getInstance().getConnection())
        {
            SQLClass = getPreparedSQLStatement("LoginRetrievalDAOQuery");

            logger.debug("SQL Statment: {}", SQLClass.toString());

            stmt = SQLClass.getStatement(connection);
            //Execute Query
			stmt.setString(1,customerLoginName);
            rs = stmt.executeQuery();

            userDetails = new UserDetails();
            int numObjsLoaded = 0;

            while (rs.next()) 
            {
            	if (numObjsLoaded > 1)
                {
            		break;
                }

            	userDetails.setCustomerLoginName(customerLoginName);
            	//userDetails.setCustomerPassword(customerPassword);
				if(!BCrypt.checkpw(customerPassword,rs.getString(LoginRetrievalDAOQuery.PASSWORD))){
					logger.error("password wrong");
					throw new TBException("1","1");
				}

            	userDetails.setCustomerName(rs.getString(LoginRetrievalDAOQuery.CUSTOMERNAME));

				userDetails.setAddressL1(rs.getString(LoginRetrievalDAOQuery.ADDRESSL1));
				userDetails.setAddressL2(rs.getString(LoginRetrievalDAOQuery.ADDRESSL2));
				userDetails.setPincode(rs.getString(LoginRetrievalDAOQuery.PINCODE));
				userDetails.setArea(rs.getString(LoginRetrievalDAOQuery.AREA));
				userDetails.setState(rs.getString(LoginRetrievalDAOQuery.STATE));
				userDetails.setDistrict(rs.getString(LoginRetrievalDAOQuery.DISTRICT));

            	userDetails.setMobileNumber(rs.getString(LoginRetrievalDAOQuery.MOBILENUMBER));
            	userDetails.setAlternateMobileNumber(rs.getString(LoginRetrievalDAOQuery.ALTERNATE_MOBILENUMBER));
            	userDetails.setTelephoneNumber(rs.getString(LoginRetrievalDAOQuery.TELEPHONENUMBER));
            	userDetails.setEmail(rs.getString(LoginRetrievalDAOQuery.EMAIL));
            	userDetails.setIsActive(1); // Setting to Active since we getting only active users
				userDetails.setCreationDate(rs.getTimestamp(LoginRetrievalDAOQuery.CREATIONDATE).getTime());
				userDetails.setDescription(rs.getString(LoginRetrievalDAOQuery.DESCRIPTION));
				userDetails.setLastModifiedBy(rs.getString(LoginRetrievalDAOQuery.SYS_LAST_MODIFIED_BY));
				userDetails.setLastModifiedDate(rs.getDate(LoginRetrievalDAOQuery.SYS_LAST_MODIFIED_DATE));
				userDetails.setFormId(rs.getString(LoginRetrievalDAOQuery.FORMID));
				userDetails.setFraud(rs.getBoolean(LoginRetrievalDAOQuery.FRAUD));
				userDetails.setMobileVerified(rs.getBoolean(LoginRetrievalDAOQuery.MOBILEVERIFIED));

            	userDetails.setCustomerTypeID(TruckBayBOTypes.CUSTOMER_TYPE.valueOf(rs.getString(LoginRetrievalDAOQuery.CUSTOMERTYPEID)));
				logger.debug("UserDetails :::: customerLoginName : {} , customerName : {} , customerID : {} ", customerLoginName, userDetails.getCustomerName(), userDetails.getAddressL1());
                logger.debug("UserDetails :::: MobileNumber : {} , alternateMobileNumber : {} , telephoneNumber : {} ", userDetails.getMobileNumber(), userDetails.getAlternateMobileNumber(), userDetails.getTelephoneNumber());
                logger.debug("UserDetails :::: email : {} , customerTypeID : {} ", userDetails.getEmail(), userDetails.getCustomerTypeID());

                numObjsLoaded++;
            }

            
            if ( 1 != numObjsLoaded )
            {
            	StringBuffer errorStr = new StringBuffer();
            	errorStr.append("More than one user with the login name '");
            	errorStr.append(customerLoginName);
            	errorStr.append("' exists OR No user exists");
            	
            	logger.error(errorStr.toString());
                ServerContext.getInstance().close(rs, stmt);
                               	
            	throw new TBException("", errorStr.toString());
            }
        	
        }
        catch(SQLException e)
        {
        	logger.error(" customerLoginName: {}, Login SQL: {}", customerLoginName, SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
        	logger.error(" customerLoginName: {}, Login SQL: {}", customerLoginName, SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, stmt);
            logger.info("Leaving LoginRetrievalDAOQuery");
        }
        
        return userDetails;
	}

	private UserDetails validateUserPhoneAndGetDetails() throws TBException
	{
		logger.info("Entering LoginRetrievalDAO");

		PreparedStatement stmt = null;
		ResultSet rs = null;
		SQLPreparedStatement SQLClass = null;

		UserDetails userDetails = null;

		try (Connection connection=ServerContext.getInstance().getConnection())
		{
			SQLClass = getPreparedSQLStatement("LoginRetrievalPhoneDAOQuery");

			logger.debug("SQL Statment: {}", SQLClass.toString());

			stmt = SQLClass.getStatement(connection);
			//Execute Query
			stmt.setString(1,customerLoginName);
			rs = stmt.executeQuery();

			userDetails = new UserDetails();
			int numObjsLoaded = 0;

			while (rs.next())
			{
				if (numObjsLoaded > 1)
				{
					break;
				}

				userDetails.setCustomerLoginName(rs.getString(LoginRetrievalPhoneDAOQuery.CUSTOMERLOGINNAME));
				//userDetails.setCustomerPassword(customerPassword);

				userDetails.setCustomerName(rs.getString(LoginRetrievalDAOQuery.CUSTOMERNAME));
				if(!BCrypt.checkpw(customerPassword,rs.getString(LoginRetrievalDAOQuery.PASSWORD))){
					logger.error("password wrong");
					throw new TBException("1","1");
				}

				userDetails.setAddressL1(rs.getString(LoginRetrievalDAOQuery.ADDRESSL1));
				userDetails.setAddressL2(rs.getString(LoginRetrievalDAOQuery.ADDRESSL2));
				userDetails.setPincode(rs.getString(LoginRetrievalDAOQuery.PINCODE));
				userDetails.setArea(rs.getString(LoginRetrievalDAOQuery.AREA));
				userDetails.setState(rs.getString(LoginRetrievalDAOQuery.STATE));
				userDetails.setDistrict(rs.getString(LoginRetrievalDAOQuery.DISTRICT));

				userDetails.setMobileNumber(rs.getString(LoginRetrievalDAOQuery.MOBILENUMBER));
				userDetails.setAlternateMobileNumber(rs.getString(LoginRetrievalDAOQuery.ALTERNATE_MOBILENUMBER));
				userDetails.setTelephoneNumber(rs.getString(LoginRetrievalDAOQuery.TELEPHONENUMBER));
				userDetails.setEmail(rs.getString(LoginRetrievalDAOQuery.EMAIL));
				userDetails.setIsActive(1); // Setting to Active since we getting only active users
				userDetails.setCreationDate(rs.getTimestamp(LoginRetrievalDAOQuery.CREATIONDATE).getTime());
				userDetails.setDescription(rs.getString(LoginRetrievalDAOQuery.DESCRIPTION));
				userDetails.setLastModifiedBy(rs.getString(LoginRetrievalDAOQuery.SYS_LAST_MODIFIED_BY));
				userDetails.setLastModifiedDate(rs.getDate(LoginRetrievalDAOQuery.SYS_LAST_MODIFIED_DATE));
				userDetails.setFormId(rs.getString(LoginRetrievalDAOQuery.FORMID));
				userDetails.setFraud(rs.getBoolean(LoginRetrievalDAOQuery.FRAUD));
				userDetails.setMobileVerified(rs.getBoolean(LoginRetrievalDAOQuery.MOBILEVERIFIED));
				userDetails.setCustomerTypeID(TruckBayBOTypes.CUSTOMER_TYPE.valueOf(rs.getString(LoginRetrievalDAOQuery.CUSTOMERTYPEID)));
				logger.debug("UserDetails :::: customerLoginName : {} , customerName : {} , customerID : {} ", customerLoginName, userDetails.getCustomerName(), userDetails.getAddressL1());
				logger.debug("UserDetails :::: MobileNumber : {} , alternateMobileNumber : {} , telephoneNumber : {} ", userDetails.getMobileNumber(), userDetails.getAlternateMobileNumber(), userDetails.getTelephoneNumber());
				logger.debug("UserDetails :::: email : {} , customerTypeID : {} ", userDetails.getEmail(), userDetails.getCustomerTypeID());

				numObjsLoaded++;
			}


			if ( 1 != numObjsLoaded )
			{
				StringBuffer errorStr = new StringBuffer();
				errorStr.append("More than one user with the login name '");
				errorStr.append(customerLoginName);
				errorStr.append("' exists OR No user exists");

				logger.error(errorStr.toString());
				ServerContext.getInstance().close(rs, stmt);

				throw new TBException("", errorStr.toString());
			}

		}
		catch(SQLException e)
		{
			logger.error(" customerLoginName: {}, Login SQL: {}", customerLoginName, SQLClass.toString());
			throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
		}
		catch (Exception e)
		{
			logger.error(" customerLoginName: {}, Login SQL: {}", customerLoginName, SQLClass.toString());
			throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
		}
		finally {
			//Close resources and return connection.
			ServerContext.getInstance().close(rs, stmt);
			logger.info("Leaving LoginRetrievalDAOQuery");
		}

		return userDetails;
	}
	*/
/**
	 * inner class for login retrieval
	 *//*


	public class LoginRetrievalDAOQuery extends SQLPreparedStatement 
	{
		*/
/** Bind Constants *//*


		*/
/** Column Positional Constants *//*

		public static final int CUSTOMERLOGINNAME     	= 1;
		public static final int CUSTOMERNAME          	= 2;
		public static final int PASSWORD              	= 3;
		public static final int ADDRESSL1            	=     4;
		public static final int ADDRESSL2            	=     5;
		public static final int PINCODE           	=     6;
		public static final int STATE           	=     7;
		public static final int DISTRICT           	=     8;
		public static final int AREA           	=     9;
		public static final int DESCRIPTION            	= 10;
		public static final int MOBILENUMBER          	= 11;
		public static final int ALTERNATE_MOBILENUMBER 	= 12;
		public static final int TELEPHONENUMBER		  	= 13;

		public static final int ISACTIVE				= 14;
		public static final int EMAIL				  	= 15;
		public static final int CREATIONDATE            =16;
		public static final int  SYS_LAST_MODIFIED_BY   = 17;
		public static final int CUSTOMERTYPEID			= 18;
		public static final int SYS_LAST_MODIFIED_DATE  =   19;
		public static final int CITY=20;
		public static final int FORMID=21;
		public static final int MOBILEVERIFIED=22;
		public static final int FRAUD=23;
		
		public LoginRetrievalDAOQuery()
		{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * ");

			sql.append(" FROM users WHERE LOGINNAME = ?");
			//sql.append(customerLoginName);
			//sql.append(customerPassword);
			//sql.append("'");

			
			statementStr= sql.toString();
		}
	}


	public class LoginRetrievalPhoneDAOQuery extends SQLPreparedStatement
	{
		*/
/** Bind Constants *//*


		*/
/** Column Positional Constants *//*

		public static final int CUSTOMERLOGINNAME     	= 1;
		public static final int CUSTOMERNAME          	= 2;
		public static final int PASSWORD              	= 3;
		public static final int ADDRESSL1            	=     4;
		public static final int ADDRESSL2            	=     5;
		public static final int PINCODE           	=     6;
		public static final int STATE           	=     7;
		public static final int DISTRICT           	=     8;
		public static final int AREA           	=     9;
		public static final int DESCRIPTION            	= 10;
		public static final int MOBILENUMBER          	= 11;
		public static final int ALTERNATE_MOBILENUMBER 	= 12;
		public static final int TELEPHONENUMBER		  	= 13;

		public static final int ISACTIVE				= 14;
		public static final int EMAIL				  	= 15;
		public static final int CREATIONDATE            =16;
		public static final int  SYS_LAST_MODIFIED_BY   = 17;
		public static final int CUSTOMERTYPEID			= 18;
		public static final int SYS_LAST_MODIFIED_DATE  =   19;
		public static final int CITY=20;
		public static final int FORMID=21;
		public static final int MOBILEVERIFIED=22;
		public static final int FRAUD=23;

		public LoginRetrievalPhoneDAOQuery()
		{
			StringBuffer sql = new StringBuffer();
			sql.append(" select * ");

			sql.append(" FROM users WHERE MOBILENUMBER = ?");
			//sql.append(customerLoginName);
			//sql.append(customerPassword);
			//sql.append("'");


			statementStr= sql.toString();
		}
	}
}
*/
