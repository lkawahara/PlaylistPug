package playlistCreation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import models.AudioData;
import models.GenreTag;

import org.junit.Test;

public class PlaylistCreatorTest {

	//test which only tests playlistCreator functionality
	//uses debug constructor of AudioData to skip analyzing songs for faster test
	@Test 
	public void PlaylistCreatorTestTest(){
		PlaylistCreator pc = new PlaylistCreator(getDummyBPMSongs());
		AudioData[] generatedPlaylist = pc.getPlaylist();
		
		int currBPM = 3000;
		//can be increasing or decreasing in bpm
		for(AudioData song : generatedPlaylist){
			int songBPM = song.getBPM();
			Assert.assertTrue(songBPM < currBPM);
			currBPM = songBPM;
		}
	}
	
	//starting song: 170bpm 
	//test should order songs in decrementing order due to next appropriate value 
	@Test 
	public void OrderPlaylistByBPM() throws IOException {
		PlaylistCreator pc = new PlaylistCreator(getSongs());
		AudioData[] generatedPlaylist = pc.getPlaylist();
		
		int currBPM = 3000;
		//can be increasing or decreasing in bpm
		for(AudioData song : generatedPlaylist){
			int songBPM = song.getBPM();
			Assert.assertTrue(songBPM < currBPM);
			currBPM = songBPM;
		}
	}
	
	//@Test
	public void OrderPlaylistByCloserBPM() throws IOException {
		PlaylistCreator pc = new PlaylistCreator(getCloserBPMSongs());
		AudioData[] generatedPlaylist = pc.getPlaylist();
		
		int currBPM = 3000;
		for(AudioData song : generatedPlaylist){
			int songBPM = song.getBPM();
			Assert.assertTrue(songBPM < currBPM);
			currBPM = songBPM;
		}
	}
	
	//@Test
	public void OrderPlaylistByTag() throws IOException {
		PlaylistCreator pc = new PlaylistCreator(getTagSongs());
		AudioData[] generatedPlaylist = pc.getPlaylist();
		
		int currBPM = 3000;
		for(AudioData song : generatedPlaylist){
			int songBPM = song.getBPM();
			Assert.assertTrue(songBPM < currBPM);
			currBPM = songBPM;
		}
	}
	
	private List<AudioData> getDummyBPMSongs(){
		List<AudioData> songs = new ArrayList<>();

		songs.add(new AudioData(170));
		songs.add(new AudioData(150));
		songs.add(new AudioData(130));
		songs.add(new AudioData(110));
		songs.add(new AudioData(90));
		songs.add(new AudioData(70));
		songs.add(new AudioData(50));
		
		return songs;
	}
	
	private List<AudioData> getSongs(){
		List<AudioData> songs = new ArrayList<>();
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1500002
		AudioData bpm170 = null, bpm150 = null, bpm130 = null, bpm110 = null, bpm90 = null;
		try {
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1500002
			bpm170 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/170BPM.mp3")));
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200053
			bpm150 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/150BPM.mp3")));
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100742
			bpm130 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/130BPM.mp3")));
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1500053
			bpm110 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/110BPM.mp3")));
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200070
			bpm90 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/90BPM.mp3")));
		} catch (FileNotFoundException e) {
			System.out.println("file not found: " + e.getMessage());
		}
		songs.add(bpm170);
		songs.add(bpm150);
		songs.add(bpm130);
		songs.add(bpm110);
		songs.add(bpm90);
		return songs;
	}
	
	private List<AudioData> getCloserBPMSongs(){
		List<AudioData> songs = new ArrayList<>();
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1500002
		AudioData bpm170 = null, bpm165 = null, bpm160 = null, bpm155 = null, bpm150 = null;
		try {
			bpm170 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/170BPM.mp3")));
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200053
			bpm165 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/165BPM.mp3")));
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100742
			bpm160 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/160BPM.mp3")));
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1500053
			bpm155 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/155BPM.mp3")));
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200070
			bpm150 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/150BPM.mp3")));
		} catch (FileNotFoundException e) {
			System.out.println("file not found: " + e.getMessage());
		}
		songs.add(bpm170);
		songs.add(bpm165);
		songs.add(bpm160);
		songs.add(bpm155);
		songs.add(bpm150);
		return songs;
	}
	
	private List<AudioData> getTagSongs(){
		List<AudioData> songs = new ArrayList<>();
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1500002
		AudioData bpm170 = null, bpm165 = null, bpm160 = null, bpm155 = null, bpm150 = null;
		try {
			bpm170 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/170BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Rock);}});
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200053
			bpm165 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/165BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Soundtrack);}});
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100742
			bpm160 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/160BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Electronic);}});
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1500053
			bpm155 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/155BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Electronic);}});
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200070
			bpm150 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/150BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Classical);}});
		} catch (FileNotFoundException e) {
			System.out.println("file not found: " + e.getMessage());
		}
		songs.add(bpm170);
		songs.add(bpm165);
		songs.add(bpm160);
		songs.add(bpm155);
		songs.add(bpm150);
		return songs;
	}
};
