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
	private static final Logger logger = 
		    LoggerFactory.getLogger(MainConfig.class);
	
	private static final String DATABASE_URL = "postgres://maphiawdhrurgw:PL4ApC1CK2qpN0XxBCfNJwehBU@ec2-54-163-228-0.compute-1.amazonaws.com:5432/d9tiku6olid4s7";
	
	private static DBConnectionProperties ConnectionInfoProperties;
	
	private class DBConnectionProperties
	{
		String username;
        String password;
        String dbUrl;
        Properties config;
        BasicDataSource basicDataSource;
        
        DBConnectionProperties()
        {
        	
        }
        
        DBConnectionProperties(
        		String username,
        		String password,
        		String dbUrl,
        		Properties config, 
        		BasicDataSource basicDataSource)
        {
        	this.username = username;
        	this.password = password;
        	this.dbUrl = dbUrl;
        	this.basicDataSource = basicDataSource;
        }
	}
	
	private void setDBConnectionProperties() throws URISyntaxException
	{
		URI dbUri = new URI(DATABASE_URL);
        
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://"   +
				        dbUri.getHost() + ':' +
				        dbUri.getPort() +
				        dbUri.getPath() +
				        "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        
        Properties config = new Properties();
        config.setProperty("user", username);
        config.setProperty("password", password);
        config.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        
        ConnectionInfoProperties = new DBConnectionProperties(username, password, dbUrl, config, basicDataSource);
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
	
	private void Setup() throws URISyntaxException
	{
		driverSetup();
        
        if(ConnectionInfoProperties == null)
        {
        	setDBConnectionProperties();
        }
	}
	
    @Bean(name="dataSource")
    public BasicDataSource dataSource() throws URISyntaxException 
    {
    	Setup();
        
        return ConnectionInfoProperties.basicDataSource;
    }
    
    @Bean(name="ConnectionToDataBase")
    public Connection getConnection() throws SQLException, URISyntaxException
    {
    	Setup();
    	
 		Connection connection = DriverManager.getConnection(
 				ConnectionInfoProperties.dbUrl,
 				ConnectionInfoProperties.config
 				);
         
         return connection; 
    }
    
}