package models;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;

public class AudioData {

	private int bpm;
	private Collection<GenreTag> tags;
	
	public AudioData(FileInputStream fileInputStream)
	{
		AudioDataHelper(fileInputStream);
	}
	
	public AudioData(FileInputStream fileInputStream, Collection<GenreTag> matchingTags)
	{
		AudioDataHelper(fileInputStream);
		tags.addAll(matchingTags);
	}
	
	public void AudioDataHelper(FileInputStream fileInputStream){
		try {
			bpm = new BeatDetector().calculateBPM(fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tags = new ArrayList<GenreTag>();
	}
	
	public Collection<GenreTag> getTags(){
		return tags;
	}
	
	public int getNumMatchingTags(Collection<GenreTag> matchingTags){
		int numMatchingTags = 0;
		for(GenreTag t : matchingTags){
			if(tags.contains(t)){
				numMatchingTags ++;
			}
		}
		return numMatchingTags;
	}

	public boolean hasTag(GenreTag tag){
		return(tags.contains(tag));
	}

	public void addTag(GenreTag tag){
		tags.add(tag);
	}
	
	public int getBPM()
	{
		return bpm;
	}
	
}
