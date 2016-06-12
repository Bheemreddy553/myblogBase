/*
package com.synycs.truckbay.server.dao;

import com.synycs.truckbay.common.BCrypt;
import com.synycs.truckbay.common.dao.DAO;
import com.synycs.truckbay.common.dao.SQLPreparedStatement;
import com.synycs.truckbay.common.exception.TBException;
import com.synycs.truckbay.common.exception.TBExceptionFactory;
import com.synycs.truckbay.server.ServerContext;
import com.synycs.truckbay.server.TruckBayBOTypes;
import com.synycs.truckbay.server.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


*//**
 * Created by hadoop on 19/05/15.
 *//*

public class UserDetailsDAO extends DAO {
    private UserDetails userDetails=null;
    private String loginName=null;
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsDAO.class);

    public UserDetailsDAO(UserDetails userDetails){
        this.userDetails=userDetails;

    }
    public UserDetailsDAO(String loginName){
        this.loginName=loginName;

    }
    public void deleteUser() throws TBException{
        PreparedStatement insertStmt = null;

        ResultSet rs = null;

        SQLPreparedStatement sqlClass = null;
        if(loginName==null){
            logger.error(" no loginName  passed");
            throw TBExceptionFactory.GetInstance().create("","Empty Registration Object passed", "Please register first!!!");


        }
        try( Connection connection=ServerContext.getInstance().getConnection())
        {
            // TO DO : Make changes according to the changed DAOs queries below
            sqlClass = getPreparedSQLStatement("UserDetailsDeleteDAOQuery");
            logger.debug("SQL Statment: {}", sqlClass.toString());


            insertStmt = sqlClass.getStatement(connection);
            insertStmt.setString(UserDetailsDeleteDAOQuery.CUSTOMERLOGINNAME, loginName);

            try {

                insertStmt.execute();

                connection.commit();
            }
            catch (Exception e){
                connection.rollback();
                logger.error(" admin : createUser SQL: {}", sqlClass.toString());
                e.printStackTrace();
            }
            connection.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            logger.error(" admin : createUser SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error(" admin : createUser SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally
        {

            //Close resources and return connection.
            ServerContext.getInstance().close(rs, insertStmt);
        }


    }

    private void createUser() throws TBException
    {
        PreparedStatement insertStmt = null;
        ResultSet rs = null;

        SQLPreparedStatement sqlClass = null;

        if(null == userDetails)
        {
            logger.error(" Empty Registration Object passed");
            throw TBExceptionFactory.GetInstance().create("","Empty Registration Object passed", "Please register first!!!");
        }



        try(Connection connection=ServerContext.getInstance().getConnection())
        {
            // TO DO : Make changes according to the changed DAOs queries below
            sqlClass = getPreparedSQLStatement("UserDetailsDAOQuery");
            logger.debug("SQL Statment: {}", sqlClass.toString());


            insertStmt = sqlClass.getStatement(connection);
            insertStmt.setString(UserDetailsDAOQuery.CUSTOMERNAME, userDetails.getCustomerName());
            insertStmt.setString(UserDetailsDAOQuery.CUSTOMERLOGINNAME, userDetails.getCustomerLoginName());
            insertStmt.setString(UserDetailsDAOQuery.PASSWORD, userDetails.getCustomerPassword());
            insertStmt.setString(UserDetailsDAOQuery.ADDRESSL1, userDetails.getAddressL1());
            insertStmt.setString(UserDetailsDAOQuery.ADDRESSL2, userDetails.getAddressL2());
            insertStmt.setString(UserDetailsDAOQuery.AREA, userDetails.getArea());
            insertStmt.setString(UserDetailsDAOQuery.PINCODE, userDetails.getPincode());
            insertStmt.setString(UserDetailsDAOQuery.STATE, userDetails.getState());
            insertStmt.setString(UserDetailsDAOQuery.DISTRICT, userDetails.getDistrict());
            insertStmt.setString(UserDetailsDAOQuery.DESCRIPTION, userDetails.getDescription());
            insertStmt.setString(UserDetailsDAOQuery.MOBILENUMBER, userDetails.getMobileNumber());
            insertStmt.setString(UserDetailsDAOQuery.ALTERNATE_MOBILENUMBER, userDetails.getAlternateMobileNumber());
            insertStmt.setString(UserDetailsDAOQuery.TELEPHONENUMBER, userDetails.getTelephoneNumber());
            insertStmt.setInt(UserDetailsDAOQuery.ISACTIVE, 1);  // Creating Active User
            insertStmt.setString(UserDetailsDAOQuery.EMAIL, userDetails.getEmail());
            insertStmt.setTimestamp(UserDetailsDAOQuery.CREATIONDATE, new java.sql.Timestamp(userDetails.getCreationDate()));
            insertStmt.setDate(UserDetailsDAOQuery.SYS_LAST_MODIFIED_DATE, new java.sql.Date(userDetails.getLastModifiedDate().getTime()));
            insertStmt.setString(UserDetailsDAOQuery.SYS_LAST_MODIFIED_BY, userDetails.getLastModifiedBy());
            insertStmt.setInt(UserDetailsDAOQuery.CUSTOMERTYPEID, userDetails.getCustomerTypeID().getNumber());
            insertStmt.setString(UserDetailsDAOQuery.CITY,userDetails.getCity());
            insertStmt.setString(UserDetailsDAOQuery.FORMID, userDetails.getFormId());
            insertStmt.setBoolean(UserDetailsDAOQuery.FRAUD, userDetails.isFraud());
            insertStmt.setBoolean(UserDetailsDAOQuery.MOBILEVERIFIED, userDetails.isMobileVerified());

            try {
                insertStmt.execute();


                connection.commit();
            }
            catch (Exception e){
                connection.rollback();
                logger.error(" admin : createUser SQL: {}", sqlClass.toString());
                e.printStackTrace();
                throw e;
            }
            connection.close();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            logger.error(" admin : createUser SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            logger.error(" admin : createUser SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally
        {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, insertStmt);
        }
    }
    public void createUser(Connection connection) throws TBException
    {
        PreparedStatement insertStmt = null;
        ResultSet rs = null;

        SQLPreparedStatement sqlClass = null;

        if(null == userDetails)
        {
            logger.error(" Empty Registration Object passed");
            throw TBExceptionFactory.GetInstance().create("","Empty Registration Object passed", "Please register first!!!");
        }



        try
        {
            // TO DO : Make changes according to the changed DAOs queries below
            sqlClass = getPreparedSQLStatement("UserDetailsDAOQuery");
            logger.debug("SQL Statment: {}", sqlClass.toString());


            insertStmt = sqlClass.getStatement(connection);
            insertStmt.setString(UserDetailsDAOQuery.CUSTOMERNAME, userDetails.getCustomerName());
            insertStmt.setString(UserDetailsDAOQuery.CUSTOMERLOGINNAME, userDetails.getCustomerLoginName());
            insertStmt.setString(UserDetailsDAOQuery.PASSWORD, userDetails.getCustomerPassword());
            insertStmt.setString(UserDetailsDAOQuery.ADDRESSL1, userDetails.getAddressL1());
            insertStmt.setString(UserDetailsDAOQuery.ADDRESSL2, userDetails.getAddressL2());
            insertStmt.setString(UserDetailsDAOQuery.AREA, userDetails.getArea());
            insertStmt.setString(UserDetailsDAOQuery.PINCODE, userDetails.getPincode());
            insertStmt.setString(UserDetailsDAOQuery.STATE, userDetails.getState());
            insertStmt.setString(UserDetailsDAOQuery.DISTRICT, userDetails.getDistrict());
            insertStmt.setString(UserDetailsDAOQuery.DESCRIPTION, userDetails.getDescription());
            insertStmt.setString(UserDetailsDAOQuery.MOBILENUMBER, userDetails.getMobileNumber());
            insertStmt.setString(UserDetailsDAOQuery.ALTERNATE_MOBILENUMBER, userDetails.getAlternateMobileNumber());
            insertStmt.setString(UserDetailsDAOQuery.TELEPHONENUMBER, userDetails.getTelephoneNumber());
            insertStmt.setInt(UserDetailsDAOQuery.ISACTIVE, 1);  // Creating Active User
            insertStmt.setString(UserDetailsDAOQuery.EMAIL, userDetails.getEmail());
            insertStmt.setTimestamp(UserDetailsDAOQuery.CREATIONDATE, new java.sql.Timestamp(userDetails.getCreationDate()));
            insertStmt.setDate(UserDetailsDAOQuery.SYS_LAST_MODIFIED_DATE, new java.sql.Date(userDetails.getLastModifiedDate().getTime()));
            insertStmt.setString(UserDetailsDAOQuery.SYS_LAST_MODIFIED_BY, userDetails.getLastModifiedBy());
            insertStmt.setInt(UserDetailsDAOQuery.CUSTOMERTYPEID, userDetails.getCustomerTypeID().getNumber());
            insertStmt.setString(UserDetailsDAOQuery.CITY,userDetails.getCity());
            insertStmt.setString(UserDetailsDAOQuery.FORMID, userDetails.getFormId());
            insertStmt.setBoolean(UserDetailsDAOQuery.FRAUD, userDetails.isFraud());
            insertStmt.setBoolean(UserDetailsDAOQuery.MOBILEVERIFIED, userDetails.isMobileVerified());

            try {
                insertStmt.execute();


            }
            catch (Exception e){
                e.printStackTrace();
                logger.error(" admin : createUser SQL: {}", sqlClass.toString());
                throw new TBException("1","1");


            }


        }
        catch (SQLException e)
        {
            e.printStackTrace();
            logger.error(" admin : createUser SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            logger.error(" admin : createUser SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally
        {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, insertStmt);
        }
    }

    public void register() throws TBException
    {
        createUser();

    }

    
*//**
     * to update password
     * @param loginName
     * @param passwordTOUpdate
     * @throws TBException
     *//*

    public void updatePassword(String loginName, String passwordTOUpdate) throws TBException
    {
        ResultSet rs = null;
        PreparedStatement updateStmt = null;

        SQLPreparedStatement sqlClass = null;

        
*//**
         * hash the password
         *//*

        passwordTOUpdate= BCrypt.hashpw(passwordTOUpdate,BCrypt.gensalt(12));

        try(Connection connection=ServerContext.getInstance().getConnection())
        {
            sqlClass = getPreparedSQLStatement("UpdatePasswordDAOQuery");
            logger.debug("SQL Statment: {}", sqlClass.toString());


            updateStmt = sqlClass.getStatement(connection);

            updateStmt.setString(UpdatePasswordDAOQuery.LOGINNAME, loginName);
            updateStmt.setString(UpdatePasswordDAOQuery.PASSWORD, passwordTOUpdate);

            try {
                int x=updateStmt.executeUpdate();
                if(x==0){
                    connection.close();
                    throw new TBException("","update failed");
                }
                connection.commit();
            }
            catch (Exception e){
                connection.rollback();
                logger.error(" updatePassword SQL: {}", sqlClass.toString());
                e.printStackTrace();
            }
            connection.close();

        }
        catch (SQLException e)
        {
            logger.error(" Registration SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            logger.error(" Registration SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally
        {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, updateStmt);
        }
    }



    public void updateFraud(String loginName,boolean fraud) throws TBException
    {
        ResultSet rs = null;
        PreparedStatement updateStmt = null;

        SQLPreparedStatement sqlClass = null;

        try(Connection connection=ServerContext.getInstance().getConnection())
        {
            sqlClass = getPreparedSQLStatement("UpdateFraudUser");
            logger.debug("SQL Statment: {}", sqlClass.toString());


            updateStmt = sqlClass.getStatement(connection);

            updateStmt.setString(UpdateFraudUser.LOGINNAME, loginName);
            updateStmt.setBoolean(UpdateFraudUser.Fraud,fraud);
            try {
                int x=updateStmt.executeUpdate();
                if(x==0){
                    connection.close();
                    throw new TBException("","update failed");
                }
                connection.commit();
            }
            catch (Exception e){
                connection.rollback();
                logger.error(" updateFraud SQL: {}", sqlClass.toString());
                e.printStackTrace();
            }
            connection.close();

        }
        catch (SQLException e)
        {
            logger.error(" Registration SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            logger.error(" Registration SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally
        {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, updateStmt);
        }
    }
    public void updateMobileVerified(String loginName,boolean mobieverified) throws TBException
    {
        ResultSet rs = null;
        PreparedStatement updateStmt = null;

        SQLPreparedStatement sqlClass = null;

        try(Connection connection=ServerContext.getInstance().getConnection())
        {
            sqlClass = getPreparedSQLStatement("UpdateMobileVerified");
            logger.debug("SQL Statment: {}", sqlClass.toString());


            updateStmt = sqlClass.getStatement(connection);

            updateStmt.setString(UpdateMobileVerified.LOGINNAME, loginName);
            updateStmt.setBoolean(UpdateMobileVerified.MOBILEVERIFIED,mobieverified);
            try {
                int x=updateStmt.executeUpdate();
                if(x==0){
                    connection.close();
                    throw new TBException("","update failed");
                }
                connection.commit();
            }
            catch (Exception e){
                connection.rollback();
                logger.error(" updateMobileVerified SQL: {}", sqlClass.toString());
                e.printStackTrace();
            }
            connection.close();

        }
        catch (SQLException e)
        {
            logger.error(" Registration SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            logger.error(" Registration SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally
        {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, updateStmt);
        }
    }

    public UserDetails validateUserAndGetDetails() throws TBException
    {
        logger.info("Entering UserDetailsRetrievalDAO");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SQLPreparedStatement SQLClass = null;

        UserDetails userDetails = null;

        try(Connection connection=ServerContext.getInstance().getConnection())
        {
            SQLClass = getPreparedSQLStatement("UserDetailsRetrievalDAOQuery");

            logger.debug("SQL Statment: {}", SQLClass.toString());

            stmt = SQLClass.getStatement(connection);
            //Execute Query
            stmt.setString(1,loginName);
            rs = stmt.executeQuery();

            userDetails = new UserDetails();
            int numObjsLoaded = 0;

            while (rs.next())
            {
                if (numObjsLoaded > 1)
                {
                    break;
                }

                userDetails.setCustomerLoginName(loginName);
                //userDetails.setCustomerPassword(rs.getString(UserDetailsRetrievalDAOQuery.PASSWORD));

                userDetails.setCustomerName(rs.getString(UserDetailsRetrievalDAOQuery.CUSTOMERNAME));
                //userDetails.setId(rs.getInt(UserDetailsRetrievalDAOQuery.CUSTOMERID));
                userDetails.setAddressL1(rs.getString(UserDetailsRetrievalDAOQuery.ADDRESSL1));
                userDetails.setAddressL2(rs.getString(UserDetailsRetrievalDAOQuery.ADDRESSL2));
                userDetails.setState(rs.getString(UserDetailsRetrievalDAOQuery.STATE));
                userDetails.setArea(rs.getString(UserDetailsRetrievalDAOQuery.AREA));
                userDetails.setPincode(rs.getString(UserDetailsRetrievalDAOQuery.PINCODE));
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
                userDetails.setFormId(rs.getString(UserDetailsRetrievalDAOQuery.FORMID));
                userDetails.setFraud(rs.getBoolean(UserDetailsRetrievalDAOQuery.FRAUD));
                userDetails.setMobileVerified(rs.getBoolean(UserDetailsRetrievalDAOQuery.MOBILEVERIFIED));
                userDetails.setCustomerTypeID(TruckBayBOTypes.CUSTOMER_TYPE.valueOf(rs.getString(UserDetailsRetrievalDAOQuery.CUSTOMERTYPEID)));
                logger.debug("UserDetails :::: customerLoginName : {} , customerName : {} , customerID : {} ", loginName, userDetails.getCustomerName(), userDetails.getAddressL1());
                logger.debug("UserDetails :::: MobileNumber : {} , alternateMobileNumber : {} , telephoneNumber : {} ", userDetails.getMobileNumber(), userDetails.getAlternateMobileNumber(), userDetails.getTelephoneNumber());
                logger.debug("UserDetails :::: email : {} , customerTypeID : {} ", userDetails.getEmail(), userDetails.getCustomerTypeID());

                numObjsLoaded++;
            }
            connection.close();

            if ( 1 != numObjsLoaded )
            {
                StringBuffer errorStr = new StringBuffer();
                errorStr.append("More than one user with the login name '");
                errorStr.append(loginName);
                errorStr.append("' exists OR No user exists");

                logger.error(errorStr.toString());
                ServerContext.getInstance().close(rs, stmt);

                throw new TBException("", errorStr.toString());
            }

        }
        catch(SQLException e)
        {
            logger.error(" customerLoginName: {}, Login SQL: {}", loginName, SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            logger.error(" customerLoginName: {}, Login SQL: {}", loginName, SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, stmt);
            logger.info("Leaving UserDetailsRetrievalDAO.load()");
        }

        return userDetails;
    }


    public void updateUserDetails() throws TBException
    {
        ResultSet rs = null;
        PreparedStatement updateStmt = null;

        SQLPreparedStatement sqlClass = null;

        try(Connection connection=ServerContext.getInstance().getConnection())
        {
            sqlClass = getPreparedSQLStatement("UserDetailsUpdateDAOQuery");
            logger.debug("SQL Statment: {}", sqlClass.toString());

            if(null == userDetails)
            {
                logger.error(" Empty  Object passed");
                throw TBExceptionFactory.GetInstance().create("","Empty Object passed", "Please register first!!!");
            }

            updateStmt = sqlClass.getStatement(connection);
            updateStmt.setString(UserDetailsUpdateDAOQuery.CUSTOMERNAME,this.userDetails.getCustomerName());
            updateStmt.setString(UserDetailsUpdateDAOQuery.ADDRESSL1, this.userDetails.getAddressL1());
            updateStmt.setString(UserDetailsUpdateDAOQuery.ADDRESSL2,userDetails.getAddressL2());
            updateStmt.setString(UserDetailsUpdateDAOQuery.PINCODE, userDetails.getPincode());
            updateStmt.setString(UserDetailsUpdateDAOQuery.STATE, userDetails.getState());
            updateStmt.setString(UserDetailsUpdateDAOQuery.DISTRICT,userDetails.getDistrict());
            updateStmt.setString(UserDetailsUpdateDAOQuery.AREA, userDetails.getArea());
            updateStmt.setString(UserDetailsUpdateDAOQuery.DESCRIPTION, userDetails.getDescription());
            updateStmt.setString(UserDetailsUpdateDAOQuery.ALTERNATE_MOBILENUMBER, userDetails.getAlternateMobileNumber());
            updateStmt.setString(UserDetailsUpdateDAOQuery.TELEPHONENUMBER,userDetails.getTelephoneNumber());
            updateStmt.setString(UserDetailsUpdateDAOQuery.EMAIL, userDetails.getEmail());
            updateStmt.setString(UserDetailsUpdateDAOQuery.SYS_LAST_MODIFIED_BY, userDetails.getLastModifiedBy());
            updateStmt.setDate(UserDetailsUpdateDAOQuery.SYS_LAST_MODIFIED_DATE,new java.sql.Date(userDetails.getLastModifiedDate().getTime()) );
            updateStmt.setString(UserDetailsUpdateDAOQuery.CITY, userDetails.getCity());
            updateStmt.setString(UserDetailsUpdateDAOQuery.CUSTOMERLOGINNAME,userDetails.getCustomerLoginName());
            try {
                int x=updateStmt.executeUpdate();
                if(x==0){
                    connection.close();
                    throw new TBException("","update failed");
                }
                connection.commit();
            }
            catch (Exception e){
                connection.rollback();
                logger.error(" updateUserDetails SQL: {}", sqlClass.toString());
                e.printStackTrace();
            }
            connection.close();

        }
        catch (SQLException e)
        {
            logger.error(" Registration SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            logger.error(" Registration SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally
        {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, updateStmt);
        }
    }

    public void changePhoneNumber(String phoneNumber) throws TBException{
        PreparedStatement updateStmt = null;
        ResultSet rs = null;

        SQLPreparedStatement sqlClass = null;

        try(Connection connection= ServerContext.getInstance().getConnection())
        {
            // TO DO : Make changes according to the changed DAOs queries below
            sqlClass = getPreparedSQLStatement("UserUpdatePhoneNoDAOQuery");
            logger.debug("SQL Statment: {}", sqlClass.toString());


            updateStmt = sqlClass.getStatement(connection);

            updateStmt.setString(UserUpdatePhoneNoDAOQuery.CUSTOMERLOGINNAME, loginName);

            updateStmt.setString(UserUpdatePhoneNoDAOQuery.MOBILENUMBER, phoneNumber);
            try {
                int x=updateStmt.executeUpdate();
                if(x==0){
                    throw new TBException("","update failed");
                }
                connection.commit();
            }
            catch (Exception e){
                connection.rollback();
                e.printStackTrace();
                throw new TBException("1","error");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            logger.error(" TransportCompany : createCompany SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error(" TransportCompany : createCompany SQL: {}", sqlClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally
        {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, updateStmt);
        }

    }




    public  class UserDetailsDAOQuery extends SQLPreparedStatement
    {
        
*//** Bind Constants *//*


        
*//** Column Positional Constants *//*

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


        public UserDetailsDAOQuery()
        {

            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO user ");
            sql.append("values(?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?)");

            statementStr= sql.toString();
        }
    }
    public  class UpdatePasswordDAOQuery extends SQLPreparedStatement
    {
        
*//** Bind Constants *//*


        
*//** Column Positional Constants *//*

        public static final int LOGINNAME            	= 2;
        public static final int PASSWORD		       	= 1;



        public UpdatePasswordDAOQuery()
        {

            StringBuffer sql = new StringBuffer();
            sql.append("update user ");
            sql.append("SET PASSWORD= ? ");
            sql.append(" where LOGINNAME = ? ");

            statementStr= sql.toString();
        }
    }


    public class UserDetailsRetrievalDAOQuery extends SQLPreparedStatement
    {
        
*//** Bind Constants *//*


        
*//** Column Positional Constants *//*

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
        public UserDetailsRetrievalDAOQuery()
        {
            StringBuffer sql = new StringBuffer();
            sql.append(" select * ");

            sql.append(" FROM users WHERE LOGINNAME = ?");



            statementStr= sql.toString();
        }
    }

    public class UserDetailsUpdateDAOQuery extends SQLPreparedStatement
    {
        
*//** Bind Constants *//*


        
*//** Column Positional Constants *//*


        public static final int CUSTOMERNAME          	= 1;

        public static final int ADDRESSL1            	= 2;
        public static final int ADDRESSL2            	=  3;
        public static final int PINCODE           	=     4;
        public static final int STATE           	=     5;
        public static final int DISTRICT           	=     6;
        public static final int AREA           	=     7;
        public static final int DESCRIPTION            	= 8;

        public static final int ALTERNATE_MOBILENUMBER 	= 9;
        public static final int TELEPHONENUMBER		  	= 10;
        public static final int EMAIL				  	= 11;
        public static final int  SYS_LAST_MODIFIED_BY   = 12;
        public static final int SYS_LAST_MODIFIED_DATE  =   13;
        public static final int CITY=14;
        public static final int CUSTOMERLOGINNAME     	= 15;

        public UserDetailsUpdateDAOQuery()
        {
            StringBuffer sql = new StringBuffer();
            sql.append(" update user set  name=?,addressl1=?,addressl2=?,pincode=?,state=?,district=?,area=?,description=?,");
            sql.append("alternate_mobilenumber=?,telephone_number=?,email=?,sys_last_modified_by=?,sys_last_modified_date=?,");
            sql.append("city=?");

            sql.append(" WHERE LOGINNAME = ?");



            statementStr= sql.toString();
        }
    }


    public class UserDetailsDeleteDAOQuery extends SQLPreparedStatement{
        public static final int CUSTOMERLOGINNAME     	= 1;
        public UserDetailsDeleteDAOQuery(){
            StringBuffer sql = new StringBuffer();
            sql.append("delete from user where loginname=?");
            statementStr= sql.toString();

        }
    }

    public  class UpdateFraudUser extends SQLPreparedStatement
    {
        
*//** Bind Constants *//*


        
*//** Column Positional Constants *//*

        public static final int LOGINNAME            	= 2;
        public static final int Fraud		       	= 1;



        public UpdateFraudUser()
        {

            StringBuffer sql = new StringBuffer();
            sql.append("update user ");
            sql.append("SET fraud= ? ");
            sql.append(" where LOGINNAME = ?");

            statementStr= sql.toString();
        }
    }
    public  class UpdateMobileVerified extends SQLPreparedStatement
    {
        
*//** Bind Constants *//*


        
*//** Column Positional Constants *//*

        public static final int LOGINNAME            	= 2;
        public static final int MOBILEVERIFIED	       	= 1;



        public UpdateMobileVerified()
        {

            StringBuffer sql = new StringBuffer();
            sql.append("update user ");
            sql.append("SET mobileverified= ? ");
            sql.append(" where LOGINNAME = ?");

            statementStr= sql.toString();
        }
    }

    public  class  UserUpdatePhoneNoDAOQuery extends SQLPreparedStatement
    {
        
*//** Bind Constants *//*


        
*//** Column Positional Constants *//*


        public static final int MOBILENUMBER= 1;
        public static final int CUSTOMERLOGINNAME    	= 2;

        public UserUpdatePhoneNoDAOQuery()
        {

            StringBuffer sql = new StringBuffer();
            sql.append("update user ");
            sql.append("SET  mobilenumber = ?   ");
            sql.append("where loginname = ? ");

            statementStr= sql.toString();
        }
    }




}

*/