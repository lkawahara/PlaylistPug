package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
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
	
	String tableName = "SongList";
	StringBuilder tableStructure;
	
	private void setupTable()
	{
		tableStructure = new StringBuilder();
		tableStructure.append(tableName);
		tableStructure.append("(");
		tableStructure.append("Id BIGINT PRIMARY KEY, ");
		tableStructure.append("Title VARCHAR(256), ");
		tableStructure.append("Lyrics VARCHAR(1701), ");
		tableStructure.append("Tags VARCHAR(1701), ");
		tableStructure.append("Song VARBINARY(8000) ");
		tableStructure.append(")");
		
		dataBaseManager.createTable(tableStructure.toString());
	}
	
	public DALpug()
	{
		dataBaseManager = DataBaseManager.getInstance();
		setupTable();
	}
	
	@Override
	public Long add(Song song)
	{
		ArrayList<String> values = seralizeSong(song);
		
		boolean result = dataBaseManager.InsertIteam(tableName, values);
		
		if(!result)
		{
			logger.error("Failed to add Song");
		}
		
		return baseId.get();
	}

	@Override
	public Song getById(Long id)
	{
		ResultSet resultSet = dataBaseManager.Pulliteam(tableName, "", "Id = " + id, null);
		
		Song song = deseralizeSong(resultSet);
		
		return song;
	}

	@Override
	public Song getByTitle(String title)
	{
		ResultSet resultSet = dataBaseManager.Pulliteam(tableName, "", "Title = " + title, null);
		
		Song song = deseralizeSong(resultSet);
		
		return song;
	}

	@Override
	public List<Song> getByTag(Tag tag)
	{
		ArrayList<Tag> tags = new ArrayList<>();
		tags.add(tag);
		ResultSet resultSet = dataBaseManager.Pulliteam(tableName, "", "Tags = " + tags.toString(), null);
		
		List<Song> songs = deseralizeMultipleSongs(resultSet);
		
		return songs;
	}

	@Override
	public List<Song> getByTags(List<Tag> tags)
	{
		ResultSet resultSet = dataBaseManager.Pulliteam(tableName, "", "Tags = " + tags.toString(), null);
		
		List<Song> songs = deseralizeMultipleSongs(resultSet);
		
		return songs;
	}

	@Override
	public List<Song> getByLyrics(String Lyrics) 
	{
		StringBuilder whereCluase = new StringBuilder();
		whereCluase.append("CONTAINS(Lyrics, ");
		whereCluase.append(Lyrics);
		whereCluase.append(")");
		ResultSet resultSet = dataBaseManager.Pulliteam(tableName, "", whereCluase.toString(), null);
		
		List<Song> songs = deseralizeMultipleSongs(resultSet);
		
		return songs;
	}
	
	//helpers
	private ArrayList<String> seralizeSong(Song song)
	{
		ArrayList<String> values = new ArrayList<String>();
		
		Long songId = baseId.incrementAndGet();
		
		values.add(songId.toString());
		values.add(song.getTitle());
		values.add(song.getLyrics());
		values.add(song.getTags().toString());
		
		return values;
	}
	
	private List<Song> deseralizeMultipleSongs(ResultSet resultSet)
	{
		List<Song> songs = new ArrayList<>();
		
		try 
		{
			while(resultSet.next())
			{
				songs.add(deseralizeSong(resultSet));
			}
		} 
		catch (SQLException e)
		{
			//e.printStackTrace();
			logger.error("deseralizeMultipleSongs: " + e.getMessage());
		}
		
		return songs; 
	}
	
	private Song deseralizeSong(ResultSet resultSet)
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

}
