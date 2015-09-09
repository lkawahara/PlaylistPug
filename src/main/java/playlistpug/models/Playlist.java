package playlistpug.models;

public class Playlist {
	private Song[] songs;
	private int currIndex;
	
	public Playlist(Song[] songs){
		this.songs = songs;
		currIndex = 0;
	}
	
	
}
