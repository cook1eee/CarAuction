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

@WebServlet("/findforumsbyuser")
public class FindForumsByUser extends HttpServlet  {
	protected UsersDao usersDao;
	protected ForumsDao forumsDao;
	
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
		forumsDao = ForumsDao.getInstance();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Forums> forums = new ArrayList<Forums>();

        int userid = -1;
        try {
        	userid = Integer.parseInt(req.getParameter("userid"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }
        
        if (userid < 0) {
            messages.put("success", "Please enter valid User ID.");
        } else {
        
        	try {
        		Users user = usersDao.getUserFromUserID(userid);
        		forums = forumsDao.getForumForUser(user);
        		messages.put("success", "Displaying Forums posted by User " + userid);
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
        req.setAttribute("messages", messages);
        List<Forums> forums = new ArrayList<Forums>();

        int userid = -1;
        try {
        	userid = Integer.parseInt(req.getParameter("userid"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }
        
        if (userid < 0) {
            messages.put("success", "Please enter valid User ID.");
        } else {
        
        	try {
        		Users user = usersDao.getUserFromUserID(userid);
        		forums = forumsDao.getForumForUser(user);
        		messages.put("success", "Displaying Forums posted by User" + userid);
        		req.setAttribute("forums", forums);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
       
        req.getRequestDispatcher("/FindForums.jsp").forward(req, resp);
	}
}
