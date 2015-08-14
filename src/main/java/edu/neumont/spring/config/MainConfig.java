package edu.neumont.spring.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig 
{
	private static final String DATABASE_URL = "postgres://maphiawdhrurgw:PL4ApC1CK2qpN0XxBCfNJwehBU@ec2-54-163-228-0.compute-1.amazonaws.com:5432/d9tiku6olid4s7";
	
    @Bean(name="dataSource")
    public BasicDataSource dataSource() throws URISyntaxException
    {
        URI dbUri = new URI(System.getenv(DATABASE_URL));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        
        return basicDataSource;
    }
}