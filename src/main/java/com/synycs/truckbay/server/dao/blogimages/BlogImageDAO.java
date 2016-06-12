package com.synycs.truckbay.server.dao.blogimages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synycs.truckbay.common.dao.DAO;
import com.synycs.truckbay.common.dao.SQLPreparedStatement;
import com.synycs.truckbay.common.exception.TBException;
import com.synycs.truckbay.common.exception.TBExceptionFactory;
import com.synycs.truckbay.server.BlogImage;
import com.synycs.truckbay.server.ServerContext;

public class BlogImageDAO extends DAO {
	private BlogImage blogImage=null;
    private String id=null;
    private static final Logger logger = LoggerFactory.getLogger(BlogImageDAO.class);

    public BlogImageDAO(){}
    public BlogImageDAO(BlogImage blogImage){
        this.blogImage=blogImage;

    }
    public BlogImageDAO(String id){
        this.id=id;

    }
     
    public void register() throws TBException
    {
        PreparedStatement insertStmt = null;
        ResultSet rs = null;

        SQLPreparedStatement sqlClass = null;

        if(null == blogImage)
        {
            logger.error(" Empty Registration Object passed");
            throw TBExceptionFactory.GetInstance().create("","Empty Registration Object passed", "Please register first!!!");
        }



        try(Connection connection= ServerContext.getInstance().getConnection())
        {
            // TO DO : Make changes according to the changed DAOs queries below
            sqlClass = getPreparedSQLStatement("BlogImageDAOQuery");
            logger.debug("SQL Statment: {}", sqlClass.toString());


            insertStmt = sqlClass.getStatement(connection);
            insertStmt.setString(BlogImageDAOQuery.TITLE, blogImage.getTitle());
            insertStmt.setString(BlogImageDAOQuery.ID, blogImage.getId());
            insertStmt.setString(BlogImageDAOQuery.URL, blogImage.getUrl());
            insertStmt.setString(BlogImageDAOQuery.LOCATION, blogImage.getLocation());
            insertStmt.setString(BlogImageDAOQuery.COUNTRY, blogImage.getCountry());
            insertStmt.setString(BlogImageDAOQuery.POSTID, blogImage.getBlogPost().getId());
            insertStmt.setTimestamp(BlogImageDAOQuery.DATE,new Timestamp(blogImage.getDate().getTime()));
            insertStmt.setString(BlogImageDAOQuery.USERID,null);
            
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
            throw new TBException(e.toString(),e.getMessage());
        }
        catch (Exception e)
        {
            logger.error(" admin : createUser SQL: {}", sqlClass.toString());
            throw new TBException(e.toString(),e.getMessage());
        }
        finally
        {
            //Close resources and return connection.
            ServerContext.getInstance().close(rs, insertStmt);
        }
    }
    
