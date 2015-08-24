package databaseTesting;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import database.DALpug;
import edu.neumont.spring.config.MainConfig;
import models.Song;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFileFormat.Type;

import org.apache.commons.dbcp.BasicDataSource;

public class DatabaseTesting {

	BasicDataSource basicDataSource = null;
	
	private void setup()
	{
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		basicDataSource = (BasicDataSource) context.getBean("dataSource");
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
	
	@Test
	public void AddingSongToDatabase()
	{
		setup();
		
		DALpug dalpug = new DALpug();
		Song song = getTestSong();
		Long id = dalpug.add(song);
		
		if(id == 0l)
		{
			fail("Song was not added");
		}
	}
	
	//Helpers
	Song getTestSong()
	{
		File soundFile = new File("/playlistpug/src/main/java/tempFiles/LooneyToonsEnd.wav");
		byte[] songBytes = null;
		
		int size = 0;
		try 
		{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			size = audioInputStream.available();
			songBytes = new byte[size];
			audioInputStream.read(songBytes);
		} 
		catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return new Song("LooneyToons", null, songBytes);
	}

}
