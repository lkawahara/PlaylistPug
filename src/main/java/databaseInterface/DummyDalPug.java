package databaseInterface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.text.html.HTML.Tag;

import models.Song;

public class DummyDalPug implements IDALpug
{	
	static Song dummySong;
	
	private void setup()
	{
		if(dummySong != null)
		{
			File soundFile = new File("/playlistpug/src/main/java/tempFiles/LooneyToonsEnd.wav");
			byte[] songBytes = null;
			Type type = Type.WAVE;
			AudioFormat audioFormat = null;
			
			int size = 0;
			try 
			{
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
				audioFormat = audioInputStream.getFormat();
				//type = audioFormat.getEncoding().
				size = audioInputStream.available();
				songBytes = new byte[size];
				audioInputStream.read(songBytes);
			} 
			catch (UnsupportedAudioFileException e)
			{
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			AudioFileFormat audioFileFormat = new AudioFileFormat(type, audioFormat, size);
			dummySong = new Song(audioFileFormat, null, songBytes);
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
