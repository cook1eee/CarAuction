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



@WebServlet("/finduseractivities")
public class FindUserActivities extends HttpServlet {
	protected UserActivitiesDao userActivitiesDao;
	protected UsersDao usersDao;
	
	public void init() throws ServletException {
		userActivitiesDao = UserActivitiesDao.getInstance();
		usersDao = UsersDao.getInstance();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String userID = req.getParameter("userID");
        if (userID == null || userID.trim().isEmpty()) {
            messages.put("title", "Invalid username");
        } else {
        	try {
        		Users user = usersDao.getUserFromUserID(Integer.parseInt(userID));
        		String firstname = user.getFirstName();
            	
        		messages.put("title", "User Activities for " + firstname);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
        
        List<UserActivities> userActivities = new ArrayList<UserActivities>();
        
        try {
        	Users user = usersDao.getUserFromUserID(Integer.parseInt(userID));
        	userActivities = userActivitiesDao.getUserActivityForUser(user);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("UserActivities", userActivities);
        req.getRequestDispatcher("/UserActivities.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	    Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String userID = req.getParameter("userID");
        if (userID == null || userID.trim().isEmpty()) {
            messages.put("title", "Invalid username");
        } else {
        	try {
        		Users user = usersDao.getUserFromUserID(Integer.parseInt(userID));
        		String firstname = user.getFirstName();
            	
        		messages.put("title", "User Activities for " + firstname);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
        
        List<UserActivities> userActivities = new ArrayList<UserActivities>();
        
        try {
        	Users user = usersDao.getUserFromUserID(Integer.parseInt(userID));
        	userActivities = userActivitiesDao.getUserActivityForUser(user);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("userActivities", userActivities);
        req.getRequestDispatcher("/UserActivities.jsp").forward(req, resp);
	        
	}
	
}
