package playlistpug.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "SONGS")
@SequenceGenerator(name = "seq", initialValue = 0, allocationSize = 1000)
public class Song {
	@Id
	@Column(name = "SONG_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	private Long id;

	@OneToOne @MapsId
	private AudioData data;
	
	private String songName;
	private String songPath;
	private String lyrics;

	public Song() {
	}

	public Song(AudioData data) {
		this.data = data;
		this.songName = String.valueOf(data.getBPM());
		this.songPath = null;
		this.lyrics = "not entered";
	}

	public Song(String songName, AudioData data) {
		this.data = data;
		this.songName = songName;// String.valueOf(data.getBPM());
		this.songPath = null;
		this.lyrics = "not entered";
	}

	public Song(String songName, AudioData data, String songPath) {
		this.data = data;
		this.songPath = songPath;
		this.songName = songName;
	}

	public Song(String songName, AudioData data, String songPath, String lyrics) {
		this.data = data;
		this.songPath = songPath;
		this.lyrics = lyrics;
		this.songName = songName;
	}

	public List<GenreTag> getTags() {
		return new ArrayList<GenreTag>(this.data.getTags());
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public String getTitle() {
		return songName;
	}

	public String getSongPath() {
		return songPath;
	}

	public AudioData getData() {
		return data;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Song)){
			return false;
		}
		
		Song otherSong = (Song) obj;
		return ( this.data.equals(otherSong.getData()) 
				&& this.songName.equals(otherSong.getTitle())
				&& this.songPath.equals(otherSong.getSongPath())
				&& this.lyrics.equals(otherSong.getLyrics()) );
	}
}