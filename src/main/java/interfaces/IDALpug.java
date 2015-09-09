package interfaces;

import java.util.List;

import models.GenreTag;
import models.Song;

public interface IDALpug
{
	void add(Song song);

	Song getByTitle(String title);
	
	List<Song> getByTag(GenreTag tag);
	
	List<Song> getByTags(List<GenreTag> tag);
	
	List<Song> getByLyrics(String Lyrics);

	void remove(Song song);

}
