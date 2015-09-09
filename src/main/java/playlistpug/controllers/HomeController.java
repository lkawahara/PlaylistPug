package playlistpug.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import playlistpug.models.AudioData;
import playlistpug.models.PlaylistCreator;
import playlistpug.models.Song;

@Controller
public class HomeController {

	public HomeController(){}
	@Autowired
	private PlaylistCreator playlistCreator;
	public void setPlaylistCreator(PlaylistCreator playlistCreator){
		this.playlistCreator = playlistCreator;
	}

	//to test song partial (WILL REMOVE LATER)
    @RequestMapping("/song")
    public ModelAndView getSong(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	return new ModelAndView("song", "model", new Song("songname", new AudioData(120), "/songpath", "lyrics"));
    }
	
	//test to show all multiple songs
    @RequestMapping("/home")
    public ModelAndView getHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return new ModelAndView("home", "allSongs", playlistCreator.getSongs());
    }
    
    //user makes request to star
    @RequestMapping("/playlist")
    public ModelAndView getPlaylist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	long songid = Long.parseLong(request.getParameter(""));
        return new ModelAndView("playlist");
    }
}