package databaseTesting;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import database.DALpug;
//import edu.neumont.spring.config.MainConfig;
//import models.GenreTag;
//import models.Song;
//import setup.MasterSetup;
//
//import static org.junit.Assert.*;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.dbcp.BasicDataSource;
//
//public class DatabaseTesting {
//
//	static BasicDataSource basicDataSource;
//	static DALpug dalpug;
//	static Song testSong;
//	
//	static boolean setupCalled = false;
//	static Long   id;
//	
//	@BeforeClass
//	public static void setup()
//	{
//		if(!setupCalled)
//		{
//			setupCalled = true;
//			MasterSetup.getInstance().run();
//			ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
//			basicDataSource = (BasicDataSource) context.getBean("dataSource");
//			dalpug = new DALpug();
//			testSong = getTestSong();
//			context.close();
//			id = dalpug.add(testSong);
//		}
//	}
//	
//	@AfterClass
//	public static void takeDown()
//	{
//		dalpug.dropTable();
//	}
//	
//	@Test
//	public void connectToDatabase() 
//	{
//		try 
//		{
//			Connection connection = basicDataSource.getConnection();
//			if(connection == null)
//			{
//				fail("No connection was established");
//			}
//		}
//		catch (SQLException e) 
//		{
//			fail("Error creating connection: " + e.getMessage());
//			//e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void CreateATable() 
//	{
//		try 
//		{
//			Connection connection = basicDataSource.getConnection();
//			if(connection == null)
//			{
//				fail("No connection was established");
//			}
//			else
//			{
//				StringBuilder command = new StringBuilder();		
//				command.append("CREATE TABLE IF NOT EXISTS test(id INT PRIMARY KEY, name VARCHAR(255))");
//				connection.prepareStatement(command.toString()).execute();
//				connection.close();
//			}
//		}
//		catch (SQLException e) 
//		{
//			fail(e.getMessage());
//		}
//	}
//	
//	@Test
//	public void CreateandDeleteATable() 
//	{
//		try 
//		{
//			Connection connection = basicDataSource.getConnection();
//			if(connection == null)
//			{
//				fail("No connection was established");
//			}
//			else
//			{
//				StringBuilder command = new StringBuilder();
//				command.append("DROP TABLE IF EXISTS test");
//				connection.prepareStatement(command.toString()).execute();
//				connection.close();
//			}
//		}
//		catch (SQLException e) 
//		{
//			fail(e.getMessage());
//		}
//	}
//	
//	@Test
//	public void AddingSongToDatabase()
//	{
//		Long id = dalpug.add(testSong);
//		
//		/**/
//		if(id == 0l)
//		{
//			fail("Song was not added");
//		}
//		/**/
//	}
//	
//	@Test
//	public void GetSongByID()
//	{
//		testSongMatch(dalpug.getById(id), testSong, "The correct song was not returned: GetSongByID");
//	}
//	
//	@Test
//	public void GetSongByLyrics()
//	{	
//		testSongMatchList(dalpug.getByLyrics("NA"), testSong, "The correct song was not returned: GetSongByLyrics");
//	}
//	
//	@Test
//	public void GetSongByTag()
//	{
//		testSongMatchList(dalpug.getByTag(GenreTag.Classical), testSong, "The correct song was not returned: GetSongByTag");
//	}
//	
//	private static void testSongMatch(Song songReturned, Song testSong, String msg)
//	{
//		if(!testSong(songReturned, testSong))
//		{
//			fail(msg);
//		}
//	}
//	
//	private static void testSongMatchList(List<Song> songsReturned, Song testSong, String msg)
//	{
//		boolean matchFound = false;
//		for(Song song : songsReturned)
//		{
//			matchFound = testSong(song, testSong);
//			if(matchFound)
//			{
//				break;
//			}
//		}
//		
//		if(!matchFound)
//		{
//			fail(msg);
//		}
//	}
//	
//	private static boolean testSong(Song songReturned, Song testSong)
//	{
//		return testSong.equals(songReturned);
//	}
//	
//	//Helpers
//	static Song getTestSong()
//	{
//		String fileName = "LooneyToonsEnd.wav";
//		String path = System.getProperty("user.dir") + "\\src\\main\\java\\tempFiles\\" + fileName;
//		String Lyrics = "NA";
//		List<GenreTag> tags = new ArrayList<>();
//		tags.add(GenreTag.Classical);
//		return new Song("LooneyToons", null, path, tags, Lyrics);
//	}
//
//}
