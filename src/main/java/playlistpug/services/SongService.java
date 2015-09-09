package playlistpug.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import playlistpug.db.DALpug;
import playlistpug.models.AudioData;
import playlistpug.models.Song;

public class SongService {
	@Autowired
	private DALpug dalPug;
	
	public SongService(){}
	
	public void setDalPug(DALpug dalPug){
		this.dalPug = dalPug;
		if(dalPug.getItems().isEmpty()){
			addSongs();
		}
	}
	
	public Long create(Song newSong) {
		return dalPug.add(newSong);
	}

	public Song read(Long id) {
		return dalPug.getById(id);
	}

	public void delete(Long id) {
		dalPug.delete(id);
	}
	
	//can't update songs
	private boolean update(Long key, Song updateAnswer) {
//		Song answer = read(updateAnswer.getId());
//		if(answer == null){
//			return false;
//		}
//		Session session = getOpenTransactionSession();
//		answer.copyData(updateAnswer);
//		session.saveOrUpdate(answer);
//		session.getTransaction().commit();
//		session.close();
//		return true;
		return false;
	}

	public Collection<Song> getItems() {
		return dalPug.getItems();
	}
	
	public void addSongs(){
		create(new Song("song1", new AudioData(100), "songpath1"));
		create(new Song("song2", new AudioData(75), "songpath2"));
		create(new Song("song3", new AudioData(50), "songpath3"));
		create(new Song("song4", new AudioData(25), "songpath4"));
	}
}
