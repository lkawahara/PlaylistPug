package playlistpug.legacy;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(value={"/song/*"})
public class SongServlet
extends HttpServlet {
    private static final long serialVersionUID = 1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] path = request.getPathInfo().split("/");
        String songPath = "";
        if (path.length > 2) {
            for (int i = 2; i <= path.length - 2; ++i) {
                songPath = String.valueOf(songPath) + path[i] + "/";
            }
            songPath = songPath.substring(0, songPath.length() - 1);
        }
        String out = "";
        out = path[1].equals("style") ? "/resources/style.css" : "/resources/test.wav";
        RequestDispatcher rd = request.getRequestDispatcher(out);
        rd.forward((ServletRequest)request, (ServletResponse)response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
