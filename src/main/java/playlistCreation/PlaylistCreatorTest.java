package playlistCreation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import models.AudioData;
import models.GenreTag;

import org.junit.Test;

public class PlaylistCreatorTest {

	@Test
	public void OrderPlaylistByBPM() throws IOException {
		List<AudioData> songs = new ArrayList<>();

		songs.add(new AudioData(new FileInputStream(new File("src/main/java/tempFiles/170BPM.mp3"))));
		songs.add(new AudioData(new FileInputStream(new File("src/main/java/tempFiles/150BPM.mp3"))));
		songs.add(new AudioData(new FileInputStream(new File("src/main/java/tempFiles/130BPM.mp3"))));
		songs.add(new AudioData(new FileInputStream(new File("src/main/java/tempFiles/110BPM.mp3"))));
		songs.add(new AudioData(new FileInputStream(new File("src/main/java/tempFiles/90BPM.mp3"))));
		songs.add(new AudioData(new FileInputStream(new File("src/main/java/tempFiles/70BPM.mp3"))));
		songs.add(new AudioData(new FileInputStream(new File("src/main/java/tempFiles/50BPM.mp3"))));
		
		PlaylistCreator pc = new PlaylistCreator(songs);
		AudioData[] generatedPlaylist = pc.getPlaylist();
		
		int currBPM = 3000;
		for(AudioData song : generatedPlaylist){
			int songBPM = song.getBPM();
			Assert.assertTrue(songBPM < currBPM);
			currBPM = songBPM;
		}
	}
	
	@Test
	public void OrderPlaylistByCloserBPM() throws IOException {
		List<AudioData> songs = new ArrayList<>();
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1500002
		AudioData bpm170 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/170BPM.mp3")));
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200053
		AudioData bpm165 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/165BPM.mp3")));
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100742
		AudioData bpm160 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/160BPM.mp3")));
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1500053
		AudioData bpm155 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/155BPM.mp3")));
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200070
		AudioData bpm150 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/150BPM.mp3")));
		songs.add(bpm170);
		songs.add(bpm165);
		songs.add(bpm160);
		songs.add(bpm155);
		songs.add(bpm150);
		
		PlaylistCreator pc = new PlaylistCreator(songs);
		AudioData[] generatedPlaylist = pc.getPlaylist();
		
		int currBPM = 3000;
		for(AudioData song : generatedPlaylist){
			int songBPM = song.getBPM();
			Assert.assertTrue(songBPM < currBPM);
			currBPM = songBPM;
		}
	}
	
	@Test
	public void OrderPlaylistByTag() throws IOException {
		List<AudioData> songs = new ArrayList<>();
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1500002
		AudioData bpm170 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/170BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Rock);}});
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200053
		AudioData bpm165 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/165BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Soundtrack);}});
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1100742
		AudioData bpm160 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/160BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Electronic);}});
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1500053
		AudioData bpm155 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/155BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Electronic);}});
		//http://incompetech.com/music/royalty-free/index.html?isrc=USUAN1200070
		AudioData bpm150 = new AudioData(new FileInputStream(new File("src/main/java/tempFiles/150BPM.mp3")), new ArrayList<GenreTag>(){{add(GenreTag.Classical);}});
		songs.add(bpm170);
		songs.add(bpm165);
		songs.add(bpm160);
		songs.add(bpm155);
		songs.add(bpm150);
		
		PlaylistCreator pc = new PlaylistCreator(songs);
		AudioData[] generatedPlaylist = pc.getPlaylist();
		
		int currBPM = 3000;
		for(AudioData song : generatedPlaylist){
			int songBPM = song.getBPM();
			Assert.assertTrue(songBPM < currBPM);
			currBPM = songBPM;
		}
	}
};
