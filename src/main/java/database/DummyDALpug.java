package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTML.Tag;

import interfaces.IDALpug;
import models.Song;

public class DummyDALpug implements IDALpug
{	
	static Song dummySong;
	
	private void setup()
	{
		if(dummySong != null)
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
			
			dummySong = new Song("LooneyToons", null, fileName);
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
		setup();
		return dummySong;
	}

	@Override
	public Song getByTitle(String title)
	{
		setup();
		return dummySong;
	}

	@Override
	public List<Song> getByTag(Tag tag)
	{
		setup();
		return new ArrayList<Song>()
		{{
			add(dummySong);
		}};
	}

	@Override
	public List<Song> getByTags(List<Tag> tag) 
	{
		setup();
		return new ArrayList<Song>()
		{{
			add(dummySong);
		}};
	}

	@Override
	public List<Song> getByLyrics(String Lyrics) 
	{
		setup();
		return new ArrayList<Song>()
		{{
			add(dummySong);
		}};
	}

}
