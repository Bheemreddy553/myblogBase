/**
 * 
 */
package com.synycs.truckbay.server;

import java.sql.Date;

/**
 * @author Srahanj and Bhargav
 * Dont use this
 *
 */
public class SearchDetails 
{
	private String srcLocation			= null;
	private String destLocation			= null;
	private Date dateOfLoad			= null;
	private String transporterName		= null;
	/**
	 * @param srcLocation
	 * @param destLocation
	 * @param dateOfLoad
	 * @param transporterName
	 */
	public SearchDetails(String srcLocation, String destLocation,
			Date dateOfLoad, String transporterName) {
		super();
		this.srcLocation = srcLocation;
		this.destLocation = destLocation;
		this.dateOfLoad = new Date(dateOfLoad.getTime());
		this.transporterName = transporterName;
	}
	/**
	 * @return the srcLocation
	 */
	public String getSrcLocation() {
		return srcLocation;
	}
	/**
	 * @return the destLocation
	 */
	public String getDestLocation() {
		return destLocation;
	}
	/**
	 * @return the dateOfLoad
	 */
	public Date getDateOfLoad() {
		return dateOfLoad;
	}
	/**
	 * @return the transporterName
	 */
	public String getTransporterName() {
		return transporterName;
	}
}
