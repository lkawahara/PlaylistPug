package models;

import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.swing.text.html.HTML.Tag;

public class Song
{
	private final byte[] songData;
	
	private final Object data;
	
	private final String songName;
	
	private List<Tag> tags;
	
	private String Lyrics;
	
	public Song(String songName, Object data, byte[] songData)
	{
		this.data = data;
		this.songData = songData;
		this.songName = songName;
	}
	
	public Song(String songName, Object data, byte[] songData, String Lyrics)
	{
		this.data = data;
		this.songData = songData;
		this.Lyrics = Lyrics;
		this.songName = songName;
	}
	
	public Song(String songName, Object data, byte[] songData, List<Tag> tags)
	{
		this.data = data;
		this.songData = songData;
		this.tags = tags;
		this.songName = songName;
	}
	
	public Song(String songName, Object data, byte[] songData, List<Tag> tags, String Lyrics)
	{
		this.data = data;
		this.songData = songData;
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

	public byte[] getSongData()
	{
		return songData;
	}

	public Object getData()
	{
		return data;
	}
	
	
}
