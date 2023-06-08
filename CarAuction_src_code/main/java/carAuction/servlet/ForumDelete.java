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

@WebServlet("/forumdelete")
public class ForumDelete extends HttpServlet {

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
        
        messages.put("title", "Delete Forum");
        req.getRequestDispatcher("/ForumDelete.jsp").forward(req, resp);
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
            messages.put("disableSubmit", "true");
        } else {
        	Forums forum = new Forums(Integer.parseInt(forumid));
        	try {
        		forum = forumsDao.delete(forum);
        		if(forum == null) {
        			messages.put("title", "Successfully deleted Forum " + forumid);
        			messages.put("disableSubmit", "true");
        		} else {
        	        messages.put("title", "Failed to delete Forum " + forumid);
        	        messages.put("disableSubmit", "false");
        		}
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ForumDelete.jsp").forward(req, resp);
    }
}
