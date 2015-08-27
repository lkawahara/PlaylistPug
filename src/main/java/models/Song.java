package models;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.swing.text.html.HTML.Tag;

public class Song
{
	private final String songPath;
	
	private final Object data;
	
	private final String songName;
	
	private List<Tag> tags;
	
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
	
	public Song(String songName, Object data, String songPath, List<Tag> tags)
	{
		this.data = data;
		this.songPath = songPath;
		this.tags = tags;
		this.songName = songName;
	}
	
	public Song(String songName, Object data, String songPath, List<Tag> tags, String Lyrics)
	{
		this.data = data;
		this.songPath = songPath;
		this.tags = tags;
		this.Lyrics = Lyrics;
		this.songName = songName;
	}
	
	public List<Tag> getTags() 
	{
		return tags;
	}

	public void setTags(List<Tag> tags) 
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
	
	
}
