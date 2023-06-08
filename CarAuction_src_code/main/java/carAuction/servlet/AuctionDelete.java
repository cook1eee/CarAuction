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


@WebServlet("/auctiondelete")
public class AuctionDelete extends HttpServlet {
	
	protected AuctionsDao auctionsDao;
	
	@Override
	public void init() throws ServletException {
		auctionsDao = AuctionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        
        messages.put("title", "Delete a Auction");        
        req.getRequestDispatcher("/AuctionDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

       
        String auctionID = req.getParameter("auctionID");
        if (auctionID == null || auctionID.trim().isEmpty()) {
            messages.put("title", "Invalid AuctionID");
            messages.put("disableSubmit", "true");
        } else {
        	
        	Auctions auction = new Auctions(Integer.parseInt(auctionID));
	        try {
	        	auction = auctionsDao.delete(auction);
	        	// Update the message.
		        if (auction == null) {
		            messages.put("title", "Successfully deleted Auction with ID " + auctionID);
		            messages.put("disableSubmit", "true");
		            
		            req.getRequestDispatcher("/UserAuctions.jsp").forward(req, resp);
		        } else {
		        	messages.put("title", "Failed to Auction with ID " + auctionID);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        
    }
}
