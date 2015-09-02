package edu.neumont.spring.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig 
{
	
	private static final String DATABASE_URL = "postgres://maphiawdhrurgw:PL4ApC1CK2qpN0XxBCfNJwehBU@ec2-54-163-228-0.compute-1.amazonaws.com:5432/d9tiku6olid4s7";
	
    @Bean(name="dataSource")
    public BasicDataSource dataSource() throws URISyntaxException, SQLException 
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
    	
        URI dbUri = new URI(DATABASE_URL);
        
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "";
        
        // Spring/Java && JDBC
        dbUrl = "jdbc:postgresql://" +
		        dbUri.getHost() + ':' +
		        dbUri.getPort() +
		        dbUri.getPath() +
		        "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

        
        @SuppressWarnings("unused")
		Connection connection = DriverManager.getConnection(dbUrl, username, password);
        
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        
        return basicDataSource;
    }
}