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


@WebServlet("/usercreditcards")
public class UserCreditCards extends HttpServlet {
	
	protected CreditCardsDao creditCardsDao;
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		creditCardsDao = CreditCardsDao.getInstance();
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
            	
        		messages.put("title", "CreditCards for " + firstname);
        		
            }catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);}
        				
        }
        
        
        List<CreditCards> creditCards = new ArrayList<CreditCards>();
        try {
        	Users user = new Users(Integer.parseInt(userID));
        	creditCards = creditCardsDao.getCreditCardsByUserID(user.getUserID());
        	
        	req.setAttribute("creditCards", creditCards);
            req.setAttribute("user", user);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.getRequestDispatcher("/UserCreditCards.jsp").forward(req, resp);
	}
}
