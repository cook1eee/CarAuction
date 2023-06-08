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


@WebServlet("/userbids")
public class UserBids extends HttpServlet {
	
	protected BidsDao bidsDao;
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		bidsDao = BidsDao.getInstance();
		usersDao =UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserID.
        String userID = req.getParameter("userID");
        if (userID == null || userID.trim().isEmpty()) {
            messages.put("title", "Invalid username.");
        } else {
        	try{
        		Users user = usersDao.getUserFromUserID(Integer.parseInt(userID));
        		String firstname = user.getFirstName();
            	
        		messages.put("title", "Bids for " + firstname);
        		
            }catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);}
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<Bids> bids = new ArrayList<Bids>();
        try {
        	Users user = new Users(Integer.parseInt(userID));
        	bids = bidsDao.getBidsForUser(user);
        	
        	req.setAttribute("user", user);
        	req.setAttribute("bids", bids);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.getRequestDispatcher("/UserBids.jsp").forward(req, resp);
	}
}
