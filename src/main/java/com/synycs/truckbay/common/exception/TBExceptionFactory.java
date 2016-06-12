/**
 * 
 */
package com.synycs.truckbay.common.exception;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * @author Srahanj and Bhargav
 *
 */
public class TBExceptionFactory 
{
	//private static final Logger logger = LoggerFactory.getLogger(TBExceptionFactory.class);

	private static TBExceptionFactory instance = new TBExceptionFactory();
	
    /**
     * hide default ctor
     */
    private TBExceptionFactory() { }
	
    public static TBExceptionFactory GetInstance()
    {
    	return instance;
    }
    
    public TBException create(String errorCode, String errorMsg, String additionalDetails )
    {
    	synchronized(this)
    	{
    		TBException excp = new TBException();
    		excp.setErrorCode(errorCode);
    		excp.setErrorMessage(errorMsg);
    		excp.setAdditionalDetails(additionalDetails);
    		
    		return excp;
    	}
    }
    
    
}
