package playlistpug.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import playlistpug.db.DALpug;
import playlistpug.models.Song;

public class SongService {
	@Autowired
	private DALpug dalPug;
	
	public SongService(){}
	
	public void setDalPug(DALpug dalPug){
		this.dalPug = dalPug;
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

}
