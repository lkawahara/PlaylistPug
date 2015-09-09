package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import interfaces.IDALpug;
import models.GenreTag;
import models.Song;

public class DALpug implements IDALpug 
{
	PostgresDataBaseManager dataBaseManager;
	
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
		dataBaseManager = PostgresDataBaseManager.getInstance();
	}
	
	@Override
	public void add(Song song)
	{
		ArrayList<String> values = serializeSong(song);
		
		int result = dataBaseManager.InsertIteam(songtableName, values);
		
		if(result == 0)
		{
			logger.error("Failed to add Song");
		}
		
		//return new Long(result);
	}

	/**
	@Override
	public Song getById(Long id)
	{
		ResultSet resultSet = dataBaseManager.Pulliteam(songtableName, "", "ID = " + id, "", "");
		
		try {
			resultSet.next();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		Song song = deserializeSong(resultSet);
		
		closeResults(resultSet);
		
		return song;
	}
	/**/

	@Override
	public Song getByTitle(String title)
	{
		ResultSet resultSet = dataBaseManager.Pulliteam(songtableName, "", "title = " + title, "", "");
		
		Song song = deserializeSong(resultSet);
		
		try {
			resultSet.next();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		closeResults(resultSet);
		
		return song;
	}

	@Override
	public List<Song> getByTag(GenreTag tag)
	{
		ArrayList<GenreTag> tags = new ArrayList<>();
		tags.add(tag);
		
		StringBuilder cluase = new StringBuilder();
		cluase.append("tags = ");
		cluase.append("\'");
		cluase.append(tags.toString());
		cluase.append("\'");
		
		ResultSet resultSet = dataBaseManager.Pulliteam(songtableName, "", cluase.toString(), "", "");
		
		List<Song> songs = deserializeMultipleSongs(resultSet);
		
		closeResults(resultSet);
		
		return songs;
	}

	@Override
	public List<Song> getByTags(List<GenreTag> tags)
	{
		StringBuilder cluase = new StringBuilder();
		cluase.append("tags = ");
		cluase.append("\'");
		cluase.append(tags.toString());
		cluase.append("\'");
		
		ResultSet resultSet = dataBaseManager.Pulliteam(songtableName, "", cluase.toString(), "", "");
		
		List<Song> songs = deserializeMultipleSongs(resultSet);
		
		closeResults(resultSet);
		
		return songs;
	}

	@Override
	public List<Song> getByLyrics(String lyrics) 
	{
		StringBuilder Cluase = new StringBuilder();
		Cluase.append("\"lyrics\" LIKE ");
		Cluase.append("\'%");
		Cluase.append(lyrics);
		Cluase.append("%\'");
		
		/**
		Cluase.append("regexp_matches(lyrics, ");
		Cluase.append(lyrics);
		Cluase.append(")");
		/**/
		
		/**
		Cluase.append("lyrics LIKE ");
		//Cluase.append("\'");
		Cluase.append(lyrics);
		//ssCluase.append("\'");
		/**/
		ResultSet resultSet = dataBaseManager.Pulliteam(songtableName, "",  Cluase.toString(), "", "");
		
		List<Song> songs = deserializeMultipleSongs(resultSet);
		
		closeResults(resultSet);
		
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
		values.add(song.getSongPath());
		
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
						resultSet.getString("SongPath"),
						convertStringToGenreTagList(resultSet.getString("tags")),
						resultSet.getString("Lyrics")
						);
		} 
		catch (SQLException e) 
		{
			logger.error("deseralizeSong: " + e.getMessage());
		}
		
		return song;
	}
	
	private List<GenreTag> convertStringToGenreTagList(String tags)
	{
		String fixed = tags;
		fixed = fixed.replace("[", "");
		fixed = fixed.replace("]", "");
		String[] tagsSplit = fixed.split(",");
		List<GenreTag> finalTags = new ArrayList<GenreTag>();
		
		for(String tag : tagsSplit)
		{
			finalTags.add(GenreTag.valueOf(tag));
		}
		
		return finalTags;
	}
	
	private void closeResults(ResultSet resultSet)
	{
		try 
		{
			resultSet.close();
		} 
		catch (SQLException e) 
		{
			logger.error("closeResults: " + e.getMessage());
		}
	}

	@Override
	public void remove(Song song) {
		// TODO Auto-generated method stub
		
	}
	
	/*
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
	*/

}
