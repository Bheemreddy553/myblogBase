/**
 *
 */
package com.synycs.truckbay.server;

import com.synycs.truckbay.common.TruckBayConstants;
import com.synycs.truckbay.common.exception.TBException;
import com.synycs.truckbay.common.exception.TBExceptionFactory;
import com.synycs.truckbay.server.datasource.DatasourceManager;
import com.synycs.truckbay.server.datasource.DatasourceManager.DatasourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * @author Srahanj and Bhargav
 * Reads from xml config file to create connections
 *
 */
public class ServerContext
{
	private static final Logger logger = LoggerFactory.getLogger(ServerContext.class);

	private static ServerContext instance;
	private static boolean bDatasourceDetails			= false;

	private final String confFilePathName;
	private String databaseHostName 	= null;
	private String databaseName	 	= null;
	private int				portNumber			= -1;
	private String databaseUserName 	= null;
	private String databasePassword 	= null;
	private DatasourceType 	datasourceType	 	= DatasourceType.ORACLE;

	private DatasourceManager dsManager			= null;
	private Connection connection 			= null;

	private HashMap<String, Integer> customerClassification = new HashMap<String, Integer>();

	private ServerContext(String confFilePathName) {
	this.confFilePathName=confFilePathName;
	}
	private ServerContext(){
		this.confFilePathName= "TruckBay.xml";
	}

	public static ServerContext getInstance()
	{
		if (instance==null){
			synchronized (ServerContext.class) {
				instance = new ServerContext();
			}
		}
		return instance;
	}
	public static void setInstance(String confFilePathName){
		synchronized (ServerContext.class) {
			instance = new ServerContext(confFilePathName);
		}
	}

	public HashMap<String, Integer> getCustomerClassificationDetails()
	{
		return customerClassification;
	}

	/**
	 * gets database connection
	 * @return
	 * @throws SQLException
	 */

	public Connection getConnection() throws SQLException
	{
		System.out.println(confFilePathName);

			synchronized(this)
			{

					if(null == dsManager)
					{
						if (!bDatasourceDetails)
						{
							getDatasourceDetails(confFilePathName);
							bDatasourceDetails = true;
						}
						dsManager = new DatasourceManager(databaseName, databaseHostName, portNumber, databaseUserName, databasePassword, datasourceType);
					}

			}


		return dsManager.getConnection();
	}

	/**
	 * closes database connection
	 * @throws SQLException
	 */

	public void closeConnection() throws SQLException
	{
		if(null != connection)
		{
			synchronized(this)
			{
				if(null != connection)
				{
					if(null != dsManager)
						dsManager.closeConnection(connection);
				}
			}
			connection = null;
		}
	}


	public void close(ResultSet iRS, Statement iStmt) throws TBException
	{
		close(iRS);
		close(iStmt);
	}

	public void close(ResultSet iRS) throws TBException
	{
		if (iRS != null)
		{
			try
			{
				iRS.close();
			}
			catch (SQLException e)
			{
				logger.error("SQLException", e.toString());
				throw TBExceptionFactory.GetInstance().create("", e.getMessage(), e.toString());
			}
		}
	}

	public void close(Statement iStatement) throws TBException
	{
		if (iStatement != null)
		{
			try
			{
				iStatement.close();
			}
			catch (SQLException e)
			{
				logger.error("SQLException", e.toString());
				throw TBExceptionFactory.GetInstance().create("", e.getMessage(), e.toString());
			}
		}
	}

	/**
	 * method for parsing xml file
	 * @param fileName
	 */

	private void getDatasourceDetails(String fileName)
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(fileName));
			Element root = document.getDocumentElement();

			NodeList sboObjects = root.getElementsByTagName(TruckBayConstants.OBJECT);

			for(int i=0; i<sboObjects.getLength(); i++)
			{
				Element truckBayObject = (Element)sboObjects.item(i);

				String truckBayObjectName  	= truckBayObject.getAttribute(TruckBayConstants.NAME);
				String truckBayDBType		=  truckBayObject.getAttribute(TruckBayConstants.TYPE);

				if(true == truckBayDBType.equalsIgnoreCase(TruckBayConstants.MYSQL))
				{
					datasourceType = DatasourceType.MYSQL;
				}
				else if(true == truckBayDBType.equalsIgnoreCase(TruckBayConstants.GOOGLE)){
					datasourceType = DatasourceType.GOOGLE;
				}
				if(true == truckBayDBType.equalsIgnoreCase(TruckBayConstants.CUSTOMER))
				{
					//datasourceType = DatasourceType.MYSQL;
				}


				if(true == truckBayObjectName.equalsIgnoreCase(TruckBayConstants.DATABASE))
				{
					NodeList fields = truckBayObject.getElementsByTagName(TruckBayConstants.FIELD);
					for(int j=0; j<fields.getLength(); j++)
					{
						Element field = (Element)fields.item(j);

						String fieldName = field.getAttribute(TruckBayConstants.NAME);
						if(true == fieldName.equalsIgnoreCase(TruckBayConstants.HOSTNAME))
						{
							databaseHostName = field.getAttribute(TruckBayConstants.VALUE);
						}

						if(true == fieldName.equalsIgnoreCase(TruckBayConstants.DBNAME))
						{
							databaseName = field.getAttribute(TruckBayConstants.VALUE);
						}

						if(true == fieldName.equalsIgnoreCase(TruckBayConstants.PORT))
						{
							portNumber = Integer.parseInt(field.getAttribute(TruckBayConstants.VALUE));
						}

						if(true == fieldName.equalsIgnoreCase(TruckBayConstants.DBUSERNAME))
						{
							databaseUserName = field.getAttribute(TruckBayConstants.VALUE);
						}

						if(true == fieldName.equalsIgnoreCase(TruckBayConstants.DBPASSWORD))
						{
							databasePassword = field.getAttribute(TruckBayConstants.VALUE);
						}

					}
				}
				else if(true == truckBayObjectName.equalsIgnoreCase(TruckBayConstants.CLASSIFICATION))
				{
					NodeList fields = truckBayObject.getElementsByTagName(TruckBayConstants.FIELD);
					for(int j=0; j<fields.getLength(); j++)
					{
						Element field = (Element)fields.item(j);

						String fieldName = field.getAttribute(TruckBayConstants.NAME);
						customerClassification.put(fieldName, Integer.parseInt(field.getAttribute(TruckBayConstants.VALUE)));
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
