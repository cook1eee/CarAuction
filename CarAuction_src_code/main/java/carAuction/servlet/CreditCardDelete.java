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


@WebServlet("/creditcarddelete")
public class CreditCardDelete extends HttpServlet {
	
	protected CreditCardsDao creditCardsDao;
	
	@Override
	public void init() throws ServletException {
		creditCardsDao = CreditCardsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        
        messages.put("title", "Delete CeditCard");        
        req.getRequestDispatcher("/CreditCardDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

       
        String cardNumber = req.getParameter("cardNumber");
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            messages.put("title", "Invalid CardNumber");
            messages.put("disableSubmit", "true");
        } else {
        	
        	CreditCards creditCard = new CreditCards(cardNumber);
	        try {
	        	creditCard = creditCardsDao.delete(creditCard);
	        	// Update the message.
		        if (creditCard == null) {
		            messages.put("title", "Successfully deleted CreditCard with number " + cardNumber);
		            messages.put("disableSubmit", "true");
		            
		            req.getRequestDispatcher("/UserCreditCards.jsp").forward(req, resp);
		        } else {
		        	messages.put("title", "Failed to CreditCard with number" + cardNumber);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        
    }
}
