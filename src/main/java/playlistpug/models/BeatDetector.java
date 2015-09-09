package playlistpug.models;

import java.io.FileInputStream;
import java.util.ArrayList;

import com.badlogic.audio.analysis.SpectrumProvider;
import com.badlogic.audio.io.MP3Decoder;


public class BeatDetector {

	private static final int HOP_SIZE = 1024;	
	private static final float secondsPerSample = HOP_SIZE/44100f;
	private static final int SAMPLE_BUFFER_SIZE = 100;
	
	public int calculateBPM(MP3Decoder decoder) throws Exception
	{
		ArrayList<Float> spectralFlux = getSpectralFlux(decoder);
		@SuppressWarnings("unchecked")
		ArrayList<Float> beatsDetected = (ArrayList<Float>) spectralFlux.clone();
		float[] currentSampleBuffer = new float[SAMPLE_BUFFER_SIZE];
		float averageSampleValue = 0;
		float previousSampleValue = -1;
		float previousPreviousSampleValue = -2;
		int sampleNum = 1;
		int beatCount = 0;
		for(int i = 0; i < spectralFlux.size(); i++)
		{
			float timeStamp = secondsPerSample*sampleNum++;

			currentSampleBuffer[sampleNum%SAMPLE_BUFFER_SIZE] = spectralFlux.get(i);
			averageSampleValue = getAverageBufferValue(currentSampleBuffer);
			if(spectralFlux.get(i)>(averageSampleValue *1.03f) && spectralFlux.get(i) < previousSampleValue && previousSampleValue > previousPreviousSampleValue) 
			{
				beatCount++;
				//System.out.println(timeStamp + ": " + spectralFlux.get(i));

			}
			else
			{
				beatsDetected.set(i, 0f);
			}
			previousPreviousSampleValue = previousSampleValue;
			previousSampleValue = spectralFlux.get(i);

		}
		float lengthInMinutes = ( secondsPerSample*spectralFlux.size() )/60f;
		
		// UNCOMMENT FOR VISUALIZER OF SPECTRAL FLUX & BEATS
//		Plot plot = new Plot( "Spectral Flux", 1024, 512 );
//		plot.plot( spectralFlux, 1, Color.red );
//		plot.plot(beatsDetected, 1, Color.green);
//		new PlaybackVisualizer( plot, HOP_SIZE, new MP3Decoder( new FileInputStream( FILE ) ) );
		
		return (int) (beatCount/lengthInMinutes);
	}
	
	private static float getAverageBufferValue(float[] buffer)
	{
		float total = 0;
		int zeroCount = 0;
		for(float sample : buffer)
		{
			if(sample == 0) zeroCount++;
			total+=sample;
		}
		return total/(buffer.length-zeroCount);
	}
	
	private ArrayList<Float> getSpectralFlux(MP3Decoder decoder ) throws Exception
	{
		SpectrumProvider spectrumProvider = new SpectrumProvider( decoder, 1024, HOP_SIZE, true );			
		float[] spectrum = spectrumProvider.nextSpectrum();
		float[] lastSpectrum = new float[spectrum.length];
		ArrayList<Float> spectralFlux = new ArrayList<Float>( );

		while(spectrum !=  null)
		{
			float flux = 0;
			for( int i = 0; i < spectrum.length; i++ )
			{
				float value = (spectrum[i] - lastSpectrum[i]); 
				flux += value < 0?0:value;
			}
			spectralFlux.add( flux );
			
			System.arraycopy( spectrum, 0, lastSpectrum, 0, spectrum.length );
			spectrum = spectrumProvider.nextSpectrum();
		}
		
		return spectralFlux;
	}
}
