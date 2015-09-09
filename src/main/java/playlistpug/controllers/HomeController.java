package playlistpug.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import playlistpug.models.PlaylistCreator;

@Controller
public class HomeController {
	private PlaylistCreator pc;

	//to test song partial (WILL REMOVE LATER)
    @RequestMapping("/song")
    public ModelAndView getSong(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	return new ModelAndView("song", "model", pc.getPlaylist()[0]);
    }
	
	//test to show all multiple songs
    @RequestMapping("/home")
    public ModelAndView getHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return new ModelAndView("home", "allSongs", pc.getSongs());
    }
    
    //user makes request to star
    @RequestMapping("/playlist")
    public ModelAndView getPlaylist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	long songid = Long.parseLong(request.getParameter(""));
        return new ModelAndView("playlist");
    }
}