    public BlogImage getBlogImageById() throws TBException
    {
        logger.info("Entering BlogImageRetrievalDAO");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SQLPreparedStatement SQLClass = null;

        BlogImage blogImage1= null;

        try(Connection connection=ServerContext.getInstance().getConnection())
        {
            SQLClass = getPreparedSQLStatement("BlogImageByIdDAOQuery");

            logger.debug("SQL Statment: {}", SQLClass.toString());

            stmt = SQLClass.getStatement(connection); 
            stmt.setString(1,id);
            rs = stmt.executeQuery();

            if(id == null){
            	throw new TBException("","empty object passed");
            }
            
            
            blogImage1 = new BlogImage();
            int numObjsLoaded = 0;

            while (rs.next())
            {
                if (numObjsLoaded > 1)
                {
                    break;
                }

                blogImage1.setId(rs.getString(TotalBlogImageDAOQuery.ID));
                blogImage1.setTitle(rs.getString(TotalBlogImageDAOQuery.TITLE));
                blogImage1.setUrl(rs.getString(TotalBlogImageDAOQuery.URL));
                blogImage1.setLocation(rs.getString(TotalBlogImageDAOQuery.LOCATION));
                blogImage1.setCountry(rs.getString(TotalBlogImageDAOQuery.COUNTRY));
                blogImage1.setDate(new Date(rs.getTimestamp((TotalBlogImageDAOQuery.DATE)).getTime()));
                blogImage1.setUserDetails(null);;
                 
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
            logger.info("Leaving BlogImageRetrievalDAO.load()");
        }

        return blogImage1;
    }

    
    public List<BlogImage> getAllBlogImages() throws TBException
    {
        logger.info("Entering BlogImageRetrievalDAO");

        PreparedStatement stmt = null;
        ResultSet rs = null;
        SQLPreparedStatement SQLClass = null;

         List<BlogImage> blogImages = new ArrayList<>();

        try(Connection connection=ServerContext.getInstance().getConnection())
        {
            SQLClass = getPreparedSQLStatement("TotalBlogImageDAOQuery");

            logger.debug("SQL Statment: {}", SQLClass.toString());

            stmt = SQLClass.getStatement(connection); 
            rs = stmt.executeQuery();
            BlogImage blogImage1 = null;
            
            while (rs.next())
            { 
            	 blogImage1 = new BlogImage();
                 blogImage1.setId(rs.getString(TotalBlogImageDAOQuery.ID));
                 blogImage1.setTitle(rs.getString(TotalBlogImageDAOQuery.TITLE));
                 blogImage1.setUrl(rs.getString(TotalBlogImageDAOQuery.URL));
                 blogImage1.setLocation(rs.getString(TotalBlogImageDAOQuery.LOCATION));
                 blogImage1.setCountry(rs.getString(TotalBlogImageDAOQuery.COUNTRY));
                 blogImage1.setDate(new Date(rs.getTimestamp((TotalBlogImageDAOQuery.DATE)).getTime()));
                 blogImage1.setUserDetails(null);
                 blogImages.add(blogImage1);
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
            logger.info("Leaving BlogImageRetrievalDAO.load()");
        }

        return blogImages;
    }
    
    public  class BlogImageDAOQuery extends SQLPreparedStatement
    {
        
 /** Bind Constants */


        
 /** Column Positional Constants */

        public static final int ID     			= 	1;
        public static final int TITLE          	= 	2;
        public static final int URL            = 	3;
        public static final int LOCATION       	=     4;
        public static final int COUNTRY        	=     5;
        public static final int POSTID           	=     6; 
        public static final int DATE           	=     7; 
        public static final int USERID   	=     8; 
      


        public BlogImageDAOQuery()
        {

            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO images ");
            sql.append("values(?, ?, ?, ?, ?, ?,?,?)");

            statementStr= sql.toString();
        }
    }
    
    public  class BlogImageByIdDAOQuery extends SQLPreparedStatement
    {
        
 /** Bind Constants */


        
 /** Column Positional Constants */

    	  public static final int ID     			= 	1;
          public static final int TITLE          	= 	2;
          public static final int URL            = 	3;
          public static final int LOCATION       	=     4;
          public static final int COUNTRY        	=     5;
          public static final int POSTID           	=     6; 
          public static final int DATE           	=     7; 
          public static final int USERID   	=     8; 
      


        public BlogImageByIdDAOQuery()
        {

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM images id = ? ");
        
            statementStr= sql.toString();
        }
    }
    
    public  class TotalBlogImageDAOQuery extends SQLPreparedStatement
    {
        
 /** Bind Constants */


        
 /** Column Positional Constants */

    	  public static final int ID     			= 	1;
          public static final int TITLE          	= 	2;
          public static final int URL            = 	3;
          public static final int LOCATION       	=     4;
          public static final int COUNTRY        	=     5;
          public static final int POSTID           	=     6; 
          public static final int DATE           	=     7; 
          public static final int USERID   	=     8; 
      


        public TotalBlogImageDAOQuery()
        {

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT * FROM images ");
        
            statementStr= sql.toString();
        }
    }

}
