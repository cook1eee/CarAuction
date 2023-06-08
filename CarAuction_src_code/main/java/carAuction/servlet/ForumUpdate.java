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

@WebServlet("/forumupdate")
public class ForumUpdate extends HttpServlet {
	protected ForumsDao forumsDao;
	
	@Override
	public void init() throws ServletException {
		forumsDao = ForumsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String forumid = req.getParameter("forumid");
        if (forumid == null || forumid.trim().isEmpty()) {
            messages.put("success", "Invalid Forum ID.");
        } else {
        	try {
        		Forums forum = forumsDao.getForumById(Integer.parseInt(forumid));
        		if(forum == null) {
        			messages.put("success", "Forum does not exist.");
        		}
        		req.setAttribute("forum", forum);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ForumUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		 // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String forumid = req.getParameter("forumid");
        if (forumid == null || forumid.trim().isEmpty()) {
            messages.put("success", "Invalid Forum ID.");
        } else {
        	try {
        		Forums forum = forumsDao.getForumById(Integer.parseInt(forumid));
        		if(forum == null) {
        			messages.put("success", "Forum does not exist. No update to perform.");
        		} else {
        			String newForum = req.getParameter("newforum");
        			if (newForum == null || newForum.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid new question:");
        	        } else {
        	        	forum = forumsDao.updateForum(forum, newForum);
        	        	messages.put("success", "Successfully updated " + forum.getForumID());
        	        }
        		}
        		req.setAttribute("forum", forum);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ForumUpdate.jsp").forward(req, resp);
    }
	
}
