package carAuction.servlet;

import carAuction.dal.*;
import carAuction.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findforumsbyid")
public class FindForumsByID extends HttpServlet  {
	protected ForumsDao forumsDao;
	
	public void init() throws ServletException {
		forumsDao = ForumsDao.getInstance();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        List<Forums> forums = new ArrayList<Forums>();
        req.setAttribute("messages", messages);

        int forumid = -1;
        try {
        	forumid = Integer.parseInt(req.getParameter("forumid"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }
        
        if (forumid < 0) {
            messages.put("success", "Please enter valid Forum ID.");
        } else {
        	try {
        		Forums forum = forumsDao.getForumById(forumid);
        		messages.put("success", "Displaying results for Forum" + forumid);
        		forums.add(forum);
        		req.setAttribute("forums", forums);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
       
        req.getRequestDispatcher("/FindForums.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        List<Forums> forums = new ArrayList<Forums>();
        req.setAttribute("messages", messages);

        int forumid = -1;
        try {
        	forumid = Integer.parseInt(req.getParameter("forumid"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }
        
        if (forumid < 0) {
            messages.put("success", "Please enter valid Forum ID.");
        } else {
        
        	try {
        		Forums forum = forumsDao.getForumById(forumid);
        		messages.put("success", "Displaying results for Forum" + forumid);
        		forums.add(forum);
        		req.setAttribute("forums", forums);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
       
        req.getRequestDispatcher("/FindForums.jsp").forward(req, resp);
	}
}
