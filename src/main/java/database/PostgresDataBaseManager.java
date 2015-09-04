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
import interfaces.IDataBaseManager;

public class PostgresDataBaseManager 
{
	 private static final Logger logger = 
			    LoggerFactory.getLogger(PostgresDataBaseManager.class);
	
	BasicDataSource basicDataSource;
	
	static PostgresDataBaseManager instance = new PostgresDataBaseManager();

	public static PostgresDataBaseManager getInstance()
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
		
		int executeUpdate(String query)
		{
			int result = 0;
			try
			{
				///PreparedStatement prepareStatement = connection.prepareStatement(query);
				//result = (prepareStatement.executeUpdate() < 0);
				//prepareStatement.close();
				
				result = (connection.prepareStatement(query).executeUpdate());
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
	
	private PostgresDataBaseManager()
	{
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		this.basicDataSource = (BasicDataSource) context.getBean("dataSource");
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
		return successful;
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
			stringBuilder.append("DROP TABLE IF EXISTS ");
			stringBuilder.append(tableName);
			successful = connactionManagment.execute(stringBuilder.toString());
		}
		
		closeConnactionManagment(connactionManagment);
		return successful;
	}
	
	/**
	 * Pull an item from the database
	 * @param tableName
	 * @param whatToPull
	 * @param where
	 * @param OrderBy
	 * @return
	 */
	public ResultSet Pulliteam(String tableName, String whatToPull, String where, String regex, String OrderBy)
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
			
			stringBuilder.append(tableName + " ");
			
			if(!"".equals(where))
			{
				stringBuilder.append("WHERE ");
				stringBuilder.append(where);
			}
			
			if(!"".equals(regex))
			{
				stringBuilder.append(regex);
			}
			
			if(!"".equals(OrderBy))
			{
				stringBuilder.append(OrderBy);
			}
			
			stringBuilder.append(";");
			result = connactionManagment.executeQuery(stringBuilder.toString());
		}
		
		//testing(result);
		//closeConnactionManagment(connactionManagment);
		//testing(result);
		return result;
	}
	
	private void testing(ResultSet result)
	{
		try 
		{
			while(result.next())
			{
				 System.out.println("GOT ONE!!");
			}
		} 
		catch (SQLException e)
		{
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * Add a row with the given values
	 * @param tableName
	 * @param Values
	 * @return
	 */
	public int InsertIteam(String tableName, List<String> Values)
	{
		int result = 0;
		ConnactionManagment connactionManagment = getConnactionManagment();
		
		if(connactionManagment != null)
		{
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("INSERT INTO ");
			stringBuilder.append(tableName + " ");
			
			
			stringBuilder.append("VALUES(");
			
			int stop = Values.size();
			
			for(int step = 0; step < stop; step++)
			{
				stringBuilder.append("\'");
				stringBuilder.append(Values.get(step));
				stringBuilder.append("\'");
				if(step + 1 < stop)
				{
					stringBuilder.append(", ");
				}
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
	private ConnactionManagment getConnactionManagment()
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
	private void closeConnactionManagment(ConnactionManagment connactionManagment)
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
