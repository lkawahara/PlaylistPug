package databaseTesting;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import database.DALpug;
import edu.neumont.spring.config.MainConfig;
import models.Song;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DatabaseTesting {

	BasicDataSource basicDataSource;
	DALpug dalpug;
	Song testSong;
	
	boolean setupCalled = false;
	
	private void setup()
	{
		if(!setupCalled)
		{
			ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
			basicDataSource = (BasicDataSource) context.getBean("dataSource");
			dalpug = new DALpug();
			testSong = getTestSong();
			context.close();
		}
	}
	
	//@Test
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
	
	//@Test
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
	
	//@Test
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
	
	//@Test
	public void AddingSongToDatabase()
	{
		setup();
		Long id = dalpug.add(testSong);
		
		if(id == 0l)
		{
			fail("Song was not added");
		}
		
		dalpug.dropTable();
	}
	
	//@Test
	public void GetSongByFromDatabase()
	{
		setup();
		Long id = dalpug.add(testSong);
		
		testSongMatch(dalpug.getById(id), testSong, "The correct song was not returned: getById");
		
		dalpug.dropTable();
	}
	
	private void testSongMatch(Song songReturned, Song testSong, String msg)
	{
		if(songReturned.equals(testSong))
		{
			fail(msg);
		}
	}
	
	//Helpers
	Song getTestSong()
	{
		String fileName = "LooneyToonsEnd.wav";
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\tempFiles\\" + fileName;
		byte[] songBytes = null;
		
		int size = 0;
		try 
		{
			File file = new File(path);
			InputStream inputStream = new FileInputStream(file);
			size = inputStream.available();
			songBytes = new byte[size];
			inputStream.read(songBytes);
			inputStream.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return new Song("LooneyToons", null, fileName);
	}

}
