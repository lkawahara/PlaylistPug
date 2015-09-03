package database;

import java.util.ArrayList;
import java.util.List;

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
			String fileName = "LooneyToonsEnd.wav";
			String path = System.getProperty("user.dir") + "\\src\\main\\java\\tempFiles\\" + fileName;
			dummySong = new Song("LooneyToons", null, path);
			dummySongCollection = new ArrayList<Song>();
			dummySongCollection.add(dummySong);
		}
	}

	
	@Override
	public Long add(Song song) 
	{
		return 1l;
	}

	@Override
	public Song getById(Long id) 
	{
		return dummySong;
	}

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

}
