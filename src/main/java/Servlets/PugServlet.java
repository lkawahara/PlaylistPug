package Servlets;

import dependencyInjector.DependencyInjector;
import interfaces.IDALpug;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.GenreTag;
import models.Song;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(value={"/pugs/*"})
public class PugServlet
extends HttpServlet {
    private static final long serialVersionUID = 1;
    private List<Song> searchList = new ArrayList<Song>();
    private String rowOne = "";
    private String rowTwo = "";
    private String rowThree = "";
    private String error = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IDALpug database = (IDALpug)DependencyInjector.getInstance().get("IDALpug");
        String destination = request.getPathInfo();
        String[] path = null;
        if (request.getPathInfo() == null || request.getPathInfo().equals("")) {
            destination = "/WEB-INF/index.jsp";
        } else {
            path = request.getPathInfo().substring(1).split("/");
            if (destination.equals("/") || path[0].equals("index") || path[0].equals("main") || path[0].equals("home")) {
                destination = "/WEB-INF/index.jsp";
            } else if (path[0].equals("upload")) {
                destination = "/WEB-INF/upload.jsp";
            } else if (path[0].equals("song") && path.length == 2) {
                try {
                    Long id = Long.parseLong(path[1]);
                    Song s = database.getById(id);
                    request.setAttribute("name", (Object)s.getTitle());
                    request.setAttribute("songPath", (Object)s.getSongPath());
                    request.setAttribute("lyrics", (Object)s.getLyrics());
                    destination = "/WEB-INF/song.jsp";
                }
                catch (NumberFormatException nfe) {
                    this.error = "This ID is not valid.";
                    destination = "/WEB-INF/error.jsp";
                }
            } else if (path[0].equals("search")) {
                request.setAttribute("rowOne", (Object)this.rowOne);
                request.setAttribute("rowTwo", (Object)this.rowTwo);
                request.setAttribute("rowThree", (Object)this.rowThree);
                destination = "/WEB-INF/search.jsp";
            } else {
                destination = "/WEB-INF/error.jsp";
                this.error = "This URL is Invalid";
            }
        }
        request.setAttribute("error", (Object)this.error);
        RequestDispatcher rd = request.getRequestDispatcher(destination);
        rd.forward((ServletRequest)request, (ServletResponse)response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect;
        IDALpug database = (IDALpug)DependencyInjector.getInstance().get("IDALpug");
        redirect = "";
        String p = request.getPathInfo().substring(1);
        String[] path = p.split("/");
        if (path[0].equals("upload") && path.length == 1) {
            redirect = "/pugs/upload";
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload((FileItemFactory)factory);
            String uploadFolder = String.valueOf(this.getServletContext().getRealPath("")) + "resources";
            try {
                List items = upload.parseRequest(request);
                for (FileItem item : (FileItem)items) {
                    if (item.isFormField()) continue;
                    String fileName = new File(item.getName()).getName();
                    String filePath = String.valueOf(uploadFolder) + File.separator + fileName;
                    File uploadedFile = new File(filePath);
                    item.write(uploadedFile);
                    ArrayList<GenreTag> tags = new ArrayList<GenreTag>();
                    GenreTag g = this.getTag(request.getParameter("genre"));
                    tags.add(g);
                    database.add(new Song(fileName, new AudioData(new FileInputStream(uploadedFile)), "/resources/" + fileName, request.getParameter("lyrics")));
                }
            }
            catch (FileUploadException ex) {
                redirect = "/pugs/error";
                this.error = "File Not Found";
            }
            catch (Exception ex) {
                redirect = "/pugs/error";
                this.error = "Error Saving File";
            }
        } else if (path[0].equals("nextSong") && path.length == 2) {
            redirect = "/pugs/song/1";
        } else if (path[0].equals("search") && path.length == 1) {
            List songs = database.getByLyrics("searchFor");
            this.searchList.add(database.getByTitle("searchFor"));
            for (Song s : songs) {
                this.searchList.add(s);
            }
            this.getResults();
            redirect = "/pugs/search";
        }
        response.sendRedirect(redirect);
    }

    /*
     * Exception decompiling
     */
    private GenreTag getTag(String s) {
        
        throw new IllegalStateException("Decompilation failed");
    }

    private void getResults() {
        int rounds = this.searchList.size() / 3;
        this.rowOne = "";
        this.rowTwo = "";
        if (this.searchList.size() == 1) {
            this.rowOne = String.valueOf(this.rowOne) + "<a href='/pugs/song/'>" + this.searchList.get(0).getTitle() + "</a>";
        } else if (this.searchList.size() == 2) {
            this.rowOne = String.valueOf(this.rowOne) + "<a href='/pugs/song/'>" + this.searchList.get(0).getTitle() + "</a>";
            this.rowTwo = String.valueOf(this.rowTwo) + "<a href='/pugs/song/'>" + this.searchList.get(1).getTitle() + "</a>";
        } else if (this.searchList.size() % 3 == 0) {
            int i;
            for (i = 0; i < rounds; ++i) {
                this.rowOne = String.valueOf(this.rowOne) + "<a href='/pugs/song/'>" + this.searchList.get(i).getTitle() + "</a>";
            }
            for (i = 0; i < rounds; ++i) {
                this.rowTwo = String.valueOf(this.rowTwo) + "<a href='/pugs/song/'>" + this.searchList.get(rounds + i).getTitle() + "</a>";
            }
            for (i = 0; i < rounds; ++i) {
                this.rowThree = String.valueOf(this.rowThree) + "<a href='/pugs/song/'>" + this.searchList.get(rounds * 2 + i).getTitle() + "</a>";
            }
        } else if (this.searchList.size() % 3 == 1) {
            int i;
            for (i = 0; i < rounds; ++i) {
                this.rowOne = String.valueOf(this.rowOne) + "<a href='/pugs/song/'>" + this.searchList.get(i).getTitle() + "</a>";
            }
            for (i = 0; i < rounds; ++i) {
                this.rowTwo = String.valueOf(this.rowTwo) + "<a href='/pugs/song/'>" + this.searchList.get(rounds + i).getTitle() + "</a>";
            }
            for (i = 0; i < rounds; ++i) {
                this.rowThree = String.valueOf(this.rowThree) + "<a href='/pugs/song/'>" + this.searchList.get(rounds * 2 + i).getTitle() + "</a>";
            }
            if (this.searchList.size() != 1) {
                this.rowOne = String.valueOf(this.rowOne) + "<a href='/pugs/song/'>" + (Object)this.searchList.get(this.searchList.size() - 1) + "</a>";
            }
        } else {
            int i;
            for (i = 0; i < rounds; ++i) {
                this.rowOne = String.valueOf(this.rowOne) + "<a href='/pugs/song/'>" + this.searchList.get(i).getTitle() + "</a>";
            }
            for (i = 0; i < rounds; ++i) {
                this.rowTwo = String.valueOf(this.rowTwo) + "<a href='/pugs/song/'>" + this.searchList.get(rounds + i).getTitle() + "</a>";
            }
            for (i = 0; i < rounds; ++i) {
                this.rowThree = String.valueOf(this.rowThree) + "<a href='/pugs/song/'>" + this.searchList.get(rounds * 2 + i).getTitle() + "</a>";
            }
            if (this.searchList.size() != 2) {
                this.rowOne = String.valueOf(this.rowOne) + "<a href='/pugs/song'>" + (Object)this.searchList.get(this.searchList.size() - 1) + "</a>";
                this.rowTwo = String.valueOf(this.rowTwo) + "<a href='/pugs/song'>" + (Object)this.searchList.get(this.searchList.size() - 1) + "</a>";
            }
        }
    }
}
