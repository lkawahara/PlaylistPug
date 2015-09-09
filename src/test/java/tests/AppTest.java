package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import playlistpug.models.PlaylistCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/springapp-servlet.xml" })
public class AppTest {
	@Autowired
	private PlaylistCreator playlistCreator;
	
	public AppTest(){}
	
	public void setPlaylistCreator(PlaylistCreator playlistCreator){
		this.playlistCreator = playlistCreator;
	}
	
	@Test
	public void test() {
		playlistCreator.getPlaylist();
	}

}
