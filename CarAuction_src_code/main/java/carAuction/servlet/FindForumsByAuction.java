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

@WebServlet("/findforumsbyauction")
public class FindForumsByAuction extends HttpServlet  {
	protected AuctionsDao auctionsDao;
	protected ForumsDao forumsDao;
	
	public void init() throws ServletException {
		auctionsDao = AuctionsDao.getInstance();
		forumsDao = ForumsDao.getInstance();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Forums> forums = new ArrayList<Forums>();

        int auctionid = -1;
        try {
        	auctionid = Integer.parseInt(req.getParameter("auctionid"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }
        
        if (auctionid < 0) {
            messages.put("success", "Please enter valid Auction ID.");
        } else {
        
        	try {
        		Auctions auction = auctionsDao.getAuctionById(auctionid);
        		forums = forumsDao.getForumForAuction(auction);
        		messages.put("success", "Displaying Forums posted for Autcion " + auctionid);
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

        int auctionid = -1;
        try {
        	auctionid = Integer.parseInt(req.getParameter("auctionid"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }
        
        if (auctionid < 0) {
            messages.put("success", "Please enter valid Auction ID.");
        } else {
        
        	try {
        		Auctions auction = auctionsDao.getAuctionById(auctionid);
        		forums = forumsDao.getForumForAuction(auction);
        		messages.put("success", "Displaying Forums posted for Autcion " + auctionid);
        		req.setAttribute("forums", forums);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
       
        req.getRequestDispatcher("/FindForums.jsp").forward(req, resp);
	}
}
