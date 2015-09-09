package playlistpug.legacy;

import java.util.List;

import playlistpug.models.GenreTag;
import playlistpug.models.Song;

public interface IDALpug
{
	Long add(Song song);
	
	Song getById(Long id);

	Song getByTitle(String title);
	
	List<Song> getByTag(GenreTag tag);
	
	List<Song> getByTags(List<GenreTag> tag);
	
	List<Song> getByLyrics(String Lyrics);

}
