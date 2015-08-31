package databaseTesting;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import database.DALpug;
import edu.neumont.spring.config.MainConfig;
import models.GenreTag;
import models.Song;
import setup.MasterSetup;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			MasterSetup.getInstance().run();
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
		
		/**/
		if(id == 0l)
		{
			fail("Song was not added");
		}
		/**/
		
		dalpug.dropTable();
	}
	
	//@Test
	public void GetSongByID()
	{
		setup();
		Long id = dalpug.add(testSong);
		
		testSongMatch(dalpug.getById(id), testSong, "The correct song was not returned: GetSongByID");
		
		dalpug.dropTable();
	}
	
	//@Test
	public void GetSongByLyrics()
	{
		setup();
		Long id = dalpug.add(testSong);
		
		testSongMatchList(dalpug.getByLyrics("NA"), testSong, "The correct song was not returned: GetSongByLyrics");
		
		dalpug.dropTable();
	}
	
	@Test
	public void GetSongByTag()
	{
		setup();
		Long id = dalpug.add(testSong);
		
		testSongMatchList(dalpug.getByTag(GenreTag.Classical), testSong, "The correct song was not returned: GetSongByTag");
		
		dalpug.dropTable();
	}
	
	private void testSongMatch(Song songReturned, Song testSong, String msg)
	{
		if(!testSong(songReturned, testSong))
		{
			fail(msg);
		}
	}
	
	private void testSongMatchList(List<Song> songsReturned, Song testSong, String msg)
	{
		boolean matchFound = false;
		for(Song song : songsReturned)
		{
			matchFound = testSong(song, testSong);
			if(matchFound)
			{
				break;
			}
		}
		
		if(!matchFound)
		{
			fail(msg);
		}
	}
	
	private boolean testSong(Song songReturned, Song testSong)
	{
		return testSong.equals(songReturned);
	}
	
	//Helpers
	Song getTestSong()
	{
		String fileName = "LooneyToonsEnd.wav";
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\tempFiles\\" + fileName;
		String Lyrics = "NA";
		List<GenreTag> tags = new ArrayList<>();
		tags.add(GenreTag.Classical);
		return new Song("LooneyToons", null, path, tags, Lyrics);
	}

}
