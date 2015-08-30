package models;

import java.util.ArrayList;

import com.badlogic.audio.io.MP3Decoder;


public class VolumeAnalyzer {

	private float startVolume;
	private float endVolume;
	private float startAverageVolume;
	private float endAverageVolume;
	
	public VolumeAnalyzer(MP3Decoder decoder)
	{
		try {
			float[] samples = readInAllSamples( decoder );
			analyze(samples);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public float getStartVolume() {
		return startVolume;
	}

	public float getEndVolume() {
		return endVolume;
	}

	private void analyze(float[] samples)
	{
		startVolume = Math.abs(samples[0]);
		endVolume = Math.abs(samples[samples.length-1]);
		
		startAverageVolume = sampleAverage(samples, 0, 10);
		endAverageVolume = sampleAverage(samples, samples.length, samples.length-1);
	}
	
	private float sampleAverage(float[] samples, int start, int end)
	{
		float total = 0;
		float count = 0;
		for(int i = start; i < end; i++)
		{
			total += Math.abs(samples[i]);
			count++;
		}
		return total/count;
		
	}
	
	
	
	private float[] readInAllSamples(MP3Decoder decoder) 
	{
		//MP3Decoder decoder = new MP3Decoder( fileInputStream );
		ArrayList<Float> allSamples = new ArrayList<Float>( );
		float[] samples = new float[1024];

		while( decoder.readSamples( samples ) > 0 )
		{
			for( int i = 0; i < samples.length; i++ )
				allSamples.add( samples[i] );
		}

		samples = new float[allSamples.size()];
		for( int i = 0; i < samples.length; i++ )
			samples[i] = allSamples.get(i);

		return samples;
	}
}
