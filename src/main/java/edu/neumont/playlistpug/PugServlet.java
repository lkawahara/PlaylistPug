package edu.neumont.java.playlistpug;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PugServlet
 */
@WebServlet("/pugs/*")
public class PugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PugServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = request.getPathInfo();
		String[] path = null;
		if(request.getPathInfo() == null || request.getPathInfo().equals("")){
			destination = "/WEB-INF/index.jsp";
		}else{
			path = request.getPathInfo().substring(1).split("/");
			Boolean b = destination.equals("/");
			if(destination.equals("/") || path[0].equals("index") || path[0].equals("main") || path[0].equals("home")){
				destination = "/WEB-INF/index.jsp";
			}else if(path[0].equals("upload")){
				destination = "/WEB-INF/upload.jsp";
			}else if(path[0].equals("song")){
				//set attributes name as the name of the song and
				//song as whatever is needed to play the song
				request.setAttribute("name", "song name");
				request.setAttribute("song", "song data");
				destination = "/WEB-INF/song.jsp";
			}else if(path[0].equals("search")){
				//depends on how we decide to do the searching
				//may set attribute to search terms or list of search results
				request.setAttribute("search_terms_or_results", "");
				destination = "/WEB-INF/search.jsp";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(destination);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}