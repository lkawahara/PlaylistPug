package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.neumont.spring.config.MainConfig;

public class DataBaseManager 
{
	 private static final Logger logger = 
			    LoggerFactory.getLogger(DataBaseManager.class);
	
	BasicDataSource basicDataSource;
	
	static DataBaseManager instance = new DataBaseManager();

	public static DataBaseManager getInstance()
	{
		return instance;
	}
	
	/**
	 * An internal class designed to handle one connection to the database
	 * @author Bastien Auxer
	 */
	private class ConnactionManagment
	{
		Connection connection;
		
		ConnactionManagment(BasicDataSource basicDataSource) throws SQLException
		{
			this.connection = basicDataSource.getConnection();
		}
		
		ResultSet executeQuery(String query)
		{
			ResultSet resultSet = null;
			try 
			{
				resultSet = connection.prepareStatement(query).executeQuery();
			} 
			catch (SQLException e)
			{
				logger.error(e.getMessage());
			}
			
			return resultSet;
		}
		
		boolean executeUpdate(String query)
		{
			boolean result = false;
			try
			{
				result = (connection.prepareStatement(query).executeUpdate() < 0);
			} 
			catch (SQLException e) 
			{
				logger.error(e.getMessage());
			}
			
			return result;
		}
		
		boolean execute(String query)
		{
			boolean result = false;
			try
			{
				result = connection.prepareStatement(query).execute();
			} 
			catch (SQLException e) 
			{
				logger.error(e.getMessage());
			}
			
			return result;
		}
		
		void close() throws SQLException
		{
			connection.close();
		}
	}
	
	private DataBaseManager()
	{
		 //This is bad but you must replace it
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		this.basicDataSource = (BasicDataSource) context.getBean("dataSource");
		 //String test = "This was a triumph";
		context.close();
	}
	
	/**
	 * Creates a table in the database
	 * tableStructure includes the name and properties of the table in SQL format
	 * EX: test(id INT PRIMARY KEY, name VARCHAR(255))
	 * @param tableStructure
	 * @return
	 */
	public boolean createTable(String tableStructure)
	{
		ConnactionManagment connactionManagment = getConnactionManagment();
		boolean successful = false;
		
		if(connactionManagment != null)
		{
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
			stringBuilder.append(tableStructure);
			successful = connactionManagment.execute(stringBuilder.toString());
		}
		
		
		closeConnactionManagment(connactionManagment);
		return false;
	}
	
	/**
	 * Drop table
	 * @param tableName
	 * @return
	 */
	public boolean deletetable(String tableName)
	{
		ConnactionManagment connactionManagment = getConnactionManagment();
		boolean successful = false;
		
		if(connactionManagment != null)
		{
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("DROP TABLE IF NOT EXISTS");
			stringBuilder.append(tableName);
			successful = connactionManagment.execute(stringBuilder.toString());
		}
		
		closeConnactionManagment(connactionManagment);
		return false;
	}
	
	/**
	 * Pull an item from the database
	 * @param tableName
	 * @param whatToPull
	 * @param where
	 * @param OrderBy
	 * @return
	 */
	public ResultSet Pulliteam(String tableName, String whatToPull, String where, String OrderBy)
	{
		ConnactionManagment connactionManagment = getConnactionManagment();
		ResultSet result = null;
		
		if(connactionManagment != null)
		{
			//SELECT * FROM table_name;
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT ");
			if("".equals(whatToPull) || whatToPull != null)
			{
				stringBuilder.append("* ");
			}
			else
			{
				stringBuilder.append(whatToPull);
			}
			
			stringBuilder.append("FROM ");
			
			stringBuilder.append(tableName);
			
			if(!"".equals(where))
			{
				stringBuilder.append("WHERE ");
				stringBuilder.append(where);
			}
			
			if(!"".equals(OrderBy) || OrderBy != null)
			{
				stringBuilder.append(OrderBy);
			}
			result = connactionManagment.executeQuery(stringBuilder.toString());
		}
		
		closeConnactionManagment(connactionManagment);
		return result;
	}
	
	/**
	 * Add a row with the given values
	 * @param tableName
	 * @param Values
	 * @return
	 */
	public boolean InsertIteam(String tableName, List<String> Values)
	{
		boolean result = false;
		ConnactionManagment connactionManagment = getConnactionManagment();
		
		if(connactionManagment != null)
		{
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("INSERT INTO ");
			stringBuilder.append("tableName ");
			
			
			stringBuilder.append("VALUES(");
			for(String value : Values)
			{
				stringBuilder.append("\'");
				stringBuilder.append(value);
				stringBuilder.append("\', ");
			}
			stringBuilder.append(")");
			
			result = connactionManagment.executeUpdate(stringBuilder.toString());
		}
		
		closeConnactionManagment(connactionManagment);
		return result;
	}
	
	//helpers
	/**
	 * Sets up a ConnactionManagment
	 * @return
	 */
	public ConnactionManagment getConnactionManagment()
	{
		ConnactionManagment connactionManagment = null;
		try 
		{
			connactionManagment = new ConnactionManagment(basicDataSource);
		} 
		catch (SQLException e) 
		{
			logger.error(e.getMessage());
			//e.printStackTrace();
		}
		
		return connactionManagment;
	}
	
	/**
	 * Closes the ConnactionManagment
	 * @param connactionManagment
	 */
	public void closeConnactionManagment(ConnactionManagment connactionManagment)
	{
		try 
		{
			connactionManagment.close();
		} 
		catch (SQLException e)
		{
			logger.error(e.getMessage());
			//e.printStackTrace();
		}
	}
	
}
