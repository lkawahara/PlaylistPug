package database;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import interfaces.IDALpug;
import models.GenreTag;
import models.Song;

public class DALpugJPA implements IDALpug {

	@PersistenceContext EntityManager em;
	
	String songtableName = "SongList";
	
	@Override
	public void add(Song song) 
	{
		em.persist(song);
	}

	/**
	@Override
	public Song getById(Long id)
	{
		return entityManager.find(Song.class, id);
	}
	/**/
	
	@Override
	public Song getByTitle(String title)
	{
		return em.find(Song.class, title);
	}

	@Override
	public List<Song> getByTag(GenreTag tag) 
	{
		StringBuilder query = new StringBuilder();
		query.append("SELECT c FROM ");
		query.append(songtableName); query.append(" ");
		query.append("WHERE ");
		query.append(tag.toString()); query.append(" ");
		query.append("MEMBER OF c.TAGS");
		
		return (List<Song>)em.createQuery(query.toString(), Song.class).getResultList();
	}

	@Override
	public List<Song> getByTags(List<GenreTag> tags) 
	{
		StringBuilder query = new StringBuilder();
		query.append("SELECT c FROM ");
		query.append(songtableName); query.append(" ");
		query.append("WHERE ");
		
		int stop = tags.size();
		for(int step = 0; step < stop; step++)
		{
			query.append(tags.get(step).toString()); query.append(" ");
			query.append("MEMBER OF c.TAGS");
			
			if((step + 1) < stop)
			{
				query.append("AND ");
			}
		}
		
		return (List<Song>)em.createQuery(query.toString(), Song.class).getResultList();
	}

	@Override
	public List<Song> getByLyrics(String lyrics) 
	{
		StringBuilder query = new StringBuilder();
		query.append("SELECT c FROM ");
		query.append(songtableName); query.append(" ");
		query.append("WHERE ");
		query.append("c.LYRICS LIKE");
		query.append("\'%");
		query.append(lyrics);
		query.append("%\'");
		
		return (List<Song>)em.createQuery(query.toString(), Song.class).getResultList();
	}

	@Override
	public void remove(Song song) 
	{
		em.remove(song);
	}
	
}
