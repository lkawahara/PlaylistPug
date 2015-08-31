package models;

import java.util.ArrayList;
import java.util.List;

public class Song
{
	private final String songPath;
	
	private final Object data;
	
	private final String songName;
	
	private List<GenreTag> tags;
	
	private String Lyrics;
	
	public Song(String songName, Object data, String songPath)
	{
		this.data = data;
		this.songPath = songPath;
		this.tags = new ArrayList<>();
		this.songName = songName;
	}
	
	public Song(String songName, Object data, String songPath, String Lyrics)
	{
		this.data = data;
		this.songPath = songPath;
		this.tags = new ArrayList<>();
		this.Lyrics = Lyrics;
		this.songName = songName;
	}
	
	public Song(String songName, Object data, String songPath, List<GenreTag> tags)
	{
		this.data = data;
		this.songPath = songPath;
		this.tags = tags;
		this.songName = songName;
	}
	
	public Song(String songName, Object data, String songPath, List<GenreTag> tags, String Lyrics)
	{
		this.data = data;
		this.songPath = songPath;
		this.tags = tags;
		this.Lyrics = Lyrics;
		this.songName = songName;
	}
	
	public List<GenreTag> getTags() 
	{
		return tags;
	}

	public void setTags(List<GenreTag> tags) 
	{
		this.tags = tags;
	}

	public String getLyrics()
	{
		return Lyrics;
	}

	public void setLyrics(String lyrics)
	{
		Lyrics = lyrics;
	}
	
	public String getTitle()
	{
		return songName;
	}

	public String getSongPath()
	{
		return songPath;
	}

	public Object getData()
	{
		return data;
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Lyrics == null) ? 0 : Lyrics.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((songName == null) ? 0 : songName.hashCode());
		result = prime * result + ((songPath == null) ? 0 : songPath.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		Song otherSong = (Song) obj;
		boolean result = true;
		
		if(!this.songPath.equals(otherSong.getSongPath()))
		{
			result = false;
		}
		/*
		else if(!this.data.equals(otherSong.getData()))
		{
			result = false;
		}
		*/
		else if(!this.songName.equals(otherSong.getTitle()))
		{
			result = false;
		}
		else if(!this.tags.equals(otherSong.getTags()))
		{
			result = false;
		}
		else if(!this.Lyrics.equals(otherSong.getLyrics()))
		{
			result = false;
		}
		
		return result;
	}
	
	
}
