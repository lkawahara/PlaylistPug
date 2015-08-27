package playlistCreation;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Assert;

//takes in an audioDataObject
//compares it against all the other audioDataObjects in the db
//generates a score for each of them
	//store song and score somewhere
//go through song score map and pick top 25 to create the playlist
public class PlaylistCreator {
	
	private class AudioData{
		public int getBpm() {
			return 0;
		}

		public AudioGenreTag[] getTags() {
			return null;
		}

		public boolean hasTag(AudioGenreTag tag) {
			// TODO Auto-generated method stub
			return false;
		}};
	private class AudioGenreTag {};
	private class AudioDataService{
		public Set<AudioData> getAllSongs() {
			return null;
		}};
	private AudioDataService songService; 
	private AudioData startingSong;
	private AudioData [] playlist;
	private static int NUM_SONGS = 10;
	private Map<Float, AudioData> songScoreMap;
	
	//AudioData
	public PlaylistCreator(AudioData songData){
		startingSong = songData;
		songService = new AudioDataService();
		playlist = new AudioData[NUM_SONGS];
		songScoreMap = new TreeMap<>();
		compareAllSongs();
	}
	
	//updates songScoreMap and playlist
	private void compareAllSongs(){
		//generates a playlist from all the songs grabbed from songService, compared to startingSong
		//grabs NUM_SONGS
		updateSongMap();
		for(int i = 0; i < playlist.length; i++){
			playlist[i] = songScoreMap.get(i);
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
	private int compareToStartingSong(AudioData toCompare){
		//generate a score
		int compatibilityScore = 0;
			//difference in bpm adversely affects score 
			int bpmDiff = Math.abs(toCompare.getBpm() - startingSong.getBpm());
			compatibilityScore -= bpmDiff;
		
		for(AudioGenreTag tag : toCompare.getTags()){
			if(startingSong.hasTag(tag)){
				compatibilityScore += TAG_SCORE_INCR;
			}
		}
		return compatibilityScore;
	}
}