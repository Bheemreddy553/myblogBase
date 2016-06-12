/*
package com.synycs.truckbay.server.dao.retrievers;

import com.synycs.truckbay.common.CacheProjectLocal;
import com.synycs.truckbay.common.dao.DAO;
import com.synycs.truckbay.common.dao.SQLPreparedStatement;
import com.synycs.truckbay.common.exception.TBException;
import com.synycs.truckbay.common.exception.TBExceptionFactory;
import com.synycs.truckbay.server.ServerContext;
import com.synycs.truckbay.server.TruckBayBOTypes;
import com.synycs.truckbay.server.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by hadoop on 23/05/15.
 *//*

public class UserDetailsRetrievalDAO extends DAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsRetrievalDAO.class);
    private String loginName=null;
    public UserDetailsRetrievalDAO(String loginName){
        this.loginName=loginName;
    }
    public UserDetailsRetrievalDAO(){


    }


    public UserDetails getUser() throws TBException{
        return CacheProjectLocal.getInstance().getUserDetails(loginName);

    }

    public UserDetails getUserMiss() throws TBException{

        logger.info("Entering UserDetailsRetrievalDAO");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SQLPreparedStatement SQLClass = null;

        UserDetails userDetails = null;

        try( Connection connection=ServerContext.getInstance().getConnection())
        {
            SQLClass = getPreparedSQLStatement("UserDetailRetrievalDAOQuery");

            logger.debug("SQL Statment: {}", SQLClass.toString());


            stmt = SQLClass.getStatement(connection);
            stmt.setString(1,loginName);
            //Execute Query
            rs = stmt.executeQuery();


            int numObjsLoaded = 0;

            while (rs.next())
            {
                userDetails = new UserDetails();

                userDetails.setCustomerLoginName(rs.getString(UserDetailRetrievalDAOQuery.CUSTOMERLOGINNAME));
                //userDetails.setCustomerPassword(rs.getString(UserDetailRetrievalDAOQuery.PASSWORD));

                userDetails.setCustomerName(rs.getString(UserDetailRetrievalDAOQuery.CUSTOMERNAME));
                //userDetails.setId(rs.getInt(UserDetailsRetrievalDAOQuery.CUSTOMERID));
                userDetails.setAddressL1(rs.getString(UserDetailRetrievalDAOQuery.ADDRESSL1));
                userDetails.setAddressL2(rs.getString(UserDetailRetrievalDAOQuery.ADDRESSL2));
                userDetails.setPincode(rs.getString(UserDetailRetrievalDAOQuery.PINCODE));
                userDetails.setArea(rs.getString(UserDetailRetrievalDAOQuery.AREA));
                userDetails.setState(rs.getString(UserDetailRetrievalDAOQuery.STATE));
                userDetails.setDistrict(rs.getString(UserDetailRetrievalDAOQuery.DISTRICT));
                userDetails.setMobileNumber(rs.getString(UserDetailRetrievalDAOQuery.MOBILENUMBER));
                userDetails.setAlternateMobileNumber(rs.getString(UserDetailRetrievalDAOQuery.ALTERNATE_MOBILENUMBER));
                userDetails.setTelephoneNumber(rs.getString(UserDetailRetrievalDAOQuery.TELEPHONENUMBER));
                userDetails.setEmail(rs.getString(UserDetailRetrievalDAOQuery.EMAIL));
                userDetails.setIsActive(1); // Setting to Active since we getting only active users
                userDetails.setCreationDate(rs.getTimestamp(UserDetailRetrievalDAOQuery.CREATIONDATE).getTime());
                userDetails.setDescription(rs.getString(UserDetailRetrievalDAOQuery.DESCRIPTION));
                userDetails.setLastModifiedBy(rs.getString(UserDetailRetrievalDAOQuery.SYS_LAST_MODIFIED_BY));
                userDetails.setLastModifiedDate(rs.getDate(UserDetailRetrievalDAOQuery.SYS_LAST_MODIFIED_DATE));
                userDetails.setCity(rs.getString(UserDetailRetrievalDAOQuery.CITY));
                userDetails.setFormId(rs.getString(UserDetailRetrievalDAOQuery.FORMID));
                userDetails.setFraud(rs.getBoolean(UserDetailsRetrievalDAOQuery.FRAUD));
                userDetails.setMobileVerified(rs.getBoolean(UserDetailsRetrievalDAOQuery.MOBILEVERIFIED));
                userDetails.setCustomerTypeID(TruckBayBOTypes.CUSTOMER_TYPE.valueOf(rs.getString(UserDetailRetrievalDAOQuery.CUSTOMERTYPEID)));
                logger.debug("UserDetails :::: customerLoginName : {} , customerName : {} , customerID : {} ", "check", userDetails.getCustomerName(), userDetails.getAddressL1());
                logger.debug("UserDetails :::: MobileNumber : {} , alternateMobileNumber : {} , telephoneNumber : {} ", userDetails.getMobileNumber(), userDetails.getAlternateMobileNumber(), userDetails.getTelephoneNumber());
                logger.debug("UserDetails :::: email : {} , customerTypeID : {} ", userDetails.getEmail(), userDetails.getCustomerTypeID());


            }
            //connection.close();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error(" customerLoginName: {}, Login SQL: {}", "checck", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error(" customerLoginName: {}, Login SQL: {}", "check", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, stmt);
            logger.info("Leaving UserDetailRetrievalDAOQuery");
        }

        return userDetails;

    }

    public UserDetails getUserPhoneMiss() throws TBException{

        logger.info("Entering UserDetailsRetrievalDAO");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SQLPreparedStatement SQLClass = null;

        UserDetails userDetails = null;

        try( Connection connection=ServerContext.getInstance().getConnection())
        {
            SQLClass = getPreparedSQLStatement("UserDetailRetrievalPhoneDAOQuery");

            logger.debug("SQL Statment: {}", SQLClass.toString());


            stmt = SQLClass.getStatement(connection);
            stmt.setString(1,loginName);
            //Execute Query
            rs = stmt.executeQuery();


            int numObjsLoaded = 0;

            while (rs.next())
            {
                userDetails = new UserDetails();

                userDetails.setCustomerLoginName(rs.getString(UserDetailRetrievalDAOQuery.CUSTOMERLOGINNAME));
              //  userDetails.setCustomerPassword(rs.getString(UserDetailRetrievalDAOQuery.PASSWORD));

                userDetails.setCustomerName(rs.getString(UserDetailRetrievalDAOQuery.CUSTOMERNAME));
                //userDetails.setId(rs.getInt(UserDetailsRetrievalDAOQuery.CUSTOMERID));
                userDetails.setAddressL1(rs.getString(UserDetailRetrievalDAOQuery.ADDRESSL1));
                userDetails.setAddressL2(rs.getString(UserDetailRetrievalDAOQuery.ADDRESSL2));
                userDetails.setPincode(rs.getString(UserDetailRetrievalDAOQuery.PINCODE));
                userDetails.setArea(rs.getString(UserDetailRetrievalDAOQuery.AREA));
                userDetails.setState(rs.getString(UserDetailRetrievalDAOQuery.STATE));
                userDetails.setDistrict(rs.getString(UserDetailRetrievalDAOQuery.DISTRICT));
                userDetails.setMobileNumber(rs.getString(UserDetailRetrievalDAOQuery.MOBILENUMBER));
                userDetails.setAlternateMobileNumber(rs.getString(UserDetailRetrievalDAOQuery.ALTERNATE_MOBILENUMBER));
                userDetails.setTelephoneNumber(rs.getString(UserDetailRetrievalDAOQuery.TELEPHONENUMBER));
                userDetails.setEmail(rs.getString(UserDetailRetrievalDAOQuery.EMAIL));
                userDetails.setIsActive(1); // Setting to Active since we getting only active users
                userDetails.setCreationDate(rs.getTimestamp(UserDetailRetrievalDAOQuery.CREATIONDATE).getTime());
                userDetails.setDescription(rs.getString(UserDetailRetrievalDAOQuery.DESCRIPTION));
                userDetails.setLastModifiedBy(rs.getString(UserDetailRetrievalDAOQuery.SYS_LAST_MODIFIED_BY));
                userDetails.setLastModifiedDate(rs.getDate(UserDetailRetrievalDAOQuery.SYS_LAST_MODIFIED_DATE));
                userDetails.setCity(rs.getString(UserDetailRetrievalDAOQuery.CITY));
                userDetails.setFormId(rs.getString(UserDetailRetrievalDAOQuery.FORMID));
                userDetails.setFraud(rs.getBoolean(UserDetailsRetrievalDAOQuery.FRAUD));
                userDetails.setMobileVerified(rs.getBoolean(UserDetailsRetrievalDAOQuery.MOBILEVERIFIED));
                userDetails.setCustomerTypeID(TruckBayBOTypes.CUSTOMER_TYPE.valueOf(rs.getString(UserDetailRetrievalDAOQuery.CUSTOMERTYPEID)));
                logger.debug("UserDetails :::: customerLoginName : {} , customerName : {} , customerID : {} ", "check", userDetails.getCustomerName(), userDetails.getAddressL1());
                logger.debug("UserDetails :::: MobileNumber : {} , alternateMobileNumber : {} , telephoneNumber : {} ", userDetails.getMobileNumber(), userDetails.getAlternateMobileNumber(), userDetails.getTelephoneNumber());
                logger.debug("UserDetails :::: email : {} , customerTypeID : {} ", userDetails.getEmail(), userDetails.getCustomerTypeID());


            }
            connection.close();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error(" customerLoginName: {}, Login SQL: {}", "checck", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error(" customerLoginName: {}, Login SQL: {}", "check", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, stmt);
            logger.info("Leaving UserDetailRetrievalDAOQuery");
        }

        return userDetails;

    }

    public UserDetails getUserFormIdMiss() throws TBException{

        logger.info("Entering UserDetailsRetrievalDAO");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SQLPreparedStatement SQLClass = null;

        UserDetails userDetails = null;

        try( Connection connection=ServerContext.getInstance().getConnection())
        {
            SQLClass = getPreparedSQLStatement("UserDetailRetrievalFormDAOQuery");

            logger.debug("SQL Statment: {}", SQLClass.toString());


            stmt = SQLClass.getStatement(connection);
            stmt.setString(1,loginName);
            //Execute Query
            rs = stmt.executeQuery();


            int numObjsLoaded = 0;

            while (rs.next())
            {
                userDetails = new UserDetails();

                userDetails.setCustomerLoginName(rs.getString(UserDetailRetrievalDAOQuery.CUSTOMERLOGINNAME));
                //  userDetails.setCustomerPassword(rs.getString(UserDetailRetrievalDAOQuery.PASSWORD));

                userDetails.setCustomerName(rs.getString(UserDetailRetrievalDAOQuery.CUSTOMERNAME));
                //userDetails.setId(rs.getInt(UserDetailsRetrievalDAOQuery.CUSTOMERID));
                userDetails.setAddressL1(rs.getString(UserDetailRetrievalDAOQuery.ADDRESSL1));
                userDetails.setAddressL2(rs.getString(UserDetailRetrievalDAOQuery.ADDRESSL2));
                userDetails.setPincode(rs.getString(UserDetailRetrievalDAOQuery.PINCODE));
                userDetails.setArea(rs.getString(UserDetailRetrievalDAOQuery.AREA));
                userDetails.setState(rs.getString(UserDetailRetrievalDAOQuery.STATE));
                userDetails.setDistrict(rs.getString(UserDetailRetrievalDAOQuery.DISTRICT));
                userDetails.setMobileNumber(rs.getString(UserDetailRetrievalDAOQuery.MOBILENUMBER));
                userDetails.setAlternateMobileNumber(rs.getString(UserDetailRetrievalDAOQuery.ALTERNATE_MOBILENUMBER));
                userDetails.setTelephoneNumber(rs.getString(UserDetailRetrievalDAOQuery.TELEPHONENUMBER));
                userDetails.setEmail(rs.getString(UserDetailRetrievalDAOQuery.EMAIL));
                userDetails.setIsActive(1); // Setting to Active since we getting only active users
                userDetails.setCreationDate(rs.getTimestamp(UserDetailRetrievalDAOQuery.CREATIONDATE).getTime());
                userDetails.setDescription(rs.getString(UserDetailRetrievalDAOQuery.DESCRIPTION));
                userDetails.setLastModifiedBy(rs.getString(UserDetailRetrievalDAOQuery.SYS_LAST_MODIFIED_BY));
                userDetails.setLastModifiedDate(rs.getDate(UserDetailRetrievalDAOQuery.SYS_LAST_MODIFIED_DATE));
                userDetails.setCity(rs.getString(UserDetailRetrievalDAOQuery.CITY));
                userDetails.setFormId(rs.getString(UserDetailRetrievalDAOQuery.FORMID));
                userDetails.setFraud(rs.getBoolean(UserDetailsRetrievalDAOQuery.FRAUD));
                userDetails.setMobileVerified(rs.getBoolean(UserDetailsRetrievalDAOQuery.MOBILEVERIFIED));
                userDetails.setCustomerTypeID(TruckBayBOTypes.CUSTOMER_TYPE.valueOf(rs.getString(UserDetailRetrievalDAOQuery.CUSTOMERTYPEID)));
                logger.debug("UserDetails :::: customerLoginName : {} , customerName : {} , customerID : {} ", "check", userDetails.getCustomerName(), userDetails.getAddressL1());
                logger.debug("UserDetails :::: MobileNumber : {} , alternateMobileNumber : {} , telephoneNumber : {} ", userDetails.getMobileNumber(), userDetails.getAlternateMobileNumber(), userDetails.getTelephoneNumber());
                logger.debug("UserDetails :::: email : {} , customerTypeID : {} ", userDetails.getEmail(), userDetails.getCustomerTypeID());


            }
            connection.close();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error(" customerLoginName: {}, Login SQL: {}", "checck", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error(" customerLoginName: {}, Login SQL: {}", "check", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, stmt);
            logger.info("Leaving UserDetailRetrievalDAOQuery");
        }

        return userDetails;

    }


    public List<UserDetails> getUsers() throws TBException
    {
        List<UserDetails> userDetailses=new ArrayList<>();
        logger.info("Entering UserDetailsRetrievalDAO");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SQLPreparedStatement SQLClass = null;

        UserDetails userDetails = null;

        try( Connection connection=ServerContext.getInstance().getConnection())
        {
            SQLClass = getPreparedSQLStatement("UserDetailsRetrievalDAOQuery");

            logger.debug("SQL Statment: {}", SQLClass.toString());



            stmt = SQLClass.getStatement(connection);
            //Execute Query
            rs = stmt.executeQuery();


            int numObjsLoaded = 0;

            while (rs.next())
            {
                userDetails = new UserDetails();

                userDetails.setCustomerLoginName(rs.getString(UserDetailsRetrievalDAOQuery.CUSTOMERLOGINNAME));
               // userDetails.setCustomerPassword(rs.getString(UserDetailsRetrievalDAOQuery.PASSWORD));

                userDetails.setCustomerName(rs.getString(UserDetailsRetrievalDAOQuery.CUSTOMERNAME));
                userDetails.setAddressL1(rs.getString(UserDetailsRetrievalDAOQuery.ADDRESSL1));
                userDetails.setAddressL2(rs.getString(UserDetailsRetrievalDAOQuery.ADDRESSL2));
                userDetails.setPincode(rs.getString(UserDetailsRetrievalDAOQuery.PINCODE));
                userDetails.setArea(rs.getString(UserDetailsRetrievalDAOQuery.AREA));
                userDetails.setState(rs.getString(UserDetailsRetrievalDAOQuery.STATE));
                userDetails.setDistrict(rs.getString(UserDetailsRetrievalDAOQuery.DISTRICT));
                userDetails.setMobileNumber(rs.getString(UserDetailsRetrievalDAOQuery.MOBILENUMBER));
                userDetails.setAlternateMobileNumber(rs.getString(UserDetailsRetrievalDAOQuery.ALTERNATE_MOBILENUMBER));
                userDetails.setTelephoneNumber(rs.getString(UserDetailsRetrievalDAOQuery.TELEPHONENUMBER));
                userDetails.setEmail(rs.getString(UserDetailsRetrievalDAOQuery.EMAIL));
                userDetails.setIsActive(1); // Setting to Active since we getting only active users
                userDetails.setCreationDate(rs.getTimestamp(UserDetailsRetrievalDAOQuery.CREATIONDATE).getTime());
                userDetails.setDescription(rs.getString(UserDetailsRetrievalDAOQuery.DESCRIPTION));
                userDetails.setLastModifiedBy(rs.getString(UserDetailsRetrievalDAOQuery.SYS_LAST_MODIFIED_BY));
                userDetails.setLastModifiedDate(rs.getDate(UserDetailsRetrievalDAOQuery.SYS_LAST_MODIFIED_DATE));
                userDetails.setCity(rs.getString(UserDetailRetrievalDAOQuery.CITY));
                userDetails.setFormId(rs.getString(UserDetailRetrievalDAOQuery.FORMID));
                userDetails.setFraud(rs.getBoolean(UserDetailsRetrievalDAOQuery.FRAUD));
                userDetails.setMobileVerified(rs.getBoolean(UserDetailsRetrievalDAOQuery.MOBILEVERIFIED));
                userDetails.setCustomerTypeID(TruckBayBOTypes.CUSTOMER_TYPE.valueOf(rs.getString(UserDetailRetrievalDAOQuery.CUSTOMERTYPEID)));
                logger.debug("UserDetails :::: customerLoginName : {} , customerName : {} , customerID : {} ", "check", userDetails.getCustomerName(), userDetails.getAddressL1());
                logger.debug("UserDetails :::: MobileNumber : {} , alternateMobileNumber : {} , telephoneNumber : {} ", userDetails.getMobileNumber(), userDetails.getAlternateMobileNumber(), userDetails.getTelephoneNumber());
                logger.debug("UserDetails :::: email : {} , customerTypeID : {} ", userDetails.getEmail(), userDetails.getCustomerTypeID());

                userDetailses.add(userDetails);
            }
            connection.close();



        }
        catch(SQLException e)
        {
            logger.error(" customerLoginName: {}, Login SQL: {}", "checck", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            logger.error(" customerLoginName: {}, Login SQL: {}", "check", SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, stmt);
            logger.info("Leaving UserDetailsRetrievalDAO.load()");
        }

        return userDetailses;
    }


    public class UserDetailsRetrievalDAOQuery extends SQLPreparedStatement
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
        public static final int CITY  =   20;
        public static final int FORMID  =   21;
        public static final int MOBILEVERIFIED=22;
        public static final int FRAUD=23;

        public UserDetailsRetrievalDAOQuery()
        {
            StringBuffer sql = new StringBuffer();
            sql.append(" select * ");

            sql.append(" FROM users");



            statementStr= sql.toString();
        }
    }

    public class UserDetailRetrievalDAOQuery extends SQLPreparedStatement
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
        public static final int CITY  =   20;
        public static final int FORMID  =   21;
        public static final int MOBILEVERIFIED=22;
        public static final int FRAUD=23;

        public UserDetailRetrievalDAOQuery()
        {
            StringBuffer sql = new StringBuffer();
            sql.append(" select * ");

            sql.append(" FROM users where ? in (loginname,mobilenumber)");



            statementStr= sql.toString();
        }
    }

    public class UserDetailRetrievalPhoneDAOQuery extends SQLPreparedStatement
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
        public static final int CITY  =   20;
        public static final int FORMID  =   21;
        public static final int MOBILEVERIFIED=22;
        public static final int FRAUD=23;

        public UserDetailRetrievalPhoneDAOQuery()
        {
            StringBuffer sql = new StringBuffer();
            sql.append(" select * ");

            sql.append(" FROM users where mobilenumber =?");



            statementStr= sql.toString();
        }
    }

    public class UserDetailRetrievalFormDAOQuery extends SQLPreparedStatement
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
        public static final int CITY  =   20;
        public static final int FORMID  =   21;
        public static final int MOBILEVERIFIED=22;
        public static final int FRAUD=23;

        public UserDetailRetrievalFormDAOQuery()
        {
            StringBuffer sql = new StringBuffer();
            sql.append(" select * ");

            sql.append(" FROM users where formid =?");



            statementStr= sql.toString();
        }
    }
}
*/
