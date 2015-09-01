package playlistCreation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import models.AudioData;
import models.GenreTag;
import models.Song;

import org.junit.Assert;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

//takes in an audioDataObject
//compares it against all the other audioDataObjects in the db
//generates a score for each of them
	//store song and score somewhere
//go through song score map and pick top 25 to create the playlist
public class PlaylistCreator {
	
	LoadingCache<Song, Map<Float, Song>> songScoreMapCache = CacheBuilder.newBuilder()
		       .maximumSize(100)
		       .expireAfterWrite(1, TimeUnit.DAYS)
		       .build(
		           new CacheLoader<Song, Map<Float, Song>>() {
		             public Map<Float, Song> load(Song startingSong) throws ExecutionException {
		               return generateSongScoreMap(startingSong);
		             }
		           });
	
	LoadingCache<Map<Float, Song>, Song []> playlistCache = CacheBuilder.newBuilder()
		       .maximumSize(100)
		       .expireAfterWrite(1, TimeUnit.DAYS)
		       .build(
		           new CacheLoader<Map<Float, Song>, Song []>() {
		             public Song [] load(Map<Float, Song> songScoreMap) throws ExecutionException {
		               return generatePlaylist(songScoreMap);
		             }
		           });
	
	//TODO replace with IDAL implementation
	private class AudioDataService{
		private List<Song> songs;
		
		public AudioDataService(List<Song> songs){
			this.songs = songs;
		}
		
		public List<Song> getAllSongs() {
			return songs;
		}
	};
	
	private AudioDataService songService; 
	private static int NUM_SONGS = 5;
	
	//AudioData
	public PlaylistCreator(List<Song> songs){
		songService = new AudioDataService(songs);
	}
	
	private Song[] generatePlaylist(Map<Float, Song> songScoreMap){
		//generates a playlist from all the songs in songService, compared to startingSong
		List<Song> allSongs = new ArrayList<Song>(songScoreMap.values());
		Song[] playlist = new Song[NUM_SONGS];
		for(float i = 0; i < playlist.length; i+= 1){
			playlist[(int)i] = allSongs.get((int)i);
		}
		return playlist;
	}
	
	private Map<Float, Song> generateSongScoreMap(Song starting){
		//key: compatibilityScore, value: songAudioData
		Map<Float, Song> songScoreMap = new TreeMap<Float, Song>();
		for(Song song : songService.getAllSongs()){
			float compatibilityKey = compareSongs(starting, song);
			while(songScoreMap.containsKey(compatibilityKey)){
				compatibilityKey += 0.001f; //automatically put the next key lower in value
			}
			songScoreMap.put(compatibilityKey, song);
		}
		return songScoreMap;
	}
	
	//tries to get a cached version of the song score map, else generates a new map and caches that value
	private Map<Float, Song> getSongScoreMap(Song starting){
		return songScoreMapCache.getUnchecked(starting);
	}
	
	//tries to get a cached version of the playlist, else generates a new playlist and caches that value
	public Song[] getPlaylist(){
		Song starting = songService.getAllSongs().get(0);
		return playlistCache.getUnchecked( getSongScoreMap(starting) );
	}
	
	public Song[] getPlaylist(Song starting){
		return playlistCache.getUnchecked( getSongScoreMap(starting) );
	}
	
	int TAG_SCORE_INCR = 50;
	int TAG_MISMATCH_SCORE_DECR = 20;
	int STARTING_COMPATIBILITY_SCORE = Integer.MAX_VALUE;//for tree map purposes
	int COMPATIBILITY_SCORE_CAP = Integer.MAX_VALUE;
	//don't allow for negative numbers as compatibility scores 
	//they will shoot that unlikely matching song to the front of the tree map
	private int compareSongs(Song startingSong, Song toCompare){
		//generate a starting score
		int compatibilityScore = STARTING_COMPATIBILITY_SCORE;
		
		//BPM adversely affects score
		compatibilityScore = affectScoreBasedOnBPM(compatibilityScore, startingSong, toCompare);
		
		//matching tags adds to the score
		compatibilityScore = affectScoreBasedOnTags(compatibilityScore,  startingSong, toCompare);
		
		return compatibilityScore;
	}
	
	//returns bpm difference
	private int affectScoreBasedOnBPM(int compatibilityScore, Song startingSong, Song toCompare){
		compatibilityScore += Math.abs(toCompare.getData().getBPM() - startingSong.getData().getBPM());
		return clamp(compatibilityScore, 0, COMPATIBILITY_SCORE_CAP);
	}
	
	private int affectScoreBasedOnTags(int compatibilityScore, Song startingSong, Song toCompare){
		Collection<GenreTag> toCompareTags = toCompare.getData().getTags();
		for(GenreTag tag : startingSong.getData().getTags()){
			//increase/decrease score based on matching/mismatching tag
			compatibilityScore = (toCompareTags.contains(tag)) ? (compatibilityScore - TAG_SCORE_INCR) : (compatibilityScore + TAG_MISMATCH_SCORE_DECR);
		}
		return clamp(compatibilityScore, 0, COMPATIBILITY_SCORE_CAP);
	}
	
	public static int clamp(int val, int min, int max) {
	    return Math.max(min, Math.min(max, val));
	}
	
	//current functionality:
	//given songs in SongService
	//calculate compatibility score of each song in DB compared to starting song
		//should get next song then recalculate based on song before
	
	//ordering cases
	//by bpm and tags
		//songs will have non-matching BPM (decrease by difference)
		//songs will have non-matching tags (decrease score)
		//songs will have matching tags (increase score)
	//electronic 180
	//classical rock country 170
	//bpm diff: 10 score = -10
	//mismatching tags: 3 * 20; score = -70
	
	//songs with matching bpm (second analyzed song comes next)
	//songs with matching bpm but different tags
		//score can be negative depending on how many unmatched tags there are 
	//songs with matching tags but different bpm 
		//if bpmDifference is > (#MatchedTags * Tag_Score_Increment): SCORE IS NEGATIVE
	
	//determining next song by bpm is more important than tags
}