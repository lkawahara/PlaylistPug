package databaseTesting;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.neumont.spring.config.MainConfig;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

public class DatabaseTesting {

	BasicDataSource basicDataSource = null;
	
	private void setup()
	{
		ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		basicDataSource = (BasicDataSource) context.getBean("dataSource");
	}
	
	@Test
	public void connectToDatabase() 
	{
		setup();
		try 
		{
			Connection connection = basicDataSource.getConnection();
			if(connection == null)
			{
				fail("No connection was established");
			}
		}
		catch (SQLException e) 
		{
			fail("Error creating connection: " + e.getMessage());
			//e.printStackTrace();
		}
	}
	
	@Test
	public void CreateATable() 
	{
		setup();
		try 
		{
			Connection connection = basicDataSource.getConnection();
			if(connection == null)
			{
				fail("No connection was established");
			}
			else
			{
				StringBuilder command = new StringBuilder();		
				command.append("CREATE TABLE IF NOT EXISTS test(id INT PRIMARY KEY, name VARCHAR(255))");
				connection.prepareStatement(command.toString()).execute();
				connection.close();
			}
		}
		catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void CreateandDeleteATable() 
	{
		setup();
		try 
		{
			Connection connection = basicDataSource.getConnection();
			if(connection == null)
			{
				fail("No connection was established");
			}
			else
			{
				StringBuilder command = new StringBuilder();
				command.append("DROP TABLE IF EXISTS test");
				connection.prepareStatement(command.toString()).execute();
				connection.close();
			}
		}
		catch (SQLException e) 
		{
			fail(e.getMessage());
		}
	}

}
