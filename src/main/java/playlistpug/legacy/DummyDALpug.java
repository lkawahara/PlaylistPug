package playlistpug.legacy;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD:src/main/java/playlistpug/legacy/DummyDALpug.java
//
//public class DummyDALpug implements IDALpug
//{	
//	private static Song dummySong;
//	private static List<Song> dummySongCollection;
//	
//	public DummyDALpug()
//	{
//		if(dummySong == null)
//		{
//			String fileName = "LooneyToonsEnd.mp3";
//			String path = "//src//main//java//tempFiles//" + fileName;
//			dummySong = new Song("LooneyToons", null, path);
//			dummySongCollection = new ArrayList<Song>();
//			dummySongCollection.add(dummySong);
//		}
//	}
//
//	
//	@Override
//	public Long add(Song song) 
//	{
//		return 1l;
//	}
//
//	@Override
//	public Song getById(Long id) 
//	{
//		return dummySong;
//	}
//
//	@Override
//	public Song getByTitle(String title)
//	{
//		return dummySong;
//	}
//
//	@Override
//	public List<Song> getByTag(GenreTag tag)
//	{
//		return dummySongCollection;
//	}
//
//	@Override
//	public List<Song> getByTags(List<GenreTag> tag) 
//	{
//		return dummySongCollection;
//	}
//
//	@Override
//	public List<Song> getByLyrics(String Lyrics) 
//	{
//		return dummySongCollection;
//	}
//
//}
=======
import interfaces.IDALpug;
import models.GenreTag;
import models.Song;

public class DummyDALpug implements IDALpug
{	
	private static Song dummySong;
	private static List<Song> dummySongCollection;
	
	public DummyDALpug()
	{
		if(dummySong == null)
		{
			String fileName = "LooneyToonsEnd.mp3";
			String path = "//src//main//java//tempFiles//" + fileName;
			dummySong = new Song("LooneyToons", null, path);
			dummySongCollection = new ArrayList<Song>();
			dummySongCollection.add(dummySong);
		}
	}

	
	@Override
	public void add(Song song) {}

	/**
	@Override
	public Song getById(Long id) 
	{
		return dummySong;
	}
	/**/

	@Override
	public Song getByTitle(String title)
	{
		return dummySong;
	}

	@Override
	public List<Song> getByTag(GenreTag tag)
	{
		return dummySongCollection;
	}

	@Override
	public List<Song> getByTags(List<GenreTag> tag) 
	{
		return dummySongCollection;
	}

	@Override
	public List<Song> getByLyrics(String Lyrics) 
	{
		return dummySongCollection;
	}


	@Override
	public void remove(Song song) {
		// TODO Auto-generated method stub
		
	}

}
>>>>>>> ed4b4f84caf5893707e1f1b30ad1adf99ccbbf0b:src/main/java/database/DummyDALpug.java
