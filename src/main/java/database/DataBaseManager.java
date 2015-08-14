package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.neumont.spring.config.MainConfig;

public class DataBaseManager 
{
	BasicDataSource basicDataSource;
	
	private class ConnactionManagment
	{
		Connection connection;
		
		ConnactionManagment(BasicDataSource basicDataSource) throws SQLException
		{
			this.connection = basicDataSource.getConnection();
		}
		
		ResultSet executQuery(String query) throws SQLException
		{
			ResultSet resultSet = connection.prepareStatement(query).executeQuery();
			return resultSet;
		}
		
		void close() throws SQLException
		{
			connection.close();
		}
	}
	
	public DataBaseManager()
	{
		 //This is bad but you must replace it
		 ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		 this.basicDataSource = (BasicDataSource) context.getBean("dataSource");
		 String test = "This was a triumph";
	}
}
