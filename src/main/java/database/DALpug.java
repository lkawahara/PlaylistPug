package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.text.html.HTML.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import interfaces.IDALpug;
import models.Song;

public class DALpug implements IDALpug 
{
	DataBaseManager dataBaseManager;
	
	 private static final Logger logger = 
			    LoggerFactory.getLogger(DALpug.class);
	
	AtomicLong baseId = new AtomicLong(0);
	
	String songtableName = "songlist";
	
	public void dropTable()
	{
		dataBaseManager.deletetable(songtableName);
	}
	
	public DALpug()
	{
		dataBaseManager = DataBaseManager.getInstance();
	}
	
	@Override
	public Long add(Song song)
	{
		ArrayList<String> values = serializeSong(song);
		
		int result = dataBaseManager.InsertIteam(songtableName, values);
		
		if(result == 0)
		{
			logger.error("Failed to add Song");
		}
		
		return new Long(result);
	}

	@Override
	public Song getById(Long id)
	{
		ResultSet resultSet = dataBaseManager.Pulliteam(songtableName, "", "Id = " + id, null);
		
		Song song = deserializeSong(resultSet);
		
		return song;
	}

	@Override
	public Song getByTitle(String title)
	{
		ResultSet resultSet = dataBaseManager.Pulliteam(songtableName, "", "Title = " + title, null);
		
		Song song = deserializeSong(resultSet);
		
		return song;
	}

	@Override
	public List<Song> getByTag(Tag tag)
	{
		ArrayList<Tag> tags = new ArrayList<>();
		tags.add(tag);
		ResultSet resultSet = dataBaseManager.Pulliteam(songtableName, "", "Tags = " + tags.toString(), null);
		
		List<Song> songs = deserializeMultipleSongs(resultSet);
		
		return songs;
	}

	@Override
	public List<Song> getByTags(List<Tag> tags)
	{
		ResultSet resultSet = dataBaseManager.Pulliteam(songtableName, "", "Tags = " + tags.toString(), null);
		
		List<Song> songs = deserializeMultipleSongs(resultSet);
		
		return songs;
	}

	@Override
	public List<Song> getByLyrics(String Lyrics) 
	{
		StringBuilder whereCluase = new StringBuilder();
		whereCluase.append("CONTAINS(Lyrics, ");
		whereCluase.append(Lyrics);
		whereCluase.append(")");
		ResultSet resultSet = dataBaseManager.Pulliteam(songtableName, "", whereCluase.toString(), null);
		
		List<Song> songs = deserializeMultipleSongs(resultSet);
		
		return songs;
	}
	
	//helpers
	private ArrayList<String> serializeSong(Song song)
	{
		ArrayList<String> values = new ArrayList<String>();
		
		Long songId = baseId.incrementAndGet();
		
		values.add(songId.toString());
		values.add(song.getTitle());
		values.add(song.getLyrics());
		values.add(song.getTags().toString());
		values.add(convertByteArrayToString(song.getSongData()));
		
		return values;
	}
	
	private List<Song> deserializeMultipleSongs(ResultSet resultSet)
	{
		List<Song> songs = new ArrayList<>();
		
		try 
		{
			while(resultSet.next())
			{
				songs.add(deserializeSong(resultSet));
			}
		} 
		catch (SQLException e)
		{
			//e.printStackTrace();
			logger.error("deseralizeMultipleSongs: " + e.getMessage());
		}
		
		return songs; 
	}
	
	private Song deserializeSong(ResultSet resultSet)
	{
		Song song = null;
		try {
			song = new Song(
					resultSet.getString("Title"),
					null,
					resultSet.getBytes("Song"),
					resultSet.getString("Lyrics")
					);
		} 
		catch (SQLException e) 
		{
			//e.printStackTrace();
			logger.error("deseralizeSong: " + e.getMessage());
		}
		
		return song;
	}
	
	private String convertByteArrayToString(byte[] bytea)
	{
		StringBuilder stringForm = new StringBuilder();
		
		stringForm.append("[");
		
		int length = bytea.length;
		
		for(int step = 0; step < length; step++)
		{
			stringForm.append(bytea[step]);
			
			if((step + 1) < length)
			{
				stringForm.append(", ");
			}
		}
		
		for(byte b : bytea)
		{
			stringForm.append(b);
		}
		
		stringForm.append("]");
		
		return stringForm.toString();
	}

}
