package carAuction.servlet;

import carAuction.dal.*;
import carAuction.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/userloginpage")
public class UserLoginPage extends HttpServlet {
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao =UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Users> users = new ArrayList<Users>();
        
        // Retrieve and validate name.
        // email is retrieved from the URL query string.
        
        String email = "";
        String password = "";
        if (req.getParameter("userID") != null) {
        	int userID = -1;
            if (req.getParameter("userID") != null) {
                try {
                	userID = Integer.parseInt(req.getParameter("userID"));
                } catch (NumberFormatException e) {
                	e.printStackTrace();
        			throw new IOException(e);
                }
            }

            Users currentUser = null;
            try {
    			currentUser = usersDao.getUserFromUserID(userID);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
            email = currentUser.getEmail();
            password = currentUser.getpassword();
        } else {
        	email = req.getParameter("email");
            password = req.getParameter("password");
        }
        
        
        if (email == null || email.trim().isEmpty()) {
            messages.put("success", "Email address is required for SignIn.");
            req.getRequestDispatcher("/UserLoginPage.jsp").forward(req, resp);
        } else {
        	// Retrieve Users, and store as a message.
        	try {
            	users = usersDao.getUsersFromEmailPassword(email, password);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + email);
        	// Save the previous search term, so it can be used as the default
        	
        	
        	req.setAttribute("users", users);
            req.getRequestDispatcher("/UserHomePage.jsp").forward(req, resp);
        }
        
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Users> users = new ArrayList<Users>();
        
        String email = "";
        String password = "";
        if (req.getParameter("userID") != null) {
        	int userID = -1;
            if (req.getParameter("userID") != null) {
                try {
                	userID = Integer.parseInt(req.getParameter("userID"));
                } catch (NumberFormatException e) {
                	e.printStackTrace();
        			throw new IOException(e);
                }
            }

            Users currentUser = null;
            try {
    			currentUser = usersDao.getUserFromUserID(userID);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
            email = currentUser.getEmail();
            password = currentUser.getpassword();
        } else {
        	email = req.getParameter("email");
            password = req.getParameter("password");
        }
        
        if (email == null || email.trim().isEmpty()) {
            messages.put("success", "Email address is required for SignIn.");
            req.getRequestDispatcher("/UserLoginPage.jsp").forward(req, resp);
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	users = usersDao.getUsersFromEmailPassword(email, password);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	if (users.isEmpty()) {
				messages.put("success", "Email or Password is not correct");
				req.getRequestDispatcher("/UserLoginPage.jsp").forward(req, resp);
			} else {
				messages.put("success", "Displaying results for " + email);
				req.setAttribute("users", users);
	            
	            req.getRequestDispatcher("/UserHomePage.jsp").forward(req, resp);
			}
        }
        
    }
}
