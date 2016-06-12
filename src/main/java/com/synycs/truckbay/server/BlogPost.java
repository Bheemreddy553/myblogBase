package com.synycs.truckbay.server;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BlogPost {
	
	private String id;
	private String title;
	private String body;
	private String location;
	private String country;
	private Date	time;
	private UserDetails	userDetails;
	private List<BlogImage> blogImages;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public List<BlogImage> getBlogImages() {
		return blogImages;
	}

	public void setBlogImages(List<BlogImage> blogImages) {
		this.blogImages = blogImages;
	}
	
	
	
	

}
