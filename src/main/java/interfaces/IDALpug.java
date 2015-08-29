package interfaces;

import java.util.List;

import javax.swing.text.html.HTML.Tag;

import models.Song;

public interface IDALpug
{
	Long add(Song song);
	
	Song getById(Long id);

	Song getByTitle(String title);
	
	List<Song> getByTag(Tag tag);
	
	List<Song> getByTags(List<Tag> tag);
	
	List<Song> getByLyrics(String Lyrics);

}
