package com.synycs.truckbay.server.dao.blogposts;

import com.synycs.truckbay.common.dao.DAO;
import com.synycs.truckbay.common.dao.SQLPreparedStatement;
import com.synycs.truckbay.common.exception.TBException;
import com.synycs.truckbay.common.exception.TBExceptionFactory;
import com.synycs.truckbay.server.BlogPostDetails;
import com.synycs.truckbay.server.ServerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhargav on 15-06-2016.
 */
public class BlogPostDetailsDAO extends DAO {
    private static final Logger logger = LoggerFactory.getLogger(BlogPostDetails.class);

    private BlogPostDetails blogPostDetails=null;
    private String id=null;

    public BlogPostDetailsDAO(){}
    public BlogPostDetailsDAO(BlogPostDetails blogPostDetails){
        this.blogPostDetails=blogPostDetails;

    }
    public BlogPostDetailsDAO(String id){
        this.id=id;

    }

    public void register() throws TBException
    {
        PreparedStatement insertStmt = null;
        ResultSet rs = null;

        SQLPreparedStatement sqlClass = null;

        if(null == blogPostDetails)
        {
            logger.error(" Empty Registration Object passed");
            throw TBExceptionFactory.GetInstance().create("","Empty Registration Object passed", "Please register first!!!");
        }



        try(Connection connection= ServerContext.getInstance().getConnection())
        {
            // TO DO : Make changes according to the changed DAOs queries below
            logger.info("getting sql statement");
            sqlClass = getPreparedSQLStatement("BlogPostDetailesDAOQuery");
            logger.debug("SQL Statment: {}", sqlClass.toString());


            insertStmt = sqlClass.getStatement(connection);
            insertStmt.setString(BlogPostDetailesDAOQuery.TITLE, blogPostDetails.getTitle());
            insertStmt.setString(BlogPostDetailesDAOQuery.ID, blogPostDetails.getId());
            insertStmt.setString(BlogPostDetailesDAOQuery.SUBJECT, blogPostDetails.getSubject());
            insertStmt.setString(BlogPostDetailesDAOQuery.POSTID, blogPostDetails.getPostId());
            insertStmt.setString(BlogPostDetailesDAOQuery.IMAGEURL, blogPostDetails.getImageUrl());
            insertStmt.setTimestamp(BlogPostDetailesDAOQuery.TIME,new Timestamp(blogPostDetails.getTime().getTime()));
            insertStmt.setString(BlogPostDetailesDAOQuery.USERID,null);

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

    public void register(Connection connection) throws TBException
    {
        PreparedStatement insertStmt = null;
        ResultSet rs = null;

        SQLPreparedStatement sqlClass = null;

        if(null == blogPostDetails)
        {
            logger.error(" Empty Registration Object passed");
            throw TBExceptionFactory.GetInstance().create("","Empty Registration Object passed", "Please register first!!!");
        }



        try
        {
            // TO DO : Make changes according to the changed DAOs queries below
            logger.info("getting sql statement");
            sqlClass = getPreparedSQLStatement("BlogPostDetailesDAOQuery");
            logger.debug("SQL Statment: {}", sqlClass.toString());


            insertStmt = sqlClass.getStatement(connection);
            insertStmt.setString(BlogPostDetailesDAOQuery.TITLE, blogPostDetails.getTitle());
            insertStmt.setString(BlogPostDetailesDAOQuery.ID, blogPostDetails.getId());
            insertStmt.setString(BlogPostDetailesDAOQuery.SUBJECT, blogPostDetails.getSubject());
            insertStmt.setString(BlogPostDetailesDAOQuery.POSTID, blogPostDetails.getPostId());
            insertStmt.setString(BlogPostDetailesDAOQuery.IMAGEURL, blogPostDetails.getImageUrl());
            insertStmt.setTimestamp(BlogPostDetailesDAOQuery.TIME,new Timestamp(blogPostDetails.getTime().getTime()));
            insertStmt.setString(BlogPostDetailesDAOQuery.USERID,null);

            try {
                insertStmt.execute();

            }
            catch (Exception e){
                logger.error(" admin : createUser SQL: {}", sqlClass.toString());
                e.printStackTrace();
                throw e;
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
    }


    public BlogPostDetails getBlogPostDetailsById() throws TBException
    {
        logger.info("Entering BlogPostRetrievalDAO");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SQLPreparedStatement SQLClass = null;

        BlogPostDetails blogPostDetails1= null;

        try(Connection connection=ServerContext.getInstance().getConnection())
        {
            SQLClass = getPreparedSQLStatement("BlogPostDetailestByIdDAOQuery");

            logger.debug("SQL Statment: {}", SQLClass.toString());

            stmt = SQLClass.getStatement(connection);
            stmt.setString(1,id);
            rs = stmt.executeQuery();

            if(id == null){
                throw new TBException("","empty object passed");
            }


            blogPostDetails1 = new BlogPostDetails();
            int numObjsLoaded = 0;

            while (rs.next())
            {
                if (numObjsLoaded > 1)
                {
                    break;
                }

                blogPostDetails1 = new BlogPostDetails();
                blogPostDetails1.setId(rs.getString(BlogPostDetailestByIdDAOQuery.ID));
                blogPostDetails1.setTitle(rs.getString(TotalBlogPostDetilesDAOQuery.TITLE));
                blogPostDetails1.setSubject(rs.getString(TotalBlogPostDetilesDAOQuery.SUBJECT));
                blogPostDetails1.setImageUrl(rs.getString(TotalBlogPostDetilesDAOQuery.IMAGEURL));
                blogPostDetails1.setPostId(rs.getString(TotalBlogPostDetilesDAOQuery.POSTID));
                blogPostDetails1.setTime(new java.util.Date(rs.getTimestamp((TotalBlogPostDetilesDAOQuery.TIME)).getTime()));


            }
            connection.close();

            if ( 1 != numObjsLoaded )
            {
                StringBuffer errorStr = new StringBuffer();
                errorStr.append("More than one user with the login name '");
                errorStr.append(id);
                errorStr.append("' exists OR No user exists");

                logger.error(errorStr.toString());
                ServerContext.getInstance().close(rs, stmt);

                throw new TBException("", errorStr.toString());
            }

        }
        catch(SQLException e)
        {
            logger.error(" customerLoginName: {}, Login SQL: {}", id, SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        catch (Exception e)
        {
            logger.error(" customerLoginName: {}, Login SQL: {}", id, SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, stmt);
            logger.info("Leaving BlogPostRetrievalDAO.load()");
        }

        return blogPostDetails1;
    }


    public List<BlogPostDetails> getAllBlogPostDetails() throws TBException
    {
        logger.info("Entering BlogPostRetrievalDAO");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SQLPreparedStatement SQLClass = null;

        List<BlogPostDetails> blogPostDetailses = new ArrayList<>();

        try(Connection connection=ServerContext.getInstance().getConnection())
        {
            SQLClass = getPreparedSQLStatement("TotalBlogPostDetilesDAOQuery");

            logger.debug("SQL Statment: {}", SQLClass.toString());

            stmt = SQLClass.getStatement(connection);
            rs = stmt.executeQuery();
            BlogPostDetails blogPostDetails1 = null;

            while (rs.next())
            {
                blogPostDetails1 = new BlogPostDetails();
                blogPostDetails1.setId(rs.getString(TotalBlogPostDetilesDAOQuery.ID));
                blogPostDetails1.setTitle(rs.getString(TotalBlogPostDetilesDAOQuery.TITLE));
                blogPostDetails1.setSubject(rs.getString(TotalBlogPostDetilesDAOQuery.SUBJECT));
                blogPostDetails1.setImageUrl(rs.getString(TotalBlogPostDetilesDAOQuery.IMAGEURL));
                blogPostDetails1.setPostId(rs.getString(TotalBlogPostDetilesDAOQuery.POSTID));
                blogPostDetails1.setTime(new java.util.Date(rs.getTimestamp((TotalBlogPostDetilesDAOQuery.TIME)).getTime()));
                blogPostDetailses.add(blogPostDetails1);
            }


        }
        catch(SQLException e)
        {
            e.printStackTrace();
            logger.error(" customerLoginName: {}, Login SQL: {}", e.getCause(),  SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), "");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error(" customerLoginName: {}, Login SQL: {}", e.getCause(),SQLClass.toString());
            throw TBExceptionFactory.GetInstance().create("",e.getMessage(), e.toString());
        }
        finally {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, stmt);
            logger.info("Leaving BlogPostRetrievalDAO.load()");
        }

        return blogPostDetailses;
    }

    public  class BlogPostDetailesDAOQuery extends SQLPreparedStatement
    {

        /** Bind Constants */



        /** Column Positional Constants */

        public static final int ID     			= 	1;
        public static final int TITLE          	= 	2;
        public static final int SUBJECT            = 	3;
        public static final int TIME           	=     4;
        public static final int POSTID       	=     5;
        public static final int USERID   	=     6;
        public static final int IMAGEURL        	=     7;



        public BlogPostDetailesDAOQuery()
        {

            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO postdetailes ");
            sql.append("values(?, ?, ?, ?, ?, ?,?)");

            statementStr= sql.toString();
        }
    }

    public  class BlogPostDetailestByIdDAOQuery extends SQLPreparedStatement
    {

        /** Bind Constants */



        /** Column Positional Constants */

        public static final int ID     			= 	1;
        public static final int TITLE          	= 	2;
        public static final int SUBJECT            = 	3;
        public static final int TIME           	=     4;
        public static final int POSTID       	=     5;
        public static final int USERID   	=     6;
        public static final int IMAGEURL        	=     7;



        public BlogPostDetailestByIdDAOQuery()
        {

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM postdetailes id = ? ");

            statementStr= sql.toString();
        }
    }

    public  class TotalBlogPostDetilesDAOQuery extends SQLPreparedStatement
    {

        /** Bind Constants */



        /** Column Positional Constants */

        public static final int ID     			= 	1;
        public static final int TITLE          	= 	2;
        public static final int SUBJECT            = 	3;
        public static final int TIME           	=     4;
        public static final int POSTID       	=     5;
        public static final int USERID   	=     6;
        public static final int IMAGEURL        	=     7;



        public TotalBlogPostDetilesDAOQuery()
        {

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM postdetailes ");

            statementStr= sql.toString();
        }
    }
}
