package playlistpug.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import playlistpug.models.GenreTag;
import playlistpug.models.Song;

public class DALpug implements IDALpug {
	@Autowired
	private SessionFactory sessionFactory;

	public DALpug() {	}
	
	public DALpug(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getOpenTransactionSession(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}
	
	@Override
	public long add(Song song) {
		Session session = getOpenTransactionSession();
		long id = (Long)session.save(song);
		session.getTransaction().commit();
		session.close();
		return id;
	}

	@Override
	public Song getById(long id) {
		Session session = getOpenTransactionSession();
		Song ret = (Song) session.get(Song.class, id);
		session.close();
		
		return ret;
	}

	@Override
	public Song getByTitle(String title) {
		Collection<Song> songs = getItems();
		for(Song s : songs){
			if(s.getTitle().equals(title)){
				return s;
			}
		}
		return null;
	}

	@Override
	public List<Song> getByTag(GenreTag tag) {
		Collection<Song> songs = getItems();
		List<Song> ret = new ArrayList<>();
		for(Song s : songs){
			if(s.getTags().contains(tag)){
				ret.add(s);
			}
		}
		return ret;
	}
	public Collection<Song> getItems() {
		Session session = getOpenTransactionSession();
		Collection<Song> ret = (Collection<Song>) session.createQuery("SELECT x FROM Song x").list();
		session.close();
		return ret;
	}
	@Override
	public List<Song> getByTags(List<GenreTag> tags) {
		Collection<Song> songs = getItems();
		List<Song> ret = new ArrayList<>();
		for(Song s : songs){
			if(s.getTags().containsAll(tags)){
				ret.add(s);
			}
		}
		return ret;
	}

	@Override
	public List<Song> getByLyrics(String lyrics) {
		Collection<Song> songs = getItems();
		List<Song> ret = new ArrayList<>();
		for(Song s : songs){
			if(s.getLyrics().contains(lyrics)){
				ret.add(s);
			}
		}
		return ret;
	}

	@Override
	public void delete(long id) {
		Session session = getOpenTransactionSession();
		Song gotten = (Song)session.get(Song.class, id);
		session.delete(gotten);
		session.getTransaction().commit();
		session.close();		
	}

}
