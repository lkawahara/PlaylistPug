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
import models.Song;

import org.junit.Test;

public class PlaylistCreatorTest {

	//test which only tests playlistCreator functionality
	//uses debug constructor of AudioData to skip analyzing songs for faster test
	@Test 
	public void PlaylistCreatorTestTest(){
		PlaylistCreator pc = new PlaylistCreator(getDummyBPMSongs());
		Song[] generatedPlaylist = pc.getPlaylist();
		
		int currBPM = 3000;
		//can be increasing or decreasing in bpm
		for(Song song : generatedPlaylist){
			int songBPM = song.getData().getBPM();
			Assert.assertTrue(songBPM < currBPM);
			currBPM = songBPM;
		}
	}
	
	//starting song: 170bpm 
	//test should order songs in decrementing order due to next appropriate value 
	@Test 
	public void OrderPlaylistByBPM() throws IOException {
		PlaylistCreator pc = new PlaylistCreator(getSongs());
		Song[] generatedPlaylist = pc.getPlaylist();
		
		int currBPM = 3000;
		//can be increasing or decreasing in bpm
		for(Song song : generatedPlaylist){
			int songBPM = song.getData().getBPM();
			Assert.assertTrue(songBPM < currBPM);
			currBPM = songBPM;
		}
	}
	
	@Test
	public void OrderPlaylistByCloserBPM() throws IOException {
		PlaylistCreator pc = new PlaylistCreator(getCloserBPMSongs());
		Song[] generatedPlaylist = pc.getPlaylist();
		
		int currBPM = 3000;
		for(Song song : generatedPlaylist){
			int songBPM = song.getData().getBPM();
			Assert.assertTrue(songBPM < currBPM);
			currBPM = songBPM;
		}
	}
	
	//@Test
	public void OrderPlaylistByTag() throws IOException {
		PlaylistCreator pc = new PlaylistCreator(getTagSongs());
		Song[] generatedPlaylist = pc.getPlaylist();
		
		int currBPM = 3000;
		for(Song song : generatedPlaylist){
			int songBPM = song.getData().getBPM();
			Assert.assertTrue(songBPM < currBPM);
			currBPM = songBPM;
		}
	}
	
	@Test
	public void TestCaching() {
		PlaylistCreator pc = new PlaylistCreator(getDummyBPMSongs());
		
		long start = System.currentTimeMillis();
		Song[] generatedPlaylist = pc.getPlaylist();
		printoutSongs(generatedPlaylist);
		long end = System.currentTimeMillis();
		long timeTaken = end - start;
		
		start = System.currentTimeMillis();
		generatedPlaylist = pc.getPlaylist();
		printoutSongs(generatedPlaylist);
		end = System.currentTimeMillis();
		long timeTakenSecondTime = end - start;
		
		System.out.println("first time: " + timeTaken + "; second time: " + timeTakenSecondTime);
		Assert.assertTrue(timeTakenSecondTime < timeTaken);
	}
	
	private void printoutSongs(Song[] songs){
		for(int i = 0; i < songs.length; i ++){
			System.out.println("title " + i + " : " + songs[i].getTitle());
		}
	}
	
	private List<Song> getDummyBPMSongs(){
		List<Song> songs = new ArrayList<>();

		songs.add(new Song("bpm170", new AudioData(170)));
		songs.add(new Song("bpm150", new AudioData(150)));
		songs.add(new Song("bpm130", new AudioData(130)));
		songs.add(new Song("bpm110", new AudioData(110)));
		songs.add(new Song("bpm90", new AudioData(90)));
		songs.add(new Song("bpm70", new AudioData(70)));
		songs.add(new Song("bpm50", new AudioData(50)));
		
		return songs;
	}
	
	private List<Song> getSongs(){
		List<Song> songs = new ArrayList<>();
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
		songs.add(new Song("bpm170", bpm170));//71
		songs.add(new Song("bpm150", bpm150));//241
		songs.add(new Song("bpm130", bpm130));//89
		songs.add(new Song("bpm110", bpm110));//188
		songs.add(new Song("bpm90", bpm90));//124
		return songs;
	}
	
	private List<Song> getCloserBPMSongs(){
		List<Song> songs = new ArrayList<>();
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
		songs.add(new Song("bpm170", bpm170));
		songs.add(new Song("bpm165", bpm165));
		songs.add(new Song("bpm160", bpm160));
		songs.add(new Song("bpm155", bpm155));
		songs.add(new Song("bpm150", bpm150));
		return songs;
	}
	
	private List<Song> getTagSongs(){
		List<Song> songs = new ArrayList<>();
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1500002
		AudioData bpm170 = null, bpm165 = null, bpm160 = null, bpm155 = null, bpm150 = null;
		try {
			//202
			bpm170 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/170BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Rock);}});
			//146
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200053
			bpm165 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/165BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Soundtrack);}});
			//179
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100742
			bpm160 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/160BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Electronic);}});
			//241
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1500053
			bpm155 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/155BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Electronic);}});
			//71
			//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200070
			bpm150 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/150BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Classical);}});
		} catch (FileNotFoundException e) {
			System.out.println("file not found: " + e.getMessage());
		}
		songs.add(new Song("170 bpm", bpm170));
		songs.add(new Song("165 bpm", bpm165));
		songs.add(new Song("160 bpm", bpm160));
		songs.add(new Song("155 bpm", bpm155));
		songs.add(new Song("150 bpm", bpm150));
		return songs;
	}
};
