package edu.neumont.spring.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig 
{
	/**
	private static final Logger logger = 
		    LoggerFactory.getLogger(MainConfig.class);
	
	private static final String DATABASE_URL = "postgres://maphiawdhrurgw:PL4ApC1CK2qpN0XxBCfNJwehBU@ec2-54-163-228-0.compute-1.amazonaws.com:5432/d9tiku6olid4s7";
	
	private static ConnectionData connectionData;
	
    @Bean(name="dataSource")
    public BasicDataSource dataSource() throws URISyntaxException 
    {
    	Setup();
        return connectionData.basicDataSource;
    }
    
    @Bean(name="ConnectionToDataBase")
    public Connection getConnection() throws SQLException, URISyntaxException
    {
    	Setup();
    	
 		Connection connection = DriverManager.getConnection(
 				connectionData.dbUrl,
 				connectionData.config
 				);
         
         return connection; 
    }

	//Helpers
    private void Setup() throws URISyntaxException
	{   
        if(connectionData == null)
        {
    		driverSetup();
        	setDBConnectionProperties();
        }
	}
    
    private void driverSetup()
	{
		try 
    	{
    		Class.forName("org.postgresql.Driver");
		}
    	catch (ClassNotFoundException e)
    	{
			System.out.println("Failed to load driver: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private class ConnectionData
	{
		String username;
        String password;
        String dbUrl;
        Properties config;
        BasicDataSource basicDataSource;
        
        ConnectionData(){}
	}
	
	private void setDBConnectionProperties() throws URISyntaxException
	{
		URI dbUri = new URI(DATABASE_URL);
		
		connectionData = new ConnectionData();
        
		connectionData.username = dbUri.getUserInfo().split(":")[0];
		connectionData.password = dbUri.getUserInfo().split(":")[1];
		connectionData.dbUrl = "jdbc:postgresql://"   +
				        dbUri.getHost() + ':' +
				        dbUri.getPort() +
				        dbUri.getPath() +
				        "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        
		connectionData.basicDataSource = new BasicDataSource();
		connectionData.basicDataSource.setUrl(connectionData.dbUrl);
		connectionData.basicDataSource.setUsername(connectionData.username);
		connectionData.basicDataSource.setPassword(connectionData.password);
        
		connectionData.config = new Properties();
		connectionData.config.setProperty("user", connectionData.username);
		connectionData.config.setProperty("password", connectionData.password);
		connectionData.config.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        
	}
	
	/**/
    
}