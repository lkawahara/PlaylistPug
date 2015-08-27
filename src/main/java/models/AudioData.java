package models;

import java.io.FileInputStream;


public class AudioData {

	
	
	private int bpm;
	
	public AudioData(FileInputStream fileInputStream)//Should take Tags Enum as well
	{
		try {
			bpm = new BeatDetector().calculateBPM(fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int getBPM()
	{
		return bpm;
	}
	
	
}
