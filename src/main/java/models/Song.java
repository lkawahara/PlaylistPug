package models;

import java.util.ArrayList;
import java.util.List;

public class Song
{
	private final String songPath;
	
	private final AudioData data;
	
	private final String songName;
	
	private List<GenreTag> tags;
	
	private String lyrics;
	
	public Song(String songName, AudioData data, String songPath)
	{
		this.data = data;
		this.songPath = songPath;
		this.tags = new ArrayList<>();
		this.songName = songName;
	}
	
	public Song(String songName, AudioData data, String songPath, String lyrics)
	{
		this.data = data;
		this.songPath = songPath;
		this.tags = new ArrayList<>();
		this.lyrics = lyrics;
		this.songName = songName;
	}
	
	public Song(String songName, AudioData data, String songPath, List<GenreTag> tags)
	{
		this.data = data;
		this.songPath = songPath;
		this.tags = tags;
		this.songName = songName;
	}
	
	public Song(String songName, AudioData data, String songPath, List<GenreTag> tags, String lyrics)
	{
		this.data = data;
		this.songPath = songPath;
		this.tags = tags;
		this.lyrics = lyrics;
		this.songName = songName;
	}
	
	public Song(AudioData data){
		this.data = data;
		this.songName = String.valueOf(data.getBPM());
		this.songPath = null;
		this.tags = new ArrayList<GenreTag>();
		this.lyrics = "not entered";
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
		return lyrics;
	}

	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}
	
	public String getTitle()
	{
		return songName;
	}

	public String getSongPath()
	{
		return songPath;
	}

	public AudioData getData()
	{
		return data;
	}
	
}
