package carAuction.servlet;

import carAuction.dal.*;
import carAuction.dal.AuctionsDao.Pair;
import carAuction.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * FindUsers is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/findusers.
 */
@WebServlet("/findbids")
public class FindBids extends HttpServlet {
	
	protected BidsDao bidsDao;
	protected UsersDao usersDao;
	protected AuctionsDao auctionsDao;
	protected CarsDao carsDao;
	protected ForumsDao forumsDao;
	
	@Override
	public void init() throws ServletException {
		bidsDao = BidsDao.getInstance();
		usersDao = UsersDao.getInstance();
		auctionsDao = AuctionsDao.getInstance();
		carsDao = CarsDao.getInstance();
		forumsDao = ForumsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Bids> bids = new ArrayList<>();
        List<Pair<Auctions, Cars>> auctionsAndCars = new ArrayList<>();
        List<Forums> forums = new ArrayList<Forums>();
        
        int auctionid = -1;
        try {
        	auctionid = Integer.parseInt(req.getParameter("auctionid"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }

        if (auctionid < 0) {
            messages.put("success", "Please enter valid auction ID.");
        } else {
        	try {
        		Auctions auction = auctionsDao.getAuctionById(auctionid);
        		Cars car = carsDao.getCarById(auction.getCar().getCarID());
        		auctionsAndCars.add(auctionsDao.new Pair<Auctions, Cars>(auction, car));
        		bids = bidsDao.getBidsForAuction(auctionsDao.getAuctionById(auctionid));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	try {
        		Auctions auction = auctionsDao.getAuctionById(auctionid);
        		forums = forumsDao.getForumForAuction(auction);
        		messages.put("success", "Displaying Forums posted for Autcion " + auctionid);
        		req.setAttribute("forums", forums);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for Auction " + auctionid);
        }
        req.setAttribute("bids", bids);
        req.setAttribute("auctionsAndCars", auctionsAndCars);
        req.setAttribute("forums", forums);
        
        req.getRequestDispatcher("/FindDetailAuction.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Bids> bids = new ArrayList<>();
        List<Pair<Auctions, Cars>> auctionsAndCars = new ArrayList<>();
        List<Forums> forums = new ArrayList<Forums>();
        
        int auctionid = -1;
        int userID = -1;
        float bidPrice = -1.0F;
        try {
        	auctionid = Integer.parseInt(req.getParameter("auctionid"));
        	userID = Integer.parseInt(req.getParameter("userID"));
        	bidPrice = Float.parseFloat(req.getParameter("bidPrice"));
        } catch (NumberFormatException e) {
        	e.printStackTrace();
			throw new IOException(e);
        }

        if (auctionid < 0) {
            messages.put("success", "Please enter valid auction ID.");
        } else if (userID < 0) {
            messages.put("success", "Please enter valid user ID.");
        } else if (bidPrice < 0) {
            messages.put("success", "Please enter valid bid price.");
        } else {
        	float currentMaxBidPrice = -1.0F;
        	try {
	        	currentMaxBidPrice = bidsDao.getMaxPriceForAuction(auctionid);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
            if (currentMaxBidPrice >= bidPrice) {
            	messages.put("success", "Please enter valid bid price.");
            } else {
            	try {
            		Auctions auction = auctionsDao.getAuctionById(auctionid);
            		Cars car = carsDao.getCarById(auction.getCar().getCarID());
            		auctionsAndCars.add(auctionsDao.new Pair<Auctions, Cars>(auction, car));
            		Bids bid = new Bids(auction, usersDao.getUserFromUserID(userID), new Date(), bidPrice);
            		bid = bidsDao.create(bid);
            		auctionsDao.updateCurrentHighestPrice(auction, bidPrice);
            		bids = bidsDao.getBidsForAuction(auction);
                } catch (SQLException e) {
        			e.printStackTrace();
        			throw new IOException(e);
                }
            	try {
            		Auctions auction = auctionsDao.getAuctionById(auctionid);
            		forums = forumsDao.getForumForAuction(auction);
            		messages.put("success", "Displaying Forums posted for Autcion " + auctionid);
            		req.setAttribute("forums", forums);
                } catch (SQLException e) {
        			e.printStackTrace();
        			throw new IOException(e);
                }
            	messages.put("success", "Displaying results for Auction " + auctionid);
            	// Save the previous search term, so it can be used as the default
            	// in the input box when rendering FindUsers.jsp.
            	messages.put("userID", Integer.toString(userID));
            }
        	
        }
        req.setAttribute("bids", bids);
        req.setAttribute("auctionsAndCars", auctionsAndCars);
        req.setAttribute("forums", forums);
        
        req.getRequestDispatcher("/FindDetailAuction.jsp").forward(req, resp);
    }
}
