package com.synycs.truckbay.server.dao.blogposts;

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
import com.synycs.truckbay.server.BlogPost;
import com.synycs.truckbay.server.ServerContext;

public class BlogPostDAO  extends DAO{
	
	 	private BlogPost blogPost=null;
	    private String id=null;
	    private static final Logger logger = LoggerFactory.getLogger(BlogPostDAO.class);

	    public BlogPostDAO(){}
	    public BlogPostDAO(BlogPost blogPost){
	        this.blogPost=blogPost;

	    }
	    public BlogPostDAO(String id){
	        this.id=id;

	    }
	     
	    public void register() throws TBException
	    {
	        PreparedStatement insertStmt = null;
	        ResultSet rs = null;

	        SQLPreparedStatement sqlClass = null;

	        if(null == blogPost)
	        {
	            logger.error(" Empty Registration Object passed");
	            throw TBExceptionFactory.GetInstance().create("","Empty Registration Object passed", "Please register first!!!");
	        }



	        try(Connection connection= ServerContext.getInstance().getConnection())
	        {
	            // TO DO : Make changes according to the changed DAOs queries below
	        	logger.info("getting sql statement");
	            sqlClass = getPreparedSQLStatement("BlogPostDAOQuery");
	            logger.debug("SQL Statment: {}", sqlClass.toString());


	            insertStmt = sqlClass.getStatement(connection);
	            insertStmt.setString(BlogPostDAOQuery.TITLE, blogPost.getTitle());
	            insertStmt.setString(BlogPostDAOQuery.ID, blogPost.getId());
	            insertStmt.setString(BlogPostDAOQuery.BODY, blogPost.getBody());
	            insertStmt.setString(BlogPostDAOQuery.LOCATION, blogPost.getLocation());
	            insertStmt.setString(BlogPostDAOQuery.COUNTRY, blogPost.getCountry());
	            insertStmt.setTimestamp(BlogPostDAOQuery.TIME,new Timestamp(blogPost.getTime().getTime()));
	            insertStmt.setString(BlogPostDAOQuery.USERID,null);
	            
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
	    
	    public BlogPost getBlogPostById() throws TBException
	    {
	        logger.info("Entering BlogPostRetrievalDAO");

	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        SQLPreparedStatement SQLClass = null;

	        BlogPost blogPost1= null;

	        try(Connection connection=ServerContext.getInstance().getConnection())
	        {
	            SQLClass = getPreparedSQLStatement("BlogPostByIdDAOQuery");

	            logger.debug("SQL Statment: {}", SQLClass.toString());

	            stmt = SQLClass.getStatement(connection); 
	            stmt.setString(1,id);
	            rs = stmt.executeQuery();

	            if(id == null){
	            	throw new TBException("","empty object passed");
	            }
	            
	            
	            blogPost1 = new BlogPost();
	            int numObjsLoaded = 0;

	            while (rs.next())
	            {
	                if (numObjsLoaded > 1)
	                {
	                    break;
	                }
 
	                 blogPost1.setId(rs.getString(BlogPostByIdDAOQuery.ID));
	                 blogPost1.setTitle(rs.getString(BlogPostByIdDAOQuery.TITLE));
	                 blogPost1.setBody(rs.getString(BlogPostByIdDAOQuery.BODY));
	                 blogPost1.setLocation(rs.getString(BlogPostByIdDAOQuery.LOCATION));
	                 blogPost1.setCountry(rs.getString(BlogPostByIdDAOQuery.COUNTRY));
	                 blogPost1.setTime(new Date(rs.getTimestamp((BlogPostByIdDAOQuery.TIME)).getTime()));
	                 
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

	        return blogPost1;
	    }

	    
	    public List<BlogPost> getAllBlogPosts() throws TBException
	    {
	        logger.info("Entering BlogPostRetrievalDAO");

	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        SQLPreparedStatement SQLClass = null;

	         List<BlogPost> blogPosts = new ArrayList<>();

	        try(Connection connection=ServerContext.getInstance().getConnection())
	        {
	            SQLClass = getPreparedSQLStatement("TotalBlogPostDAOQuery");

	            logger.debug("SQL Statment: {}", SQLClass.toString());

	            stmt = SQLClass.getStatement(connection); 
	            rs = stmt.executeQuery();
	            BlogPost blogPost1 = null;
	            
	            while (rs.next())
	            { 
	            	 blogPost1 = new BlogPost();
	                 blogPost1.setId(rs.getString(TotalBlogPostDAOQuery.ID));
	                 blogPost1.setTitle(rs.getString(TotalBlogPostDAOQuery.TITLE));
	                 blogPost1.setBody(rs.getString(TotalBlogPostDAOQuery.BODY));
	                 blogPost1.setLocation(rs.getString(TotalBlogPostDAOQuery.LOCATION));
	                 blogPost1.setCountry(rs.getString(TotalBlogPostDAOQuery.COUNTRY));
	                 blogPost1.setTime(new Date(rs.getTimestamp((TotalBlogPostDAOQuery.TIME)).getTime()));
	                 blogPosts.add(blogPost1);
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

	        return blogPosts;
	    }
	    
	    public  class BlogPostDAOQuery extends SQLPreparedStatement
	    {
	        
	 /** Bind Constants */


	        
	 /** Column Positional Constants */

	        public static final int ID     			= 	1;
	        public static final int TITLE          	= 	2;
	        public static final int BODY            = 	3;
	        public static final int LOCATION       	=     4;
	        public static final int COUNTRY        	=     5;
	        public static final int TIME           	=     6; 
	        public static final int USERID   	=     7; 
	      


	        public BlogPostDAOQuery()
	        {

	            StringBuffer sql = new StringBuffer();
	            sql.append(" INSERT INTO blogposts ");
	            sql.append("values(?, ?, ?, ?, ?, ?,?)");

	            statementStr= sql.toString();
	        }
	    }
	    
	    public  class BlogPostByIdDAOQuery extends SQLPreparedStatement
	    {
	        
	 /** Bind Constants */


	        
	 /** Column Positional Constants */

	        public static final int ID     			= 	1;
	        public static final int TITLE          	= 	2;
	        public static final int BODY            = 	3;
	        public static final int LOCATION       	=     4;
	        public static final int COUNTRY        	=     5;
	        public static final int TIME           	=     6; 
	        public static final int USERID   	=     7; 
	      


	        public BlogPostByIdDAOQuery()
	        {

	            StringBuffer sql = new StringBuffer();
	            sql.append(" SELECT * FROM blogposts id = ? ");
	        
	            statementStr= sql.toString();
	        }
	    }
	    
	    public  class TotalBlogPostDAOQuery extends SQLPreparedStatement
	    {
	        
	 /** Bind Constants */


	        
	 /** Column Positional Constants */

	        public static final int ID     			= 	1;
	        public static final int TITLE          	= 	2;
	        public static final int BODY            = 	3;
	        public static final int LOCATION       	=     4;
	        public static final int COUNTRY        	=     5;
	        public static final int TIME           	=     6; 
	        public static final int USERID   	=     7; 
	      


	        public TotalBlogPostDAOQuery()
	        {

	            StringBuffer sql = new StringBuffer();
	            sql.append(" SELECT * FROM blogposts ");
	        
	            statementStr= sql.toString();
	        }
	    }
}
