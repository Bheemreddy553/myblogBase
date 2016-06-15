package com.synycs.truckbay.server.services;

import com.synycs.truckbay.common.exception.TBException;
import com.synycs.truckbay.server.BlogImage;
import com.synycs.truckbay.server.BlogPost;
import com.synycs.truckbay.server.BlogPostDetails;
import com.synycs.truckbay.server.dao.blogimages.BlogImageDAO;
import com.synycs.truckbay.server.dao.blogposts.BlogPostDAO;
import com.synycs.truckbay.server.dao.blogposts.BlogPostDetailsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AdminService {
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
	
	private static AdminService adminService = new AdminService();
	private AdminService(){}
	
	public static AdminService getInstance(){
		return adminService;
	}

	
	/**
	 * to add blog post
	 * @param blogPost
	 * @throws TBException
	 */
	public void addBlogPost(BlogPost blogPost)throws TBException{
		new BlogPostDAO(blogPost).register();
	}
	
	/**
	 * to add blog image
	 * @param blogImage
	 * @throws TBException
	 */
	public void addBlogImage(BlogImage blogImage)throws TBException{
		new BlogImageDAO(blogImage).register();
	}
	
	
	/**
	 * to get all blog posts
	 * @return
	 * @throws TBException
	 */
	public List<BlogPost> getAllBlogPosts()throws TBException{		
		return new BlogPostDAO().getAllBlogPosts();		
	}
	
	/**
	 * to get all blog images
	 * @return
	 * @throws TBException
	 */
	public List<BlogImage> getAllBlogImages()throws TBException{		
		return new BlogImageDAO().getAllBlogImages();		
	}
	
	/**
	 * to get  post by id
	 * @param id
	 * @return
	 * @throws TBException
	 */
	public BlogPost getBlogPostById(String id) throws TBException{
		return new BlogPostDAO(id).getBlogPostById();
	}
	
	/**
	 * to get blog image by id
	 * @return
	 * @throws TBException
	 */
	public BlogImage getBlogImageById(String id)throws TBException{
		return new BlogImageDAO(id).getBlogImageById();
	}

	/**
	 * to add Blog post details
	 * @param blogPostDetails
	 * @throws TBException
	 */
	public void addBlogPostDetails(BlogPostDetails blogPostDetails)throws TBException{
		new BlogPostDetailsDAO(blogPostDetails).register();
	}


	/**
	 * to get Blog Post Details By Id
	 * @param id
	 * @return
	 * @throws TBException
	 */
	public BlogPostDetails getBlogPostDetailsById(String id)throws TBException{
	    return  	new BlogPostDetailsDAO(id).getBlogPostDetailsById();
	}

	/**
	 * to get all blog post details
	 * @return
	 * @throws TBException
	 */
	public List<BlogPostDetails> getAllBlogPostDetails()throws TBException{
		return  new BlogPostDetailsDAO().getAllBlogPostDetails();
	}

}
