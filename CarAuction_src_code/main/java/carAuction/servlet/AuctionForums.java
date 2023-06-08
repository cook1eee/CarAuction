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


@WebServlet("/forumsforauction")
public class AuctionForums extends HttpServlet {
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

        String auctionid = req.getParameter("auctionid");
        List<Forums> forums = new ArrayList<Forums>();
        
        if (auctionid == null || auctionid.trim().isEmpty()) {
            messages.put("title", "Invalid username");
        } else {
        	try {
        		Auctions auction = auctionsDao.getAuctionById(Integer.parseInt(auctionid));
        	    forums = forumsDao.getForumForAuction(auction);
        	    
        		messages.put("title", "Q&A for current Auction ID: " + auction.getAuctionID());
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
      
        req.setAttribute("forums", forums);
        req.getRequestDispatcher("/QAforAuction.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String auctionid = req.getParameter("auctionid");
        List<Forums> forums = new ArrayList<Forums>();
        
        if (auctionid == null || auctionid.trim().isEmpty()) {
            messages.put("title", "Invalid auction id");
        } else {
        	try {
        		Auctions auction = auctionsDao.getAuctionById(Integer.parseInt(auctionid));
        		forums = forumsDao.getForumForAuction(auction);
        		
        		messages.put("title", "Q&A for current Auction ID: " + auction.getAuctionID());
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
        

        req.setAttribute("forums", forums);
        req.getRequestDispatcher("/AuctionForums.jsp").forward(req, resp);
	}
}















