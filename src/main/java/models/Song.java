package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SongList")
public class Song
{
	@Id
	private String songName;
	
	@Column(name="SONG_PATH")
	private String songPath;
	
	@OneToOne
	@JoinColumn(name="AUDIO_DATA_ID")
	private AudioData data;
	
	@Column(name="TAGS")
	@ElementCollection(targetClass = GenreTag.class)
	@Enumerated(EnumType.STRING)
	private List<GenreTag> tags;
	
	@Column(name="LYRICS")
	private String lyrics;
	
	public Song(){}
	
	public Song(AudioData data)
	{
		this.data = data;
		this.songName = String.valueOf(data.getBPM());
		this.songPath = null;
		this.tags = new ArrayList<GenreTag>();
		this.lyrics = "not entered";
	}
	
	public Song(String songName, AudioData data)
	{
		this.data = data;
		this.songName = songName;//String.valueOf(data.getBPM());
		this.songPath = null;
		this.tags = new ArrayList<GenreTag>();
		this.lyrics = "not entered";
	}

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
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lyrics == null) ? 0 : lyrics.hashCode());
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
		else if(!this.lyrics.equals(otherSong.getLyrics()))
		{
			result = false;
		}
		
		return result;
	}
}
