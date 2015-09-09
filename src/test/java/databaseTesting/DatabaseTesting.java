package databaseTesting;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import models.Song;
import models.AudioData;
import models.GenreTag;
import setup.MasterSetup;
import interfaces.IDALpug;
import edu.neumont.spring.config.MainConfig;
import dependencyInjector.DependencyInjector;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseTesting 
{
	static IDALpug dalpug;
	static Song testSong;
	
	static boolean setupCalled = false;
	static Long   id;
	
	static EntityManager entityManager;
	
	@BeforeClass
	public static void setup()
	{
		//System.out.println("@BeforeClass");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("playlistpug");
		entityManager = emf.createEntityManager();
		
		MasterSetup.getInstance().run();
		
		dalpug = (IDALpug) DependencyInjector.getInstance().get("IDALpug");
		
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		testSong = getTestSong();
		context.close();
		dalpug.add(testSong);
		
		System.out.println("DALpug is: " + dalpug);
	}
	
	@AfterClass
	public static  void tearDown()
	{
		
    }
	
	@Test
	public void GetByTitle()
	{
		testSongMatch(dalpug.getByTitle(testSong.getTitle()), testSong, "The incorrect song was returned: GetByTitle");
	}

	@Test
	public void GetByLyrics()
	{	
		testSongMatchList(dalpug.getByLyrics(testSong.getLyrics()), testSong, "The incorrect song was returned: GetByLyrics");
	}
	
	@Test
	public void GetByTag()
	{
		testSongMatchList(dalpug.getByTag(testSong.getTags().get(0)), testSong, "The correct song was not returned: GetByTag");
	}
	
	@Test
	public void GetByTags()
	{
		testSongMatchList(dalpug.getByTags(testSong.getTags()), testSong, "The correct song was not returned: GetByTags");
	}
	
	//Helpers
	private static void testSongMatch(Song songReturned, Song testSong, String msg)
	{
		if(!testSong(songReturned, testSong))
		{
			fail(msg);
		}
	}
	
	private static void testSongMatchList(List<Song> songsReturned, Song testSong, String msg)
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
	
	private static boolean testSong(Song songReturned, Song testSong)
	{
		return testSong.equals(songReturned);
	}
	
	//Helpers
	static Song getTestSong()
	{
		String title = "LooneyToonsEnd";
		String fileName = "LooneyToonsEnd.wav";
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\tempFiles\\" + fileName;
		String Lyrics = "NA";
		List<GenreTag> tags = new ArrayList<>();
		tags.add(GenreTag.Classical);
		return new Song(title, new AudioData(), path, tags, Lyrics);
	}

}
