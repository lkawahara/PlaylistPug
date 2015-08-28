package playlistCreation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import models.AudioData;
import models.GenreTag;

import org.junit.Assert;

//takes in an audioDataObject
//compares it against all the other audioDataObjects in the db
//generates a score for each of them
	//store song and score somewhere
//go through song score map and pick top 25 to create the playlist
public class PlaylistCreator {
	
	private class AudioDataService{
		private List<AudioData> songs;
		
		public AudioDataService(List<AudioData> songs){
			this.songs = songs;
		}
		
		public List<AudioData> getAllSongs() {
			return songs;
		}
	};
	
	private AudioDataService songService; 
	private AudioData startingSong;
	private AudioData [] playlist;
	private static int NUM_SONGS = 5;
	private Map<Float, AudioData> songScoreMap;
	
	//AudioData
	public PlaylistCreator(List<AudioData> songs){
//		startingSong = songData; TODO change to input song
		songService = new AudioDataService(songs);
		startingSong = songService.getAllSongs().get(0);
		playlist = new AudioData[NUM_SONGS];
		songScoreMap = new TreeMap<>();
		compareAllSongs();
	}
	
	//updates songScoreMap and playlist
	private void compareAllSongs(){
		//generates a playlist from all the songs grabbed from songService, compared to startingSong
		//grabs NUM_SONGS
		updateSongMap();
		List<AudioData> allSongs = new ArrayList<AudioData>(songScoreMap.values());
		
		for(float i = 0; i < playlist.length; i+= 1){
			playlist[(int)i] = allSongs.get((int)i);
		}
	}
	
	private void updateSongMap(){
		songScoreMap.clear();
		for(AudioData song : songService.getAllSongs()){
			//key: compatibilityScore, value: songAudioData
			float compatibilityKey = compareToStartingSong(song);
			while(songScoreMap.containsKey(compatibilityKey)){
				compatibilityKey += 0.001f; //automatically put the next key lower in value
			}
			songScoreMap.put(compatibilityKey, song);
		}
	}
	
	public AudioData[] getPlaylist(){
		Assert.assertNotNull(playlist);
		return playlist;
	}
	
	int TAG_SCORE_INCR = 50;
	int TAG_MISMATCH_SCORE_DECR = -20;
	private int compareToStartingSong(AudioData toCompare){
		//generate a starting score
		int compatibilityScore = 0;
		
		//BPM adversely affects score
		affectScoreBasedOnBPM(compatibilityScore, toCompare);
		
		//matching tags adds to the score
		affectScoreBasedOnTags(compatibilityScore, toCompare);
		
		return compatibilityScore;
	}
	
	//returns bpm difference
	private int affectScoreBasedOnBPM(int compatibilityScore, AudioData toCompare){
		int bpmDiff = Math.abs(toCompare.getBPM() - startingSong.getBPM());
		compatibilityScore -= bpmDiff;
		return bpmDiff;
	}
	
	private void affectScoreBasedOnTags(int compatibilityScore, AudioData toCompare){
		Collection<GenreTag> toCompareTags = toCompare.getTags();
		for(GenreTag tag : startingSong.getTags()){
			//increase/decrease score based on matching/mismatching tag
			compatibilityScore = (toCompareTags.contains(tag)) ? (compatibilityScore + TAG_SCORE_INCR) : (compatibilityScore + TAG_MISMATCH_SCORE_DECR);
		}
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