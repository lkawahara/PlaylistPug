package models;

import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.swing.text.html.HTML.Tag;

public class Song
{
	private final AudioFileFormat file;
	
	private final byte[] songData;
	
	private final Object data;
	
	private List<Tag> tags;
	
	private String Lyrics;
	
	public Song(AudioFileFormat file, Object data, byte[] songData)
	{
		this.file = file;
		this.data = data;
		this.songData = songData;
	}
	
	public Song(AudioFileFormat file, Object data, byte[] songData, List<Tag> tags, String Lyrics)
	{
		this.file = file;
		this.data = data;
		this.songData = songData;
		this.tags = tags;
		this.Lyrics = Lyrics;
	}
}
