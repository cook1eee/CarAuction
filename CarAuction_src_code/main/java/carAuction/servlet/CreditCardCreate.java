package carAuction.servlet;

import carAuction.dal.*;
import carAuction.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/creditcardcreate")
public class CreditCardCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/CreditCardCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String userID = req.getParameter("userID");
        String CardNumber = req.getParameter("CardNumber");
        if (CardNumber == null || CardNumber.trim().isEmpty()) {
            messages.put("success", "Invalid CardNumber");
        } else {
        		try {
                	Users user = usersDao.getUserFromUserID(Integer.parseInt(userID));
                	
                	int ExpirationMonth = Integer.parseInt(req.getParameter("ExpirationMonth"));
                	int ExpirationYear = Integer.parseInt(req.getParameter("ExpirationYear"));
                	String NameOnCard = req.getParameter("NameOnCard");
                	String ZipCode = req.getParameter("ZipCode");
                	
                	CreditCards creditCard = new CreditCards(CardNumber, user, ExpirationMonth, ExpirationYear,  NameOnCard, ZipCode);
    	        	creditCard = creditCardsDao.create(creditCard);
    	        	
    	        	messages.put("success", "New CreditCard has been added !");
    	        	
    	        	req.setAttribute("creditCard", creditCard);
        		}catch (SQLException e) {
        			e.printStackTrace();
        			throw new IOException(e);
        		}
        		      
        }
        req.getRequestDispatcher("/CreditCardCreate.jsp").forward(req, resp);
        
    }
}
