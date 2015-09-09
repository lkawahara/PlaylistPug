package playlistpug.db;

import java.util.List;

import playlistpug.models.GenreTag;
import playlistpug.models.Song;

public interface IDALpug
{
	long add(Song song);
	
	Song getById(long id);

	Song getByTitle(String title);
	
	List<Song> getByTag(GenreTag tag);
	
	List<Song> getByTags(List<GenreTag> tag);
	
	List<Song> getByLyrics(String Lyrics);

	void delete(long id);
}
