package audioTesting;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import junit.framework.Assert;

import org.junit.Test;

import playlistpug.models.AudioData;


public class AudioAnalysisTest {

	@Test
	public void testBPM() throws FileNotFoundException {
		AudioData ad = new AudioData(new FileInputStream("src/main/java/tempFiles/Springtime.mp3"));
		Assert.assertNotSame(0, ad.getBPM());
	}

}
