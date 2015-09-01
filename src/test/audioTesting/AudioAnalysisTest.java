package audioTesting;
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import junit.framework.Assert;
import models.AudioData;
import models.Song;

import org.junit.Test;


public class AudioAnalysisTest {

	@Test
	public void testBPM() throws FileNotFoundException {
		AudioData ad = new AudioData(new FileInputStream("src/main/java/tempFiles/Springtime.mp3"));
		Assert.assertNotSame(0, ad.getBPM());
	}

}
