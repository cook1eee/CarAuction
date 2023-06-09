package carAuction.servlet;

import carAuction.dal.*;
import carAuction.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/userpasswordupdate")
public class UserPasswordUpdate extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

//         Retrieve user and validate.
        String userID = req.getParameter("userID");
        String password = req.getParameter("password");
        if (password == null || password.trim().isEmpty()) {
            messages.put("success", "Please enter a valid userID.");
        } else {
        	try {
        		Users user = usersDao.getUserFromUserID(Integer.parseInt(userID));
        		if(user == null) {
        			messages.put("success", "UserID does not exist.");
        		}
        		req.setAttribute("user", user);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserPasswordUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String userID = req.getParameter("userID");      
        if (userID == null || userID.trim().isEmpty()) {
            messages.put("success", "Please enter a valid userID.");
        } else {
        	try {
        		Users user = usersDao.getUserFromUserID(Integer.parseInt(userID));
        		if(user == null) {
        			messages.put("success", "UserID does not exist. No update to perform.");
        		} else {
        			String newPassword = req.getParameter("password");	
        			if (newPassword == null || newPassword.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid new Password.");
        	        } else {
        	        	user = usersDao.updatepassword(user, newPassword);
        	        	messages.put("success", "Password has been updated successfully.");       
        	        }
        		}
			req.setAttribute("user", user);
		} catch (SQLException e) {
		e.printStackTrace();
		throw new IOException(e);
		}
		}
		
		req.getRequestDispatcher("/UserPasswordUpdate.jsp").forward(req, resp);
		}
		}
